package markov;

public class MarkovConfig {
	private static final String TEXT_FILE = "data/hawthorne.txt";
	String modelName;
	/**
	 * Size of training text.
	 */
	int N;
	/**
	 * Size of generated text.
	 */
	int T;
	/**
	 * order of model.
	 */
	int k;

		
	/**
	 * Construct a {@code MarkovConfig} object.
	 * @param N training text size.
	 * @param T maximum allowed generated text size. Generation stops with an EOS.
	 * @param k order of the Markov model.
	 * @throws Exception wrong model name
	 */
	MarkovConfig(String modelName, int N, int T, int k) throws Exception {
		if ( !(modelName.equals("BruteMarkov") || 
				modelName.equals("EfficientMarkov")) ) {
			throw new Exception("Wrong model name!");
		}
		this.modelName = modelName;
		this.N = N;
		this.T = T;
		this.k = k;
		
	}

	
}
