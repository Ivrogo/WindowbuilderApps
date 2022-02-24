package asseguradora;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Group;

public class asseguradoraCotxes {

	protected Shell shlAsseguradoraCotxes;
	private Group grpDadesVehicle;
	private Label lblCavalls;
	private Label lblCombustible;
	private Text textCavalls;
	private Combo comboCombus;
	private Button btnAcceptar;
	private Group groupBonificacions;
	private Label lblAntiguitatDelCarnet;
	private Text textAntiguitat;
	private Label lblAnys;
	private Label lblUltimComunicatAccident;
	private Text textAccident;
	private Label lblAnys_1;
	private Button btnCalcular;
	private Button btnRestablir;
	private Label lblImportvalorsEn;
	private Label lblAnual;
	private Text textAnual;
	private Label lblTrimestral;
	private Text textTrimestral;
	private Label lblMensual;
	private Text textMensual;
	private Label lblBonificacionsAplicades;
	private Text textBonificacions;
	private Group Alertes;
	private Text textAlertes;
	private String missatge;
	private int numCavalls;
	private int ind1;
	private int anysAntiguitat;
	private int anysAccident;
	private double preuBase;
	private double preuPostBoni;


	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			asseguradoraCotxes window = new asseguradoraCotxes();
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
		habilitarDadesVehicle(true);
		habilitarBonificacions(false);
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
		shlAsseguradoraCotxes.setBackground(SWTResourceManager.getColor(SWT.COLOR_TEXT_DISABLED_BACKGROUND));
		shlAsseguradoraCotxes.setSize(447, 562);
		shlAsseguradoraCotxes.setText("Asseguradora Cotxes");
		shlAsseguradoraCotxes.setLayout(null);

		grpDadesVehicle = new Group(shlAsseguradoraCotxes, SWT.NONE);
		grpDadesVehicle.setBounds(10, 10, 418, 138);
		grpDadesVehicle.setText("Dades Vehicle");

		lblCavalls = new Label(grpDadesVehicle, SWT.NONE);
		lblCavalls.setBounds(10, 42, 42, 15);
		lblCavalls.setText("Cavalls");

		lblCombustible = new Label(grpDadesVehicle, SWT.NONE);
		lblCombustible.setBounds(10, 90, 68, 15);
		lblCombustible.setText("Combustible");

		textCavalls = new Text(grpDadesVehicle, SWT.BORDER);
		textCavalls.setBounds(107, 39, 121, 21);

		comboCombus = new Combo(grpDadesVehicle, SWT.NONE);
		comboCombus.setItems(new String[] { "benzina", "diesel", "hibrid", "electric" });
		comboCombus.setBounds(107, 87, 121, 23);

		btnAcceptar = new Button(grpDadesVehicle, SWT.NONE);
		btnAcceptar.setGrayed(true);
		btnAcceptar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (validaDadesVehicle()) {
					habilitarDadesVehicle(false);
					habilitarBonificacions(true);
					textAlertes.setText("");
				} else {
					textAlertes.setText(missatge);
				}
			}
		});
		btnAcceptar.setBounds(297, 42, 111, 25);
		btnAcceptar.setText("Acceptar");

		groupBonificacions = new Group(shlAsseguradoraCotxes, SWT.NONE);
		groupBonificacions.setText("Bonificacions");
		groupBonificacions.setBounds(10, 154, 418, 290);

		lblAntiguitatDelCarnet = new Label(groupBonificacions, SWT.NONE);
		lblAntiguitatDelCarnet.setBounds(10, 49, 117, 15);
		lblAntiguitatDelCarnet.setText("Antiguitat del Carnet:");

		textAntiguitat = new Text(groupBonificacions, SWT.BORDER);
		textAntiguitat.setEditable(false);
		textAntiguitat.setBounds(140, 49, 87, 21);

		lblAnys = new Label(groupBonificacions, SWT.NONE);
		lblAnys.setBounds(230, 49, 24, 15);
		lblAnys.setText("anys");

		lblUltimComunicatAccident = new Label(groupBonificacions, SWT.NONE);
		lblUltimComunicatAccident.setBounds(10, 98, 147, 15);
		lblUltimComunicatAccident.setText("\u00DAltim comunicat accident:");

		textAccident = new Text(groupBonificacions, SWT.BORDER);
		textAccident.setEditable(false);
		textAccident.setBounds(163, 98, 63, 21);

		lblAnys_1 = new Label(groupBonificacions, SWT.NONE);
		lblAnys_1.setBounds(230, 98, 24, 15);
		lblAnys_1.setText("anys");

		btnCalcular = new Button(groupBonificacions, SWT.NONE);
		btnCalcular.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(validaBonificacions()) {
					Calculs();
					textAlertes.setText("");
				} else {
					netejadorText();
					textAlertes.setText(missatge);
				}
			}
		});
		btnCalcular.setBounds(312, 44, 75, 25);
		btnCalcular.setText("Calcular");

		btnRestablir = new Button(groupBonificacions, SWT.NONE);
		btnRestablir.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				habilitarDadesVehicle(true);
				habilitarBonificacions(false);
			}
		});
		btnRestablir.setBounds(312, 93, 75, 25);
		btnRestablir.setText("Restablir");

		lblImportvalorsEn = new Label(groupBonificacions, SWT.NONE);
		lblImportvalorsEn.setBounds(10, 157, 158, 15);
		lblImportvalorsEn.setText("IMPORT (tots els valors en \u20AC)");

		lblAnual = new Label(groupBonificacions, SWT.NONE);
		lblAnual.setBounds(10, 178, 39, 15);
		lblAnual.setText("Anual");

		textAnual = new Text(groupBonificacions, SWT.BORDER);
		textAnual.setEditable(false);
		textAnual.setBounds(10, 199, 76, 21);

		lblTrimestral = new Label(groupBonificacions, SWT.NONE);
		lblTrimestral.setBounds(163, 178, 55, 15);
		lblTrimestral.setText("Trimestral");

		textTrimestral = new Text(groupBonificacions, SWT.BORDER);
		textTrimestral.setEditable(false);
		textTrimestral.setBounds(163, 199, 76, 21);

		lblMensual = new Label(groupBonificacions, SWT.NONE);
		lblMensual.setBounds(312, 178, 55, 15);
		lblMensual.setText("Mensual");

		textMensual = new Text(groupBonificacions, SWT.BORDER);
		textMensual.setEditable(false);
		textMensual.setBounds(312, 199, 76, 21);

		lblBonificacionsAplicades = new Label(groupBonificacions, SWT.NONE);
		lblBonificacionsAplicades.setBounds(10, 253, 132, 15);
		lblBonificacionsAplicades.setText("Bonificacions aplicades:");

		textBonificacions = new Text(groupBonificacions, SWT.BORDER);
		textBonificacions.setEditable(false);
		textBonificacions.setBounds(163, 253, 75, 21);

		Alertes = new Group(shlAsseguradoraCotxes, SWT.NONE);
		Alertes.setText("Alertes");
		Alertes.setBounds(10, 450, 418, 63);

		textAlertes = new Text(Alertes, SWT.BORDER);
		textAlertes.setEditable(false);
		textAlertes.setBounds(10, 20, 398, 33);

	}

	/**
	 * Metodes encarregats de les distintes funcions dels diferent camps de
	 * l'aplicació
	 **/

	private boolean validaDadesVehicle() {

		missatge = "";

		if (campBuit(textCavalls.getText())) {
			enfocaText(textCavalls);
			missatge = "Quantitat de cavalls Obligatori";
			return false;
		}

		try {
			numCavalls = Integer.parseInt(textCavalls.getText());

		} catch (NumberFormatException | NullPointerException ex) {
			enfocaText(textCavalls);
			missatge = "El nombre de cavalls ha de ser un enter";
			return false;
		}

		if (numCavalls <= 60) {
			enfocaText(textCavalls);
			missatge = "El nombre de cavalls ha de ser major que 60";
			return false;
		}
		
		ind1 = comboCombus.getSelectionIndex();
		int maxIndex = comboCombus.getItemCount() - 1;
		if (ind1 < 0) {
			comboCombus.setFocus();
			missatge = "El combustible no pot estar buit";
			return false;
			
		} else if (maxIndex > 4) {
			comboCombus.setFocus();
			missatge = "Valor fora dels limits";
			return false;
		}
		return true;
	}

	private boolean validaBonificacions() {

		if (campBuit(textAntiguitat.getText())) {
			enfocaText(textAntiguitat);
			missatge = "nombre d'anys d'Antiguitat del Carnet Obligatori";
			return false;
		}

		try {
			anysAntiguitat = Integer.parseInt(textAntiguitat.getText());
		} catch (NumberFormatException | NullPointerException ex) {
			enfocaText(textAntiguitat);
			missatge = "El nombre d'anys d'Antiguitat del carnet ha de ser un enter";
			return false;
		}

		if (campBuit(textAccident.getText())) {
			enfocaText(textAccident);
			missatge = "Nombre d'anys de comunicacions d'accidents Obligatori";
			return false;
		}

		try {
			anysAccident = Integer.parseInt(textAccident.getText());
		} catch (NumberFormatException | NullPointerException ex) {
			enfocaText(textAccident);
			missatge = "El nombre d'anys corresponent a la comunicació d'accident ha de ser un enter";
			return false;
		}

		if (anysAccident < 0) {
			enfocaText(textAccident);
			missatge = "El nombre d'anys de comunicacions d'accidents ha de ser igual o superior a 0";
			return false;
		}

		missatge = "";
		return true;

	}

	private void enfocaText(Text text) {
		text.setFocus();
		text.selectAll();
	}

	private boolean campBuit(String text) {
		return text.trim().isEmpty();
	}

	private void habilitarDadesVehicle(boolean habilitacio) {

		grpDadesVehicle.setEnabled(habilitacio);
		textCavalls.setEditable(habilitacio);
		comboCombus.setEnabled(habilitacio);
		if (habilitacio) {
			textCavalls.setText("");
			comboCombus.select(-1);
			comboCombus.setText("");
		}
	}

	private void habilitarBonificacions(boolean habilitacio) {

		groupBonificacions.setEnabled(habilitacio);
		textAntiguitat.setEditable(habilitacio);
		textAccident.setEditable(habilitacio);

		if (!habilitacio) {
			textAntiguitat.setText("");
			textAccident.setText("");
			netejadorText();
		}

	}

	private void Calculs() {
		double preuPerCav;
		double preuPerCombus;
		double preuPerAnt;
		double preuPerAcc;
		double impBonif;
		double impAnual;
		double impTrim;
		double impMen;
		
		preuPerCav = calculPreuCavalls(numCavalls);
		preuPerCombus = calculPreuCombustible(ind1);
		preuBase = preuPerCav * preuPerCombus;
		preuPerAnt = preuBase * calculPreuAnti(anysAntiguitat);
		preuPerAcc = preuPerAnt - (calculPreuAcc(anysAccident)* preuPerAnt);
		impBonif = preuBase - preuPerAcc;
		preuPostBoni = preuBase - impBonif;
		
		impAnual = preuPostBoni - (0.02 * preuPostBoni);
		impTrim = preuPostBoni / 4;
		impMen = (preuPostBoni / 12) + (0.02 * preuPostBoni);
		
		
		
		textAnual.setText(String.format("%.2f", impAnual));
		textTrimestral.setText(String.format("%.2f", impTrim));
		textMensual.setText(String.format("%.2f", impMen));
		textBonificacions.setText(String.format("%.2f", impBonif));
		
	}

	private double calculPreuCavalls(int valorCavalls) {
		
		if (valorCavalls < 90) {
			return 450.00;
		} else if (valorCavalls >= 90 && valorCavalls <= 99) {
			return 510.00;
		} else if (valorCavalls >= 100 && valorCavalls <= 109) {
			return 540.00;
		} else if (valorCavalls >= 110 && valorCavalls <= 119) {
			return 560.00;
		} else if (valorCavalls >= 120 && valorCavalls <= 135) {
			return 580.00;
		} else if (valorCavalls > 135) {
			return 600.00;
		}
		return valorCavalls;
	}
	
	private double calculPreuCombustible(int catCombustible) {	
		switch (catCombustible) {
		case 0: 
			return 1;
		case 1:
			return 1.2;
		case 2:
			return 0.9;
		case 3:
			return 0.8;
		default:
			return 0;
		}	   
	   }
	
	private double calculPreuAnti(int valorAntiguitat) { 
			
		if (valorAntiguitat <= 1) {
			return 1.2;
		} else if (valorAntiguitat >= 2 && valorAntiguitat <= 9) {
			return 1;
		} else if (valorAntiguitat >= 10 && valorAntiguitat <= 19) {
			return 0.9;
		} else if (valorAntiguitat > 20) {
			return 0.8;
		}
		return valorAntiguitat;
	}
	
	private double calculPreuAcc(int valorAccident) {
		
		if (valorAccident < 1) {
			return 0.035;
		} else if (valorAccident >= 1 && valorAccident <= 2) {
			return 0;
		} else if (valorAccident >= 3 && valorAccident <= 4) {
			return 0.05;
		} else if (valorAccident >= 5) {
			return 0.155;
		}
		return valorAccident;
	}
	
	private void netejadorText() {
		
		textAntiguitat.setText("");
		textAccident.setText("");
		textAnual.setText("");
		textMensual.setText("");
		textTrimestral.setText("");
		textBonificacions.setText("");
	}
}
