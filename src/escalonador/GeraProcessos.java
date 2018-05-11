package escalonador;

import java.util.Random;

public class GeraProcessos implements Runnable {

	private int processosPorMinuto;
	private int tempoVidaMax;
	private int probabilidade;
	private Escalonador escal;
	
	public GeraProcessos(int processosPorMinuto, int tempoVidaMax, int probabilidade) {
		this.processosPorMinuto = processosPorMinuto;
		this.tempoVidaMax = tempoVidaMax;
		this.probabilidade = probabilidade;
		escal = Escalonador.getInstance();
	}
	
	@Override
	public void run() {
		int sleepTime = 60000/processosPorMinuto;
		Random gerador = new Random();
		int tempoProcesso;
		int aleatorio; //1 a 100
		boolean ioBound;
        //laço para criação de processos		
		while(true){
			//gera tempo de processo
			tempoProcesso = gerador.nextInt(tempoVidaMax)+1;
			//gera número aleatório de 1 a 100
			aleatorio = gerador.nextInt(100)+1;
			//se número aleatório é menor ou igual a probabilidade
			if(aleatorio <= probabilidade){
				//considera processo IObound
				ioBound = true;
			} else {	
				ioBound = false;
			}
			//Cria novo processo
			Processo processo = new Processo(tempoProcesso, ioBound);
			//Adiciona novo processo
			escal.addProcesso(processo);
			//Dorme
		
		}
	}
}
