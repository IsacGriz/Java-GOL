package gol;// Isac de Paula Grizante

import java.util.Objects;

class FormatArgs extends Main{
    private static void formatarArgumentos(String[] array) {
        String[] valor = new String[2];
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                valor = array[i].split("=");
            }

            if (valor[0] != null) {
                if (valor[0].charAt(0) == 'w' && array[i] != null) {
                    array[i] = array[i].replace("w", "width ");
                    array[i] = array[i].replace("=", "= [");
                    array[i] = array[i] + "]";
                } else if (valor[0].charAt(0) == 'h' && array[i] != null) {
                    array[i] = array[i].replace("h", "height ");
                    array[i] = array[i].replace("=", "= [");
                    array[i] = array[i] + "]";
                } else if (valor[0].charAt(0) == 'g' && array[i] != null) {
                    array[i] = array[i].replace("g", "generations");
                    array[i] = array[i].replace("=", " = [");
                    array[i] = array[i] + "]";
                } else if (valor[0].charAt(0) == 's' && array[i] != null) {
                    array[i] = array[i].replace("s", "speed");
                    array[i] = array[i].replace("=", " = [");
                    array[i] = array[i] + "]";
                } else if (valor[0].charAt(0) == 'p' && array[i] != null) {
                    array[i] = array[i].replace("p", "population");
                    array[i] = array[i].replace("=", " = [\"");
                    array[i] = array[i] + "\"]";
                }
            }
        }
    }
    private static String[] verificarSeVaiavelExiste(String[] args) {
        boolean[] letraExiste = new boolean[5];
        String[] errosDeExistencia = new String[5];

        for (String argue : args) {
            String[] valor = argue.split("=");
            if (Objects.equals(valor[0], "w")) letraExiste[0] = true;
            else if (Objects.equals(valor[0], "h")) letraExiste[1] = true;
            else if (Objects.equals(valor[0], "g")) letraExiste[2] = true;
            else if (Objects.equals(valor[0], "s")) letraExiste[3] = true;
            else if (Objects.equals(valor[0], "p")) letraExiste[4] = true;
        }
        if (!letraExiste[0]) errosDeExistencia[0] = "width = [Não Presente]";
        if (!letraExiste[1]) errosDeExistencia[1] = "height = [Não Presente]";
        if (!letraExiste[2]) errosDeExistencia[2] = "generations = [Não Presente]";
        if (!letraExiste[3]) errosDeExistencia[3] = "speed = [Não Presente]";
        if (!letraExiste[4]) errosDeExistencia[4] = "population = [Não Presente]";

        return errosDeExistencia;
    }
    private static String[] verificarSeValorEValido(String[] args) {
        String[] errosDeValores = new String[5];
        int valorInteiro = 0;
        int valorInteiroHeigth = 0;
        int valorInteiroWidth = 0;

        for (String arg : args) {
            String[] valor = arg.split("=");
            switch (valor[0]) {
                case "w" -> {
                    try {
                        valorInteiroWidth = Integer.parseInt(valor[1]);
                    } catch (Exception NumberFormatException) {
                        errosDeValores[0] = valor[0] + " =Inválido";
                    }
                    if (valorInteiroWidth != 10 && valorInteiroWidth != 20 && valorInteiroWidth != 40 && valorInteiroWidth != 80)
                        errosDeValores[0] = valor[0] + " =Inválido";
                }
                case "h" -> {
                    try {
                        valorInteiroHeigth = Integer.parseInt(valor[1]);
                    } catch (Exception NumberFormatException) {
                        errosDeValores[1] = valor[0] + " =Inválido";
                    }
                    if (valorInteiroHeigth != 10 && valorInteiroHeigth != 20 && valorInteiroHeigth != 40)
                        errosDeValores[1] = valor[0] + " =Inválido";
                }
                case "g" -> {
                    try {
                        valorInteiro = Integer.parseInt(valor[1]);
                    } catch (Exception NumberFormatException) {
                        errosDeValores[2] = valor[0] + " =Inválido";
                    }
                    if (valorInteiro < 0) errosDeValores[2] = valor[0] + " =Inválido";
                }
                case "s" -> {
                    try {
                        valorInteiro = Integer.parseInt(valor[1]);
                    } catch (Exception NumberFormatException) {
                        errosDeValores[3] = valor[0] + " =Inválido";
                    }
                    if (valorInteiro < 250 || valorInteiro > 1000) errosDeValores[3] = valor[0] + " =Inválido";
                }
                case "p" -> {
                    String[] pSplitado = valor[1].split("#");
                    for (int j = 0; j < valor[1].length(); j++) {
                        if (valor[1].charAt(j) != '0' && valor[1].charAt(j) != '1' && valor[1].charAt(j) != '#' && !valor[1].equalsIgnoreCase("rnd")) {
                            errosDeValores[4] = valor[0] + " =Inválido";
                            break;
                        }
                    }
                    for (String parte : pSplitado) {
                        if (parte.length() > valorInteiroWidth) {
                            errosDeValores[4] = valor[0] + " =Inválido";
                            break;
                        }
                    }
                    if (pSplitado.length > valorInteiroHeigth) {
                        errosDeValores[4] = valor[0] + " =Inválido";
                    }
                }
            }
        }

        formatarArgumentos(errosDeValores);
        return errosDeValores;
    }
    private int enviarArgumentosTratados (String[] errosDeValores, String[] nonExistentVariables, String[] args) {
        String[] todosOsErros = new String[5];
        String[] argumentosTratados = new String[5];
        boolean temErro = false;

        for (int i = 0; i < 5; i++) {
            if (nonExistentVariables[i] == null) {
                todosOsErros[i] = errosDeValores[i];
            } else {
                todosOsErros[i] = nonExistentVariables[i];
            }
        }

        args = organizarArgumentos(args);

        formatarArgumentos(args);

        for (int i = 0; i < 5; i++) {
            if (todosOsErros[i] == null) {
                System.out.println(args[i]);
                argumentosTratados[i] = args[i];
            } else {
                System.out.println(todosOsErros[i]);
                argumentosTratados[i] = todosOsErros[i];
                temErro = true;
            }
        }

        if (temErro) return 1;

        Main.width = (Integer.parseInt(argumentosTratados[0].substring(9, args[0].length() - 1)));
        Main.height = (Integer.parseInt(argumentosTratados[1].substring(10, args[1].length() - 1)));
        Main.generations = (Integer.parseInt(args[2].substring(15, args[2].length() - 1)));
        Main.speed = (Integer.parseInt(args[3].substring(9, args[3].length() - 1)));
        String population = (argumentosTratados[4].substring(15, args[4].length() - 2));
        Main.population = population.replaceAll("#", "\n").replaceAll("0", ".").replaceAll("1", "X");

        return 0;
    }
    private String[] organizarArgumentos(String[] args) {
        String[] argumentosOrganizados = new String[5];

        for (String argumento : args) {
            if (argumento.charAt(0) == 'w') argumentosOrganizados[0] = argumento;
            else if (argumento.charAt(0) == 'h') argumentosOrganizados[1] = argumento;
            else if (argumento.charAt(0) == 'g') argumentosOrganizados[2] = argumento;
            else if (argumento.charAt(0) == 's') argumentosOrganizados[3] = argumento;
            else if (argumento.charAt(0) == 'p') argumentosOrganizados[4] = argumento;
        }
        return argumentosOrganizados;
    }
    public int inicio(String[] args) {
        String[] errosDeExistencia = verificarSeVaiavelExiste(args);
        String[] errosDeValores = verificarSeValorEValido(args);
        return enviarArgumentosTratados(errosDeValores, errosDeExistencia, args);
    }
}