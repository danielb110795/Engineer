package okno;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import graf.Graf;
import zmienne.Globalne;

public class PanelWprowadzaniaGrafu {

	static JPanel panelDolny = null;
	static JPanel panelGorny = null;
	static JPanel panelLewy1 = null;
	static JPanel panelLewy2 = null;
	static JPanel panelLewy3 = null;
	static JPanel panelLewy4 = null;
	static JPanel panelSrodkowy = null;
	static JPanel panelPrawy = null;
	
	static JScrollPane scrollPanelWprowadzaniaKrawedzi = null;
	static JPanel panelDoWprowadzaniaKrawedzi = null;
	static JScrollPane scrollPanelWartosciWierzcholkow = null;
	static JPanel panelDoWprowadzaniaWartosciWierzcholkow = null;

	static JButton przyciskZapisz = null;
	static JButton przyciskPrzerwij = null;
	static JRadioButton przyciskSkierowany = null;
	static JRadioButton przyciskNieskierowany = null;
	public static JRadioButton przyciskWartosciTak = null;
	static JRadioButton przyciskWartosciNie = null;
	static JComboBox<Integer> wyborIlosciWierzcholkow = null;
	static JComboBox<Integer> wyborIlosciKrawedzi = null;
	static JTextField[] tablicaTextFieldKrawedzie = null;
	static JLabel[] tablicaJLabelKrawedzie = null;
	static JTextField[] tablicaTextFieldWierzcholki = null;
	static JLabel[] tablicaJLabelWierzcholki = null;



	static JPanel getPanelWprowadzaniaGrafu1() {
		if (Globalne.panelWG1 == null) {
			Globalne.panelWG1 = new JPanel(new BorderLayout());
			
			Font font = new Font("Dialog", Font.BOLD, 18);
			Font font2 = new Font("Dialog", Font.BOLD, 14);
			
			panelDolny = new JPanel(new GridLayout(2,1));
			panelDolny.setBorder(BorderFactory.createTitledBorder(""));
			panelDolny.add(getPrzyciskZapisz());
			panelDolny.add(getPrzyciskPrzerwij());

			panelLewy1 = new JPanel(new GridLayout(2,1));
			panelLewy1.setBorder(BorderFactory.createTitledBorder(null, "Rodzaj grafu", 
					TitledBorder.LEFT, TitledBorder.TOP, font2, Color.BLACK));
			panelLewy1.add(getPrzyciskSkierowany());
			panelLewy1.add(getPrzyciskNieskierowany());
			ButtonGroup grupa = new ButtonGroup();
			grupa.add(przyciskSkierowany);
			grupa.add(przyciskNieskierowany);
			
			panelLewy2 = new JPanel(new GridLayout(2,1));
			panelLewy2.setBorder(BorderFactory.createTitledBorder(null, "Nazwy dla wierzcho³ków", 
					TitledBorder.LEFT, TitledBorder.TOP, font2, Color.BLACK));
			panelLewy2.add(getPrzyciskWartosciTak());
			panelLewy2.add(getPrzyciskWartosciNie());
			ButtonGroup grupa2 = new ButtonGroup();
			grupa2.add(przyciskWartosciTak);
			grupa2.add(przyciskWartosciNie);
			
			panelLewy3 = new JPanel(new BorderLayout());
			panelLewy3.setBorder(BorderFactory.createTitledBorder(null, "Iloœæ wierzcho³ków", 
					TitledBorder.LEFT, TitledBorder.TOP, font2, Color.BLACK));
			panelLewy3.add(getWyborIlosciWierzcholkow(), null);
			
			panelLewy4 = new JPanel(new BorderLayout());
			panelLewy4.setBorder(BorderFactory.createTitledBorder(null, "Iloœæ krawêdzi", 
					TitledBorder.LEFT, TitledBorder.TOP, font2, Color.BLACK));
			panelLewy4.add(getWyborIlosciKrawedzi(), null);

			Box box = Box.createVerticalBox();
			box.setBorder(BorderFactory.createTitledBorder(null, "Informacje", 
					TitledBorder.LEFT, TitledBorder.TOP, font, Color.RED));
			box.add(panelLewy1);
			box.add(panelLewy2);
			box.add(panelLewy3);
			box.add(panelLewy4);
			
			panelGorny = new JPanel(new GridLayout(1,3));
			panelGorny.setBorder(BorderFactory.createTitledBorder(""));
			panelGorny.add(box);
			panelGorny.add(getScrollPanelWprowadzaniaKrawedzi());
			panelGorny.add(getScrollPanelWartosciWierzcholkow());
			
			Globalne.panelWG1.add(panelGorny);
			Globalne.panelWG1.add(panelDolny, BorderLayout.SOUTH);

		}

		return Globalne.panelWG1;
	}

	static JPanel getPanel() {
		if (panelDoWprowadzaniaKrawedzi == null) {
			panelDoWprowadzaniaKrawedzi = new JPanel();
			panelDoWprowadzaniaKrawedzi.setLayout(new GridLayout(0, 3, 2, 5));
		}
		return panelDoWprowadzaniaKrawedzi;
	}

	static JScrollPane getScrollPanelWprowadzaniaKrawedzi() {
		if (scrollPanelWprowadzaniaKrawedzi == null) {
			scrollPanelWprowadzaniaKrawedzi = new JScrollPane();
			Font font = new Font("Dialog", Font.BOLD, 18);
			scrollPanelWprowadzaniaKrawedzi.setEnabled(true);
			scrollPanelWprowadzaniaKrawedzi.setViewportView(getPanel());
			scrollPanelWprowadzaniaKrawedzi.setBorder(BorderFactory.createTitledBorder(null, "Krawedzie", 
					TitledBorder.LEFT, TitledBorder.TOP, font, Color.RED));
		}
		return scrollPanelWprowadzaniaKrawedzi;
	}

	static JPanel getPanel2() {
		if (panelDoWprowadzaniaWartosciWierzcholkow == null) {
			panelDoWprowadzaniaWartosciWierzcholkow = new JPanel();
			panelDoWprowadzaniaWartosciWierzcholkow.setLayout(new GridLayout(0, 2, 2, 5));
		}
		return panelDoWprowadzaniaWartosciWierzcholkow;
	}

	static JScrollPane getScrollPanelWartosciWierzcholkow() {
		if (scrollPanelWartosciWierzcholkow == null) {
			scrollPanelWartosciWierzcholkow = new JScrollPane();
			Font font = new Font("Dialog", Font.BOLD, 18);
			scrollPanelWartosciWierzcholkow.setEnabled(true);
			scrollPanelWartosciWierzcholkow.setViewportView(getPanel2());
			scrollPanelWartosciWierzcholkow.setBorder(BorderFactory.createTitledBorder(null, "Wierzcho³ki", 
					TitledBorder.LEFT, TitledBorder.TOP, font, Color.RED));
		}
		return scrollPanelWartosciWierzcholkow;
	}

	static JRadioButton getPrzyciskSkierowany() {
		if (przyciskSkierowany == null) {
			przyciskSkierowany = new JRadioButton();
			przyciskSkierowany.setSelected(true);
			przyciskSkierowany.setText("skierowany");
		}
		return przyciskSkierowany;
	}

	static JRadioButton getPrzyciskNieskierowany() {
		if (przyciskNieskierowany == null) {
			przyciskNieskierowany = new JRadioButton();
			przyciskNieskierowany.setText("nieskierowany");
		}
		return przyciskNieskierowany;
	}

	static JRadioButton getPrzyciskWartosciNie() {
		if (przyciskWartosciNie == null) {
			przyciskWartosciNie = new JRadioButton();
			przyciskWartosciNie.setText("nie");
			przyciskWartosciNie.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					tablicaTextFieldWierzcholki = usunTabliceJTextField(tablicaTextFieldWierzcholki,
							panelDoWprowadzaniaWartosciWierzcholkow);
					tablicaJLabelWierzcholki = usunTabliceJLabel(tablicaJLabelWierzcholki,
							panelDoWprowadzaniaWartosciWierzcholkow);
					Globalne.okno.repaint();
				}
			});
		}
		return przyciskWartosciNie;
	}

	static JRadioButton getPrzyciskWartosciTak() {
		if (przyciskWartosciTak == null) {
			przyciskWartosciTak = new JRadioButton();
			przyciskWartosciTak.setSelected(true);
			przyciskWartosciTak.setText("tak");
			przyciskWartosciTak.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					tablicaTextFieldWierzcholki = usunTabliceJTextField(tablicaTextFieldWierzcholki,
							panelDoWprowadzaniaWartosciWierzcholkow);
					tablicaJLabelWierzcholki = usunTabliceJLabel(tablicaJLabelWierzcholki,
							panelDoWprowadzaniaWartosciWierzcholkow);
					getTablicaTextFieldAndJLabelWierzcholki();
					panelDoWprowadzaniaWartosciWierzcholkow.revalidate();
					panelDoWprowadzaniaWartosciWierzcholkow.repaint();
					Globalne.okno.repaint();
				}
			});
		}
		return przyciskWartosciTak;
	}

	static JButton getPrzyciskZapisz() {
		if (przyciskZapisz == null) {
			przyciskZapisz = new JButton();
			przyciskZapisz.setText("Zapisz");
			przyciskZapisz.setEnabled(true);
			przyciskZapisz.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					int liczbaW = (int) wyborIlosciWierzcholkow.getSelectedItem();
					int liczbaK = (int) wyborIlosciKrawedzi.getSelectedItem();
					int bladPrzecinkaWartosci = 0;

					if (przyciskWartosciTak.isSelected()) {
						for (int i = 0; i < liczbaW; i++) {
							String wartoscWierzcholka = tablicaTextFieldWierzcholki[i].getText();
							char[] tablicaZnakow = wartoscWierzcholka.toCharArray();
							for (int j = 0; j < tablicaZnakow.length; j++) {
								if (tablicaZnakow[j] == ',' || tablicaZnakow[j] == ';')
									bladPrzecinkaWartosci = 1;
							}
						}
					}
					int czyPoprawneWartosciWierzcholkow = 1;
					int czyPoprawneKrawedzie = 1;
					if (liczbaW > 0 && przyciskWartosciTak.isSelected()) {
						czyPoprawneWartosciWierzcholkow = Graf
								.sprawdzCzyPoprawneWartosciWierzcholkow(tablicaTextFieldWierzcholki);
					}
					if (liczbaK > 0) {
						czyPoprawneKrawedzie = Graf.sprawdzWierzcholkoStartoweKoncowe(tablicaTextFieldKrawedzie,
								liczbaW);
					}

					if (czyPoprawneWartosciWierzcholkow == 1 && czyPoprawneKrawedzie == 1
							&& bladPrzecinkaWartosci == 0) {

						Globalne.grafy[Globalne.aktywnyGraf].liczbaWierzcholkow = liczbaW;
						Globalne.grafy[Globalne.aktywnyGraf].liczbaKrawedzi = liczbaK;

						if (przyciskSkierowany.isSelected()) {
							Globalne.grafy[Globalne.aktywnyGraf].czySkierowany = true;
							Globalne.grafy[Globalne.aktywnyGraf].czyNieskierowany = false;
						} else {
							Globalne.grafy[Globalne.aktywnyGraf].czySkierowany = false;
							Globalne.grafy[Globalne.aktywnyGraf].czyNieskierowany = true;
						}

						if (przyciskWartosciNie.isSelected()) {
							Globalne.grafy[Globalne.aktywnyGraf].wartoscWierzcholka = null;
						} else if (przyciskWartosciTak.isSelected()) {
							Globalne.grafy[Globalne.aktywnyGraf].wartoscWierzcholka = new String[liczbaW];
							for (int i = 0; i < liczbaW; i++) {
								String wartoscWierzcholka = i + " - " + tablicaTextFieldWierzcholki[i].getText();
								Globalne.grafy[Globalne.aktywnyGraf].wartoscWierzcholka[i] = wartoscWierzcholka;
							}
						}

						Graf.stworzGraf(tablicaTextFieldKrawedzie, tablicaJLabelKrawedzie);

						tablicaTextFieldKrawedzie = usunTabliceJTextField(tablicaTextFieldKrawedzie,
								panelDoWprowadzaniaKrawedzi);
						tablicaJLabelKrawedzie = usunTabliceJLabel(tablicaJLabelKrawedzie, panelDoWprowadzaniaKrawedzi);
						tablicaTextFieldWierzcholki = usunTabliceJTextField(tablicaTextFieldWierzcholki,
								panelDoWprowadzaniaWartosciWierzcholkow);
						tablicaJLabelWierzcholki = usunTabliceJLabel(tablicaJLabelWierzcholki,
								panelDoWprowadzaniaWartosciWierzcholkow);

						Globalne.informacjeOWykonanymAlgorytmie += "\n\nStworzon nowy graf. Numer grafu "
								+ Globalne.aktywnyGraf + ".";
						PanelAlgorytmow.zmienInformacjeOWykonanymAlgorytmie();
						PanelAlgorytmow.wlaczLubWylaczPrzyciskWykonaj();
						przyciskWartosciTak.setSelected(true);
						Globalne.cardlayout.show(Globalne.panele, "A");

					} else if (czyPoprawneWartosciWierzcholkow == -1) {
						JOptionPane.showMessageDialog(null, "Wartoœci wierzcho³ków nie mog¹ byæ puste!");
					} else if (czyPoprawneKrawedzie == -1) {
						JOptionPane.showMessageDialog(null, "Ktoras z wprowadzonych danych nie jest liczba!");
					} else if (czyPoprawneKrawedzie == -2) {
						JOptionPane.showMessageDialog(null, "Nie ma takiego numeru wierzcholku!");
					} else if (bladPrzecinkaWartosci == 1) {
						JOptionPane.showMessageDialog(null, "W wartoœci wierzcho³ka nie mo¿e wystêpowaæ \",\"");
					}
				}
			});
		}
		return przyciskZapisz;
	}

	static JButton getPrzyciskPrzerwij() {
		if (przyciskPrzerwij == null) {
			przyciskPrzerwij = new JButton();
			przyciskPrzerwij.setText("Przerwij");
			przyciskPrzerwij.setEnabled(true);
			przyciskPrzerwij.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int potwierdzenie = JOptionPane.showConfirmDialog(null,
							"Czy na pewno chcesz przerwaæ " + "wprowadzanie grafu?", "Info", JOptionPane.YES_NO_OPTION);
					if (potwierdzenie == JOptionPane.YES_OPTION) {
						wyborIlosciWierzcholkow.setSelectedIndex(4);
						wyborIlosciKrawedzi.setSelectedIndex(5);

						tablicaTextFieldKrawedzie = usunTabliceJTextField(tablicaTextFieldKrawedzie,
								panelDoWprowadzaniaKrawedzi);
						tablicaJLabelKrawedzie = usunTabliceJLabel(tablicaJLabelKrawedzie, panelDoWprowadzaniaKrawedzi);
						tablicaTextFieldWierzcholki = usunTabliceJTextField(tablicaTextFieldWierzcholki,
								panelDoWprowadzaniaWartosciWierzcholkow);
						tablicaJLabelWierzcholki = usunTabliceJLabel(tablicaJLabelWierzcholki,
								panelDoWprowadzaniaWartosciWierzcholkow);

						Graf.ustawAktywnyGrafPoPrzerwaniuLubUsunieciu();
						PanelAlgorytmow.wlaczLubWylaczPrzyciskWykonaj();
						Globalne.cardlayout.show(Globalne.panele, "A");
					}
				}
			});
		}
		return przyciskPrzerwij;
	}

	static JComboBox<Integer> getWyborIlosciWierzcholkow() {
		if (wyborIlosciWierzcholkow == null) {
			Integer[] iloscWierzcholkow = new Integer[] {};
			wyborIlosciWierzcholkow = new JComboBox<Integer>(iloscWierzcholkow);

			int i = 1;
			for (i = 1; i < Globalne.MAX_LICZBA_WIERZCHOLKOW; i++) {
				wyborIlosciWierzcholkow.addItem(i);
			}
			wyborIlosciWierzcholkow.setSelectedIndex(4);
			wyborIlosciWierzcholkow.setEditable(false);
		}
		wyborIlosciWierzcholkow.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				tablicaTextFieldWierzcholki = usunTabliceJTextField(tablicaTextFieldWierzcholki,
						panelDoWprowadzaniaWartosciWierzcholkow);
				tablicaJLabelWierzcholki = usunTabliceJLabel(tablicaJLabelWierzcholki,
						panelDoWprowadzaniaWartosciWierzcholkow);
				if (przyciskWartosciTak.isSelected())
					getTablicaTextFieldAndJLabelWierzcholki();
				panelDoWprowadzaniaWartosciWierzcholkow.revalidate();
				panelDoWprowadzaniaWartosciWierzcholkow.repaint();
				Globalne.okno.repaint();
			}
		});
		return wyborIlosciWierzcholkow;
	}

	static JComboBox<Integer> getWyborIlosciKrawedzi() {
		if (wyborIlosciKrawedzi == null) {
			Integer[] iloscWierzcholkow = new Integer[] {};
			wyborIlosciKrawedzi = new JComboBox<Integer>(iloscWierzcholkow);

			int i = 0;
			for (i = 0; i < Globalne.MAX_LICZBA_KRAWEDZI; i++) {
				wyborIlosciKrawedzi.addItem(i);
			}
			wyborIlosciKrawedzi.setSelectedIndex(5);
			wyborIlosciKrawedzi.setEditable(false);
		}
		wyborIlosciKrawedzi.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {

				scrollPanelWprowadzaniaKrawedzi.setVisible(true);
				Font font = new Font("Dialog", Font.BOLD, 18);
				scrollPanelWprowadzaniaKrawedzi.setBorder(BorderFactory.createTitledBorder(null, "Krawedzie", 
							TitledBorder.LEFT, TitledBorder.TOP, font, Color.RED));

				tablicaTextFieldKrawedzie = usunTabliceJTextField(tablicaTextFieldKrawedzie,
						panelDoWprowadzaniaKrawedzi);
				tablicaJLabelKrawedzie = usunTabliceJLabel(tablicaJLabelKrawedzie, panelDoWprowadzaniaKrawedzi);
				getTablicaTextFieldAndJLabelKrawedzie();
				panelDoWprowadzaniaKrawedzi.revalidate();
				panelDoWprowadzaniaKrawedzi.repaint();
				Globalne.okno.repaint();
			}
		});
		return wyborIlosciKrawedzi;
	}

	static void getTablicaTextFieldAndJLabelKrawedzie() {
		int tmp = 0;

		int iloscKrawedzi = (int) wyborIlosciKrawedzi.getSelectedItem();

		if (tablicaTextFieldKrawedzie == null)
			tablicaTextFieldKrawedzie = new JTextField[iloscKrawedzi * 2];

		if (tablicaJLabelKrawedzie == null)
			tablicaJLabelKrawedzie = new JLabel[iloscKrawedzi];

		for (int i = 0; i < tablicaTextFieldKrawedzie.length; i++) {
			if (i % 2 == 0) {
				tablicaJLabelKrawedzie[tmp] = new JLabel();
				tablicaJLabelKrawedzie[tmp].setText(String.valueOf(tmp) + ". ");
				panelDoWprowadzaniaKrawedzi.add(tablicaJLabelKrawedzie[tmp], null);
				tmp++;
			}
			tablicaTextFieldKrawedzie[i] = new JTextField();
			tablicaTextFieldKrawedzie[i].setText("0");
			panelDoWprowadzaniaKrawedzi.add(tablicaTextFieldKrawedzie[i], null);

		}
	}

	static void getTablicaTextFieldAndJLabelWierzcholki() {
		int iloscWierzcholkow = (int) wyborIlosciWierzcholkow.getSelectedItem();

		if (tablicaTextFieldWierzcholki == null)
			tablicaTextFieldWierzcholki = new JTextField[iloscWierzcholkow];

		if (tablicaJLabelWierzcholki == null)
			tablicaJLabelWierzcholki = new JLabel[iloscWierzcholkow];

		for (int i = 0; i < tablicaTextFieldWierzcholki.length; i++) {
			tablicaJLabelWierzcholki[i] = new JLabel();
			tablicaJLabelWierzcholki[i].setText(String.valueOf(i) + ". ");
			panelDoWprowadzaniaWartosciWierzcholkow.add(tablicaJLabelWierzcholki[i], null);

			tablicaTextFieldWierzcholki[i] = new JTextField();
			tablicaTextFieldWierzcholki[i].setText("0");
			panelDoWprowadzaniaWartosciWierzcholkow.add(tablicaTextFieldWierzcholki[i], null);
		}
	}

	public static JTextField[] usunTabliceJTextField(JTextField[] tablicaJFieldText, JPanel panel) {
		if (tablicaJFieldText != null)
			for (int i = 0; i < tablicaJFieldText.length; i++)
				panel.remove(tablicaJFieldText[i]);

		tablicaJFieldText = null;
		return tablicaJFieldText;
	}

	public static JLabel[] usunTabliceJLabel(JLabel[] tablicaJLabel, JPanel panel) {
		if (tablicaJLabel != null)
			for (int i = 0; i < tablicaJLabel.length; i++)
				panel.remove(tablicaJLabel[i]);

		tablicaJLabel = null;
		return tablicaJLabel;
	}
}
