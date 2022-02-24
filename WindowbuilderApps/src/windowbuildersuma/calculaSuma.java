package windowbuildersuma;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class calculaSuma {

	protected Shell shell;
	private Label lblCalculadoraSuma;
	private Label lblPrimerNumero;
	private Label lblSegundoNumero;
	private Text textPrimerNumero;
	private Text textSegundoNumero;
	private Label lblResultado;
	private Text textResultado;
	private Button btnSumar;
	private Button btnLimpiar;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			calculaSuma window = new calculaSuma();
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
		
		lblCalculadoraSuma = new Label(shell, SWT.NONE);
		lblCalculadoraSuma.setFont(SWTResourceManager.getFont("Tahoma", 14, SWT.BOLD));
		lblCalculadoraSuma.setBounds(0, 10, 253, 23);
		lblCalculadoraSuma.setText("CALCULADORA SUMA");
		
		lblPrimerNumero = new Label(shell, SWT.NONE);
		lblPrimerNumero.setBounds(35, 70, 88, 15);
		lblPrimerNumero.setText("Primer numero:");
		
		lblSegundoNumero = new Label(shell, SWT.NONE);
		lblSegundoNumero.setBounds(28, 109, 95, 15);
		lblSegundoNumero.setText("Segundo numero:");
		
		textPrimerNumero = new Text(shell, SWT.BORDER);
		textPrimerNumero.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		textPrimerNumero.setBounds(179, 70, 74, 21);
		
		textSegundoNumero = new Text(shell, SWT.BORDER);
		textSegundoNumero.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		textSegundoNumero.setBounds(179, 104, 74, 23);
		
		lblResultado = new Label(shell, SWT.NONE);
		lblResultado.setBounds(35, 148, 55, 15);
		lblResultado.setText("Resultado: ");
		
		textResultado = new Text(shell, SWT.BORDER);
		textResultado.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		textResultado.setEditable(false);
		textResultado.setBounds(179, 148, 95, 21);
		
		btnSumar = new Button(shell, SWT.NONE);
		btnSumar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					int a,b,c;
					 a = Integer.parseInt(textPrimerNumero.getText());
					 b = Integer.parseInt(textSegundoNumero.getText());
					 c = a + b;
					 textResultado.setText(String.valueOf(c));
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "enter erroni", "Avís", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnSumar.setBounds(67, 203, 75, 25);
		btnSumar.setText("Sumar");
		
		btnLimpiar = new Button(shell, SWT.NONE);
		btnLimpiar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//Limpiamos las cajas
				textPrimerNumero.setText("");
				textSegundoNumero.setText("");
				textResultado.setText("");
			}
		});
		btnLimpiar.setBounds(199, 203, 75, 25);
		btnLimpiar.setText("Limpiar");

	}
}
