package okno;

public class WatekPorownanie extends Thread {

	public void run() {
		PanelPorownania.panelPrzycisku2.setVisible(true);
		PanelPorownania.panelPrzycisku.setVisible(false);

		PanelPorownania.aktualizujWykres();

		PanelPorownania.panelPrzycisku.setVisible(true);
		PanelPorownania.panelPrzycisku2.setVisible(false);
	}
}
