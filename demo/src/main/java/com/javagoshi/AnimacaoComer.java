package com.javagoshi;

public class AnimacaoComer {

    private static final String LIMPAR_LINHA = "\r\u001B[K";
    private static final String ESCONDER_CURSOR = "\u001B[?25l";
    private static final String MOSTRAR_CURSOR = "\u001B[?25h";

   
    private static final String BICHINHO_BOCA_ABERTA = "(ºoº)";
    private static final String BICHINHO_MASTIGANDO = "(º-º)";

    public static void comer(char alimento, String rosto) {
        String BICHINHO_NORMAL = rosto;
        System.out.print(ESCONDER_CURSOR);
        try {
            // Alimento aparece à frente do bichinho
            quadro(BICHINHO_NORMAL, "  " + alimento + alimento + alimento, 600);

            // Bichinho abre a boca e o alimento se aproxima
            quadro(BICHINHO_BOCA_ABERTA, " " + alimento + alimento + alimento, 400);
            quadro(BICHINHO_BOCA_ABERTA, alimento + "" + alimento + alimento, 400);

            // Mordidas: o alimento vai sumindo
            quadro(BICHINHO_MASTIGANDO, " " + alimento + alimento, 300);
            quadro(BICHINHO_BOCA_ABERTA, " " + alimento + alimento, 300);
            quadro(BICHINHO_MASTIGANDO, " " + alimento, 300);
            quadro(BICHINHO_BOCA_ABERTA, " " + alimento, 300);
            quadro(BICHINHO_MASTIGANDO, "", 300);

            // Mastigando
            quadro(BICHINHO_BOCA_ABERTA, "", 250);
            quadro(BICHINHO_MASTIGANDO, "", 250);
            quadro(BICHINHO_BOCA_ABERTA, "", 250);
            quadro(BICHINHO_MASTIGANDO, "", 250);

            // Volta ao estado normal
            quadro(BICHINHO_NORMAL, "", 0);
            System.out.println();
        } finally {
            System.out.print(MOSTRAR_CURSOR);
            System.out.flush();
        }
    }

    private static void quadro(String bichinho, String comida, int duracaoMs) {
        System.out.print(LIMPAR_LINHA + bichinho + comida);
        System.out.flush();
        if (duracaoMs > 0) {
            try {
                Thread.sleep(duracaoMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}