import java.util.Objects;
/***
 * @author isac
 * */
public class Main {
    /***
     * @param args w, h, g, s, p;
     * This method receive the params and calls function executarVerificacoes and print the return
     * */
    public static void main(String[] args) {
        System.out.println("==========INICIO==========");
        String exec = executarVerificacoes(args);
        System.out.println("==========FINAL==========");

        System.out.println(exec);
    }
    /**
     * @return String informing if it had errors or not
     * This function get the errors that came from verificarSeValorConsta and soltarErroVariavelInexistente and replace the original values
     * with the errors and display it
     * This function is also responsible for calls the others functions and organize the order os executions
     * */
    static String executarVerificacoes(String[] args) {
        String[] errosVariaveis = verificarSeValorConsta(args);
        String[] errosValores = soltarErroVariavelInexistente(args);
        String[] argumentosTratados = new String[5];

        if (!verificarParametrosRepetidos(args)) {
            return "Parâmetros repetidos";
        }

        for (int i = 0; i < args.length; i++) {
            args[i] = formatarString(args[i]);
            args[i] += "]";
        }

        for (int i = 0; i < argumentosTratados.length; i++) {
            if (errosValores[i] == null && errosVariaveis[i] == null) {
                argumentosTratados[i] = args[i];
            } else if (errosValores[i] == null) {
                argumentosTratados[i] = errosVariaveis[i];
            } else if (errosVariaveis[i] == null) {
                argumentosTratados[i] = errosValores[i];
            }
        }

        for (String argumento : argumentosTratados) {
            System.out.println(argumento);
        }

        if (nullOuNao(errosVariaveis) && nullOuNao(errosValores)) {
            return "Houve erros";
        }
        return "Executado com sucesso";
    }
    /**
     * @return String
     *
     * **/
    static String formatarString(String args) {
        if (args.contains("w")) {
            args = args.replace("w", "width ");
            args = args.replace("=", "= [");
            return args;
        } else if (args.contains("g")) {
            args = args.replace("g", "generations");
            args = args.replace("=", "=[");
            return args;
        } else if (args.contains("h")) {
            args = args.replace("h", "height ");
            args = args.replace("=", "= [");
            return args;
        } else if (args.contains("s")) {
            args = args.replace("s", "speed");
            args = args.replace("=", "=[");
            return args;
        }

        args = args.replace("p", "population");
        args = args.replace("=", "=[");
        return args;
    }
    static void organizarArgs(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].charAt(0) == 'w') {
                String temp = args[0];
                args[0] = args[i];
                args[i] = temp;
            } else if (args[i].charAt(0) == 'h') {
                String temp = args[1];
                args[1] = args[i];
                args[i] = temp;
            } else if (args[i].charAt(0) == 'g') {
                String temp = args[2];
                args[2] = args[i];
                args[i] = temp;
            } else if (args[i].charAt(0) == 's') {
                String temp = args[3];
                args[3] = args[i];
                args[i] = temp;
            } else if (args[i].charAt(0) == 'p') {
                String temp = args[4];
                args[4] = args[i];
                args[i] = temp;
            }
        }
    }

    static boolean verificarParametrosRepetidos(String[] args) {
        for (int i = 0; i < args.length; i++) {
            for (int j = i + 1; j < args.length; j++) {
                if (args[i].charAt(0) == args[j].charAt(0)) {
                    return false;
                }
            }
        }
        return true;
    }

    static String[] verificarSeValorConsta(String[] args) {
        String[] parametroSplitado;
        String[] erros = new String[5];
        int tamanhoWidth = 0;
        int tamanhoHeigth = 0;

        for (String parametros : args) {
            parametroSplitado = parametros.split("=");
            if (Objects.equals(parametroSplitado[0], "w")) {
                if (!Objects.equals(parametroSplitado[1], "10") && !Objects.equals(parametroSplitado[1], "20") &&
                    !Objects.equals(parametroSplitado[1], "40") && !Objects.equals(parametroSplitado[1], "80")) {
                    erros[0] = "width = [Inválido]";
                } else {
                    try {
                        tamanhoWidth = Integer.parseInt(parametroSplitado[1]);
                    } catch (Exception NumberFormatException) {
                        erros[0] = "width = [Inválido]";
                    }
                }
            } else if (Objects.equals(parametroSplitado[0], "h")) {
                if (!Objects.equals(parametroSplitado[1], "10") && !Objects.equals(parametroSplitado[1], "20") &&
                        !Objects.equals(parametroSplitado[1], "40")) {
                    erros[1] = "heigth = [Inválido]";
                } else {
                    try {
                        tamanhoHeigth = Integer.parseInt(parametroSplitado[1]);
                    } catch (Exception NumberFormatException) {
                        erros[1] = "heigth = [Inválido]";
                    }
                }
            } else if (Objects.equals(parametroSplitado[0], "g")) {
                int comparacao = 0;
                try {
                    comparacao = Integer.parseInt(parametroSplitado[1]);
                } catch (Exception NumberFormatException) {
                    erros[2] = "generations = [Inválido]";
                }
                if (comparacao < 0) {
                    erros[2] = "generations = [Inválido]";
                }
            } else if (Objects.equals(parametroSplitado[0], "s")) {
                int comparacao = 0;
                try {
                    comparacao = Integer.parseInt(parametroSplitado[1]);
                } catch (Exception NumberFormatException) {
                    erros[3] = "speed = [Inválido]";
                }
                if (!(comparacao >= 250 && comparacao <= 1000)) {
                    erros[3] = "speed = [Inválido]";
                }
            } else if (Objects.equals(parametroSplitado[0], "p")) {
                for (int i = 1; i < parametroSplitado[1].length() - 1; i++) {
                    if (parametroSplitado[1].charAt(i) != '"' && parametroSplitado[1].charAt(i) != '0' &&
                            parametroSplitado[1].charAt(i) != '1' && parametroSplitado[1].charAt(i) != '#' && parametroSplitado[1].equals("rnd")) {
                        erros[4] = "population = [Inválido]";
                        break;
                    }
                    String[] arrayTeste = parametroSplitado[1].split("#");
                    for (String parte : arrayTeste) {
                        if (parte.length() > tamanhoWidth || arrayTeste.length > tamanhoHeigth) {
                            erros[4] = "population = [Inválido]";
                            break;
                        }
                    }
                }
            }
        }
        return erros;
    }

    static String[] soltarErroVariavelInexistente(String[] args) {
        boolean[] temOuNao = new boolean[5];
        String[] erros = new String[5];

        if (args.length < 5) {
            for (String arg : args) {
                verificarSeVariavelConsta(arg, temOuNao);
            }
            if (!temOuNao[1]) {
                erros[0] = "width = [Não Presente]";
            }
            if (!temOuNao[0]) {
                erros[1] = "height = [Não Presente]";
            }
            if (!temOuNao[2]) {
                erros[2] = "generations=[Não Presente]";
            }
            if (!temOuNao[3]) {
                erros[3] = "speed=[Não Presente]";
            }
            if (!temOuNao[4]) {
                erros[4] = "population=[Não Presente]";
            }
        }
        return erros;
    }

    static void verificarSeVariavelConsta(String arg, boolean[] temOuNao) {
        if (arg.charAt(0) == 'h') {
            temOuNao[0] = true;
        } else if (arg.charAt(0) == 'w') {
            temOuNao[1] = true;
        } else if (arg.charAt(0) == 'g') {
            temOuNao[2] = true;
        } else if (arg.charAt(0) == 's') {
            temOuNao[3] = true;
        } else if (arg.charAt(0) == 'p') {
            temOuNao[4] = true;
        }
    }
    static boolean nullOuNao(String[] array) {
        for (String nullOuNao : array) {
            if (!(nullOuNao == null)) {
                return true;
            }
        }
        return false;
    }
}