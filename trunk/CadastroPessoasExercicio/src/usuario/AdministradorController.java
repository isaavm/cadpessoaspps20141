/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import dao.UsuarioDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Usuario;
import view.InclusaoUsuarioView;

/**
 *
 * @author Isaac
 */
public class AdministradorController {

    private LoginController login;
    private InclusaoUsuarioView view;
    private UsuarioDAO dao;

    public AdministradorController(UsuarioDAO d,LoginController lc) {
        view = new InclusaoUsuarioView();
        dao = d;
        login = lc;
        view.getBtnCadastrar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrar();
            }
        });
        view.getBtnCancelar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        });
        view.getCmLogin().requestFocus();
        view.setVisible(true);
    }

    private void cadastrar() {
        String login = view.getCmLogin().getText();
        String senha = view.getCmSenha().getText();
        String repSenha = view.getCmRepSenha().getText();
        System.out.println(senha);
        System.out.println(repSenha);
        if (senha.equals(repSenha)) {
            Usuario u = new Usuario(login, senha);
            u.setAdm(true);
            try {
                dao.addUsuario(u);
                view.dispose();
                this.login.visualizaFrame();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage());
                Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage());
                Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(view, "As senhas não conferem!");
            view.getCmLogin().setText("");
            view.getCmSenha().setText("");
            view.getCmRepSenha().setText("");
            view.getCmLogin().requestFocus();
        }
    }

    private void cancelar() {
        view.dispose();
        this.login.sair();
    }
}
