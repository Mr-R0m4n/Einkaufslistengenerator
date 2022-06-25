import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Einkaufsliste {
    List<List> einkaufsliste = new ArrayList<>();
    String[] kategorien = {
            "Obst", "Gemuese", "Konserven",
            "Kaffee und Brot", "Nudeln und Co", "Haushalt",
            "Hygiene", "Kuehltheke", "Fleisch", "Tiefkuehl",
            "Suesses und Knabbern", "Getraenke", "Sonstiges"
    };
    File datei = new File("rezepte.json");
    String rezepteDateiInhalt = Files.readString(Paths.get(datei.toURI()));
    JSONObject rezepteJsonObject = new JSONObject(rezepteDateiInhalt);
    Map<String, Object> rezepteMap = rezepteJsonObject.toMap();

    public Einkaufsliste() throws IOException {
        for (String s : kategorien) {
            einkaufsliste.add(new ArrayList<>(List.of(s)));
        }
    }

    public List<String> rezepteAnzeigen() {
        List<String> rezeptListe = new ArrayList<>();
        rezepteMap.forEach((key, value) -> {
            JSONObject rezept = new JSONObject(value.toString().replace('=', ':'));
            rezeptListe.add(rezept.get("name").toString());
        });
        return rezeptListe;
    }

    public void rezeptHinzufuegen(String auswahl) {
        JSONObject rezeptJsonObject = new JSONObject(rezepteMap.get(auswahl).toString().replace('=', ':'));
        JSONObject zutatenJsonObject = new JSONObject(rezeptJsonObject.get("zutaten").toString());
        Map<String, Object> zutatenMap = zutatenJsonObject.toMap();

        zutatenMap.forEach((key, value) -> einkaufsliste.forEach(kategorie -> {
            if (kategorie.contains(value)) {
                kategorie.add(key);
            }
        }));
    }

    private void einkaufslisteOrganisieren() {

    }

    public void listeAnzeigen() {
        einkaufslisteOrganisieren();
        einkaufsliste.forEach(System.out::println);
    }
}
