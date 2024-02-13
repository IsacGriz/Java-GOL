package gol;

import java.util.Objects;
import java.util.Random;

class Logica {
    public static void main(String[] args) {
        int rows = Integer.parseInt(args[0].substring(9, args[0].length() - 1));
        int cols = Integer.parseInt(args[1].substring(10, args[1].length() - 1));
        int maxGenerations = Integer.parseInt(args[2].substring(15, args[2].length() - 1));
        int frequency = Integer.parseInt(args[3].substring(9, args[3].length() - 1));

        final String[] population = {args[4].substring(15, args[4].length() - 2)};

        if (population[0].equalsIgnoreCase("RND")) {
            population[0] = createRandomGrid(rows, cols);
        }

        population[0] = population[0].replaceAll("#", "\n").replaceAll("0", ".").replaceAll("1", "X");

        String[] populationArray = population[0].split("\n");
        String[][] finalPopulation = new String[1][rows];

        for (int i = 0; i < finalPopulation[0].length; i++) {
            finalPopulation[0][i] = ".";
            if (i < populationArray.length) finalPopulation[0][i] = populationArray[i];
            while (finalPopulation[0][i].length() < rows) finalPopulation[0][i] += ".";
        }

        population[0] = "";

        final GolGenerator generator = new GolGenerator() {
            @Override
            public String getNextGenerationAsString(long generation) {
                population[0] = "";

                for (int i = 0; i < finalPopulation[0].length; i++) {
                    for (int j = 0; j < finalPopulation[0][i].length(); j++) {
                        population[0] += verificarVizinhos(finalPopulation[0], i, j);
                    }
                    population[0] += "\n";
                }

                finalPopulation[0] = population[0].split("\n");

                return population[0];
            }
        };

        SwingRenderer.render(generator, new GolSettings(rows, cols,frequency, maxGenerations));
    }
    public static String verificarVizinhos(String[] matriz, int linha, int coluna) {
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
    static String createRandomGrid(int rows, int cols) {
        var random  = new Random();
        String population = "";

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                population +=  random.nextBoolean()? "X": ".";
            }
            population +=  "\n";
        }

        return population;
    }
}