import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GUI {
    Einkaufsliste einkaufsliste;

    public GUI(Einkaufsliste einkaufsliste) {
        this.einkaufsliste = einkaufsliste;
    }

    public void startScreen() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("#----------- EINKAUFSLISTENGENERATOR ----------#");
        System.out.println("|");
        System.out.println("|  1 -> Rezept zur Einkaufsliste hinzufuegen");
        System.out.println("|  2 -> Produkte zur Einkaufsliste hinzufuegen");
        System.out.println("|");
        System.out.println("|  3 -> Einkaufsliste sortieren");
        System.out.println("|  4 -> Einkaufsliste anzeigen");
        System.out.println("|");
        System.out.println("|  5 -> Einkaufslistengenerator beenden");
        System.out.println("|");
        System.out.println("#----------------------------------------------#");
        System.out.println();
        System.out.println("Auswahl: ");

        String auswahl = scanner.next();
        switch (Integer.parseInt(auswahl)) {
            case 1 -> rezeptHinzufuegen();
            case 2 -> produktHinzufuegen();
            case 3 -> listeSortieren();
            case 4 -> einkaufsliste.listeAnzeigen();
            case 5 -> System.exit(0);
            default -> System.out.println("Ungueltige Eingabe");
        }
//        scanner.close();
    }

    public void rezeptHinzufuegen() {
        System.out.println("#------------- REZEPTE HINZUFUEGEN -------------#");
        System.out.println("|");

        List<String> rezeptListe = einkaufsliste.rezepteAnzeigen();
        Collections.reverse(rezeptListe);
        for (int i = 0; i < rezeptListe.size(); i++) {
            System.out.println("|  " + (i + 1) + " -> " + rezeptListe.get(i));
        }

        System.out.println("|");
        System.out.println("#-----------------------------------------------#");
        System.out.println();
        System.out.println("Auswahl: ");

        Scanner scanner = new Scanner(System.in);
        String auswahl = scanner.next();
        einkaufsliste.rezeptHinzufuegen(auswahl);
//        scanner.close();
        startScreen();
    }

    public void produktHinzufuegen() {
        System.out.println("produkt hinzufuegen");
    }

    public void listeSortieren() {
        System.out.println("liste sortieren");
    }
}
