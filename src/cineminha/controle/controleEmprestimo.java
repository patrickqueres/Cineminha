/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cineminha.controle;

import cineminha.dao.daoEmprestimo;
import cineminha.model.Emprestimo;
import cineminha.model.Emprestimo;
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
public class controleEmprestimo {
    cineminha.dao.daoEmprestimo DAOEmprestimo = new daoEmprestimo();
    
    public void cadastrar(Emprestimo o) throws SQLException {
        DAOEmprestimo.cadastrar(o);
        
    }
    
    public void excluir (Long o) throws SQLException {
        DAOEmprestimo.excluir(o);
    
    }
    
    public void alterar(Emprestimo o) throws SQLException {
        DAOEmprestimo.alterar(o);
    
    }
     
    public List atualizarLista() throws SQLException{
        return DAOEmprestimo.listarTodos();
    
    }
    
    public void gerarPDFnaodevolvido(String filename)
	throws DocumentException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cineminha_v2");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Emprestimo> consulta = em.createQuery("Select c from Emprestimo c where c.devolvido=0",Emprestimo.class);
        List<Emprestimo> emprestimos = consulta.getResultList();
        Document document = new Document();        
        PdfWriter.getInstance(document, new FileOutputStream(filename));        
        document.open();        
        document.add(new Paragraph("Número de emprestimos não devolvidos: "+emprestimos.size()));   
        document.add(new Paragraph("-------------------------------------------------------------"));
        /*for(Emprestimo c : emprestimos){
            document.add(new Paragraph("Nome: " + c.getNome() + "       |       Nacionalidade: " + c.getPais()+ "       |       Data de Nascimento: " + c.getDataNascimento()));
            document.add(new Paragraph("-------------------------------------------------------------"));
        } */               
        for(Emprestimo c : emprestimos){
            document.add(new Paragraph("Filme: " + c.getCopia().getFilme().getTitulo()));
            document.add(new Paragraph("Copia: " + c.getCopia().getCodigoCopia()));
            document.add(new Paragraph("Data de Emprestimo: " + c.getDataEmprestimo()));
            document.add(new Paragraph("Nome da pessoa: " + c.getPessoa().getNome()));
            document.add(new Paragraph("Telefone: " + c.getPessoa().getTelefone()));
            document.add(new Paragraph("-------------------------------------------------------------"));
        }
        document.close();
    }
    
        public void gerarPDFdevolvido(String filename)
            throws DocumentException, IOException {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cineminha_v2");
            EntityManager em = emf.createEntityManager();
            TypedQuery<Emprestimo> consulta = em.createQuery("Select c from Emprestimo c where c.devolvido=1",Emprestimo.class);
            List<Emprestimo> emprestimos = consulta.getResultList();
            Document document = new Document();        
            PdfWriter.getInstance(document, new FileOutputStream(filename));        
            document.open();        
            document.add(new Paragraph("Número de emprestimos devolvidos: "+emprestimos.size()));   
            document.add(new Paragraph("-------------------------------------------------------------"));
            /*for(Emprestimo c : emprestimos){
                document.add(new Paragraph("Nome: " + c.getNome() + "       |       Nacionalidade: " + c.getPais()+ "       |       Data de Nascimento: " + c.getDataNascimento()));
                document.add(new Paragraph("-------------------------------------------------------------"));
            } */               
            for(Emprestimo c : emprestimos){
                document.add(new Paragraph("Filme: " + c.getCopia().getFilme().getTitulo()));
                document.add(new Paragraph("Copia: " + c.getCopia().getCodigoCopia()));
                document.add(new Paragraph("Data de Emprestimo: " + c.getDataEmprestimo()));
                document.add(new Paragraph("Data da Devolução: " + c.getDataDevolucao()));
                document.add(new Paragraph("Nome da pessoa: " + c.getPessoa().getNome()));
                document.add(new Paragraph("Telefone: " + c.getPessoa().getTelefone()));
                document.add(new Paragraph("-------------------------------------------------------------"));
            }
            document.close();
    }
        
        
        public void gerarPDF(String filename)
            throws DocumentException, IOException {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cineminha_v2");
            EntityManager em = emf.createEntityManager();
            TypedQuery<Emprestimo> consulta = em.createQuery("Select c from Emprestimo c",Emprestimo.class);
            List<Emprestimo> emprestimos = consulta.getResultList();
            Document document = new Document();        
            PdfWriter.getInstance(document, new FileOutputStream(filename));        
            document.open();        
            document.add(new Paragraph("Número de emprestimos cadastrados: "+emprestimos.size()));   
            document.add(new Paragraph("-------------------------------------------------------------"));
            /*for(Emprestimo c : emprestimos){
                document.add(new Paragraph("Nome: " + c.getNome() + "       |       Nacionalidade: " + c.getPais()+ "       |       Data de Nascimento: " + c.getDataNascimento()));
                document.add(new Paragraph("-------------------------------------------------------------"));
            } */               
            for(Emprestimo c : emprestimos){
                document.add(new Paragraph("Filme: " + c.getCopia().getFilme().getTitulo()));
                document.add(new Paragraph("Copia: " + c.getCopia().getCodigoCopia()));
                document.add(new Paragraph("Nome da pessoa: " + c.getPessoa().getNome()));
                document.add(new Paragraph("Telefone: " + c.getPessoa().getTelefone()));
                document.add(new Paragraph("Data de Emprestimo: " + c.getDataEmprestimo()));
                if(c.getDevolvido()==1) {
                    document.add(new Paragraph("Data da Devolução: " + c.getDataDevolucao()));
                } else {
                    document.add(new Paragraph("Filme ainda não devolvido. Ligue para o(a) " + c.getPessoa().getNome() + "!"));
                }
                document.add(new Paragraph("-------------------------------------------------------------"));
            }
            document.close();
    }
    
}
