package br.uefs.wms.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import br.uefs.wms.model.Produto;
import br.uefs.wms.util.ArvoreAVL;

/**
 * Classe que cont�m os m�todos de manipulu��o dos dados das mercadorias, o arquivo no qual
 * elas est�o inseridas e a �rvore AVL pela qual ser�o feitas as opera��es
 * @author Brendo Nascimento e Gabriel Azevedo
 */

public class Controller {
	
	ArvoreAVL arvore = new ArvoreAVL();
	int quantidadeMercadorias = 0;
	
	/**
	 * M�todo que faz a leitura dos dados de um arquivo e os coloca numa �rvore avl.
	 * @param end Endere�o do arquivo que ser� lido
	 * @return Uma resposta true caso o arquivo tenha sido carregado com sucesso, e false caso contr�rio
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
	 * M�todo que insere os dados de uma nova mercadoria no arquivo
	 * @param lote N�mero referente ao lote do produto
	 * @param end Endere�o do produto
	 * @param bloco Bloco no qual o produto se encontra
	 * @param numero N�mero do produto
	 * @param fornce Nome do fornecedor
	 * @param data Data de entrega do produto
	 * @param hr Hora de entrega do produto
	 * @return Um valor true indicado o sucesso da opera��o
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
	 * M�todo que adiciona um produto aos dados do arquivo
	 * @param caixa Objeto que contem os dados do produto que ser� adicionado no arquivo
	 * @param sFileName Nome do arquivo
	 * @return Uma resposta true caso o m�todo tenha cumprido sua fun��o e uma resposta false caso contr�rio
	 * @throws IOException Exce��o de entrada e sa�da para o arquivo
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
	 * M�todo que passa os dados da �rvore avl para o arquivo
	 * @param sFileName Endere�o do arquivo (nesse caso, o nome)
	 * @return True, caso tenha sido bem sucedido e false, caso contr�rio
	 * @throws IOException Exce��o de entrada e sa�da para o arquivo
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
	 * M�todo que faz a busca por um produto atrav�s dos dados de sua localiza��o
	 * @param lote N�mero referente ao lote do produto
	 * @param end Endere�o do produto
	 * @param bloco Bloco no qual o produto se encontra
	 * @param numero N�mero do produto
	 * @param fornecedor Nome do fornecedor
	 */
	public Produto acharProdutoPorCoda(String lote, String end, String bloco, String numero, String fornecedor){
		return arvore.retornar(new Produto(lote, end, bloco, numero, fornecedor));
	}
	
	/**
	 * M�todo que remove um produto da �rvore avl
	 * @param lote N�mero referente ao lote do produto
	 * @param end Endere�o do produto
	 * @param bloco Bloco no qual o produto se encontra
	 * @param numero N�mero do produto
	 * @param fornce Nome do fornecedor
	 * @param data Data de entrega do produto
	 * @param hr Hora de entrega do produto
	 * @param fornecedor Nome do fornecedor
	 */
	public void deletarProduto(String lote, String end, String bloco, String numero, String fornecedor){
		arvore.remover(new Produto(lote, end, bloco, numero, fornecedor));
	}
	
	/**
	 * M�todo que escreve no console os dados contidos na �rvore avl
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