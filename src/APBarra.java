import java.io.*;
import javax.swing.*;

public interface APBarra{ //AbstractProduct Barra
	public String Apri() throws IOException,EOFException;
	public void Salva(JTextPane testo) throws IOException,EOFException;
	public void Chiudi();
	public JMenuItem getApri();
	public JMenuItem getSalva();
	public JMenuItem getChiudi();
	public JMenuItem getNuovaLista();
	public JMenuBar getMenu();
	public void setBounds(int x,int y,int l1,int l2);
	
}