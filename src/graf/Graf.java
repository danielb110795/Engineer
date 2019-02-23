package graf;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JTextField;

import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseMultigraph;
import lombok.Data;
import okno.PanelAlgorytmow;
import zmienne.Globalne;

@Data

public class Graf {
	public boolean czyWykorzystywany;
	public boolean czyIstnieje;
	public boolean czySkierowany;
	public boolean czyNieskierowany;
	public int liczbaWierzcholkow;
	public int liczbaKrawedzi;
	public int numerGrafu;
	public int[][] macierzSasiedztwa;
	public int[][] macierzIncydencji;
	public LinkedList<Integer> listaSasiedztwa[];
	public String[] wartoscWierzcholka;
	public Graph<String, String> grafJung = null; // rysowanie grafu!!!
	public int [] krawedzie = null;

	public Graf(boolean czyIstnieje) {
		setCzyIstnieje(czyIstnieje);
	}

	public static int[][] wype³nijMacierzZerami(int[][] macierz) {
		int i, j;
		for (i = 0; i < macierz.length; i++)
			for (j = 0; j < macierz[i].length; j++)
				macierz[i][j] = 0;
		return macierz;
	}

	public static String wypiszMacierz(int[][] macierz) {
		int i, j;
		String info = "";
		for (i = 0; i < macierz.length; i++) {
			info += "| ";
			for (j = 0; j < macierz[i].length; j++) {
				info += macierz[i][j] + " ";
			}
			info += "|\n";
		}
		return info;
	}

	public static String wypiszListeSasiedztwa(LinkedList<Integer> listaSasiedztwa[]) {
		int i;
		String info = "";
		for (i = 0; i < listaSasiedztwa.length; i++) {
			info += "wierzcho³ek " + i + ": " + listaSasiedztwa[i] + "\n";
		}
		return info;
	}

	public static void inicjalizacjaGrafow() {
		int i;
		for (i = 0; i < Globalne.grafy.length; i++)
			Globalne.grafy[i] = new Graf(false);
	}

	public static boolean sprawdzCzyJestMiejsceNaGraf() {
		int tmp = Globalne.aktywnyGraf;
		int numerGrafu = 0;
		int i = 0;
		Globalne.aktywnyGraf = numerGrafu;
		for (i = 0; i < Globalne.grafy.length; i++) {
			if (Globalne.grafy[i].czyIstnieje == false) {
				Globalne.aktywnyGraf = i;
				return true;
			}
		}
		Globalne.aktywnyGraf = tmp;
		return false;
	}

	public static void ustawAktywnyGrafPoPrzerwaniuLubUsunieciu() {
		int numerGrafu = -1;
		int i = 0;
		Globalne.aktywnyGraf = numerGrafu;
		for (i = 0; i < Globalne.grafy.length; i++) {
			if (Globalne.grafy[i].czyIstnieje == true) {
				Globalne.aktywnyGraf = i;
				return;
			}
		}
	}

    public static int[][] kopiujMacierzSasiedztwa(int[][] macierzSasiedztwa) {
        int[][] kopiaMacierzySasiedztwa = new int[macierzSasiedztwa.length][macierzSasiedztwa.length];
        for (int i = 0; i < macierzSasiedztwa.length; i++) {
            System.arraycopy(macierzSasiedztwa[i], 0, kopiaMacierzySasiedztwa[i], 0, macierzSasiedztwa.length);
        }
        return kopiaMacierzySasiedztwa;
    }
    
	public static boolean sprawdzCzyToLiczba(String liczba) {
		if (Pattern.matches("[0-9]*", liczba))
			return true;
		else
			return false;
	}

	public static int sprawdzWierzcholkoStartoweKoncowe(JTextField[] tablicaTextFieldKrawedzie, int liczbaW) {
		int i;
		boolean czyLiczba = true;
		for (i = 0; i < tablicaTextFieldKrawedzie.length; i++) {
			czyLiczba = sprawdzCzyToLiczba(tablicaTextFieldKrawedzie[i].getText());
			if (czyLiczba == false)
				return -1; // blad nie podano liczby
			int liczba = Integer.parseInt(tablicaTextFieldKrawedzie[i].getText());
			if (liczba < 0 || liczba > liczbaW - 1)
				return -2; // blad przeroczono zakres
		}
		return 1; // poprawne
	}

	public static int sprawdzCzyPoprawneWartosciWierzcholkow(JTextField[] tablicaTextFieldWierzcholki) {
		int i;

		for (i = 0; i < tablicaTextFieldWierzcholki.length; i++) {
			if (tablicaTextFieldWierzcholki[i].getText().isEmpty()
					|| tablicaTextFieldWierzcholki[i].getText() == null) {
				return -1; // wartosc nie moze byc pusta jak wybrano graf z wartosciami
			}
		}
		return 1; // poprawne
	}

	@SuppressWarnings("unchecked")
	public static void stworzGraf(JTextField[] tablicaTextField, JLabel[] tablicaJLabel) {
		int i = 0;
		int[][] macierzSasiedztwa;
		int[][] macierzIncydencji;
		LinkedList<Integer> listaSasiedztwa[];
		int wierzcholekStartowy, wierzcholekKoncowy;
		int tmp = 0;

		int liczbaWierzcholkow = Globalne.grafy[Globalne.aktywnyGraf].liczbaWierzcholkow;
		int liczbaKrawedzi = Globalne.grafy[Globalne.aktywnyGraf].liczbaKrawedzi;

		if (Globalne.grafy[Globalne.aktywnyGraf].czySkierowany == true)
			Globalne.grafy[Globalne.aktywnyGraf].grafJung = new DirectedSparseMultigraph<String, String>();
		else
			Globalne.grafy[Globalne.aktywnyGraf].grafJung = new UndirectedSparseMultigraph<String, String>();

		if (Globalne.grafy[Globalne.aktywnyGraf].wartoscWierzcholka == null) {
			for (int j = 0; j < liczbaWierzcholkow; j++) {
				Globalne.grafy[Globalne.aktywnyGraf].grafJung.addVertex(String.valueOf(j));
			}
		} else {
			for (int j = 0; j < liczbaWierzcholkow; j++) {
				Globalne.grafy[Globalne.aktywnyGraf].grafJung
						.addVertex(Globalne.grafy[Globalne.aktywnyGraf].wartoscWierzcholka[j]);
			}
		}

		if(liczbaKrawedzi > 0)
			Globalne.grafy[Globalne.aktywnyGraf].krawedzie = new int[liczbaKrawedzi * 2];
		
		macierzSasiedztwa = new int[liczbaWierzcholkow][liczbaWierzcholkow];
		macierzSasiedztwa = Graf.wype³nijMacierzZerami(macierzSasiedztwa);

		macierzIncydencji = new int[liczbaWierzcholkow][liczbaKrawedzi];
		macierzIncydencji = Graf.wype³nijMacierzZerami(macierzIncydencji);

		listaSasiedztwa = new LinkedList[liczbaWierzcholkow];
		for (i = 0; i < liczbaWierzcholkow; i++)
			listaSasiedztwa[i] = new LinkedList<Integer>();

		Globalne.grafy[Globalne.aktywnyGraf].czyIstnieje = true;
		Globalne.grafy[Globalne.aktywnyGraf].czyWykorzystywany = true;
		Globalne.grafy[Globalne.aktywnyGraf].numerGrafu = Globalne.aktywnyGraf;

		for (i = 0; i < Globalne.grafy.length; i++) {
			if (Globalne.grafy[i].numerGrafu != Globalne.aktywnyGraf && Globalne.grafy[i].czyIstnieje == true) {
				Globalne.grafy[i].czyWykorzystywany = false;
			}
		}

		// odczytac pola z tablicy fieldow i stworzyc macierze
		for (i = 0; i < Globalne.grafy[Globalne.aktywnyGraf].liczbaKrawedzi; i++) {
			wierzcholekStartowy = Integer.parseInt(tablicaTextField[tmp].getText());
			wierzcholekKoncowy = Integer.parseInt(tablicaTextField[tmp + 1].getText());
			if(liczbaKrawedzi > 0) {
				Globalne.grafy[Globalne.aktywnyGraf].krawedzie[tmp] = wierzcholekStartowy;
				Globalne.grafy[Globalne.aktywnyGraf].krawedzie[tmp+1] = wierzcholekKoncowy;
			}
			if (Globalne.grafy[Globalne.aktywnyGraf].czyNieskierowany == true) {
				if (Globalne.grafy[Globalne.aktywnyGraf].wartoscWierzcholka == null)
					Globalne.grafy[Globalne.aktywnyGraf].grafJung.addEdge(String.valueOf(i),
							String.valueOf(wierzcholekStartowy), String.valueOf(wierzcholekKoncowy));
				else
					Globalne.grafy[Globalne.aktywnyGraf].grafJung.addEdge(String.valueOf(i),
							Globalne.grafy[Globalne.aktywnyGraf].wartoscWierzcholka[Integer.parseInt(tablicaTextField[tmp].getText())],
							Globalne.grafy[Globalne.aktywnyGraf].wartoscWierzcholka[Integer.parseInt(tablicaTextField[tmp + 1].getText())]);
				macierzSasiedztwa[wierzcholekStartowy][wierzcholekKoncowy] = 1;
				macierzSasiedztwa[wierzcholekKoncowy][wierzcholekStartowy] = 1;

				if (wierzcholekStartowy != wierzcholekKoncowy) {
					macierzIncydencji[wierzcholekStartowy][i] = 1; // Wierzcho³ek startowy
					macierzIncydencji[wierzcholekKoncowy][i] = 1; // Wierzcho³ek koñcowy
				} else {
					macierzIncydencji[wierzcholekStartowy][i] = 2;
				}

				listaSasiedztwa[wierzcholekStartowy].add(wierzcholekKoncowy);
				listaSasiedztwa[wierzcholekKoncowy].add(wierzcholekStartowy);
			} else {
				if (Globalne.grafy[Globalne.aktywnyGraf].wartoscWierzcholka == null)
					Globalne.grafy[Globalne.aktywnyGraf].grafJung.addEdge(String.valueOf(i),
							String.valueOf(wierzcholekStartowy), String.valueOf(wierzcholekKoncowy));
				else
					Globalne.grafy[Globalne.aktywnyGraf].grafJung.addEdge(String.valueOf(i),
							Globalne.grafy[Globalne.aktywnyGraf].wartoscWierzcholka[Integer.parseInt(tablicaTextField[tmp].getText())],
							Globalne.grafy[Globalne.aktywnyGraf].wartoscWierzcholka[Integer.parseInt(tablicaTextField[tmp + 1].getText())]);
				macierzSasiedztwa[wierzcholekStartowy][wierzcholekKoncowy] = 1;

				if (wierzcholekStartowy != wierzcholekKoncowy) {
					macierzIncydencji[wierzcholekStartowy][i] = 1; // Wierzcho³ek startowy
					macierzIncydencji[wierzcholekKoncowy][i] = -1; // Wierzcho³ek koñcowy
				} else {
					macierzIncydencji[wierzcholekStartowy][i] = 2;
				}

				listaSasiedztwa[wierzcholekStartowy].addFirst(wierzcholekKoncowy);
			}
			tmp += 2;
		}

		Globalne.grafy[Globalne.aktywnyGraf].macierzSasiedztwa = macierzSasiedztwa;
		Globalne.grafy[Globalne.aktywnyGraf].macierzIncydencji = macierzIncydencji;
		Globalne.grafy[Globalne.aktywnyGraf].listaSasiedztwa = listaSasiedztwa;

		Globalne.czyToNowyGraf = true;
		PanelAlgorytmow.aktualizujWyborGrafu();
		PanelAlgorytmow.aktualizujWyborWierzcholka();
	}

	public static void ustawWartosciGrafuUsunietego(int tmp) {
		Globalne.grafy[tmp].setCzyWykorzystywany(false);
		Globalne.grafy[tmp].setCzyIstnieje(false);
		Globalne.grafy[tmp].setCzySkierowany(false);
		Globalne.grafy[tmp].setCzyNieskierowany(false);
		Globalne.grafy[tmp].setLiczbaWierzcholkow(-1);
		Globalne.grafy[tmp].setLiczbaKrawedzi(-1);
		Globalne.grafy[tmp].setNumerGrafu(-1);
		Globalne.grafy[tmp].setMacierzSasiedztwa(null);
		Globalne.grafy[tmp].setMacierzIncydencji(null);
		Globalne.grafy[tmp].setListaSasiedztwa(null);
		Globalne.grafy[tmp].setWartoscWierzcholka(null);
	}

	public static void usunWszystkieGrafy() {
		int i = 0;
		for (i = 0; i < Globalne.grafy.length; i++) {
			if (Globalne.grafy[i].czyIstnieje == true) {
				ustawWartosciGrafuUsunietego(i);
			}
		}
		Globalne.aktywnyGraf = -1;
	}

	public static void usunWybranyGraf(int wybranyGraf) {
		ustawWartosciGrafuUsunietego(wybranyGraf);
		ustawAktywnyGrafPoPrzerwaniuLubUsunieciu();
	}

	public static int sprawdzIloscIstniejacychGrafow() {
		int ilosc = 0;
		int i = 0;
		for (i = 0; i < Globalne.grafy.length; i++) {
			if (Globalne.grafy[i].czyIstnieje == true)
				ilosc++;
		}
		return ilosc;
	}
	

	public static boolean sprawdzCzyGrafMaPetle(Graf graf) {
		int i, j;
		for (i = 0; i < graf.macierzIncydencji.length; i++) {
			for (j = 0; j < graf.macierzIncydencji[i].length; j++) {
				if (graf.macierzIncydencji[i][j] == 2) {
					return false; // sa petle
				}
			}
		}
		return true; // nie ma petli
	}
	
	public static boolean sprawdzCzyToGrafProsty(Graf graf) {
		int i, j;
		int tab[] = new int [graf.liczbaWierzcholkow];
		
		for (i = 0; i < graf.liczbaWierzcholkow; i++) {
			Arrays.fill(tab, 0);
			Iterator<Integer> iterator = graf.listaSasiedztwa[i].listIterator();
			while (iterator.hasNext()) {
				int wartosc = iterator.next();
				tab[wartosc]++;
				if (i == wartosc)
					return false; //graf nie jest prosty
			}
			for (j = 0; j < tab.length; j++) {
				if (tab[j] > 1)
					return false; //graf nie jest prosty
			}
		}
		return true; //graf prosty
	}
	
	@SuppressWarnings("unchecked")
	public static LinkedList<Integer>[] stworzSzkieletGrafu (LinkedList<Integer> listaSasiedztwa[])
	{
		LinkedList<Integer> szkieletListy[];
		szkieletListy = new LinkedList[listaSasiedztwa.length];
		for (int i = 0; i < szkieletListy.length; i++)
			szkieletListy[i] = new LinkedList<Integer>();
		
		for (int j = 0; j < listaSasiedztwa.length; j++) {
			Iterator<Integer> iterator = listaSasiedztwa[j].listIterator();
			while (iterator.hasNext()) // Przegl¹damy listê s¹siadów
			{
				int wartosc = iterator.next();
				szkieletListy[wartosc].addFirst(j);
				szkieletListy[j].addFirst(wartosc);
			}
		}
		return szkieletListy;
	}
	

	@SuppressWarnings("unchecked")
	public static LinkedList<Integer>[] kopiujListeSasiedztwa(LinkedList<Integer> listaSasiedztwa[]) {
		int i, j;
		int liczbaWierzcholkow = listaSasiedztwa.length;
		LinkedList<Integer> listaSasiedztwaKopia[];
		listaSasiedztwaKopia = new LinkedList[liczbaWierzcholkow];
		for (i = 0; i < liczbaWierzcholkow; i++)
			listaSasiedztwaKopia[i] = new LinkedList<Integer>();

		for (j = 0; j < liczbaWierzcholkow; j++) {
			Iterator<Integer> iterator = listaSasiedztwa[j].listIterator();
			while (iterator.hasNext()) // Przegl¹damy listê s¹siadów
			{
				int wartosc = iterator.next();
				listaSasiedztwaKopia[j].addLast(wartosc);
			}
		}
		return listaSasiedztwaKopia;
	}
	
	public static int[] odwrocTabliceInt(int[] kolejnosc) {
		int[] nowaTablica = new int[kolejnosc.length];
	    
		for (int i = 0; i < kolejnosc.length; i++)
	        nowaTablica[kolejnosc.length - i - 1] = kolejnosc[i];

	    return nowaTablica;
	}
}