/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario.inclusao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import usuario.controller.InclusaoUsuarioController;

/**
 *
 * @author Isaac
 */
public class VisualizacaoUsuario extends EstadoFrameInclusaoUsuario {

    public VisualizacaoUsuario(InclusaoUsuarioController c) {
        super(c);
        c.getView().getCmLogin().setText(c.getInstancia().getLogin());
        c.getView().getCmSenha().setText(c.getInstancia().getSenha());
        c.getView().getCmRepSenha().setText("");
        c.getView().getCmLogin().setEditable(false);
        c.getView().getCmSenha().setEditable(false);
        c.getView().getCmRepSenha().setEditable(false);
        c.getView().setTitle("Visualizar usuario");
        c.getView().getBtnCancelar().setText("Fechar");
        c.getView().getBtnCadastrar().setText("Editar");
        c.getView().getBtnCancelar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fechar();
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
        c.setEstado(new EdicaoUsuario(c));
    }

}
