/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author elias
 */
public class Eleitor {
    
    private int id;
    private String nome;
    private String cpf;
    private int tituloEleitor;
    private String imagemRosto;
    private int secao;
    
    /**
     * Construtor sem parâmetro.
     */
    public Eleitor() {
        this("Sem nome", "Sem CPF", 0, "Sem foto", 0);
    }

    /**
     * Construtor com os seguintes parâmetros.
     * @param nome
     * @param cpf
     * @param tituloEleitor
     * @param imagemRosto
     * @param secao 
     */
    public Eleitor(String nome, String cpf, int tituloEleitor, String imagemRosto, int secao) {
        this.nome = nome;
        this.cpf = cpf;
        this.tituloEleitor = tituloEleitor;
        this.imagemRosto = imagemRosto;
        this.secao = secao;
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

    public int getTituloEleitor() {
        return tituloEleitor;
    }

    public void setTituloEleitor(int tituloEleitor) {
        this.tituloEleitor = tituloEleitor;
    }

    public String getImagemRosto() {
        return imagemRosto;
    }

    public void setImagemRosto(String imagemRosto) {
        this.imagemRosto = imagemRosto;
    }

    public int getSecao() {
        return secao;
    }

    public void setSecao(int secao) {
        this.secao = secao;
    }
     
}
