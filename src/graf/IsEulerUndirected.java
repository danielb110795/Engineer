package graf;

import java.util.Iterator;

/*https://www.geeksforgeeks.org/eulerian-path-and-circuit/
 *
 * Œcie¿ka Eulera (ang Eulerian path) jest œcie¿k¹ w grafie, która przechodzi dok³adnie jeden 
 * raz przez ka¿d¹ jego krawêdŸ. Aby taka œcie¿ka istnia³a, graf musi byæ spójny (z pominiêciem 
 * wierzcho³ków  o stopniu 0, czyli bez krawêdzi) oraz ka¿dy jego wierzcho³ek za wyj¹tkiem 
 * dwóch musi posiadaæ parzysty stopieñ.
 * 
 * Cykl Eulera (ang. Eulerian cycle) jest œcie¿k¹ w grafie, która przechodzi przez wszystkie jego 
 * krawêdzie i koñczy siê w wierzcho³ku startowym. Aby istnia³ cykl Eulera, graf musi byæ spójny 
 * (z pominiêciem wierzcho³ków  o stopniu 0) oraz ka¿dy jego wierzcho³ek musi posiadaæ stopieñ parzysty.
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

	// Metoda sprawdza czy wszystkie niezerowe wierzcholki s¹ ze sob¹ po³¹czone
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

		// Sprawdzam, czy wszystkie niezerowe wierzcho³ki s¹ odwiedzane
		for (i = 0; i < graf.liczbaWierzcholkow; i++)
			if (odwiedzone[i] == false && graf.listaSasiedztwa[i].size() > 0)
				return false;

		return true;
	}

	public int sprawdzCzyJestCykl(Graf graf) {
		// SprawdŸ, czy wszystkie niezerowe wierzcho³ki s¹ po³¹czone
		if (czyWierzcholkiPolaczone(graf) == false)
			return 0;

		// Zliczam wierzcholki z nieparzystym stopniem
		int nieparzysteStopnie = 0;
		for (int i = 0; i < graf.liczbaWierzcholkow; i++)
			if (graf.listaSasiedztwa[i].size() % 2 != 0)
				nieparzysteStopnie++;

		// jeœli jest wiecej ni¿ 2 nieparzyste wierzcholki to brak cyklu i sciezki
		if (nieparzysteStopnie > 2)
			return 0;

		// jest 2 nieparzyste to sciezka
		// jesli 0 nieparzystych to cykl
		// Note that odd count can never be 1 for undirected graph
		return (nieparzysteStopnie == 2) ? 1 : 2;
	}
}