/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alunoprofessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
/**
 *
 * @author Felipe
 */
public class ProfessorDAO {
    
    
    public void AddProfessor(Professor professor) throws SQLException{
        Connection conecta = null;
        String sql = "insert into professor (nome,idade,cpf,sexo,siape) " +
                    "values(?,?,?,?,?)";
        try {
            conecta = DriverManager.getConnection("jdbc:derby://localhost/alunoProfessor", "root", "");
            
            PreparedStatement ps = conecta.prepareStatement(sql);
            ps.setString(1, professor.getNome());
            ps.setInt(2, professor.getIdade());
            ps.setLong(3, professor.getCpf());
            ps.setString(4, professor.getSexo());
            ps.setLong(5, professor.getSiape());
            ps.execute();
       
            JOptionPane.showMessageDialog(null, "Adicionado com Sucesso !");
               
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally{
            conecta.close();
        }
    }
    public void EditaProfessor(Professor professor) throws SQLException{
        String sql =  "update professor set nome = ?,idade = ?,cpf = ?,sexo = ? where siape= ?";
        
        Connection conecta = null;
        try {
                conecta = DriverManager.getConnection("jdbc:derby://localhost/alunoProfessor", "root", "");
                PreparedStatement ps = conecta.prepareStatement(sql);
                ps.setString(1, professor.getNome());
                ps.setInt(2, professor.getIdade());
                ps.setLong(3, professor.getCpf());
                ps.setString(4, professor.getSexo());
                ps.setLong(5, professor.getSiape());
                int retorno = ps.executeUpdate();
                if (retorno > 0) {
                    JOptionPane.showMessageDialog(null, "Alterado com Sucesso !");
                }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally{
            conecta.close();
        }
     
    }
      
    public ArrayList<Professor> ListarProfessor() throws SQLException{
        ArrayList<Professor> listaProfessores = new ArrayList();
        String sql = "Select* From professor";
        Connection conecta = null;
        ResultSet rs = null;
        Statement st = null;
        try {
               conecta = DriverManager.getConnection("jdbc:derby://localhost/alunoProfessor", "root", "");
               st = conecta.createStatement();
               rs = st.executeQuery(sql);
               while (rs.next()){
                   Professor professor = new Professor();
                   professor.setNome(rs.getString("nome"));
                   professor.setIdade(rs.getInt("idade"));
                   professor.setCpf(rs.getLong("cpf"));
                   professor.setSexo(rs.getString("sexo"));
                   professor.setSiape(rs.getLong("siape"));
                   listaProfessores.add(professor);
               }
 
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally{
            conecta.close();
        }
        return listaProfessores;
        
    }
    public Professor BuscaProfessor(long cpf) throws SQLException {
        Professor professor = new Professor();
        Connection conecta = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM professor WHERE cpf= " + cpf;
        try {
            conecta = DriverManager.getConnection("jdbc:derby://localhost/alunoProfessor", "root", "");
            st = conecta.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {
                professor.setNome(rs.getString("nome"));
                professor.setIdade(rs.getInt("idade"));
                professor.setCpf(rs.getLong("cpf"));
                professor.setSexo(rs.getString("sexo"));
                professor.setSiape(rs.getLong("siape"));
            }
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally{
            conecta.close();
        }
        return professor;
    }
       public void RemoveProfessor(long cpf) throws SQLException{
        Connection conecta = null;  
        String sql = "delete from professor where cpf = ?";
        
        Professor professor = BuscaProfessor(cpf);
        try {
                conecta = DriverManager.getConnection("jdbc:derby://localhost/alunoProfessor", "root", "");
                PreparedStatement ps = conecta.prepareStatement(sql);
                ps.setLong(1, professor.getCpf());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Deletado com Sucesso !");
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } finally{
                conecta.close();
            }
       }
    }
