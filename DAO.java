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
public class DAO {
    
    
    public void addAluno(Aluno aluno) throws SQLException{
        Connection conecta = null;
        String sql = "insert into aluno (nome,idade,cpf,sexo,ra) " +
                    "values(?,?,?,?,?)";
        try {
            conecta = DriverManager.getConnection("jdbc:derby://localhost/alunoProfessor", "root", "");
            
            PreparedStatement ps = conecta.prepareStatement(sql);
            ps.setString(1, aluno.getNome());
            ps.setInt(2, aluno.getIdade());
            ps.setLong(3, aluno.getCpf());
            ps.setString(4, aluno.getSexo());
            ps.setInt(5, aluno.getRa());
            ps.execute();
       
            JOptionPane.showMessageDialog(null, "Adicionado com Sucesso !");
               
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally{
            conecta.close();
        }
    }
    public void atualizaAluno(Aluno aluno) throws SQLException{
        String sql =  "update aluno set nome = ?,idade = ?,cpf = ?,sexo = ? where ra= ?";
        
        Connection conecta = null;
        try {
                conecta = DriverManager.getConnection("jdbc:derby://localhost/alunoProfessor", "root", "");
                PreparedStatement ps = conecta.prepareStatement(sql);
                ps.setString(1, aluno.getNome());
                ps.setInt(2, aluno.getIdade());
                ps.setLong(3, aluno.getCpf());
                ps.setString(4, aluno.getSexo());
                ps.setInt(5, aluno.getRa());
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
      
    public ArrayList<Aluno> listarAluno() throws SQLException{
        ArrayList<Aluno> listaAlunos = new ArrayList();
        String sql = "Select* From aluno";
        Connection conecta = null;
        ResultSet rs = null;
        Statement st = null;
        try {
               conecta = DriverManager.getConnection("jdbc:derby://localhost/alunoProfessor", "root", "");
               st = conecta.createStatement();
               rs = st.executeQuery(sql);
               while (rs.next()){
                   Aluno aluno = new Aluno();
                   aluno.setNome(rs.getString("nome"));
                   aluno.setIdade(rs.getInt("idade"));
                   aluno.setCpf(rs.getLong("cpf"));
                   aluno.setSexo(rs.getString("sexo"));
                   aluno.setRa(rs.getInt("ra"));
                   listaAlunos.add(aluno);
               }
 
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally{
            conecta.close();
        }
        return listaAlunos;
        
    }
    public Aluno buscaAluno(long cpf) throws SQLException {
        Aluno aluno = new Aluno();
        Connection conecta = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM aluno WHERE cpf = " + cpf;
        try {
            conecta = DriverManager.getConnection("jdbc:derby://localhost/alunoProfessor", "root", "");
            st = conecta.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {
                aluno.setNome(rs.getString("nome"));
                aluno.setIdade(rs.getInt("idade"));
                aluno.setCpf(rs.getLong("cpf"));
                aluno.setSexo(rs.getString("sexo"));
                aluno.setRa(rs.getInt("ra"));
            }
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } finally{
            conecta.close();
        }
        return aluno;
    }
       public void removeAluno(long cpf) throws SQLException{
        Connection conecta = null;  
        String sql = "delete from aluno where cpf = ?";
        
        Aluno aluno = buscaAluno(cpf);
        try {
                conecta = DriverManager.getConnection("jdbc:derby://localhost/alunoProfessor", "root", "");
                PreparedStatement ps = conecta.prepareStatement(sql);
                ps.setLong(1, aluno.getCpf());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Deletado com Sucesso !");
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } finally{
                conecta.close();
            }
       }
    }
