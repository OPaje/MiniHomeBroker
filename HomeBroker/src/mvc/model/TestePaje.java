/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mvc.model;

import java.time.LocalDate;

/**
 *
 * @author jeanc
 */
public class TestePaje {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        OrdemDAO ordemDAO = new OrdemDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO(clienteDAO.getClientes());
        Cliente c = new Cliente();
        AtivoDAO ativoDAO = new AtivoDAO();
        Ordem o1 = new Ordem();
        
//        c = clienteDAO.getClientes();
//        for (Cliente cliente : c) {
//            System.out.println(cliente.toString());
//        }
        
        o1.setConta(contaCorrenteDAO.buscaPorId(0));
        o1.setTipoOrdem("Venda");
        o1.setTicker(ativoDAO.buscaPorTicker("NTCO3"));
        o1.setQuantidade(40);
        o1.setValor(10);
        o1.setEstadoOrdem("VVVVV");
        o1.setValorTotal(o1.getValor() * o1.getQuantidade());
        o1.setDataCriacao(LocalDate.now());
        o1.setDataModificacao(LocalDate.now());
        
        ordemDAO.adicionaOrdem(o1);
    }
    
}
