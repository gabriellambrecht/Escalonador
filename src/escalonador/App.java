package escalonador;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		int quantum;
		int processosPorMinuto;
		int tempoVidaMax;
		int probabilidade;
		//declara rotina de leitura
		Scanner ler = new Scanner(System.in);
		//aceita quantum
		System.out.println("Digite o valor do QUANTUM para a simulação:");
		quantum = ler.nextInt();
		System.out.println("Digite quantos processos devem ser criados por minuto:");
		processosPorMinuto = ler.nextInt();
		System.out.println("Digite o tempo máximo de vida de um processo:");
		tempoVidaMax = ler.nextInt();
		System.out.println("Digite a probabilidade de que os novos processos sejam I/O-bound:");
		probabilidade = ler.nextInt();
		//Intancia escalonador
		Escalonador escal = Escalonador.getInstance(quantum);
		//Gera tread que monitora fila de processos
		MonitoraEscalonador monitor = new MonitoraEscalonador();
		Thread threadDoEscalonador = new Thread(monitor);
		threadDoEscalonador.start();
		//gera tread que insere processos
		GeraProcessos gerador = new GeraProcessos(processosPorMinuto, tempoVidaMax, probabilidade);
		Thread threadDoGerador = new Thread(gerador);
		threadDoGerador.start();
	}

}
