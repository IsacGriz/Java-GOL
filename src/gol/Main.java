package gol;

public class Main {
    public static int height;
    public static int width;
    public static int generations;
    public static int speed;
    public static String population;
//
    public static void main(String[] args) {
        FormatArgs format = new FormatArgs();
        Display logica = new Display();

        int valueError = format.inicio(args);

        if (valueError == 1) System.out.println("Valores inválidos!");
        else logica.mandarProSwing();
    }
}
