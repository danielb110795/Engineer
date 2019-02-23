package graf;

import java.util.Iterator;
import java.util.Stack;

import lombok.Data;

@Data

/*
 * https://eduinf.waw.pl/inf/alg/001_search/0136.php W grafie skierowanym
 * istnieje cykl Eulera je�li wszystkie wierzcho�ki posiadaj� r�wne stopnie
 * wchodz�ce i wychodz�ce, wszystkie wierzcho�ki o niezerowym stopniu nale�� do
 * tej samej silnie sp�jnej sk�adowej. �cie�ka Eulera istnieje wtedy, gdy:
 * 
 * tylko w jednym wierzcho�ku zachodzi warunek: stopie� wychodz�cy - stopie�
 * wchodz�cy = 1 i tylko w jednym innym: stopie� wchodz�cy - stopie� wychodz�cy
 * = 1, a we wszystkich pozosta�ych wierzcho�kach stopnie wej�ciowe i wyj�ciowe
 * s� r�wne. wszystkie wierzcho�ki o niezerowym stopniu nale�� do tej samej
 * silnie sp�jnej sk�adowej.
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
		numerWierzcholka = 0; // Zerujemy numer wierzcho�ka

		for (v = 0; v < liczbaWierzcholkow; v++) // Przegl�damy kolejne wierzcho�ki
			if (tabNrWierzcholkow[v] == 0)
				DFSscc(v, graf); // W wierzcho�ku nieodwiedzonym uruchamiamy DFS

		// Szukamy pierwszego wierzcho�ka o niezerowym stopniu

		for (v = 0; v < liczbaWierzcholkow; v++)
			if (stopnieWchodzace[v] + stopnieWychodzace[v] != 0)
				break;

		if (v == liczbaWierzcholkow)
			return 0; // Graf jest pusty

		numerWierzcholka = tanNrSkladowych[v]; // Zapami�tujemy numer silnie sp�jnej sk�adowej
		licznikWchodzacych = licznikWychodzacych = 0;

		for (v = 0; v < liczbaWierzcholkow; v++) // Przegl�damy graf
			if (stopnieWchodzace[v] + stopnieWychodzace[v] != 0) {
				if (tanNrSkladowych[v] != numerWierzcholka)
					return 0; // graf nie jest silnie sp�jny
				if (stopnieWchodzace[v] != stopnieWychodzace[v]) {
					if (stopnieWchodzace[v] - stopnieWychodzace[v] == 1) {
						if (++licznikWchodzacych > 1)
							return 0; // Za du�o wierzcho�k�w o nier�wnych stopniach
					} else if (stopnieWychodzace[v] - stopnieWchodzace[v] == 1) {
						if (++licznikWychodzacych > 1)
							return 0; // Za du�o wierzcho�k�w o nier�wnych stopniach
					} else
						return 0; // Stopnie r�ni� si� o wi�cej ni� 1
				}
			}

		if (licznikWchodzacych == 1)
			return 1; // mamy �cie�k� Eulera
		else
			return 2; // mamy cykl Eulera
	}

	// Procedura wykonuj�ca przej�cie DFS i wyznaczaj�ca
	// silnie sp�jn� sk�adow� oraz stopnie wierzcho�k�w
	// v - wierzcho�ek startowy dla DFS
	// ---------------------------------------------------
	public void DFSscc(int nrWierzcholka, Graf graf) {

		Stack<Integer> S = new Stack<Integer>(); // stos wierzcholkow
		int u;

		numerWierzcholka++;
		tabNrWierzcholkow[nrWierzcholka] = tabLow[nrWierzcholka] = numerWierzcholka; // Ustalamy wst�pnie parametr Low
		S.push(nrWierzcholka); // Wierzcho�ek na stos
		czyNaStosie[nrWierzcholka] = true; // Zapami�tujemy, �e v jest na stosie

		Iterator<Integer> iterator = graf.listaSasiedztwa[nrWierzcholka].listIterator();
		while (iterator.hasNext()) { // Przegl�damy list� s�siad�w
			int p = iterator.next();
			stopnieWychodzace[nrWierzcholka]++; // Wyznaczamy stopie� wychodz�cy
			stopnieWchodzace[p]++; // i wchodz�cy
			if (tabNrWierzcholkow[p] == 0) {
				DFSscc(p, graf);
				tabLow[nrWierzcholka] = Math.min(tabLow[nrWierzcholka], tabLow[p]);
			} else if (czyNaStosie[p]) {
				tabLow[nrWierzcholka] = Math.min(tabLow[nrWierzcholka], tabNrWierzcholkow[p]);
			}
		}

		if (tabLow[nrWierzcholka] == tabNrWierzcholkow[nrWierzcholka]) // Sprawdzamy, czy mamy kompletn� sk�adow�
			do {
				u = S.pop(); // Pobieramy wierzcho�ek ze stosu i go usuwamy
				// System.out.println(u);
				czyNaStosie[u] = false; // Zapami�tujemy, �e nie ma go ju� na stosie
				tanNrSkladowych[u] = nrWierzcholka; // Zapami�tujemy numer silnie sp�jnej sk�adowej
			} while (u != nrWierzcholka); // Kontynuujemy a� do korzenia sk�adowej
	}
}