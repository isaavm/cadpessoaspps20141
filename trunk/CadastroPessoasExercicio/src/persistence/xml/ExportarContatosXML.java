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
    private Element root;

    public ExportarContatosXML(Component Paiview) throws SQLException, ClassNotFoundException, IOException {
        this.view = Paiview;
        this.lista = new PessoaDAO().getCompletos();
        this.root = new Element("contatos");
        this.agendaContatos = new Document(this.root);
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
        Element contato;
       for (Pessoa p : lista) {
            contato = new Element("contato");
            contato.setAttribute("Nome", p.getNome());
            contato.addContent(new Element("Telefone").setText(p.getTelefone()));
            contato.addContent(new Element("Operadora").setText(p.getOperadora()));
            contato.addContent(new Element("UF").setText(p.getUf()));
            this.root.addContent(contato);
        }
        XMLOutputter out = new XMLOutputter();
        out.output(this.agendaContatos, this.arquivo);
    }

    public int getQuantidade() {
        return this.lista.size();
    }
}
