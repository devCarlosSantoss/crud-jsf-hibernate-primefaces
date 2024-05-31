package com.github.datenmuehle.bean;

import com.github.datenmuehle.dao.PessoaDAO;
import com.github.datenmuehle.model.Pessoa;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;


import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "pessoaBean")
@RequestScoped
public class PessoaBean implements Serializable {
    private Pessoa pessoa = new Pessoa();

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Pessoa> getPessoas() {
        PessoaDAO pessoaDAO = new PessoaDAO();
        return pessoaDAO.findAll();
    }

    public void salvarPessoa() {
        try {
            PessoaDAO pessoaDAO = new PessoaDAO();
            pessoaDAO.save(pessoa);
            pessoa = new Pessoa();
        } catch (Exception e) {
            e.printStackTrace(); // Imprime o stack trace para depuração
        }
    }




/*    public List<Pessoa> getPessoas(){
        List<Pessoa> pessoas = new ArrayList<>();
        Pessoa p1 = new Pessoa();
        p1.setId(1L);
        p1.setNome("Carlos Vinicius");
        Calendar calendar = Calendar.getInstance();
        calendar.set(1996, Calendar.MARCH, 13);
        p1.setDataNascimento(calendar.getTime());
        p1.setSexo("M");
        p1.setEndereco(new Endereco());
        pessoas.add(p1);
        return pessoas;
    } */
}
