/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cineminha.controle;

import cineminha.dao.daoPessoa;
import cineminha.model.Pessoa;
import cineminha.model.Pessoa;
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
public class controlePessoa {
    cineminha.dao.daoPessoa DAOPessoa = new daoPessoa();

    
    public void cadastrar(Pessoa o) throws SQLException {
        DAOPessoa.cadastrar(o);
        
    }
    
    public void excluir (Long o) throws SQLException {
        DAOPessoa.excluir(o);
    
    }
    
    public void alterar(Pessoa o) throws SQLException {
        DAOPessoa.alterar(o);
    
    }
     
    public List atualizarLista() throws SQLException{
        return DAOPessoa.listarTodos();
    
    }
    
    public void gerarPDF(String filename)
	throws DocumentException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cineminha_v2");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Pessoa> consulta = em.createQuery("Select c from Pessoa c",Pessoa.class);
        List<Pessoa> pessoas = consulta.getResultList();
        Document document = new Document();        
        PdfWriter.getInstance(document, new FileOutputStream(filename));        
        document.open();        
        document.add(new Paragraph("Número de pessoas cadastrados: "+pessoas.size()));   
        document.add(new Paragraph("-------------------------------------------------------------"));
        for(Pessoa c : pessoas){
            document.add(new Paragraph("Nome: " + c.getNome()));
            document.add(new Paragraph("Telefone: " + c.getTelefone()));
            document.add(new Paragraph("Email: " + c.getEmail()));
            document.add(new Paragraph("-------------------------------------------------------------"));
        }
        document.close();
    }    
    
}
