package com.javagoshi;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Menu.limparConsole();
        Scanner scanner = new Scanner(System.in);
        Pet petAtual = Menu.carregarArquivo();
        if(petAtual != null) {
            petAtual.simularTempo();
        }
        else {
            petAtual = Menu.criarPetSeNaoExiste(scanner);
        }
        
        char entrada;
        while(petAtual.getVivo()) {
            Menu.limparConsole();
            petAtual.desenharPet();
            System.out.println("Olá! me chamo " + petAtual.getNome() );
            System.out.println("Pressione:");
            System.out.println("A --> para alimentar");
            System.out.println("D --> para faze-ló dormir");
            System.out.println("S --> para Sair");
            System.out.println("Ainda não temos uma opção para o W, paciência!");
            entrada = scanner.next().charAt(0);

            switch (entrada) {
                case 'A':
                    Menu.limparConsole();
                    petAtual.desenharPet();
                    System.out.println("Escolha uma letra para o " + petAtual.getNome() + " comer:");
                    char letra = scanner.next().charAt(0);
                    Menu.limparConsole();
                    petAtual.comer(letra);
                    break;
                
                case 'D':
                    Menu.limparConsole();
                    petAtual.desenharPet();
                    System.out.println(" Vou Mimir! Pode pressionar ENTER.");
                    scanner.nextLine(); //Esse limpa o buffer da variavel entrada
                    scanner.nextLine(); //esse de fato faz a pausa pro usuario ler a mensagem
                    Menu.limparConsole();
                    petAtual.dormindo();
                    
                   
                    
                    
                    break;
                case 'S':
                    Menu.limparConsole();
                    Menu.salvarArquivo(petAtual);
                    System.exit(0);
                    break;
                default:
                    break;
            }
            Menu.limparConsole();
            
        }
        System.out.println("Seu Incompente Imundo! Você deixou ele morrer!");
        System.out.println(petAtual.getRostoAtual());
        System.out.println("GAME OVER");
        System.out.println("R.I.P \n Aqui jaz " + petAtual.getNome());
        petAtual.pararTempo();
        scanner.close();


    }
}