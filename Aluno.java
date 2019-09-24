/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alunoprofessor;

/**
 *
 * @author Felipe
 */
public class Aluno extends Pessoa{
    private int ra;

    public Aluno(String nome,int idade,long cpf,String sexo,int ra){
        super.setNome(nome);
        super.setIdade(idade);
        super.setCpf(cpf);
        super.setSexo(sexo); 
        this.ra = ra ;
    }

    Aluno() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getRa() {
        return ra;
    }

    public void setRa(int ra) {
        this.ra = ra;
    }
    
    
}
