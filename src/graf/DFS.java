package graf;

import java.util.Iterator;
import java.util.LinkedList;

import lombok.Data;

@Data
/*
https://pl.wikipedia.org/wiki/Przeszukiwanie_w_g³¹b
https://eduinf.waw.pl/inf/alg/001_search/0125.php#P1

Algorytm dzia³a dla wszystkich grafów, jednak mo¿e nie odwiedziæ on wszystkich wierzcho³ków
Odwiedzi te wierzcholki do których jest dostêp z wierzcho³ka startowego

*/
public class DFS {

	private String zwracanyWynik = "";
	
	public void DFSMacierzSasiedztwa(int startowyWierzcholek, int[][] macierzSasiedztwa, boolean[] odwiedzone) {
		int i;
		odwiedzone[startowyWierzcholek] = true;
		zwracanyWynik += startowyWierzcholek + " ";
		for (i = 0; i < macierzSasiedztwa.length; i++)
			if ((macierzSasiedztwa[startowyWierzcholek][i] == 1) && !odwiedzone[i])
				DFSMacierzSasiedztwa(i, macierzSasiedztwa, odwiedzone);
	}

	public void DFSListaSasiedztwa(int startowyWierzcholek, LinkedList<Integer> listaSasiedztwa[], boolean[] odwiedzone) {
		odwiedzone[startowyWierzcholek] = true;
		zwracanyWynik += startowyWierzcholek + " ";
		Iterator<Integer> i = listaSasiedztwa[startowyWierzcholek].listIterator();
		while (i.hasNext()) {
			int n = i.next();
			if (!odwiedzone[n]) {
				DFSListaSasiedztwa(n, listaSasiedztwa, odwiedzone);
			}
		}
	}
}