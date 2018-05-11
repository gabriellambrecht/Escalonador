package escalonador;

public class MonitoraEscalonador  implements Runnable{
	
	Escalonador escal;
	public MonitoraEscalonador (){
		escal = Escalonador.getInstance();
	}
	
	@Override
	public void run() {
		while(true){
			escal.monitora();
		}
	}
}
