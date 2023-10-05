package org.example;

import com.github.javafaker.Faker;

public class NovoNome {
    private static final Faker faker = new Faker();

    public static String novoNome(){
        return faker.name().fullName();
    }
}