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
import model.Usuario;

/**
 *
 * @author Isaac
 */
public class UsuarioDAO extends GenericDAO {

    public boolean addUsuario(Usuario u) throws SQLException, ClassNotFoundException {
        conectar();
        boolean ret = false;
        String ad;
        if (u.isAdm()) {
            ad = "1";
        } else {
            ad = "0";
        }
        int rs = s.executeUpdate("insert into Usuario (login,senha,adm) values (\'"
                + u.getLogin() + "\',\'" + u.getSenha() + "\'," + ad + ")");
        if (rs > 0) {
            ret = true;
        }
        desconectar();
        notifica();
        return ret;
    }

    public void deletarUsuario(Usuario u) throws SQLException, ClassNotFoundException {
        conectar();
        s.executeUpdate("delete from Usuario where login like \'" + u.getLogin() + "\'");
        desconectar();
        notifica();
    }

    public void modificaUsuario(Usuario u, String loginAntigo) throws SQLException, ClassNotFoundException {
        conectar();
        s.executeUpdate("update Usuario set login=\''" + u.getLogin() + "\',"
                + "senha=\'" + u.getSenha() + "\' where login like '" + loginAntigo + "\'");
        desconectar();
        notifica();
    }

    public Usuario getUsuario(String filtro) throws SQLException, ClassNotFoundException {
        conectar();
        Usuario ret = null;
        ResultSet rs = s.executeQuery("select * from Usuario where login like \'" + filtro + "\'");
        if (rs.next()) {
            String login = rs.getString("login");
            String senha = rs.getString("senha");
            int adm = rs.getInt("adm");
            ret = new Usuario(login, senha);
            if (adm == 1) {
                ret.setAdm(true);
            } else {
                ret.setAdm(false);
            }
        }
        desconectar();
        notifica();
        return ret;
    }

    public boolean temUsuario() throws SQLException, ClassNotFoundException {
        boolean ret = false;
        conectar();
        ResultSet rs = s.executeQuery("select * from Usuario");
        if (rs.next()) {
            ret = true;
        }
        desconectar();
        return ret;
    }

    public List<Usuario> getByFiltro(String filtro) throws SQLException, ClassNotFoundException {
        List<Usuario> ret = new ArrayList<Usuario>();
        if (!filtro.equals("")) {
            conectar();
            ResultSet rs = s.executeQuery("select * from Usuario where nome like \'%" + filtro + "%\';");
            while (rs.next()) {
                String login = rs.getString("login");
                String senha = rs.getString("senha");
                int ad = rs.getInt("adm");
                Usuario u = new Usuario(login, senha);
                if (ad == 0) {
                    u.setAdm(false);
                } else {
                    u.setAdm(true);
                }
                ret.add(u);
            }
            desconectar();
        } else {
            ret = getUsuarios();
        }
        return ret;
    }

    public void excluir(String login) throws SQLException, ClassNotFoundException {
        conectar();
        int r = s.executeUpdate("delete from Usuario where login like \'" + login + "\'");
        desconectar();
        notifica();

    }

    public List<Usuario> getUsuarios() throws SQLException, ClassNotFoundException {
        List<Usuario> ret = new ArrayList<Usuario>();
        conectar();
        ResultSet rs = s.executeQuery("select * from Usuario");
        while (rs.next()) {
            String login = rs.getString("login");
            String senha = rs.getString("senha");
            int ad = rs.getInt("adm");
            Usuario u = new Usuario(login, senha);
            if (ad == 0) {
                u.setAdm(false);
            } else {
                u.setAdm(true);
            }
            ret.add(u);
        }
        desconectar();
        return ret;
    }
}
