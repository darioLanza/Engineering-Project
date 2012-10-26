

public class ListaDLinkata{
	private Nodo Header,Trailer;
	private int size;
	public ListaDLinkata(){
		Header = new Nodo(null);
		Trailer = new Nodo(null);
		Header.setNext(Trailer);
		Trailer.setPrev(Header);
		
		size = 0;
	}
	public int getSize(){return size;}
	public boolean isEmpty(){return (size==0);}
	public void Add(String text){ //AGGIUNGE IN CODA
		Nodo tmp = new Nodo(text);
		tmp.setPrev(Trailer.getPrev());
		tmp.setNext(Trailer);
		Trailer.getPrev().setNext(tmp);
		Trailer.setPrev(tmp);
		size++;
	}
	public boolean search(String text){
		Nodo tmp = Header.getNext();
		while(tmp!=Trailer){
			if(tmp.getElement().compareTo(text)==0)
				return true;
			else
				tmp = tmp.getNext();
		}
		return false;
	}
	public Nodo getHeader(){return Header;}
	public Nodo getTrailer(){return Trailer;}
}
