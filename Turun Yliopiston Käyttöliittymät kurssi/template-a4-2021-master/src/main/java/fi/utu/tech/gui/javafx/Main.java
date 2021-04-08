package fi.utu.tech.gui.javafx;

import fi.utu.tech.gui.javafx.assignment1.MainApp1;
import fi.utu.tech.gui.javafx.assignment2.MainApp2;
import fi.utu.tech.gui.javafx.assignment3.MainApp3;
import fi.utu.tech.gui.javafx.assignment4.MainApp4;
import fi.utu.tech.gui.javafx.assignment5.MainApp5;
import fi.utu.tech.gui.javafx.assignment6.MainApp6;
import fi.utu.tech.gui.javafx.assignment7.MainApp7;
import fi.utu.tech.gui.javafx.assignment8.MainApp8;
import javafx.application.Application;

public class Main {
    static Class<?> chooseMain(int exercise) {
        switch (exercise) {
            case 1:
                return MainApp1.class;
            case 2:
                return MainApp2.class;
            case 3:
                return MainApp3.class;
            case 4:
                return MainApp4.class;
            case 5:
                return MainApp5.class;
            case 6:
                return MainApp6.class;
            case 7:
                return MainApp7.class;
            case 8:
                return MainApp8.class;
            default:
                return null;
        }
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application. main()
     * serves only as fallback in case the application can not be launched through
     * deployment artifacts, e.g., in IDEs with limited FX support. NetBeans ignores
     * main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 1 && args[0].equals("--test"))
            return;

        int exercise = 1;
        if (args.length == 1)
            exercise = Integer.parseInt(args[0]);

        var mainClass = chooseMain(exercise);

        if (mainClass != null)
            Application.launch(mainClass.asSubclass(Application.class), args);
        else
            System.out.println("Provide an exercise number as a command line argument!");
    }

}
