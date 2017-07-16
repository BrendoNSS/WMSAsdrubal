package br.uefs.wms.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import br.uefs.wms.model.Produto;

/** Classe respons�vel pela constru��o da �rvore bin�ria
 * @author Brendo Nascimento e Gabriel Azevedo
 * @param raiz Primeiro n� da arvore
 */

public class ArvoreAVL{
	
	protected No raiz;
	
    /**
     * M�todo de inser��o na �rvore
     * @param k Objeto que ser� inserido
     */
	public void inserir(Produto k) {
		No n = new No(k);
		inserirAVL(this.raiz, n);
	}
    
	/**
	 * M�todo de inser��o na �rvore AVL
	 * @param aComparar N� que ser� comparado com o qual ser� inserido para colocar na posi��o adequada
	 * @param aInserir N� que ser� inserido na arvore
	 * @return Uma resposta true caso tenha inserido com sucesso e false caso contr�rio
	 */
	public boolean inserirAVL(No aComparar, No aInserir) {

		if (aComparar == null) {
			this.raiz = aInserir;
			return true;
		} else {

			if (aInserir.getChave().getLote().compareTo(aComparar.getChave().getLote()) == -1 ) {

				if (aComparar.getEsquerda() == null) {
					aComparar.setEsquerda(aInserir);
					aInserir.setPai(aComparar);
					verificarBalanceamento(aComparar);
					return true;
				} 
				else {
					inserirAVL(aComparar.getEsquerda(), aInserir);}

			} else if (aInserir.getChave().getLote().compareTo(aComparar.getChave().getLote()) == 1) {

				if (aComparar.getDireita() == null) {
					aComparar.setDireita(aInserir);
					aInserir.setPai(aComparar);
					verificarBalanceamento(aComparar);
					return true;
				} 
				else {
					inserirAVL(aComparar.getDireita(), aInserir);}

			} else {
				if (aInserir.getChave().getEndereco().compareTo(aComparar.getChave().getEndereco()) == -1 ){
					//inser��o logo a esquerda
					if (aComparar.getEsquerda() == null) {
						aComparar.setEsquerda(aInserir);
						aInserir.setPai(aComparar);
						verificarBalanceamento(aComparar);
						return true;
					} 
					else {
						inserirAVL(aComparar.getEsquerda(), aInserir);}

					
				}
				else if(aInserir.getChave().getEndereco().compareTo(aComparar.getChave().getEndereco()) == 1){
					//insercao logo a direita
					if (aComparar.getDireita() == null) {
						aComparar.setDireita(aInserir);
						aInserir.setPai(aComparar);
						verificarBalanceamento(aComparar);
						return true;
					} 
					else {
						inserirAVL(aComparar.getDireita(), aInserir);}

				}
				else{
					if(aInserir.getChave().getBloco().compareTo(aComparar.getChave().getBloco()) == -1){
						//inser��o logo a esquerda
						if (aComparar.getEsquerda() == null) {
							aComparar.setEsquerda(aInserir);
							aInserir.setPai(aComparar);
							verificarBalanceamento(aComparar);
							return true;
						} 
						else {
							inserirAVL(aComparar.getEsquerda(), aInserir);}
					}
					else if(aInserir.getChave().getBloco().compareTo(aComparar.getChave().getBloco()) == 1){
						//insercao logo a direita
						if (aComparar.getDireita() == null) {
							aComparar.setDireita(aInserir);
							aInserir.setPai(aComparar);
							verificarBalanceamento(aComparar);
							return true;
						} 
						else {
							inserirAVL(aComparar.getDireita(), aInserir);}
					}
					else{
						if (aInserir.getChave().getNumero().compareTo(aComparar.getChave().getNumero()) == -1){
							//inser��o logo a esquerda
							if (aComparar.getEsquerda() == null) {
								aComparar.setEsquerda(aInserir);
								aInserir.setPai(aComparar);
								verificarBalanceamento(aComparar);
								return true;
							} 
							else {
								inserirAVL(aComparar.getEsquerda(), aInserir);}
						}
						else if (aInserir.getChave().getNumero().compareTo(aComparar.getChave().getNumero()) == 1){
							//insercao logo a direita
							if (aComparar.getDireita() == null) {
								aComparar.setDireita(aInserir);
								aInserir.setPai(aComparar);
								verificarBalanceamento(aComparar);
								return true;
							} 
							else {
								inserirAVL(aComparar.getDireita(), aInserir);}
						}
						else{
							if (aComparar.getChave().getFornecedor().compareTo(aInserir.getChave().getFornecedor()) == -1){
								//inser��o logo a esquerda
								if (aComparar.getEsquerda() == null) {
									aComparar.setEsquerda(aInserir);
									aInserir.setPai(aComparar);
									verificarBalanceamento(aComparar);
									return true;
								} 
								else {
									inserirAVL(aComparar.getEsquerda(), aInserir);}
							}
							else if (aComparar.getChave().getFornecedor().compareTo(aInserir.getChave().getFornecedor()) == 1){
								//insercao logo a direita
								if (aComparar.getDireita() == null) {
									aComparar.setDireita(aInserir);
									aInserir.setPai(aComparar);
									verificarBalanceamento(aComparar);
									return true;
								} 
								else {
									inserirAVL(aComparar.getDireita(), aInserir);}
							}
							else{
								return false;
							}
						}
					}
				}

			}
		}
		return false;
	}
    
	/**
	 * M�todo que verifica o balanceamento da �rvore AVL e � chamado no m�todo de inserir
	 * @param atual N� pelo qual ser� verificado o balanceamento de uma sub�rovre da qual ele � raiz
	 */
	public void verificarBalanceamento(No atual) {
		setBalanceamento(atual);
		int balanceamento = atual.getBalanceamento();

		if (balanceamento == -2) {

			if (altura(atual.getEsquerda().getEsquerda()) >= altura(atual.getEsquerda().getDireita())) {
				atual = rotacaoDireita(atual);

			} else {
				atual = duplaRotacaoEsquerdaDireita(atual);
			}

		} else if (balanceamento == 2) {

			if (altura(atual.getDireita().getDireita()) >= altura(atual.getDireita().getEsquerda())) {
				atual = rotacaoEsquerda(atual);

			} else {
				atual = duplaRotacaoDireitaEsquerda(atual);
			}
		}

		if (atual.getPai() != null) {
			verificarBalanceamento(atual.getPai());
		} else {
			this.raiz = atual;
		}
	}
    
	/**
	 * M�todo para remo��o em arvore AVL
	 * @param k Produto que ser� removido pelo m�todo
	 */
	public void remover(Produto k) {
		removerAVL(this.raiz, k);
	}
    
	/**
	 * M�todo que faz a busca pelo elemento que ser� removido e o remove
	 * @param atual N� pelo qual se inicia a busca
	 * @param k Objeto que ser� removido da �rovre
	 */
	public void removerAVL(No atual, Produto k) {
		if (atual == null) {
			return;

		} else {

			if (atual.getChave().compareTo(k) > 0) {
				removerAVL(atual.getEsquerda(), k);

			} else if (atual.getChave().compareTo(k) <0) {
				removerAVL(atual.getDireita(), k);

			} else if (atual.getChave().compareTo(k) == 0) {
				removerNoEncontrado(atual);
			}
		}
	}
    
	/**
	 * M�todo pelo qual � feito a remo��o do m�todo encontrado no m�todo removerAVL
	 * @param aRemover N� que foi encontrado e ser� removido
	 */
	public void removerNoEncontrado(No aRemover) {
		No r;

		if (aRemover.getEsquerda() == null || aRemover.getDireita() == null) {

			if (aRemover.getPai() == null) {
				this.raiz = null;
				aRemover = null;
				return;
			}
			r = aRemover;

		} else {
			r = sucessor(aRemover);
			aRemover.setChave(r.getChave());
		}

		No p;
		if (r.getEsquerda() != null) {
			p = r.getEsquerda();
		} else {
			p = r.getDireita();
		}

		if (p != null) {
			p.setPai(r.getPai());
		}

		if (r.getPai() == null) {
			this.raiz = p;
		} else {
			if (r == r.getPai().getEsquerda()) {
				r.getPai().setEsquerda(p);
			} else {
				r.getPai().setDireita(p);
			}
			verificarBalanceamento(r.getPai());
		}
		r = null;
	}
    
	/**
	 * M�todo que retorna um produto que foi buscado na �rvore
	 * @param bota Produto que ser� buscado
	 * @return O m�todo que retorna o produto que foi buscado na �rvore
	 */
	public Produto retornar(Produto bota){
		return retornarAVL(this.raiz, bota);
	}
	
	/**
	 * M�todo pelo qual � retornado um n� com o produto buscado
	 * @param atual N� pelo qual come�a a busca
	 * @param bota Produto que ser� encontrado na �rvore
	 * @return O n� encontrado pela busca atrav�s do par�metro bota
	 */
	public Produto retornarAVL(No atual, Produto bota){
		if(atual != null){
			if(bota.compareTo(atual.getChave())<0){
				return retornarAVL(atual.getEsquerda(),bota);
			}
			else if(bota.compareTo(atual.getChave())>0){
				return retornarAVL(atual.getDireita(),bota);
			}
			else{
				return atual.getChave();
			}
		}
		else{
			return null;
		}
	}
	
	/**
	 * M�todo que realiza a organiza��o dos n�s a esquerda da �rvore para garantir o seu balanceamento
	 * @param inicial N� pelo qual se inicia a rota��o
	 * @return A sub�rvore balanceada
	 */
	public No rotacaoEsquerda(No inicial) {

		No direita = inicial.getDireita();
		direita.setPai(inicial.getPai());

		inicial.setDireita(direita.getEsquerda());

		if (inicial.getDireita() != null) {
			inicial.getDireita().setPai(inicial);
		}

		direita.setEsquerda(inicial);
		inicial.setPai(direita);

		if (direita.getPai() != null) {

			if (direita.getPai().getDireita() == inicial) {
				direita.getPai().setDireita(direita);
			
			} else if (direita.getPai().getEsquerda() == inicial) {
				direita.getPai().setEsquerda(direita);
			}
		}

		setBalanceamento(inicial);
		setBalanceamento(direita);

		return direita;
	}
    
	/**
	 * M�todo que realiza a organiza��o dos n�s a direita da �rvore para garantir o seu balanceamento
	 * @param inicial N� pelo qual se inicia a rota��o
	 * @return A sub�rovre balanceada
	 */
	public No rotacaoDireita(No inicial) {

		No esquerda = inicial.getEsquerda();
		esquerda.setPai(inicial.getPai());

		inicial.setEsquerda(esquerda.getDireita());

		if (inicial.getEsquerda() != null) {
			inicial.getEsquerda().setPai(inicial);
		}

		esquerda.setDireita(inicial);
		inicial.setPai(esquerda);

		if (esquerda.getPai() != null) {

			if (esquerda.getPai().getDireita() == inicial) {
				esquerda.getPai().setDireita(esquerda);
			
			} else if (esquerda.getPai().getEsquerda() == inicial) {
				esquerda.getPai().setEsquerda(esquerda);
			}
		}

		setBalanceamento(inicial);
		setBalanceamento(esquerda);

		return esquerda;
	}
    
	/**
	 * M�todo que faz a rota��o nas sub�rvores da esquerda para a direita
	 * @param inicial N� pelo qual se inicia a rota��o
	 * @return O resultado da rota��o pela direita do n� inicial
	 */
	public No duplaRotacaoEsquerdaDireita(No inicial) {
		inicial.setEsquerda(rotacaoEsquerda(inicial.getEsquerda()));
		return rotacaoDireita(inicial);
	}
    
	/**
	 * M�todo que faz a rota��o nas sub�rvores direita e equerda
	 * @param inicial N� pelo qual se inicia a rota��o
	 * @return O resultado da rota��o pela direita do n� inicial
	 */
	public No duplaRotacaoDireitaEsquerda(No inicial) {
		inicial.setDireita(rotacaoDireita(inicial.getDireita()));
		return rotacaoEsquerda(inicial);
	}
    
	/**
	 * M�todo que coloca um n� no lugar de outro quando este �ltimo � removido
	 * @param q N� que ser� removido do seu lugar original
	 * @return O n� que ficou no lugar do n� p
	 */
	public No sucessor(No q) {
		if (q.getDireita() != null) {
			No r = q.getDireita();
			while (r.getEsquerda() != null) {
				r = r.getEsquerda();
			}
			return r;
		} else {
			No p = q.getPai();
			while (p != null && q == p.getDireita()) {
				q = p;
				p = q.getPai();
			}
			return p;
		}
	}
    
	/**
	 * M�todo que informa a altura de uma sub�rvore ou da �rvore inteira
	 * @param atual N� pelo qual se come�a a contar a altura
	 * @return A altura da �rvore, seja pela sub�rvore direita, esquerda ou a �rvore inteira
	 */
	private int altura(No atual) {
		if (atual == null) {
			return -1;
		}

		if (atual.getEsquerda() == null && atual.getDireita() == null) {
			return 0;
		
		} else if (atual.getEsquerda() == null) {
			return 1 + altura(atual.getDireita());
		
		} else if (atual.getDireita() == null) {
			return 1 + altura(atual.getEsquerda());
		
		} else {
			return 1 + Math.max(altura(atual.getEsquerda()), altura(atual.getDireita()));
		}
	}
    
	/**
	 * M�todo que altera o valor da vari�vel que regula o balanceamento dos n�s da �rvore
	 * @param no N� cujo valor do fator de balanceamento ser� alterado
	 */
	private void setBalanceamento(No no) {
		no.setBalanceamento(altura(no.getDireita()) - altura(no.getEsquerda()));
	}

	final protected ArrayList<No> inOrder() {
		ArrayList<No> ret = new ArrayList<No>();
		inOrder(raiz, ret);
		return ret;
	}

	final protected void inOrder(No no, ArrayList<No> lista) {
		if (no == null) {
			return;
		}
		inOrder(no.getEsquerda(), lista);
		lista.add(no);
		inOrder(no.getDireita(), lista);
	}
	
	/**
	 * M�todo que escreve na tela os dados de uma �rvore a partir da raiz
	 */
	public void posOrdem() {
        posOrdem(raiz);
    }
	
	/**
	 * M�todo que escreve na tela os dados de uma �rvore a partir de um certo n�
	 * @param p N� pelo qual se come�a a percorrer o caminho da �rvore e escrever os dados
	 */
    protected void posOrdem(No p) {
        if (p != null) {
             posOrdem(p.getEsquerda());
             posOrdem(p.getDireita());
             System.out.print(p.getChave().toString());
        }
    }
    
    /**
     * M�todo que procura o n� pai de um determinado n� atrav�s da sua chave (endere�o)
     * @param el Atributo que representa o endere�o do n� cujo pai est� sendo buscado
     * @return
     */
    protected No procurarPai (String el) {
        No p = raiz;
        No ant = null;
        while (p != null && !(p.getChave().getEndereco().compareTo(el) == 0)) {  // acha o n� p com a chave el
            ant = p;                           
            if (p.getChave().getEndereco().compareTo(el) == -1)
                  p = p.getDireita();
            else p = p.getEsquerda();
        }
        if (p!=null && p.getChave().getEndereco().compareTo(el) == 0)         	
        	return ant;
        return null;
    }
    
    public boolean escreverArvore(FileWriter writer) throws IOException {
    	if (this.raiz == null){
    	    return false;
        }    		
		String separador = String.valueOf("\r\n");
		try {
			writer.write(this.raiz.getChave().toString());
			writer.write("\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		mostrarSubArvore(raiz.getEsquerda(),  separador, writer);
		mostrarSubArvore(raiz.getDireita(), separador, writer);
		return true;
	}
    
    private void mostrarSubArvore(No no, String separador, FileWriter writer) throws IOException {
		if (no != null) {				
				writer.write(separador+no.getChave().toString());
				writer.write("\n");
			
			mostrarSubArvore(no.getEsquerda(),  separador, writer);
			mostrarSubArvore(no.getDireita(), separador, writer);
		}
    }
    
}