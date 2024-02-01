import java.util.Objects;

class Main {
    public static void formatarVariavel(String[] array) {
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
    public static String[] verificarSeVariavelExiste(String[] args) {
        boolean[] temLetra = new boolean[5];
        String[] errosVariaveis = new String[5];

        for (String argumento : args) {
            String[] valor = argumento.split("=");
            if (Objects.equals(valor[0], "w")) temLetra[0] = true;
            else if (Objects.equals(valor[0], "h")) temLetra[1] = true;
            else if (Objects.equals(valor[0], "g")) temLetra[2] = true;
            else if (Objects.equals(valor[0], "s")) temLetra[3] = true;
            else if (Objects.equals(valor[0], "p")) temLetra[4] = true;
        }
        if (!temLetra[0]) errosVariaveis[0] = "width" + " = " + "[Não Presente]";
        if (!temLetra[1]) errosVariaveis[1] = "height" + " = " + "[Não Presente]";
        if (!temLetra[2]) errosVariaveis[2] = "generations" + " = " + "[Não Presente]";
        if (!temLetra[3]) errosVariaveis[3] = "speed" + " = " + "[Não Presente]";
        if (!temLetra[4]) errosVariaveis[4] = "population" + " = " + "[Não Presente]";

        return errosVariaveis;
    }
    public static String[] verificarValores(String[] args) {
        String[] errosValores = new String[5];
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
                        errosValores[0] = valor[0] + " =" + "Inválido";
                    }
                    if (valorInteiroWidth != 10 && valorInteiroWidth != 20 && valorInteiroWidth != 40 && valorInteiroWidth != 80)
                        errosValores[0] = valor[0] + " =" + "Inválido";
                }
                case "h" -> {
                    try {
                        valorInteiroHeigth = Integer.parseInt(valor[1]);
                    } catch (Exception NumberFormatException) {
                        errosValores[1] = valor[0] + " =" + "Inválido";
                    }
                    if (valorInteiroHeigth != 10 && valorInteiroHeigth != 20 && valorInteiroHeigth != 40)
                        errosValores[1] = valor[0] + " =" + "Inválido";
                }
                case "g" -> {
                    try {
                        valorInteiro = Integer.parseInt(valor[1]);
                    } catch (Exception NumberFormatException) {
                        errosValores[2] = valor[0] + " =" + "Inválido";
                    }
                    if (valorInteiro < 0) errosValores[2] = valor[0] + " =" + "Inválido";
                }
                case "s" -> {
                    try {
                        valorInteiro = Integer.parseInt(valor[1]);
                    } catch (Exception NumberFormatException) {
                        errosValores[3] = valor[0] + " =" + "Inválido";
                    }
                    if (valorInteiro < 250 || valorInteiro > 1000) errosValores[3] = valor[0] + " =" + "Inválido";
                }
                case "p" -> {
                    String[] pSplitado = valor[1].split("#");
                    for (int j = 0; j < valor[1].length(); j++) {
                        if (valor[1].charAt(j) != '0' && valor[1].charAt(j) != '1' && valor[1].charAt(j) != '#' && !valor[1].equals("rnd")) {
                            errosValores[4] = valor[0] + " =" + "Inválido";
                            break;
                        }
                    }
                    for (String parte : pSplitado) {
                        if (parte.length() > valorInteiroWidth) {
                            errosValores[4] = valor[0] + " =" + "Inválido";
                            break;
                        }
                    }
                    if (pSplitado.length > valorInteiroHeigth) {
                        errosValores[4] = valor[0] + " =" + "Inválido";
                    }
                }
            }
        }
        formatarVariavel(errosValores);
        return errosValores;
    }
    static void organizarArgumentos (String[] errosValores, String[] variaveisInexistentes, String[] args) {
        String[] errosTotais = new String[5];
        String[] argsOrganizados = new String[5];

        for (int i = 0; i < 5; i++) {
            if (variaveisInexistentes[i] == null) {
                errosTotais[i] = errosValores[i];
            } else {
                errosTotais[i] = variaveisInexistentes[i];
            }
        }

        for (String argumento : args) {
            if (argumento.charAt(0) == 'w') argsOrganizados[0] = argumento;
            else if (argumento.charAt(0) == 'h') argsOrganizados[1] = argumento;
            else if (argumento.charAt(0) == 'g') argsOrganizados[2] = argumento;
            else if (argumento.charAt(0) == 's') argsOrganizados[3] = argumento;
            else if (argumento.charAt(0) == 'p') argsOrganizados[4] = argumento;
        }

        formatarVariavel(argsOrganizados);

        for (int i = 0; i < 5; i++) {
            if (errosTotais[i] == null) {
                System.out.println(argsOrganizados[i]);
            } else {
                System.out.println(errosTotais[i]);
            }
        }
    }
    public static void main(String[] args) {
        String[] errosVariaveis = verificarSeVariavelExiste(args);
        String[] errosValores = verificarValores(args);
        organizarArgumentos(errosValores, errosVariaveis, args);
    }
}