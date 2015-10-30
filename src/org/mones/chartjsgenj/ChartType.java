/*
 * ChartjsGenj - Cheap an dirty Chart.js generator for Java.
 * Copyright (c) 2013-2015, Ricardo Mones <ricardo@mones.org>
 * All rights reserved.
 * Read the accompanying LICENCE file for usage details.
 */
package org.mones.chartjsgenj;

/**
 * Types of charts supported.
 *
 * @author mones
 */
public enum ChartType {
	LINE("Line"),
	BAR("Bar"),
	RADAR("Radar"),
	POLAR_AREA("PolarArea"),
	PIE("Pie"),
	DOUGHNUT("Doughnut");

	private final String type;

	ChartType(String type) {
		this.type = type;
	}

	String chartType() {
		return this.type;
	}
}
