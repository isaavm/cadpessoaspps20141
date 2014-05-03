/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pessoas.controller;

import java.util.Comparator;
import model.Pessoa;

/**
 *
 * @author Isaac
 */

public class ComparadorDeTelefoneDecrescente implements Comparator<Pessoa> {

    @Override
    public int compare(Pessoa aPerson, Pessoa anotherPerson) {
        return anotherPerson.getTelefone().compareTo(aPerson.getTelefone());
    }
}