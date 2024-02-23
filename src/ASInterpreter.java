package src;

import java.util.HashMap;
import java.util.Scanner;

public class ASInterpreter implements Runnable, AutoCloseable {
    private HashMap<String, ASBlock> methods;
    private HashMap<String, String> globals;
    private Scanner keyScanner;

    public ASInterpreter(HashMap<String, ASBlock> methods, Scanner keyScanner) {
        this.methods = methods;
        this.keyScanner = keyScanner;
        globals = new HashMap<>();
    }

    @Override
    public void run() {
        runMethod("main");
    }

    private void runMethod(String method) {
        runMethod(methods.get(method));
    }

    private void runMethod(ASBlock method) {
        HashMap<String, String> locals = new HashMap<>();

        try (Scanner scanner = new Scanner(method.code)) {
            while (scanner.hasNext()) {
                String next = scanner.next();

                if (next.startsWith("%")) {
                    runMethod(next.substring(1));
                    continue;
                }

                if (next.startsWith("@")) {
                    String checkVar = getString(scanner, locals);

                    if (checkVar.equals("true"))
                        runMethod(next.substring(1));
                    
                    continue;
                }

                if (next.equals("print")) {
                    print(scanner, locals);
                    continue;
                }

                if (next.equals("println")) {
                    println(scanner, locals);
                    continue;
                }

                if (next.equals("global")) {
                    createGlobal(scanner, locals);
                    continue;
                }

                if (next.equals("local")) {
                    createLocal(scanner, locals);
                    continue;
                }

                if (next.equals("keyio")) {
                    keyio(scanner, locals);
                    continue;
                }

                if (next.equals("equals")) {
                    doequals(scanner, locals);
                    continue;
                }
            }
        }
    }

    private String getString(Scanner scanner, HashMap<String, String> locals) {
        String str = "";
        
        while (scanner.hasNext()) {
            String next = scanner.next();

            if (next.endsWith("&")) {
                if (locals != null && next.startsWith("*"))
                    next = locals.get(next.substring(1, next.length() - 1)) + "&";

                if (next.startsWith("^"))
                    next = globals.get(next.substring(1, next.length() - 1)) + "&";

                str += next.substring(0, next.length() - 1);
                break;
            }

            if (locals != null && next.startsWith("*"))
                next = locals.get(next.substring(1));

            if (next.startsWith("^"))
                next = globals.get(next.substring(1));

            str += String.format("%s ", next);
        }

        return str;
    }

    private void keyio(Scanner scanner, HashMap<String, String> locals) {
        String varName = scanner.next();

        locals.put(varName, keyScanner.nextLine());
    }

    private void createLocal(Scanner scanner, HashMap<String, String> locals) {
        String varName = scanner.next();

        locals.put(varName, getString(scanner, locals));
    }

    private void createGlobal(Scanner scanner, HashMap<String, String> locals) {
        String varName = scanner.next();

        globals.put(varName, getString(scanner, locals));
    }

    private void print(Scanner scanner, HashMap<String, String> locals) {
        System.out.print(getString(scanner, locals));
    }

    private void println(Scanner scanner, HashMap<String, String> locals) {
        System.out.println(getString(scanner, locals));
    }

    private void doequals(Scanner scanner, HashMap<String, String> locals) {
        String varName = scanner.next();
        String a = getString(scanner, locals);
        String b = getString(scanner, locals);
        locals.put(varName, a.equals(b) ? "true" : "false");
    }

    @Override
    public void close() {
        keyScanner.close();
    }
}
