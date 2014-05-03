/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario.inclusao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import usuario.controller.InclusaoUsuarioController;

/**
 *
 * @author Isaac
 */
public class EdicaoUsuario extends EstadoFrameInclusaoUsuario {

    public EdicaoUsuario(InclusaoUsuarioController c) {
        super(c);
        c.setNomeAntigo(c.getView().getCmLogin().getText());
        c.getView().getCmLogin().setEditable(true);
        c.getView().getCmSenha().setEditable(true);
        c.getView().getCmRepSenha().setText("");
        c.getView().getCmRepSenha().setEditable(true);
        c.getView().setTitle("Editar usuário");
        c.getView().getBtnCancelar().setText("Cancelar");
        c.getView().getBtnCadastrar().setText("Salvar");
        c.getView().getBtnCancelar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        });
        c.getView().getBtnCadastrar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                editar();
            }
        });
    }

    @Override
    public void editar() {
        try {
            c.editar();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EdicaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EdicaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EdicaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        c.setEstado(new VisualizacaoUsuario(c));

    }

    @Override
    public void cancelar() {
        try {
            c.cancelarEdicao();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EdicaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EdicaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        c.setEstado(new VisualizacaoUsuario(c));
    }
}
