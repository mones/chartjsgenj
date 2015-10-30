/*
 * ChartjsGenj - Cheap an dirty Chart.js generator for Java.
 * Copyright (c) 2013-2015, Ricardo Mones <ricardo@mones.org>
 * All rights reserved.
 * Read the accompanying LICENCE file for usage details.
 */
package org.mones.chartjsgenj;

/**
 * Objects which can append themselves as JSON to a {@link StringBuilder}.
 *
 * @author mones
 */
public interface JSONable {
	void addJSON(StringBuilder sb);
}
