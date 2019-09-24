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
public class Professor extends Pessoa{
    private long siape;

    public Professor(String nome,int idade,long cpf,String sexo,long siape){
        super.setNome(nome);
        super.setIdade(idade);   
        super.setCpf(cpf);
        super.setSexo(sexo);
        this.siape = siape;
    }
    
    public long getSiape() {
        return siape;
    }

    public void setSiape(long siape) {
        this.siape = siape;
    }
}
