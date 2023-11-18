public class Watek extends Thread {

    private Obraz obraz;
    private char c;
    private int id;

    public Watek(Obraz obraz, char c, int id) {
        this.obraz = obraz;
        this.c = c;
        this.id = id;
    }

    @Override
    public void run() {
        int powtorzenia = obraz.calculate_histogram(c);
        String wiadomosc = "Watek: " + id + " znak: " + c;

        obraz.show(wiadomosc, powtorzenia);
    }


}
