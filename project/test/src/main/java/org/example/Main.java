package org.example;

import io.github.lucianodacunha.NovoNome;

public class Main {
    public static void main(String[] args) {
        var nome = NovoNome.getNome();
        System.out.printf("Nome: %s", nome);
    }
}