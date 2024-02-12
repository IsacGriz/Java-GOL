import java.util.Arrays;

class Logica {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        String teste = args[4].substring(15);
        String[] testeArray = teste.split("#");

        int[][] matriz = new int[Integer.parseInt(args[1].substring(10, args[1].length() - 1))][Integer.parseInt(args[0].substring(9, args[0].length() - 1))];


        for (int i = 0; i < testeArray.length - 2; i++) {
            for (int j = 0; j < testeArray[i].length(); j++) {
                matriz[i][j] = Integer.parseInt(String.valueOf(testeArray[i].charAt(j)));
            }
        }

        int[][] matrizResultado = new int[matriz.length][matriz[0].length];
        int count = 0;

        while (count < Integer.parseInt(args[2].substring(15, args[2].length() - 1))) {
            if (count == 0) {
                for (int[] penis : matriz) {
                    for (int buceta : penis) {
                        System.out.print(buceta + " ");
                    }
                    System.out.println();
                }
                System.out.println();
            }
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                    verificarVizinhos(matriz, i, j, matrizResultado);
                }
            }

            for (int[] piroca : matrizResultado) {
                for (int teste2 : piroca) {
                    System.out.print(teste2 + " ");
                }
                System.out.println();
            }
            System.out.println();

            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                    matriz[i][j] = matrizResultado[i][j];
                }
            }
            try {
                Thread.sleep(Integer.parseInt(args[3].substring(9, args[3].length() - 1)));
            } catch (Exception IllegalArgumentException) {
                System.out.println("Inválido");
            }

            count++;
        }
    }
    public static void verificarVizinhos(int[][] matriz, int linha, int coluna, int[][] matrizResultado) {
        String vizinho = "";

        for (int i = linha - 1; i <= linha + 1; i++) {
            for (int j = coluna - 1; j <= coluna + 1; j++) {
                if (i >= 0 && i < matriz.length && j >= 0 && j < matriz[0].length && (i != linha || j != coluna)) {
                    if (matriz[i][j] == 1) vizinho += matriz[i][j];
                }
            }
        }

        if (vizinho.length() > 3 || vizinho.length() < 2) matrizResultado[linha][coluna] = 0;
        else if (vizinho.length() == 3) matrizResultado[linha][coluna] = 1;
        else matrizResultado[linha][coluna] = matriz[linha][coluna];
    }
}