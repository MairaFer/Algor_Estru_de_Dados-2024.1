package Questao1;

public class Hash {

    public int Hash(String phrase){
        int hashValue = 0;
        for (int i = 0; i < phrase.length(); i++) {
            hashValue = (phrase.charAt(i) * 31 + hashValue);
        }
        return hashValue;
    }

    public int hashValor(String phrase, int sizeArray) {
        return Hash(phrase) % sizeArray;
    }

    private void reHash(String[] tabela){
        for (String palavra : tabela) {
            if (palavra != null) {
                tabela[hashValor(palavra, tabela.length)] = palavra;
            }
        }
    }

    public String[] resize(String [] tabela) {
        String[] novoTamanho = new String[tabela.length * 2];
        reHash(novoTamanho);
        tabela = novoTamanho;
        return tabela;
    }
}