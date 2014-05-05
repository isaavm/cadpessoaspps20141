/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Isaac
 */
public class Operadora {

    private String operadora;
    private String[] ddd;
    private String[] banda;

    public Operadora(String operadora, String[] ddd, String[] banda) {
        this.operadora = operadora;
        this.ddd = ddd;
        this.banda = banda;
    }

    public String getOperadora() {
        return operadora;
    }

    public void setOperadora(String operadora) {
        this.operadora = operadora;
    }

    public String[] getDdd() {
        return ddd;
    }

    public void setDdd(String[] ddd) {
        this.ddd = ddd;
    }

    public String[] getBanda() {
        return banda;
    }

    public void setBanda(String[] banda) {
        this.banda = banda;
    }
}
