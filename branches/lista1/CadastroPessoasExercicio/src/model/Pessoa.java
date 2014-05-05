package model;

import java.io.Serializable;
import java.util.Observable;

public class Pessoa extends Observable implements Comparable<Pessoa>, Serializable {

    private String nome;
    private String telefone;
    private String uf;
    private String operadora;

    public void setNome(String nome) {
        this.nome = nome;
        setChanged();
        notifyObservers();
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
        setChanged();
        notifyObservers();
    }

    public String getOperadora() {
        return operadora;
    }

    public void setOperadora(String operadora) {
        this.operadora = operadora;
        setChanged();
        notifyObservers();
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
        setChanged();
        notifyObservers();
    }

    public Pessoa(String nome, String telefone, String uf, String operadora) {
        this.nome = nome;
        this.telefone = telefone;
        this.uf = uf;
        this.operadora = operadora;
        setChanged();
        notifyObservers();

    }

    public Pessoa(String pNome, String pTelefone) {
        this.nome = pNome;
        this.telefone = pTelefone;
        this.uf = null;
        this.operadora = null;
        setChanged();
        notifyObservers();
    }

    public String getNome() {
        return this.nome;
    }

    public String getTelefone() {
        return this.telefone;
    }

    @Override
    public String toString() {
        return "" + this.nome
                + "," + this.telefone;
    }

    @Override
    public int compareTo(Pessoa o) {
        return telefone.compareTo(o.telefone);
    }
}
