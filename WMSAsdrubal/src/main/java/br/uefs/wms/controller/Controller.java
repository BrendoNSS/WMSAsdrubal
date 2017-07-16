package br.uefs.wms.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import br.uefs.wms.model.Produto;
import br.uefs.wms.util.ArvoreAVL;

/**
 * Classe que contém os métodos de manipulução dos dados das mercadorias, o arquivo no qual
 * elas estão inseridas e a árvore AVL pela qual serão feitas as operações
 * @author Brendo Nascimento e Gabriel Azevedo
 */

public class Controller {
	
	ArvoreAVL arvore = new ArvoreAVL();
	int quantidadeMercadorias = 0;
	
	/**
	 * Método que faz a leitura dos dados de um arquivo e os coloca numa árvore avl.
	 * @param end Endereço do arquivo que será lido
	 * @return Uma resposta true caso o arquivo tenha sido carregado com sucesso, e false caso contrário
	 */
	public boolean lerAqruivo(String end){
		File arquivoCSV = new File(end);
		
		try{
	        @SuppressWarnings("resource")
			Scanner leitor = new Scanner(arquivoCSV);	        
	        String linhasDoArquivo = new String();	        
	        //percorre todo o arquivo
	        while(leitor.hasNext()){
	        	Produto novo = new Produto();
	            linhasDoArquivo = leitor.nextLine();	            
	            //separa os campos entre as virgulas de cada linha
	            novo.setVetor( linhasDoArquivo.split(";"));            
	            arvore.inserir(novo);
	            novo.toString();
	            quantidadeMercadorias++;
	        }
	        return true;
	    
	    }catch(FileNotFoundException e){
	        return false;
	    }
		
	}
	
	/**
	 * Método que insere os dados de uma nova mercadoria no arquivo
	 * @param lote Número referente ao lote do produto
	 * @param end Endereço do produto
	 * @param bloco Bloco no qual o produto se encontra
	 * @param numero Número do produto
	 * @param fornce Nome do fornecedor
	 * @param data Data de entrega do produto
	 * @param hr Hora de entrega do produto
	 * @return Um valor true indicado o sucesso da operação
	 */
	public boolean novaMercadoria(String lote, String end, String bloco, String numero, String fornce, String data, String hr){
		
		Produto novo = new Produto(lote,end,bloco,numero,fornce);
		novo.setData(data);
		novo.setHora(hr);
		
		if (acharProdutoPorCoda(lote,end,bloco,numero,fornce) == null){
			arvore.inserir(novo);	
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Método que adiciona um produto aos dados do arquivo
	 * @param caixa Objeto que contem os dados do produto que será adicionado no arquivo
	 * @param sFileName Nome do arquivo
	 * @return Uma resposta true caso o método tenha cumprido sua função e uma resposta false caso contrário
	 * @throws IOException Exceção de entrada e saída para o arquivo
	 */
	public boolean escreverNoArquivo(Produto caixa, String sFileName) throws IOException{		
		FileWriter writer = null;    
		try{
		        writer = new FileWriter(sFileName);
		        
		        for(int i=0;i<7;i++){
		        	writer.append(caixa.getVetor()[i]);
		        	writer.append(";");
		        }	      	        
		        return true;
		    }catch(IOException e){
		         e.printStackTrace();
		         return false;
		    }finally{
		    	writer.flush();
		        writer.close();
		    } 

	}
	
	/**
	 * Método que passa os dados da árvore avl para o arquivo
	 * @param sFileName Endereço do arquivo (nesse caso, o nome)
	 * @return True, caso tenha sido bem sucedido e false, caso contrário
	 * @throws IOException Exceção de entrada e saída para o arquivo
	 */
	public boolean escreverArvoreToda(String sFileName) throws IOException{
		
		FileWriter writer = null;
		try {
			writer = new FileWriter(sFileName, true);
			
			arvore.escreverArvore(writer);
			return true;
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}finally{
			writer.close();
		}

	}
	
	/**
	 * Método que faz a busca por um produto através dos dados de sua localização
	 * @param lote Número referente ao lote do produto
	 * @param end Endereço do produto
	 * @param bloco Bloco no qual o produto se encontra
	 * @param numero Número do produto
	 * @param fornecedor Nome do fornecedor
	 */
	public Produto acharProdutoPorCoda(String lote, String end, String bloco, String numero, String fornecedor){
		return arvore.retornar(new Produto(lote, end, bloco, numero, fornecedor));
	}
	
	/**
	 * Método que remove um produto da árvore avl
	 * @param lote Número referente ao lote do produto
	 * @param end Endereço do produto
	 * @param bloco Bloco no qual o produto se encontra
	 * @param numero Número do produto
	 * @param fornce Nome do fornecedor
	 * @param data Data de entrega do produto
	 * @param hr Hora de entrega do produto
	 * @param fornecedor Nome do fornecedor
	 */
	public void deletarProduto(String lote, String end, String bloco, String numero, String fornecedor){
		arvore.remover(new Produto(lote, end, bloco, numero, fornecedor));
	}
	
	/**
	 * Método que escreve no console os dados contidos na árvore avl
	 */
	public void printar(){
		
		arvore.posOrdem();
		
	}
	
	public void quickSort(Produto datashit[], int i, int j){
		
		if(i<j){
			int l = i,p= j, r = j-1;
			while(l <= r){
				while(l <= r && datashit[l].getLote().compareTo(datashit[p].getLote()) == - 1){
					l++;					
				}
				while(l <= r && datashit[l].getLote().compareTo(datashit[p].getLote()) == 1){
					r--;
				}
				if(l <= r){
					Produto temp = datashit[l];
					datashit[l] = datashit[r];
					datashit[r] = temp;
					l++;
					r--;
				}
			}
			Produto temp = datashit[l];
			datashit[l] = datashit[p];
			datashit[p] = temp;
			quickSort(datashit,i,l-1);
			quickSort(datashit,l+1,j);
		}
	}

}