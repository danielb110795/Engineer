package graf;

import java.util.Iterator;

/*https://www.geeksforgeeks.org/eulerian-path-and-circuit/
 *
 * �cie�ka Eulera (ang Eulerian path) jest �cie�k� w grafie, kt�ra przechodzi dok�adnie jeden 
 * raz przez ka�d� jego kraw�d�. Aby taka �cie�ka istnia�a, graf musi by� sp�jny (z pomini�ciem 
 * wierzcho�k�w  o stopniu 0, czyli bez kraw�dzi) oraz ka�dy jego wierzcho�ek za wyj�tkiem 
 * dw�ch musi posiada� parzysty stopie�.
 * 
 * Cykl Eulera (ang. Eulerian cycle) jest �cie�k� w grafie, kt�ra przechodzi przez wszystkie jego 
 * kraw�dzie i ko�czy si� w wierzcho�ku startowym. Aby istnia� cykl Eulera, graf musi by� sp�jny 
 * (z pomini�ciem wierzcho�k�w  o stopniu 0) oraz ka�dy jego wierzcho�ek musi posiada� stopie� parzysty.
 */

public class IsEulerUndirected {

	public void DFSListaSasiedztwa(int startowyWierzcholek, Graf graf, boolean[] odwiedzone) {
		odwiedzone[startowyWierzcholek] = true;
		Iterator<Integer> i = graf.listaSasiedztwa[startowyWierzcholek].listIterator();
		while (i.hasNext()) {
			int n = i.next();
			if (!odwiedzone[n])
				DFSListaSasiedztwa(n, graf, odwiedzone);
		}
	}

	// Metoda sprawdza czy wszystkie niezerowe wierzcholki s� ze sob� po��czone
	boolean czyWierzcholkiPolaczone(Graf graf) {
		int i;
		boolean odwiedzone[] = new boolean[graf.liczbaWierzcholkow];

		// zaznaczam wszystkie wierzcholki jako nieodwiedzone
		for (i = 0; i < graf.liczbaWierzcholkow; i++)
			odwiedzone[i] = false;

		// Szukam wierzcholka o niezerowym stopniu
		for (i = 0; i < graf.liczbaWierzcholkow; i++)
			if (graf.listaSasiedztwa[i].size() != 0)
				break;

		// Gdy brak wierzcholkow zwracamy true
		if (i == graf.liczbaWierzcholkow)
			return true;

		// Wykonanie DFS
		DFSListaSasiedztwa(i, graf, odwiedzone);

		// Sprawdzam, czy wszystkie niezerowe wierzcho�ki s� odwiedzane
		for (i = 0; i < graf.liczbaWierzcholkow; i++)
			if (odwiedzone[i] == false && graf.listaSasiedztwa[i].size() > 0)
				return false;

		return true;
	}

	public int sprawdzCzyJestCykl(Graf graf) {
		// Sprawd�, czy wszystkie niezerowe wierzcho�ki s� po��czone
		if (czyWierzcholkiPolaczone(graf) == false)
			return 0;

		// Zliczam wierzcholki z nieparzystym stopniem
		int nieparzysteStopnie = 0;
		for (int i = 0; i < graf.liczbaWierzcholkow; i++)
			if (graf.listaSasiedztwa[i].size() % 2 != 0)
				nieparzysteStopnie++;

		// je�li jest wiecej ni� 2 nieparzyste wierzcholki to brak cyklu i sciezki
		if (nieparzysteStopnie > 2)
			return 0;

		// jest 2 nieparzyste to sciezka
		// jesli 0 nieparzystych to cykl
		// Note that odd count can never be 1 for undirected graph
		return (nieparzysteStopnie == 2) ? 1 : 2;
	}
}