import java.io.IOException;

public class Einkaufslistengenerator {
    public void init() throws IOException {
        Einkaufsliste einkaufsliste = new Einkaufsliste();
        GUI gui = new GUI(einkaufsliste);
        gui.startScreen();
    }
}
