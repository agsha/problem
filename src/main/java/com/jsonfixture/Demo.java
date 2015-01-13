package com.jsonfixture;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by sgururaj on 1/13/15.
 */
public class Demo {
    private static final Logger log = LogManager.getLogger();

    public static void main(String[] args) throws Exception {

        if(args.length != 2) {
            System.out.println("----------------------------------");
            System.out.println("USAGE:");
            System.out.println("Usage: mvn exec:java -Dexec.mainClass=\"com.jsonfixture.Demo\" -Dexec.args=\"<classname> <referenceId>\".\n" +
                    " Example: mvn exec:java -Dexec.mainClass=\"com.jsonfixture.Demo\" -Dexec.args=\"com.example.Order order1\"");
            System.out.println("----------------------------------");
            return;
        }
        Main main = new Main();
        main.initialize();
        System.out.println(main.getJsonString(args[0], args[1]));
    }
}
