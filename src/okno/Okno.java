package okno;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import graf.Graf;
import zmienne.Globalne;

@SuppressWarnings("serial")
public class Okno extends JFrame {

	public static void ustawPanele() {
		Globalne.panele.add(Globalne.panelWG1, "WG1");
		Globalne.panele.add(Globalne.panelA, "A");
		Globalne.panele.add(Globalne.panelPorownania, "P");
		Globalne.cardlayout.show(Globalne.panele, "A");
	}

	public Okno() {
		super();
		Globalne.okno.setSize(800, 600);
		Graf.inicjalizacjaGrafow();
		PanelWprowadzaniaGrafu.getPanelWprowadzaniaGrafu1();
		PanelAlgorytmow.getPanelAlgorytmow();
		PanelPorownania.getPanelPorownania();
		Globalne.okno.add(Globalne.panele);
		ustawPanele();
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(Globalne.okno);
		//Globalne.okno.setResizable(false);
		Globalne.okno.setTitle("Algorytmy dla grafów bez wag");
		Globalne.okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Globalne.okno.setVisible(true);
		Globalne.okno.setJMenuBar(Globalne.gorneMenu.stworzGorneMenu());
	}
}
