package zmienne;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import graf.Graf;
import okno.GorneMenu;

public class Globalne {
	public static final int MAX_LICZBA_GRAFOW = 20;
	public static final int MAX_LICZBA_WIERZCHOLKOW = 30;
	public static final int MAX_LICZBA_KRAWEDZI = 200;

	public static Graf[] grafy = new Graf[MAX_LICZBA_GRAFOW];
	public static int aktywnyGraf = -1;

	public static JFrame okno = new JFrame("okno");
	public static CardLayout cardlayout = new CardLayout();
	public static JPanel panele = new JPanel(cardlayout);
	public static JPanel panelWG1 = null;
	public static JPanel panelA = null;
	public static JPanel panelPorownania = null;
	public static GorneMenu gorneMenu = new GorneMenu();

	public static String informacjeOWykonanymAlgorytmie = "Tutaj bêd¹ pojawia³y siê informacjê o wykonanych algorymtach.";

	public static boolean czyToNowyGraf = false;
}
