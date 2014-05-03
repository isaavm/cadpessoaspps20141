/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario.controller;

import dao.UsuarioDAO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.Usuario;
import usuario.listagem.EstadoFrameListagemUsuario;
import usuario.listagem.UsuarioNaoSelecionado;
import view.ListaGenericaView;

/**
 *
 * @author Isaac
 */
public class ListagemUsuarioController {

    private Usuario usuario;
    private ListaGenericaView view;
    private UsuarioDAO dao;
    private EstadoFrameListagemUsuario estado;
    private DefaultTableModel tm;

    public ListagemUsuarioController() {
        this.usuario = null;
        this.dao = new UsuarioDAO();
        this.view = new ListaGenericaView();
        this.view.getCbFiltro().setVisible(false);
        this.estado = new UsuarioNaoSelecionado(this);
        configurarTabela();
        view.getTabela().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                estado.selecionar();
            }
        });
        view.setVisible(true);

    }

    private void configurarTabela() {
        tm = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Login"}) {

                    @Override
                    public boolean isCellEditable(int row, int col) {
                        return false;
                    }
                };
        view.getTabela().setRowSorter(new TableRowSorter(tm));
        view.getTabela().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void carregaTableModel(Collection<Usuario> c) {
        tm.setNumRows(0);
        Iterator<Usuario> it = c.iterator();
        while (it.hasNext()) {
            Usuario p = it.next();
            String pessoa = p.getLogin();
            tm.addRow(new Object[]{pessoa});
        }
        this.view.getTabela().setModel(tm);
    }

    public void pesquisar() throws ClassNotFoundException, SQLException {
        montarTabelaFiltrada(dao.getByFiltro(view.getCmFiltro().getText()));
    }

    private void montarTabelaFiltrada(List<Usuario> ret) {
        Iterator<Usuario> it = ret.iterator();
        this.tm.setRowCount(0);
        while (it.hasNext()) {
            Usuario p = it.next();
            tm.addRow(new Object[]{p.getLogin()});
        }
        view.getTabela().setModel(tm);
        setEstado(new UsuarioNaoSelecionado(this));
    }

    public void visualizar() throws IOException, ClassNotFoundException {
        new InclusaoUsuarioController(this.usuario);
    }

    public void setEstado(EstadoFrameListagemUsuario estado) {
        this.estado = estado;
    }
    
    public void excluir() throws SQLException, ClassNotFoundException {
        int i = JOptionPane.showConfirmDialog(view, "Tem certeza que deseja excluir o registro?",
                "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
        if (i == JOptionPane.YES_OPTION) {
 
                dao.excluir(usuario.getLogin());

        }
        estado.desselecionar();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ListaGenericaView getView() {
        return view;
    }

    public void setView(ListaGenericaView view) {
        this.view = view;
    }

    public UsuarioDAO getDao() {
        return dao;
    }

    public void setDao(UsuarioDAO dao) {
        this.dao = dao;
    }

    public DefaultTableModel getTm() {
        return tm;
    }

    public void setTm(DefaultTableModel tm) {
        this.tm = tm;
    }
    
}
