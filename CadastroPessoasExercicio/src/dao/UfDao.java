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
public class UfDao extends GenericDAO {

    public List<String> getUfs() throws SQLException, ClassNotFoundException {
        conectar();
        ResultSet rs = s.executeQuery("select distinct uf from estado order by uf asc");
        List<String> ret = new ArrayList<String>();
        while (rs.next()) {
            ret.add(rs.getString("uf"));
        }
        desconectar();
        return ret;
    }
}
