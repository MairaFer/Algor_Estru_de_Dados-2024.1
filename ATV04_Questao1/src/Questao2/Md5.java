package Questao2;

import java.io.*;

import Questao1_AgainDenovo.*;

public class Md5 {

    public static String calcularHash(String mensagem) {
        System.out.println("Mensagem antes do padding: " + mensagem);
        int[] s = { 0x67452301, 0xEFCDAB89, 0x98BADCFE, 0x10325476 };
        int[] k = new int[64];
        for (int i = 0; i < 64; i++) {
            k[i] = (int) (long) ((1L << 32) * Math.abs(Math.sin(i + 1)));
        }

        byte[] mensagemBytes = mensagem.getBytes();
        int mensagemBits = mensagemBytes.length * 8;

        mensagem += (char) 0b10000000;

        while ((mensagem.length() + 64) % 512 != 0) {
            mensagem += (char) 0;
        }

        String tamanho = Integer.toBinaryString(mensagemBits);
        while (tamanho.length() < 64) {
            tamanho = "0" + tamanho;
        }
        mensagem += tamanho;

        System.out.println("Mensagem após o padding: " + mensagem);

        for (int i = 0; i < mensagem.length() / 512; i++) {
            int[] palavras = new int[16];
            for (int j = 0; j < 16; j++) {
                String palavraBin = mensagem.substring(i * 512 + j * 32, i * 512 + (j + 1) * 32);

                palavras[j] = Integer.parseUnsignedInt(palavraBin, 2);
            }

            int[] a = s.clone();

            for (int j = 0; j < 64; j++) {
                int f, g;
                if (j < 16) {
                    f = (a[1] & a[2]) | (~a[1] & a[3]);
                    g = j;
                } else if (j < 32) {
                    f = (a[3] & a[1]) | (~a[3] & a[2]);
                    g = (5 * j + 1) % 16;
                } else if (j < 48) {
                    f = a[1] ^ a[2] ^ a[3];
                    g = (3 * j + 5) % 16;
                } else {
                    f = a[2] ^ (a[1] | ~a[3]);
                    g = (7 * j) % 16;
                }

                int temp = a[3];
                a[3] = a[2];
                a[2] = a[1];
                a[1] += Integer.rotateLeft((a[0] + f + k[j] + palavras[g]), 7);
                a[0] = temp;
            }

            for (int j = 0; j < 4; j++) {
                s[j] += a[j];
            }
        }

        StringBuilder hash = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            hash.append(String.format("%08x", s[i]));
        }
        return hash.toString();
    }


    public static void main(String[] args) {
        HashTable tabela = new HashTable();

        try {
            BufferedReader br = new BufferedReader(new FileReader("Entrada1.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("Saida1.txt"));

            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println("Calculando hash para a linha: " + linha);
                String hash = calcularHash(linha); // Calcula o hash da linha
                System.out.println("Hash calculado: " + hash);
                tabela.inserir(linha);
                bw.write(hash);
                bw.newLine();
            }

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader("Entrada2.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("Saida2.txt"));

            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println("Buscando original para o hash: " + linha);
                String original = tabela.buscarOriginal(linha);
                System.out.println("Original encontrado: " + original);
                bw.write(original);
                bw.newLine();
            }

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
