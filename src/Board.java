
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class Board extends JPanel {

	//ATTRIBUTI
	private static Board b =new Board();

	//COSTRUTTORE
	private Board(){
		this.setLayout(null);
		//INSERISCI UNO SFONDO !! 
	}
	public static Board Instance(){return b;}
}