package gol;

public class Main {
    public int height, width, generations, speed;
    public String population;
//
//    public int getHeight() {
//        return height;
//    }
//
//    public void setHeight(int height) {
//        this.height = height;
//    }
//
//    public int getWidth() {
//        return width;
//    }
//
//    public void setWidth(int width) {
//        this.width = width;
//    }
//
//    public int getGenerations() {
//        return generations;
//    }
//
//    public void setGenerations(int generations) {
//        this.generations = generations;
//    }
//
//    public int getSpeed() {
//        return speed;
//    }
//
//    public void setSpeed(int speed) {
//        this.speed = speed;
//    }
//
//    public String getPopulation() {
//        return population;
//    }
//
//    public void setPopulation(String population) {
//        this.population = population;
//    }

    public static void main(String[] args) {
        Format format = new Format();
        Logica logica = new Logica();

        int valueError = format.inicio(args);


        if (valueError == 1) System.out.println("Valores inválidos!");
        else logica.mandarProSwing();
    }
}
