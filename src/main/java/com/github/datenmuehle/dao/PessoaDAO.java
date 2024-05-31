package com.github.datenmuehle.dao;

import com.github.datenmuehle.model.Endereco;
import com.github.datenmuehle.model.Pessoa;
import com.github.datenmuehle.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


public class PessoaDAO {
    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

    public void save(Pessoa pessoa) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(pessoa);
        entityManager.flush(); // Assegura que a pessoa é salva no banco antes de prosseguir
        entityManager.getTransaction().commit();
        JPAUtil.shotdown();
    }

    public void update(Pessoa pessoa) {
        entityManager.getTransaction().begin();
        entityManager.merge(pessoa);
        entityManager.getTransaction().commit();
        JPAUtil.shotdown();
    }

    public Pessoa findById(Long id) {
        Pessoa pessoa = entityManager.find(Pessoa.class, id);
        JPAUtil.shotdown();
        return pessoa;
    }

    public List<Pessoa> findAll() {
        List<Pessoa> pessoaList = new ArrayList<>();
        Query query = entityManager.createQuery("select p from Pessoa p");
        pessoaList = query.getResultList();
        return pessoaList;
    }

    public void delete(Pessoa pessoa) {
        entityManager.getTransaction().begin();
        entityManager.remove(pessoa);
        entityManager.getTransaction().commit();
        JPAUtil.shotdown();
    }
}
