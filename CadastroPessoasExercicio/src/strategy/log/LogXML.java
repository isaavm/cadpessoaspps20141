/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Pessoa;
import model.Usuario;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author Isaac
 */
public class LogXML implements ILog {

    private final String diretorio = "log/log.xml";
    private Document doclog;
    private Element root;
    private FormatLog formata;
    File arq;

    public LogXML() {
        this.arq = new File(diretorio);
        if (!arq.exists()) {
            try {
                arq.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(LogXML.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.formata = new FormatLog();
        this.root = new Element("log");
        this.doclog = new Document(root);

    }

    @Override
    public void msgManter(String operacao, Pessoa contato, Usuario usuario) {
        GregorianCalendar g = new GregorianCalendar();
        gravar(operacao + " do contato " + contato.getNome()
                + ", (" + formata.formataData(g.getTime()) + ", " + formata.formataHora(g.getTime()) + ", e " + usuario.getLogin() + ")");
    }

    @Override
    public void msgFalhaManter(String msgFalha, String operacao, Pessoa contato, Usuario usuario) {
        GregorianCalendar g = new GregorianCalendar();
        gravar("Ocorreu uma falha " + msgFalha + " ao realizar a " + operacao + " do contato " + contato.getNome()
                + ", (" + formata.formataData(g.getTime()) + ", " + formata.formataHora(g.getTime()) + ", e " + usuario.getLogin() + ")");
    }

    @Override
    public void msgImportacao(int sucesso, int incompleto, Usuario usuario) {
        GregorianCalendar g = new GregorianCalendar();
        gravar(String.valueOf(sucesso) + " contatos com sucesso, "
                + String.valueOf(incompleto) + " incompletos, (" + formata.formataData(g.getTime()) + ", " + formata.formataHora(g.getTime()) + ", e " + usuario.getLogin() + ")");
    }

    @Override
    public void msgExportacao(int quantidade, Usuario usuario) {
        GregorianCalendar g = new GregorianCalendar();
        gravar("Exportação realizada: " + String.valueOf(quantidade) + " contatos exportados. "
                + " (" + formata.formataData(g.getTime()) + ", " + formata.formataHora(g.getTime()) + ", e " + usuario.getLogin() + ")");
    }

    @Override
    public void msgFalhaImpExportacao(String msg, Usuario usuario) {
        GregorianCalendar g = new GregorianCalendar();
        gravar("Falha " + msg + " durante a importação (" + formata.formataData(g.getTime()) + ", " + formata.formataHora(g.getTime()) + ", e " + usuario.getLogin() + ")");
    }

    private void gravar(String msg) {
        Element reg;
        reg = new Element("registro");
        reg.addContent(new Element("mensagem").setText(msg));
        this.root.addContent(reg);
        XMLOutputter out = new XMLOutputter();
        String vai = out.outputString(doclog);
        FileWriter w;
        try {
            w = new FileWriter(arq, true);
            BufferedWriter b = new BufferedWriter(w);
            b.write(vai);
            b.newLine();
            b.close();
        } catch (IOException ex) {
            Logger.getLogger(LogTXT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
