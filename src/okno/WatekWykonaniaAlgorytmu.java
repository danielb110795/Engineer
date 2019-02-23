package okno;

public class WatekWykonaniaAlgorytmu extends Thread {

	public void run() {
		PanelAlgorytmow.panelPrzycisku2.setVisible(true);
		PanelAlgorytmow.panelPrzycisku.setVisible(false);

		PanelAlgorytmow.wykonajAlgorytm();

		PanelAlgorytmow.panelPrzycisku.setVisible(true);
		PanelAlgorytmow.panelPrzycisku2.setVisible(false);
	}
}
