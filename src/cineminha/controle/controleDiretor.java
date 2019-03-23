/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cineminha.controle;

import cineminha.dao.daoDiretor;
import cineminha.model.Diretor;
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
public class controleDiretor {
    cineminha.dao.daoDiretor DAODiretor = new daoDiretor();

    
    public void cadastrar(Diretor o) throws SQLException {
        DAODiretor.cadastrar(o);
        
    }
    
    public void excluir (Long o) throws SQLException {
        DAODiretor.excluir(o);
    
    }
    
    public void alterar(Diretor o) throws SQLException {
        DAODiretor.alterar(o);
    
    }
     
    public List atualizarLista() throws SQLException{
        return DAODiretor.listarTodos();
    
    }
    
    public void gerarPDF(String filename)
	throws DocumentException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cineminha_v2");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Diretor> consulta = em.createQuery("Select c from Diretor c",Diretor.class);
        List<Diretor> diretores = consulta.getResultList();
        Document document = new Document();        
        PdfWriter.getInstance(document, new FileOutputStream(filename));        
        document.open();        
        document.add(new Paragraph("Número de diretores cadastrados: "+diretores.size()));   
        document.add(new Paragraph("-------------------------------------------------------------"));
        for(Diretor c : diretores){
            document.add(new Paragraph("Nome: " + c.getNome()));
            document.add(new Paragraph("Nacionalidade: " + c.getPais()));
            document.add(new Paragraph("Data de Nascimento: " + c.getDataNascimento()));
            document.add(new Paragraph("-------------------------------------------------------------"));
        }
        document.close();
    }
    
}
