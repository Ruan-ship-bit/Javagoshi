package com.javagoshi;

import tools.jackson.databind.ObjectMapper;

public class PetSave {
    private ObjectMapper mapper = new ObjectMapper();
    public String salvar(Pet pet) {
        String resultadoJson = mapper.writeValueAsString(pet);
        return resultadoJson;
    }

    public Pet carregar(String jsonPet) {
        Pet pet = mapper.readValue(jsonPet, Pet.class);
        return pet;
    }
}
