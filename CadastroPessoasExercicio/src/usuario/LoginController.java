/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import controller.PrincipalController;
import dao.UsuarioDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Pessoa;
import model.Usuario;
import org.jdom2.JDOMException;
import persistence.xml.ImportarContatosXML;
import view.LoginView;

/**
 *
 * @author Isaac
 */
public class LoginController {

    private LoginView view;
    private UsuarioDAO dao;

    public LoginController() throws ClassNotFoundException, SQLException {
        view = new LoginView();
        dao = new UsuarioDAO();
        view.getBtnOk().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                entrar();
            }
        });
        view.getBtnSair().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                sair();
            }
        });
        if (dao.temUsuario()) {
            view.setVisible(true);
        } else {
            criaAdm();
        }

    }

    public void visualizaFrame() {
        view.setVisible(true);
    }

    private void entrar() {
        String login = view.getCmLogin().getText();
        String senha = view.getCmSenha().getText();
        Usuario user;
        try {
            user = dao.getUsuario(login);
            if (user != null) {
                if (senha.equals(user.getSenha())) {
                    new PrincipalController(user);
                    view.dispose();
                } else {
                    JOptionPane.showMessageDialog(view, "Senha incorreta!");
                }
            } else {
                JOptionPane.showMessageDialog(view, "Usuário incorreto!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sair() {
        view.dispose();

    }

    public static void main(String[] args) {
        try {
            new LoginController();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void criaAdm() {
        new AdministradorController(dao, this);
    }
}
