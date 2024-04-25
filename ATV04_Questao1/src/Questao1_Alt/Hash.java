package Questao1_Alt;

public class Hash{

  private int hashMetodo(String phrase){
    int hashValue=0;
    for(int i=0;i<phrase.length();i++){
      hashValue = (31 * hashValue + phrase.charAt(i));
    }
    
    return Math.abs(hashValue);
  }

  private int hashMetodo(String phrase, int colisao){
    int hashValue=0;

    for(int i=0;i<phrase.length();i++){
      hashValue = (((31 * hashValue)^i) + colisao ) + phrase.charAt(i);
    }
    return Math.abs(hashValue);
  }

  public int hashValor(String phrase, int tableSize){
    return hashMetodo(phrase)%tableSize;
  }

  public int hashValor(String phrase, int tableSize,int colisao){
    return hashMetodo(phrase,colisao)%tableSize;
  }
  


  public String[] reSize(String[] tabela){
    System.out.println("Re-sizING THE TABLE ");
    String[] novoTamanho = new String[tabela.length*2];
    int posicao=0;
    for(String phrase: tabela){
      if(phrase!=null){
        posicao=hashValor(phrase,novoTamanho.length);
        if(novoTamanho[posicao]==null){
          novoTamanho[posicao]=phrase;
        }else{
          novoTamanho = colisao(novoTamanho,phrase,1);
        }
      }
    }
    return novoTamanho;
  }

  public String[] colisao(String[] tabela, String palavra, int colisao){
    int posicao = 0;
      if(palavra != null){
        posicao=hashValor(palavra,tabela.length,colisao);
        if(tabela[posicao]==null){
          tabela[posicao]=palavra;
        }else{
          tabela = colisao(tabela,palavra,colisao+1);
        }
      }
    
    return tabela;
  }
}