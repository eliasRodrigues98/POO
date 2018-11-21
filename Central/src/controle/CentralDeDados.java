/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

//import java.awt.List;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import model.bean.Candidato;
import model.bean.Deputado;
import model.bean.Eleitor;
import model.bean.Partido;
import model.bean.Voto;
import model.dao.PartidoDAO;
import dao.PartDAO;
import java.util.Iterator;

/**
 *
 * @author elias
 */
public class CentralDeDados {
    
    //private Partido partido = new Partido();
    //private PartidoDAO partidos = new PartidoDAO();
    private List<Partido> partido = new ArrayList<>();
    private List<Eleitor> eleitor = new ArrayList<>();
    private List<Candidato> candidato = new ArrayList<>();
    private List<Deputado> deputado = new ArrayList<>();
    private List<Voto> votos = new ArrayList<>();
    private String arqEleitor; 
    private String arqPartido;
    private String arqCandidato;
    private String arqVotos;

    /**
     * Construtur sem parâmetro.
     */
    public CentralDeDados() {
        this("Não tem", "Não tem", "Não tem", "Não tem");
    }

    /**
     * Construtor com os seguintes parâmetros.
     * @param arqEleitor
     * @param arqPartido
     * @param arqCandidato 
     */
    public CentralDeDados(String arqEleitor, String arqPartido, String arqCandidato, String arqVotos) {
        this.arqEleitor = arqEleitor;
        this.arqPartido = arqPartido;
        this.arqCandidato = arqCandidato;
        this.arqVotos = arqVotos;
        this.importarDadosIniciais(arqEleitor, arqCandidato, arqPartido);
    }

    /**
     * Método para cadastrar os eleitores em uma lista não ordenada, inserindo 
     * sempre na última posição. Foram usados os parâmetros.
     * @param tituloEleitor
     * @param nome
     * @param cpf
     * @param imagemRosto
     * @param secao
     * @return 
     */
    public boolean cadastrarEleitor(String nome, String cpf, int tituloEleitor, String imagemRosto, int secao) {
        if (this.digitoVerifCPF(cpf)) {
            for (Eleitor eleitor1 : eleitor) {
                if (eleitor1 != null) {
                    if(eleitor1.getCpf().equals(cpf) || (eleitor1.getImagemRosto().equals(imagemRosto))) {
                        System.out.println("igual");
                        return false;
                    }
                } else {
                    //System.out.println("aqui");
                    Eleitor eleit = new Eleitor();
                    eleit.setCpf(cpf);
                    eleit.setImagemRosto(imagemRosto);
                    eleit.setNome(nome);
                    eleit.setSecao(secao);
                    eleit.setTituloEleitor(tituloEleitor);
                    eleitor.add(eleit);
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Método para cadastrar candidatos verificando a validação do cpf e a 
     * existência do partido.
     * @param estado
     * @param nome
     * @param cpf
     * @param numero
     * @param tipo
     * @param partidoPolitico
     * @return 
     */
    public boolean cadastrarCandidato(String estado, String nome, String cpf, int numero, int tipo, String partidoPolitico) {
        if (this.digitoVerifCPF(cpf)) {
            Partido partidos = new Partido();
            partidos = this.getPartido(partidoPolitico);
            if (partidos != null) {
                for (Candidato candidato1 : candidato) {
                    if (candidato1 != null) {
                        if (candidato1.getCpf().equals(cpf)) {
                            JOptionPane.showMessageDialog(null, "EERO: Candidato cadastrado.", 
                                                          "Alerta", JOptionPane.WARNING_MESSAGE);
                            return false;
                        } else if (candidato1.getNumero() == numero) {
                            JOptionPane.showMessageDialog(null, "ERRO: Número já está cadastrado no sistema",
                                                          "Alerta", JOptionPane.WARNING_MESSAGE);
                            return false;
                        }
                    } else {
                        if (tipo == 1) {
                            Candidato cand = new Candidato();
                            cand.setCpf(cpf);
                            cand.setNome(nome);
                            cand.setNumero(numero);
                            cand.setPartido(partidos);
                            cand.setTipo(tipo);
                            candidato.add(cand);
                        } else if (tipo == 2) {
                            Deputado depu = new Deputado();
                            depu.setCpf(cpf);
                            depu.setEstado(estado);
                            depu.setNome(nome);
                            depu.setNumero(numero);
                            depu.setPartido(partidos);
                            depu.setTipo(tipo);
                            deputado.add(depu);
                        }
                        return true;
                    }
                } 
            } else {
                JOptionPane.showMessageDialog(null, "Partido inexistente", "Alerta", 
                                              JOptionPane.WARNING_MESSAGE);
            }
        }
        return false;
    }
    
    /**
     * Verifica se o cpf informado é um cpf válido.
     * @param cpf
     * @return 
     */
    public boolean digitoVerifCPF(String cpf) {
        if (expressaoRegCPF(cpf)) {
            int soma1 = (cpf.charAt(0)*10) + (cpf.charAt(1)*9) + (cpf.charAt(2)*8)
                         +(cpf.charAt(4)*7) + (cpf.charAt(5)*6) + (cpf.charAt(6)*5)
                         +(cpf.charAt(8)*4) + (cpf.charAt(9)*3) + (cpf.charAt(10)*2);
            int resto1 = (soma1 * 10 ) % 11;
            
            if (resto1 == 10) {
                resto1 = 0;
            }
            
            if (resto1 == cpf.charAt(1)) {
                int soma2 = (cpf.charAt(0)*11) + (cpf.charAt(1)*10) + (cpf.charAt(2)*9)
                             + (cpf.charAt(4)*8) + (cpf.charAt(5)*7) + (cpf.charAt(6)*6)
                             + (cpf.charAt(8)*5) + (cpf.charAt(9)*4) + (cpf.charAt(10)*3)
                             + (cpf.charAt(12)*2);
                int resto2 = (soma2 * 10) % 11;
                
                if (resto2 == 10) {
                    resto2 = 0;
                }
                            
                if (resto2 == cpf.charAt(0)) {
                    return true;
                } 
            }
            return true;
        }
        return false;
    }
    
    /**
     * Valida cpf com expressão regular.
     * @param cpf
     * @return 
     */
    public boolean expressaoRegCPF(String cpf) {
        if (cpf != null) {
            return cpf.matches("[0-9]{3}+[.]{1}+[0-9]{3}+[.]{1}+[0-9]{3}+[-]{1}+[0-9]{2}");
        }
        return false;
    }
    
    /**
     * Método para pegar o nome do partido político.
     * @param partidoPolitico
     * @return 
     */
    public Partido getPartido(String partidoPolitico) {
        for (Partido partido1 : partido) {
            if (partido1 != null) {
                if (partido1.getNome().equals(partidoPolitico)) {
                    return partido1;
                }
            }
        }
        return null;
    }
    
    /**
     * Método para verificar o número do candidato.
     * @param numero
     * @return 
     */
    public int getCandidato(int numero) {
        for (Candidato candidato1 : candidato) {
            if (candidato1 != null) {
                if(candidato1.getNumero() == numero) {
                    return candidato1.getNumero();
                }
            }
        }
        return -1;
    }
    
    /**
     * Método para cadastar os partidos com os parâmetros de nome, número
     * e sigla.
     * @param nome
     * @param numero
     * @param sigla
     * @return 
     */
    public boolean cadastrarPartidos(String nome, int numero, String sigla) {
        System.out.println("Partido1");
        
        for (Iterator<Partido> iterator = partido.iterator(); iterator.hasNext();) {
            Partido next = iterator.next();
        
            System.out.println("Partido2");
            if (next != null) {
                System.out.println("Partido3");
                Partido pt = new Partido(nome, numero, sigla);
                if (next.equals(pt)) {
                    return false;
                } else {
                    //partidos.inserir(nome, numero, sigla);
                    return true;
                }
                
            }
        }
        /* 
        for (Partido partido1 : partido) {
            if (partido1 != null) {
                if (partido1.getNome().equals(nome) || (partido1.getNumero() == numero)
                        || (partido1.getSigla().equals(sigla))) {
                    System.out.println("igual");
                    return false;
                }
            } else {
                partidos.inserir(nome, numero, sigla);
                /*
                Partido part = new Partido();
                part.setNome(nome);
                part.setNumero(numero);
                part.setSigla(sigla);
                partido.add(part);
                
                return true;
            }
        }*/
        return false;
    }
	
    /**
     * Método para checar o número do partido.
     * @param numero
     * @return 
     */
    public Partido getPartido(int numero) {
        for (Partido partido1 : partido) {
            if (partido1 != null) {
                if (partido1.getNumero() == numero) {
                    return partido1;
                }
            }
        }
        return null;
    }
    
    /**
     * Método toString personalizado.
     * @return 
     */
    @Override
    public String toString() {
        String r = "";
        for (Partido partido1 : partido) {
            if(partido1 !=null) {
                r += partido1.toString();
            }
        }
        return r;
    }
    /*
    public Candidato relacaoDeCandidatos() {
        return candidato;
    }
    */
    public void relacaoEleitoresUrna(int urna) {
        int qtdVotosRealizados = 0;
        for (Voto votos1 : votos) {
            if (votos1 != null) {
                if(votos1.getSecao() == urna) {
                    qtdVotosRealizados++;
                }
            }
        }
        JOptionPane.showMessageDialog(null, qtdVotosRealizados+" Votos recebidos na Seção/Urna "+urna);
    }
	
    public void setVotos(Eleitor eleitor, int secao, Date hora, Candidato candidato) {
        Voto v = new Voto(eleitor, candidato, secao, hora);
        for (Voto votos1 : votos) {
            if (votos1 == null) {
                v.setCandidato(candidato);
                v.setEleitor(eleitor);
                v.setHoraVoto(hora);
                v.setSecao(secao);
                votos1 = v;
                return;
            }
        }
    }
    
    public boolean buscarVotosUrna() {
        Gson gson = new Gson();
        Voto voto = new Voto();
        try {
            FileReader reader;
            reader = new FileReader(arqVotos);
            BufferedReader leitor = new BufferedReader(reader);
            
            String linha = leitor.readLine(); // lê a primeira linha
            while (linha != null) {
                voto = gson.fromJson(linha, Voto.class);
                setVotos(voto.getEleitor(), voto.getSecao(), voto.getHoraVoto(), voto.getCandidato());
                linha = leitor.readLine(); // lê da segunda até a última linha
            }
            leitor.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    
    public void enviarDados() {
        if (eleitoresToJson(this.arqEleitor) && partidosToJson(this.arqPartido) && candidatosToJson(this.arqCandidato)) {
            JOptionPane.showMessageDialog(null, "Dados enviados", "Informação", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "ERRO: Dados não enviados", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }
	
    public int[] mostrarResultadosEleicao() {
        int votoPorCandidato[] = new int[50];
        for (Voto voto1 : votos) {
            int posicao = this.getCandidato(voto1.getCandidato().getNumero());
            if(posicao != -1) {
                votoPorCandidato[posicao]++;
            }
        }
        return votoPorCandidato;
    }
    
    public boolean partidosToJson(String nomeArq) {
    	/**
         * Exporta para um arquivo todos os dados dos partidos, no modelo json.
         */
        Gson gson = new Gson();
        try {
            /**
             * Escreve Json convertido em arquivo chamado "file.json".
             */
            FileWriter writer = new FileWriter(nomeArq);
            for (Partido partido1 : partido) {
                    if(partido1 != null) {
                        String aux = gson.toJson(partido1);
                        System.out.println(aux);
                        writer.write(aux);
                        writer.write("\n");
                    }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean candidatosToJson(String nomeArq) {
        /**
         * Exporta para um arquivo todos os dados dos candidatos, no modelo json.
         */
        Gson gson = new Gson();
        try {
            /**
             * Escreve Json convertido em arquivo chamado "file.json".
             */
            FileWriter writer = new FileWriter(nomeArq);
            for (Candidato candidato1 : candidato) {
                if (candidato1 != null) {
                    String aux = gson.toJson(candidato1);
                    System.out.println(aux);
                    writer.write(aux);
                    writer.write("\n");//Não sei se pode ter esse \n
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
	
    public boolean eleitoresToJson(String nomeArq) {
        /**
         * Exporta para um arquivo todos os dados dos eleitores, no modelo json.
         */
        Gson gson = new Gson();
        try {
            /**
             * Escreve Json convertido em arquivo chamado "file.json".
             */
            FileWriter writer = new FileWriter(nomeArq);
            for (Eleitor eleitor1 : eleitor) {
                if (eleitor1 != null) {
                    String aux = gson.toJson(eleitor1);
                    // System.out.println(aux);
                    writer.write(aux);
                    writer.write("\n");//Não sei se pode ter esse \n
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    
    public boolean importarDadosIniciais(String arqEleitor, String arqCandidato, String arqPartido) {
        /**
         * Importa de um arquivo todos os dados dos candidatos, eleitores e 
         * partidos que já estavam cadastrados, no modelo json.
         */
        if (!importarEleitores(arqEleitor) || !importarPartidos(arqPartido) || !importarCandidatos(arqCandidato)) {
            return false;
        }
        
        return true;
    }

    private boolean importarCandidatos(String nomeArq) {
        Gson gson = new Gson();
        Candidato candidato = new Candidato();
        Deputado deputado = new Deputado();
        try {
            FileReader reader;
            reader = new FileReader(nomeArq);
            BufferedReader leitor = new BufferedReader(reader);

            String linha = leitor.readLine(); // lê a primeira linha
            while (linha != null) {
                candidato = gson.fromJson(linha, Candidato.class);
                cadastrarCandidato(null, candidato.getNome(), candidato.getCpf(), candidato.getNumero(), candidato.getTipo(), candidato.getPartido().getNome());
                linha = leitor.readLine(); // lê da segunda até a última linha
            }
            
            leitor.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private boolean importarPartidos(String nomeArq) {
        Gson gson = new Gson();
        Partido partido = new Partido();
        try {
            FileReader reader;
            reader = new FileReader(nomeArq);
            BufferedReader leitor = new BufferedReader(reader);

            String linha = leitor.readLine(); // lê a primeira linha
            while (linha != null) {
                partido = gson.fromJson(linha, Partido.class);
                cadastrarPartidos(partido.getNome(), partido.getNumero(), partido.getSigla());
                linha = leitor.readLine(); // lê da segunda até a última linha
            }
            
            leitor.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public boolean importarEleitores(String nomeArq) {
        Gson gson = new Gson();
        Eleitor eleitor = new Eleitor();
        try {
            FileReader reader;
            reader = new FileReader(nomeArq);
            BufferedReader leitor = new BufferedReader(reader);

            String linha = leitor.readLine(); // lê a primeira linha
            while (linha != null) {
                eleitor = gson.fromJson(linha, Eleitor.class);
                cadastrarEleitor(eleitor.getNome(), eleitor.getCpf(), eleitor.getTituloEleitor(), eleitor.getImagemRosto(), eleitor.getSecao());
                linha = leitor.readLine(); // lê da segunda até a última linha
            }

            leitor.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    
}
