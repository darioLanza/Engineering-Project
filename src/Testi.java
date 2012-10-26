import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class Testi implements APTesto{
	private JPanel pannello = new JPanel();
	private JTextArea Originale;
	private JTextPane Formattato;
	private JScrollPane scroll1,scroll2;
	public Testi(String t1){
		
		pannello.setLayout(null);
		Originale = new JTextArea(t1);
		
		//SETTAGGIO DELLA MIA JTEXTPANE
		Formattato = new JTextPane();
		// FINE
		
		
		JLabel labelOR = new JLabel("Originale");
		JLabel labelFO = new JLabel("Formattato");
		labelOR.setFont( new Font("",1,20));
		labelFO.setFont( new Font("",1,20));
		labelOR.setBounds(0,0,100,25);
		labelFO.setBounds(510,0,150,25);
		scroll1 = new JScrollPane(Originale);
		scroll1.setBounds(0,40,500,500);
		scroll2 = new JScrollPane(Formattato);
		scroll2.setBounds(510,40,500,500);
		
		
		pannello.add(labelOR);
		pannello.add(labelFO);
		pannello.add(scroll1);
		pannello.add(scroll2);
		pannello.setBounds(10,30,1200,540);
		
	}
	public void setInput(String t1){
		Originale.setText(t1);
	}
	public void show(String [] testo, String[] param){
		
		Formattato.setText("");
		
		//CONTROLLO QUALE STILE DEVO DARE ALLE PAROLE CHIAVI
		String typeStyle = "JavaStyle";
		if(param != null && param[0].compareTo("CustomLista") == 0)
			typeStyle = "CustomStyle";
		
		//ASSEGNO IL TIPO DI STILE SCELTO ALLE PAROLE CHIAVI 
		String[] fonts = new String [testo.length];
		for(int i = 0 ; i<testo.length; i++){
			if(testo[i].startsWith("1")){
				fonts[i] = typeStyle;
				testo[i] = testo[i].replace("1","");
			}
			else
				fonts[i]= "regular";
		}
		
		//IL METORO addStylesToDocument(doc, param) CREA LO STILE CLASSICO JAVA E QUELLO SCELTO DALL'UTENTE
		StyledDocument doc = Formattato.getStyledDocument();
		addStylesToDocument(doc,param);
		
		try {
			for (int i=0; i < testo.length; i++) {
				doc.insertString(doc.getLength(), testo[i],doc.getStyle(fonts[i]));
			}
		}
		catch (BadLocationException ble){
			System.out.println("Couldn't insert initial text into text pane.");
		}
	}
	
	public void setBounds(int x, int y, int z, int t){
		pannello.setBounds(x,y,z,t);
	}
	public JPanel getPannello(){return pannello;}
	public JTextArea getOriginale(){return Originale;}
	public JTextPane getFormattato(){return Formattato; }
	protected void addStylesToDocument(StyledDocument doc, String[] param) {
		
		//STILE CLASSICO JAVA
		Style style = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
		style = doc.addStyle("JavaStyle",style);
		StyleConstants.setForeground(style,new Color(35,35,170));
		StyleConstants.setBold(style, true);
		
		//STILE SCELTO DALL'UTENTE
		if(param != null){
			style = doc.addStyle("CustomStyle",style);
			StyleConstants.setFontFamily(style,param[1]);
			if(param[2].compareTo("1") == 0)
				StyleConstants.setBold(style, true);
			if(param[2].compareTo("2") == 0)
				StyleConstants.setItalic(style, true);
			if(param[2].compareTo("3") == 0){
				StyleConstants.setBold(style, true);
				StyleConstants.setItalic(style, true);
			}
			
			Integer temp = new Integer(param[3]);
			
			StyleConstants.setFontSize(style, temp.intValue());
			
			if(param[4].compareTo("ROSSO") == 0)
				StyleConstants.setForeground(style,Color.RED);
			if(param[4].compareTo("VERDE") == 0)
				StyleConstants.setForeground(style,Color.GREEN);
			if(param[4].compareTo("BLU") == 0 )
				StyleConstants.setForeground(style,Color.BLUE);
			if(param[4].compareTo("GIALLO") == 0)
				StyleConstants.setForeground(style,Color.YELLOW);
			
		}
	}
	
}