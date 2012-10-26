import javax.swing.*;
import java.awt.*;

public class ControlliFormattazione implements APControlli {
	private JPanel pannello = new JPanel();
	private CheckboxGroup checkLista;
	private Checkbox grassetto;
	private Checkbox corsivo;
	private JComboBox carattere;
	private JComboBox colore;
	private JComboBox dimensione;
	private JButton Avvia;
	public ControlliFormattazione(){
		pannello.setLayout(null);
		
		
		JLabel ListeL= new JLabel("Lista Keyword : ");
		checkLista= new CheckboxGroup();
		Checkbox JavaLista = new Checkbox("Java", checkLista, true);
		Checkbox CustomLista = new Checkbox("Custom", checkLista, false);
		
		grassetto = new Checkbox("Grassetto",null,false);
		corsivo = new Checkbox("Corsivo",null,false);
		
		JLabel DimensioneL = new JLabel("Dimensione ");
		
		String[] dim = {"10","11","12","13","14","15","16","17","18","19","20"};
		dimensione = new JComboBox(dim);
		dimensione.setSelectedIndex(1);
		
		JLabel CarattereL = new JLabel("Carattere :");
		String[] caratteri = {"Times Roman","Arial","Comics","Sans Serif"};
		carattere = new JComboBox(caratteri);
		carattere.setSelectedIndex(0);
		
		JLabel ColoreL = new JLabel("Colore :");
		String[] colori = {"NERO","ROSSO","VERDE","BLU","GIALLO"};
		colore = new JComboBox(colori);
		colore.setSelectedIndex(0);
		
		Avvia = new JButton("Avvia");
		
		JLabel info = new JLabel("nomeSistemaSoftware Dario Lanza");
		info.setFont(new Font("Arial",3,16));
		//POSIZIONI
		ListeL.setBounds(0,0,140,25);
		JavaLista.setBounds(150,0,50,25);
		CustomLista.setBounds(210,0,60,25);
		
		CarattereL.setBounds(0,30,120,25);
		carattere.setBounds(130,30,120,25);
		grassetto.setBounds(300,30,80,25);
		corsivo.setBounds(390,30,80,25);
		ColoreL.setBounds(0,60,100,25);
		colore.setBounds(130,60,120,25);
		DimensioneL.setBounds(300,60,120,25);
		dimensione.setBounds(380,60,50,25);
		
		
		Avvia.setBounds(700,30,100,25);
		info.setBounds(750,100,300,25);
		
		pannello.setBounds(10,572,1200,150);
		
		pannello.add(ListeL);
		pannello.add(JavaLista);
		pannello.add(CustomLista);
		
		pannello.add(CarattereL);
		pannello.add(carattere);
		pannello.add(grassetto);
		pannello.add(corsivo);
		pannello.add(DimensioneL);
		pannello.add(dimensione);
		pannello.add(ColoreL);
		pannello.add(colore);
		pannello.add(Avvia);
		pannello.add(info);
		
	}
	public JPanel getPannello(){return pannello;}
	public JButton getAvvia(){return Avvia;}
	public String[] getDati(){
		String[] param = new String[5];
		if(checkLista.getSelectedCheckbox().getLabel().compareTo("Java")==0)
			param[0] = "JavaLista";
		else
			param[0] ="CustomLista";
		param[1] = carattere.getSelectedItem().toString();
		int value=0;
		if(grassetto.getState()==true)
			value++;
		if(corsivo.getState()==true)
			value++;
		if(value == 2) value++;
		param[2] = ""+value;
		
		param[3] = dimensione.getSelectedItem().toString();
		param[4] = colore.getSelectedItem().toString();
		
		return param;
	}
}