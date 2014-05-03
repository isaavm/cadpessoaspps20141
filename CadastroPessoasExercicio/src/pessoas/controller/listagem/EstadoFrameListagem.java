/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pessoas.controller.listagem;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import pessoas.controller.ListaPessoasController;

/**
 *
 * @author Isaac
 */
public abstract class EstadoFrameListagem {

    protected ListaPessoasController c;

    public EstadoFrameListagem(ListaPessoasController c) {
        this.c = c;
        limparListeners();
        c.setPessoa(null);
        
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
            JOptionPane.showMessageDialog(c.getView(), ex.getMessage());
            Logger.getLogger(EstadoFrameListagem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(c.getView(), ex.getMessage());
            Logger.getLogger(EstadoFrameListagem.class.getName()).log(Level.SEVERE, null, ex);
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
        c.getPessoas().removeObserver(c);
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
