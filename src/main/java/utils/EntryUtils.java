package utils;

import escapeRoomExceptions.ConsoleEntryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EntryUtils {

    private static final Logger log = LogManager.getLogger(EntryUtils.class);

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
        String entrada = null;
        do{
            if(missatge!=null) System.out.print(missatge+" ");
            entrada = scanner.nextLine();
            if((entrada.isEmpty() && !isNullable)){
                System.out.println("  Error: This field is required.");
            }
        }while(entrada.isEmpty() && !isNullable);
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
        String entrada = null;
        Integer sortida = null;
        do{
            if(missatge!=null) System.out.print(missatge+" ");
            entrada = scanner.nextLine();
            if((entrada.isEmpty() && !isNullable)){
                System.out.println("  Error: This field is required.");
            }else if(isInteger(entrada)){
                sortida = Integer.valueOf(entrada);
            }
        }while(entrada.isEmpty() && !isNullable);
        return sortida;
    };

    public static Long llegirIntHasLong(Scanner scanner, String missatge) {
        Integer resultInt = llegirInt( scanner, missatge, true);

        return null;
    }

    public static Integer llegirInt(Scanner scanner, String missatge) {
        return llegirInt( scanner, missatge, true);
    }

    public static Integer llegirInt(Scanner scanner, String missatge, Boolean isNullable) {
        Integer resultat = null;
        boolean entradaValida = false;

        do {
            if (missatge != null) {
                System.out.print(missatge + " ");
            }

            try {
                if (scanner.hasNextInt()) {
                    resultat = scanner.nextInt();
                    entradaValida = true;
                } else if (isNullable) {
                    String resultatStr = scanner.nextLine();
                    if(resultatStr.isEmpty()){
                        entradaValida = true;
                    }
                } else {
                    System.out.println("    Error: Si us plau, introdueix un número.");
                    scanner.nextLine();
                }
            } catch (InputMismatchException ee) {
                System.out.println("    Error: Si us plau, introdueix un número.");
                scanner.nextLine();
            }

            if ((resultat == null && !isNullable) && entradaValida) {
                System.out.println("    Error: Si us plau, introdueix un número.");
                entradaValida = false;
            }

        } while (!entradaValida);
        // scanner.nextLine();
        return resultat;
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

    public static char llegirChar(Scanner scanner, String missatge){
        String entrada = null;
        scanner.nextLine();
        do{
            System.out.print(missatge+" ");
            entrada = scanner.nextLine();
            try{
                if(entrada!=null && entrada.length()>=2){
                    entrada=null;
                    throw new ConsoleEntryException("** Escriu sols un caràcter!");
                }else{
                    System.out.println(">> " + entrada + " és correcte.");
                }

            } catch (ConsoleEntryException e) {
                System.out.println(e.getMessage());
            }

        }while(entrada==null);

        return entrada.charAt(0);
    };

    public static boolean readYesNo(Scanner scanner, String missatge) {
        String entrada = null;
        Boolean retorn = null;
        do{
            System.out.print(missatge+" ");
            entrada = scanner.nextLine();
            try{
                if ("y".equals(entrada) || "Y".equals(entrada)){
                    retorn = true;
                }else if ("n".equals(entrada) || "N".equals(entrada)){
                    retorn = false;
                }else{
                    throw new ConsoleEntryException("   Error: Type 'y' o 'n'.");
                }
            } catch (ConsoleEntryException e) {
                log.info(e.getMessage());
            }
        }while(retorn==null);

        return retorn;

    };

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

    public static boolean isUpdateable(String strObj){
        return !(strObj==null || strObj.isEmpty() || strObj.isBlank());
    }

}

