package alphabet;
/**
 * Klasa tworzy alfabetet na podstawie pliku txt
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class AlphabetCreator {

	private static Scanner odczyt;
	public static  String publicLine;
	static File file = new File(
			"wiadomosc.txt");

	public static List<Symbols> createAlphabt(List<Symbols> symbols)
			throws FileNotFoundException {

		String line = getMsg(), symbol;
		publicLine=line;
		int i = 0, counter = line.length();
		while (i < counter) {
			symbol = line.substring(i, i + 1);
			if (check(symbols, symbol)) {

				symbols.add(new Symbols(symbol, count(line, symbol, i + 1)));
			}
			i++;
		}
		return symbols;

	}
	/**
	 * metoda odpowiedzialna za wydobycie wiadomosci z pliku i przerobienie jej na stringa
	 * @return zwykly string z wiadomoscia
	 * @throws FileNotFoundException
	 */
	public static String getMsg() throws FileNotFoundException {

		odczyt = new Scanner(file);
		String line = odczyt.nextLine();
		while (odczyt.hasNext()) {
			line += odczyt.nextLine();
		}
		
		return line;
	}
	/**
	 * metoda sprawdza czy dany symbol wczesniej juz nie wystepowal na liscie alfabetu
	 * @param symbols
	 * @param symbol
	 * @return
	 */
	private static boolean check(List<Symbols> symbols, String symbol) {

		for (int i = 0; i < symbols.size(); i++) {
			if (symbols.get(i).getSymbol().equals(symbol)) {
				return false;
			}
		}
		return true;
	}
	/**
	 * metoda liczy ilosc wystapien danego symbolu
	 * @param line
	 * @param symbol
	 * @param counter
	 * @return
	 */
	private static double count(String line, String symbol, int counter) {
		int length = line.length();
		double probability = length;
		double numberOfmarks = 1;

		for (int i = counter; i < length; i++) {
			if (line.subSequence(i, i + 1).equals(symbol)) {
				numberOfmarks++;
			}
		}

		return numberOfmarks / probability;

	}
}
