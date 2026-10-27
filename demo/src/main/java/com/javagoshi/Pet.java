package com.javagoshi;
import java.time.LocalDate;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Pet {
    private String nome = "";
    private boolean vivo = true;
    private boolean dormindo = false;
    private LocalDate birthday = LocalDate.now();
    AtomicInteger segundos = new AtomicInteger(0);
    @JsonIgnore  ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private volatile double taxaSono = 0;
    private volatile double taxaFome = 0;
    private volatile double taxaFeliz = 100;

    public  Pet() { 
        iniciarTimer();
    }//Construtor Vazio para o jackson
    public boolean getAcordado() {
        return this.dormindo;
    }

    public boolean getVida() {
        return this.vivo;
    }

    public LocalDate getNiver() {
        return this.birthday;
    }

    public  double getSono(){
        return this.taxaSono;
    }

    public  double getFome(){
        return this.taxaFome;
    }

    public  double getFelicidade(){
        return this.taxaFeliz;
    }

    public void calcularFelicidade() {
        this.taxaFeliz = 100 - (0.3*this.taxaFome + 0.1*this.taxaSono + 0.6* (this.taxaFome * this.taxaSono / 100));
    }

    public void desenharPet () {
        
        if(this.taxaFeliz >= 100) {
            System.out.println("(ºwº)");
        }
        else if(this.taxaFeliz >= 50) {
            System.out.println("(º-º)");
        }
        else if(this.taxaFeliz >= 25) {
            System.out.println("(º/º)");
        }
        else if(this.taxaFeliz >= 10) {
            System.out.println("(T-T)");
        }
        else {
            System.out.println("(ToT)");
        }

        if (this.taxaFome > 60) {
            System.out.println("To com fome e quero merendar");
        }

        if (this.taxaSono > 60) {
            System.out.println("Quero mimir!");
        }

        
    }

    public void animacaoDormindo () {
        Menu.limparConsole();
        System.out.println("(-.-)");
        while(this.dormindo) {
            
            System.out.print("Z");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.print("Z");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.print("Z");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.print("\u001B[3D   \u001B[3D");
            System.out.flush();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void iniciarTimer() {
        
        this.scheduler =  Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {
            int tempoAtual = this.segundos.getAndIncrement();
             if (tempoAtual > 0 && tempoAtual % 60 == 0) {
                this.taxaFome += 0.33;
                this.taxaSono += 0.20;
                this.calcularFelicidade();
                
            }
        }, 0, 1, TimeUnit.SECONDS);
    }
    
    public Pet (String nome ) {
        this.nome = nome;
        this.vivo = true;
        this.birthday = LocalDate.now();
        this.dormindo = false;
        this.taxaFeliz = 100;
        this.taxaFome = 0;
        this.taxaSono = 0;
        this.iniciarTimer();
        this.segundos = new AtomicInteger(0);
    }
    
    
        
        
}
