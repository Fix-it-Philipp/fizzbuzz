import java.util.*;
import java.util.Timer;

public class Main {
    public static boolean inTime = false;

    public static void main(String[] args) {
        // Variablendeklaration
        int countTo = 0;
        Scanner scanner = new Scanner(System.in);
        String eingabe;
        boolean correctInput;
        int delay = 10000;

        // Abfrage bis wohin gezählt werden soll
        do {
            try {
                System.out.print("Bis wohin möchten Sie zählen? ");
                eingabe = scanner.nextLine();
                countTo = Integer.parseInt(eingabe);
                System.out.print("Wie viel Zeit (in Sekunden) möchten Sie zur Verfügung haben? ");
                eingabe = scanner.nextLine();
                delay = Integer.parseInt(eingabe) * 1000;
                correctInput = true;
            }
            catch(Exception error) {
                correctInput = false;
                System.out.println("Ups, da ist etwas schief gegangen. Versuch mal bitte eine Zahl einzugeben!");
            }
        } while (!correctInput);

        // Programmablauf
        int i = 1;
        while (i <= countTo) {
            if (!checkInput(getUserInput(delay),i)) {
                System.out.println("Ups, das war falsch!\n");
                System.exit(0);
            }
            i++;
            System.out.println("Computer sagt: " + GetCPUOut(i));
            i++;
        }
        System.exit(0);

    }

    private static boolean checkInput(String userInput,int zahl) {
        boolean b = false;
        if ((zahl % 3) == 0) b = userInput.equals("Fizz");
        if ((zahl % 5) == 0) b = userInput.equals("Buzz");
        if (((zahl % 3) == 0) && ((zahl % 5) == 0)) b = userInput.equals("FizzBuzz");
        if (!((zahl % 3) == 0) && !((zahl % 5) == 0)) b = userInput.equals(String.valueOf(zahl));
        return b;
    }

    private static String getUserInput(int delay) {
        String userEingabe;
        Scanner scanner = new Scanner(System.in);
        Timer timer = new Timer();

        TimerTask kill = new TimerTask() {
            public void run() {
                if (!inTime) {
                    System.out.println("\nDu warst zu langsam!");
                    System.exit(0);
                }
                else {
                    inTime = false;
                }
            }
        };

        System.out.print("Du bist dran: ");
        timer.schedule(kill,delay);
        userEingabe = scanner.nextLine();
        inTime = true;
        return userEingabe;
    }

    private static String GetCPUOut(int zahl) {
        String out = "";
        if ((zahl % 3) == 0) out = "Fizz";
        if ((zahl % 5) == 0) out = "Buzz";
        if (((zahl % 3) == 0) && ((zahl % 5) == 0)) out = "FizzBuzz";
        if (!((zahl % 3) == 0) && !((zahl % 5) == 0)) out = String.valueOf(zahl);
        return out;
    }
}