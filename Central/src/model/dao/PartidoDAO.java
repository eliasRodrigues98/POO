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
import model.bean.Partido;

/**
 * CRUD
 * @author elias
 */
public class PartidoDAO {
    
    private Connection con = null;

    public PartidoDAO() {
        con = ConnectionFactory.getConnection();
    }
    
    public boolean save(Partido partido) {
        String sql = "INSERT INTO partido (nome, numero, sigla) VALUES (?, ?, ?, ?)";
        
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, partido.getNome());
            stmt.setInt(2, partido.getNumero());
            stmt.setString(3, partido.getSigla());
            stmt.executeUpdate();
            return true;
            
        } catch (SQLException ex) {
            System.err.println("ERRO! " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    
}
