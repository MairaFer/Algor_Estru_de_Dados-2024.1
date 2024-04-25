package Questao1_Alt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HashTable{
  private int colisao = 0;
  private int tamAtual=0;
  private String[] tabela = new String[10];

  Hash hash = new Hash();

  public void inserirDeArquivo(String path) {
    String palavra = "";
    try (BufferedReader br = new BufferedReader(new FileReader("src\\Questao1\\Entrada\\"+path+".txt"))) {
      while (br.ready()) {
        palavra = br.readLine();
        inserir(palavra);
      }
    } catch (IOException e) {
      System.out.println("Arquivo não encontrado");
    }
  }

  public void inserir(String phrase){
    int posicao;
    int hashValue = hash.hashValor(phrase, tabela.length);

    if((double)tamAtual/tabela.length >= 0.75){tabela = hash.reSize(tabela);}
    if(tabela[hashValue] == null){
      tabela[hashValue] = phrase;
    }else{
      colisao++;
      posicao = hash.hashValor(phrase,tabela.length,colisao);
      if(tabela[posicao] == null){
        colisao=colisao/2;
        tabela[posicao] = phrase;
      }else{
        inserir(phrase);
      }
    }
  }

  public void imprimir(){
    for(String palavra: tabela){
      System.out.println(palavra);
    }
  }
  
}