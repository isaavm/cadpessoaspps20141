package pessoas.controller;

import java.sql.SQLException;
import model.Usuario;
import strategy.log.ILog;

public class ListaPessoasController extends ListaPessoas {

    public ListaPessoasController(ILog l, Usuario u) {
        super(l, u);
    }

    @Override
    protected void pesquisaByNome() throws ClassNotFoundException, SQLException {
        montarTabelaFiltrada(pessoas.getByNome(view.getCmFiltro().getText()));
    }

    @Override
    protected void pesquisaByTel() throws ClassNotFoundException, SQLException {
        montarTabelaFiltrada(pessoas.getByTelefone(view.getCmFiltro().getText()));
    }

    @Override
    protected void pesquisaByUf() throws ClassNotFoundException, SQLException {
        montarTabelaFiltrada(pessoas.getByUf(view.getCmFiltro().getText()));
    }

    @Override
    protected void pesquisaByOperadora() throws ClassNotFoundException, SQLException {
        montarTabelaFiltrada(pessoas.getByOperadora(view.getCmFiltro().getText()));
    }
}
