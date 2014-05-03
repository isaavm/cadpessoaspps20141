/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.xml;

import dao.PessoaDAO;
import java.awt.Component;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JFileChooser;
import model.Pessoa;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author Isaac
 */
public class ExportarContatosXML {

    private JFileChooser fc;
    private String diretorioArquivo, nomeArquivo;
    private FileWriter arquivo;
    private Component view;
    private Document agendaContatos;
    private List<Pessoa> lista;

    public ExportarContatosXML(Component Paiview) throws SQLException, ClassNotFoundException, IOException {
        this.view = Paiview;
        this.lista = new PessoaDAO().getCompletos();
        this.agendaContatos = new Document(new Element("contatos"));
        this.agendaContatos.detachRootElement();
        fc = new JFileChooser();
        int rjanela = fc.showSaveDialog(view);
        if (rjanela == JFileChooser.APPROVE_OPTION) {
            this.diretorioArquivo = fc.getSelectedFile().getAbsolutePath().replace(fc.getSelectedFile().getAbsoluteFile().getName(), "");
            this.nomeArquivo = fc.getSelectedFile().getAbsoluteFile().getName();
            this.arquivo = new FileWriter(new File(diretorioArquivo + nomeArquivo));
            exportar();
        }
    }

    private void exportar() throws IOException {
        long ini, fim;
        Element contato;
        ini = GregorianCalendar.getInstance().getTimeInMillis();
        for (Pessoa p : lista) {
            contato = new Element("contato");
            contato.setAttribute("Nome", p.getNome());
            contato.addContent(new Element("Telefone").setText(p.getTelefone()));
            contato.addContent(new Element("Operadora").setText(p.getOperadora()));
            contato.addContent(new Element("UF").setText(p.getUf()));
            Element copy = (Element) contato.clone();
            copy.detach();
            agendaContatos.addContent(copy);
        }
        XMLOutputter out = new XMLOutputter();
        out.output(this.agendaContatos, this.arquivo);
        fim = GregorianCalendar.getInstance().getTimeInMillis();
//        long delta = fim - ini;
//        System.out.println(delta);
    }

    public int getQuantidade() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
