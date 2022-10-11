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
public class OrdemExecucao {
    private long id;
    private static long serial = 0;
    private Ordem ordem;
    private ContaCorrente contaCompra;
    private ContaCorrente contaVenda;
    private int quantidade;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    public OrdemExecucao() {
        
        this.id = ++serial;
    }

    public long getId() {
        return id;
    }

    public Ordem getOrdem() {
        return ordem;
    }

    public void setOrdem(Ordem ordem) {
        this.ordem = ordem;
    }

    public ContaCorrente getContaCompra() {
        return contaCompra;
    }

    public void setContaCompra(ContaCorrente contaCompra) {
        this.contaCompra = contaCompra;
    }

    public ContaCorrente getContaVenda() {
        return contaVenda;
    }

    public void setContaVenda(ContaCorrente contaVenda) {
        this.contaVenda = contaVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
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
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final OrdemExecucao other = (OrdemExecucao) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "\nID = " + id + "\nOrdem = " + ordem + "\nConta que comprou = " + contaCompra + "\nConta que vendeu = " + contaVenda + 
                "\nQuantidade = " + quantidade + "\nData de criacao = " + dataCriacao + "\nData de Modificacao=" + dataModificacao;
    }
    
    
    
    
}
