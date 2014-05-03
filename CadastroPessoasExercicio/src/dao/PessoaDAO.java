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
import java.util.Observer;
import model.Pessoa;

/**
 *
 * @author Isaac
 */
public class PessoaDAO extends GenericDAO {

    public void carregarPessoas() {
    }

    public boolean add(Pessoa p) throws SQLException, ClassNotFoundException {
        conectar();
        String sql = "";
        if (p.getUf() != null && p.getOperadora() != null) {
            sql = "insert into Pessoa (nome,telefone,uf,operadora) values (\'"
                    + p.getNome() + "\',\'" + p.getTelefone() + "\',\'"
                    + p.getUf() + "\',\'" + p.getOperadora() + "\')";
        } else if (p.getUf() != null) {
            sql = "insert into Pessoa (nome,telefone,uf) values (\'"
                    + p.getNome() + "\',\'" + p.getTelefone() + "\',\'"
                    + p.getUf() + "\')";
        } else if (p.getOperadora() != null) {
            sql = "insert into Pessoa (nome,telefone,operadora) values (\'"
                    + p.getNome() + "\',\'" + p.getTelefone() + "\',\'"
                    + p.getOperadora() + "\')";
        } else {
            sql = "insert into Pessoa (nome,telefone) values (\'"
                    + p.getNome() + "\',\'" + p.getTelefone() + "\')";
        }

        int r = s.executeUpdate(sql);
        desconectar();
        notifica();
        if (r > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean editar(String nomeAntigo, Pessoa p) throws ClassNotFoundException, SQLException {
        conectar();
        int r = s.executeUpdate("update Pessoa set nome=\'" + p.getNome() + "\',"
                + "telefone=\'" + p.getTelefone()
                + "' where nome like \'" + nomeAntigo + "\'");
//        int r = s.executeUpdate("update Pessoa set nome=\'" + p.getNome() + "\',"
//                + "telefone=\'" + p.getTelefone() + "\',"
//                + "operadora=\'" + p.getOperadora() + "\',"
//                + "uf=\'" + p.getUf() + "\' where nome like \'" + nomeAntigo + "\'");
        desconectar();
        notifica();
        if (r > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean excluir(String nome) throws SQLException, ClassNotFoundException {
        conectar();
        int r = s.executeUpdate("delete from Pessoa where nome like \'" + nome + "\'");
        desconectar();
        notifica();
        if (r > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<Pessoa> getPessoas() throws ClassNotFoundException, SQLException {
        conectar();
        ResultSet rs = s.executeQuery("select * from Pessoa");
        List<Pessoa> ret = new ArrayList<Pessoa>();;
        while (rs.next()) {
            String nome = rs.getString("nome");
            String telefone = rs.getString("telefone");
            String op = rs.getString("operadora");
            String uf = rs.getString("uf");
            Pessoa p = new Pessoa(nome, telefone);
            p.setOperadora(op);
            p.setUf(uf);
            ret.add(p);
        }
        desconectar();
        return ret;
    }

    public Pessoa getPessoaByName(String filtro) throws ClassNotFoundException, SQLException {
        conectar();
        ResultSet rs = s.executeQuery("select * from Pessoa where nome like \'" + filtro + "\'");
        Pessoa p = null;
        while (rs.next()) {
            String nome = rs.getString("nome");
            String telefone = rs.getString("telefone");
            String op = rs.getString("operadora");
            String uf = rs.getString("uf");
            p = new Pessoa(nome, telefone);
            p.setOperadora(op);
            p.setUf(uf);
        }
        desconectar();
        return p;
    }

    public List<Pessoa> getByNome(String filtro) throws ClassNotFoundException, SQLException {
        List<Pessoa> ret = new ArrayList<Pessoa>();
        if (!filtro.equals("")) {
            conectar();
            ResultSet rs = s.executeQuery("select * from Pessoa where nome like \'%" + filtro + "%\';");
            while (rs.next()) {
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String op = rs.getString("operadora");
                String uf = rs.getString("uf");
                Pessoa p = new Pessoa(nome, telefone);
                p.setOperadora(op);
                p.setUf(uf);
                ret.add(p);
            }
            desconectar();
        } else {
            ret = getPessoas();
        }
        return ret;
    }

    public List<Pessoa> getByTelefone(String filtro) throws ClassNotFoundException, SQLException {

        List<Pessoa> ret = new ArrayList<Pessoa>();;
        if (!filtro.equals("")) {
            conectar();
            ResultSet rs = s.executeQuery("select * from Pessoa where telefone like \'%" + filtro + "%\'");
            while (rs.next()) {
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String op = rs.getString("operadora");
                String uf = rs.getString("uf");
                Pessoa p = new Pessoa(nome, telefone);
                p.setOperadora(op);
                p.setUf(uf);
                ret.add(p);
            }
            desconectar();
        } else {
            ret = getPessoas();
        }
        return ret;
    }

    public List<Pessoa> getCompletos() throws SQLException, ClassNotFoundException {
        List<Pessoa> ret = new ArrayList<Pessoa>();
        conectar();
        ResultSet rs = s.executeQuery("select * from Pessoa "
                + "where nome is not null and telefone is not null "
                + "and uf is not null and operadora is not null");
        while (rs.next()) {
            String nome = rs.getString("nome");
            String telefone = rs.getString("telefone");
            String op = rs.getString("operadora");
            String uf = rs.getString("uf");
            Pessoa p = new Pessoa(nome, telefone, uf, op);
            ret.add(p);
        }
        desconectar();
        return ret;
    }
}
