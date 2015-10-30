/*
 * ChartjsGenj - Cheap an dirty Chart.js generator for Java.
 * Copyright (c) 2013-2015, Ricardo Mones <ricardo@mones.org>
 * All rights reserved.
 * Read the accompanying LICENCE file for usage details.
 */
package org.mones.chartjsgenj;

/**
 * Raw JavaScript code.
 *
 * @author mones
 */
public final class JavaScript implements JSONable {

	private String code;

	public JavaScript(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public void addJSON(StringBuilder sb) {
		if (code != null) {
			sb.append(code);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof JavaScript) {
			JavaScript js = (JavaScript) obj;
			if (this.code == null) {
				return (js.code == null);
			}
			return this.code.equals(js.code);
		}
		return false;
	}
}
