/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario.listagem;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import usuario.controller.ListagemUsuarioController;

/**
 *
 * @author Isaac
 */
public abstract class EstadoFrameListagemUsuario {

    protected ListagemUsuarioController c;

    public EstadoFrameListagemUsuario(ListagemUsuarioController c) {
        this.c = c;
        limparListeners();
        c.setUsuario(null);
        c.getView().getBtnPesquisar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                pesquisar();
            }
        });
        c.getView().getBtnFehar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fechar();
            }
        });
    }

    private void pesquisar() {

        try {
            c.pesquisar();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EstadoFrameListagemUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EstadoFrameListagemUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private final void limparListeners() {
        for (Component comp : c.getView().getContentPane().getComponents()) {
            if (comp instanceof JButton) {
                for (ActionListener a : ((JButton) comp).getActionListeners()) {
                    ((JButton) comp).removeActionListener(a);
                }
            }
        }
    }

    public final void fechar() {
        c.getView().dispose();
    }

    public void excluir() {
    }

    public void visualizar() {
    }

    public void selecionar() {
    }

    public void desselecionar() {
    }

}
