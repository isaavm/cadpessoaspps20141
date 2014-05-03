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
public class InclusaoUsuario extends EstadoFrameInclusaoUsuario {

    public InclusaoUsuario(InclusaoUsuarioController c) {
        super(c);
        c.getView().getCmLogin().setText("");
        c.getView().getCmSenha().setText("");
        c.getView().getCmRepSenha().setText("");

        c.getView().setTitle("Incluir usuario");
        c.getView().getBtnCancelar().setText("Cancelar");
        c.getView().getBtnCadastrar().setText("Salvar");
        c.getView().getBtnCancelar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fechar();
            }
        });
        c.getView().getBtnCadastrar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                salvar();
            }
        });
    }

    @Override
    public void salvar() {
        try {
            c.salvar();
            c.setEstado(new VisualizacaoUsuario(c));
        } catch (SQLException ex) {
            Logger.getLogger(InclusaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InclusaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(InclusaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
