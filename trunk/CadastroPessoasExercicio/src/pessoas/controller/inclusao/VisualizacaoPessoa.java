/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pessoas.controller.inclusao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pessoas.controller.InclusaoPessoaController;

/**
 *
 * @author Isaac
 */
public class VisualizacaoPessoa extends EstadoFrameInclusao {

    public VisualizacaoPessoa(InclusaoPessoaController c) {
        super(c);
        c.getView().getTxtNome().setText(c.getInstancia().getNome());
        c.getView().getTxtTelefone().setText(c.getInstancia().getTelefone());
        c.getView().getTxtNome().setEditable(false);
        c.getView().getTxtTelefone().setEditable(false);
        c.getView().setTitle("Visualizar contato");
        c.getView().getBtnFechar().setText("Fechar");
        c.getView().getBtnSalvar().setText("Editar");
        c.getView().getBtnFechar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fechar();
            }
        });
        c.getView().getBtnSalvar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                editar();
            }
        });
        c.getLog().msgManter("Consuta", c.getInstancia(), c.getUser());
    }

    @Override
    public void editar() {
        c.setEstado(new EdicaoPessoa(c));
    }
}
