/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

import java.time.LocalDate;


/**
 *
 * @author jeanc
 */
public class Ordem {
    private long id;
    private int quantidade;
    private double valor;
    private double valorTotal;
    private String tipoOrdem;
    private String estadoOrdem;
    private ContaCorrente conta;
    private Ativo ticker;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    public long getId() {
        return id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipoOrdem() {
        return tipoOrdem;
    }

    public void setTipoOrdem(String tipoOrdem) {
        this.tipoOrdem = tipoOrdem;
    }

    public String getEstadoOrdem() {
        return estadoOrdem;
    }

    public void setEstadoOrdem(String estadoOrdem) {
        this.estadoOrdem = estadoOrdem;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public ContaCorrente getConta() {
        return conta;
    }

    public void setConta(ContaCorrente conta) {
        this.conta = conta;
    }

    public Ativo getTicker() {
        return ticker;
    }

    public void setTicker(Ativo ticker) {
        this.ticker = ticker;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDate dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    @Override
    public String toString() {
        return "\n\nid: " + id + "\nQuantidade: " + quantidade + "\nTipo da Ordem: " + tipoOrdem + "\nEstado da Ordem: " + estadoOrdem + "\nValor: " + valor + 
                "\nValor Total: " + valorTotal + "\nConta: " + conta.getC().getNome() + "\nTicker: " + ticker.getTicker() +"\nNome da empresa: " + ticker.getNomeEmpresa() +
                "\nData de Criacao:" + dataCriacao + 
                "\nData de Modificacao: " + dataModificacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ordem other = (Ordem) obj;
        return this.id == other.id;
    }
    
    
    
}
