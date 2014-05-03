/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy.log;

import model.Pessoa;
import model.Usuario;

/**
 *
 * @author Isaac
 */
public interface ILog {

    void msgManter(String operacao, Pessoa contato, Usuario usuario);

    void msgFalhaManter(String msgFalha, String operacao, Pessoa contato, Usuario usuario);

    void msgImportacao(int sucesso, int incompleto, Usuario usuario);

    void msgExportacao(int quantidade, Usuario usuario);

    void msgFalhaImpExportacao(String msg,  Usuario usuario);
}
