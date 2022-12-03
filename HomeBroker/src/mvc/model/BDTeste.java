/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Stheffany
 */
public class BDTeste {
       
    public static void main(String[] args) throws SQLException {
         Connection conexao = new ConnectionFactory().getConnection();
         System.out.println("Conectado!");
         conexao.close();
    }
}
