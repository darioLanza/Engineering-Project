import javax.swing.*;

public interface APTesto{ //AbstractProduct Testo
	public void setInput(String text);
	public void show(String[] testo, String[] param);
	public void setBounds(int x, int y, int z, int t);
	public JPanel getPannello();
	public JTextArea getOriginale();
	public JTextPane getFormattato();
	
}