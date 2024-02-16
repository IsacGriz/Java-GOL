package gol;

import java.util.Objects;
import java.util.Random;

class Display {
    public void mandarProSwing() {
        int linhas = Main.height;
        int colunas = Main.width;
        int maximoGeracoes = Main.generations;
        int frequencia = Main.speed;
        String[] populacaoEmArray = {Main.population};

        if (populacaoEmArray[0].equalsIgnoreCase("RND")) {
            populacaoEmArray[0] = criarGridAleatorio(linhas, colunas);
        }

        String[][] populacaoEmMatriz = transformarPopulacaoEmMatriz(populacaoEmArray, linhas);

        populacaoEmArray[0] = "";

        final GolGenerator generator = new GolGenerator() {
            @Override
            public String getNextGenerationAsString(long generation) {
                populacaoEmArray[0] = "";

                for (int i = 0; i < populacaoEmMatriz[0].length; i++) {
                    for (int j = 0; j < populacaoEmMatriz[0][i].length(); j++) {
                        populacaoEmArray[0] += verificarVizinhos(populacaoEmMatriz[0], i, j);
                    }
                    populacaoEmArray[0] += "\n";
                }

                populacaoEmMatriz[0] = populacaoEmArray[0].split("\n");

                return populacaoEmArray[0];
            }
        };

        SwingRenderer.render(generator, new GolSettings(linhas, colunas,frequencia, maximoGeracoes));
    }
    private String[][] transformarPopulacaoEmMatriz(String[] populacaoEmArray, int linhas) {
        String[] populacaoEmArrayArray = populacaoEmArray[0].split("\n");
        String[][] populacaoEmMatriz = new String[1][linhas];

        for (int i = 0; i < populacaoEmMatriz[0].length; i++) {
            populacaoEmMatriz[0][i] = ".";
            if (i < populacaoEmArrayArray.length) populacaoEmMatriz[0][i] = populacaoEmArrayArray[i];
            while (populacaoEmMatriz[0][i].length() < linhas) populacaoEmMatriz[0][i] += ".";
        }
        return populacaoEmMatriz;
    }
    private String verificarVizinhos(String[] matriz, int linha, int coluna) {
        String vizinho = "";

        for (int i = linha - 1; i <= linha + 1; i++) {
            for (int j = coluna - 1; j <= coluna + 1; j++) {
                if (i >= 0 && i < matriz.length && j >= 0 && j < matriz[0].length() && (i != linha || j != coluna)) {
                    if (Objects.equals(matriz[i].charAt(j), 'X')) vizinho += matriz[i].charAt(j);
                }
            }
        }

        if (vizinho.length() > 3 || vizinho.length() < 2) return ".";
        else if (vizinho.length() == 3) return "X";
        return String.valueOf(matriz[linha].charAt(coluna));
    }
    private String criarGridAleatorio(int linhas, int colunas) {
        var random  = new Random();
        String populacaoEmArray = "";

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                populacaoEmArray +=  random.nextBoolean()? "X": ".";
            }
            populacaoEmArray +=  "\n";
        }

        return populacaoEmArray;
    }
}