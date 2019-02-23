package graf;

import java.awt.EventQueue;

import lombok.Data;
import okno.Okno;

@Data

public class Inzynier {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> new Okno());
	}
}
