package graf;

import lombok.Data;

/*
https://eduinf.waw.pl/inf/alg/001_search/0126b.php#P1

Algorytm obliczania kwadratu grafu zadanego macierz¹ s¹siedztwa
 */

@Data

public class KwadratGrafu {
	private int i, j, k;
	private int liczbaW;
	private int[][] kwadratGrafu;
	
	public int[][] wyznaczKwadratGrafuMacierz(int[][] macierzSasiedztwa) {
		liczbaW = macierzSasiedztwa.length;
		kwadratGrafu = new int[liczbaW][liczbaW];
		kwadratGrafu = Graf.kopiujMacierzSasiedztwa(macierzSasiedztwa);
		for (i = 0; i < liczbaW; i++) {
			for (j = 0; j < liczbaW; j++)
				if ((i != j) && macierzSasiedztwa[i][j] != 0)
					for (k = 0; k < liczbaW; k++)
						if (macierzSasiedztwa[j][k] != 0)
							kwadratGrafu[i][k] = 1;
		}
		return kwadratGrafu;
	}
}