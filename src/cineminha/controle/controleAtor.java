/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cineminha.controle;

import cineminha.dao.daoAtor;
import cineminha.model.Ator;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
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
public class controleAtor {
    cineminha.dao.daoAtor DAOAtor = new daoAtor();
    
    public void cadastrar(Ator o) throws SQLException {
        DAOAtor.cadastrar(o);
        
    }
    
    public void excluir (Long o) throws SQLException {
        DAOAtor.excluir(o);
    
    }
    
    public void alterar(Ator o) throws SQLException {
        DAOAtor.alterar(o);
    
    }
     
    public List atualizarLista() throws SQLException{
        return DAOAtor.listarTodos();
    
    }
    
    public void gerarPDF(String filename)
	throws DocumentException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cineminha_v2");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Ator> consulta = em.createQuery("Select c from Ator c",Ator.class);
        List<Ator> atores = consulta.getResultList();
        Document document = new Document();        
        PdfWriter.getInstance(document, new FileOutputStream(filename));        
        document.open();        
        document.add(new Paragraph("Número de atores cadastrados: "+atores.size()));   
        document.add(new Paragraph("-------------------------------------------------------------"));
        /*for(Ator c : atores){
            document.add(new Paragraph("Nome: " + c.getNome() + "       |       Nacionalidade: " + c.getPais()+ "       |       Data de Nascimento: " + c.getDataNascimento()));
            document.add(new Paragraph("-------------------------------------------------------------"));
        } */               
        for(Ator c : atores){
            document.add(new Paragraph("Nome: " + c.getNome()));
            document.add(new Paragraph("Nacionalidade: " + c.getPais()));
            document.add(new Paragraph("Data de Nascimento: " + c.getDataNascimento()));
            document.add(new Paragraph("-------------------------------------------------------------"));
        }
        document.close();
    }
    
}
