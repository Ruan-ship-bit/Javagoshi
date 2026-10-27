package com.javagoshi;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.Files;
import java.nio.file.Path;
public class Menu {
    PetSave  petSave = new PetSave();
    public static void limparConsole() {
        try {
            // Detecta o sistema operacional
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                // No Windows, limpa chamando o CMD com o comando 'cls'
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // No Linux / macOS, chama o comando 'clear'
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            // Trata eventuais falhas na execução do processo externo
            Thread.currentThread().interrupt();
            System.out.println("Não foi possível limpar o console.");
        }
    }

    public  void salvarArquivo (Pet pet) {
        Path diretorio = Paths.get("./SaveState");
        Path caminhoArquivo = diretorio.resolve(pet.getNome() + ".json");
        String conteudo = petSave.salvar(pet);
        try {
            if(Files.notExists(diretorio)) {
                Files.createDirectories(diretorio);
                Files.writeString(caminhoArquivo, conteudo, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            }
            else {
                try {
                    Files.writeString(caminhoArquivo, conteudo);

                } catch (IOException e) {
                    System.err.println("Ocorreu um erro ao manipular o arquivo: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Ocorreu um erro ao manipular o arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Pet carregarArquivo(String nome) {
        Path diretorio = Paths.get("./SaveState");
        Path caminhoArquivo = diretorio.resolve(nome + ".json");
        if(Files.exists(caminhoArquivo)) {
            try {
                String conteudo = Files.readString(caminhoArquivo);
                return petSave.carregar(conteudo);
            } catch (IOException e) {
               System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            }
        }
        return null;
    }

}
