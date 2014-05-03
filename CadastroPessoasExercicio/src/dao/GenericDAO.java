/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import observer.Subject;

/**
 *
 * @author Isaac
 */
public abstract class GenericDAO extends Subject{

    protected Connection c;
    protected Statement s;

    public GenericDAO() {
    }

    protected void conectar() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/cadpessoas", "root", " ");
        s = c.createStatement();
    }

    protected void desconectar() throws SQLException {
        if (!s.isClosed()) {
            s.close();
        }
        if (!c.isClosed()) {
            c.close();
        }
    }
}
