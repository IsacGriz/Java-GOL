// Isac de Paula Grizante

import java.util.Objects;

class Main {
    public static void formatVariable(String[] array) {
        String[] value = new String[2];
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                value = array[i].split("=");
            }

            if (value[0] != null) {
                if (value[0].charAt(0) == 'w' && array[i] != null) {
                    array[i] = array[i].replace("w", "width ");
                    array[i] = array[i].replace("=", "= [");
                    array[i] = array[i] + "]";
                } else if (value[0].charAt(0) == 'h' && array[i] != null) {
                    array[i] = array[i].replace("h", "height ");
                    array[i] = array[i].replace("=", "= [");
                    array[i] = array[i] + "]";
                } else if (value[0].charAt(0) == 'g' && array[i] != null) {
                    array[i] = array[i].replace("g", "generations");
                    array[i] = array[i].replace("=", " = [");
                    array[i] = array[i] + "]";
                } else if (value[0].charAt(0) == 's' && array[i] != null) {
                    array[i] = array[i].replace("s", "speed");
                    array[i] = array[i].replace("=", " = [");
                    array[i] = array[i] + "]";
                } else if (value[0].charAt(0) == 'p' && array[i] != null) {
                    array[i] = array[i].replace("p", "population");
                    array[i] = array[i].replace("=", " = [\"");
                    array[i] = array[i] + "\"]";
                }
            }
        }
    }
    public static String[] verifyIfVariableExists(String[] args) {
        boolean[] letterExists = new boolean[5];
        String[] variableErrors = new String[5];

        for (String argue : args) {
            String[] value = argue.split("=");
            if (Objects.equals(value[0], "w")) letterExists[0] = true;
            else if (Objects.equals(value[0], "h")) letterExists[1] = true;
            else if (Objects.equals(value[0], "g")) letterExists[2] = true;
            else if (Objects.equals(value[0], "s")) letterExists[3] = true;
            else if (Objects.equals(value[0], "p")) letterExists[4] = true;
        }
        if (!letterExists[0]) variableErrors[0] = "width = [Não Presente]";
        if (!letterExists[1]) variableErrors[1] = "height = [Não Presente]";
        if (!letterExists[2]) variableErrors[2] = "generations = [Não Presente]";
        if (!letterExists[3]) variableErrors[3] = "speed = [Não Presente]";
        if (!letterExists[4]) variableErrors[4] = "population = [Não Presente]";

        return variableErrors;
    }
    public static String[] verifyValue(String[] args) {
        String[] valueError = new String[5];
        int valueInteger = 0;
        int valueIntegerHeigth = 0;
        int valueIntegerWidth = 0;

        for (String arg : args) {
            String[] value = arg.split("=");
            switch (value[0]) {
                case "w" -> {
                    try {
                        valueIntegerWidth = Integer.parseInt(value[1]);
                    } catch (Exception NumberFormatException) {
                        valueError[0] = value[0] + " =Inválido";
                    }
                    if (valueIntegerWidth != 10 && valueIntegerWidth != 20 && valueIntegerWidth != 40 && valueIntegerWidth != 80)
                        valueError[0] = value[0] + " =Inválido";
                }
                case "h" -> {
                    try {
                        valueIntegerHeigth = Integer.parseInt(value[1]);
                    } catch (Exception NumberFormatException) {
                        valueError[1] = value[0] + " =Inválido";
                    }
                    if (valueIntegerHeigth != 10 && valueIntegerHeigth != 20 && valueIntegerHeigth != 40)
                        valueError[1] = value[0] + " =Inválido";
                }
                case "g" -> {
                    try {
                        valueInteger = Integer.parseInt(value[1]);
                    } catch (Exception NumberFormatException) {
                        valueError[2] = value[0] + " =Inválido";
                    }
                    if (valueInteger < 0) valueError[2] = value[0] + " =Inválido";
                }
                case "s" -> {
                    try {
                        valueInteger = Integer.parseInt(value[1]);
                    } catch (Exception NumberFormatException) {
                        valueError[3] = value[0] + " =Inválido";
                    }
                    if (valueInteger < 250 || valueInteger > 1000) valueError[3] = value[0] + " =Inválido";
                }
                case "p" -> {
                    String[] splitP = value[1].split("#");
                    for (int j = 0; j < value[1].length(); j++) {
                        if (value[1].charAt(j) != '0' && value[1].charAt(j) != '1' && value[1].charAt(j) != '#' && !value[1].equals("rnd")) {
                            valueError[4] = value[0] + " =Inválido";
                            break;
                        }
                    }
                    for (String parte : splitP) {
                        if (parte.length() > valueIntegerWidth) {
                            valueError[4] = value[0] + " =Inválido";
                            break;
                        }
                    }
                    if (splitP.length > valueIntegerHeigth) {
                        valueError[4] = value[0] + " =Inválido";
                    }
                }
            }
        }
        formatVariable(valueError);
        return valueError;
    }
    static void orderArgs (String[] valueError, String[] nonExistentVariables, String[] args) {
        String[] totalErrors = new String[5];
        String[] organizedArgues = new String[5];

        for (int i = 0; i < 5; i++) {
            if (nonExistentVariables[i] == null) {
                totalErrors[i] = valueError[i];
            } else {
                totalErrors[i] = nonExistentVariables[i];
            }
        }

        for (String argue : args) {
            if (argue.charAt(0) == 'w') organizedArgues[0] = argue;
            else if (argue.charAt(0) == 'h') organizedArgues[1] = argue;
            else if (argue.charAt(0) == 'g') organizedArgues[2] = argue;
            else if (argue.charAt(0) == 's') organizedArgues[3] = argue;
            else if (argue.charAt(0) == 'p') organizedArgues[4] = argue;
        }

        formatVariable(organizedArgues);

        for (int i = 0; i < 5; i++) {
            if (totalErrors[i] == null) {
                args[i] = organizedArgues[i];
            } else {
                args[i] = totalErrors[i];
            }
        }

        Logica.main(args);
    }
    public static void main(String[] args) {
        String[] variableErrors = verifyIfVariableExists(args);
        String[] valueError = verifyValue(args);
        orderArgs(valueError, variableErrors, args);
    }
}