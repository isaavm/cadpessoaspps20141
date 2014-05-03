/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy.log;

import java.io.Serializable;
import java.util.GregorianCalendar;
import model.Pessoa;
import model.Usuario;
import mytxtlogger.MyTXTLogger;

/**
 *
 * @author Isaac
 */
public class LogTXT implements Serializable, ILog {

    private MyTXTLogger log;

    @Override
    public void msgManter(String operacao, Pessoa contato, Usuario usuario) {
        log = new MyTXTLogger();
        log.writeMessage(operacao + " do contato " + contato.getNome()
                + ", (" + GregorianCalendar.getInstance().getTime().toString() + ", e" + usuario.getLogin()+")");
    }

    @Override
    public void msgFalhaManter(String msgFalha, String operacao, Pessoa contato, Usuario usuario) {
        log = new MyTXTLogger();
        log.writeMessage("Ocorreu uma falha "+ msgFalha+" ao realizar a "+operacao + " do contato " + contato.getNome()
                + ", (" + GregorianCalendar.getInstance().getTime().toString() + ", e" + usuario.getLogin()+")");
    }

    @Override
    public void msgImportacao(int sucesso, int incompleto, Usuario usuario) {
            log = new MyTXTLogger();
        log.writeMessage(String.valueOf(sucesso)+" contatos com sucesso, "
                + String.valueOf(incompleto)+" incompletos, (" + GregorianCalendar.getInstance().getTime().toString() + ", e" + usuario.getLogin()+")");
}

    @Override
    public void msgExportacao(int quantidade, Usuario usuario) {
            log = new MyTXTLogger();
        log.writeMessage("Exportação realizada: "+String.valueOf(quantidade)+" contatos exportados. "
                + " (" + GregorianCalendar.getInstance().getTime().toString() + ", e" + usuario.getLogin()+")");
    }

    @Override
    public void msgFalhaImpExportacao(String msg, Usuario usuario) {
              log = new MyTXTLogger();
        log.writeMessage("Falha "+msg+" durante a importação (" + GregorianCalendar.getInstance().getTime().toString() + ", e" + usuario.getLogin()+")");
  }
}
