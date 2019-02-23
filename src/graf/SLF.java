package graf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Data;

@Data

/*
 * znajdŸ wierzcho³ek o maksymalnym stopniu spoœród wierzcho³ków o maksymalnym stopniu nasycenia
   pokoloruj znaleziony wierzcho³ek zach³annie
 */
public class SLF {

	private int liczbaWierzcholkow;
	private int[] kolorowanie;
	private List<Integer> kolejnosc;
	private List<Integer> doPokolorowania;

	public void kolorujSLF(int[][] macierzSasiedztwa) {

		liczbaWierzcholkow = macierzSasiedztwa.length;
		kolorowanie = new int[liczbaWierzcholkow];
		kolejnosc = new ArrayList<>();
		doPokolorowania = new ArrayList<>();

		Arrays.fill(kolorowanie, -1);
		for (int i = 0; i < liczbaWierzcholkow; i++)
			doPokolorowania.add(i);

		while (!doPokolorowania.isEmpty()) {

			Map<Integer, Integer> saturacjaWierzcholkow = new HashMap<>(); //wierzcholek, saturacja

			doPokolorowania.forEach((wierzcholek) -> {
				int saturacja = 0;

				for (int j = 0; j < liczbaWierzcholkow; j++) {
					if (wierzcholek != j) {
						if (macierzSasiedztwa[wierzcholek][j] > 0 || macierzSasiedztwa[j][wierzcholek] > 0) {
							if (kolorowanie[j] != -1) {
								saturacja++;
							}
						}
					}
				}

				saturacjaWierzcholkow.put(wierzcholek, saturacja);
			});

			Integer wierzcholek = saturacjaWierzcholkow.entrySet().stream()
					.max((e1, e2) -> e1.getValue() > e2.getValue() ? -1 : 1).get().getKey();

			doPokolorowania.remove(wierzcholek);
			Set<Integer> kolorySasiadow = new HashSet<>();
			kolejnosc.add(wierzcholek);
			for (int j = 0; j < liczbaWierzcholkow; j++) {
				if (wierzcholek != j) {
					if (macierzSasiedztwa[wierzcholek][j] > 0 || macierzSasiedztwa[j][wierzcholek] > 0) {
						kolorySasiadow.add(kolorowanie[j]);
					}
				}
			}

			Integer kolor = 0;
			while (kolorySasiadow.contains(kolor)) {
				kolor++;
			}
			kolorowanie[wierzcholek] = kolor;
		}
	}
}