

# 🐣 Javagoshi

> **Um Tamagoshi (bichinho virtual) feito em Java puro para console.**

O **Javagoshi** simula um ciclo de vida real, onde o bichinho sente fome, sono e tem sua felicidade calculada em tempo real. O projeto foi criado como um laboratório prático para dominar conceitos avançados de Java, como **Programação Orientada a Objetos (POO)**, **concorrência (Threads e Schedulers)**, **persistência de dados** e **manipulação de terminal com códigos ANSI**.

---

## 🚀 Funcionalidades

- **Ciclo de vida completo:** Fome e sono aumentam com o tempo; a felicidade é calculada dinamicamente com base nesses fatores.
- **Persistência de dados:** O estado do bichinho é salvo automaticamente em `./SaveState/pet.json` e carregado ao iniciar, permitindo continuar de onde parou.
- **Simulação de tempo offline (Catch-up):** Ao carregar um save, o jogo calcula quanto tempo se passou desde o último acesso e atualiza os status do bichinho como se o tempo tivesse passado na vida real.
- **Animações no terminal:** Comer e dormir exibem animações em ASCII e códigos ANSI (com manipulação de cursor e limpeza de linha).
- **Interação via menu:** Alimente, faça dormir ou saia do jogo usando comandos simples no teclado.
- **Morte do Pet:** Se a fome ou o sono atingirem o máximo, o bichinho morre e uma tela de *Game Over* é exibida.

---

## 🛠️ Tecnologias Utilizadas

- **Java 17+** (linguagem principal)
- **Maven** (gerenciamento de dependências e build)
- **Jackson** (serialização e desserialização de JSON)
- **Ansi Escapes** (animações e manipulação de terminal)

---

## ⚙️ Pré-requisitos

- **JDK 17** ou superior instalado.
- **Maven** instalado (ou utilize o Maven Wrapper `./mvnw` incluso no projeto).
- Um terminal que suporte sequências de escape ANSI (Linux, macOS ou Windows Terminal).

---

## 🎮 Como Rodar

### Opção 1: Usando o Maven (recomendado)

Na raiz do projeto (onde está o `pom.xml`), execute:

```bash
mvn exec:java -Dexec.mainClass="com.javagoshi.Main"
```

> 💡 **Dica:** Se você não tiver o Maven instalado no sistema, use o wrapper incluso:
> ```bash
> ./mvnw exec:java -Dexec.mainClass="com.javagoshi.Main"
> ```

### Opção 2: Gerando um JAR executável

```bash
mvn clean package
java -jar target/demo-1.0-SNAPSHOT.jar
```

*(Caso o `pom.xml` não gere um JAR executável diretamente, use a Opção 1.)*

---

## 🕹️ Como Jogar

Após iniciar o jogo, siga as instruções no terminal:

- **A** → Alimentar o bichinho (digite uma letra para representar a comida).
- **D** → Colocar o bichinho para dormir (ele acorda sozinho após o sono zerar).
- **S** → Salvar e sair do jogo.

O progresso é salvo automaticamente ao sair. Na próxima vez que você abrir, o tempo que passou será simulado.

---

## 📂 Estrutura do Projeto

```
demo/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── javagoshi/
│   │   │           ├── AnimacaoComer.java   # Animação de alimentação
│   │   │           ├── Main.java            # Ponto de entrada e game loop
│   │   │           ├── Menu.java            # Menus, persistência e utilidades
│   │   │           ├── Pet.java             # Lógica do bichinho (estado e comportamento)
│   │   │           └── PetSave.java         # Serialização/desserialização com Jackson
│   │   └── resources/
│   └── test/
│       └── java/
│           └── Teste01.java                 # Testes manuais
├── SaveState/                               # Pasta gerada automaticamente (contém pet.json)
├── pom.xml
└── README.md
```

---

## 🧠 Aprendizados e Decisões Técnicas

- **Uso de `volatile` e `AtomicInteger`:** Para garantir a segurança de threads entre o *Scheduler* (que atualiza o tempo) e a *Thread Principal* (que lê os dados para desenhar a tela).
- **Encapsulamento:** Atributos `private` com getters e setters, mantendo a integridade dos dados.
- **Classes Utilitárias:** `Menu` e `PetSave` possuem construtores privados e métodos estáticos, seguindo o padrão *Utility Class*.
- **Persistência com Jackson:** Uso de anotações como `@JsonIgnore` para evitar a serialização de recursos não serializáveis (como o `ScheduledExecutorService`).
- **Simulação de tempo offline:** Cálculo da diferença entre `Instant` (momento atual) e `ultimoAcesso` salvo, com limite de 1 ano para evitar *loops* infinitos.
- **Tratamento de erros:** *Try/catch* para `IOException` e `JsonProcessingException`, com mensagens amigáveis no console.

---

## 📄 Licença

Este projeto é de código aberto e está disponível sob a licença **MIT**. Sinta-se livre para estudar, modificar e compartilhar.

---

## 👤 Autor

Desenvolvido por **Ruan** — estudante de TI apaixonado por infraestrutura, redes e sistemas.

Se este projeto te ajudou de alguma forma, deixe uma ⭐ no repositório!

---

### 🎯 Dica final

Para ver o *catch-up* em ação, abra o arquivo `SaveState/pet.json` e edite o campo `"ultimoAcesso"` para uma data de horas (ou dias) atrás. Ao abrir o jogo, o bichinho sentirá o tempo passado e você poderá ver a simulação de tempo offline funcionando!