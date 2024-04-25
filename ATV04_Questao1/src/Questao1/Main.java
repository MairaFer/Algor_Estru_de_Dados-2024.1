package Questao1;

public class Main {
    public static void main(String[] args) {

        HashTable hashSetado = new HashTable();
        hashSetado.inserir("arroz");
        hashSetado.inserir("feijao");
        hashSetado.inserir("salada");
        hashSetado.inserir("batata");
        hashSetado.inserir("macaxeira");
        hashSetado.inserir("noodles");
        hashSetado.inserir("mexerica");
        hashSetado.inserir("linha");
        hashSetado.inserir("migalha");
        hashSetado.inserir("macumba");
        hashSetado.inserir("Maromba");
        hashSetado.inserir("Julho");
        hashSetado.inserir("Bumbum");

        hashSetado.imprimir();
        hashSetado.inserirDeArquivo("entradaQst1");
        hashSetado.imprimir();
        hashSetado.buscar("[2024-04-14 21:46:51.587] [info]  loader keycode-3653 true");
        hashSetado.remover("[2024-04-14 21:46:51.587] [info]  loader keycode-3653 true");
        hashSetado.buscar("[2024-04-14 21:46:51.587] [info]  loader keycode-3653 true");
    }

}