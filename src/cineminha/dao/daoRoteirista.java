/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cineminha.dao;

import cineminha.model.Roteirista;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author patri
 */
public class daoRoteirista implements IDAO{

    @Override
    public void cadastrar(Object o) throws SQLException {
        Roteirista a = (Roteirista) o;
        EntityManager em = FabricaConexao.getConexao().createEntityManager();
        em.getTransaction().begin();
        em.persist(a);
        em.getTransaction().commit();
        em.close();
        
    }

    @Override
    public void alterar(Object o) throws SQLException {
        Roteirista a = (Roteirista) o;
        EntityManager em = FabricaConexao.getConexao().createEntityManager();
        em.getTransaction().begin();
        em.merge(a);
        em.getTransaction().commit();
        em.close();
        
    }

    @Override
    public void excluir(Long o) throws SQLException {
        EntityManager em = FabricaConexao.getConexao().createEntityManager();
        em.getTransaction().begin();
        Roteirista a = em.find(Roteirista.class, o);
        em.remove(a);
        em.getTransaction().commit();
        em.close();
        
        
    }

    @Override
    public List listarTodos() throws SQLException {
        EntityManager em = FabricaConexao.getConexao().createEntityManager();
        
        TypedQuery<Roteirista> query = em.createQuery("SELECT p FROM Roteirista p", Roteirista.class);
        List<Roteirista> lista = query.getResultList();
        em.close();
        
        return lista;
        
    }
            
}