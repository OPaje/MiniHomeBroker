/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package homebroker;

/**
 *
 * @author Stheffany
 */
public class ContaCorrente {
    int id;
    double saldo;
    String dataCriacao;
    String dataModificacao;    
    Cliente c;
   
    void depositar(double valor){
        this.saldo = this.saldo + valor; // this referencia o objeto corrente, nesse caso o da conta, diferenciar dados da classe com os do parâmetro
    }

    boolean sacar(double valor){
        if(this.saldo < valor){
            return false;
        }else{
            this.saldo = this.saldo - valor;
            return true;
        }
    }

    String mostraSaldo(){
        return "Saldo conta do(a) " + this.c.nome + ": " + saldo;
    }
    
    boolean transfere(ContaCorrente destino, double valor){
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
