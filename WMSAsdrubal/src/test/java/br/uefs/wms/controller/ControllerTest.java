package br.uefs.wms.controller;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import br.uefs.wms.model.Produto;
import junit.framework.TestCase;

public class ControllerTest extends TestCase{
	
    private Controller control;
    
	@Before
	public void setUp(){
		control = new Controller();
	}
	
	@Test
	public void testLerArquivo() {
		
		String local = "armazem.csv";
		boolean pegou = control.lerAqruivo(local);
		control.printar();
		assertEquals(true, pegou);
		
	}
	
	@Test
	public void testAcharProdutoPorCodaEEscreverArvoreToda() throws IOException{			
		
		control.lerAqruivo("armazem.csv");
		String lotee = "1";
		String primeiro = "16587";
		String bloco = "B1";
		String numero = "513";
		String forne = "F1";
		System.out.println(control.quantidadeMercadorias);
		
		Produto test = control.acharProdutoPorCoda(lotee, primeiro, bloco, numero, forne);
		if(test == null){
			System.out.println("deu ruim berg");
		}
		boolean test2 = control.escreverArvoreToda("armario.csv");
		assertEquals(true, test2);
		assertEquals("1", test.getLote());
	}
	
	@Test
	public void testDeletarProduto(){
	
		control.lerAqruivo("armazem.csv");
		String lotee = "1";
		String primeiro = "16587";
		String bloco = "B1";
		String numero = "513";
		String forne = "F1";
		
		control.deletarProduto(lotee, primeiro, bloco, numero, forne);
		
		Produto test = control.acharProdutoPorCoda(lotee, primeiro, bloco, numero, forne);
		
		assertEquals(null, test);
		
	}
	
	@Test
	public void testAdicionaNovoProduto(){
			
		control.lerAqruivo("armazem.csv");
		
		String lotee = "4";
		String primeiro = "44568";
		String bloco = "T1";
		String numero = "513";
		String forne = "G1";
		String data = "23/12/2017";
		String hr = "13:00";
		
		assertEquals(true, control.novaMercadoria(lotee, primeiro, bloco, numero, forne, data, hr));
		
	}
	
	@Test
	public void testProdutosIdenticos(){
		
		control.lerAqruivo("armazem.csv");
		
		/*Esses primeiros dados são adicionados e não devem ser adicionados na arvore
		  são iguais aos dados de outro produto que já está no arquivo*/
		String lotee = "1";
		String primeiro = "16587";
		String bloco = "B1";
		String numero = "513";
		String forne = "F1";
		String data = "22/03/2017";
		String hr = "12:00";
		
		/*Esses segundos dados são adicionados e podem ser adicionados na arvore
		  pois o atributo lote o diferencia de todos os outros produtos que já estão no arquivo*/
		String lotee2 = "5";
		String segundo = "16587";
		String bloco2 = "B1";
		String numero2 = "513";
		String forne2 = "F1";
		String data2 = "22/03/2017";
		String hr2 = "12:00";
		
		assertEquals(false, control.novaMercadoria(lotee, primeiro, bloco, numero, forne, data, hr));
		assertEquals(true, control.novaMercadoria(lotee2, segundo, bloco2, numero2, forne2, data2, hr2));
		
	}
	
	@Test
	public void testOrdenarArvorePorLote(){
		
		String lotee = "1";
		String primeiro = "16587";
		String bloco = "B1";
		String numero = "513";
		String forne = "F1";
		String data = "22/03/2017";
		String hr = "12:00";
		
		String lotee2 = "5";
		String segundo = "16587";
		String bloco2 = "B1";
		String numero2 = "513";
		String forne2 = "F1";
		String data2 = "22/03/2017";
		String hr2 = "12:00";
		
		String lotee3 = "3";
		String terceiro = "16789";
		String bloco3 = "B2";
		String numero3 = "543";
		String forne3 = "F5";
		String data3 = "22/03/2017";
		String hr3 = "12:05";
		
		control.novaMercadoria(lotee, primeiro, bloco, numero, forne, data, hr);
		control.novaMercadoria(lotee2, segundo, bloco2, numero2, forne2, data2, hr2);
		control.novaMercadoria(lotee3, terceiro, bloco3, numero3, forne3, data3, hr3);
		
		Produto p1 = control.acharProdutoPorCoda(lotee, primeiro, bloco, numero, forne);
		Produto p2 = control.acharProdutoPorCoda(lotee2, segundo, bloco2, numero2, forne2);
		Produto p3 = control.acharProdutoPorCoda(lotee3, terceiro, bloco3, numero3, forne3);
		
		Produto produtos[] = {p1, p2, p3};
		
		control.quickSort(produtos, 0, produtos.length);
		
		assertEquals("1", produtos[0].getLote());
	}
	
}