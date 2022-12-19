/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author jeanc
 */
public class OrdemExecucaoDAO {

//    public void executarOrdem(OrdemDAO ordens, ContaCorrenteDAO conta, MeusAtivosDAO meusAtivos, MovimentaContaDAO movimenta){
//        int k = 0;
//        Ordem[] todas = ordens.getOrdens();
//        Ordem[] ordensVenda = new Ordem[10];
//        Ordem[] ordensCompra = new Ordem[10];
//        
//        for(int i=0; i<todas.length; i++){
//            if(todas[i] != null){
//                if(todas[i].getTipoOrdem().equals("Compra")){
//                    ordensCompra[k] = todas[i];
//                    k++;
//                }
//            }
//        }
//        
//        k = 0;
//         for(int i=0; i<todas.length; i++){
//             if(todas[i] != null){
//                if(todas[i].getTipoOrdem().equals("Venda")){
//                    ordensVenda[k] = todas[i];
//                    k++;
//                }
//             }
//        }
//        
//        
//        for(int i=0; i<ordensVenda.length; i++){
//            if(ordensVenda[i] != null){
//                for(int j=0; j<ordensCompra.length; j++){
//                    if(ordensCompra[j] != null){
//                        if(ordensVenda[i].getTicker().equals(ordensCompra[j].getTicker())){
//                            if(ordensVenda[i].getValor()== ordensCompra[j].getValor()){
//                                if(ordensVenda[i].getQuantidade() == 0){ 
//                                    ordensCompra[j].setEstadoOrdem("Não");
//
//                                }else if(ordensVenda[i].getQuantidade() == ordensCompra[j].getQuantidade()){
//                                    OrdemExecucao o = new OrdemExecucao();
//                                    
//                                                
//                                    ordensCompra[j].setEstadoOrdem("Total");
//                                    ordensVenda[i].setEstadoOrdem("Total");
//                                    ordensVenda[i].setQuantidade(0);
//                                    
//                                    o.setOrdemCompra(ordensCompra[j]);
//                                    o.setOrdemVenda(ordensVenda[i]);
//                                    o.setQuantidade(ordensCompra[j].getQuantidade());
//                                    o.setContaCompra(ordensCompra[j].getConta());
//                                    o.setContaVenda(ordensVenda[i].getConta());
//                                    o.setDataCriacao(LocalDate.now());
//                                    o.setDataModificacao(LocalDate.now());
//
//                                    this.adicionaOrdemExecucao(o);
//                                    conta.transfere(conta, ordensCompra[j].getValorTotal(), ordensCompra[j].getConta().getId(), ordensVenda[i].getConta().getId());
//                                    movimenta.novaMovimentacao("Débito", "Negociação de Ativos", ordensCompra[j].getValorTotal(), LocalDate.now(), LocalDate.now(), ordensCompra[j].getConta()); // movimento conta compra
//                                    movimenta.novaMovimentacao("Débito", "Negociação de Ativos", ordensCompra[i].getValorTotal(), LocalDate.now(), LocalDate.now(), ordensVenda[i].getConta()); // movimento conta venda
//
//                                    ordens.removerPorId(ordensVenda[i].getId());
//                                    ordens.removerPorId(ordensCompra[j].getId()); 
//                  
//                                }else if(ordensVenda[i].getQuantidade() < ordensCompra[j].getQuantidade()){
//                                    OrdemExecucao o = new OrdemExecucao();
//                                    ordensCompra[j].setEstadoOrdem("Parcial");
//                                    ordensVenda[i].setEstadoOrdem("Total");
//
//                                    o.setOrdemCompra(ordensCompra[j]);
//                                    o.setOrdemVenda(ordensVenda[i]);
//                                    o.setQuantidade(ordensVenda[i].getQuantidade()); 
//                                    ordensVenda[i].setQuantidade(0);
//                                    o.setContaCompra(ordensCompra[j].getConta());
//                                    o.setContaVenda(ordensVenda[i].getConta());
//                                    o.setDataCriacao(LocalDate.now());
//                                    o.setDataModificacao(LocalDate.now());
//
//                                    this.adicionaOrdemExecucao(o);
//                                    
//                                    conta.transfere(conta, o.getQuantidade() * ordensCompra[j].getValor(), ordensCompra[j].getConta().getId(), ordensVenda[i].getConta().getId());
//                                    
//                                    movimenta.novaMovimentacao("Débito", "Negociação de Ativos", ordensCompra[j].getValorTotal(), LocalDate.now(), LocalDate.now(), ordensCompra[j].getConta()); // movimento conta compra
//                                    movimenta.novaMovimentacao("Débito", "Negociação de Ativos", ordensCompra[i].getValorTotal(), LocalDate.now(), LocalDate.now(), ordensVenda[i].getConta()); // movimento conta venda
//
//                                    
//                                    ordens.removerPorId(ordensVenda[i].getId());
//                                    ordens.removerPorId(ordensCompra[j].getId()); 
//
//                                }else if(ordensVenda[i].getQuantidade() > ordensCompra[j].getQuantidade()){
//                                    OrdemExecucao o = new OrdemExecucao();
//                                    ordensCompra[j].setEstadoOrdem("Total");
//                                    ordensVenda[i].setEstadoOrdem("Parcial");
//
//                                    o.setOrdemCompra(ordensCompra[j]);
//                                    o.setOrdemVenda(ordensVenda[i]);
//                                    o.setQuantidade(ordensCompra[j].getQuantidade());
//                                    ordensVenda[i].setQuantidade(ordensVenda[i].getQuantidade() - ordensCompra[j].getQuantidade());
//                                    o.setContaCompra(ordensCompra[j].getConta());
//                                    o.setContaVenda(ordensVenda[i].getConta());
//                                    o.setDataCriacao(LocalDate.now());
//                                    o.setDataModificacao(LocalDate.now());
//
//                                    this.adicionaOrdemExecucao(o);
//                                    
//                                    conta.transfere(conta, ordensCompra[j].getValorTotal(), ordensCompra[j].getConta().getId(), ordensVenda[i].getConta().getId());
//                                    
//                                    movimenta.novaMovimentacao("Débito", "Negociação de Ativos", ordensCompra[j].getValorTotal(), LocalDate.now(), LocalDate.now(), ordensCompra[j].getConta()); // movimento conta compra
//                                    movimenta.novaMovimentacao("Débito", "Negociação de Ativos", ordensCompra[i].getValorTotal(), LocalDate.now(), LocalDate.now(), ordensVenda[i].getConta()); // movimento conta venda
//
//                                    ordens.removerPorId(ordensVenda[i].getId());
//                                    ordens.removerPorId(ordensCompra[j].getId());
//                                }
//                            }
//                        }
//                        
//                    }
//                }
//                
//            }
//            
//        }
//    }
    
    public void executarOrdem(OrdemDAO ordens, ContaCorrenteDAO conta, MovimentaContaDAO movimenta) throws SQLException{

        List<Ordem> todas = ordens.lista(null);
        List<Ordem> ordensVenda = new ArrayList<>();
        List<Ordem> ordensCompra = new ArrayList<>();
        
        for(int i=0; i<todas.size(); i++){
            if(todas.get(i) != null){
                if(todas.get(i).getTipoOrdem().equals("Compra")){
                    ordensCompra.add(todas.get(i));
                }
            }
        }

         for(int i=0; i<todas.size(); i++){
             if(todas.get(i) != null){
                if(todas.get(i).getTipoOrdem().equals("Venda")){
                    ordensVenda.add(todas.get(i));
                }
             }
        }
        
        
        for(int i=0; i<ordensVenda.size(); i++){
            if(ordensVenda.get(i) != null){
                for(int j=0; j<ordensCompra.size(); j++){
                    if(ordensCompra.get(j) != null){
                        if(ordensVenda.get(i).getTicker().equals(ordensCompra.get(j).getTicker())){
                            if(ordensVenda.get(i).getValor()== ordensCompra.get(j).getValor()){
                                if(ordensVenda.get(i).getQuantidade() == 0){ 
                                    ordensCompra.get(j).setEstadoOrdem("Não");

                                }else if(ordensVenda.get(i).getQuantidade() == ordensCompra.get(j).getQuantidade()){
                                    OrdemExecucao o = new OrdemExecucao();
                                    
                                                
                                    ordensCompra.get(j).setEstadoOrdem("Total");
                                    ordensVenda.get(i).setEstadoOrdem("Total");  // qualquer coisa é só tirar isso
                                    ordensVenda.get(i).setQuantidade(0);
                                    
                                    o.setOrdemCompra(ordensCompra.get(j));
                                    o.setOrdemVenda(ordensVenda.get(i));
                                    o.setQuantidade(ordensCompra.get(j).getQuantidade());
                                    o.setContaCompra(ordensCompra.get(j).getConta());
                                    o.setContaVenda(ordensVenda.get(i).getConta());
                                    o.setDataCriacao(LocalDate.now());
                                    o.setDataModificacao(LocalDate.now());

                                    this.adicionaOrdemExecucao(o);
                                    conta.transfere(conta, ordensCompra.get(j).getValorTotal(), ordensCompra.get(j).getConta().getId(), ordensVenda.get(i).getConta().getId());
                                    movimenta.novaMovimentacao("Débito", "Negociação de Ativos", ordensCompra.get(j).getValorTotal(), LocalDate.now(), LocalDate.now(), ordensCompra.get(j).getConta().getId()); // movimento conta compra
                                    movimenta.novaMovimentacao("Débito", "Negociação de Ativos", ordensCompra.get(i).getValorTotal(), LocalDate.now(), LocalDate.now(), ordensVenda.get(i).getConta().getId()); // movimento conta venda

                                    ordens.exclui(ordensVenda.get(i).getId());
                                    ordens.exclui(ordensCompra.get(j).getId()); 
                  
                                }else if(ordensVenda.get(i).getQuantidade() < ordensCompra.get(j).getQuantidade()){
                                    OrdemExecucao o = new OrdemExecucao();
                                    ordensCompra.get(j).setEstadoOrdem("Parcial");
                                    ordensVenda.get(i).setEstadoOrdem("Total");

                                    o.setOrdemCompra(ordensCompra.get(j));
                                    o.setOrdemVenda(ordensVenda.get(i));
                                    o.setQuantidade(ordensVenda.get(i).getQuantidade()); 
                                    ordensVenda.get(i).setQuantidade(0);
                                    o.setContaCompra(ordensCompra.get(j).getConta());
                                    o.setContaVenda(ordensVenda.get(i).getConta());
                                    o.setDataCriacao(LocalDate.now());
                                    o.setDataModificacao(LocalDate.now());

                                    this.adicionaOrdemExecucao(o);
                                    
                                    conta.transfere(conta, o.getQuantidade() * ordensCompra.get(j).getValor(), ordensCompra.get(j).getConta().getId(), ordensVenda.get(i).getConta().getId());
                                    
                                    movimenta.novaMovimentacao("Débito", "Negociação de Ativos", ordensCompra.get(j).getValorTotal(), LocalDate.now(), LocalDate.now(), ordensCompra.get(j).getConta().getId()); // movimento conta compra
                                    movimenta.novaMovimentacao("Débito", "Negociação de Ativos", ordensCompra.get(i).getValorTotal(), LocalDate.now(), LocalDate.now(), ordensVenda.get(i).getConta().getId()); // movimento conta venda

                                    
                                    ordens.exclui(ordensVenda.get(i).getId());
                                    ordens.exclui(ordensCompra.get(j).getId()); 

                                }else if(ordensVenda.get(i).getQuantidade() > ordensCompra.get(j).getQuantidade()){
                                    OrdemExecucao o = new OrdemExecucao();
                                    ordensCompra.get(j).setEstadoOrdem("Total");
                                    ordensVenda.get(i).setEstadoOrdem("Parcial");

                                    o.setOrdemCompra(ordensCompra.get(j));
                                    o.setOrdemVenda(ordensVenda.get(i));
                                    o.setQuantidade(ordensCompra.get(j).getQuantidade());
                                    ordensVenda.get(i).setQuantidade(ordensVenda.get(i).getQuantidade() - ordensCompra.get(j).getQuantidade());
                                    o.setContaCompra(ordensCompra.get(j).getConta());
                                    o.setContaVenda(ordensVenda.get(i).getConta());
                                    o.setDataCriacao(LocalDate.now());
                                    o.setDataModificacao(LocalDate.now());

                                    this.adicionaOrdemExecucao(o);
                                    
                                    conta.transfere(conta, ordensCompra.get(j).getValorTotal(), ordensCompra.get(j).getConta().getId(), ordensVenda.get(i).getConta().getId());
                                    
                                    movimenta.novaMovimentacao("Débito", "Negociação de Ativos", ordensCompra.get(j).getValorTotal(), LocalDate.now(), LocalDate.now(), ordensCompra.get(j).getConta().getId()); // movimento conta compra
                                    movimenta.novaMovimentacao("Débito", "Negociação de Ativos", ordensCompra.get(i).getValorTotal(), LocalDate.now(), LocalDate.now(), ordensVenda.get(i).getConta().getId()); // movimento conta venda

                                    ordens.exclui(ordensVenda.get(i).getId());
                                    ordens.exclui(ordensCompra.get(j).getId());
                                }
                            }
                        }
                        
                    }
                }
                
            }
            
        }
    }
    
    
    public OrdemExecucao adicionaOrdemExecucao(OrdemExecucao elemento) {
        String sql = "insert into ordem_exec "
                + "(qtde_ordem_exec,data_criacao_ordem_exec,data_mod_ordem_exec, ordem_exec_ordemc, ordem_exec_ordemv,"
                + "ordem_exec_ccorrentec, ordem_exec_ccorrentev)" + " values (?,?,?,?,?,?,?)";

        try ( Connection connection = new ConnectionFactory().getConnection();  PreparedStatement stmt = connection.prepareStatement(sql)) {
            // seta os valores
            stmt.setInt(1, elemento.getQuantidade());
            stmt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setLong(4, elemento.getOrdemCompra().getId());
            stmt.setLong(5, elemento.getOrdemVenda().getId());
            stmt.setLong(6, elemento.getContaCompra().getId());
            stmt.setLong(7, elemento.getContaVenda().getId());
            stmt.execute();

            System.out.println("Elemento inserido com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return elemento;
    }
    
    public List<OrdemExecucao> lista(OrdemExecucao elemento) {
        String sql = "select * from ordem_exec";
        ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO();
        OrdemDAO ordemDAO = new OrdemDAO();
        List<OrdemExecucao> ordens = new ArrayList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Long id = rs.getLong("idordem_exec");
                int quantidade = rs.getInt("qtde_ordem_exec");
                Date currenteDate = rs.getDate("data_criacao_ordem_exec");
                LocalDate dataCriacao = currenteDate.toLocalDate();
                Date currenteMod = rs.getDate("data_mod_ordem_exec");
                LocalDate dataModificacao = currenteMod.toLocalDate();
                long idOrdemCompra = rs.getLong("ordem_exec_ordemc");
                long idOrdemVenda = rs.getLong("ordem_exec_ordemv");
                long idContaCompra = rs.getLong("ordem_ccorrentec");
                long idContaVenda = rs.getLong("ordem_ccorrentev");

                
                ContaCorrente contaCompra = contaCorrenteDAO.buscaPorId(idContaCompra);
                ContaCorrente contaVenda = contaCorrenteDAO.buscaPorId(idContaVenda);
                Ordem ordemCompra = ordemDAO.buscaPorID(idOrdemCompra);
                Ordem ordemVenda = ordemDAO.buscaPorID(idOrdemVenda);
                

                OrdemExecucao ordem = new OrdemExecucao();
                ordem.setId(id); 
                ordem.setQuantidade(quantidade);
                ordem.setDataCriacao(dataCriacao);
                ordem.setDataModificacao(dataModificacao);
                ordem.setContaCompra(contaCompra);
                ordem.setContaVenda(contaVenda);
                ordem.setOrdemCompra(ordemCompra);
                ordem.setOrdemVenda(ordemVenda);
                ordens.add(ordem);
            }
        } catch (SQLException e) {
             throw new RuntimeException(e);
        }

        // itera no ResultSet
        return ordens;
    }
    
    public void mostrarTodos() {
        List<OrdemExecucao> ordens = null;
        ordens = this.lista(null);
        StringBuilder builder = new StringBuilder("");
        
        for (OrdemExecucao o : ordens) {
             builder.append(o.toString());            
        }      
       
        JOptionPane.showMessageDialog(null,builder.toString(),"Ordens",JOptionPane.INFORMATION_MESSAGE);
        
    }
    
    public void mostraUltimaNegociacao(){
        StringBuilder builder = new StringBuilder("");
        try{
            List<OrdemExecucao> ordens = this.lista(null);
            String ativo = ordens.get(-1).getOrdemCompra().getTicker().getTicker(); // o primeiro ticker retorna o ativo
            double valor = ordens.get(-1).getOrdemCompra().getValor();

            builder.append("\nAtivo: ").append(ativo).append("  Valor: ").append(valor);
            //builder.append("\nValor: ").append(valor);

            JOptionPane.showMessageDialog(null,builder.toString(),"Última Negociação",JOptionPane.INFORMATION_MESSAGE);
        }catch(IndexOutOfBoundsException erro){
            JOptionPane.showMessageDialog(null,"Nenhuma negociação foi realizada até o momento","Erro",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
}
