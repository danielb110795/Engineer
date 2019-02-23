package plik;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JFileChooser;

import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseMultigraph;
import okno.PanelAlgorytmow;
import okno.PanelWprowadzaniaGrafu;
import zmienne.Globalne;

public class Odczyt {

	public static String odczytajTekstZPliku() {
		String tekstZPliku = "";
		JFileChooser fc = new JFileChooser();
		if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File plik = fc.getSelectedFile();
				try {
					Scanner skaner = new Scanner(plik);
					while (skaner.hasNext())
						tekstZPliku += skaner.nextLine() + "\n";
					skaner.close();
				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
				}
		}
		return tekstZPliku;
	}

	public static boolean sprawdzCzySkierowany(String tekstZPliku) {
		int czySkierowany = tekstZPliku.indexOf("Graf skierowany: true"); // -1 nie znaleziono, inaczej skierowany==true
		if (czySkierowany == -1)
			return false;
		return true;
	}

	public static boolean sprawdzCzyNieskierowany(String tekstZPliku) {
		int czyNieskierowany = tekstZPliku.indexOf("Graf nieskierowany: true"); // -1 nie znaleziono, inaczej //
																				// skierowany==true
		if (czyNieskierowany == -1)
			return false;
		return true;
	}

	public static int sprawdzLiczbeWierzcholkow(String tekstZPliku) {
		String liczbaWierzcholkow = "";
		int liczbaWierzcholkowIndex = tekstZPliku.indexOf("Liczba wierzcho³ków: ");
		String skroconyTekstZPliku = tekstZPliku.substring(liczbaWierzcholkowIndex + "Liczba wierzcho³ków: ".length());
		char[] tablicaZnakow = skroconyTekstZPliku.toCharArray();

		for (int i = 0; i < tablicaZnakow.length; i++) {
			if (Character.isDigit(tablicaZnakow[i])) {
				liczbaWierzcholkow += tablicaZnakow[i];
			} else {
				break;
			}
		}
		return Integer.parseInt(liczbaWierzcholkow);
	}

	public static int sprawdzLiczbeKrawedzi(String tekstZPliku) {
		String liczbaKrawedzi = "";
		int liczbaKrawedziInt = tekstZPliku.indexOf("Liczba krawêdzi: ");
		String skroconyTekstZPliku = tekstZPliku.substring(liczbaKrawedziInt + "Liczba krawêdzi: ".length());
		char[] tablicaZnakow = skroconyTekstZPliku.toCharArray();

		for (int i = 0; i < tablicaZnakow.length; i++) {
			if (Character.isDigit(tablicaZnakow[i])) {
				liczbaKrawedzi += tablicaZnakow[i];
			} else {
				break;
			}
		}
		return Integer.parseInt(liczbaKrawedzi);
	}

	public static int[][] sprawdzMacierzSasiedztwa(String tekstZPliku, int liczbaWierzcholkow) {
		String komorkaMacierzy = "";
		int kolumny = 0;
		int wiersze = 0;
		int[][] macierzSasiedztwa = new int[liczbaWierzcholkow][liczbaWierzcholkow];
		int macierzSasiedztwaIndex = tekstZPliku.indexOf("Macierz s¹siedztwa: ");
		String skroconyTekstZPliku = tekstZPliku.substring(macierzSasiedztwaIndex + "Macierz s¹siedztwa: ".length());
		char[] tablicaZnakow = skroconyTekstZPliku.toCharArray();

		for (int i = 0; i < tablicaZnakow.length; i++) {
			if (Character.isDigit(tablicaZnakow[i])) {
				komorkaMacierzy += tablicaZnakow[i];
			} else if ((tablicaZnakow[i]) == ',' || (tablicaZnakow[i]) == ';') {
				macierzSasiedztwa[wiersze][kolumny] = Integer.parseInt(komorkaMacierzy);
				kolumny++;
				if (kolumny == liczbaWierzcholkow) {
					kolumny = 0;
					wiersze++;
				}
				komorkaMacierzy = "";
			}
			if (tablicaZnakow[i] == ';')
				break;
		}
		return macierzSasiedztwa;
	}

	public static int[][] sprawdzMacierzIncydencji(String tekstZPliku, int liczbaWierzcholkow, int liczbaKrawedzi) {
		String komorkaMacierzy = "";
		int kolumny = 0;
		int wiersze = 0;
		int[][] macierzIncydencji = new int[liczbaWierzcholkow][liczbaKrawedzi];
		int macierzIncydencjiIndex = tekstZPliku.indexOf("Macierz incydencji: ");
		String skroconyTekstZPliku = tekstZPliku.substring(macierzIncydencjiIndex + "Macierz incydencji: ".length());
		char[] tablicaZnakow = skroconyTekstZPliku.toCharArray();

		for (int i = 0; i < tablicaZnakow.length; i++) {
			
			if (Character.isDigit(tablicaZnakow[i]) || tablicaZnakow[i] == '-') {
				komorkaMacierzy += tablicaZnakow[i];
			} else if (tablicaZnakow[i] == ',' || tablicaZnakow[i] == ';') {
				macierzIncydencji[wiersze][kolumny] = Integer.parseInt(komorkaMacierzy);
				kolumny++;
				if (kolumny == liczbaKrawedzi) {
					kolumny = 0;
					wiersze++;
				}
				komorkaMacierzy = "";
			}
			if (tablicaZnakow[i] == ';')
				break;
		}
		return macierzIncydencji;
	}

	@SuppressWarnings("unchecked")
	public static LinkedList<Integer>[] sprawdzListeSasiedztwa(String tekstZPliku, int liczbaWierzcholkow) {
		String element = "";
		int j = 0;
		LinkedList<Integer> listaSasiedztwa[];
		listaSasiedztwa = new LinkedList[liczbaWierzcholkow];
		for (int i = 0; i < liczbaWierzcholkow; i++)
			listaSasiedztwa[i] = new LinkedList<Integer>();
		int listaSasiedztwaIndex = tekstZPliku.indexOf("Lista s¹siedztwa: ");
		String skroconyTekstZPliku = tekstZPliku.substring(listaSasiedztwaIndex + "Lista s¹siedztwa: ".length());
		char[] tablicaZnakow = skroconyTekstZPliku.toCharArray();

		for (int i = 0; i < tablicaZnakow.length; i++) {
			
			if (Character.isDigit(tablicaZnakow[i])) {
				element += tablicaZnakow[i];
			} else if (tablicaZnakow[i] == ',' && !element.equals("")) {
				listaSasiedztwa[j].addLast(Integer.parseInt(element));
				element = "";
			} else if (tablicaZnakow[i] == '.' && !element.equals("")) {
				listaSasiedztwa[j].addLast(Integer.parseInt(element));
				element = "";
				j++;		
			} else if (tablicaZnakow[i] == '.' && element.equals("")) {
					element = "";
					j++;
			} else if (tablicaZnakow[i] == ';' && !element.equals("")) {
				listaSasiedztwa[j].addLast(Integer.parseInt(element));
				element = "";
			}
			if (tablicaZnakow[i] == ';')
				break;
		}
		return listaSasiedztwa;
	}

	public static String[] sprawdzWartoscWierzcholkow(String tekstZPliku, int liczbaWierzcholkow) {
		String[] wartoscWierzcholka;
		int wartoscWierzcholkaIndex = tekstZPliku.indexOf("Wartoœci wierzcho³ków: \n");
		String skroconyTekstZPliku = tekstZPliku
				.substring(wartoscWierzcholkaIndex + "Wartoœci wierzcho³ków: \n".length());
		
		String sprawdzCzyNull = skroconyTekstZPliku.substring(0, 4);
		
		if (sprawdzCzyNull.equals("null")) {
			wartoscWierzcholka = null;
		} else {
			String element = "";
			wartoscWierzcholka = new String[liczbaWierzcholkow];
			int j = 0;
			char[] tablicaZnakow = skroconyTekstZPliku.toCharArray();
			for (int i = 0; i < tablicaZnakow.length; i++) {
				if (tablicaZnakow[i] != ',' && tablicaZnakow[i] != ';') {
					element += tablicaZnakow[i];
				} else if (tablicaZnakow[i] == ',' || tablicaZnakow[i] == ';') {
					wartoscWierzcholka[j] = element;
					element = "";
					j++;
				}
				if (tablicaZnakow[i] == ';')
					break;
			}
		}
		return wartoscWierzcholka;
	}

	public static int[] sprawdzKrawedzie(String tekstZPliku, int liczbaKrawedzi) {
		int[] krawedzie;
		int krawedzieIndex = tekstZPliku.indexOf("Krawêdzie: \n");
		String skroconyTekstZPliku = tekstZPliku
				.substring(krawedzieIndex + "Krawêdzie: \n".length());
		char[] tablicaZnakow = skroconyTekstZPliku.toCharArray();
		
		String sprawdzCzyNull = skroconyTekstZPliku.substring(0, 4);
		
		if (sprawdzCzyNull.equals("null")) {
			krawedzie = null;
		} else {
			String element = "";
			krawedzie = new int[liczbaKrawedzi*2];
			int j = 0;
			for (int i = 0; i < tablicaZnakow.length; i++) {
				if (Character.isDigit(tablicaZnakow[i])) {
					element += tablicaZnakow[i];
				} else if (tablicaZnakow[i] == ',' || tablicaZnakow[i] == ';') {
					krawedzie[j] = Integer.parseInt(element);
					element = "";
					j++;
				}
				if (tablicaZnakow[i] == ';')
					break;
			}
		}
		return krawedzie;
	}
	
	public static Graph<String, String> sprawdzJungGraph(boolean czySkierowany, 
			int liczbaWierzcholkow, int liczbaKrawedzi, String[] wartoscWierzcholka, int [] krawedzie) 
	{
		Graph<String, String> grafJung = null;
		int tmp = 0;
		if (czySkierowany == true)
			grafJung = new DirectedSparseMultigraph<String, String>();
		else
			grafJung = new UndirectedSparseMultigraph<String, String>();

		if (wartoscWierzcholka == null) {
			for (int j = 0; j < liczbaWierzcholkow; j++) {
				grafJung.addVertex(String.valueOf(j));
			}
		} else {
			for (int j = 0; j < liczbaWierzcholkow; j++) {
				grafJung.addVertex(wartoscWierzcholka[j]);
			}
		}
		
		for (int i = 0; i < liczbaKrawedzi; i++) {
			
			if (wartoscWierzcholka == null) {
				grafJung.addEdge(String.valueOf(i), String.valueOf(krawedzie[tmp]), String.valueOf(krawedzie[tmp+1]));
			}
			else {
				grafJung.addEdge(String.valueOf(i), wartoscWierzcholka[krawedzie[tmp]], wartoscWierzcholka[krawedzie[tmp+1]]);
			}
			tmp += 2;
		} 
		
		return grafJung;
		}
	
	public static void storzWczytanyGraf(String tekstZPliku) {

		boolean czyWykorzystywany = true;
		boolean czyIstnieje = true;
		boolean czySkierowany = sprawdzCzySkierowany(tekstZPliku);
		boolean czyNieskierowany = sprawdzCzyNieskierowany(tekstZPliku);
		int liczbaWierzcholkow = sprawdzLiczbeWierzcholkow(tekstZPliku);
		int liczbaKrawedzi = sprawdzLiczbeKrawedzi(tekstZPliku);
		int[][] macierzSasiedztwa = sprawdzMacierzSasiedztwa(tekstZPliku, liczbaWierzcholkow);
		int[][] macierzIncydencji = sprawdzMacierzIncydencji(tekstZPliku, liczbaWierzcholkow, liczbaKrawedzi);
		LinkedList<Integer> listaSasiedztwa[] = sprawdzListeSasiedztwa(tekstZPliku, liczbaWierzcholkow);
		String[] wartoscWierzcholka = sprawdzWartoscWierzcholkow(tekstZPliku, liczbaWierzcholkow);
		int[] krawedzie = sprawdzKrawedzie(tekstZPliku, liczbaKrawedzi);
		Graph<String, String> grafJung = sprawdzJungGraph(czySkierowany, liczbaWierzcholkow, liczbaKrawedzi, wartoscWierzcholka, krawedzie);
		
		Globalne.grafy[Globalne.aktywnyGraf].numerGrafu = Globalne.aktywnyGraf;
		Globalne.grafy[Globalne.aktywnyGraf].czyWykorzystywany = czyWykorzystywany;
		Globalne.grafy[Globalne.aktywnyGraf].czyIstnieje = czyIstnieje;
		Globalne.grafy[Globalne.aktywnyGraf].czySkierowany = czySkierowany;
		Globalne.grafy[Globalne.aktywnyGraf].czyNieskierowany = czyNieskierowany;
		Globalne.grafy[Globalne.aktywnyGraf].liczbaWierzcholkow = liczbaWierzcholkow;
		Globalne.grafy[Globalne.aktywnyGraf].liczbaKrawedzi = liczbaKrawedzi;
		Globalne.grafy[Globalne.aktywnyGraf].macierzSasiedztwa = macierzSasiedztwa;
		Globalne.grafy[Globalne.aktywnyGraf].macierzIncydencji = macierzIncydencji;
		Globalne.grafy[Globalne.aktywnyGraf].listaSasiedztwa = listaSasiedztwa;
		Globalne.grafy[Globalne.aktywnyGraf].wartoscWierzcholka = wartoscWierzcholka;
		Globalne.grafy[Globalne.aktywnyGraf].krawedzie = krawedzie;
		Globalne.grafy[Globalne.aktywnyGraf].grafJung = grafJung;
		
		Globalne.czyToNowyGraf = true;

		Globalne.informacjeOWykonanymAlgorytmie += "\n\nWczytano graf z pliku. Numer grafu " + Globalne.aktywnyGraf + ".";
		PanelAlgorytmow.zmienInformacjeOWykonanymAlgorytmie();
		PanelAlgorytmow.wlaczLubWylaczPrzyciskWykonaj();
		PanelWprowadzaniaGrafu.przyciskWartosciTak.setSelected(true);
		PanelAlgorytmow.aktualizujWyborGrafu();
		
	}
}
