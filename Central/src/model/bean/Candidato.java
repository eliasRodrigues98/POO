/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import model.bean.Partido;

/**
 *
 * @author elias
 */
public class Candidato {
    
    private int id;
    private String nome;
    private String cpf;
    private int numero;
    private int tipo;
    private Partido partido;

    /**
     * Construtor sem parâmetro.
     */
    public Candidato() {
        this("Sem nome", "Sem CPF", 0, 0, null);
    }

    /**
     * Construtor com os seguintesparâmetros.
     * @param nome
     * @param cpf
     * @param numero
     * @param tipo
     * @param partido 
     */
    public Candidato(String nome, String cpf, int numero, int tipo, Partido partido) {
        this.nome = nome;
        this.cpf = cpf;
        this.numero = numero;
        this.tipo = tipo;
        this.partido = partido;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }
    
}
