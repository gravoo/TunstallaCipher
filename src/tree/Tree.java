package tree;

/**
 * Klasa reprezentuje drzewo przechowujace kody 
 */
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import alphabet.AlphabetCreator;
import alphabet.Symbols;

public class Tree {
	public Symbols root;
	private static int leafs;
	private static double maxLeafs;
	private static int code;
	private static int cipher;

	public Tree(Symbols rootData, List<Symbols> alphabet, double cipher) {
		root = new Symbols(rootData, alphabet);
		leafs = alphabet.size();
		maxLeafs = Math.pow((double) 2, (double) cipher) - 1;
		this.cipher=(int)cipher;
		code = 0;

	}

	/**
	 * Wypisuje elementy drzewa
	 * 
	 * @param root
	 */
	public void print(Symbols root) {

		for (int i = 0; i < root.children.size(); i++) {

			if (root.children.get(i).children != null) {

				print(root.children.get(i));
			}
			if (root.children.get(i).getProbability() != 0) {
				System.out.println("symbol: "
						+ root.children.get(i).getSymbol()
						+ " prawdopodobienstwo "
						+ root.children.get(i).getProbability()
						+ " tyle lisci mozna miec" + maxLeafs
						+ " tyle lisci mamy " + leafs + "kody: "
						+ root.children.get(i).getCode());
			}
		}
	}

	/**
	 * Ustawiamy kody dla lisci
	 * 
	 * @param root
	 */
	public void setCode(Symbols root) {

		for (int i = 0; i < root.children.size(); i++) {

			if (root.children.get(i).children != null) {

				setCode(root.children.get(i));
			}
			if (root.children.get(i).getProbability() != 0) {
				root.children.get(i).setCode(code);
				code++;
			}
		}
	}

	/**
	 * Funkcja przeszukuje drzewo w celu znalezienia odpowiedniego kodu dla
	 * danego ciagu znaków
	 * 
	 * @param root
	 * @param msg
	 * @return
	 */
	public int codeMsg(Symbols root, String msg) {

		int tmpCod = -1;
		for (int i = 0; i < root.children.size(); i++) {

			if ((root.children.get(i).getSymbol().equals(msg))
					&& (root.children.get(i).children == null)) {
				return root.children.get(i).getCode();
			} else if (root.children.get(i).children != null && tmpCod == -1) {
				tmpCod = codeMsg(root.children.get(i), msg);
			}

		}
		return tmpCod;
	}

	/**
	 * Najwazniejsza metoda, od 0 do maxLeafs znajudemy maksymalne
	 * prawdopodobienstwo i dodajemy do danego liscia wsztykie element w
	 * alfabecie
	 * 
	 * @param alphabet
	 */
	public void codeLeafs(List<Symbols> alphabet) {
		while (leafs < maxLeafs) {
			Symbols max = findMaxProbability(root);
			addLeafs(max, alphabet);

		}
		setCode(root);

	}

	/**
	 * metoda znajduje najwieksze prawdopodobienstwo
	 * 
	 * @param root
	 * @return
	 */

	private Symbols findMaxProbability(Symbols root) {
		Symbols max = root;
		Symbols tmp;
		double maxProb = 0;
		int size;
		try {
			size = root.children.size();
		} catch (NullPointerException e) {
			return root;
		}

		for (int i = 0; i < size; i++) {

			if (root.children.get(i) != null) {

				tmp = findMaxProbability(root.children.get(i));
				if (maxProb < tmp.getProbability()) {
					maxProb = tmp.getProbability();
					max = tmp;
				}
			}
			if (root.children.get(i).getProbability() > maxProb) {
				maxProb = root.children.get(i).getProbability();
				max = root.children.get(i);
			}
		}

		return max;
	}

	/**
	 * metoda dodaje liscie do drzewa
	 * 
	 * @param max
	 * @param alphabet
	 */

	private void addLeafs(Symbols max, List<Symbols> alphabet) {

		max.children = new ArrayList<Symbols>();
		String line = max.getSymbol();
		double prob = max.getProbability();
		int i = 0;
		for (i = 0; i < alphabet.size(); i++) {
			max.children.add(new Symbols(line + alphabet.get(i).getSymbol(),
					prob * alphabet.get(i).getProbability()));

		}
		leafs += i - 1;
		max.setProbability(0);

	}

	public String codeText(String line) {

		
		int code = 0;
		String validate="",finalCode="";
		int j = 1,i=0;
		try{
		while(!line.substring(i, j).isEmpty()) {
			
			
			code = codeMsg(root, line.substring(i, j));
			if (code != -1) {
				finalCode+=code;
				toBinary(code);
				i=j;
				j++;
			}
			else{
				j++;
			}
		}}
		catch(StringIndexOutOfBoundsException ex){
			
			validate=line.substring(i,line.length());
			if(validate!=""){
				validate="";
				validate=""+maxLeafs;
				finalCode+=validate.substring(0, validate.length()-2);
				toBinary((int) maxLeafs);
			}
		}
		System.out.println("\n"+"kod dziesiętnie: "+finalCode);
		
		return finalCode;
	}
	
	private void toBinary(int code2){
		
		String bin = Integer.toBinaryString(code2); 
		
		for(;bin.length()<cipher;){
			bin="0"+bin;
			
		}
		
		System.out.print(bin+" ");
		
	} 

}