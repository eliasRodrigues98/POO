/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import model.bean.Eleitor;
import model.bean.Candidato;
import java.util.Date;

/**
 *
 * @author elias
 */
public class Voto {
    
    private int id;
    private Eleitor eleitor;
    private Candidato candidato;
    private int secao;
    private Date horaVoto;

    /**
     * Construtor com os seguintes parâmetros.
     * @param eleitor
     * @param candidato
     * @param secao
     * @param horaVoto 
     */
    public Voto(Eleitor eleitor, Candidato candidato, int secao, Date horaVoto) {
        this.eleitor = eleitor;
        this.candidato = candidato;
        this.secao = secao;
        this.horaVoto = horaVoto;
    }

    /**
     * Construtor sem parâmetros.
     */
    public Voto() {
        this(null, null, 0, null);
    }

    /**
     * Getters e setters.
     * @return 
     */
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public Eleitor getEleitor() {
        return this.eleitor;
    }

    public Candidato getCandidato() {
        return this.candidato;
    }

    public int getSecao() {
        return this.secao;
    }

    public Date getHoraVoto() {
        return this.horaVoto;
    }
    
    public void setEleitor(Eleitor eleitor) {
        this.eleitor = eleitor;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public void setSecao(int secao) {
        this.secao = secao;
    }

    public void setHoraVoto(Date horaVoto) {
        this.horaVoto = horaVoto;
    }
    
}
