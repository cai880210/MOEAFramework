/* Copyright 2009-2011 David Hadka
 * 
 * This file is part of the MOEA Framework.
 * 
 * The MOEA Framework is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or (at your 
 * option) any later version.
 * 
 * The MOEA Framework is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public 
 * License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License 
 * along with the MOEA Framework.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.moeaframework.core.indicator;

import org.moeaframework.core.Indicator;
import org.moeaframework.core.NondominatedPopulation;
import org.moeaframework.core.Problem;
import org.moeaframework.util.ArrayMath;

/**
 * Spacing metric. Represents the spread of the Pareto front.
 */
public class Spacing implements Indicator {

	/**
	 * The problem.
	 */
	private final Problem problem;

	/**
	 * Constructs a spacing metric evaluator for the specified problem.
	 * 
	 * @param problem the problem
	 */
	public Spacing(Problem problem) {
		this.problem = problem;
	}

	@Override
	public double evaluate(NondominatedPopulation approximationSet) {
		return evaluate(problem, approximationSet);
	}

	/**
	 * Computes the spread metric for the specified problem given an
	 * approximation set.
	 * 
	 * @param problem the problem
	 * @param approximationSet an approximation set for the problem
	 * @return the spread metric for the specified problem given an
	 *         approximation set
	 */
	static double evaluate(Problem problem,
			NondominatedPopulation approximationSet) {
		if (approximationSet.size() < 2) {
			return 0.0;
		}
		
		double[] d = new double[approximationSet.size()];

		for (int i = 0; i < approximationSet.size(); i++) {
			double min = Double.POSITIVE_INFINITY;
			
			for (int j = 0; j < approximationSet.size(); j++) {
				if (i != j) {
					min = Math.min(min, IndicatorUtils.manhattanDistance(
							problem, approximationSet.get(i), approximationSet
									.get(j)));
				}
			}

			d[i] = min;
		}

		double dbar = ArrayMath.sum(d) / approximationSet.size();
		double sum = 0.0;
		
		for (int i = 0; i < approximationSet.size(); i++) {
			sum += Math.pow(d[i] - dbar, 2.0);
		}

		return Math.sqrt(sum / (approximationSet.size() - 1));
	}
}