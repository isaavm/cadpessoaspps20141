/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario.listagem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import usuario.controller.ListagemUsuarioController;

/**
 *
 * @author Isaac
 */
public class UsuarioSelecionado extends EstadoFrameListagemUsuario {

    public UsuarioSelecionado(ListagemUsuarioController c) throws SQLException, ClassNotFoundException {
        super(c);
        int linha = c.getView().getTabela().getSelectedRow();
        String nome = c.getView().getTabela().getValueAt(linha, 0).toString();
        c.setUsuario(c.getDao().getUsuario(nome));
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
        c.setEstado(new UsuarioNaoSelecionado(c));
    }

    @Override
    public void excluir() {

        try {
            c.excluir();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioSelecionado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioSelecionado.class.getName()).log(Level.SEVERE, null, ex);
        }
        desselecionar();
    }

    @Override
    public void visualizar() {
        try {
            c.visualizar();
            c.pesquisar();
        } catch (IOException ex) {
            Logger.getLogger(UsuarioSelecionado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioSelecionado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioSelecionado.class.getName()).log(Level.SEVERE, null, ex);
        }

        desselecionar();
    }
}
