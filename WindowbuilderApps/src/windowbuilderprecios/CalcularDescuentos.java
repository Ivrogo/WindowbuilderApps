package windowbuilderprecios;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class CalcularDescuentos {

	protected Shell shlDescuentos;
	private Label lblPrecio;
	private Text textPrecio;
	private Button btnTotal;
	private Button btnLimpiar;
	private Label lblSalida;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CalcularDescuentos window = new CalcularDescuentos();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlDescuentos.open();
		shlDescuentos.layout();
		while (!shlDescuentos.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlDescuentos = new Shell();
		shlDescuentos.setSize(450, 300);
		shlDescuentos.setText("Descuentos");
		
		lblPrecio = new Label(shlDescuentos, SWT.NONE);
		lblPrecio.setBounds(10, 10, 55, 15);
		lblPrecio.setText("Precio:");
		
		textPrecio = new Text(shlDescuentos, SWT.BORDER);
		textPrecio.setText("100");
		textPrecio.setBounds(87, 10, 76, 21);
		
		btnTotal = new Button(shlDescuentos, SWT.NONE);
		btnTotal.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				double precio = Double.parseDouble(textPrecio.getText());
				
				//Proceso
				double dto = 25; 
				if (precio < 50) {
					dto = 5;
				} else if (precio <= 150) {
					dto = 10;
				}
				
				precio -= precio * dto / 100;
				String res = String.format("Total: %.2f €", precio);
				lblSalida.setText(res);
			}
		});
		btnTotal.setBounds(10, 56, 75, 25);
		btnTotal.setText("Total");
		
		btnLimpiar = new Button(shlDescuentos, SWT.NONE);
		btnLimpiar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				textPrecio.setText("100");
				lblSalida.setText("---");
			}
		});
		btnLimpiar.setBounds(125, 56, 75, 25);
		btnLimpiar.setText("Limpiar");
		
		lblSalida = new Label(shlDescuentos, SWT.NONE);
		lblSalida.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		lblSalida.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblSalida.setFont(SWTResourceManager.getFont("Tahoma", 20, SWT.BOLD));
		lblSalida.setAlignment(SWT.CENTER);
		lblSalida.setBounds(10, 125, 249, 90);
		lblSalida.setText("---");

	}

}
