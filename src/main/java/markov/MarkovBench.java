package markov;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import cs201.keping.markov.BruteMarkov;
import cs201.keping.markov.EfficientMarkov;
import cs201.keping.markov.MarkovInterface;
import cs201.keping.markov.TextSource;


/**
 * Benchmarking the training and text-generating time
 * of the Markov Model.
 * @author keping
 *
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class MarkovBench {
	private static final String TEXT_FILE = "data/hawthorne.txt";
	public MarkovInterface<String> model;
	public String src;
	
	
	/**
	 * Name of the model: BruteMarkov or EfficientMarkov
	 */
	@Param({"BruteMarkov", "EfficientMarkov"})
	public String modelName;
	
	/**
	 * Order of the model.
	 */
	@Param({"6"})
	public int k;
	
	/**
	 * Size of training text.
	 */
	@Param({"8000"})
	public int N;
	
	/**
	 * Size of generated text.
	 */
	@Param({"500","1000","2000","4000","8000"})
	public int T;

	/**
	 * Initialize MarkovConfig and the model.
	 * @throws Exception 
	 */
	@Setup(Level.Iteration)
	public void init() throws Exception {
		if (modelName.equals("BruteMarkov")) {
			model = new BruteMarkov(k);
		} else {
			model = new EfficientMarkov(k);
		}
		
		src = TextSource.textFromFile(new File(TEXT_FILE))
				.substring(0, N);
		model.setTraining(src);
	}
	
	@Benchmark
    public void testTraining() {
		model.setTraining(src);
    }
    
	@Benchmark
	public void testGetRandomText() {
		int t = T;
		String text = model.getRandomText(t);
		while (text.length() < t) {
			t = t - text.length();
			text = model.getRandomText(t);
		}
	}

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
        		.result("result/markov_bench.txt")
        		.resultFormat(ResultFormatType.CSV)
                .include(MarkovBench.class.getSimpleName())
                .warmupIterations(10)
                .measurementIterations(10)
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
