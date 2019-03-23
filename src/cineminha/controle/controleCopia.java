/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cineminha.controle;

import cineminha.dao.daoCopia;
import cineminha.model.Copia;
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
public class controleCopia {
    cineminha.dao.daoCopia DAOCopia = new daoCopia();
    
    public void cadastrar(Copia o) throws SQLException {
        DAOCopia.cadastrar(o);
        
    }
    
    public void excluir (Long o) throws SQLException {
        DAOCopia.excluir(o);
    
    }
    
    public void alterar(Copia o) throws SQLException {
        DAOCopia.alterar(o);
    
    }
     
    public List atualizarLista() throws SQLException{
        return DAOCopia.listarTodos();
    
    }
    
    public void gerarPDF(String filename)
	throws DocumentException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cineminha_v2");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Copia> consulta = em.createQuery("Select c from Copia c",Copia.class);
        List<Copia> copias = consulta.getResultList();
        Document document = new Document();        
        PdfWriter.getInstance(document, new FileOutputStream(filename));        
        document.open();        
        document.add(new Paragraph("Número de copias cadastrados: "+copias.size()));   
        document.add(new Paragraph("-------------------------------------------------------------"));
        for(Copia c : copias){
            document.add(new Paragraph("Codigo da Copia: " + c.getCodigoCopia()));
            document.add(new Paragraph("Estado da Copia: " + c.getEstadoFita()));
            document.add(new Paragraph("Filme: " + c.getFilme().getTitulo()));
            document.add(new Paragraph("-------------------------------------------------------------"));
        }
        document.close();
    }
    
}
