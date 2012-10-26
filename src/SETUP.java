import java.io.FileNotFoundException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.*;

public class SETUP extends JFrame{
	//	COSTRUTTORE
	public SETUP() throws FileNotFoundException{
		
		FaçadeManager manager = new FaçadeManager();
		
		JScrollPane scroll=new JScrollPane(manager.getMain());
		add(scroll);
		
		//IMPOSTO IL JFRAME
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1080,750);
		setTitle("INGEGNERIA DEL SOFTWARE");
		setResizable(true);
		setVisible(true);
	}
	public static void main(String args[]) throws FileNotFoundException{
		new SETUP();
	}
}