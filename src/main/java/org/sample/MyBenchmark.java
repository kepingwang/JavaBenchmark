/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.sample;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class MyBenchmark {
	
	/**
	 * We are using the same source textFile, just cutting different length.
	 */
	private static final String TEXT_FILE = "data/hawthorne.txt";

	/**
	 * While testing markov, we are interested in the running time
	 * of {@code setTraining()} and {@code getRandomText} with respect to:<br>
	 * <b>N</b>: training text size.<br>
	 * <b>T</b>: size of text generated using Markov model.<br>
	 * <b>k</b>: order of the Markov model.
	 * <p>Note that size refers to the number of characters. Time is stored
	 * in milliseconds.</p>
	 * @author keping
	 */
	private static class MarkovConfig {
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

    
    @Benchmark
    public void testMethod() {
        // This is a demo/sample template for building your JMH benchmarks. Edit as needed.
        // Put your benchmark code here.
    }
    

}
