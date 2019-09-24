/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alunoprofessor;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author Felipe
 */
public class EntradaDeDados {

    DAO dao = new DAO();

    public void entradaPessoa(String opcao) throws SQLException { 
        if (opcao.equals("1")) {
            String nome = JOptionPane.showInputDialog("Nome: ");
            int idade = Integer.parseInt(JOptionPane.showInputDialog("Idade: "));
            long cpf = Long.parseLong(JOptionPane.showInputDialog("CPF: "));
            String sexo = JOptionPane.showInputDialog("Sexo: ");
            int ra = Integer.parseInt(JOptionPane.showInputDialog("RA: "));
            
            Aluno aluno = new Aluno(nome,idade,cpf,sexo,ra);
            dao.addAluno(aluno);
        } else {
            String nome = JOptionPane.showInputDialog("Nome: ");
            int idade = Integer.parseInt(JOptionPane.showInputDialog("Idade: "));
            long cpf = Long.parseLong(JOptionPane.showInputDialog("CPF: "));
            String sexo = JOptionPane.showInputDialog("Sexo: ");
            long siape  = Long.parseLong(JOptionPane.showInputDialog("SIAPE: "));
            Professor professor = new Professor(nome, idade, cpf, sexo,siape);
           // dao.addProfessor(professor);
        }
    }
    
    public void exibirTodos(String opcao) throws SQLException{
        ArrayList<Aluno> listaAlunos = new ArrayList();
        String msg = "";
      
        listaAlunos =dao.listarAluno();
        //listaProfessores = dao.listaProfessor();
        if (opcao.equals("2")) {
            for (int i = 0; i < listaAlunos.size(); i++) {
                    Aluno aluno = listaAlunos.get(i);
                    msg += aluno.getNome() + " - RA: " + aluno.getRa() + "CPF: "+aluno.getCpf()+"\n";
                
            }
        } else {
            //for (int i = 0; i < listaProfessores.size(); i++) {
                
              //      Professor professor = (Professor) listaProfessores.get(i);
                //    msg += professor.getNome() + " - SIAPE: " + professor.getSiape()"CPF: "+professor.getCpf()+"\n";
            }
        
        JOptionPane.showMessageDialog(null, msg);
    }    
    
    public void remover(String opcao) throws SQLException{
        String cpf = JOptionPane.showInputDialog("Entre com o cpf de quem deseja remover: ");
        long c = Long.parseLong(cpf);
        dao.removeAluno(c);
    }
}