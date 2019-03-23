/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cineminha.controle;

import cineminha.dao.daoFilme;
import cineminha.model.Filme;
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
public class controleFilme {
    private cineminha.dao.daoFilme DAOFilme = new daoFilme();
    
    public void cadastrar(Filme o) throws SQLException {
        DAOFilme.cadastrar(o);
        
    }
    
    public void excluir (Long o) throws SQLException {
        DAOFilme.excluir(o);
    
    }
    
    public void alterar(Filme o) throws SQLException {
        DAOFilme.alterar(o);
    
    }
     
    public List atualizarLista() throws SQLException{
        return DAOFilme.listarTodos();
    
    }
    
    public void gerarPDF(String filename)
	throws DocumentException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cineminha_v2");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Filme> consulta = em.createQuery("Select c from Filme c",Filme.class);
        List<Filme> filmes = consulta.getResultList();
        Document document = new Document();        
        PdfWriter.getInstance(document, new FileOutputStream(filename));        
        document.open();        
        document.add(new Paragraph("Número de filmes cadastrados: "+filmes.size()));   
        document.add(new Paragraph("-------------------------------------------------------------"));
        for(Filme c : filmes){
            document.add(new Paragraph("Titulo do Filme: " + c.getTitulo()));
            document.add(new Paragraph("Ano do Filme: " + c.getAno()));
            document.add(new Paragraph("Diretor do Filme: " + c.getDiretor().getNome()));
            document.add(new Paragraph("Roteirista do Filme: " + c.getRoteirista().getNome()));
            document.add(new Paragraph("-------------------------------------------------------------"));
        }
        document.close();
    }
    
}
