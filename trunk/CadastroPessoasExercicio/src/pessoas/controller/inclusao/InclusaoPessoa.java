/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pessoas.controller.inclusao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import pessoas.controller.InclusaoPessoaController;

/**
 *
 * @author Isaac
 */
public class InclusaoPessoa extends EstadoFrameInclusao {

    public InclusaoPessoa(InclusaoPessoaController c) {
        super(c);
        c.getView().getTxtNome().setText("");
        c.getView().getTxtTelefone().setText("");
        c.getView().setTitle("Incluir contato");
        c.getView().getBtnFechar().setText("Cancelar");
        c.getView().getBtnSalvar().setText("Salvar");
        c.getView().getBtnFechar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fechar();
            }
        });
        c.getView().getBtnSalvar().addActionListener(new ActionListener() {

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
            c.getLog().msgManter("Inclusão", c.getInstancia(), c.getUser());
            c.setEstado(new VisualizacaoPessoa(c));
        } catch (IOException ex) {
            c.getLog().msgFalhaManter(ex.getMessage(), "Inclusão", c.getInstancia(), c.getUser());
            JOptionPane.showMessageDialog(c.getView(), ex.getMessage());
            Logger.getLogger(InclusaoPessoa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            c.getLog().msgFalhaManter(ex.getMessage(), "Inclusão", c.getInstancia(), c.getUser());
            JOptionPane.showMessageDialog(c.getView(), ex.getMessage());
            Logger.getLogger(InclusaoPessoa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            c.getLog().msgFalhaManter(ex.getMessage(), "Inclusão", c.getInstancia(), c.getUser());
            JOptionPane.showMessageDialog(c.getView(), ex.getMessage());
            Logger.getLogger(InclusaoPessoa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            c.getLog().msgFalhaManter(ex.getMessage(), "Inclusão", c.getInstancia(), c.getUser());
            JOptionPane.showMessageDialog(c.getView(), ex.getMessage());
            Logger.getLogger(InclusaoPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
