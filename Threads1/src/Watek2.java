public class Watek2 implements Runnable {


    private Obraz obraz;
    private int id;
    private int N;
    private int l_w;

    public Watek2(Obraz obraz, int id, int N, int l_w) {
        this.obraz = obraz;
        this.id = id;
        this.N = N; // liczba znakow
        this.l_w = l_w;
    }

    @Override
    public void run() {
        int start = (int) Math.ceil((double) N / (double) l_w * id);
        int koniec = (int) Math.ceil((double) N / (double) l_w * (id + 1));
        koniec = Math.min(koniec, N);

        obraz.calculate_histogram(start, koniec);
        obraz.show(start, koniec, id);
    }

}
