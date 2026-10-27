package com.javagoshi;
import java.io.IOException;

public class Menu {
    
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

}
