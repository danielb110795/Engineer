package graf;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import lombok.Data;

@Data

/*
https://eduinf.waw.pl/inf/alg/001_search/0142.php

Kolorowanie sequential nie zmienia ¿adnej kolejnosci wierzcholkow
Kolorje je od razu, w moim przypadku od wierzcholka 0 do N

Dla grafu skierowanego tworzê szkielet grafu w celu poprawnego kolorowania
 */
public class Sequential {
	
	private int i, j, nrWierzcholka;
	private int[] tabKolorow;
	private boolean[] dostepnoscKol;
	private LinkedList<Integer> szkieletListy[];
	
	public void kolorujS(LinkedList<Integer> listaSasiedztwa[], boolean czySkierowany) {
		tabKolorow = new int[listaSasiedztwa.length];
		dostepnoscKol = new boolean[listaSasiedztwa.length];

		rozpoznajGraf(listaSasiedztwa, czySkierowany);
		
		Arrays.fill(tabKolorow, -1);
		tabKolorow[0] = 0;

		for (nrWierzcholka = 1; nrWierzcholka < listaSasiedztwa.length; nrWierzcholka++) {
			Arrays.fill(dostepnoscKol, false);

			Iterator<Integer> iterator = szkieletListy[nrWierzcholka].listIterator();
			while (iterator.hasNext())
			{
				int wartosc = iterator.next();
				if (tabKolorow[wartosc] > -1)
					dostepnoscKol[tabKolorow[wartosc]] = true;
			}

			for (i = 0; dostepnoscKol[i]; i++);
				tabKolorow[nrWierzcholka] = i;
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