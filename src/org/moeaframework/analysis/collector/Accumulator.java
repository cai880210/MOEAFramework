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
package org.moeaframework.analysis.collector;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An accumulator stores collected data from a single run of an algorithm.
 */
public class Accumulator implements Serializable {

	private static final long serialVersionUID = -7483439787468468601L;

	/**
	 * The internal storage of data.
	 */
	private final Map<String, List<Serializable>> data;

	/**
	 * Constructs an empty accumulator.
	 */
	public Accumulator() {
		data = new HashMap<String, List<Serializable>>();
	}

	/**
	 * Adds the data to the sequence of observations with the specified key.
	 * 
	 * @param key the key of this observation
	 * @param value the value of this observation
	 */
	public void add(String key, Serializable value) {
		List<Serializable> entries = data.get(key);
		
		if (entries == null) {
			entries = new ArrayList<Serializable>();
			data.put(key, entries);
		}

		entries.add(value);
	}

	/**
	 * Returns the set of keys stored in this accumulator.
	 * 
	 * @return the set of keys stored in this accumulator
	 */
	public Set<String> keySet() {
		return data.keySet();
	}

	/**
	 * Returns the value at the specified index for the specified key.
	 * 
	 * @param key the key
	 * @param index the index
	 * @return the value at the specified index for the specified key
	 */
	public Serializable get(String key, int index) {
		return data.get(key).get(index);
	}

	/**
	 * Returns the number of values stored for the specified key.
	 * 
	 * @param key the key
	 * @return the number of values stored for the specified key
	 */
	public int size(String key) {
		return data.get(key).size();
	}

}