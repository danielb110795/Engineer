package okno;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import cern.colt.Arrays;
import graf.BFS;
import graf.DFS;
import graf.Graf;
import graf.Hamilton;
import graf.IsEulerDirected;
import graf.IsEulerUndirected;
import graf.KolorowanieDokladne;
import graf.KolorowanieKrawedzi;
import graf.KwadratGrafu;
import graf.LF;
import graf.SL;
import graf.SLF;
import graf.Sequential;
import graf.Transpozycja;
import zmienne.Globalne;

public class PanelAlgorytmow {

	static WatekWykonaniaAlgorytmu watek;

	static JPanel prawyGlowny = null;
	static JPanel lewyGlowny = null;
	static JPanel lewy1 = null;
	static JPanel lewy2 = null;
	static JPanel lewy3 = null;
	static JPanel lewy4 = null;
	static JPanel panelPrzycisku = null;
	static JPanel panelPrzycisku2 = null;

	public static JComboBox<String> wyborGrafu = null;
	static JScrollPane jScrollInfoGraf = null;
	static JScrollPane jScrollInfoAlgorytmy = null;
	static JTextArea tekstInformacjeOWykonanymAlgorytmie = null;
	static JTextArea tekstInformacjeOGrafie = null;
	static JComboBox<String> wyborAlgorytmu = null;
	static JButton przyciskWykonaj = null;
	static JButton przyciskPrzerwijWykonywanie = null;
	static JButton przyciskWyczysc = null;
	static JButton przyciskNarysujGraf = null;
	static JComboBox<String> wyborWierzcholkaStartowego = null;

	public static String informacjeOGrafie = "Brak graf�w.";

	static JPanel getPanelAlgorytmow() {
		if (Globalne.panelA == null) {
			Globalne.panelA = new JPanel(new GridLayout(1, 2));
			Font font = new Font("Dialog", Font.BOLD, 18);
			Font font2 = new Font("Dialog", Font.BOLD, 14);
			prawyGlowny = new JPanel(new BorderLayout());
			prawyGlowny.setBorder(BorderFactory.createTitledBorder(null, "Informacje o algorytmach", TitledBorder.LEFT,
					TitledBorder.TOP, font, Color.RED));
			prawyGlowny.add(getJScrollInfoAlgorytmy());
			prawyGlowny.add(getPrzyciskWyczysc(), BorderLayout.SOUTH);

			lewy1 = new JPanel(new BorderLayout());
			lewy1.setBorder(BorderFactory.createTitledBorder(null, "Wybierz graf", TitledBorder.LEFT, TitledBorder.TOP,
					font2, Color.BLACK));
			lewy1.add(getWyborGrafu());
			lewy1.add(getPrzyciskNarysujGraf(), BorderLayout.SOUTH);

			lewy2 = new JPanel(new BorderLayout());
			lewy2.setBorder(BorderFactory.createTitledBorder(null, "Informacje o grafie", TitledBorder.LEFT,
					TitledBorder.TOP, font2, Color.BLACK));
			lewy2.add(getJScrollInfoGraf());

			lewy3 = new JPanel(new BorderLayout());
			lewy3.setBorder(BorderFactory.createTitledBorder(null, "Wybierz algorytm", TitledBorder.LEFT,
					TitledBorder.TOP, font2, Color.BLACK));
			lewy3.add(getWyborAlgorytmu());

			lewy4 = new JPanel(new BorderLayout());
			lewy4.setBorder(BorderFactory.createTitledBorder(null, "Wybierz wierzcho�ek startowy", TitledBorder.LEFT,
					TitledBorder.TOP, font2, Color.BLACK));
			lewy4.add(getWyborWierzcholkaStartowego(), null);

			if (panelPrzycisku == null)
				panelPrzycisku = new JPanel(new GridLayout(0, 1));
			panelPrzycisku.add(getPrzyciskWykonaj());
			panelPrzycisku.setVisible(true);

			if (panelPrzycisku2 == null)
				panelPrzycisku2 = new JPanel(new GridLayout(0, 1));
			panelPrzycisku2.add(getPrzyciskPrzerwijWykonywanie());
			panelPrzycisku2.setVisible(false);

			Box box = Box.createVerticalBox();
			box.add(lewy1);
			box.add(lewy2);
			box.add(lewy3);
			box.add(lewy4);
			box.add(panelPrzycisku);
			box.add(panelPrzycisku2);

			lewyGlowny = new JPanel(new BorderLayout());
			lewyGlowny.setBorder(BorderFactory.createTitledBorder(null, "Formularz", TitledBorder.LEFT,
					TitledBorder.TOP, font, Color.RED));
			lewyGlowny.add(box);

			Globalne.panelA.add(lewyGlowny);
			Globalne.panelA.add(prawyGlowny);
		}
		return Globalne.panelA;
	}

	static JComboBox<String> getWyborGrafu() {
		if (wyborGrafu == null) {
			String[] grafy = new String[] {};
			wyborGrafu = new JComboBox<String>(grafy);
			wyborGrafu.setEditable(false);
		}
		wyborGrafu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {

				if (Globalne.czyToNowyGraf == true) {
					String abc = Integer.toString(Globalne.aktywnyGraf);
					wyborGrafu.getModel().setSelectedItem(abc);
					Globalne.czyToNowyGraf = false;
				}

				String wybranygraf = (String) wyborGrafu.getSelectedItem(); // to ten, ktory mamy po kliknieciu

				if (wybranygraf != null && !wybranygraf.isEmpty()) {
					Globalne.grafy[Globalne.aktywnyGraf].czyWykorzystywany = false; // zmieniam pprzedni graf na
																					// niewykorzystywany
					Globalne.aktywnyGraf = Integer.parseInt(wybranygraf); // ustawiam nowy graf
					Globalne.grafy[Globalne.aktywnyGraf].czyWykorzystywany = true; // nowy na wykorzystywany
				}
				if (Globalne.aktywnyGraf != -1)
					wyswietlInformacjeOGrafie();
				PanelAlgorytmow.aktualizujWyborWierzcholka();
			}
		});

		return wyborGrafu;
	}

	static JComboBox<String> getWyborWierzcholkaStartowego() {
		if (wyborWierzcholkaStartowego == null) {
			String[] wierzcholki = new String[] {};
			wyborWierzcholkaStartowego = new JComboBox<String>(wierzcholki);
			wyborWierzcholkaStartowego.setEditable(false);
		}
		wyborWierzcholkaStartowego.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {

			}
		});
		return wyborWierzcholkaStartowego;
	}

	static JButton getPrzyciskPrzerwijWykonywanie() {
		if (przyciskPrzerwijWykonywanie == null) {
			przyciskPrzerwijWykonywanie = new JButton("Przerwij");
			przyciskPrzerwijWykonywanie.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(java.awt.event.ActionEvent e) {
					watek.stop();
					JOptionPane.showMessageDialog(null, "Przerwano wykonywane algorytmu!");
					panelPrzycisku.setVisible(true);
					panelPrzycisku2.setVisible(false);
				}
			});
		}
		return przyciskPrzerwijWykonywanie;
	}

	static JButton getPrzyciskWyczysc() {
		if (przyciskWyczysc == null) {
			przyciskWyczysc = new JButton("Wyczy��");
			przyciskWyczysc.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Globalne.informacjeOWykonanymAlgorytmie = "\n\nWyczyszczono";
					zmienInformacjeOWykonanymAlgorytmie();
				}
			});
		}
		return przyciskWyczysc;
	}

	static JButton getPrzyciskNarysujGraf() {
		if (przyciskNarysujGraf == null) {
			przyciskNarysujGraf = new JButton();
			przyciskNarysujGraf.setText("Narysuj graf");
			przyciskNarysujGraf.setEnabled(true);
			przyciskNarysujGraf.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (Globalne.aktywnyGraf != -1)
						new RysowanieGrafu(Globalne.grafy[Globalne.aktywnyGraf].grafJung);
					else
						JOptionPane.showMessageDialog(null, "B��d rysowania, prawdopodobnie nie ma dodanych graf�w");
				}
			});
		}
		return przyciskNarysujGraf;
	}

	static JComboBox<String> getWyborAlgorytmu() {
		if (wyborAlgorytmu == null) {
			String[] algorytmy = new String[] { "DFS - macierz s�siedztwa", "DFS - lista s�siedztwa",
					"BFS - macierz s�siedztwa", "BFS - lista s�siedztwa", "Sequential", "Kolorowanie dok�adne",
					"Kolorowanie LF", "Kolorowanie SL", "Kolorowanie SLF", "Kolorowanie kraw�dzi", "Transpozycja",
					"Kwadrat grafu", "Istnienie cyklu lub �cie�ki Eulera", "Cykl Hamiltona", "�cie�ka Hamiltona"};
			wyborAlgorytmu = new JComboBox<String>(algorytmy);
			wyborAlgorytmu.setEditable(false);
			wyborAlgorytmu.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String wybranyAlgorytm = (String) wyborAlgorytmu.getSelectedItem();
					if (wybranyAlgorytm.equals("DFS - macierz s�siedztwa")
							|| wybranyAlgorytm.equals("DFS - lista s�siedztwa")
							|| wybranyAlgorytm.equals("BFS - macierz s�siedztwa")
							|| wybranyAlgorytm.equals("BFS - lista s�siedztwa")) {
						lewy4.setVisible(true);
					} else {
						lewy4.setVisible(false);
					}
				}
			});
		}
		return wyborAlgorytmu;
	}

	static JButton getPrzyciskWykonaj() {
		if (przyciskWykonaj == null) {
			przyciskWykonaj = new JButton();
			przyciskWykonaj.setText("Wykonaj");
			przyciskWykonaj.setEnabled(false);
			przyciskWykonaj.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					watek = new WatekWykonaniaAlgorytmu();
					watek.start();

				}
			});
		}
		return przyciskWykonaj;
	}

	static JTextArea getInformacjeOWykonanymAlgorytmie() {
		if (tekstInformacjeOWykonanymAlgorytmie == null) {
			tekstInformacjeOWykonanymAlgorytmie = new JTextArea();
			tekstInformacjeOWykonanymAlgorytmie.setEnabled(true);
			tekstInformacjeOWykonanymAlgorytmie.setEditable(false);
			tekstInformacjeOWykonanymAlgorytmie.setLineWrap(true);
			tekstInformacjeOWykonanymAlgorytmie.setWrapStyleWord(true);
			tekstInformacjeOWykonanymAlgorytmie.setFont(new Font("Dialog", Font.BOLD, 12));
			tekstInformacjeOWykonanymAlgorytmie.append(Globalne.informacjeOWykonanymAlgorytmie);

		}
		return tekstInformacjeOWykonanymAlgorytmie;
	}

	static JTextArea getInformacjeOGrafie() {
		if (tekstInformacjeOGrafie == null) {
			tekstInformacjeOGrafie = new JTextArea();
			tekstInformacjeOGrafie.setEnabled(true);
			tekstInformacjeOGrafie.setEditable(false);
			tekstInformacjeOGrafie.setLineWrap(true);
			tekstInformacjeOGrafie.setWrapStyleWord(true);
			tekstInformacjeOGrafie.setFont(new Font("Dialog", Font.BOLD, 12));
			tekstInformacjeOGrafie.append(informacjeOGrafie);

		}
		return tekstInformacjeOGrafie;
	}

	static JScrollPane getJScrollInfoGraf() {
		if (jScrollInfoGraf == null) {
			jScrollInfoGraf = new JScrollPane();
			jScrollInfoGraf.setEnabled(true);
			jScrollInfoGraf.setViewportView(getInformacjeOGrafie());
			jScrollInfoGraf.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		}
		return jScrollInfoGraf;
	}

	static JScrollPane getJScrollInfoAlgorytmy() {
		if (jScrollInfoAlgorytmy == null) {
			jScrollInfoAlgorytmy = new JScrollPane();
			jScrollInfoAlgorytmy.setEnabled(true);
			jScrollInfoAlgorytmy.setViewportView(getInformacjeOWykonanymAlgorytmie());
			jScrollInfoAlgorytmy.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		}
		return jScrollInfoAlgorytmy;
	}
	////////////////////////////////////////////////////////////////

	static void wyswietlInformacjeOGrafie() {
		int ilosc = Graf.sprawdzIloscIstniejacychGrafow();
		String informacje = "";

		if (ilosc > 0) {
			String czySkierowany = "";
			String wartosciWierzcholkow = "";

			if (Globalne.grafy[Globalne.aktywnyGraf].czySkierowany)
				czySkierowany = "skierowany";
			else
				czySkierowany = "nieskierowany";

			if (Globalne.grafy[Globalne.aktywnyGraf].wartoscWierzcholka == null) {
				wartosciWierzcholkow = "Graf nie ma warto�ci powi�zanych z wierzcho�kami";
			} else {
				wartosciWierzcholkow = "Z wierzcho�kami grafu powi�zane s� warto�ci.\nKolejne warto�ci wierzcho�k�w:\n";
				for (int i = 0; i < Globalne.grafy[Globalne.aktywnyGraf].liczbaWierzcholkow; i++) {
					wartosciWierzcholkow += "Wierzcho�ek nr " + i + ": "
							+ Globalne.grafy[Globalne.aktywnyGraf].wartoscWierzcholka[i] + "\n";
				}
			}

			String listaSasiedztwa = Graf.wypiszListeSasiedztwa(Globalne.grafy[Globalne.aktywnyGraf].listaSasiedztwa);
			String macierzSasiedztwa = Graf.wypiszMacierz(Globalne.grafy[Globalne.aktywnyGraf].macierzSasiedztwa);
			String macierzIncydencji = Graf.wypiszMacierz(Globalne.grafy[Globalne.aktywnyGraf].macierzIncydencji);
			informacje = "Jest to graf " + czySkierowany + ".\n" + "\n" + wartosciWierzcholkow
					+ "\nLiczba wierzcholk�w: " + Globalne.grafy[Globalne.aktywnyGraf].liczbaWierzcholkow
					+ "\nLiczba kraw�dzi: " + Globalne.grafy[Globalne.aktywnyGraf].liczbaKrawedzi
					+ ".\n\nZawarto�� listy s�siedztwa: \n" + listaSasiedztwa + "\nZawarto�� macierzy s�siedztwa: \n"
					+ macierzSasiedztwa + "\nZawarto�� macierzy incydencji: \n" + macierzIncydencji;
		} else {
			informacje = "Brak graf�w.";
		}

		tekstInformacjeOGrafie.setText("");
		tekstInformacjeOGrafie.append(informacje);
	}

	public static void zmienInformacjeOWykonanymAlgorytmie() {
		tekstInformacjeOWykonanymAlgorytmie.setText("");
		tekstInformacjeOWykonanymAlgorytmie.append(Globalne.informacjeOWykonanymAlgorytmie);
	}

	public static void wlaczLubWylaczPrzyciskWykonaj() {
		int ilosc = Graf.sprawdzIloscIstniejacychGrafow();
		if (ilosc > 0) {
			przyciskWykonaj.setEnabled(true);
		} else {
			przyciskWykonaj.setEnabled(false);
		}
	}

	public static void aktualizujWyborGrafu() {
		int i = 0;
		wyborGrafu.removeAllItems();
		for (i = 0; i < Globalne.grafy.length; i++) {
			if (Globalne.grafy[i].czyIstnieje == true)
				wyborGrafu.addItem(String.valueOf(Globalne.grafy[i].numerGrafu));
		}
	}

	public static void aktualizujWyborWierzcholka() {
		int i = 0;
		wyborWierzcholkaStartowego.removeAllItems();
		if (Globalne.aktywnyGraf > -1 && Globalne.aktywnyGraf < Globalne.MAX_LICZBA_GRAFOW)
			for (i = 0; i < Globalne.grafy[Globalne.aktywnyGraf].liczbaWierzcholkow; i++) {
				wyborWierzcholkaStartowego.addItem(String.valueOf(i));
			}
	}

	public static void wykonajAlgorytm() {
		String wybranyAlgorytm = (String) wyborAlgorytmu.getSelectedItem();
		if (wybranyAlgorytm.equals("DFS - macierz s�siedztwa")) {
			DFS dfs = new DFS();
			boolean[] odwiedzone = new boolean[Globalne.grafy[Globalne.aktywnyGraf].liczbaWierzcholkow];
			int wybranyInt = Integer.parseInt((String) wyborWierzcholkaStartowego.getSelectedItem());
			dfs.DFSMacierzSasiedztwa(wybranyInt, Globalne.grafy[Globalne.aktywnyGraf].macierzSasiedztwa, odwiedzone);

			Globalne.informacjeOWykonanymAlgorytmie += "\n\nWykonano algorytm DFS oparty o macierz s�siedztwa. "
					+ "Algorytm przechodzi� kolejno przez wierzcho�ki: " + dfs.getZwracanyWynik();
			zmienInformacjeOWykonanymAlgorytmie();

		} else if (wybranyAlgorytm.equals("DFS - lista s�siedztwa")) {
			DFS dfs = new DFS();
			boolean[] odwiedzone = new boolean[Globalne.grafy[Globalne.aktywnyGraf].liczbaWierzcholkow];
			int wybranyInt = Integer.parseInt((String) wyborWierzcholkaStartowego.getSelectedItem());
			dfs.DFSListaSasiedztwa(wybranyInt, Globalne.grafy[Globalne.aktywnyGraf].listaSasiedztwa, odwiedzone);

			Globalne.informacjeOWykonanymAlgorytmie += "\n\nWykonano algorytm DFS oparty o liste s�siedztwa. "
					+ "Algorytm przechodzi� kolejno przez wierzcho�ki: " + dfs.getZwracanyWynik();
			zmienInformacjeOWykonanymAlgorytmie();

		} else if (wybranyAlgorytm.equals("BFS - lista s�siedztwa")) {
			BFS bfs = new BFS();
			int wybranyInt = Integer.parseInt((String) wyborWierzcholkaStartowego.getSelectedItem());
			bfs.BFSListaSasiedztwa(wybranyInt, Globalne.grafy[Globalne.aktywnyGraf].listaSasiedztwa);

			Globalne.informacjeOWykonanymAlgorytmie += "\n\nWykonano algorytm BFS oparty o liste s�siedztwa. "
					+ "Algorytm przechodzi� kolejno przez wierzcho�ki: " + bfs.getZwracanyWynik();
			zmienInformacjeOWykonanymAlgorytmie();

		} else if (wybranyAlgorytm.equals("BFS - macierz s�siedztwa")) {
			BFS bfs = new BFS();
			int wybranyInt = Integer.parseInt((String) wyborWierzcholkaStartowego.getSelectedItem());
			bfs.BFSMacierzSasiedztwa(wybranyInt, Globalne.grafy[Globalne.aktywnyGraf].macierzSasiedztwa);
			Globalne.informacjeOWykonanymAlgorytmie += "\n\nWykonano algorytm BFS oparty o liste s�siedztwa. "
					+ "Algorytm przechodzi� kolejno przez wierzcho�ki: " + bfs.getZwracanyWynik();
			zmienInformacjeOWykonanymAlgorytmie();

		} else if (wybranyAlgorytm.equals("Sequential")) {
			if (Graf.sprawdzCzyGrafMaPetle(Globalne.grafy[Globalne.aktywnyGraf])) {
				Sequential sequential = new Sequential();
				sequential.kolorujS(Globalne.grafy[Globalne.aktywnyGraf].listaSasiedztwa,
						Globalne.grafy[Globalne.aktywnyGraf].czySkierowany);
				Globalne.informacjeOWykonanymAlgorytmie += "\n\nWykonano kolorowanie zach�anne. "
						+ "Algorytm pokolorowa� kolejne wierzcho�ki nast�puj�co: "
						+ Arrays.toString(sequential.getTabKolorow());
				zmienInformacjeOWykonanymAlgorytmie();
			} else
				JOptionPane.showMessageDialog(null, "Graf z p�tlami nie mo�e by� kolorowany.");

		} else if (wybranyAlgorytm.equals("Kolorowanie dok�adne")) {
			if (Graf.sprawdzCzyGrafMaPetle(Globalne.grafy[Globalne.aktywnyGraf])) {
				KolorowanieDokladne kolorowanieDokladne = new KolorowanieDokladne();
				kolorowanieDokladne.kolorujDokladnie(Globalne.grafy[Globalne.aktywnyGraf].listaSasiedztwa,
						Globalne.grafy[Globalne.aktywnyGraf].liczbaKrawedzi);
				Globalne.informacjeOWykonanymAlgorytmie += "\n\nWykonano kolorowanie dok�adne. "
						+ "Algorytm pokolorowa� kolejne wierzcho�ki nast�puj�co: "
						+ Arrays.toString(kolorowanieDokladne.getTabKolorow());
				zmienInformacjeOWykonanymAlgorytmie();
			} else
				JOptionPane.showMessageDialog(null, "Graf z p�tlami nie mo�e by� kolorowany.");

		} else if (wybranyAlgorytm.equals("Kolorowanie SL")) {
			if (Graf.sprawdzCzyGrafMaPetle(Globalne.grafy[Globalne.aktywnyGraf])) {
				SL sl = new SL();
				sl.kolorujSL(Globalne.grafy[Globalne.aktywnyGraf].listaSasiedztwa,
						Globalne.grafy[Globalne.aktywnyGraf].czySkierowany);
				Globalne.informacjeOWykonanymAlgorytmie += "\n\nWykonano kolorowanie SL. "
						+ "Algorytm pokolorowa� kolejne wierzcho�ki nast�puj�co: "
						+ Arrays.toString(sl.getTabKolorowWierzcholkow());
				zmienInformacjeOWykonanymAlgorytmie();
			} else
				JOptionPane.showMessageDialog(null, "Graf z p�tlami nie mo�e by� kolorowany.");

		} else if (wybranyAlgorytm.equals("Kolorowanie LF")) {
			if (Graf.sprawdzCzyGrafMaPetle(Globalne.grafy[Globalne.aktywnyGraf])) {
				LF lf = new LF();
				lf.koloruj(Globalne.grafy[Globalne.aktywnyGraf].listaSasiedztwa,
						Globalne.grafy[Globalne.aktywnyGraf].czySkierowany);
				Globalne.informacjeOWykonanymAlgorytmie += "\n\nWykonano kolorowanie LF. "
						+ "Algorytm pokolorowa� kolejne wierzcho�ki nast�puj�co: "
						+ Arrays.toString(lf.getTabKolorowWierzcholkow());
				zmienInformacjeOWykonanymAlgorytmie();
			} else
				JOptionPane.showMessageDialog(null, "Graf z p�tlami nie mo�e by� kolorowany.");

		} else if (wybranyAlgorytm.equals("Kolorowanie SLF")) {
			if (Graf.sprawdzCzyGrafMaPetle(Globalne.grafy[Globalne.aktywnyGraf])) {
				SLF slf = new SLF();
				slf.kolorujSLF(Graf.kopiujMacierzSasiedztwa(Globalne.grafy[Globalne.aktywnyGraf].macierzSasiedztwa));

				Globalne.informacjeOWykonanymAlgorytmie += "\n\nWykonano kolorowanie SLF grafu "
						+ Globalne.grafy[Globalne.aktywnyGraf].numerGrafu + ". Wierzcho�ki kolorowano nast�puj�co: "
						+ Arrays.toString(slf.getKolorowanie());

				zmienInformacjeOWykonanymAlgorytmie();
				wyswietlInformacjeOGrafie();
			} else
				JOptionPane.showMessageDialog(null, "Graf z p�tlami nie mo�e by� kolorowany.");

		} else if (wybranyAlgorytm.equals("Transpozycja")) {
			if (Globalne.grafy[Globalne.aktywnyGraf].czySkierowany == true) {
				Transpozycja transpozycja = new Transpozycja();
				transpozycja.transponujGraf(Globalne.grafy[Globalne.aktywnyGraf]);
				Globalne.informacjeOWykonanymAlgorytmie += "\n\nWykonano transpozycje grafu "
						+ Globalne.grafy[Globalne.aktywnyGraf].numerGrafu + ".";
				zmienInformacjeOWykonanymAlgorytmie();
				wyswietlInformacjeOGrafie();
			} else
				JOptionPane.showMessageDialog(null, "Transpozycj� mo�na wykona� tylko na grafie skierowanym");

		} else if (wybranyAlgorytm.equals("Kwadrat grafu")) {
			if (Graf.sprawdzCzyToGrafProsty(Globalne.grafy[Globalne.aktywnyGraf])
					&& Globalne.grafy[Globalne.aktywnyGraf].czySkierowany) {
				KwadratGrafu kwadratGrafu = new KwadratGrafu();
				kwadratGrafu.wyznaczKwadratGrafuMacierz(Globalne.grafy[Globalne.aktywnyGraf].macierzSasiedztwa);
				String macierz = Graf.wypiszMacierz(kwadratGrafu.getKwadratGrafu());
				Globalne.informacjeOWykonanymAlgorytmie += "\n\nWykonano kwadrat grafu "
						+ Globalne.grafy[Globalne.aktywnyGraf].numerGrafu + ". Macierz s�siedztwa kwadratu grafu:\n"
						+ macierz;
				zmienInformacjeOWykonanymAlgorytmie();
				wyswietlInformacjeOGrafie();
			} else
				JOptionPane.showMessageDialog(null, "Graf musi by� prosty oraz skierowany.");

		} else if (wybranyAlgorytm.equals("Kolorowanie kraw�dzi")) {
			if (Graf.sprawdzCzyToGrafProsty(Globalne.grafy[Globalne.aktywnyGraf])) {
				KolorowanieKrawedzi kk = new KolorowanieKrawedzi();
				kk.kolorujKrawedzie(
						Graf.kopiujMacierzSasiedztwa(Globalne.grafy[Globalne.aktywnyGraf].macierzSasiedztwa),
						Globalne.grafy[Globalne.aktywnyGraf].czySkierowany);
				Globalne.informacjeOWykonanymAlgorytmie += "\n\nWykonano kolorowanie kraw�dzi grafu "
						+ Globalne.grafy[Globalne.aktywnyGraf].numerGrafu + ". Kraw�dzie kolorowano nast�puj�co:\n";

				for (int i = 0; i < kk.getKolory().length; i++) {
					Globalne.informacjeOWykonanymAlgorytmie += Arrays.toString(kk.getKrawedzie().get(i)) + ": ";
					Globalne.informacjeOWykonanymAlgorytmie += String.valueOf(kk.getKolory()[i]) + "\n";
				}

				zmienInformacjeOWykonanymAlgorytmie();
				wyswietlInformacjeOGrafie();
			} else
				JOptionPane.showMessageDialog(null, "Graf musi by� prosty.");

		} else if (wybranyAlgorytm.equals("Istnienie cyklu lub �cie�ki Eulera")) {
			if (Globalne.grafy[Globalne.aktywnyGraf].czyNieskierowany == true) {
				IsEulerUndirected euler = new IsEulerUndirected();
				int res = euler.sprawdzCzyJestCykl(Globalne.grafy[Globalne.aktywnyGraf]);
				if (res == 0)
					Globalne.informacjeOWykonanymAlgorytmie += "\n\nW tym grafie nie ma cyklu ani �cie�ki Eulera.";
				else if (res == 1)
					Globalne.informacjeOWykonanymAlgorytmie += "\n\nW tym grafie jest �cie�ka Eulera.";
				else if (res == 2)
					Globalne.informacjeOWykonanymAlgorytmie += "\n\nW tym grafie jest cykl Eulera.";
				zmienInformacjeOWykonanymAlgorytmie();
				wyswietlInformacjeOGrafie();
			} else {
				IsEulerDirected euler = new IsEulerDirected();
				int res = euler.isEulerian(Globalne.grafy[Globalne.aktywnyGraf]);
				if (res == 0)
					Globalne.informacjeOWykonanymAlgorytmie += "\n\nW tym grafie nie ma cyklu ani �cie�ki Eulera.";
				else if (res == 1)
					Globalne.informacjeOWykonanymAlgorytmie += "\n\nW tym grafie jest �cie�ka Eulera.";
				else if (res == 2)
					Globalne.informacjeOWykonanymAlgorytmie += "\n\nW tym grafie jest cykl Eulera.";
				zmienInformacjeOWykonanymAlgorytmie();
				wyswietlInformacjeOGrafie();
			}

		} else if (wybranyAlgorytm.equals("Cykl Hamiltona")) {
			Hamilton hamilton = new Hamilton();
			hamilton.znajdzCyklHamiltona(Graf.kopiujMacierzSasiedztwa(Globalne.grafy[Globalne.aktywnyGraf].macierzSasiedztwa));
			if (hamilton.znalezionoCyklHamiltona)
				Globalne.informacjeOWykonanymAlgorytmie += "\n\nZnaleziono cykl Hamiltona: " + Arrays.toString(hamilton.sciezka);
	        else
	        	Globalne.informacjeOWykonanymAlgorytmie += "\n\nNie znaleziono cyklu Hamiltona.";
			zmienInformacjeOWykonanymAlgorytmie();
			wyswietlInformacjeOGrafie();
		} else if (wybranyAlgorytm.equals("�cie�ka Hamiltona")) {
			Hamilton hamilton = new Hamilton();
			hamilton.znajdzSciezkeHamiltona(Graf.kopiujMacierzSasiedztwa(Globalne.grafy[Globalne.aktywnyGraf].macierzSasiedztwa));
	        if (hamilton.znalezionoSciezkeHamiltona)
	        	Globalne.informacjeOWykonanymAlgorytmie += "\n\nZnaleziono �cie�k� Hamiltona: " + Arrays.toString(hamilton.sciezka);
	        else
	        	Globalne.informacjeOWykonanymAlgorytmie += "\n\nNie znaleziono �cie�ki Hamiltona.";
	        zmienInformacjeOWykonanymAlgorytmie();
			wyswietlInformacjeOGrafie();
		}
	}
}
