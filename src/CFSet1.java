public class CFSet1 implements AFSet{ //ConcreteFactory Set1
	public APBarra createBarra(){
		return new MenuBar();
	}
	public APTesto createTesto(){
		return new Testi("In attesa di un testo ");
	}
	public APControlli createControlli(){
		return new ControlliFormattazione();
	}
}
