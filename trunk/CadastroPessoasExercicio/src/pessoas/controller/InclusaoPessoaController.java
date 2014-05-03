package pessoas.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Pessoa;
import pessoas.controller.inclusao.EstadoFrameInclusao;
import pessoas.controller.inclusao.InclusaoPessoa;
import pessoas.controller.inclusao.VisualizacaoPessoa;
import dao.PessoaDAO;
import model.Usuario;
import strategy.log.ILog;
import view.InclusaoPessoaView;

public final class InclusaoPessoaController {

    private ILog log;
    private Usuario user;
    private PessoaDAO pessoas;
    private Pessoa instancia;
    private String nomeAntigo;
    private EstadoFrameInclusao estado;
    private InclusaoPessoaView view;

    @SuppressWarnings("empty-statement")// por que a ide recomendou isso?
    public InclusaoPessoaController(Pessoa inst, ILog l, Usuario us) {
        this.log = l;
        this.user = us;
        this.pessoas = new PessoaDAO();
        this.instancia = inst;
        view = new InclusaoPessoaView();
        if (instancia == null) {
            estado = new InclusaoPessoa(this);;
        } else {
            estado = new VisualizacaoPessoa(this);
        }
        view.setVisible(true);
    }

    public InclusaoPessoaView getView() {
        return view;
    }

    public void salvar() throws IOException, SQLException, ClassNotFoundException, Exception {
        String nome = view.getTxtNome().getText();
        String telefone = (String) view.getTxtTelefone().getValue();
        if (isDadosValidos(nome, telefone)) {
            Pessoa p = new Pessoa(nome, telefone);
            if (!pessoas.add(p)) {
                throw new Exception("Pessoa já existente");
            } else {
                this.instancia = p;
                JOptionPane.showMessageDialog(view, nome + " cadastrado com sucesso!");
                view.getTxtNome().setText("");
                view.getTxtTelefone().setText("");
            }
        }
    }

    public void editar() throws IOException, ClassNotFoundException, SQLException, Exception {
        String nome = view.getTxtNome().getText();
        String telefone = (String) view.getTxtTelefone().getValue();
        if (isDadosValidos(nome, telefone)) {
            Pessoa p = new Pessoa(nome, telefone);
            pessoas.editar(nomeAntigo, p);
            this.instancia = p;
            JOptionPane.showMessageDialog(view, "Editado com sucesso!");
        }
    }

    private boolean isDadosValidos(String nome, String telefone) throws Exception {
        if ((!nome.equals("")) && (telefone != null)) {
            if (nome.contains(" ")) {
                return true;
            } else {
                JOptionPane.showMessageDialog(view, "É necessário pelo menos um sobre nome para o caboco");
                throw new Exception("É necessário pelo menos um sobre nome para o caboco");
            }
        } else {
            throw new Exception("\"Você precisa informar os campos");
        }
    }

    public void setEstado(EstadoFrameInclusao estado) {
        this.estado = estado;
    }

    public Pessoa getInstancia() {
        return instancia;
    }

    public ILog getLog() {
        return log;
    }

    public Usuario getUser() {
        return user;
    }

    public void setInstancia(Pessoa instancia) {
        this.instancia = instancia;
    }

    public String getNomeAntigo() {
        return nomeAntigo;
    }

    public void setNomeAntigo(String nomeAntigo) {
        this.nomeAntigo = nomeAntigo;
    }

    public void cancelarEdicao() throws ClassNotFoundException, SQLException {
        Pessoa aux = pessoas.getPessoaByName(nomeAntigo);
        instancia = aux;
    }
}
