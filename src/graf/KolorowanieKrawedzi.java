package graf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Data;

@Data

public class KolorowanieKrawedzi {

    private List<Integer[]> krawedzie;
    private int kolory[];
    private int liczbaWierzcholkow;

    public void kolorujKrawedzie(int[][] macierzSasiedztwa, boolean czySkierowany) {

        krawedzie = new ArrayList<>();
        liczbaWierzcholkow = macierzSasiedztwa.length;

        for (int i = 0; i < liczbaWierzcholkow; i++) {
            for (int j = 0; j < liczbaWierzcholkow; j++) {
                for (int k = 0; k < macierzSasiedztwa[i][j]; k++) {
                    if (czySkierowany || (!czySkierowany && i <= j)) {
                        krawedzie.add(new Integer[]{i, j});
                    }
                }
            }
        }

        int kolor = 0;
        kolory = new int[krawedzie.size()];
        Arrays.fill(kolory, -1);
        for (int i = 0; i < kolory.length; i++) {
            if (kolory[i] == -1) {
                kolory[i] = kolor;
                kolor = (kolor + 1);

            }
            for (int j = i + 1; j < kolory.length; j++) {

                if (kolory[j] == -1) {
                    boolean krawedzSasiednia = false;
                    for (int k = 0; k < kolory.length; k++) {
                        if (k != j && kolory[k] == kolory[i]) {
                            if (czyKrawedzSasiednia(k, j)) {
                                krawedzSasiednia = true;
                                break;
                            }
                        }
                    }
                    if (!krawedzSasiednia) {
                        kolory[j] = kolory[i];
                    }
                }
            }
        }
    }

    boolean czyKrawedzSasiednia(int i, int j) {

        int poczatek1 = krawedzie.get(i)[0];
        int koniec1 = krawedzie.get(i)[1];

        int poczatek2 = krawedzie.get(j)[0];
        int koniec2 = krawedzie.get(j)[1];

        return poczatek1 == poczatek2
                || poczatek1 == koniec2
                || koniec1 == poczatek2
                || koniec1 == koniec2;
    }
}