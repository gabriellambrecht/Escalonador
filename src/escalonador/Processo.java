package escalonador;

import java.util.concurrent.atomic.AtomicInteger;

public class Processo {
	public enum Status { NOVO, EXECUTANDO, ESPERA, LIMITE, PRONTO, ENCERRADO }

	private int id;
	private int tempoProcesso;	
	private int tempoDecorrido;
	private static final AtomicInteger count = new AtomicInteger(0);
	private boolean ioBound;
	private Status estado; 

	public Processo(int tempoProcesso, boolean ioBound) {
		id = count.incrementAndGet(); 
		this.tempoProcesso = tempoProcesso;
		this.ioBound = ioBound;
		estado = Status.NOVO;
	}

	public boolean getIoBound(){
		return ioBound;
	}
	public int getId() {
		return id;
	}
	public int gettempoProcesso() {
		return tempoProcesso;
	}

	public int gettempoDecorrido() {
		return tempoDecorrido;
	}
	public Status getStatus() {
		return estado;
	}
	
	public Status executar(int quantum){
		estado = Status.EXECUTANDO;
		//Lança tread para contar o quantum
		ThreadQuantum limite = new ThreadQuantum(quantum);
		Thread threadLimite = new Thread(limite);
		threadLimite.start();
		
		
		
		System.out.println("\nProcesso " + id + " entrou em EXECUÇÃO!");
		while(tempoDecorrido != tempoProcesso && threadLimite.isAlive()){
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tempoDecorrido++;
		}
		
		if(tempoDecorrido==tempoProcesso){
			estado = Status.ENCERRADO;
			System.out.println("Processo " + id + " ENCERRADO!");			
		}else{
			System.out.println("A execução de " + id + " parou. Fim do quantum!");
			estado = Status.LIMITE;
		}
		return estado;
	}
	
	private class ThreadQuantum implements Runnable{

		int quantum;
		
		private ThreadQuantum (int quantum){
			this.quantum = quantum;
		}
		
		@Override
		public void run() {
			try {
				Thread.sleep(quantum);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
