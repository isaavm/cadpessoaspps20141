/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pessoas.controller.inclusao;

import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import pessoas.controller.InclusaoPessoaController;

/**
 *
 * @author Isaac
 */
public abstract class EstadoFrameInclusao {

    protected InclusaoPessoaController c;

    public EstadoFrameInclusao(InclusaoPessoaController c) {
        this.c = c;
        limparListeners();
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

    public void salvar() {
    }

    public void editar() {
    }
    
    public void cancelar() {
    }
}
