package windowbuilderresta;

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

public class calculaResta {

	protected Shell shell;
	private Label lblCalculadoraResta;
	private Label lblPrimerNumero;
	private Label lblSegundoNumero;
	private Label lblResultado;
	private Text textPrimerNumero;
	private Text textSegundoNumero;
	private Text textResultado;
	private Button btnRestar;
	private Button btnLimpiar;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			calculaResta window = new calculaResta();
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
		
		lblCalculadoraResta = new Label(shell, SWT.NONE);
		lblCalculadoraResta.setFont(SWTResourceManager.getFont("Tahoma", 20, SWT.BOLD));
		lblCalculadoraResta.setBounds(10, 10, 313, 38);
		lblCalculadoraResta.setText("CALCULADORA RESTA");
		
		lblPrimerNumero = new Label(shell, SWT.NONE);
		lblPrimerNumero.setBounds(42, 70, 96, 15);
		lblPrimerNumero.setText("Primer Numero:");
		
		lblSegundoNumero = new Label(shell, SWT.NONE);
		lblSegundoNumero.setBounds(42, 114, 102, 15);
		lblSegundoNumero.setText("Segundo Numero:");
		
		lblResultado = new Label(shell, SWT.NONE);
		lblResultado.setBounds(42, 162, 55, 15);
		lblResultado.setText("Resultado:");
		
		textPrimerNumero = new Text(shell, SWT.BORDER);
		textPrimerNumero.setBounds(181, 70, 76, 21);
		
		textSegundoNumero = new Text(shell, SWT.BORDER);
		textSegundoNumero.setBounds(181, 114, 76, 21);
		
		textResultado = new Text(shell, SWT.BORDER);
		textResultado.setEditable(false);
		textResultado.setBounds(181, 162, 76, 21);
		
		btnRestar = new Button(shell, SWT.NONE);
		btnRestar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				try {
					int a,b,c;
					a = Integer.parseInt(textPrimerNumero.getText());
					b = Integer.parseInt(textSegundoNumero.getText());
					c = a - b;
					textResultado.setText(String.valueOf(c));
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Enter erroni", "Avis", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnRestar.setBounds(46, 214, 75, 25);
		btnRestar.setText("Restar");
		
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
		btnLimpiar.setBounds(195, 214, 75, 25);
		btnLimpiar.setText("Limpiar");

	}

}
