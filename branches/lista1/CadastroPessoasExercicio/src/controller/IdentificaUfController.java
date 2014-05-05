/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.TelefoneDao;
import java.sql.SQLException;
import model.Pessoa;

/**
 *
 * @author Isaac
 */
public class IdentificaUfController {

    private String tel;
    private TelefoneDao dao;

    public IdentificaUfController(String telefone) {
        this.tel = telefone;
        this.dao = new TelefoneDao();
    }

    public String recuperaUf() throws SQLException, ClassNotFoundException {
        String ret= "";
        if(tel.contains("(")){
            ret = dao.getUfByDdd(tel.substring(1, 3));
        }else{
            ret = dao.getUfByDdd(tel.substring(0,2));
        }
        return ret;
    }
}
