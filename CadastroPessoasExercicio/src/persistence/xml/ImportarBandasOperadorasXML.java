/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence.xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Operadora;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author Isaac
 */
public class ImportarBandasOperadorasXML {

    private final String url1 = "data/operadoras1.xml";
    private final String url2 = "data/operadoras2.xml";
    private File arquivo1, arquivo2;
    private List<Element> elementosXml1, elementosXml2;
    private List<Operadora> operadoras;

    public ImportarBandasOperadorasXML() throws JDOMException, IOException {
        operadoras = new ArrayList<Operadora>();
        arquivo1 = new File(url1);
        arquivo2 = new File(url2);
        importar1();
        importar2();
    }

    private void importar1() throws JDOMException, IOException {
        Document d = new SAXBuilder().build(arquivo1);
        Element root = d.getRootElement();
        elementosXml1 = root.getChildren();
        for (Element e : elementosXml1) {
            String nome = e.getChildText("operadora");
            String[] ddd = e.getChildText("ddd").split(" ");
            String[] banda = e.getChildText("banda").split(" ");
            this.operadoras.add(new Operadora(nome, ddd, banda));
        }
    }

    private void importar2() throws JDOMException, IOException {
        Document d = new SAXBuilder().build(arquivo2);
        Element root = d.getRootElement();
        elementosXml2 = root.getChildren();
        for (Element e : elementosXml2) {
            String nome = e.getChildText("operadora");
            String[] ddd = e.getChildText("ddd").split(" ");
            int inicio = Integer.valueOf(e.getChildText("bandaInicial"));
            int fim = Integer.valueOf(e.getChildText("bandaFinal"));
            String[] banda = gerarBanda(inicio, fim);
            this.operadoras.add(new Operadora(nome, ddd, banda));
        }
    }

    private String[] gerarBanda(int inicio, int fim) {
        String[] ret;
        int size = fim-inicio+1;
        ret = new String[size];
        for(int i=0;i<size;i++){
            ret[i] = String.valueOf(inicio);
            inicio++;
        }
        return ret;
    }

    public List<Operadora> getOperadoras() {
        return operadoras;
    }
}
