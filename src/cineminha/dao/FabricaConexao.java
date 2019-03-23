/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cineminha.dao;
import java.sql.SQLException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FabricaConexao {
    private static EntityManagerFactory emf;
    public static EntityManagerFactory getConexao(){
        if(emf==null){
            emf=Persistence.createEntityManagerFactory("Cineminha_v2");
        }
        return emf;
    }
}
