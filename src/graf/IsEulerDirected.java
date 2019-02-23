package graf;

import java.util.Iterator;
import java.util.Stack;

import lombok.Data;

@Data

/*
 * https://eduinf.waw.pl/inf/alg/001_search/0136.php W grafie skierowanym
 * istnieje cykl Eulera jeœli wszystkie wierzcho³ki posiadaj¹ równe stopnie
 * wchodz¹ce i wychodz¹ce, wszystkie wierzcho³ki o niezerowym stopniu nale¿¹ do
 * tej samej silnie spójnej sk³adowej. Œcie¿ka Eulera istnieje wtedy, gdy:
 * 
 * tylko w jednym wierzcho³ku zachodzi warunek: stopieñ wychodz¹cy - stopieñ
 * wchodz¹cy = 1 i tylko w jednym innym: stopieñ wchodz¹cy - stopieñ wychodz¹cy
 * = 1, a we wszystkich pozosta³ych wierzcho³kach stopnie wejœciowe i wyjœciowe
 * s¹ równe. wszystkie wierzcho³ki o niezerowym stopniu nale¿¹ do tej samej
 * silnie spójnej sk³adowej.
 */

public class IsEulerDirected {

	public int numerWierzcholka;
	public int[] tabNrWierzcholkow, tabLow, stopnieWchodzace, stopnieWychodzace, tanNrSkladowych;
	public boolean[] czyNaStosie;

	public int isEulerian(Graf graf) {
		int v, licznikWchodzacych, licznikWychodzacych;
		int liczbaWierzcholkow = graf.liczbaWierzcholkow;

		tabNrWierzcholkow = new int[liczbaWierzcholkow];
		czyNaStosie = new boolean[liczbaWierzcholkow];
		tabLow = new int[liczbaWierzcholkow];
		stopnieWchodzace = new int[liczbaWierzcholkow];
		stopnieWychodzace = new int[liczbaWierzcholkow];
		tanNrSkladowych = new int[liczbaWierzcholkow];
		numerWierzcholka = 0; // Zerujemy numer wierzcho³ka

		for (v = 0; v < liczbaWierzcholkow; v++) // Przegl¹damy kolejne wierzcho³ki
			if (tabNrWierzcholkow[v] == 0)
				DFSscc(v, graf); // W wierzcho³ku nieodwiedzonym uruchamiamy DFS

		// Szukamy pierwszego wierzcho³ka o niezerowym stopniu

		for (v = 0; v < liczbaWierzcholkow; v++)
			if (stopnieWchodzace[v] + stopnieWychodzace[v] != 0)
				break;

		if (v == liczbaWierzcholkow)
			return 0; // Graf jest pusty

		numerWierzcholka = tanNrSkladowych[v]; // Zapamiêtujemy numer silnie spójnej sk³adowej
		licznikWchodzacych = licznikWychodzacych = 0;

		for (v = 0; v < liczbaWierzcholkow; v++) // Przegl¹damy graf
			if (stopnieWchodzace[v] + stopnieWychodzace[v] != 0) {
				if (tanNrSkladowych[v] != numerWierzcholka)
					return 0; // graf nie jest silnie spójny
				if (stopnieWchodzace[v] != stopnieWychodzace[v]) {
					if (stopnieWchodzace[v] - stopnieWychodzace[v] == 1) {
						if (++licznikWchodzacych > 1)
							return 0; // Za du¿o wierzcho³ków o nierównych stopniach
					} else if (stopnieWychodzace[v] - stopnieWchodzace[v] == 1) {
						if (++licznikWychodzacych > 1)
							return 0; // Za du¿o wierzcho³ków o nierównych stopniach
					} else
						return 0; // Stopnie ró¿ni¹ siê o wiêcej ni¿ 1
				}
			}

		if (licznikWchodzacych == 1)
			return 1; // mamy œcie¿kê Eulera
		else
			return 2; // mamy cykl Eulera
	}

	// Procedura wykonuj¹ca przejœcie DFS i wyznaczaj¹ca
	// silnie spójn¹ sk³adow¹ oraz stopnie wierzcho³ków
	// v - wierzcho³ek startowy dla DFS
	// ---------------------------------------------------
	public void DFSscc(int nrWierzcholka, Graf graf) {

		Stack<Integer> S = new Stack<Integer>(); // stos wierzcholkow
		int u;

		numerWierzcholka++;
		tabNrWierzcholkow[nrWierzcholka] = tabLow[nrWierzcholka] = numerWierzcholka; // Ustalamy wstêpnie parametr Low
		S.push(nrWierzcholka); // Wierzcho³ek na stos
		czyNaStosie[nrWierzcholka] = true; // Zapamiêtujemy, ¿e v jest na stosie

		Iterator<Integer> iterator = graf.listaSasiedztwa[nrWierzcholka].listIterator();
		while (iterator.hasNext()) { // Przegl¹damy listê s¹siadów
			int p = iterator.next();
			stopnieWychodzace[nrWierzcholka]++; // Wyznaczamy stopieñ wychodz¹cy
			stopnieWchodzace[p]++; // i wchodz¹cy
			if (tabNrWierzcholkow[p] == 0) {
				DFSscc(p, graf);
				tabLow[nrWierzcholka] = Math.min(tabLow[nrWierzcholka], tabLow[p]);
			} else if (czyNaStosie[p]) {
				tabLow[nrWierzcholka] = Math.min(tabLow[nrWierzcholka], tabNrWierzcholkow[p]);
			}
		}

		if (tabLow[nrWierzcholka] == tabNrWierzcholkow[nrWierzcholka]) // Sprawdzamy, czy mamy kompletn¹ sk³adow¹
			do {
				u = S.pop(); // Pobieramy wierzcho³ek ze stosu i go usuwamy
				// System.out.println(u);
				czyNaStosie[u] = false; // Zapamiêtujemy, ¿e nie ma go ju¿ na stosie
				tanNrSkladowych[u] = nrWierzcholka; // Zapamiêtujemy numer silnie spójnej sk³adowej
			} while (u != nrWierzcholka); // Kontynuujemy a¿ do korzenia sk³adowej
	}
}