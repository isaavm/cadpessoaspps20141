/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Pessoa;
import model.Usuario;

/**
 *
 * @author Isaac
 */
public class LogTXT implements Serializable, ILog {

    private final String diretorio = "log/log.txt";
    private File arq;
    private FormatLog formata;

    public LogTXT() {
        formata = new FormatLog();
        arq = new File(diretorio);
        if (!arq.exists()) {
            try {
                arq.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(LogTXT.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void msgManter(String operacao, Pessoa contato, Usuario usuario) {
        GregorianCalendar g = new GregorianCalendar();
        gravar(operacao + " do contato " + contato.getNome()
                + ", (" + formata.formataData(g.getTime()) + ", " + formata.formataHora(g.getTime()) + ", e " + usuario.getLogin() + ")");
    }

    @Override
    public void msgFalhaManter(String msgFalha, String operacao, Pessoa contato, Usuario usuario) {
        GregorianCalendar g = new GregorianCalendar();
        gravar("Ocorreu uma falha " + msgFalha + " ao realizar a " + operacao + " do contato " + contato.getNome()
                + ", (" + formata.formataData(g.getTime()) + ", " + formata.formataHora(g.getTime()) + ", e " + usuario.getLogin() + ")");
    }

    @Override
    public void msgImportacao(int sucesso, int incompleto, Usuario usuario) {
        GregorianCalendar g = new GregorianCalendar();
        gravar(String.valueOf(sucesso) + " contatos com sucesso, "
                + String.valueOf(incompleto) + " incompletos, (" + formata.formataData(g.getTime()) + ", " + formata.formataHora(g.getTime()) + ", e " + usuario.getLogin() + ")");
    }

    @Override
    public void msgExportacao(int quantidade, Usuario usuario) {
        GregorianCalendar g = new GregorianCalendar();
        gravar("Exportação realizada: " + String.valueOf(quantidade) + " contatos exportados. "
                + " (" + formata.formataData(g.getTime()) + ", " + formata.formataHora(g.getTime()) + ", e " + usuario.getLogin() + ")");
    }

    @Override
    public void msgFalhaImpExportacao(String msg, Usuario usuario) {
        GregorianCalendar g = new GregorianCalendar();
        gravar("Falha " + msg + " durante a importação (" + formata.formataData(g.getTime()) + ", " + formata.formataHora(g.getTime()) + ", e " + usuario.getLogin() + ")");
    }

    private void gravar(String msg) {
        FileWriter w;
        try {
            w = new FileWriter(arq, true);
            BufferedWriter b = new BufferedWriter(w);
            b.write(msg);
            b.newLine();
            b.close();
        } catch (IOException ex) {
            Logger.getLogger(LogTXT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
