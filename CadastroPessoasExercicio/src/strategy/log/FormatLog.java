/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy.log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Isaac
 */
public class FormatLog {

    SimpleDateFormat formData;
    SimpleDateFormat formHora;

    public FormatLog() {
        formData = new SimpleDateFormat("dd/MM/yyyy");
        formHora = new SimpleDateFormat("hh:mm:ss");
    }
    
    public String formataHora(Date d){
        return formHora.format(d);
    }
    
    public String formataData(Date d){
        return formData.format(d);
    }

}
