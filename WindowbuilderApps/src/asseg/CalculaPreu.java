package asseg;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class CalculaPreu {

	protected Shell shlAsseguradoraCotxes;
	private Group grpDadesVehicle;
	private Text txtCavalls;
	private Combo comboCombustible;
	private Label lblCavalls;
	private Label lblCombustible;
	private Button btnAcceptar;
	private Group grpBonificacions;
	private Label lblAntiguitatCarnet;
	private Label lblUltimComunicat;
	private Text txtAntiguitatCarnet;
	private Text txtUltimComunicat;
	private Label lblAnysCarnet;
	private Label lblAnysUltimComunicat;
	private Button btnCalcular_1;
	private Button btnRestablir;
	private Label lblImport;
	private Text txtImportAnual;
	private Text txtImportTrimestral;
	private Text txtImportMensual;
	private Label lblAnual;
	private Label lblTrimestral;
	private Label lblMensual;
	private Group grpAlertes;
	private Text txtError;
	private String missatge;
	private int numCavalls;
	private int indicadorCombus;
	private double modalitat;
	private int anysCarnet;
	private int anysComunicat;
	private Label lblBonificacionsAplicades;
	private Text txtBonificacionsAplicades;
	private Label lbltotsElsImports;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CalculaPreu window = new CalculaPreu();
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
		shlAsseguradoraCotxes.open();
		shlAsseguradoraCotxes.layout();
		while (!shlAsseguradoraCotxes.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlAsseguradoraCotxes = new Shell();
		shlAsseguradoraCotxes.setSize(450, 500);
		shlAsseguradoraCotxes.setText("Asseguradora Cotxes");
		
		grpDadesVehicle = new Group(shlAsseguradoraCotxes, SWT.NONE);
		grpDadesVehicle.setText("Dades Vehicle");
		grpDadesVehicle.setBounds(10, 10, 414, 112);
		
		txtCavalls = new Text(grpDadesVehicle, SWT.BORDER);
		txtCavalls.setToolTipText("Introduir els cavalls del cotxe");
		txtCavalls.setBounds(97, 29, 130, 21);

		
		lblCavalls = new Label(grpDadesVehicle, SWT.NONE);
		lblCavalls.setBounds(10, 32, 55, 15);
		lblCavalls.setText("Cavalls");
		
		comboCombustible = new Combo(grpDadesVehicle, SWT.NONE);
		comboCombustible.setBounds(97, 72, 130, 23);
		comboCombustible.setItems(new String[] {"Benzina", "Diesel", "H\u00EDbrid", "El\u00E8ctric"});
		
		lblCombustible = new Label(grpDadesVehicle, SWT.NONE);
		lblCombustible.setBounds(10, 75, 81, 15);
		lblCombustible.setText("Combustible");
		
				
		btnAcceptar = new Button(grpDadesVehicle, SWT.NONE);
		btnAcceptar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(validaParametres()) {
					habilitarParametres(false);
					habilitarCalculs(true);
					txtError.setText("");
				}else {
					txtError.setText(missatge);
				}
			}
		});
		btnAcceptar.setBounds(313, 49, 75, 25);
		btnAcceptar.setText("Acceptar");

		
		grpBonificacions = new Group(shlAsseguradoraCotxes, SWT.NONE);
		grpBonificacions.setEnabled(false);
		grpBonificacions.setText("Bonificacions");
		grpBonificacions.setBounds(10, 128, 414, 253);
		
		
		lblAntiguitatCarnet = new Label(grpBonificacions, SWT.NONE);
		lblAntiguitatCarnet.setBounds(10, 43, 101, 15);
		lblAntiguitatCarnet.setText("Antiguitat carnet:");
		
		txtAntiguitatCarnet = new Text(grpBonificacions, SWT.BORDER);
		txtAntiguitatCarnet.setEditable(false);
		txtAntiguitatCarnet.setToolTipText("Introduir els anys que fa que té el carnet");
		txtAntiguitatCarnet.setBounds(117, 40, 91, 21);
		
		lblAnysCarnet = new Label(grpBonificacions, SWT.NONE);
		lblAnysCarnet.setBounds(212, 43, 55, 15);
		lblAnysCarnet.setText("anys");		
		
		lblUltimComunicat = new Label(grpBonificacions, SWT.NONE);
		lblUltimComunicat.setBounds(10, 83, 150, 15);
		lblUltimComunicat.setText("\u00DAltim comunicat accident:");
		
		txtUltimComunicat = new Text(grpBonificacions, SWT.BORDER);
		txtUltimComunicat.setEditable(false);
		txtUltimComunicat.setToolTipText("Introduir els anys que fa que va fer l'últim comunicat");
		txtUltimComunicat.setBounds(161, 80, 47, 21);
			
		lblAnysUltimComunicat = new Label(grpBonificacions, SWT.NONE);
		lblAnysUltimComunicat.setText("anys");
		lblAnysUltimComunicat.setBounds(214, 83, 55, 15);
		
		
		btnCalcular_1 = new Button(grpBonificacions, SWT.NONE);
		btnCalcular_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(validaCalculs()) {
					calculs();
					txtError.setText("");
				}else {
					netejaSortida();
					txtError.setText(missatge);
				}
			}
		});
		btnCalcular_1.setText("Calcular");
		btnCalcular_1.setBounds(313, 38, 75, 25);
		
		btnRestablir = new Button(grpBonificacions, SWT.NONE);
		btnRestablir.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				habilitarParametres(true);
				habilitarCalculs(false);
			}
		});
		btnRestablir.setBounds(313, 78, 75, 25);
		btnRestablir.setText("Restablir");
		
		lblImport = new Label(grpBonificacions, SWT.NONE);
		lblImport.setBounds(10, 137, 47, 15);
		lblImport.setText("IMPORT");
		
		lbltotsElsImports = new Label(grpBonificacions, SWT.NONE);
		lbltotsElsImports.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.NORMAL));
		lbltotsElsImports.setBounds(58, 138, 119, 15);
		lbltotsElsImports.setText("(tots els imports en \u20AC)");
		
		txtImportAnual = new Text(grpBonificacions, SWT.BORDER);
		txtImportAnual.setEditable(false);
		txtImportAnual.setEnabled(false);
		txtImportAnual.setBounds(10, 181, 76, 21);
		
		txtImportTrimestral = new Text(grpBonificacions, SWT.BORDER);
		txtImportTrimestral.setEditable(false);
		txtImportTrimestral.setEnabled(false);
		txtImportTrimestral.setBounds(161, 181, 76, 21);
		
		txtImportMensual = new Text(grpBonificacions, SWT.BORDER);
		txtImportMensual.setEditable(false);
		txtImportMensual.setEnabled(false);
		txtImportMensual.setBounds(312, 181, 76, 21);
		
		lblAnual = new Label(grpBonificacions, SWT.NONE);
		lblAnual.setBounds(10, 160, 55, 15);
		lblAnual.setText("Anual");
		
		lblTrimestral = new Label(grpBonificacions, SWT.NONE);
		lblTrimestral.setBounds(161, 160, 55, 15);
		lblTrimestral.setText("Trimestral");
		
		lblMensual = new Label(grpBonificacions, SWT.NONE);
		lblMensual.setBounds(313, 160, 55, 15);
		lblMensual.setText("Mensual");
		
		lblBonificacionsAplicades = new Label(grpBonificacions, SWT.NONE);
		lblBonificacionsAplicades.setBounds(10, 225, 136, 15);
		lblBonificacionsAplicades.setText("Bonificacions aplicades:");
		
		txtBonificacionsAplicades = new Text(grpBonificacions, SWT.BORDER);
		txtBonificacionsAplicades.setEnabled(false);
		txtBonificacionsAplicades.setEditable(false);
		txtBonificacionsAplicades.setBounds(161, 222, 76, 21);
		
		grpAlertes = new Group(shlAsseguradoraCotxes, SWT.NONE);
		grpAlertes.setText("Alertes");
		grpAlertes.setBounds(10, 387, 414, 63);
		
		txtError = new Text(grpAlertes, SWT.BORDER);
		txtError.setEditable(false);
		txtError.setEnabled(false);
		txtError.setBounds(10, 24, 380, 29);
		
		
	}
	
	
	/** validaParametres: mètode encarregat de validar els camps del grup dades vehicle**/
	private boolean validaParametres() {
		
		missatge = "";
		
		if (campBuit(txtCavalls.getText())) {
			enfocaText(txtCavalls);
			missatge = "Número de cavalls Obligatori";
			return false;
		}
		
		try {
			numCavalls= Integer.parseInt(txtCavalls.getText());
			
		}catch(NumberFormatException | NullPointerException ex){  
			enfocaText(txtCavalls);
			missatge = "Els cavall d'un cotxe ha de ser un enter";
			return false;
		}
		
		if(numCavalls < 60) {
			enfocaText(txtCavalls);
			missatge = "No assegurem cotxes de menys de 60 cavalls";
			return false;
		}
		indicadorCombus = comboCombustible.getSelectionIndex();
		int maxIndex = comboCombustible.getItemCount()-1;
		if(indicadorCombus<0) {
			comboCombustible.setFocus();
			missatge = "La categoria no pot estar buida";
			return false;
		}else if(maxIndex>3) {
			comboCombustible.setFocus();
			missatge = "Tipus de combustible incorrecte";
			return false;
		}
		
		return true;
	}
	
	private boolean validaCalculs() {
		
		if (campBuit(txtAntiguitatCarnet.getText())) {
			enfocaText(txtAntiguitatCarnet);
			missatge = "Has d'indicar quants anys fa que tens el carnet";
			return false;
		}
		
		try {
			anysCarnet = Integer.parseInt(txtAntiguitatCarnet.getText());
		}catch(NumberFormatException | NullPointerException ex){  
			enfocaText(txtAntiguitatCarnet);
			missatge = "Has de posar un número enter";
			return false;
		}
		
		if(anysCarnet < 0) {
			enfocaText(txtAntiguitatCarnet);
			missatge = "Anys Carnet ha de ser un nombre positiu";
			return false;
		}
		
		
		if (campBuit(txtUltimComunicat.getText())) {
			enfocaText(txtUltimComunicat);
			missatge = "Has d'indicar quant fa que vas tramitat l'últim comunicat";
			return false;
		}
		
		try {
		anysComunicat = Integer.parseInt(txtUltimComunicat.getText());
		}catch(NumberFormatException | NullPointerException ex) {
			enfocaText(txtUltimComunicat);
			missatge = "Has de posar un número enter als anys de comunicat";
			return false;
		}
		
		if(anysComunicat < 0) {
			enfocaText(txtUltimComunicat);
			missatge = "Ha de ser un nombre enter positiu!";
			return false;
		}
		
		missatge = "";
		return true;
	}
	
	private boolean campBuit(String s){
		return s.trim().isEmpty();
	}
	
	private void enfocaText(Text t){
		t.setFocus();
		t.selectAll();
	}
	
	private void habilitarParametres(boolean habilitacio) {
		
		grpDadesVehicle.setEnabled(habilitacio);
		txtCavalls.setEditable(habilitacio);
		comboCombustible.setEnabled(habilitacio);
		if(habilitacio) {
			txtCavalls.setText("");
			comboCombustible.select(-1);
			comboCombustible.setText("");
		}
	
	}
	private void habilitarCalculs(boolean habilitacio) {
		
		grpBonificacions.setEnabled(habilitacio);
		txtAntiguitatCarnet.setEditable(habilitacio);
		txtUltimComunicat.setEditable(habilitacio);
		
		if(!habilitacio) {
			txtAntiguitatCarnet.setText("");
			txtUltimComunicat.setText("");
			netejaSortida();
		}
	}
	
	private void calculs() {
		
		double preuBase;
		double preuCavalls;
		double combustible;
		double bonifAntiguitat;
		double bonifComunicat;
		double preuBonifAntiguitat;
		double preuBonifComunicats;
		double bonificacioTotal;
		double preuAnual;
		double preuTrimestral;
		double preuMensual;

		combustible=calculCombustible(indicadorCombus);
		if (numCavalls>135) {
			preuCavalls=600;
		}
		else if(numCavalls>=120) {
			preuCavalls=580;
		}
		else if(numCavalls>=110) {
			preuCavalls=560;
		}
		else if(numCavalls>=100) {
			preuCavalls=540;
		}
		else if(numCavalls>=90) {
			preuCavalls=510;
		}
		else preuCavalls=450;
		
		preuBase=combustible*preuCavalls;
		
		if (anysCarnet>=20) {
			bonifAntiguitat=0.8;
			}
		else if (anysCarnet>=10) {
			bonifAntiguitat=0.9;
		}
		else if (anysCarnet>=2) {
			bonifAntiguitat=1;
		}
		else bonifAntiguitat=1.2;
		
		if (anysComunicat>=5) {
			bonifComunicat=15.5;
			}
		else if (anysComunicat>=3) {
			bonifComunicat=5;
		}
		else if (anysComunicat>=1) {
			bonifComunicat=0;
		}
		else bonifComunicat=-3.5;
		
		preuBonifAntiguitat=preuBase -(preuBase*bonifAntiguitat);
		preuBonifComunicats=(preuBase - preuBonifAntiguitat)*(bonifComunicat/100);
		bonificacioTotal=preuBonifAntiguitat+preuBonifComunicats;
		
		preuAnual=(preuBase-bonificacioTotal)*0.98;
		preuTrimestral=(preuBase-bonificacioTotal)/4;
		preuMensual=((preuBase-bonificacioTotal)/12)*1.02;
		
		txtImportAnual.setText(String.format( "%.2f", preuAnual));
		txtImportTrimestral.setText(String.format( "%.2f", preuTrimestral));
		txtImportMensual.setText(String.format( "%.2f", preuMensual));
		txtBonificacionsAplicades.setText(String.format( "%.2f", bonificacioTotal));
	}
	
	private double calculCombustible(int combustibleVehicle){
		switch(combustibleVehicle) {
		case 0:
			return 1.00;
		case 1:
			return 1.20;
		case 2:
			return 0.90;
		case 3:
			return 0.80;
		default:
			return 1;
		}
		
	}
	
	private void netejaSortida() {
		txtImportAnual.setText("");
		txtImportTrimestral.setText("");
		txtImportMensual.setText("");
		txtBonificacionsAplicades.setText("");
		
	}
}


