/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author jeanc
 */
public class Ordem {
    private long id;
    private static long serialOrdem = 0;
    private int quantidade;
    private int tipoOrdem;
    private int estadoOrdem;
    private double valor;
    private double valorTotal;
    private ContaCorrente conta;
    private Ativo ticker;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
    
    public Ordem(){
        this.id = ++Ordem.serialOrdem;
    }

    public long getId() {
        return id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getTipoOrdem() {
        return tipoOrdem;
    }

    public void setTipoOrdem(int tipoOrdem) {
        this.tipoOrdem = tipoOrdem;
    }

    public int getEstadoOrdem() {
        return estadoOrdem;
    }

    public void setEstadoOrdem(int estadoOrdem) {
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

    // MELHORAR ESSE TOSTRING
    @Override
    public String toString() {
        return "Ordem{" + "id=" + id + ", quantidade=" + quantidade + ", tipoOrdem=" + tipoOrdem + ", estadoOrdem=" + estadoOrdem + ", valor=" + valor + 
                ", valorTotal=" + valorTotal + ", conta=" + conta + ", ticker=" + ticker + ", dataCriacao=" + dataCriacao + ", dataModificacao=" + dataModificacao + '}';
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
