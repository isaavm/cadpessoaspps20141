/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pessoas.controller.listagem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import pessoas.controller.ListaPessoas;

/**
 *
 * @author Isaac
 */
public class PessoaSelecionada extends EstadoFrameListagem {

    public PessoaSelecionada(ListaPessoas c) throws ClassNotFoundException, SQLException {
        super(c);
        int linha = c.getView().getTabela().getSelectedRow();
        String nome = c.getView().getTabela().getValueAt(linha, 0).toString();
        c.setPessoa(c.getPessoas().getPessoaByName(nome));
        c.getView().getBtnExcluir().setEnabled(true);
        c.getView().getBtnVisualizar().setEnabled(true);
        c.getView().getBtnVisualizar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                visualizar();
            }
        });
        c.getView().getBtnExcluir().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                excluir();
            }
        });
    }

    @Override
    public void desselecionar() {
        c.setEstado(new PessoaNaoSelecionada(c));
    }

    @Override
    public void excluir() {

        c.excluir();
        desselecionar();
    }

    @Override
    public void visualizar() {
        try {
            c.visualizar();
            c.pesquisar();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(c.getView(), ex.getMessage());
            Logger.getLogger(PessoaSelecionada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(c.getView(), ex.getMessage());
            Logger.getLogger(PessoaSelecionada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(c.getView(), ex.getMessage());
            Logger.getLogger(PessoaSelecionada.class.getName()).log(Level.SEVERE, null, ex);
        }
        desselecionar();
    }
}
