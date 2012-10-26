
public class Nodo{
	private Nodo Next,Prev;
	private String Element;
	
	public Nodo(String Element){
		this.Element = Element;
		Next=Prev=null;
	}
	public void setNext(Nodo n){Next = n;}
	public void setPrev(Nodo n){Prev = n;}
	public Nodo getNext(){return Next;}
	public Nodo getPrev(){return Prev;}
	public String getElement(){return Element;}
}