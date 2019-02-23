package graf;

import java.util.Iterator;
import java.util.LinkedList;

import lombok.Data;

@Data

/*
https://eduinf.waw.pl/inf/alg/001_search/0126.php
https://pl.wikipedia.org/wiki/Przeszukiwanie_wszerz

Algorytm dzia³a dla wszystkich grafów, jednak mo¿e nie odwiedziæ on wszystkich wierzcho³ków
Odwiedzi te wierzcholki do których jest dostêp z wierzcho³ka startowego
 */

public class BFS {

	private String zwracanyWynik = "";
	private LinkedList<Integer> kolejka = new LinkedList<Integer>();
	private boolean[] odwiedzone;
	private int liczbaWierzcholkow;
	
	public void BFSListaSasiedztwa(int startowyWierzcholek, LinkedList<Integer> listaSasiedztwa[]) {
		
		odwiedzone = new boolean[listaSasiedztwa.length];
		odwiedzone[startowyWierzcholek] = true;
		kolejka.addLast(startowyWierzcholek);

		while (kolejka.size() != 0) {
			startowyWierzcholek = kolejka.poll();
			zwracanyWynik += startowyWierzcholek + " ";
			Iterator<Integer> i = listaSasiedztwa[startowyWierzcholek].listIterator();
			while (i.hasNext()) {
				int n = i.next();
				if (!odwiedzone[n]) {
					odwiedzone[n] = true;
					kolejka.addLast(n);
				}
			}
		}
	}

	
	public void BFSMacierzSasiedztwa(int startowyWierzcholek, int[][] macierzSasiedztwa) {

		int i, element;
		odwiedzone = new boolean[macierzSasiedztwa.length];
		liczbaWierzcholkow = macierzSasiedztwa.length;
		odwiedzone[startowyWierzcholek] = true;
		kolejka.add(startowyWierzcholek);

		while (!kolejka.isEmpty()) {
			element = kolejka.poll();
			i = 0;
			zwracanyWynik += element + " ";
			while (i < liczbaWierzcholkow) {
				if (macierzSasiedztwa[element][i] == 1 && !odwiedzone[i]) {
					kolejka.add(i);
					odwiedzone[i] = true;
				}
				i++;
			}
		}
	}
}