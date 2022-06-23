import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Einkaufsliste {
    List<String> einkaufsliste = new ArrayList<>();
    File datei = new File("rezepte.json");
    String rezepteDateiInhalt = Files.readString(Paths.get(datei.toURI()));
    JSONObject rezepteJsonObject = new JSONObject(rezepteDateiInhalt);
    Map<String, Object> rezepteMap = rezepteJsonObject.toMap();

    public Einkaufsliste() throws IOException {
    }

    public List<String> rezepteAnzeigen() {
        List<String> rezeptListe = new ArrayList<>();
        rezepteMap.forEach((String rezepte, Object rezeptObject) -> {
            JSONObject tempJsonObject = new JSONObject(rezeptObject.toString().replace('=', ':'));
            Map<String, Object> rezeptDetailsMap = tempJsonObject.toMap();

            rezeptDetailsMap.forEach((String detail, Object rezeptDetailObject) -> {
                if (detail.contains("name")) {
                    rezeptListe.add(rezeptDetailObject.toString());
                }
            });
        });
        return rezeptListe;
    }

    public void rezeptHinzufuegen(String auswahl) {
        JSONObject rezeptDetailsObject = new JSONObject(rezepteMap.get("rezept" + auswahl)
                .toString().replace('=', ':'));
        Map<String, Object> rezeptDetailsMap = rezeptDetailsObject.toMap();

        rezeptDetailsMap.forEach((String detail, Object rezeptDetailObject) -> {
            if (detail.contains("zutat")) {
                einkaufsliste.add(rezeptDetailObject.toString());
            }
        });
    }

    public void listeAnzeigen() {
        einkaufsliste.forEach(System.out::println);
    }
}
