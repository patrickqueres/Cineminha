/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cineminha_v2;

import cineminha.model.Ator;
import java.io.File;
import java.io.IOException;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
/**
 *
 * @author anamm
 */
public class JPAExemploIText {
    /** Path to the resulting PDF file. */
    public static final String RESULT
        = "atores.pdf";
 
    /**
     * Creates a PDF file: hello.pdf
     * @param    args    no arguments needed
     */
    public static void main(String[] args)
    	throws DocumentException, IOException {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
            File arquivo = fileChooser.getSelectedFile();            
            new JPAExemploIText().createPdf(arquivo.getPath().contains(".pdf")?arquivo.getPath():arquivo.getPath()+".pdf");
        }
    }
 
    /**
     * Creates a PDF document.
     * @param filename the path to the new PDF document
     * @throws    DocumentException 
     * @throws    IOException 
     */
    public void createPdf(String filename)
	throws DocumentException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cineminha_v2");
        EntityManager em = emf.createEntityManager();
        TypedQuery<Ator> consulta = em.createQuery("Select c from Ator c",Ator.class);
        List<Ator> atores = consulta.getResultList();
        Document document = new Document();        
        PdfWriter.getInstance(document, new FileOutputStream(filename));        
        document.open();        
        document.add(new Paragraph("Atores: "+atores.size()));      
        for(Ator c : atores){
            document.add(new Paragraph(c.getNome()));                  
        }                
        document.close();
    }
}
