package Questao1_AgainDenovo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HashTable {
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;


    Lista<Lista<String>> elementos;
    int max;
    int qtdElementos;

    public HashTable() {
        qtdElementos = 0;
        elementos = new Lista<>();
        max = INITIAL_CAPACITY;
        iniciaLista(elementos);
    }

    private void iniciaLista(Lista<Lista<String>> elementos) {
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            elementos.inserir(new Lista<>(), i);
        }
    }

    private int gerarPosicao(String frase) {
        return Math.abs(frase.hashCode()) % max;
    }

    public void inserir(String chave) {
        if ((double) quantidadeElementos() / elementos.tamanho() >= LOAD_FACTOR_THRESHOLD) {
            resize();
        }
        int posicao = gerarPosicao(chave);
        Lista<String> lista = elementos.buscar(posicao);
        lista.inserir(chave);
        qtdElementos++;
    }


    public void inserirDeArquivo(String path) {
        // String palavra = "";
        try (BufferedReader br = new BufferedReader(new FileReader("src\\Entrada\\" + path + ".txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                inserir(linha);
            }
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado");
        }
    }

    private int quantidadeElementos() {
        int quantidade = 0;
        for (int i = 0; i < elementos.tamanho(); i++) {
            Lista<String> atualLista = elementos.buscar(i);
            quantidade += atualLista.tamanho();
        }
        return quantidade;
    }

    public void imprimir() {

        for (int i = 0; i < elementos.tamanho(); i++) {
            Lista<String> atual = elementos.buscar(i);
            System.out.println("\nLista: " + i + "- ");
            atual.exibir();
        }
    }

    public boolean buscar(String frase) {
        int posicao = gerarPosicao(frase);
        Lista<String> listaElemento = elementos.buscar(posicao);
        int hashFrase = frase.hashCode();
        for (int i = 0; i < listaElemento.tamanho(); i++) {
            if (listaElemento.buscar(i).hashCode() == hashFrase) {
                System.out.println(listaElemento.buscar(i));
                return true;
            }
        }
        return false;
    }

    public void resize() {
        String frase = "";

        Lista<Lista<String>> novaLista = new Lista<>();
        Lista<Lista<String>> antigaLista = elementos;

        max = max + max / 2;
        iniciaLista(novaLista);
        elementos = novaLista;

        for (int i = 0; i < antigaLista.tamanho(); i++) {
            Lista<String> atual = antigaLista.buscar(i);

            for (int j = 0; j < atual.tamanho(); j++) {
                frase = atual.buscar(j);
                if (frase != null) {
                    inserir(frase);
                }

            }
        }
    }

    public String buscarOriginal(String chave) {
        int posicao = gerarPosicao(chave);
        Lista<String> listaElemento = elementos.buscar(posicao);
        int hashChave = chave.hashCode();
        for (int i = 0; i < listaElemento.tamanho(); i++) {
            if (listaElemento.buscar(i).hashCode() == hashChave) {
                return listaElemento.buscar(i);
            }
        }
        return null;
    }


}
