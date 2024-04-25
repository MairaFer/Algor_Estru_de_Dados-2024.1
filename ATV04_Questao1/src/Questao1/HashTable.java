package Questao1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HashTable {
    private int colisao=1;
    private int tamAtual = 0;
    private String[] tabela = new String[10];
    Hash hash = new Hash();

    public void inserirDeArquivo(String path) {
        String palavra = "";
        try (BufferedReader br = new BufferedReader(new FileReader("src\\Questao1\\Entrada\\"+path+".txt"))) {
            while (br.ready()) {
                if ((double) tamAtual / tabela.length >= 0.75) {
                    tabela = hash.resize(tabela);
                }
                palavra = br.readLine();
                inserir(palavra);
            }
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado");
        }
    }

    public void inserir(String palavra) {
        if ((double) tamAtual / tabela.length >= 0.85) {
            tabela = hash.resize(tabela);
        }
        int hashedValue = hash.hashValor(palavra, tabela.length);
        if (tabela[hashedValue] == null) {
            tabela[hashedValue] = palavra;
            tamAtual++;
        } else {
            inserir(hashedValue,palavra);
        }
    }

    public void inserir(int posicao, String palavra) {
        if(posicao>=tabela.length){
            colisao =(int)Math.log((double) colisao);
            colisao+=1;
            inserir(colisao,palavra);
        } else {
            if (tabela[posicao] == null) {
                tabela[posicao] = palavra;
                tamAtual++;
            }else {
                inserir(posicao + colisao, palavra);
            }
        }
    }

    public void remover(String phrase) {
        int posi = hash.hashValor(phrase, tabela.length);
        System.out.println("A palavra procurada: " + tabela[posi] + " está na posição: " + posi);
        tabela[posi] = null;
    }

    public void buscar(String phrase) {
        int posi = hash.hashValor(phrase, tabela.length);
        System.out.println("A palavra procurada: " + tabela[posi] + " está na posição: " + posi);
    }

    public void imprimir() {
        for (String linha : tabela) {
            System.out.println(linha);
        }
        System.out.println("Foram registrados: " + tamAtual);
    }

}
