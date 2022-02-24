package windowbuilderoperaciones;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class CalculaTodo {

	protected Shell shell;
	private Label lblCalculoOperaciones;
	private Label lblPrimerNumero;
	private Label lblSegundoNumero;
	private Button btnCalcular;
	private Text textPrimerNumero;
	private Text textSegundoNumero;
	private Label lblResultados;
	private Text textResultado1;
	private Text textResultado2;
	private Text textResultado3;
	private Text textResultado4;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CalculaTodo window = new CalculaTodo();
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
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		lblCalculoOperaciones = new Label(shell, SWT.NONE);
		lblCalculoOperaciones.setFont(SWTResourceManager.getFont("Tahoma", 20, SWT.BOLD));
		lblCalculoOperaciones.setBounds(0, 0, 340, 42);
		lblCalculoOperaciones.setText("CALCULO OPERACIONES");
		
		lblPrimerNumero = new Label(shell, SWT.NONE);
		lblPrimerNumero.setBounds(10, 63, 85, 15);
		lblPrimerNumero.setText("Primer Numero:");
		
		lblSegundoNumero = new Label(shell, SWT.NONE);
		lblSegundoNumero.setBounds(10, 114, 97, 15);
		lblSegundoNumero.setText("Segundo Numero:");
		
		btnCalcular = new Button(shell, SWT.NONE);
		btnCalcular.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				double a,b,c,d,f,g; 
				a = Double.parseDouble(textPrimerNumero.getText());
				b = Double.parseDouble(textSegundoNumero.getText());
				c = a + b;
				d = a - b;
				f = a * b;
				g = a / b;
				
				textResultado1.setText(String.valueOf(c));
				textResultado2.setText(String.valueOf(d));
				textResultado3.setText(String.valueOf(f));
				textResultado4.setText(String.valueOf(g));
				
				
			}
		});
		btnCalcular.setBounds(166, 160, 75, 25);
		btnCalcular.setText("Calcular");
		
		textPrimerNumero = new Text(shell, SWT.BORDER);
		textPrimerNumero.setBounds(166, 63, 76, 21);
		
		textSegundoNumero = new Text(shell, SWT.BORDER);
		textSegundoNumero.setBounds(165, 114, 76, 21);
		
		lblResultados = new Label(shell, SWT.NONE);
		lblResultados.setBounds(10, 195, 66, 15);
		lblResultados.setText("Resultados:");
		
		textResultado1 = new Text(shell, SWT.BORDER);
		textResultado1.setBounds(10, 228, 76, 21);
		
		textResultado2 = new Text(shell, SWT.BORDER);
		textResultado2.setBounds(105, 228, 76, 21);
		
		textResultado3 = new Text(shell, SWT.BORDER);
		textResultado3.setBounds(202, 228, 76, 21);
		
		textResultado4 = new Text(shell, SWT.BORDER);
		textResultado4.setBounds(309, 228, 76, 21);

	}

}
