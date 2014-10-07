package alphabet;

/**
 * Klasa reprezentuje węzły w drzewie. Każdy węzeł posiada listę swoich potomków, polę przechowującę statystyke występowania znaku w 
 * i konkrenty symbol. 
 */
import java.util.ArrayList;
import java.util.List;

public class Symbols {

	/**
	 * przechowuje dany symbol
	 */
	private String symbol;
	/**
	 * prawdopodobienstwo
	 */
	private double probability;
	/**
	 * rodzic
	 */
	private Symbols parent;
	/**
	 * lista dzieci
	 */
	public List<Symbols> children;
	
	private int code;

	/**
	 * Konstruktor przyjmuje dany symbol i prawdopodobienstwo
	 * @param symbol
	 * @param probability
	 */
	public Symbols(String symbol, double probability) {
		this.children = null;
		this.probability = probability;
		this.symbol = symbol;
	}
	/**
	 * konstruktor domyslny
	 */
	public Symbols() {
	}
	
	public void setProbability(double probability) {
		this.probability = probability;
	}

	public String getSymbol() {
		return symbol;
	}

	public double getProbability() {
		return probability;
	}
	/**
	 * Konstruktor potrzebny do reprezentacji w drzewie
	 * @param root
	 * @param children
	 */
	public Symbols(Symbols root, List<Symbols> children) {
		this.symbol = root.getSymbol();
		this.probability = root.getProbability();
		this.parent = null;
		this.children = new ArrayList<Symbols>();
		this.children = children;
	}
	public void setCode(int code){
		this.code = code;
	}
	public int getCode(){
		return this.code;
	}

}
