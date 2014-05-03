/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pessoas.controller;

import java.util.Comparator;
import model.Pessoa;

/**
 *
 * @author Clayton
 */
public class ComparadorDeTelefoneCrescente implements Comparator<Pessoa> {

    @Override
    public int compare(Pessoa aPerson, Pessoa anotherPerson) {
        return aPerson.getTelefone().compareTo(anotherPerson.getTelefone());
    }
    
    
}