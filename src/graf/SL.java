package graf;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import lombok.Data;

@Data

/*Algorytm SL (smallest last)
Algorytm SL wygl¹da nastêpuj¹co:

ZnajdŸ wierzcho³ek o minimalnym stopniu i usuñ go z grafu.
Powtarzaj krok pierwszy tak d³ugo, a¿ graf bêdzie pusty (zapamiêtaj kolejnoœæ usuwanych wierzcho³ków).
Koloruj wierzcho³ki zach³annie, zgodnie z ustalon¹ wczeœniej kolejnoœci¹ (zaczynaj¹c od wierzcho³ków usuniêtych póŸniej).
Algorytm SL jest statyczny, jego z³o¿onoœæ wynosi {\displaystyle O(n+m)} , gdzie {\displaystyle n}  - liczba wierzcho³ków, {\displaystyle m}  - liczba krawêdzi.
*/

public class SL {

	private LinkedList<Integer> listaSasiedztwaKopia[], szkieletListy[];
	private int[] kolejnosc, tabKolorowWierzcholkow;
	private int i, j, k, nrWierzcholka, liczbaWierzcholkow;
	private boolean[] kolory;
	
	public void kolorujSL(LinkedList<Integer> listaSasiedztwa[], boolean czySkierowany) {
		liczbaWierzcholkow = listaSasiedztwa.length;
		kolejnosc = new int[liczbaWierzcholkow];
		tabKolorowWierzcholkow = new int[liczbaWierzcholkow];
		kolory = new boolean[liczbaWierzcholkow];
		listaSasiedztwaKopia = Graf.kopiujListeSasiedztwa(listaSasiedztwa);
		
		//ustalamy kolejnoœæ wierzcho³ków
		for (i = 0; i < liczbaWierzcholkow; i++)
		{
			for (j = 0; j < liczbaWierzcholkow; j++) // wyszukuje nie nulla i przypisuje nr wierzcholka
				if (listaSasiedztwaKopia[j] != null) {
					kolejnosc[i] = j;
					break;
				}
			for (int j = 0; j < liczbaWierzcholkow; j++) {
				if (listaSasiedztwaKopia[j] == null)
					continue;
				if (listaSasiedztwaKopia[kolejnosc[i]].size() > listaSasiedztwaKopia[j].size())
					kolejnosc[i] = j;
			}

			listaSasiedztwaKopia[kolejnosc[i]] = null;
			for (k = 0; k < liczbaWierzcholkow; k++)
				if (listaSasiedztwaKopia[k] != null)
					listaSasiedztwaKopia[k].removeIf(s -> s == kolejnosc[i]);
		}

		//kolorowanie zach³anne wed³ug odpowiedniej kolejnoœci ustalonej powy¿ej
		Arrays.fill(tabKolorowWierzcholkow, -1);

		kolejnosc = Graf.odwrocTabliceInt(kolejnosc);
		tabKolorowWierzcholkow[kolejnosc[0]] = 0;
		
		rozpoznajGraf(listaSasiedztwa, czySkierowany);
		
		for (nrWierzcholka = 1; nrWierzcholka < liczbaWierzcholkow; nrWierzcholka++) {
			for (i = 0; i < liczbaWierzcholkow; i++)
				kolory[i] = false;
			Iterator<Integer> iterator = szkieletListy[kolejnosc[nrWierzcholka]].listIterator();
			while (iterator.hasNext()) {
				int wartosc = iterator.next();
				if (tabKolorowWierzcholkow[wartosc] > -1)
					kolory[tabKolorowWierzcholkow[wartosc]] = true;
			}
			for (i = 0; kolory[i]; i++);
				tabKolorowWierzcholkow[kolejnosc[nrWierzcholka]] = i;
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