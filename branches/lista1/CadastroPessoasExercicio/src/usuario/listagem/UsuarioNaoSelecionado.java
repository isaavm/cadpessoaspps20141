/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario.listagem;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import usuario.controller.ListagemUsuarioController;

/**
 *
 * @author Isaac
 */
public class UsuarioNaoSelecionado extends EstadoFrameListagemUsuario {

    public UsuarioNaoSelecionado(ListagemUsuarioController c) {
        super(c);
        c.setUsuario(null);
        c.getView().getBtnExcluir().setEnabled(false);
        c.getView().getBtnVisualizar().setEnabled(false);
    }

    @Override
    public void selecionar() {

        try {
            c.setEstado(new UsuarioSelecionado(c));
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioNaoSelecionado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioNaoSelecionado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
