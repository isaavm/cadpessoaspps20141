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
public class EdicaoPessoa extends EstadoFrameInclusao {

    public EdicaoPessoa(InclusaoPessoaController c) {
        super(c);
        c.setNomeAntigo(c.getView().getTxtNome().getText());
        c.getView().getTxtNome().setEditable(true);
        c.getView().getTxtTelefone().setEditable(true);
        c.getView().setTitle("Editar contato");
        c.getView().getBtnFechar().setText("Cancelar");
        c.getView().getBtnSalvar().setText("Salvar");
        c.getView().getBtnFechar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        });
        c.getView().getBtnSalvar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                editar();
            }
        });
    }

    @Override
    public void editar() {
        try {
            c.editar();
            c.getLog().msgManter("Edição", c.getInstancia(), c.getUser());
            c.setEstado(new VisualizacaoPessoa(c));
        } catch (IOException ex) {
            c.getLog().msgFalhaManter(ex.getMessage(), "Edição", c.getInstancia(), c.getUser());
            JOptionPane.showMessageDialog(c.getView(), ex.getMessage());
            Logger.getLogger(EdicaoPessoa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            c.getLog().msgFalhaManter(ex.getMessage(), "Edição", c.getInstancia(), c.getUser());
            JOptionPane.showMessageDialog(c.getView(), ex.getMessage());
            Logger.getLogger(EdicaoPessoa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            c.getLog().msgFalhaManter(ex.getMessage(), "Edição", c.getInstancia(), c.getUser());
            JOptionPane.showMessageDialog(c.getView(), ex.getMessage());
            Logger.getLogger(EdicaoPessoa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            c.getLog().msgFalhaManter(ex.getMessage(), "Edição", c.getInstancia(), c.getUser());
            JOptionPane.showMessageDialog(c.getView(), ex.getMessage());
            Logger.getLogger(EdicaoPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void cancelar() {
        try {
            c.cancelarEdicao();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(c.getView(), ex.getMessage());
            Logger.getLogger(EdicaoPessoa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(c.getView(), ex.getMessage());
            Logger.getLogger(EdicaoPessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        c.setEstado(new VisualizacaoPessoa(c));
    }
}
