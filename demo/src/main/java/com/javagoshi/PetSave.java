package com.javagoshi;

import java.io.IOException;

import tools.jackson.databind.ObjectMapper;

public class PetSave {
    private PetSave( ) {
        throw new AssertionError("Não instancie esta classe!");
    }
    private static ObjectMapper mapper = new ObjectMapper();
    public static  String salvar(Pet pet) throws IOException {
        String resultadoJson = mapper.writeValueAsString(pet);
        return resultadoJson;
    }

    public static Pet carregar(String jsonPet) throws IOException {
        Pet pet = mapper.readValue(jsonPet, Pet.class);
        return pet;
    }
}
