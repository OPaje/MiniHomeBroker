/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Stheffany
 */
public class ContaCorrenteDAO {
    
    ContaCorrente[] contas = new ContaCorrente[5];

    public ContaCorrente[] getContaCorrente() {
        return contas;
    }    
    
    public void depositar(double valor){
        this.saldo = this.saldo + valor;
    }

    public boolean sacar(double valor){
        if(this.saldo < valor){
            return false;
        }else{
            this.saldo = this.saldo - valor;
            return true;
        }
    }

    public String mostraSaldo(){
        return "Saldo conta do(a) " + this.c.getNome() + ": " + saldo;
    }
    
    public boolean transfere(ContaCorrente destino, double valor){
        boolean retirou = this.sacar(valor);
        if(retirou == false){
        return false;
        }else{
            destino.depositar(valor);
            return true;
        }
    }

    
    void extrato(){
        
    }
    
    void pagamento(){
        //transferir para a conta do administrador?
    }    
}
