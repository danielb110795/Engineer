package graf;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import lombok.Data;

@Data

/*
 * https://eduinf.waw.pl/inf/alg/001_search/0142.php#P1 Najpierw kolorujemy
 * wierzcho³ki grafu wszystkimi mo¿liwymi kombinacjami dwóch pierwszych kolorów.
 * Przy ka¿dej kombinacji sprawdzamy warunek pokolorowania – ¿adne dwa s¹siednie
 * wierzcho³ki nie s¹ tego samego koloru. Jeœli warunek bêdzie spe³niony,
 * koñczymy.
 * 
 * W kolejnych krokach zwiêkszamy liczbê kombinacji kolorów do 3, 4, ..., n i
 * powtarzamy powy¿szy krok a¿ do znalezienia takiej kombinacji, która bêdzie
 * spe³nia³a warunek pokolorowania grafu.
 */

public class KolorowanieDokladne {

	private int nrWierzcholka;
	private int i = 0;
	private int liczbaUzytychKolorow = 2;
	private int licznikKolorow = 0;
	private boolean test;
	private int[] tabKolorow;
	private int liczbaWierzcholkow;

	public void kolorujDokladnie(LinkedList<Integer> listaSasiedztwa[], int liczbaKrawedzi) {

		liczbaWierzcholkow = listaSasiedztwa.length;
		tabKolorow = new int[liczbaWierzcholkow];

		Arrays.fill(tabKolorow, 0);

		if (liczbaKrawedzi > 1) { // krawêdzi!
			while (true) {
				if (licznikKolorow != 0) {
					test = true;
					for (nrWierzcholka = 0; nrWierzcholka < liczbaWierzcholkow; nrWierzcholka++) {
						Iterator<Integer> iterator = listaSasiedztwa[nrWierzcholka].listIterator();
						while (iterator.hasNext()) {
							int wartosc = iterator.next();
							if (tabKolorow[nrWierzcholka] == tabKolorow[wartosc]) {
								test = false;
								break;
							}
						}
						if (!test)
							break;
					}
					if (test)
						break;
				}
				while (true) {
					for (i = 0; i < liczbaWierzcholkow; i++) {
						tabKolorow[i]++;
						if (tabKolorow[i] == liczbaUzytychKolorow - 1)
							licznikKolorow++;
						if (tabKolorow[i] < liczbaUzytychKolorow)
							break;
						tabKolorow[i] = 0;
						licznikKolorow--;
					}
					if (i < liczbaWierzcholkow)
						break;
					liczbaUzytychKolorow++;
				}
			}
		}
	}
}