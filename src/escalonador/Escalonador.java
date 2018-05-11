package escalonador;

import java.util.ArrayList;
import escalonador.Processo.Status;

public class Escalonador{
	private static ArrayList<Processo> listaProcessos = new ArrayList<Processo>();
	private int quantum;
	private static Escalonador instance = null;
	
	protected Escalonador(int quantum) {
		this.quantum = quantum;
		listaProcessos.clear();
	}
	
	public static Escalonador getInstance(int quantum) {
	      if(instance == null) {
	         instance = new Escalonador(quantum);
	      }
	      return instance;
	}

	public static Escalonador getInstance() {
	      return instance;
	}
	
	public synchronized void addProcesso (Processo novoProcesso){
		listaProcessos.add(novoProcesso);
		System.out.println("\nNovo processo! Tamanho da lista: " + listaProcessos.size());
	}

	public synchronized void monitora() {
		if (!listaProcessos.isEmpty()) {
			Processo processo = listaProcessos.get(0);
			Status st = processo.executar(quantum);
			listaProcessos.remove(0);
			if (st == Status.PRONTO) {
				System.out.println("O processo " + processo.getId() + " Encerrado.");
			}
			else if(st == Status.LIMITE){
				addProcesso(processo);
				System.out.println("O processo " + processo.getId() + " Foi reinserido na lista.");
				
//				System.out.println("O processo " + processo.getId() + " teve retorno da entrada.");
//				System.out.println("O processo " + processo.getId() + " foi para o fim da lista.");
//				listaProcessos.add(processo);
			}
		}
	}
}
