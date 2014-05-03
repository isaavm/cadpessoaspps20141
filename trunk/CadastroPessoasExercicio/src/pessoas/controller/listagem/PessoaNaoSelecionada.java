/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pessoas.controller.listagem;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import pessoas.controller.ListaPessoasController;

/**
 *
 * @author Isaac
 */
public class PessoaNaoSelecionada extends EstadoFrameListagem {

    public PessoaNaoSelecionada(ListaPessoasController c) {
        super(c);
        c.setPessoa(null);
        c.getView().getBtnExcluir().setEnabled(false);
        c.getView().getBtnVisualizar().setEnabled(false);
    }

    @Override
    public void selecionar() {
        try {
            c.setEstado(new PessoaSelecionada(c));
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(c.getView(), ex.getMessage());
            Logger.getLogger(PessoaNaoSelecionada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(c.getView(), ex.getMessage());
            Logger.getLogger(PessoaNaoSelecionada.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
