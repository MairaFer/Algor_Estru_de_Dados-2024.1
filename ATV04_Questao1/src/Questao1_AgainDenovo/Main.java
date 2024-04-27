package Questao1_AgainDenovo;

public class Main {

    public static void main(String[] args) {

        HashTable tabela = new HashTable();
        tabela.inserirDeArquivo("arquivo_50kb");

        System.out.println(tabela.buscar("c1yf8w4r6i0ytqnweudfenhh3gqceckgh844akpqipmd7enfx8"));
        
        System.out.println(tabela.buscar("c1yf8w4r6i0ytnweudfenhh3gqceckgh844akpqipmd7enfx8"));

    }

}
