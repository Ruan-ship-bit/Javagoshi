package com.javagoshi;

import java.io.IOException;

import tools.jackson.databind.ObjectMapper;

public class PetSave {
    private ObjectMapper mapper = new ObjectMapper();
    public String salvar(Pet pet) throws IOException {
        String resultadoJson = mapper.writeValueAsString(pet);
        return resultadoJson;
    }

    public Pet carregar(String jsonPet) throws IOException {
        Pet pet = mapper.readValue(jsonPet, Pet.class);
        return pet;
    }
}
