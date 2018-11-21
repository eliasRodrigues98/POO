/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import model.bean.Candidato;
import model.bean.Partido;

/**
 *
 * @author elias
 */
public class Deputado extends Candidato {
    
    private String estado;

    /**
     * Construtor sem parâmetro.
     */
    public Deputado() {
        this("Sem estado");
    }
    
    /**
     * Construtor com o seguinte parâmetro.
     * @param estado 
     */
    public Deputado(String estado) {
        this.estado = estado;
    }

    /**
     * Construtor referente aos parâmetros da classe pai.
     * @param estado
     * @param nome
     * @param cpf
     * @param numero
     * @param tipo
     * @param partido 
     */
    public Deputado(String estado, String nome, String cpf, int numero, int tipo, Partido partido) {
        super(nome, cpf, numero, tipo, partido);
        this.estado = estado;
    }

    /**
     * Getter e setter.
     * @return 
     */
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
