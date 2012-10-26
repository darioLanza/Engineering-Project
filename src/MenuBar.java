import javax.swing.*;
import java.io.*;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.text.*;

import javax.swing.text.rtf.RTFEditorKit;



public class MenuBar  implements APBarra { //Product of AbstractProduct barra
	private JMenuBar Menu;
	private JMenuItem Apri;
	private JMenuItem Salva;
	private JMenuItem Chiudi;
	private JMenuItem NuovaLista;
	
	public MenuBar(){
		
		Menu = new JMenuBar(); // BARRA PRINCIPALE
		Menu.setBounds(0,0,1200,25);
		
		JMenu File = new JMenu("File"); // MENU DENOMINATO "FILE"
		JMenu Inserisci = new JMenu("Inserisci");// MENU DENOMINATO "INSERISCI"
		
		Apri = new JMenuItem("Apri"); // OPZIONE "Apri" DEL MENU
		Salva = new JMenuItem("Salva"); // OPZIONE "Salva" DEL MENU
		Chiudi = new JMenuItem("Chiudi"); // OPZIONE "Chiudi" DEL MENU
		
		NuovaLista = new JMenuItem("Nuova Lista");
		
		
		//COLLEGAMENTO DEI MENU E DEGL'ITEM . 
		File.add(Apri);
		File.add(Salva);
		File.add(Chiudi);
		Inserisci.add(NuovaLista);
		
		Menu.add(File);
		Menu.add(Inserisci);
	
	}
	public String Apri() throws IOException,EOFException{
		JFileChooser dlgFile = new JFileChooser();
		dlgFile.setFileFilter(new docFormat());
		dlgFile.addChoosableFileFilter(new txtFormat());
		dlgFile.addChoosableFileFilter(new javaFormat());
		dlgFile.addChoosableFileFilter(new pdfFormat());
		
		
		int dato;
		FileInputStream inpF = null;
		
		if (dlgFile.showOpenDialog (null) ==JFileChooser.APPROVE_OPTION) {
			File file = dlgFile.getSelectedFile();
			inpF = new FileInputStream (file);
		}
		//LETTURA DI OGNI CARATTERE DEL FILE SCELTO
		String OUT="";
		if(inpF != null){	
			do {
				dato = inpF.read();
				if (dato != -1){
					//System.out.print((char)dato);
					OUT+=(char)dato;
				}
			} while (dato != -1);
			inpF.close();
		}
		return OUT;
	}
	public void Salva(JTextPane testo ) throws IOException,EOFException{
		JFileChooser dlgFile = new JFileChooser();
		dlgFile.setFileFilter(new docFormat());
		dlgFile.addChoosableFileFilter(new txtFormat());
		
		
		
		StyledDocument doc = testo.getStyledDocument();
		RTFEditorKit kit = new RTFEditorKit();
		
		try {
			if (dlgFile.showSaveDialog (null) ==JFileChooser.APPROVE_OPTION) {
				File file = dlgFile.getSelectedFile();
				String path = file.getAbsolutePath();
				if(dlgFile.getFileFilter().getDescription().equals(".doc")||dlgFile.getFileFilter().getDescription().equals(".txt"))
					path+=dlgFile.getFileFilter().getDescription();
				FileOutputStream outStream = new FileOutputStream(path);
				kit.write(outStream, doc, 0, doc.getLength());
				outStream.close();
			}
		
		} catch (Exception e) {e.printStackTrace();}
		
		
		
	}
	public void Chiudi(){
		System.exit(0);
	}
	public void setBounds(int x,int y,int l1, int l2){
		Menu.setBounds(x,y,l1,l2);
	}
	public JMenuBar getMenu(){return Menu;}
	public JMenuItem getApri(){return Apri;}
	public JMenuItem getSalva(){return Salva;}
	public JMenuItem getChiudi(){return Chiudi;}
	public JMenuItem getNuovaLista(){return NuovaLista;}

}