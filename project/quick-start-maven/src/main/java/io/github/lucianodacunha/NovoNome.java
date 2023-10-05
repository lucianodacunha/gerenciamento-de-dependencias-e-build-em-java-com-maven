package io.github.lucianodacunha;

import com.github.javafaker.Faker;

public class NovoNome {
    private final static Faker faker = new Faker();

    public static String getNome(){
        return faker.name().fullName();
    }
}
