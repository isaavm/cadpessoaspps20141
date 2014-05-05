/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Isaac
 */
public class TelefoneDao extends GenericDAO{

    public TelefoneDao() {
    }
    
    public List<String> getNonos() throws SQLException, ClassNotFoundException{
        List<String> ret = new ArrayList<String>();
        ResultSet rs;
        conectar();
        rs = s.executeQuery("select ddd from estado where nonoDigito = 1");
        while(rs.next()){
            ret.add(rs.getString("ddd"));
        }
        desconectar();
        return ret;
    }

    public String getUfByDdd(String filtro) throws SQLException, ClassNotFoundException {
        conectar();
        String ret = "";
        ResultSet rs = s.executeQuery("select uf from estado where ddd like \'"+filtro+"\'");
        if(rs.next()){
            ret = rs.getString("uf");
        }
        desconectar();
        return ret;
    }
}
