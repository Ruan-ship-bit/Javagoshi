package com.javagoshi;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
public class Menu {
   private Menu() {

    throw new AssertionError("Não instancie esta classe!");
   }
    
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

    public static void salvarArquivo (Pet pet) {
        Path diretorio = Paths.get("./SaveState");
        Path caminhoArquivo = diretorio.resolve(pet.getNome() + ".json");
        
            try {
            // 1. Garante que a pasta existe (não faz nada se já existir)
            Files.createDirectories(diretorio);
            
            // 2. Serializa o objeto
            String conteudo = PetSave.salvar(pet);
            
            // 3. Escreve o arquivo (cria se não existir, sobrescreve se existir)
            Files.writeString(caminhoArquivo, conteudo);
            
        } catch (IOException e) {
            // 4. Tratamento único de erro
            System.err.println("Erro crítico ao salvar o jogo: " + e.getMessage());
            // Aqui você pode lançar um alerta para o jogador ou tomar outra atitude
        }
    }

    public Pet carregarArquivo(String nome) {
        Path diretorio = Paths.get("./SaveState");
        Path caminhoArquivo = diretorio.resolve(nome + ".json");
        if(Files.exists(caminhoArquivo)) {
            try {
                String conteudo = Files.readString(caminhoArquivo);
                return PetSave.carregar(conteudo);
            } catch (Exception e) {
               System.err.println("Erro ao carregar o save (arquivo corrompido?): " + e.getMessage());
            }
        }
        return null;
    }

}
