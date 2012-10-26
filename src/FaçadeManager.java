
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class FaçadeManager implements MouseListener,ActionListener{// FACADE CLASS
	//ATTRIBUTI
	private JPanel main;
	private AFSet SET ;
	private APBarra Barra ;
	private APTesto Testi;
	private APControlli Controlli;
	private Board board =Board.Instance();//CREO LA BOARD TRAMITE PATTERN : SINGLETON

	private ListaDLinkata ListaJava = new ListaDLinkata();
	private ListaDLinkata ListaCustom = new ListaDLinkata();
	
	private Scanner scan;
	public FaçadeManager() throws FileNotFoundException{
		//	1 - SETTAGGIO DELLA BOARD
		SET= new CFSet1();
		Barra = SET.createBarra();
		Testi = SET.createTesto();
		Controlli = SET.createControlli();
		
		Barra.getApri().addActionListener(this);
		Barra.getSalva().addActionListener(this);
		Barra.getChiudi().addActionListener(this);
		Barra.getNuovaLista().addActionListener(this);
		Controlli.getAvvia().addMouseListener(this);
		
		board.add(Barra.getMenu());
		board.add(Testi.getPannello());
		board.add(Controlli.getPannello());
		//	1 - FINE
		
		//	2 - SETTAGGIO PANNELLI 
		//Carico un pannello il principale "MAIN",infine, dopo aver creato la "BOARD" la innesto nel "MAIN"
		main=new JPanel();
		main.setLayout(new GridBagLayout());
		
		int x=Toolkit.getDefaultToolkit().getScreenSize().width;// RICAVO LE DIMENSIONI DELLO SCHERMO IN USO
		int y=Toolkit.getDefaultToolkit().getScreenSize().height;
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints(); //IMPOSTO LE DIMENSIONI DELLA MIA GRIDBAGCONSTRAINTS
		gridBagConstraints.ipadx = x;
		gridBagConstraints.ipady = y;
		
		main.add(board, gridBagConstraints);// Carico la "BOARD"
		main.setPreferredSize(new Dimension(1000,700));
		//	2 - FINE
		
		//	3 - SETTAGGIO LISTA JAVA PRECARICATA
		/*scan = new Scanner(new FileReader("C:\\Users\\Dario\\Documents\\NetBeansProjects\\IngProject\\src\\ListaJava.txt"));
		while(scan.hasNext()){
			ListaJava.Add(scan.next());
		}*/
                creaListaJava();
		//	3 - FINE
	}
	public JPanel getMain(){return main;}
	public void actionPerformed(ActionEvent e) {
		try{
			JMenuItem choise=(JMenuItem)e.getSource();
			if(choise == Barra.getApri() ){
				String testo = Barra.Apri();
				Testi.setInput(testo);
			
			}
			if(choise == Barra.getSalva() ){
				Barra.Salva(Testi.getFormattato());
			}
			if(choise == Barra.getChiudi() ){
				Barra.Chiudi();
			}
			if(choise == Barra.getNuovaLista() ){
				NuovaLista();
			}
		}
		catch(IOException ex1){System.out.println("IOException");}
	}
	//EVENTI MOUSE CLICK
	public void mouseClicked(MouseEvent e){
		
		JButton clic=(JButton)e.getSource();
		if(clic==Controlli.getAvvia() && Controlli.getAvvia().isEnabled()){
			
			makeFormatText();
		}
	}
	private void NuovaLista() throws IOException{
		
			JFileChooser dlgFile = new JFileChooser();
			dlgFile.setFileFilter(new txtFormat());
			FileInputStream inpF = null;

			if (dlgFile.showOpenDialog (null) ==JFileChooser.APPROVE_OPTION) {
				File file = dlgFile.getSelectedFile();
				
				inpF = new FileInputStream (file);
			}
			if(inpF != null){
				scan = new Scanner(inpF);
				while(scan.hasNext()){
					ListaCustom.Add(scan.next());
				}
				inpF.close();
			}
	}
	private void makeFormatText(){
		
		boolean check = true;
		String[] param = Controlli.getDati();
		//CONTROLLO QUALE LISTA DI PAROLE CHIAVE USARE
		ListaDLinkata Lista = ListaJava;
		if(param[0].compareTo("CustomLista")==0){
			if(ListaCustom.getSize() == 0){
				
				JOptionPane.showMessageDialog(null,
				"Nessuna Lista keyword � stata caricata.\nPer usare la lista standard scegliere:\nLista Keyword : Java ",
				"INPUT ERROR",JOptionPane.ERROR_MESSAGE);
				
				
				check=false;
			}
			else
				Lista = ListaCustom;
		}
		
		if(check){
		
			String testo = Testi.getOriginale().getText();
			String word ="";
			Nodo tmp = Lista.getHeader().getNext();
			while(tmp != Lista.getTrailer()){
				word = tmp.getElement();
				
				testo=testo+" ";
				testo = testo.replaceAll(word, " "+word+" ");
				testo = testo.replaceAll(" "+word+"  ", "/&1"+word+"/& ");
				testo = testo.replaceAll("\t "+word+"  ", "\t/&1"+word+"/& ");
				testo = testo.replaceAll("\n "+word+"  ", "\n/&1"+word+"/& ");
				testo = testo.replaceAll("  "+word+"  ", " /&1"+word+"/& ");
				testo = testo.replaceAll(" "+word+" ", word);
				
				tmp = tmp.getNext();
			}
			
			int length = 0;
			Scanner scanner = new Scanner(testo);
			scanner = scanner.useDelimiter("/&");
			while(scanner.hasNext()){ 
				scanner.next();
				length++;
			}
			
			scanner.close();
			scanner = new Scanner(testo);
			scanner = scanner.useDelimiter("/&");
			String[] FormatText = new String[length];
			length = 0;
			while(scanner.hasNext()){
				FormatText[length++] = scanner.next();
			}
			scanner.close();
			Testi.show(FormatText, param);
		}
	}
        private void creaListaJava(){
            String[] parole;
            String testoparole = "abstract double int static boolean else interface super break extends long switch byte final native synchronized case finally new this catch float null throw char for package throws class goto private transient const if protected try continue implements public void default import return volatile do instanceof short while";
            parole = testoparole.split(" ");
            for(int i=0; i<parole.length;i++){
                ListaJava.Add(parole[i]);
            }
        }
	//METODI FANTASMA
	public void mouseEntered(MouseEvent e) {}	// Si devono dichiarare anche i metodi che non si utilizzano
	public void mouseReleased(MouseEvent e) {} // per via dell'implementazione dell'interfaccia
	public void mousePressed(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	//FINE
}