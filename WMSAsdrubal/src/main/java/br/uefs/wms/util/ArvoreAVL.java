package br.uefs.wms.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import br.uefs.wms.model.Produto;

/** Classe responsável pela construção da árvore binária
 * @author Brendo Nascimento e Gabriel Azevedo
 * @param raiz Primeiro nó da arvore
 */

public class ArvoreAVL{
	
	protected No raiz;
	
    /**
     * Método de inserção na árvore
     * @param k Objeto que será inserido
     */
	public void inserir(Produto k) {
		No n = new No(k);
		inserirAVL(this.raiz, n);
	}
    
	/**
	 * Método de inserção na árvore AVL
	 * @param aComparar Nó que será comparado com o qual será inserido para colocar na posição adequada
	 * @param aInserir Nó que será inserido na arvore
	 * @return Uma resposta true caso tenha inserido com sucesso e false caso contrário
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
					//inserção logo a esquerda
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
						//inserção logo a esquerda
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
							//inserção logo a esquerda
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
								//inserção logo a esquerda
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
	 * Método que verifica o balanceamento da árvore AVL e é chamado no método de inserir
	 * @param atual Nó pelo qual será verificado o balanceamento de uma subárovre da qual ele é raiz
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
	 * Método para remoção em arvore AVL
	 * @param k Produto que será removido pelo método
	 */
	public void remover(Produto k) {
		removerAVL(this.raiz, k);
	}
    
	/**
	 * Método que faz a busca pelo elemento que será removido e o remove
	 * @param atual Nó pelo qual se inicia a busca
	 * @param k Objeto que será removido da árovre
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
	 * Método pelo qual é feito a remoção do método encontrado no método removerAVL
	 * @param aRemover Nó que foi encontrado e será removido
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
	 * Método que retorna um produto que foi buscado na árvore
	 * @param bota Produto que será buscado
	 * @return O método que retorna o produto que foi buscado na árvore
	 */
	public Produto retornar(Produto bota){
		return retornarAVL(this.raiz, bota);
	}
	
	/**
	 * Método pelo qual é retornado um nó com o produto buscado
	 * @param atual Nó pelo qual começa a busca
	 * @param bota Produto que será encontrado na árvore
	 * @return O nó encontrado pela busca através do parâmetro bota
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
	 * Método que realiza a organização dos nós a esquerda da árvore para garantir o seu balanceamento
	 * @param inicial Nó pelo qual se inicia a rotação
	 * @return A subárvore balanceada
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
	 * Método que realiza a organização dos nós a direita da árvore para garantir o seu balanceamento
	 * @param inicial Nó pelo qual se inicia a rotação
	 * @return A subárovre balanceada
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
	 * Método que faz a rotação nas subárvores da esquerda para a direita
	 * @param inicial Nó pelo qual se inicia a rotação
	 * @return O resultado da rotação pela direita do nó inicial
	 */
	public No duplaRotacaoEsquerdaDireita(No inicial) {
		inicial.setEsquerda(rotacaoEsquerda(inicial.getEsquerda()));
		return rotacaoDireita(inicial);
	}
    
	/**
	 * Método que faz a rotação nas subárvores direita e equerda
	 * @param inicial Nó pelo qual se inicia a rotação
	 * @return O resultado da rotação pela direita do nó inicial
	 */
	public No duplaRotacaoDireitaEsquerda(No inicial) {
		inicial.setDireita(rotacaoDireita(inicial.getDireita()));
		return rotacaoEsquerda(inicial);
	}
    
	/**
	 * Método que coloca um nó no lugar de outro quando este último é removido
	 * @param q Nó que será removido do seu lugar original
	 * @return O nó que ficou no lugar do nó p
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
	 * Método que informa a altura de uma subárvore ou da árvore inteira
	 * @param atual Nó pelo qual se começa a contar a altura
	 * @return A altura da árvore, seja pela subárvore direita, esquerda ou a árvore inteira
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
	 * Método que altera o valor da variável que regula o balanceamento dos nós da árvore
	 * @param no Nó cujo valor do fator de balanceamento será alterado
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
	 * Método que escreve na tela os dados de uma árvore a partir da raiz
	 */
	public void posOrdem() {
        posOrdem(raiz);
    }
	
	/**
	 * Método que escreve na tela os dados de uma árvore a partir de um certo nó
	 * @param p Nó pelo qual se começa a percorrer o caminho da árvore e escrever os dados
	 */
    protected void posOrdem(No p) {
        if (p != null) {
             posOrdem(p.getEsquerda());
             posOrdem(p.getDireita());
             System.out.print(p.getChave().toString());
        }
    }
    
    /**
     * Método que procura o nó pai de um determinado nó através da sua chave (endereço)
     * @param el Atributo que representa o endereço do nó cujo pai está sendo buscado
     * @return
     */
    protected No procurarPai (String el) {
        No p = raiz;
        No ant = null;
        while (p != null && !(p.getChave().getEndereco().compareTo(el) == 0)) {  // acha o nó p com a chave el
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