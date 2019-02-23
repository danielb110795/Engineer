package okno;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import graf.Graf;
import plik.Odczyt;
import plik.Zapis;
import zmienne.Globalne;

public class GorneMenu implements ActionListener {

	private JMenuBar gorneMenu;
	private JMenu plikMenu, pomocMenu, wygladMenu;
	private JMenuItem nowy, wykonajAlgorytm, usun, usunWszystko, zamknij, informacjaOProgramie,
			informacjaOWprowadzaniuGrafu, metal, windows, nimbus, zapiszGraf, zapiszWyniki, wczytajGraf, analiza;
	private ImageIcon ikonaNowyGrafDuza, ikonaNowyGraf2020, ikonaUsunDuza, ikonaUsun2020, ikonaZnakZapytaniaDuza,
			ikonaZnakZapytania2020, ikonaZnakZapytania4040, ikonaWykonajAlgorytmDuza, ikonaWykonajAlgorytm2020,
			ikonaZapiszDuza, ikonaZapisz2020, ikonaWczytajDuza, ikonaWczytaj2020, ikonaZamknijDuza, ikonaZamknij2020;
	private Image img, newimg, img2, newimg2, img3, newimg3, newimg4, img5, newimg5, img6, newimg6, img7, newimg7, img8,
			newimg8;

	public void zaladujIkonyMenu() {
		ikonaNowyGrafDuza = new ImageIcon("resources/nowy_plik.png");
		img = ikonaNowyGrafDuza.getImage();
		newimg = img.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		ikonaNowyGraf2020 = new ImageIcon(newimg);
		ikonaUsunDuza = new ImageIcon("resources/usun.png");
		img2 = ikonaUsunDuza.getImage();
		newimg2 = img2.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		ikonaUsun2020 = new ImageIcon(newimg2);
		ikonaZnakZapytaniaDuza = new ImageIcon("resources/znak_zapytania.png");
		img3 = ikonaZnakZapytaniaDuza.getImage();
		newimg3 = img3.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
		newimg4 = img3.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		ikonaZnakZapytania4040 = new ImageIcon(newimg3);
		ikonaZnakZapytania2020 = new ImageIcon(newimg4);
		ikonaWykonajAlgorytmDuza = new ImageIcon("resources/wykonaj_algorytm.png");
		img5 = ikonaWykonajAlgorytmDuza.getImage();
		newimg5 = img5.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		ikonaWykonajAlgorytm2020 = new ImageIcon(newimg5);
		ikonaZapiszDuza = new ImageIcon("resources/zapisz.png");
		img6 = ikonaZapiszDuza.getImage();
		newimg6 = img6.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		ikonaZapisz2020 = new ImageIcon(newimg6);
		ikonaWczytajDuza = new ImageIcon("resources/wczytaj.png");
		img7 = ikonaWczytajDuza.getImage();
		newimg7 = img7.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		ikonaWczytaj2020 = new ImageIcon(newimg7);
		ikonaZamknijDuza = new ImageIcon("resources/wyjdz.png");
		img8 = ikonaZamknijDuza.getImage();
		newimg8 = img8.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		ikonaZamknij2020 = new ImageIcon(newimg8);
	}

	JMenuBar stworzGorneMenu() {

		gorneMenu = new JMenuBar();
		plikMenu = new JMenu("Plik");
		wygladMenu = new JMenu("Wyglad");
		pomocMenu = new JMenu("Pomoc");

		zaladujIkonyMenu();

		nowy = new JMenuItem("  Nowy graf", ikonaNowyGraf2020);
		nowy.addActionListener(this);
		nowy.setToolTipText("Tworzenie nowego grafu");
		wykonajAlgorytm = new JMenuItem("  Wykonaj algorytm", ikonaWykonajAlgorytm2020);
		wykonajAlgorytm.addActionListener(this);
		wykonajAlgorytm.setToolTipText("Przejœcie do wykonywania algorytmów");
		analiza = new JMenuItem("  Porównaj algorytmy", ikonaWykonajAlgorytm2020);
		analiza.addActionListener(this);
		analiza.setToolTipText("Przejœcie do analizy");
		usun = new JMenuItem("  Usuñ graf", ikonaUsun2020);
		usun.addActionListener(this);
		usun.setToolTipText("Usuwa wybrany graf");
		usunWszystko = new JMenuItem("  Usuñ grafy", ikonaUsun2020);
		usunWszystko.addActionListener(this);
		usunWszystko.setToolTipText("Usuwa wszystkie grafy");
		zamknij = new JMenuItem("  Zamknij", ikonaZamknij2020);
		zamknij.addActionListener(this);
		zamknij.setToolTipText("Zamknij aplikacje");
		informacjaOProgramie = new JMenuItem("   O programie", ikonaZnakZapytania2020);
		informacjaOProgramie.addActionListener(this);
		informacjaOProgramie.setToolTipText("Wyœwietlenie informacji o programie");
		informacjaOWprowadzaniuGrafu = new JMenuItem("   Jak wprowadzaæ graf", ikonaZnakZapytania2020);
		informacjaOWprowadzaniuGrafu.addActionListener(this);
		informacjaOWprowadzaniuGrafu.setToolTipText("Wyœwietlenie informacji o tym jak wprowadziæ graf do programu");
		metal = new JMenuItem("  Metal");
		metal.addActionListener(this);
		metal.setToolTipText("Wygl¹d metal");
		nimbus = new JMenuItem("  Nimbus");
		nimbus.addActionListener(this);
		nimbus.setToolTipText("Wygl¹d nimbus");
		windows = new JMenuItem("  Windows");
		windows.addActionListener(this);
		windows.setToolTipText("Wygl¹d windows");
		zapiszGraf = new JMenuItem("  Zapisz graf", ikonaZapisz2020);
		zapiszGraf.addActionListener(this);
		zapiszGraf.setToolTipText("Zapisanie grafu do pliku tekstowego");
		zapiszWyniki = new JMenuItem("  Zapisz wyniki", ikonaZapisz2020);
		zapiszWyniki.addActionListener(this);
		zapiszWyniki.setToolTipText("Zapisanie informacji o wykonanych algorytmach");
		wczytajGraf = new JMenuItem("  Wczytaj graf", ikonaWczytaj2020);
		wczytajGraf.addActionListener(this);
		wczytajGraf.setToolTipText("Wczytanie grafu z pliku");

		plikMenu.add(nowy);
		plikMenu.addSeparator();
		plikMenu.add(zapiszGraf);
		plikMenu.add(zapiszWyniki);
		plikMenu.addSeparator();
		plikMenu.add(wczytajGraf);
		plikMenu.addSeparator();
		plikMenu.add(wykonajAlgorytm);
		plikMenu.add(analiza);
		plikMenu.addSeparator();
		plikMenu.add(usun);
		plikMenu.add(usunWszystko);
		plikMenu.addSeparator();
		plikMenu.add(zamknij);

		wygladMenu.add(windows);
		wygladMenu.add(metal);
		wygladMenu.add(nimbus);

		pomocMenu.add(informacjaOProgramie);
		pomocMenu.add(informacjaOWprowadzaniuGrafu);

		gorneMenu.add(plikMenu);
		gorneMenu.add(wygladMenu);
		gorneMenu.add(pomocMenu);

		return gorneMenu;
	}

	public void actionPerformed(ActionEvent e) {
		Object obiektMenu = e.getSource();

		if (obiektMenu == zamknij) {
			System.exit(0);
		}

		else if (obiektMenu == nowy) {
			boolean czyJestMiejsce = Graf.sprawdzCzyJestMiejsceNaGraf();
			if (czyJestMiejsce == false)
				JOptionPane.showMessageDialog(null, "Brak miejsca, usuñ któryœ z grafów!");
			if (Globalne.panelWG1.isVisible() == true)
				JOptionPane.showMessageDialog(null, "Aktualnie wprowadzasz graf!");
			else {
				PanelWprowadzaniaGrafu.getTablicaTextFieldAndJLabelKrawedzie();
				PanelWprowadzaniaGrafu.getTablicaTextFieldAndJLabelWierzcholki();
				Globalne.cardlayout.show(Globalne.panele, "WG1");
			}
		}

		else if (obiektMenu == wykonajAlgorytm) {
			if (Globalne.panelWG1.isVisible() == true)
				JOptionPane.showMessageDialog(null, "Aktualnie wprowadzasz graf!  Dokoñcz operacje!");
			else {
				PanelAlgorytmow.wlaczLubWylaczPrzyciskWykonaj();
				Globalne.cardlayout.show(Globalne.panele, "A");
			}
		}

		else if (obiektMenu == usunWszystko) {
			if (Globalne.panelWG1.isVisible() == true)
				JOptionPane.showMessageDialog(null, "Aktualnie wprowadzasz graf! Dokoñcz operacje!");
			else {
				int ilosc = Graf.sprawdzIloscIstniejacychGrafow();
				if (ilosc == 0) {
					JOptionPane.showMessageDialog(null, "Nie wprowadziles ¿adnych grafów do programu!");
				} else {
					Object[] opcje = { "Tak", "Nie" };
					int potwierdzenie = JOptionPane.showOptionDialog(null,
							"Czy na pewno chcesz usun¹æ " + "wszystkie grafy?", "Info", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, ikonaZnakZapytania4040, opcje, opcje[0]);
					if (potwierdzenie == JOptionPane.YES_OPTION) {
						Graf.usunWszystkieGrafy();
						PanelAlgorytmow.wlaczLubWylaczPrzyciskWykonaj();
						PanelAlgorytmow.aktualizujWyborGrafu();
						PanelAlgorytmow.aktualizujWyborWierzcholka();
						PanelAlgorytmow.wyswietlInformacjeOGrafie();
						Globalne.informacjeOWykonanymAlgorytmie += "\n\nUsuniêto wszystkie grafy.";
						PanelAlgorytmow.zmienInformacjeOWykonanymAlgorytmie();
					}
				}
			}
		}

		else if (obiektMenu == usun) {
			if (Globalne.panelWG1.isVisible() == true)
				JOptionPane.showMessageDialog(null, "Aktualnie wprowadzasz graf! Dokoñcz operacje!");
			else {
				int ilosc = Graf.sprawdzIloscIstniejacychGrafow();
				if (ilosc == 0) {
					JOptionPane.showMessageDialog(null, "Nie wprowadziles ¿adnych grafów do programu!");
				} else {
					Object[] grafy = new Object[ilosc];
					int tmp = 0;
					for (int i = 0; i < Globalne.grafy.length; i++) {
						if (Globalne.grafy[i].czyIstnieje == true) {
							grafy[tmp] = String.valueOf(Globalne.grafy[i].numerGrafu);
							tmp++;
						}
					}

					int wybranyGraf = -1;
					String wyborUzytkownika = (String) JOptionPane.showInputDialog(null, "Który graf chcesz usun¹æ:",
							"Info", JOptionPane.PLAIN_MESSAGE, ikonaZnakZapytania4040, grafy, "Graf 1");
					if (wyborUzytkownika != null) {
						wybranyGraf = Integer.parseInt(wyborUzytkownika);
						Graf.usunWybranyGraf(wybranyGraf);
						PanelAlgorytmow.wlaczLubWylaczPrzyciskWykonaj();
						PanelAlgorytmow.aktualizujWyborGrafu();
						PanelAlgorytmow.aktualizujWyborWierzcholka();
						PanelAlgorytmow.wyswietlInformacjeOGrafie();
						Globalne.informacjeOWykonanymAlgorytmie += "\n\nUsuniêto graf o numerze " + wybranyGraf + ".";
						PanelAlgorytmow.zmienInformacjeOWykonanymAlgorytmie();
					}
				}
			}
		}

		else if (obiektMenu == metal) {
			try {
				UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			}
			SwingUtilities.updateComponentTreeUI(Globalne.okno);
		}

		else if (obiektMenu == nimbus) {
			try {
				UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			}
			SwingUtilities.updateComponentTreeUI(Globalne.okno);
		}

		else if (obiektMenu == windows) {
			try {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			}
			SwingUtilities.updateComponentTreeUI(Globalne.okno);
		}

		else if (obiektMenu == zapiszWyniki) {
			String tekst = Zapis.odczytajTekstZJTextArea(PanelAlgorytmow.tekstInformacjeOWykonanymAlgorytmie);
			Zapis.zapiszTekstDoPlikuTxt(tekst);
			Globalne.informacjeOWykonanymAlgorytmie += "\n\nZapisano informacje o wykonanych algorytmach.";
			PanelAlgorytmow.zmienInformacjeOWykonanymAlgorytmie();
		}

		else if (obiektMenu == zapiszGraf) {
			int ilosc = Graf.sprawdzIloscIstniejacychGrafow();
			if (ilosc == 0) {
				JOptionPane.showMessageDialog(null, "Nie wprowadziles ¿adnych grafów do programu!");
			} else {
				Object[] grafy = new Object[ilosc];
				int tmp = 0;
				for (int i = 0; i < Globalne.grafy.length; i++) {
					if (Globalne.grafy[i].czyIstnieje == true) {
						grafy[tmp] = String.valueOf(Globalne.grafy[i].numerGrafu);
						tmp++;
					}
				}

				int wybranyGraf = -1;
				String wyborUzytkownika = (String) JOptionPane.showInputDialog(null, "Który graf chcesz zapisaæ:",
						"Info", JOptionPane.PLAIN_MESSAGE, ikonaZnakZapytania4040, grafy, "Graf 1");

				if (wyborUzytkownika != null) {
					wybranyGraf = Integer.parseInt(wyborUzytkownika);
					String tekst = Zapis.stworzStringDoZapisuGrafu(wybranyGraf);
					boolean sprawdzCoWybrano = Zapis.zapiszTekstDoPlikuTxt(tekst);
					if (sprawdzCoWybrano == true) {
						Globalne.informacjeOWykonanymAlgorytmie += "\n\nZapisano graf o nr " + wybranyGraf + ".";
						PanelAlgorytmow.zmienInformacjeOWykonanymAlgorytmie();
					}
				}
			}
		}

		else if (obiektMenu == wczytajGraf) {
			boolean czyJestMiejsce = Graf.sprawdzCzyJestMiejsceNaGraf(); // to zmienia nam nr globalnego grafu
			if (czyJestMiejsce == false)
				JOptionPane.showMessageDialog(null, "Brak miejsca, usuñ któryœ z grafów!");
			if (Globalne.panelWG1.isVisible() == true)
				JOptionPane.showMessageDialog(null, "Aktualnie wprowadzasz graf!");
			else {
				String tekstZPliku = Odczyt.odczytajTekstZPliku();
				try {
					Odczyt.storzWczytanyGraf(tekstZPliku);
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "Coœ posz³o nie tak!");
					Graf.ustawAktywnyGrafPoPrzerwaniuLubUsunieciu();
				}
			}
		}

		else if (obiektMenu == informacjaOProgramie) {
			JOptionPane.showMessageDialog(null,
					"Program umo¿liwia wykonywanie okreœlonych algorytmów na grafach. \n"
							+ "Grafy mo¿na dodaæ \"rêcznie\" lub wczytaæ z pliku. \n"
							+ "Ka¿dy graf mo¿na przedstawiæ w formie graficznej. \n"
							+ "Na wybranych algorytmach mo¿na dokonaæ analizy czasowej po wprowadzeniu \n"
							+ "opowiedniej liczny grafów.");
		}

		else if (obiektMenu == informacjaOWprowadzaniuGrafu) {
			JOptionPane.showMessageDialog(null,
					"Instrukcja wprowadzania grafu: \n" + "- ponumeruj wierzcho³ki zaczynj¹c od 0,\n"
							+ "- ponumeruj krawêdzie zaczynaj¹c od 0,\n" + "- wybierz z menu górnego \"Nowy graf\",\n"
							+ "- wype³nij formularz i kliknij \"Zapisz\".\n\n"
							+ "W grafie skierowanym wa¿na jest kolejnoœæ wierzcho³ków przy podawaniu krawêdzi.\n"
							+ "Najpierw nale¿y podaæ wierzcho³ek, gdzie krawêdŸ siê zaczyna a nastêpnie, \n"
							+ "gdzie krawêdŸ siê koñczy.");
		} else if (obiektMenu == analiza) {
			int ilosc = Graf.sprawdzIloscIstniejacychGrafow();
			
			if (Globalne.panelWG1.isVisible() == true)
				JOptionPane.showMessageDialog(null, "Aktualnie wprowadzasz graf!  Dokoñcz operacje!");
			else if (ilosc < 2)
				JOptionPane.showMessageDialog(null, "Wprowadz minimum 2 grafy.");
			else {
				Globalne.cardlayout.show(Globalne.panele, "P");
			}
		}
	}
}
