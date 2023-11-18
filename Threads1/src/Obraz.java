import  java.util.Random;


class Obraz {

    private int size_n;
    private int size_m;
    private char[][] tab;
    private char[] tab_symb;
    private int[] histogram;

    private int[] hist_parallel;
    private int[] hist_parallel_2;

    public Obraz(int n, int m) {

        this.size_n = n;
        this.size_m = m;
        tab = new char[n][m];
        tab_symb = new char[94];

        final Random random = new Random();

        // for general case where symbols could be not just integers
        for(int k=0;k<94;k++) {
            tab_symb[k] = (char)(k+33); // substitute symbols
        }

        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                tab[i][j] = tab_symb[random.nextInt(94)];  // ascii 33-127
                //tab[i][j] = (char)(random.nextInt(94)+33);  // ascii 33-127
                System.out.print(tab[i][j]+" ");
            }
            System.out.print("\n");
        }
        System.out.print("\n\n");

        hist_parallel = new int[94];
        hist_parallel_2 = new int[94];
        histogram = new int[94];
        clear_histogram();
    }

    public void clear_histogram(){

        for(int i=0;i<94;i++) {
            histogram[i]=0;
            hist_parallel[i]=0;
            hist_parallel_2[i] = 0;
            //hist_parallel[i]= 8;
            //hist_parallel_2[i] = 8;
        }

    }

    public void calculate_histogram(){

        for(int i=0;i<size_n;i++) {
            for(int j=0;j<size_m;j++) {

                for(int k=0;k<94;k++) {
                    if(tab[i][j] == tab_symb[k]) histogram[k]++;
                    //if(tab[i][j] == (char)(k+33)) histogram[k]++;
                }

            }
        }

    }

    public int calculate_histogram(char c){

        for(int i=0;i<size_n;i++) {
            for (int j = 0; j < size_m; j++) {

                if (tab[i][j] == c) {
                    hist_parallel[(int) c - 33]++;
                }
            }
        }
            if (histogram[(int) c -33] != hist_parallel[(int) c - 33]) {
                System.out.println("Brak zgodności");
            }
            return hist_parallel[(int) c -33];
    }

    public void calculate_histogram(int start, int koniec) {
        for(int i = start; i < koniec; i++) {
            for(int j = 0; j < size_n; j++) {
                for(int z = 0; z <size_m; z++) {
                    if (tab_symb[i] == tab[j][z]) hist_parallel_2[i]++;
                }
            }
        }
    }



    public void print_histogram(){

        for(int i=0;i<94;i++) {
            System.out.print(tab_symb[i]+" "+histogram[i]+"\n");
            //System.out.print((char)(i+33)+" "+histogram[i]+"\n");
        }

    }

    public synchronized void show(String wiadomosc, int powtorzenia) {
        System.out.print(wiadomosc);
        for (int i = 0 ; i < powtorzenia; i++) {
            System.out.print("=");
        }
        System.out.println();
    }

    public synchronized void show(int start, int koniec, int id) {
        for (int i = start; i < koniec; i++) {
            System.out.print("Watek " + id + ": znak" + (char)(i+33));
            for (int j = 0; j <hist_parallel_2[i]; j++) {
                System.out.print("=");
            }
            System.out.println();
        }
    }

    public void isTableEqual() {
        for (int i = 0; i < histogram.length; i++) {
            if (histogram[i] != hist_parallel[i]) {
                System.out.println("Brak zgodnosci");
                return;
            }
        }
        System.out.println("Tablice sa takie same");
    }


}