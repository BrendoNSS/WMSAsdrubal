package br.uefs.wms.util;

import br.uefs.wms.model.Produto;

/**
 * Classe que representa um nó da árvore, ou vértice
 * @author Brendo Nascimento e Gabriel Azevedo
 */
public class No{
	
	private No esquerda;
	private No direita;
	private No pai;
	private Produto chave;
	private int balanceamento;

	public No(Produto k) {
		setEsquerda(setDireita(setPai(null)));
		setBalanceamento(0);
		setChave(k);
	}

	public String toString() {
		return chave.toString();
	}

	public Produto getChave() {
		return chave;
	}

	public void setChave(Produto chave) {
		this.chave = chave;
	}

	public int getBalanceamento() {
		return balanceamento;
	}

	public void setBalanceamento(int balanceamento) {
		this.balanceamento = balanceamento;
	}

	public No getPai() {
		return pai;
	}

	public No setPai(No pai) {
		this.pai = pai;
		return pai;
	}

	public No getDireita() {
		return direita;
	}

	public No setDireita(No direita) {
		this.direita = direita;
		return direita;
	}

	public No getEsquerda() {
		return esquerda;
	}

	public void setEsquerda(No esquerda) {
		this.esquerda = esquerda;
	}

}