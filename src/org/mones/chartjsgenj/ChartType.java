package org.mones.chartjsgenj;

/**
 * Types of charts supported.
 *
 * @author mones
 */
public enum ChartType {
	LINE("Line"),
	BAR("Bar"),
	RADAR("Radar");

	private final String type;

	ChartType(String type) {
		this.type = type;
	}

	String chartType() {
		return this.type;
	}
}
