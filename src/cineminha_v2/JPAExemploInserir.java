/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cineminha_v2;

import cineminha.model.Ator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author patri
 */
public class JPAExemploInserir {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cineminha_v2");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        Ator p = new Ator();
        p.setNome("Arroz");
        p.setPais("Tipo 2");
        em.persist(p);
        em.getTransaction().commit();
        em.close();
        
        
        
        emf.close();
        
        
        
        
        
        
    }
    
    
}
