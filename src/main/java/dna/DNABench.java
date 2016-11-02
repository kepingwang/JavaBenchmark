package dna;

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

/**
 * Benchmark for DNA: StringStrand, StringBuilderStrand, and LinkedStrand.
 * @author keping
 *
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class DNABench {
	private IDnaStrand model;
	private String source;
	/**
	 * Where to break and replace by splicee.
	 */
	private final String enzyme = "B";
	private String splicee;
	
	
	/**
	 * Name of the DnaStrand model.
	 */
	@Param({"StringStrand", "StringBuilderStrand", "LinkStrand"})
	public String modelName;
	
	/**
	 * Number of breaks (additions).
	 */
	@Param({"10","20"})
	public int b; 
	
	/**
	 * Length of the splicee.
	 */
	@Param({"100","200"})
	public int S;
	
	/**
	 * Total length of the strand. 
	 */
	@Param({"100000","200000"})
	public int N;
	
	
	/**
	 * Create source string and initialize strand.
	 * @throws Exception 
	 */
	@Setup(Level.Invocation)
	public void init() throws Exception {
		
		StringBuilder sbSource = new StringBuilder();
		for (int i = 0; i < b; i++) {
			sbSource.append(enzyme);
		}
		for (int i = 0; i < N-b*enzyme.length(); i++) {
			sbSource.append('x'); // x for non-break letter
		}
		source = sbSource.toString();
		
		StringBuilder sbSplicee = new StringBuilder();
		for (int i = 0; i < S; i++) {
			sbSplicee.append('S'); // SSSS... for splicee
		}
		splicee = sbSplicee.toString();
		
		if (model.equals("StringStrand")) {
			model = new StringStrand(source);
		} else if (model.equals("StringBuilderStrand")) {
			model = new StringBuilderStrand(source);
		} else if (model.equals("LinkStrand")) {
			model = new LinkStrand(source);
		} else {
			throw new Exception("Wrong model name");
		}
		
	}
	
	@Benchmark
    public void cutAndSplice() {
		model.cutAndSplice(enzyme, splicee);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
        		.result("result/dna/dna_bench.txt")
        		.resultFormat(ResultFormatType.CSV)
                .include(DNABench.class.getSimpleName())
                .warmupIterations(10)
                .measurementIterations(10)
                .forks(1)
                .build();

        new Runner(opt).run();
    }
	
}
