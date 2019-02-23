package graf;

import java.util.Iterator;
import java.util.LinkedList;

public class Transpozycja {
	private int i, tmp, tmp2;
	
	public void transponujGraf(Graf graf) {
		
		for (i = 0; i < graf.liczbaKrawedzi; i++)
			graf.grafJung.removeEdge(String.valueOf(i));

		for (i = 0; i < graf.krawedzie.length; i += 2) {
			tmp = graf.krawedzie[i];
			tmp2 = graf.krawedzie[i + 1];
			graf.krawedzie[i] = tmp2;
			graf.krawedzie[i + 1] = tmp;
			graf.grafJung.addEdge(String.valueOf(i / 2), String.valueOf(tmp2), String.valueOf(tmp));
		}

		graf.macierzSasiedztwa = transponujMacierzSasiedztwa(graf.macierzSasiedztwa, graf.liczbaWierzcholkow);
		graf.macierzIncydencji = transponujMacierzIncydencji(graf.macierzIncydencji, graf.liczbaKrawedzi,
				graf.liczbaWierzcholkow);
		graf.listaSasiedztwa = transponujListeSasiedztwa(graf.listaSasiedztwa, graf.liczbaWierzcholkow);
	}

	private int[][] transponujMacierzIncydencji(int[][] macierzIncydencji, int lKrawedzi, int lWierzcholkow) {
		int i, j;
		for (i = 0; i < lKrawedzi; i++)
			for (j = 0; j < lWierzcholkow; j++)
				if (macierzIncydencji[j][i] != 0 && macierzIncydencji[j][i] != 2)
					macierzIncydencji[j][i] = -macierzIncydencji[j][i];
		return macierzIncydencji;
	}

	private int[][] transponujMacierzSasiedztwa(int[][] macierzSasiedztwa, int lWierzcholkow) {
		int i, j;
		int[][] macierz = new int[lWierzcholkow][lWierzcholkow];
		macierz = Graf.wype³nijMacierzZerami(macierz);
		for (i = 0; i < lWierzcholkow; i++)
			for (j = 0; j < lWierzcholkow; j++)
				if (macierzSasiedztwa[j][i] != 0)
					macierz[i][j] = 1;
		return macierz;
	}

	@SuppressWarnings("unchecked")
	private LinkedList<Integer>[] transponujListeSasiedztwa(LinkedList<Integer>[] listaSasiedztwa,
			int lWierzcholkow) {
		int nrWierzcholka, i;
		LinkedList<Integer>[] listaSasiedztwaNowa = new LinkedList[lWierzcholkow];
		for (i = 0; i < lWierzcholkow; i++)
			listaSasiedztwaNowa[i] = new LinkedList<Integer>();

		for (nrWierzcholka = 0; nrWierzcholka < lWierzcholkow; nrWierzcholka++) {
			Iterator<Integer> iterator = listaSasiedztwa[nrWierzcholka].listIterator();
			while (iterator.hasNext()) // Przegl¹damy listê s¹siadów
			{
				int wartosc = iterator.next();
				listaSasiedztwaNowa[wartosc].addFirst(nrWierzcholka);
			}
		}
		return listaSasiedztwaNowa;
	}
}