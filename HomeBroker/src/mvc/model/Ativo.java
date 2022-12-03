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
public class Ativo {
    private long id;
    private int totalAtivos;
    private double precoInicial;
    private String nomeEmpresa;
    private String ticker;       
    private LocalDate dataCriacao;
    private LocalDate dataModficacao;

    public long getId() {
        return id;
    }

    public int getTotalAtivos() {
        return totalAtivos;
    }

    public void setTotalAtivos(int totalAtivos) {
        this.totalAtivos = totalAtivos;
    }

    public double getPrecoInicial() {
        return precoInicial;
    }

    public void setPrecoInicial(double precoInicial) {
        this.precoInicial = precoInicial;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataModficacao() {
        return dataModficacao;
    }

    public void setDataModficacao(LocalDate dataModficacao) {
        this.dataModficacao = dataModficacao;
    }

    @Override
    public String toString() {
        return "\nAtivo id=" + id + " \nNome da Empresa = " + nomeEmpresa + "\nTicker = " + ticker + "\nTotal de Ativos = " + totalAtivos + 
                "\nPreço Inicial = " + precoInicial + "\nData de Criação = " + dataCriacao + "\nData de Modficacao = " + dataModficacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Ativo other = (Ativo) obj;
        return this.id == other.id;
    }
    
    
}
