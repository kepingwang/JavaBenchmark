package markov;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import cs201.keping.markov.TextSource;
import cs201.keping.markov.WordGram;

/**
 * Benchmarking the time of inserting WordGram
 * into HashMap and TreeMap.
 * @author keping
 *
 */
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class MapBench {
	private static final String TEXT_FILE = "data/kjv10.txt";
	public WordGram[] grams; 
	public Map<WordGram, Integer> map;
	public int ptr;
	
	
	/**
	 * Type of the map: HashMap or TreeMap.
	 */
	@Param({"IdentityHashMap","HashMap", "TreeMap"})
	public String mapType;
	
	
	/**
	 * Order of the WordGram.
	 */
	@Param({"10"})
	public int k;

	/**
	 * Initialize MarkovConfig and the model.
	 * @throws Exception 
	 */
	@Setup(Level.Iteration)
	public void init() throws Exception {
		if (mapType.equals("IdentityHashMap")) {
			map = new IdentityHashMap<>();
		} else if (mapType.equals("HashMap")) {
			map = new HashMap<>();
		} else if (mapType.equals("TreeMap")) {
			map = new TreeMap<>();
		} else { }
		String[] text = TextSource.textFromFile(new File(TEXT_FILE)).split("\\s+");
		HashSet<WordGram> set = new HashSet<>();
		for (int i = 0; i < text.length-k; i++) {
			WordGram gram = new WordGram(text, i, k);
			if (!set.contains(gram)) set.add(gram);
		}
		grams = set.toArray(new WordGram[0]);
		ptr = 0;
	}
	
	@Benchmark
	@BenchmarkMode(Mode.SingleShotTime)
	@Warmup(batchSize = 5000)
	@Measurement(batchSize = 5000)
	public void mapInsert_5000() {
		map.put(grams[ptr++], 0);
	}
    
	@Benchmark
	@BenchmarkMode(Mode.SingleShotTime)
	@Warmup(batchSize = 10000)
	@Measurement(batchSize = 10000)
	public void mapInsert_10000() {
		map.put(grams[ptr++], 0);
	}

	@Benchmark
	@BenchmarkMode(Mode.SingleShotTime)
	@Warmup(batchSize = 20000)
	@Measurement(batchSize = 20000)
	public void mapInsert_20000() {
		map.put(grams[ptr++], 0);
	}
	
	@Benchmark
	@BenchmarkMode(Mode.SingleShotTime)
	@Warmup(batchSize = 40000)
	@Measurement(batchSize = 40000)
	public void mapInsert_40000() {
		map.put(grams[ptr++], 0);
	}
	@Benchmark
	@BenchmarkMode(Mode.SingleShotTime)
	@Warmup(batchSize = 60000)
	@Measurement(batchSize = 60000)
	public void mapInsert_60000() {
		map.put(grams[ptr++], 0);
	}
	
	@Benchmark
	@BenchmarkMode(Mode.SingleShotTime)
	@Warmup(batchSize = 80000)
	@Measurement(batchSize = 80000)
	public void mapInsert_80000() {
		map.put(grams[ptr++], 0);
	}

	@Benchmark
	@BenchmarkMode(Mode.SingleShotTime)
	@Warmup(batchSize = 100000)
	@Measurement(batchSize = 100000)
	public void mapInsert_100000() {
		map.put(grams[ptr++], 0);
	}
	
	@Benchmark
	@BenchmarkMode(Mode.SingleShotTime)
	@Warmup(batchSize = 120000)
	@Measurement(batchSize = 120000)
	public void mapInsert_120000() {
		map.put(grams[ptr++], 0);
	}
	
	@Benchmark
	@BenchmarkMode(Mode.SingleShotTime)
	@Warmup(batchSize = 160000)
	@Measurement(batchSize = 160000)
	public void mapInsert_160000() {
		map.put(grams[ptr++], 0);
	}
	
	@Benchmark
	@BenchmarkMode(Mode.SingleShotTime)
	@Warmup(batchSize = 200000)
	@Measurement(batchSize = 200000)
	public void mapInsert_200000() {
		map.put(grams[ptr++], 0);
	}
	
	@Benchmark
	@BenchmarkMode(Mode.SingleShotTime)
	@Warmup(batchSize = 240000)
	@Measurement(batchSize = 240000)
	public void mapInsert_240000() {
		map.put(grams[ptr++], 0);
	}


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
        		.result("result/map_bench.txt")
        		.resultFormat(ResultFormatType.CSV)
                .include(MapBench.class.getSimpleName())
                .warmupIterations(10)
                .measurementIterations(10)
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
