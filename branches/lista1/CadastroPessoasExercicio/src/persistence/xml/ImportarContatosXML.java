/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.xml;

import controller.IdentificaOperadoraController;
import controller.IdentificaUfController;
import controller.PrincipalController;
import controller.VerificaNonodigController;
import dao.PessoaDAO;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import model.Pessoa;
import model.Usuario;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import strategy.log.ILog;
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
    private List<Pessoa> importados;
    private List<Element> elementosXml;
    private BarraDeProgressoView pr;
    private PessoaDAO dao;
    private int sucesso = 0, incompleto = 0;
    private Usuario user;
    private ILog log;

    public ImportarContatosXML(Component paiView, ILog log, Usuario u) throws IOException, JDOMException, SQLException, ClassNotFoundException {
        this.user = u;
        this.log = log;        
        this.dao = new PessoaDAO();
        this.view = paiView;
        pr = new BarraDeProgressoView();
        this.importados = new ArrayList<Pessoa>();
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

    private void importar() throws ClassNotFoundException, IOException, JDOMException, SQLException {
        configuraBarra();
        int i = 0;
        for (Element e : this.elementosXml) {
            pr.getProgressbar().setValue(++i);
            String nome = e.getChildText("Nome");
            String telefone = e.getChildText("Telefone");
            String uf = new IdentificaUfController(telefone).recuperaUf();
            String opera = new IdentificaOperadoraController(telefone).getOperadora();
            try {
                Pessoa p = new VerificaNonodigController(new Pessoa(nome, telefone, uf, opera)).executarVerificacao();
                if (dao.add(p)) {
                    if (p.getOperadora().equals("") || p.getUf().equals("")) {
                        this.incompleto++;
                    } else {
                        this.sucesso++;
                    }
                }
            } catch (SQLException ex) {
                log.msgFalhaImpExportacao(ex.getMessage(), user);
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        pr.dispose();
    }

    private void configuraBarra() {
        pr.setLocationRelativeTo(view);
        pr.getProgressbar().setMaximum(this.elementosXml.size());
        pr.pack();
        pr.setVisible(true);
    }

    public int qtdSucesso() {
        return this.sucesso;
    }

    public int qtdIncomp() {
        return this.incompleto;
    }
}
