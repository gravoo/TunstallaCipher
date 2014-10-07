package main;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import alphabet.AlphabetCreator;
import alphabet.Symbols;
import tree.Tree;

public class main {

	public static void main(String[] args) {

		long time1 = System.currentTimeMillis();
		long time2 = 0, finalTime = 0;
		long bitsInMsg = 0;
		List<Symbols> alphabet = new ArrayList<Symbols>();
		List<Symbols> nodes = new ArrayList<Symbols>();

		try {
			alphabet = AlphabetCreator.createAlphabt(alphabet);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("error");
		}
		for (int i = 0; i < alphabet.size(); i++) {
			nodes.add(new Symbols(alphabet.get(i).getSymbol(), alphabet.get(i)
					.getProbability()));
		}

		Symbols root = new Symbols("root", 0);

		Tree codeTree = new Tree(root, nodes, 10);

		codeTree.codeLeafs(alphabet);
		codeTree.print(codeTree.root);
		System.out.println("kod binarnie: ");
		codeTree.codeText(AlphabetCreator.publicLine);

		// liczba bitów stringa to jego długość * 8
		bitsInMsg = AlphabetCreator.publicLine.length() * 8;
		// podsumowanie
		time2 = System.currentTimeMillis();
		finalTime = time2 - time1;
		double speed = bitsInMsg/finalTime;
		System.out.println("liczba bitów w wiadomosci:" + bitsInMsg
				+ "\nczas potrzebny na zakodowanie wiadomosci w milisekundach:"
				+ finalTime+"\nsrednia predkosc kodowania slowa na milisekunde:"+speed);
		

	}

}
