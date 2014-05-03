/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.xml;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import model.Pessoa;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import view.BarraDeProgressoView;

/**
 *
 * @author Isaac
 */
public class ImportarContatosXML {

    private JFileChooser fc;
    private String diretorioArquivo, nomeArquivo;
    private File arquivo;
    private Component view;
    private List<Pessoa> lista;
    private List<Element> elementosXml;
    private BarraDeProgressoView pr;

    public ImportarContatosXML(Component paiView) throws IOException, JDOMException {
        this.view = paiView;
        pr = new BarraDeProgressoView();
        this.lista = new ArrayList<Pessoa>();
        fc = new JFileChooser();
        int rjanela = fc.showOpenDialog(view);
        if (rjanela == JFileChooser.OPEN_DIALOG) {
            this.diretorioArquivo = fc.getSelectedFile().getAbsolutePath().replace(fc.getSelectedFile().getAbsoluteFile().getName(), "");
            this.nomeArquivo = fc.getSelectedFile().getAbsoluteFile().getName();
            if (this.diretorioArquivo != null && this.nomeArquivo != null) {
                this.arquivo = new File(this.diretorioArquivo + this.nomeArquivo);
                Document doc = new SAXBuilder().build(arquivo);
                Element root = doc.getRootElement();
                this.elementosXml = root.getChildren();
                importar();
            }
        }
    }

    private List<Pessoa> importar() {
        configuraBarra();
        int i = 0;
        for (Element e : this.elementosXml) {
            pr.getProgressbar().setValue(++i);
            String nome = e.getChildText("Nome");
            String telefone = e.getChildText("Telefone");
            this.lista.add(new Pessoa(nome, telefone));
        }
        pr.dispose();
        return this.lista;
    }

    private void configuraBarra() {
        pr.setLocationRelativeTo(view);
        pr.getProgressbar().setMaximum(this.elementosXml.size());
        pr.pack();
        pr.setVisible(true);
    }
    
    public int qtdSucesso(){
        return 0;
    }
    
    public int qtdIncomp(){
        return 0;
    }
}
