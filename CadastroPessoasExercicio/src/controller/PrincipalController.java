/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Usuario;
import org.jdom2.JDOMException;
import persistence.xml.ExportarContatosXML;
import persistence.xml.ImportarContatosXML;
import pessoas.controller.InclusaoPessoaController;
import pessoas.controller.ListaPessoasController;
import pessoas.controller.ListaPessoasIncompletasController;
import usuario.controller.InclusaoUsuarioController;
import usuario.controller.ListagemUsuarioController;
import view.MainView;

/**
 *
 * @author Isaac
 */
public class PrincipalController {

    private MainView view;
    private LogController log;
    private Usuario user;

    public PrincipalController(Usuario u) throws IOException, ClassNotFoundException, SQLException {
        user = u;
        log = new LogController();
        view = new MainView();
        view.getMenuAdd().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                novo();
            }
        });
        view.getMenuListar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                listar();
            }
        });
        view.getMenuContatoIncompleto().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                listaContatosIncompletos();
            }
        });
        view.getMenuImportar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                importarXml();
            }
        });
        view.getMenuExportar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                exportarXml();
            }
        });
        view.getMenuLog().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mudarLog();
            }
        });
        view.getMenuContatoIncompleto().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                listaIncompletos();
            }
        });
        if (!this.user.isAdm()) {
            view.getSuperMenuUsuario().setEnabled(false);
            view.getSuperMenuUsuario().setVisible(false);
        } else {
            view.getMenuAddUser().addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    addUser();
                }
            });
            view.getMenuListaUser().addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    listaUser();
                }
            });
        }
        view.setVisible(true);
    }

    private void mudarLog() {
        this.log.exibirConfiguracoes();
    }

    private void addUser() {
        new InclusaoUsuarioController(null);
    }
private void listaIncompletos(){
    new ListaPessoasIncompletasController(log.getLog(), user);
}
    private void listaContatosIncompletos() {
        new ListaPessoasIncompletasController(log.getLog(), user);
    }

    private void listaUser() {
        new ListagemUsuarioController();
    }

    private void importarXml() {
        try {
            ImportarContatosXML im = new ImportarContatosXML(view,log.getLog(),user);
            log.getLog().msgImportacao(im.qtdSucesso(), im.qtdIncomp(), user);
        } catch (IOException ex) {
            log.getLog().msgFalhaImpExportacao(ex.getMessage(), user);
            JOptionPane.showMessageDialog(view, ex.getMessage());
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JDOMException ex) {
            log.getLog().msgFalhaImpExportacao(ex.getMessage(), user);
            JOptionPane.showMessageDialog(view, ex.getMessage());
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            log.getLog().msgFalhaImpExportacao(ex.getMessage(), user);
            JOptionPane.showMessageDialog(view, ex.getMessage());
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            log.getLog().msgFalhaImpExportacao(ex.getMessage(), user);
            JOptionPane.showMessageDialog(view, ex.getMessage());
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void exportarXml() {
        try {
            int quant = new ExportarContatosXML(view).getQuantidade();
            log.getLog().msgExportacao(quant, user);
        } catch (SQLException ex) {
            log.getLog().msgFalhaImpExportacao(ex.getMessage(), user);
            JOptionPane.showMessageDialog(view, ex.getMessage());
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            log.getLog().msgFalhaImpExportacao(ex.getMessage(), user);
            JOptionPane.showMessageDialog(view, ex.getMessage());
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            log.getLog().msgFalhaImpExportacao(ex.getMessage(), user);
            JOptionPane.showMessageDialog(view, ex.getMessage());
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void novo() {
        new InclusaoPessoaController(null, log.getLog(), user);
    }

    private void listar() {
        new ListaPessoasController(log.getLog(), user);

    }

}
