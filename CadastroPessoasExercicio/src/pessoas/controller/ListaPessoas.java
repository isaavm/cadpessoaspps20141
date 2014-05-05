/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pessoas.controller;

import dao.PessoaDAO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.Pessoa;
import model.Usuario;
import observer.Observer;
import pessoas.controller.listagem.EstadoFrameListagem;
import pessoas.controller.listagem.PessoaNaoSelecionada;
import strategy.log.ILog;
import view.ListaGenericaView;

/**
 *
 * @author Isaac
 */
public abstract class ListaPessoas implements Observer {

    protected ILog log;
    protected Usuario user;
    protected ListaGenericaView view;
    protected EstadoFrameListagem estado;
    protected PessoaDAO pessoas;
    protected DefaultTableModel tm;
    protected Pessoa pessoa;

    public ListaPessoas(ILog l, Usuario u) {
        this.log = l;
        this.user = u;
        this.pessoas = new PessoaDAO();
        pessoas.addObserver(this);
        this.pessoa = null;
        this.view = new ListaGenericaView();
        carregarComboboxPesquisar();
        configurarTabela();
        view.getTabela().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
                estado.selecionar();
            }
        });
        view.setVisible(true);
        setEstado(new PessoaNaoSelecionada(this));
    }

    public void carregaTableModel(Collection<Pessoa> c) {
        tm.setNumRows(0);
        Iterator<Pessoa> it = c.iterator();
        while (it.hasNext()) {
            Pessoa p = it.next();
            String pessoa = p.toString();
            String linha[] = pessoa.split(",");
            tm.addRow(new Object[]{linha[0], linha[1]});
        }
        this.view.getTabela().setModel(tm);
    }

    public void excluir() {
        int i = JOptionPane.showConfirmDialog(view, "Tem certeza que deseja excluir o registro?",
                "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
        if (i == JOptionPane.YES_OPTION) {
            try {
                pessoas.excluir(pessoa.getNome());
                log.msgManter("Exclusão", pessoa, user);
            } catch (SQLException ex) {
                log.msgFalhaManter(ex.getMessage(), "Exclusão", pessoa, user);
                JOptionPane.showMessageDialog(view, ex.getMessage());
                Logger.getLogger(ListaPessoasController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                log.msgFalhaManter(ex.getMessage(), "Exclusão", pessoa, user);
                JOptionPane.showMessageDialog(view, ex.getMessage());
                Logger.getLogger(ListaPessoasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        estado.desselecionar();
    }

    private void carregarComboboxPesquisar() {
        view.getCbFiltro().addItem("Nome");
        view.getCbFiltro().addItem("Telefone");
        view.getCbFiltro().addItem("Operadora");
        view.getCbFiltro().addItem("Uf");
    }

    private void configurarTabela() {
        tm = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Nome", "Telefone", "UF", "Operadora"}) {

                    @Override
                    public boolean isCellEditable(int row, int col) {
                        return false;
                    }
                };
        view.getTabela().setRowSorter(new TableRowSorter(tm));
        view.getTabela().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    protected abstract void pesquisaByNome() throws ClassNotFoundException, SQLException;

    protected abstract void pesquisaByTel() throws ClassNotFoundException, SQLException;

    protected abstract void pesquisaByUf() throws ClassNotFoundException, SQLException;

    protected abstract void pesquisaByOperadora() throws ClassNotFoundException, SQLException;

    protected void montarTabelaFiltrada(List<Pessoa> ret) {
        Iterator<Pessoa> it = ret.iterator();
        this.tm.setRowCount(0);
        while (it.hasNext()) {
            Pessoa p = it.next();
            tm.addRow(new Object[]{p.getNome(), p.getTelefone(), p.getUf(), p.getOperadora()});
        }
        view.getTabela().setModel(tm);
        setEstado(new PessoaNaoSelecionada(this));
    }

    public void visualizar() throws IOException, ClassNotFoundException {
        new InclusaoPessoaController(pessoa, log, user);
    }

    public void pesquisar() throws ClassNotFoundException, SQLException {
        String param = view.getCbFiltro().getSelectedItem().toString().toLowerCase();//pesquisar com minuscula
        if (param.equals("nome")) {
            pesquisaByNome();
        } else if (param.equals("telefone")) {
            pesquisaByTel();
        } else if (param.equals("uf")) {
            pesquisaByUf();
        } else if (param.equals("operadora")) {
            pesquisaByOperadora();
        }
    }

    public EstadoFrameListagem getEstado() {
        return estado;
    }

    public void setEstado(EstadoFrameListagem estado) {
        this.estado = estado;
    }

    public PessoaDAO getPessoas() {
        return pessoas;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public ListaGenericaView getView() {
        return view;
    }

    public DefaultTableModel getTm() {
        return tm;
    }

    @Override
    public void update() {
        try {
            pesquisar();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
            Logger.getLogger(ListaPessoasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
            Logger.getLogger(ListaPessoasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
