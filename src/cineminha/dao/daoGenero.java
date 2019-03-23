/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cineminha.dao;

import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import cineminha.model.Genero;

/**
 *
 * @author patri
 */
public class daoGenero implements IDAO {
    
    @Override
    public void cadastrar(Object o) throws SQLException {
        Genero a = (Genero) o;
        EntityManager em = FabricaConexao.getConexao().createEntityManager();
        em.getTransaction().begin();
        em.persist(a);
        em.getTransaction().commit();
        em.close();
        
    }

    @Override
    public void alterar(Object o) throws SQLException {
        Genero a = (Genero) o;
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
        Genero a = em.find(Genero.class, o);
        em.remove(a);
        em.getTransaction().commit();
        em.close();
        
        
    }

    @Override
    public List listarTodos() throws SQLException {
        EntityManager em = FabricaConexao.getConexao().createEntityManager();
        
        TypedQuery<Genero> query = em.createQuery("SELECT p FROM Genero p", Genero.class);
        List<Genero> lista = query.getResultList();
        em.close();
        
        return lista;
        
    }
            
}