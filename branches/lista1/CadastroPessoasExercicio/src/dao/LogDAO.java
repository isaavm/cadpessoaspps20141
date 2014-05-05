/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Isaac
 */
public class LogDAO extends GenericDAO{

    public LogDAO() {
    }
    
    public void trocarLog(String log) throws SQLException, ClassNotFoundException{
        conectar();
       ResultSet rs=s.executeQuery("select * from Log");
        if(rs.next()){
            String filtro = rs.getString("nome");
            desconectar();
            conectar();
            s.executeUpdate("update Log set nome=\'"+log+"\' where nome like \'"+filtro+"\'");
        }else{
            desconectar();
            conectar();
            s.executeUpdate("inset int Log (nome) values (\'"+log+"\')");
        }
        desconectar();
    }
    
    public String recuperarLog() throws SQLException, ClassNotFoundException{
        String ret = "";
        conectar();
        ResultSet rs = s.executeQuery("select * from Log");
        if(rs.next()){
            ret = rs.getString("nome");
        }else{
            desconectar();
            conectar();
            s.executeUpdate("insert into Log (nome) values ('txt')"  );
            ret = "txt";
        }
        desconectar();
        return ret;
    }
}
