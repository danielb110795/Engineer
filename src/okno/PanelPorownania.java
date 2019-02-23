package okno;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.stream.DoubleStream;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

import graf.BFS;
import graf.DFS;
import graf.Graf;
import graf.KolorowanieDokladne;
import graf.KolorowanieKrawedzi;
import graf.KwadratGrafu;
import graf.LF;
import graf.SL;
import graf.SLF;
import graf.Sequential;
import graf.Transpozycja;
import zmienne.Globalne;

public class PanelPorownania {
	static WatekPorownanie watek;

	static JButton przyciskWykonaj = null;
	static JButton przyciskPrzerwijWykonywanie = null;
	static JComboBox<String> wyborAlgorytmu = null;
	static JPanel panelWyboruAlgorytmu = null;
	static JPanel panelPrzycisku = null;
	static JPanel panelPrzycisku2 = null;
	static JPanel panelRodzajuAnalizy = null;
	static JPanel panelWyboruCzasu = null;
	static Box box = Box.createHorizontalBox();
	static JRadioButton przyciskKrawedzie = null;
	static JRadioButton przyciskWierzcholki = null;
	static JRadioButton przyciskKrawedzieWierzcholki = null;
	static JRadioButton przyciskMilisekundy = null;
	static JRadioButton przyciskNanosekundy = null;
	static org.jfree.chart.JFreeChart chart;
	static ChartPanel chartPanel = null;
	static DefaultCategoryDataset dataset;
	static int[] X;
	static double[] Y;

	static JPanel getPanelPorownania() {
		if (Globalne.panelPorownania == null) {

			Globalne.panelPorownania = new JPanel(new BorderLayout());
			initUI();

			if (panelWyboruAlgorytmu == null)
				panelWyboruAlgorytmu = new JPanel(new GridLayout(0, 1));
			panelWyboruAlgorytmu.setBorder(BorderFactory.createTitledBorder("Wybierz algorytm"));
			panelWyboruAlgorytmu.add(getWyborAlgorytmu());

			if (panelPrzycisku == null)
				panelPrzycisku = new JPanel(new GridLayout(0, 1));
			panelPrzycisku.setBorder(BorderFactory.createTitledBorder("Przycisk"));
			panelPrzycisku.add(getPrzyciskWykonaj());

			if (panelPrzycisku2 == null)
				panelPrzycisku2 = new JPanel(new GridLayout(0, 1));
			panelPrzycisku2.setBorder(BorderFactory.createTitledBorder("Przycisk"));
			panelPrzycisku2.add(getPrzyciskPrzerwijWykonywanie());
			panelPrzycisku2.setVisible(false);

			if (panelRodzajuAnalizy == null)
				panelRodzajuAnalizy = new JPanel(new GridLayout(1, 3));
			panelRodzajuAnalizy.setBorder(BorderFactory.createTitledBorder("Typ analizy"));
			panelRodzajuAnalizy.add(getPrzyciskCzasWierzcholki());
			panelRodzajuAnalizy.add(getPrzyciskCzasKrawedzie());
			panelRodzajuAnalizy.add(getPrzyciskCzasKrawedzieWierzcholki());
			ButtonGroup grupaPrzyciskow = new ButtonGroup();
			grupaPrzyciskow.add(przyciskKrawedzie);
			grupaPrzyciskow.add(przyciskWierzcholki);
			grupaPrzyciskow.add(przyciskKrawedzieWierzcholki);

			if (panelWyboruCzasu == null)
				panelWyboruCzasu = new JPanel(new GridLayout(1, 2));
			panelWyboruCzasu.setBorder(BorderFactory.createTitledBorder("Czas"));
			panelWyboruCzasu.add(getPrzyciskMilisekundy());
			panelWyboruCzasu.add(getPrzyciskNanosekundy());
			ButtonGroup grupaPrzyciskowCzasu = new ButtonGroup();
			grupaPrzyciskowCzasu.add(przyciskNanosekundy);
			grupaPrzyciskowCzasu.add(przyciskMilisekundy);

			box.add(panelRodzajuAnalizy);
			box.add(panelWyboruAlgorytmu);
			box.add(panelWyboruCzasu);
			box.add(panelPrzycisku);
			box.add(panelPrzycisku2);

			Globalne.panelPorownania.add(box, BorderLayout.SOUTH);
		}
		return Globalne.panelPorownania;
	}

	static JComboBox<String> getWyborAlgorytmu() {
		if (wyborAlgorytmu == null) {
			String[] algorytmy = new String[] { "DFS - macierz s¹siedztwa", "DFS - lista s¹siedztwa",
					"BFS - macierz s¹siedztwa", "BFS - lista s¹siedztwa", "Kolorowanie sequential",
					"Kolorowanie dok³adne", "Kolorowanie LF", "Kolorowanie SL", "Kolorowanie SLF",
					"Kolorowanie krawêdzi", "Transpozycja", "Kwadrat grafu" };
			wyborAlgorytmu = new JComboBox<String>(algorytmy);
			wyborAlgorytmu.setEditable(false);
			wyborAlgorytmu.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

				}
			});
		}
		return wyborAlgorytmu;
	}

	static JButton getPrzyciskPrzerwijWykonywanie() {
		if (przyciskPrzerwijWykonywanie == null) {
			przyciskPrzerwijWykonywanie = new JButton("Przerwij");
			przyciskPrzerwijWykonywanie.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(java.awt.event.ActionEvent e) {
					watek.stop();
					JOptionPane.showMessageDialog(null, "Przerwano wykonywane obliczeñ!");
					panelPrzycisku.setVisible(true);
					panelPrzycisku2.setVisible(false);
				}
			});
		}
		return przyciskPrzerwijWykonywanie;
	}

	static JButton getPrzyciskWykonaj() {
		if (przyciskWykonaj == null) {
			przyciskWykonaj = new JButton("Wykonaj");
			przyciskWykonaj.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String wybranyAlgorytm = (String) wyborAlgorytmu.getSelectedItem();
					if (wybranyAlgorytm.equals("Kolorowanie dok³adne")
							|| wybranyAlgorytm.equals("Kolorowanie sequential")
							|| wybranyAlgorytm.equals("Kolorowanie LF") || wybranyAlgorytm.equals("Kolorowanie SL")
							|| wybranyAlgorytm.equals("Kolorowanie SLF")) {
						boolean czySaPetle = sprawdzCzyGrafyMajaPetle();

						if (czySaPetle == true) {
							watek = new WatekPorownanie();
							watek.start();
						} else
							JOptionPane.showMessageDialog(null,
									"Któryœ z grafów ma pêtle, nie mo¿na wykonaæ tego algorytmu.");
					} else if (wybranyAlgorytm.equals("Transpozycja")) {
						boolean czyWszystkieSkierowane = sprawdzCzyGrafySkierowane();
						if (czyWszystkieSkierowane == true) {
							watek = new WatekPorownanie();
							watek.start();
						} else
							JOptionPane.showMessageDialog(null,
									"Wszystkie grafy musz¹ byæ sierowane aby wykonaæ ten algorytm.");
					} else if (wybranyAlgorytm.equals("Kwadrat grafu")) {
						boolean czyProste = sprawdzCzyGrafyProste();
						boolean czyWszystkieSkierowane = sprawdzCzyGrafySkierowane();
						if (czyProste && czyWszystkieSkierowane) {
							watek = new WatekPorownanie();
							watek.start();
						} else
							JOptionPane.showMessageDialog(null,
									"Wszystkie grafy musz¹ byæ proste (brak krawêdzi wielokrotnych i pêtli) i skierowane.");
					} else if (wybranyAlgorytm.equals("Kolorowanie krawêdzi")) {
						boolean czyProste = sprawdzCzyGrafyProste();
						if (czyProste) {
							watek = new WatekPorownanie();
							watek.start();
						} else
							JOptionPane.showMessageDialog(null,
									"Wszystkie grafy musz¹ byæ proste (brak krawêdzi wielokrotnych i pêtli).");
					} else {
						watek = new WatekPorownanie();
						watek.start();
					}
				}
			});
		}
		return przyciskWykonaj;
	}

	static JRadioButton getPrzyciskCzasKrawedzie() {
		if (przyciskKrawedzie == null) {
			przyciskKrawedzie = new JRadioButton("Czas - E");
			przyciskKrawedzie.setSelected(true);
			przyciskKrawedzie.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						chart.setTitle("Analiza czasu do liczby krawêdzi");
						CategoryPlot plot = chart.getCategoryPlot();
				        CategoryAxis axis = plot.getDomainAxis();
				        axis.setAttributedLabel("Krawêdzie");
					}
				}
			});
		}
		return przyciskKrawedzie;
	}

	static JRadioButton getPrzyciskCzasWierzcholki() {
		if (przyciskWierzcholki == null) {
			przyciskWierzcholki = new JRadioButton("Czas - V");
			przyciskWierzcholki.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						chart.setTitle("Analiza czasu do liczby wierzcho³ków");
						CategoryPlot plot = chart.getCategoryPlot();
				        CategoryAxis axis = plot.getDomainAxis();
				        axis.setAttributedLabel("Wierzcho³ki");
					}
				}
			});
		}
		return przyciskWierzcholki;
	}

	static JRadioButton getPrzyciskCzasKrawedzieWierzcholki() {
		if (przyciskKrawedzieWierzcholki == null) {
			przyciskKrawedzieWierzcholki = new JRadioButton("Czas - V+E");
			przyciskKrawedzieWierzcholki.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						chart.setTitle("Analiza czasu do liczby krawêdzi+wierzcho³ków");
						CategoryPlot plot = chart.getCategoryPlot();
				        CategoryAxis axis = plot.getDomainAxis();
				        axis.setAttributedLabel("Krawêdzie + wierzcho³ki");
					}
				}
			});
		}
		return przyciskKrawedzieWierzcholki;
	}

	static JRadioButton getPrzyciskMilisekundy() {
		if (przyciskMilisekundy == null) {
			przyciskMilisekundy = new JRadioButton("ms");
			przyciskMilisekundy.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						  CategoryPlot p = chart.getCategoryPlot(); 
					      ValueAxis axis2 = p.getRangeAxis();
					      axis2.setAttributedLabel("Czas [ms]");
					}
				}
			});
		}
		return przyciskMilisekundy;
	}

	static JRadioButton getPrzyciskNanosekundy() {
		if (przyciskNanosekundy == null) {
			przyciskNanosekundy = new JRadioButton("ns");
			przyciskNanosekundy.setSelected(true);
			przyciskNanosekundy.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						  CategoryPlot p = chart.getCategoryPlot(); 
					      ValueAxis axis2 = p.getRangeAxis();
					      axis2.setAttributedLabel("Czas [ns]");
					}
				}
			});
		}
		return przyciskNanosekundy;
	}

	static int[] stworzWartosciX() {

		Graf[] grafy = stworzKopieGrafów();
		int[] X = new int[grafy.length];
		for (int i = 0; i < grafy.length; i++) {
			if (przyciskKrawedzie.isSelected())
				X[i] = grafy[i].liczbaKrawedzi;
			else if (przyciskWierzcholki.isSelected())
				X[i] = grafy[i].liczbaWierzcholkow;
			else if (przyciskKrawedzieWierzcholki.isSelected())
				X[i] = grafy[i].liczbaWierzcholkow + grafy[i].liczbaKrawedzi;
		}
		return X;
	}

	static double[] stworzWartosciY() {
		Graf[] grafy = stworzKopieGrafów();

		double[] Y = new double[grafy.length];
		String wybranyAlgorytm = (String) wyborAlgorytmu.getSelectedItem();
		long czasRozpoczecia, czasZakonczenia, czasTrwania;
		for (int i = 0; i < grafy.length; i++) {
			if (wybranyAlgorytm.equals("DFS - macierz s¹siedztwa")) {
				DFS dfs = new DFS();
				boolean[] odwiedzone = new boolean[grafy[i].liczbaWierzcholkow];
				czasRozpoczecia = rozpoznajCzas();
				dfs.DFSMacierzSasiedztwa(0, grafy[i].macierzSasiedztwa, odwiedzone);
				czasZakonczenia = rozpoznajCzas();
				czasTrwania = czasZakonczenia - czasRozpoczecia;
				Y[i] = czasTrwania;

			} else if (wybranyAlgorytm.equals("DFS - lista s¹siedztwa")) {
				DFS dfs = new DFS();
				boolean[] odwiedzone = new boolean[grafy[i].liczbaWierzcholkow];
				czasRozpoczecia = rozpoznajCzas();
				dfs.DFSListaSasiedztwa(0, grafy[i].listaSasiedztwa, odwiedzone);
				czasZakonczenia = rozpoznajCzas();
				czasTrwania = czasZakonczenia - czasRozpoczecia;
				Y[i] = czasTrwania;

			} else if (wybranyAlgorytm.equals("BFS - lista s¹siedztwa")) {
				BFS bfs = new BFS();
				czasRozpoczecia = rozpoznajCzas();
				bfs.BFSListaSasiedztwa(0, grafy[i].listaSasiedztwa);
				czasZakonczenia = rozpoznajCzas();
				czasTrwania = czasZakonczenia - czasRozpoczecia;
				Y[i] = czasTrwania;

			} else if (wybranyAlgorytm.equals("BFS - macierz s¹siedztwa")) {
				BFS bfs = new BFS();
				czasRozpoczecia = rozpoznajCzas();
				bfs.BFSMacierzSasiedztwa(0, grafy[i].macierzSasiedztwa);
				czasZakonczenia = rozpoznajCzas();
				czasTrwania = czasZakonczenia - czasRozpoczecia;
				Y[i] = czasTrwania;

			} else if (wybranyAlgorytm.equals("Kolorowanie dok³adne")) {
				KolorowanieDokladne kDokladne = new KolorowanieDokladne();
				czasRozpoczecia = rozpoznajCzas();
				kDokladne.kolorujDokladnie(grafy[i].listaSasiedztwa, grafy[i].liczbaKrawedzi);
				czasZakonczenia = rozpoznajCzas();
				czasTrwania = czasZakonczenia - czasRozpoczecia;
				Y[i] = czasTrwania;

			} else if (wybranyAlgorytm.equals("Kolorowanie sequential")) {
				Sequential sequential = new Sequential();
				czasRozpoczecia = rozpoznajCzas();
				sequential.kolorujS(grafy[i].listaSasiedztwa, grafy[i].czySkierowany);
				czasZakonczenia = rozpoznajCzas();
				czasTrwania = czasZakonczenia - czasRozpoczecia;
				Y[i] = czasTrwania;

			} else if (wybranyAlgorytm.equals("Kolorowanie LF")) {
				LF lf = new LF();
				czasRozpoczecia = rozpoznajCzas();
				lf.koloruj(grafy[i].listaSasiedztwa, grafy[i].czySkierowany);
				czasZakonczenia = rozpoznajCzas();
				czasTrwania = czasZakonczenia - czasRozpoczecia;
				Y[i] = czasTrwania;

			} else if (wybranyAlgorytm.equals("Kolorowanie SL")) {
				SL sl = new SL();
				czasRozpoczecia = rozpoznajCzas();
				sl.kolorujSL(grafy[i].listaSasiedztwa, grafy[i].czySkierowany);
				czasZakonczenia = rozpoznajCzas();
				czasTrwania = czasZakonczenia - czasRozpoczecia;
				Y[i] = czasTrwania;

			} else if (wybranyAlgorytm.equals("Kolorowanie SLF")) {
				SLF slf = new SLF();
				czasRozpoczecia = rozpoznajCzas();
				slf.kolorujSLF(grafy[i].macierzSasiedztwa);
				czasZakonczenia = rozpoznajCzas();
				czasTrwania = czasZakonczenia - czasRozpoczecia;
				Y[i] = czasTrwania;

			} else if (wybranyAlgorytm.equals("Kolorowanie krawêdzi")) {
				KolorowanieKrawedzi kk = new KolorowanieKrawedzi();
				czasRozpoczecia = rozpoznajCzas();
				kk.kolorujKrawedzie(grafy[i].macierzSasiedztwa, grafy[i].czySkierowany);
				czasZakonczenia = rozpoznajCzas();
				czasTrwania = czasZakonczenia - czasRozpoczecia;
				Y[i] = czasTrwania;

			} else if (wybranyAlgorytm.equals("Transpozycja")) {
				Transpozycja transpozycja = new Transpozycja();
				czasRozpoczecia = rozpoznajCzas();
				transpozycja.transponujGraf(grafy[i]);
				czasZakonczenia = rozpoznajCzas();
				czasTrwania = czasZakonczenia - czasRozpoczecia;
				Y[i] = czasTrwania;

			} else if (wybranyAlgorytm.equals("Kwadrat grafu")) {
				// czy graf prosty
				KwadratGrafu kwadratGrafu = new KwadratGrafu();
				czasRozpoczecia = rozpoznajCzas();
				kwadratGrafu.wyznaczKwadratGrafuMacierz(grafy[i].macierzSasiedztwa);
				czasZakonczenia = rozpoznajCzas();
				czasTrwania = czasZakonczenia - czasRozpoczecia;
				Y[i] = czasTrwania;
			}
		}
		return Y;
	}

	private static boolean sprawdzCzyGrafyMajaPetle() {
		Graf[] grafy = stworzKopieGrafów();
		for (int i = 0; i < grafy.length; i++) {
			boolean test = Graf.sprawdzCzyGrafMaPetle(grafy[i]);
			if (test == true)
				return true;
		}
		return false;
	}

	private static boolean sprawdzCzyGrafySkierowane() {
		Graf[] grafy = stworzKopieGrafów();
		for (int i = 0; i < grafy.length; i++)
			if (grafy[i].czySkierowany == false)
				return false; // nie jest skierowany
		return true; // wszystkie skierowane
	}

	private static boolean sprawdzCzyGrafyProste() {
		Graf[] grafy = stworzKopieGrafów();
		for (int i = 0; i < grafy.length; i++) {
			boolean test = Graf.sprawdzCzyToGrafProsty(grafy[i]);
			if (test == false)
				return false; // nie prosty
		}
		return true; // proste
	}

	private static Graf[] stworzKopieGrafów() {
		int ilosc = Graf.sprawdzIloscIstniejacychGrafow();
		Graf[] grafy = new Graf[ilosc];
		int tmp = 0;
		for (int i = 0; i < Globalne.MAX_LICZBA_GRAFOW; i++) {
			if (Globalne.grafy[i].czyIstnieje == true) {
				grafy[tmp] = Globalne.grafy[i];
				tmp++;
			}
		}
		return grafy;
	}

	public static void aktualizujWykres() {
		X = stworzWartosciX();
		Y = stworzWartosciY();
		int max = Arrays.stream(X).max().getAsInt();			 
		int[] czyTakaSamaIlosc = new int[max];
		Arrays.fill(czyTakaSamaIlosc, 0);
		boolean contains = DoubleStream.of(Y).allMatch(x -> x == 0);
		if(contains == true)
			JOptionPane.showMessageDialog(null, "Wybierz ns w celu porownania. Algorytmy wykonuja sie bardzo krotko.");
		dataset.clear();
		for (int i = 0; i < X.length; i++) {
			czyTakaSamaIlosc[X[i] - 1] ++;
			dataset.setValue(Y[i], "Graf" + czyTakaSamaIlosc[X[i] - 1], String.valueOf(X[i]));
		}

	}

	private static void initUI() {
		dataset = new DefaultCategoryDataset();
		chart = createChart(dataset); // tu na null i nie ma wykresu
		chartPanel = new ChartPanel(chart);
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		Globalne.panelPorownania.add(chartPanel);

	}

	private static org.jfree.chart.JFreeChart createChart(DefaultCategoryDataset dataset) {
		chart = ChartFactory.createBarChart3D("Analiza czasu do liczby krawêdzi",
				"Krawêdzie", "Czas [ns]", dataset, PlotOrientation.VERTICAL,
				false, true, false);

		chart.setTitle(new TextTitle("Analza czasu do liczby krawêdzi", new Font("Serif", java.awt.Font.BOLD, 18)));

		return chart;
	}

	private static long rozpoznajCzas() {
		long czas;
		if (przyciskMilisekundy.isSelected())
			czas = System.currentTimeMillis();
		else
			czas = System.nanoTime();

		return czas;
	}
}
