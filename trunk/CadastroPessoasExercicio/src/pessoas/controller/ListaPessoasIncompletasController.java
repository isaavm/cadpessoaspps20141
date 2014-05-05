/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pessoas.controller;

import java.sql.SQLException;
import model.Usuario;
import strategy.log.ILog;

/**
 *
 * @author Isaac
 */
public class ListaPessoasIncompletasController extends ListaPessoas {

    public ListaPessoasIncompletasController(ILog l, Usuario u) {
        super(l, u);
    }

    @Override
    protected void pesquisaByNome() throws ClassNotFoundException, SQLException {
        montarTabelaFiltrada(pessoas.getByNomeInc(view.getCmFiltro().getText()));
    }

    @Override
    protected void pesquisaByTel() throws ClassNotFoundException, SQLException {
        montarTabelaFiltrada(pessoas.getByTelefoneInc(view.getCmFiltro().getText()));
    }

    @Override
    protected void pesquisaByUf() throws ClassNotFoundException, SQLException {
        montarTabelaFiltrada(pessoas.getByUfInc(view.getCmFiltro().getText()));
    }

    @Override
    protected void pesquisaByOperadora() throws ClassNotFoundException, SQLException {
        montarTabelaFiltrada(pessoas.getByOperadoraInc(view.getCmFiltro().getText()));
    }

}
