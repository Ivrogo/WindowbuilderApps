package windowbuilderstrings;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class juntaLaFrase {

	protected Shell shell;
	private Label lblCreadorDeFrases;
	private Label lblPrimeraPalabrafrase;
	private Text textPrimera;
	private Label lblSegundaPalabrafrase;
	private Text textSegunda;
	private Button btnCreaTuFrase;
	private Text textFrase;
	private Label lblFrase;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			juntaLaFrase window = new juntaLaFrase();
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
		shell.setLayout(null);
		
		lblCreadorDeFrases = new Label(shell, SWT.NONE);
		lblCreadorDeFrases.setFont(SWTResourceManager.getFont("Tahoma", 20, SWT.BOLD));
		lblCreadorDeFrases.setBounds(0, 0, 300, 44);
		lblCreadorDeFrases.setText("CREADOR DE FRASES");
		
		lblPrimeraPalabrafrase = new Label(shell, SWT.NONE);
		lblPrimeraPalabrafrase.setBounds(34, 70, 124, 20);
		lblPrimeraPalabrafrase.setText("Primera palabra/frase:");
		
		textPrimera = new Text(shell, SWT.BORDER);
		textPrimera.setBounds(208, 69, 76, 21);
		
		lblSegundaPalabrafrase = new Label(shell, SWT.NONE);
		lblSegundaPalabrafrase.setBounds(34, 120, 124, 15);
		lblSegundaPalabrafrase.setText("Segunda palabra/frase:");
		
		textSegunda = new Text(shell, SWT.BORDER);
		textSegunda.setBounds(208, 120, 76, 21);
		
		btnCreaTuFrase = new Button(shell, SWT.NONE);
		btnCreaTuFrase.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String a = textPrimera.getText();
				String b = textSegunda.getText();
				
				textFrase.setText(a+ " " +b);
			}
		});
		btnCreaTuFrase.setBounds(158, 169, 75, 25);
		btnCreaTuFrase.setText("Crea tu frase");
		
		textFrase = new Text(shell, SWT.BORDER);
		textFrase.setEditable(false);
		textFrase.setBounds(34, 218, 367, 20);
		
		lblFrase = new Label(shell, SWT.NONE);
		lblFrase.setBounds(32, 197, 55, 15);
		lblFrase.setText("Frase:");

	}
}
