/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.LogDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import strategy.log.ILog;
import strategy.log.LogTXT;
import strategy.log.LogXML;
import view.ConfigLogView;

/**
 *
 * @author Isaac
 */
public class LogController {

    private ILog log;
    private ConfigLogView view;
    private LogDAO dao;

    public LogController() throws SQLException, ClassNotFoundException {
        dao = new LogDAO();
        view = new ConfigLogView();
        carregarListaLogs();
        defineLog();
        view.getBtnCancelar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        });
        view.getBtnSalvar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                salvar();
            }
        });
    }

    private void carregarListaLogs() {
        view.getCbListaLogs().addItem("txt");
        view.getCbListaLogs().addItem("xml");
    }

    private void salvar() {
        String fil = view.getCbListaLogs().getSelectedItem().toString().toLowerCase();
        try {
            dao.trocarLog(fil);
            defineLog();
            view.dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
            Logger.getLogger(LogController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
            Logger.getLogger(LogController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void cancelar() {
        view.dispose();
    }

    private void defineLog() throws SQLException, ClassNotFoundException {
        String ls = dao.recuperarLog();
        if (ls.equals("txt")) {
            log = new LogTXT();
            view.getCbListaLogs().setSelectedItem("txt");
        } else {
            log = new LogXML();
            view.getCbListaLogs().setSelectedItem("xml");
        }
    }

    public void exibirConfiguracoes() {
        view.setVisible(true);
    }

    public ILog getLog() {
        return log;
    }
}
