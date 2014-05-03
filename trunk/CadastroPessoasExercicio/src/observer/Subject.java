/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package observer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Isaac
 */
public abstract class Subject {
    List<Observer> observers;

    public Subject() {
    this.observers = new ArrayList<Observer>();
    }
    
    public final void addObserver(Observer o){
        observers.add(o);
    }
    
    public final void removeObserver(Observer o){
        observers.remove(o);
    }
    
    public void notifica(){
        for(Observer o : this.observers){
            o.update();
        }
    }
}
