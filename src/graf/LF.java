package graf;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import lombok.Data;

@Data
/*
https://eduinf.waw.pl/inf/alg/001_search/0142.php
https://pl.wikipedia.org/wiki/Kolorowanie_grafu

Kolorowanie grafu za pomoc¹ algorytmu LF mo¿na opisaæ nastêpuj¹co:

Uporz¹dkuj wierzcho³ki grafu malej¹co wed³ug ich stopni (liczby krawêdzi z nich wychodz¹cych).
Koloruj wierzcho³ki zach³annie, zgodnie z ustalon¹ wczeœniej kolejnoœci¹ (zaczynaj¹c od wierzcho³ka o najwiêkszym stopniu).
 */

public class LF {
	private int i, nrWierzcholka, tmp = 0;
	private int[] tabKolorowWierzcholkow, tabStopniWyj, tabNrWierzcholkow;
	private boolean[] kolory;
	private LinkedList<Integer> szkieletListy[];
	
	public void koloruj(LinkedList<Integer> listaSasiedztwa[], boolean czySkierowany) {
		int liczbaWierzcholkow = listaSasiedztwa.length;
		tabKolorowWierzcholkow = new int[liczbaWierzcholkow];
		tabStopniWyj = new int[liczbaWierzcholkow];
		tabNrWierzcholkow = new int[liczbaWierzcholkow];
		kolory = new boolean[liczbaWierzcholkow];

		for (nrWierzcholka = 0; nrWierzcholka < liczbaWierzcholkow; nrWierzcholka++) {
			tabNrWierzcholkow[nrWierzcholka] = nrWierzcholka;
			tabStopniWyj[nrWierzcholka] = listaSasiedztwa[nrWierzcholka].size();

			tmp = tabStopniWyj[nrWierzcholka];
			for (i = nrWierzcholka; (i > 0) && (tabStopniWyj[i - 1] < tmp); i--) {
				tabStopniWyj[i] = tabStopniWyj[i - 1];
				tabNrWierzcholkow[i] = tabNrWierzcholkow[i - 1];
			}
			tabStopniWyj[i] = tmp;
			tabNrWierzcholkow[i] = nrWierzcholka;
		}

		rozpoznajGraf(listaSasiedztwa, czySkierowany);	
		Arrays.fill(tabKolorowWierzcholkow, -1);
		tabKolorowWierzcholkow[tabNrWierzcholkow[0]] = 0;

		for (nrWierzcholka = 1; nrWierzcholka < liczbaWierzcholkow; nrWierzcholka++) {
			Arrays.fill(kolory, false);
			Iterator<Integer> iterator = szkieletListy[tabNrWierzcholkow[nrWierzcholka]].listIterator();
			while (iterator.hasNext()) {
				int wartosc = iterator.next();
				if (tabKolorowWierzcholkow[wartosc] > -1)
					kolory[tabKolorowWierzcholkow[wartosc]] = true;
			}
			for (i = 0; kolory[i]; i++);
			tabKolorowWierzcholkow[tabNrWierzcholkow[nrWierzcholka]] = i;
		}
	}
	
	private void rozpoznajGraf (LinkedList<Integer> listaSasiedztwa[], boolean czySkierowany)
	{
		if (czySkierowany == true) 
			szkieletListy = Graf.stworzSzkieletGrafu(listaSasiedztwa);
		else
			szkieletListy = listaSasiedztwa;
	}
}