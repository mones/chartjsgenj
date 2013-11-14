package org.mones.chartjsgenj;

/**
 * Objects which can append themselves as JSON to a {@link StringBuilder}.
 *
 * @author mones
 */
public interface JSONable {
	void addJSON(StringBuilder sb);
}
