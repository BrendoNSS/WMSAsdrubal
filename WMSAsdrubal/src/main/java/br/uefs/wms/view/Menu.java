package br.uefs.wms.view;

import java.io.IOException;

import br.uefs.wms.controller.Controller;
import br.uefs.wms.util.Console;

/**
 * Desconsidere essa classe
 * @author Brendo-PC
 *
 */
public class Menu {
	
	private static Controller controle = new Controller();
	
	/*public static void inserirItem() throws NumberFormatException, IOException{
		System.out.println("Digite o lote da mercadoria:");
		int lote = Console.readInt();
		
		System.out.println("Digite o endereco da mercadoria:");
		int endereco = Console.readInt();
		
		System.out.println("Digite o bloco da mercadoria:");
		String bloco = Console.readString();
		
		System.out.println("Digite o numero da mercadoria:");
		int num = Console.readInt();
		
		System.out.println("Digite o nome do fornecedor da mercadoria:");
		String nomeF = Console.readString();
		
		System.out.println("Digite a data de entrega:");
		String data = Console.readString();
		
		System.out.println("Digite a hora de entrega da mercadoria:");
		String hora = Console.readString();
		
		controle.inserirMercadoriaNaArvore(lote, endereco, bloco, num, nomeF, data, hora);
		
		System.out.println("Cadastrado com sucesso!");
	}*/
	
	public static void carregarArquivoNaArvore() throws IOException{
		
		//controle.lerArquivo(arquivo);
		
		System.out.println("Arquivo carregado com sucesso!");
	}
	
	/*public static void buscarMercadoria() throws IOException{
		
		System.out.println("Digite o numero da mercadoria que deseja buscar");
		int num = Console.readInt();
		
		controle.retornarMercadoria(num);
			
	}*/
	
	public static void exibirDados(){
		
	    controle.printar();
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		System.out.println("Bem vindo ao sistema de cadastro de mercadorias!\n");
		
		int resp = -1;
		
		do{
			System.out.println("Digite o numero da operação que deseja realizar: ");
        	System.out.println("1 - Cadastros de mercadorias \n2 - Carregar dados do arquivo \n3 - Buscar uma mercadoria");
        	System.out.println("4 - Exibir dados");
        	resp = Console.readInt();
        	
        	switch(resp){
   	             case 1: {
   	            	 //inserirItem();
   	            	 break;
   	             }
   	             case 2: {
   	            	 carregarArquivoNaArvore();
   	            	 break;
   	             }
   	             case 3: {
   	            	 //buscarMercadoria();
   	            	 break;
   	             }
   	             case 4: {
   	            	exibirDados();
  	            	break;
   	             }
        	}   
        	
        	System.out.println("Deseja realizar outra operação? Digite qualquer número para continua ou zero se quiser sair");
            resp = Console.readInt();
		}while(resp != 0);

	}

}