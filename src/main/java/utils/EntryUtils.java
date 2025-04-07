package utils;

import escapeRoomExceptions.ConsoleEntryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EntryUtils {

    private static final Logger log = LogManager.getLogger(EntryUtils.class);
    public static final String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

    public static byte llegirByte(Scanner scanner, String missatge){
        Byte resultat = null;
        do{
            System.out.print(missatge+" ");
            try{
                resultat = scanner.nextByte();
                System.out.println(">> " + resultat);
            }catch(InputMismatchException ee){
                System.out.println("Exception InputMismatchException controlada.");
                scanner.nextLine();
            }
        }while(resultat==null);

        return resultat;
    };

    public static String llegirString(Scanner scanner, String missatge, Boolean isNullable){
        return llegirString(scanner, missatge, isNullable, null, null);
    }

    public static String llegirString(Scanner scanner, String missatge, Boolean isNullable, Integer maxLength){
        return llegirString(scanner, missatge, isNullable, maxLength, null);
    }

    public static String llegirString(Scanner scanner, String missatge, Boolean isNullable, String regExp){
        return llegirString(scanner, missatge, isNullable, null, regExp);
    }

    public static String llegirString(Scanner scanner, String missatge, Boolean isNullable, Integer maxLength, String regExp){
        String entrada = null;
        do{
            if(missatge!=null) System.out.print(missatge+" ");
            entrada = scanner.nextLine();
            if((entrada.isEmpty() && !isNullable)){
                System.out.println("  Error: This field is required.");
            }

            if(!entrada.isEmpty() && maxLength!=null && entrada.length()>maxLength){
                System.out.println("  The character limit in this field has been exceeded. The maximum number of characters is "+maxLength);
                entrada="validationError";
            }

            if(regExp!=null && isNotNullOrEmpty(entrada)){
                Pattern pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(entrada);
                if(!matcher.matches()){
                    entrada = null;
                    System.out.println(" ERROR: The field does not have a valid format. ");
                }
            }

        }while((!isNotNullOrEmpty(entrada) && !isNullable) || "validationError".equals(entrada));
        return entrada;

    };

    public static Long readStringLikeLong(Scanner scanner, String missatge, Boolean isNullable){
        String entrada = null;
        Integer sortida = null;
        do{
            if(missatge!=null) System.out.print(missatge+" ");
            entrada = scanner.nextLine();
            if((entrada.isEmpty() && !isNullable)){
                System.out.println("  Error: This field is required.");
            }else if(isNumeric(entrada)){
                sortida = Integer.valueOf(entrada);
            }
        }while(entrada.isEmpty() && !isNullable);
        return Long.valueOf(sortida);
    };

    public static Integer readStringLikeInt(Scanner scanner, String missatge, Boolean isNullable){
        return readStringLikeInt( scanner, missatge, isNullable, null, null);
    }

    public static Integer readStringLikeInt(Scanner scanner, String missatge, Boolean isNullable, Integer maxValue){
        return readStringLikeInt( scanner, missatge, isNullable, 0, maxValue);
    }

    public static Integer readStringLikeInt(Scanner scanner, String missatge, Boolean isNullable, Integer minValue, Integer maxValue){
        String entryStr = null;
        Integer resultInt = null;
        do{
            if(missatge!=null) System.out.print(missatge+" ");
            entryStr = scanner.nextLine();
            if((entryStr.isEmpty() && !isNullable)){
                System.out.println("  Error: This field is required.");
            }else if(isInteger(entryStr)){
                resultInt = Integer.valueOf(entryStr);
                if( maxValue!=null && (resultInt<minValue || resultInt>maxValue)){
                    System.out.println("    Error: A value must be entered between " + minValue + "  i " + maxValue);
                }
            }

        }while(!isNullable && isNullOrEmpty(entryStr));
        return resultInt;
    };

    public static Long llegirIntHasLong(Scanner scanner, String missatge) {
        Integer resultInt = llegirInt( scanner, missatge, true);

        return null;
    }

    public static Integer llegirInt(Scanner scanner, String missatge) {
        return llegirInt(scanner, missatge, true);
    }

    public static Integer llegirInt(Scanner scanner, String missatge, Boolean isNullable) {
        return llegirInt(scanner, missatge, isNullable, null);
    }

    public static Integer llegirInt(Scanner scanner, String missatge, Boolean isNullable, Integer maxValue) {
        return llegirInt(scanner, missatge, isNullable, 0, maxValue);
    }

    public static Integer llegirInt(Scanner scanner, String missatge, Boolean isNullable, Integer minValue, Integer maxValue) {
        Integer resultatInt = null;
        boolean entradaValida = false;

        do {
            if (missatge != null) {
                System.out.print(missatge + " ");
            }

            try {
                if (scanner.hasNextInt()) {
                    resultatInt = scanner.nextInt();
                    entradaValida = true;
                } else if (isNullable) {
                    String resultatStr = scanner.nextLine();
                    if(resultatStr.isEmpty()){
                        resultatInt = Integer.valueOf(resultatStr);
                        entradaValida = true;
                    }
                } else {
                    System.out.println("    Error: Si us plau, introdueix un número.");
                    scanner.nextLine();
                }

                if(entradaValida && ( maxValue!=null && (resultatInt<minValue || resultatInt>maxValue))){
                    System.out.println("    Error: A value must be entered between " + minValue + "  i " + maxValue);
                    entradaValida = false;
                }

            } catch (InputMismatchException ee) {
                System.out.println("    Error: Si us plau, introdueix un número.");
                scanner.nextLine();
            }

            if (resultatInt == null && !isNullable && entradaValida) {
                System.out.println("    Error: Si us plau, introdueix un número.");
                entradaValida = false;
            }

        } while (!entradaValida);
        // scanner.nextLine();
        return resultatInt;
    }

    public static float llegirFloat(Scanner scanner, String missatge){
        Float resultat = null;
        do{
            System.out.print(missatge+" ");
            try{
                resultat = scanner.nextFloat();
                System.out.println(">> " + resultat );
            }catch(InputMismatchException ee){
                System.out.println("Exception InputMismatchException controlada.");
                scanner.nextLine();
            }
        }while(resultat==null);

        return resultat;
    };

    public static double llegirDouble(Scanner scanner, String missatge){
        Double resultat = null;
        do{
            System.out.print(missatge+" ");
            try{
                resultat = scanner.nextDouble();
                System.out.println(">> " + resultat );
            }catch(InputMismatchException ee){
                System.out.println("Exception InputMismatchException controlada.");
                scanner.nextLine();
            }
        }while(resultat==null);

        return resultat;
    };

    public static long llegirLong(Scanner scanner, String missatge) {
        Long resultado = null;
        do {
            System.out.print(missatge + " ");
            try {
                resultado = scanner.nextLong();
                System.out.println(">> " + resultado);
            } catch (InputMismatchException ee) {
                System.out.println("Exception InputMismatchException controlled.");
                scanner.nextLine();
            }
        } while (resultado == null);

        return resultado;
    }

    /*
        AmidaException (Exception):
        public static char llegirChar(String missatge);
        public static String llegirString(String missatge);
        public static boolean llegirSiNo(String missatge), si l’usuari/ària introdueix “s”,
        retorna “true”, si l’usuari/ària introdueix “n”, retorna “false”.
     */
    public static char llegirChar(Scanner scanner, String missatge) {
        String entrada = null;
        scanner.nextLine();
        do {
            System.out.print(missatge + " ");
            entrada = scanner.nextLine();
            try {
                if (entrada != null && entrada.length() >= 2) {
                    entrada = null;
                    throw new ConsoleEntryException("** Escriu sols un caràcter!");
                } else {
                    System.out.println(">> " + entrada + " és correcte.");
                }

            } catch (ConsoleEntryException e) {
                System.out.println(e.getMessage());
            }

        } while (entrada == null);

        return entrada.charAt(0);
    }

    ;

    public static boolean readYesNo(Scanner scanner, String missatge) {
        String entrada = null;
        Boolean retorn = null;
        do {
            System.out.print(missatge + " ");
            entrada = scanner.nextLine();
            try {
                if ("y".equals(entrada) || "Y".equals(entrada)) {
                    retorn = true;
                } else if ("n".equals(entrada) || "N".equals(entrada)) {
                    retorn = false;
                } else {
                    throw new ConsoleEntryException("   Error: Type 'y' o 'n'.");
                }
            } catch (ConsoleEntryException e) {
                log.info(e.getMessage());
            }
        } while (retorn == null);

        return retorn;

    }


    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotNullOrEmpty(String strObj){
        return !(strObj==null || strObj.isEmpty() || strObj.isBlank());
    }
    public static boolean isNullOrEmpty(String strObj){
        return (strObj==null || strObj.isEmpty() || strObj.isBlank());
    }


}