package graf;

import java.util.Arrays;

//Warunkiem jest, ¿e œcie¿ka istnieje od wierzcho³ka 0!!!

public class Hamilton {

	private int dlugoscSciezki = 0;
    public int[] sciezka;
    public boolean znalezionoSciezkeHamiltona;
    public boolean znalezionoCyklHamiltona;

    private int liczbaWierzcholkow;
    private int[][] macierzSasiedztwa;

    private boolean naSciezce(int v) {
        for (int i = 0; i < dlugoscSciezki - 1; i++)
            if (sciezka[i] == v)
                return true;
        return false;
    }

    public void szukaj(int w, boolean szukanieCyklu) {

        if (dlugoscSciezki == liczbaWierzcholkow) {
            if (szukanieCyklu && macierzSasiedztwa[w][0] == 1) {
                znalezionoCyklHamiltona = true;
                return;
            } else if (!szukanieCyklu) {
                znalezionoSciezkeHamiltona = true;
                return;
            }
        }

        if (dlugoscSciezki == liczbaWierzcholkow)
            return;

        for (int v = 0; v < liczbaWierzcholkow; v++) {
            if (macierzSasiedztwa[w][v] == 1) {
                sciezka[dlugoscSciezki++] = v;
                macierzSasiedztwa[w][v] = 0;
                if (!naSciezce(v)) {
                    szukaj(v, szukanieCyklu);
                    if (!szukanieCyklu && znalezionoSciezkeHamiltona)
                        return;
                    if (szukanieCyklu && znalezionoCyklHamiltona)
                        return;
                }
                macierzSasiedztwa[w][v] = 1;
                sciezka[--dlugoscSciezki] = -1;
            }
        }
    }

    public void znajdzSciezkeHamiltona(int[][] macierzSasiedztwa) {
        this.macierzSasiedztwa = macierzSasiedztwa;
        this.liczbaWierzcholkow = macierzSasiedztwa.length;
        znalezionoSciezkeHamiltona = false;
        sciezka = new int[liczbaWierzcholkow];

        if (sciezka.length > 0) {
            Arrays.fill(sciezka, -1);
            sciezka[0] = 0;
            dlugoscSciezki = 1;
            szukaj(0, false);
        }
    }

    public void znajdzCyklHamiltona(int[][] macierzSasiedztwa) {
        this.macierzSasiedztwa = macierzSasiedztwa;
        this.liczbaWierzcholkow = macierzSasiedztwa.length;
        znalezionoCyklHamiltona = false;
        sciezka = new int[liczbaWierzcholkow + 1];

        if (sciezka.length > 0) {
            Arrays.fill(sciezka, -1);
            sciezka[0] = 0;
            dlugoscSciezki = 1;
            szukaj(0, true);
        }

        if (znalezionoCyklHamiltona)
            sciezka[liczbaWierzcholkow] = sciezka[0];
    }
}
