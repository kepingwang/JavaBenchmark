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
import org.openjdk.jmh.infra.Blackhole;
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
public class MapNumBench {
	public WordGram[] grams; 
	public Map<Integer, Integer> map;
	public int num;
	
	
	/**
	 * Type of the map: HashMap or TreeMap.
	 */
	@Param({"IdentityHashMap","HashMap", "TreeMap"})
	public String mapType;
	
	
	@Setup(Level.Iteration)
	public void init() throws Exception {
		if (mapType.equals("IdentityHashMap")) {
			map = new IdentityHashMap<>();
		} else if (mapType.equals("HashMap")) {
			map = new HashMap<>();
		} else {
			map = new TreeMap<>();
		}
	
		num = 0;
	}
	
	@Benchmark
	@BenchmarkMode(Mode.SingleShotTime)
	@Warmup(batchSize = 20000)
	@Measurement(batchSize = 20000)
	public void mapInsert_20000() {
		map.put(num++, 0);
	}
	
	@Benchmark
	@BenchmarkMode(Mode.SingleShotTime)
	@Warmup(batchSize = 40000)
	@Measurement(batchSize = 40000)
	public void mapInsert_40000() {
		map.put(num++, 0);
	}
	
	@Benchmark
	@BenchmarkMode(Mode.SingleShotTime)
	@Warmup(batchSize = 80000)
	@Measurement(batchSize = 80000)
	public void mapInsert_80000() {
		map.put(num++, 0);
	}

	@Benchmark
	@BenchmarkMode(Mode.SingleShotTime)
	@Warmup(batchSize = 160000)
	@Measurement(batchSize = 160000)
	public void mapInsert_160000() {
		map.put(num++, 0);
	}
	
	@Benchmark
	@BenchmarkMode(Mode.SingleShotTime)
	@Warmup(batchSize = 320000)
	@Measurement(batchSize = 320000)
	public void mapInsert_320000() {
		map.put(num++, 0);
	}
	
	@Benchmark
	@BenchmarkMode(Mode.SingleShotTime)
	@Warmup(batchSize = 640000)
	@Measurement(batchSize = 640000)
	public void mapInsert_640000() {
		map.put(num++, 0);
	}
	
	@Benchmark
	@BenchmarkMode(Mode.SingleShotTime)
	@Warmup(batchSize = 1280000)
	@Measurement(batchSize = 1280000)
	public void mapInsert_1280000() {
		map.put(num++, 0);
	}


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
        		.result("result/map_bench.txt")
        		.resultFormat(ResultFormatType.CSV)
                .include(MapNumBench.class.getSimpleName())
                .warmupIterations(10)
                .measurementIterations(10)
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
