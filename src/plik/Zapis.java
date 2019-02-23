package plik;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;

import zmienne.Globalne;

public class Zapis {
	
	public static String odczytajTekstZJTextArea(JTextArea textArea) 
	{
		String tekst = "";
		Scanner skaner = new Scanner(textArea.getText());
		while (skaner.hasNext())
			tekst += skaner.nextLine() + System.getProperty("line.separator");
		skaner.close();
		return tekst;
	}
	
	public static String stworzStringDoZapisuGrafu(int wybranyGraf) 
	{
		String tekst = "";
		if (Globalne.grafy[wybranyGraf].czySkierowany == true) 
		{
			tekst = "Graf skierowany: true" + System.getProperty("line.separator") +
					"Graf nieskierowany: false" + System.getProperty("line.separator");
		} else {
			tekst = "Graf skierowany: false" + System.getProperty("line.separator") +
					"Graf nieskierowany: true" + System.getProperty("line.separator");
		}
		
		tekst += "Liczba wierzcho³ków: " + Globalne.grafy[wybranyGraf].liczbaWierzcholkow + System.getProperty("line.separator") +
				 "Liczba krawêdzi: " + Globalne.grafy[wybranyGraf].liczbaKrawedzi + System.getProperty("line.separator");
		
		tekst +=  "Macierz s¹siedztwa: " + System.getProperty("line.separator");
		for(int i=0; i<Globalne.grafy[wybranyGraf].liczbaWierzcholkow; i++)
        {
			for(int j=0; j<Globalne.grafy[wybranyGraf].liczbaWierzcholkow; j++) 
			{    
				if (i == Globalne.grafy[wybranyGraf].liczbaWierzcholkow-1 && j == Globalne.grafy[wybranyGraf].liczbaWierzcholkow-1)
					tekst += Globalne.grafy[wybranyGraf].macierzSasiedztwa[i][j] + ";";
				else
					tekst += Globalne.grafy[wybranyGraf].macierzSasiedztwa[i][j] + ",";
			}
			tekst += System.getProperty("line.separator");
        }
		
		tekst += "Macierz incydencji: " + System.getProperty("line.separator");
		for(int i=0; i<Globalne.grafy[wybranyGraf].liczbaWierzcholkow; i++)
        {
			for(int j=0; j<Globalne.grafy[wybranyGraf].liczbaKrawedzi; j++) 
			{
				if (i == Globalne.grafy[wybranyGraf].liczbaWierzcholkow-1 && j == Globalne.grafy[wybranyGraf].liczbaKrawedzi-1)
					tekst += Globalne.grafy[wybranyGraf].macierzIncydencji[i][j] + ";";
				else
					tekst += Globalne.grafy[wybranyGraf].macierzIncydencji[i][j] + ",";
			}
			tekst += System.getProperty("line.separator");
        }
		
		tekst += "Lista s¹siedztwa: " + System.getProperty("line.separator");
		for(int i = 0; i < Globalne.grafy[wybranyGraf].listaSasiedztwa.length; i++) {
			int tmp = 0;
			if (Globalne.grafy[wybranyGraf].listaSasiedztwa[i].isEmpty() && Globalne.grafy[wybranyGraf].listaSasiedztwa.length-1 != i)
				tekst += "null.";
			else if (Globalne.grafy[wybranyGraf].listaSasiedztwa[i].isEmpty() && Globalne.grafy[wybranyGraf].listaSasiedztwa.length-1 == i)
				tekst += "null;";
			for(Iterator<Integer> it = Globalne.grafy[wybranyGraf].listaSasiedztwa[i].iterator(); it.hasNext(); ) {
				int rozmiar = Globalne.grafy[wybranyGraf].listaSasiedztwa[i].size();
				if(tmp != rozmiar - 1)
					tekst += it.next() + ",";
				else if (tmp == rozmiar - 1 && Globalne.grafy[wybranyGraf].listaSasiedztwa.length - 1 != i)
					tekst += it.next() + ".";	
				else if (tmp == rozmiar - 1 && Globalne.grafy[wybranyGraf].listaSasiedztwa.length - 1 == i)
					tekst += it.next() + ";";	
				tmp++;
		    }
			tekst += System.getProperty("line.separator");
		}
		
		tekst += "Wartoœci wierzcho³ków: " + System.getProperty("line.separator");
		if (Globalne.grafy[wybranyGraf].wartoscWierzcholka == null)	{
			tekst += "null";
		} else {
			for(int i=0; i < Globalne.grafy[wybranyGraf].wartoscWierzcholka.length; i++) {
				if(Globalne.grafy[wybranyGraf].liczbaWierzcholkow-1 != i)
					tekst += Globalne.grafy[wybranyGraf].wartoscWierzcholka[i] + ",";
				else
					tekst += Globalne.grafy[wybranyGraf].wartoscWierzcholka[i] + ";";
			}
		}		
		
		tekst += System.getProperty("line.separator");
		tekst += "Krawêdzie: " + System.getProperty("line.separator");
		if (Globalne.grafy[wybranyGraf].krawedzie != null) {
			for(int i=0; i < Globalne.grafy[wybranyGraf].krawedzie.length; i++) {		
					if (Globalne.grafy[wybranyGraf].krawedzie.length - 1 == i)
						tekst += Globalne.grafy[wybranyGraf].krawedzie[i] + ";";
					else
						tekst += Globalne.grafy[wybranyGraf].krawedzie[i] + ",";
			}
		} else {
			tekst += "null";
		}
			
		
		return tekst;
	}
	
	public static boolean zapiszTekstDoPlikuTxt (String tekst) {
		JFileChooser fc = new JFileChooser();
		
		if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
		{
			File plik = fc.getSelectedFile();
			String filePath = plik.getPath();
			if(!filePath.toLowerCase().endsWith(".txt"))
			{
				plik = new File(filePath + ".txt");
			}
			try 
			{
				PrintWriter pw = new PrintWriter(plik);
				pw.print(tekst);
				pw.close();
				return true;
			} catch (FileNotFoundException ex){
				ex.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
	}
}
