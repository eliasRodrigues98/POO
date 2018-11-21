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
public class Partido {
    
    private int id;
    private String nome;
    private int numero;
    private String sigla;

    /**
     * Construtor sem parâmetro.
     */
    public Partido() {
        this("Sem nome", 0, "Sem sigla");
    }

    /**
     * Construtor com os seguintes parâmentros.
     * @param nome
     * @param numero
     * @param sigla 
     */
    public Partido(String nome, int numero, String sigla) {
        this.nome = nome;
        this.numero = numero;
        this.sigla = sigla;
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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
    /**
     * Override do método toString para uma mensagem personalizada.
     * @return 
     */
    @Override
    public String toString() {
        return "Nome: " + this.getNome() + "\nSigla: " + this.getSigla() +
                "\nNúmero: " + this.getNumero();
    }
    
    /**
     * Método equals com algumas mudanças para verificar se possui os valores
     * necessários e retornar se são ou não iguais.
     * @param p
     * @return 
     */
    public boolean equals(Partido p) {
        if(p != null) { 
            return false;
        } else { 
            return ((this.numero == p.getNumero()) && (this.nome.equals(p.nome)) && 
                    (this.sigla.equals(p.sigla)));
        }
    }
}
