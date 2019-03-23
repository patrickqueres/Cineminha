/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cineminha.controle;

import cineminha.dao.daoGenero;
import java.sql.SQLException;
import java.util.List;
import cineminha.model.Genero;

/**
 *
 * @author patri
 */
public class controleGenero {
    cineminha.dao.daoGenero GeneroDAO = new daoGenero();
    
    
    public void cadastrar(Genero o) throws SQLException {
        GeneroDAO.cadastrar(o);
        
    }
    
    public void excluir (Long o) throws SQLException {
        GeneroDAO.excluir(o);
    
    }
    
    public void alterar(Genero o) throws SQLException {
        GeneroDAO.alterar(o);
   
    }
     
    public List atualizarLista() throws SQLException{
        return GeneroDAO.listarTodos();
        
    }
    
}
