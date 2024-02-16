package gol;

import java.util.Objects;
import java.util.Random;

class Logica extends Main{
    Main main = new Main();
    int rows = main.height;
    int cols = main.width;
    int maxGenerations = main.generations;
    int frequency = main.speed;

    final String[] population = {main.population};

    public void mandarProSwing() {

        System.out.println(main.speed);
        if (population[0].equalsIgnoreCase("RND")) {
            population[0] = createRandomGrid(rows, cols);
        }

        population[0] = population[0].replaceAll("#", "\n").replaceAll("0", ".").replaceAll("1", "X");

        String[][] finalPopulation = transformarPopulacaoEmArray(population);

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
    private String[][] transformarPopulacaoEmArray(String[] population) {
        String[] populationArray = population[0].split("\n");
        String[][] finalPopulation = new String[1][rows];

        for (int i = 0; i < finalPopulation[0].length; i++) {
            finalPopulation[0][i] = ".";
            if (i < populationArray.length) finalPopulation[0][i] = populationArray[i];
            while (finalPopulation[0][i].length() < rows) finalPopulation[0][i] += ".";
        }
        return finalPopulation;
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
    private String createRandomGrid(int rows, int cols) {
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