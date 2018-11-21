/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;
import model.bean.Partido;

/**
 *
 * @author elias
 */
public class PartDAO {
    
    private final List<Partido> partidos;
    private Partido p;
    
    public PartDAO() {
        partidos = new ArrayList<>();
    }
    
    public void inserir(String nome, int numero, String sigla) {
        p = new Partido(nome, numero, sigla);
        partidos.add(p);
    }
    
    public Partido buscar(int numero) {
        return partidos.get(numero);
    }
    
    public Partido remover(int numero) {
        return partidos.remove(numero);
    }
    
    public void alterar(String nome, int numero, String sigla) {
        p = new Partido(nome, numero, sigla);
        partidos.replaceAll((UnaryOperator<Partido>) p);
    }
    
    
}
