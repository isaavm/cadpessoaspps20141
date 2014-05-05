/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario.controller;

import dao.UsuarioDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Usuario;
import usuario.inclusao.EstadoFrameInclusaoUsuario;
import usuario.inclusao.InclusaoUsuario;
import usuario.inclusao.VisualizacaoUsuario;
import view.InclusaoUsuarioView;

/**
 *
 * @author Isaac
 */
public class InclusaoUsuarioController {

    private Usuario instancia;
    private UsuarioDAO pessoas;
    private String nomeAntigo;
    private EstadoFrameInclusaoUsuario estado;
    private InclusaoUsuarioView view;

    public InclusaoUsuarioController(Usuario inst) {
        this.instancia = inst;
        this.pessoas = new UsuarioDAO();
        this.view = new InclusaoUsuarioView();
        if (instancia == null) {
            estado = new InclusaoUsuario(this);
        } else {
            estado = new VisualizacaoUsuario(this);
        }
        view.setVisible(true);
    }

    public Usuario getInstancia() {
        return instancia;
    }

    public void setInstancia(Usuario instancia) {
        this.instancia = instancia;
    }

    public UsuarioDAO getPessoas() {
        return pessoas;
    }

    public void setPessoas(UsuarioDAO pessoas) {
        this.pessoas = pessoas;
    }

    public String getNomeAntigo() {
        return nomeAntigo;
    }

    public void setNomeAntigo(String nomeAntigo) {
        this.nomeAntigo = nomeAntigo;
    }

    public EstadoFrameInclusaoUsuario getEstado() {
        return estado;
    }

    public void setEstado(EstadoFrameInclusaoUsuario estado) {
        this.estado = estado;
    }

    public InclusaoUsuarioView getView() {
        return view;
    }

    public void setView(InclusaoUsuarioView view) {
        this.view = view;
    }

    public void cancelarEdicao() throws ClassNotFoundException, SQLException {
        Usuario aux = pessoas.getUsuario(nomeAntigo);
        instancia = aux;
    }

    public void salvar() throws IOException, SQLException, ClassNotFoundException, Exception {
        String nome = view.getCmLogin().getText();
        String senha = view.getCmSenha().getText();
        String outrasenha = view.getCmRepSenha().getText();
        if (isDadosValidos(nome, senha, outrasenha)) {
            Usuario p = new Usuario(nome, senha);
            p.setAdm(false);
            if (!pessoas.addUsuario(p)) {
                throw new Exception("Usuario já existente");
            } else {
                this.instancia = p;
                JOptionPane.showMessageDialog(view, nome + " cadastrado com sucesso!");
                view.getCmLogin().setText("");
                view.getCmSenha().setText("");
                view.getCmRepSenha().setText("");
            }
        }
    }

    public void editar() throws IOException, ClassNotFoundException, SQLException, Exception {
        String nome = view.getCmLogin().getText();
        String senha = (String) view.getCmSenha().getText();
        String outrasenha = view.getCmRepSenha().getText();
        if (isDadosValidos(nome, senha, outrasenha)) {
            Usuario p = new Usuario(nome, senha);
            pessoas.modificaUsuario(p, nomeAntigo);
            this.instancia = p;
            JOptionPane.showMessageDialog(view, "Editado com sucesso!");
        }
    }

    private boolean isDadosValidos(String nome, String senha, String repSenha) {
        boolean ret = false;
        if ((!nome.equals("")) && (senha != null)) {
            if (nome.length() <= 8) {
                if (senha.length() >= 3) {
                    if (senha.equals(repSenha)) {
                        ret = true;
                    } else {
                        JOptionPane.showMessageDialog(view, "As senhas não conferem");
                    }
                } else {
                    JOptionPane.showMessageDialog(view, "A senha é muito curta!");
                }
            } else {
                JOptionPane.showMessageDialog(view, "O tamanho de usuário é muito grande!");
            }
        } else {
            JOptionPane.showMessageDialog(view, "Você precisa informar os campos");

        }
        return ret;
    }
}
