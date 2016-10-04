package markov;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import cs201.keping.markov.BruteMarkov;
import cs201.keping.markov.EfficientMarkov;
import cs201.keping.markov.MarkovInterface;
import cs201.keping.markov.TextSource;

@State(Scope.Thread)
public class MyBenchmark {
	public MarkovConfig config;
	public MarkovInterface<String> model;
	public String src;
	private static final String TEXT_FILE = "data/hawthorne.txt";
	
	/**
	 * Initialize MarkovConfig and the model.
	 * @throws Exception 
	 */
	@Setup(Level.Iteration)
	public void init() throws Exception {
		// TODO
		config = new MarkovConfig("EfficientMarkov", 100000, 16000, 5);
		if (config.modelName.equals("BruteMarkov")) {
			model = new BruteMarkov(config.k);
		} else {
			model = new EfficientMarkov(config.k);
		}
		src = TextSource.textFromFile(new File(TEXT_FILE))
				.substring(0, this.config.N);
		model.setTraining(src);
	}

//	@Benchmark
//    @BenchmarkMode(Mode.AverageTime)
//    @OutputTimeUnit(TimeUnit.MICROSECONDS)
//    public void testTraining(Blackhole bh) {
//		model.setTraining(src);
//		bh.consume(model);
//    }
    
	@Benchmark
	@BenchmarkMode(Mode.AverageTime)
	@OutputTimeUnit(TimeUnit.MICROSECONDS)
	public void testGetRandomText(Blackhole bh) {
		model.getRandomText(config.T);
		bh.consume(model);
	}

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(MyBenchmark.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(5)
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
