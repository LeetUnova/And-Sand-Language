package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0)
            return;

        HashMap<String, ASBlock> methods;

        try (Scanner scanner = new Scanner(new File(args[0]))) {
            methods = createProgram(scanner);
        }
        catch (FileNotFoundException e) {
            System.out.println("Could not find source file.");
            return;
        }

        try (ASInterpreter asInterpreter = new ASInterpreter(methods, new Scanner(System.in))) {
            asInterpreter.run();
        }
    }

    private static HashMap<String, ASBlock> createProgram(Scanner scanner) {
        HashMap<String, ASBlock> methods = new HashMap<>();

        while (scanner.hasNext()) {
            String next = scanner.next();

            if (next.equals("fun"))
                methods.put(scanner.next(), createBlock(scanner));
        }

        return methods;
    }

    private static ASBlock createBlock(Scanner scanner) {
        String code = "";
        while (scanner.hasNext()) {
            String next = scanner.next();
            
            if (next.equals("end"))
                break;
            
            code += String.format(" %s ", next);
        }
        return new ASBlock(code);
    }
}