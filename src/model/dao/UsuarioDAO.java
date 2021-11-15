/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Usuario;

/**
 *
 * @author kazuo
 */
public class UsuarioDAO {
    
    public boolean create(Usuario t){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO usuario (privilegio, nome, biometria) VALUES (?,?,?)");
            stmt.setInt(1, t.getPrivilegio());
            stmt.setString(2, t.getNome());
            stmt.setBytes(3, t.getFoto());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao salvar: " + ex);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }
    public static void main(String[] args){
        Usuario usu = new Usuario(1, "Gustavo",null);
        UsuarioDAO usuDAO = new UsuarioDAO();
        if(usuDAO.create(usu)){
            System.out.println("Salvo com sucesso");
        }else{
            System.err.println("Erro ao salvar");
        }
        
    }
}
