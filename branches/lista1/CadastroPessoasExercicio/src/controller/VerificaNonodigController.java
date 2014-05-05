/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.TelefoneDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Pessoa;

/**
 *
 * @author Isaac
 */
public class VerificaNonodigController {

    private Pessoa pessoa;
    private List<String> dddNonodig;
    private List<String> bandas;

    public VerificaNonodigController(Pessoa pessoa) throws SQLException, ClassNotFoundException {
        this.pessoa = pessoa;
        this.bandas = new ArrayList<String>();
        populaBandas();
        dddNonodig = new TelefoneDao().getNonos();
    }

    private void populaBandas() {
        for(int i=6;i<=9;i++){
            bandas.add(String.valueOf(i));
        }
    }

    public Pessoa executarVerificacao() {
        String[] cels = {"6", "7", "8", "9"};
        for (String d : dddNonodig) {
            pessoa.setTelefone(pessoa.getTelefone().replace(" ", ""));
            if (pessoa.getTelefone().startsWith("(" + d) && 
                    bandas.contains(String.valueOf(pessoa.getTelefone().charAt(4)))) {
                String novo = pessoa.getTelefone().substring(0, 4) + "9" + pessoa.getTelefone().substring(4);
                pessoa.setTelefone(novo);
                break;
            }
        }
        return pessoa;
    }

}
