/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.List;
import model.Operadora;
import org.jdom2.JDOMException;
import persistence.xml.ImportarBandasOperadorasXML;

/**
 *
 * @author Isaac
 */
public class IdentificaOperadoraController {

    List<Operadora> operadoras;
    String numero;
    String operadora;

    public IdentificaOperadoraController(String numero) throws IOException, JDOMException {
        this.numero = numero.replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
        operadoras = new ImportarBandasOperadorasXML().getOperadoras();
        operadora = identificaOperadora2();
    }

    private String identificaOperadora2(){
        String ret = "";
        String ddd = numero.substring(0, 2);
        String bandanum = numero.substring(2);
        for(Operadora o: operadoras){
            if(temDdd(ddd,o)){
                if(temBanda(bandanum,o)){
                    ret = o.getOperadora();
                    break;
                }
            }
        }
        return ret;
    }
    private String identificaOperadora() {
        String ret = "";
        boolean achou = false;
        
        String ddd = numero.substring(0, 2);
        String bandanum = numero.substring(2);
        for (Operadora o : this.operadoras) {
            for (String d : o.getDdd()) {
                if (d.equals(ddd)) {
                    for (String ban : o.getBanda()) {
                        if (bandanum.substring(0, ban.length()+1).equals(ban)) {
                            ret = o.getOperadora();
                            achou = true;
                        }
                        if (achou) {
                            break;
                        }
                    }
                }
                if (achou) {
                    break;
                }
            }
            if (achou) {
                break;
            }
        }
        return ret;
    }

    public String getOperadora() {
        return operadora;
    }

    private boolean temDdd(String ddd, Operadora o) {
        for(String oddd: o.getDdd()){
            if(ddd.equals(oddd))
                return true;
        }
        return false;
    }

    private boolean temBanda(String bandanum, Operadora o) {
        for(String obanda: o.getBanda()){
            String co = new String(bandanum.substring(0,obanda.length()));
            if(co.equals(obanda)){
                return true;
            }
        }
        return false;
    }

}
