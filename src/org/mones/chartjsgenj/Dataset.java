package org.mones.chartjsgenj;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic chart dataset representation.
 *
 * @author mones
 */
public class Dataset implements JSONable {
	// Line
	private Colour fillColor;
	private Colour strokeColor;
	private Colour pointColor;
	private Colour pointStrokeColor;
	private Colour pointHighlightFill;
	private Colour pointHighlightStroke;
	// Bar
	private Colour highlightFill;
	private Colour highlightStroke;
	// Line, Bar
	private List<Number> data;
	private String label;
	// Others
	private String title; // FIXME

	public Dataset() { }

	public Dataset(String label) {
		this.label = label;
	}

	public Dataset(String label, int[] data) {
		this.label = label;
		this.setData(data);
	}

	public Dataset(String label, float[] data) {
		this.label = label;
		this.setData(data);
	}

	public Colour getFillColor() {
		return fillColor;
	}

	public void setFillColor(Colour fillColor) {
		this.fillColor = fillColor;
	}

	public Colour getStrokeColor() {
		return strokeColor;
	}

	public void setStrokeColor(Colour strokeColor) {
		this.strokeColor = strokeColor;
	}

	public Colour getPointColor() {
		return pointColor;
	}

	public void setPointColor(Colour pointColor) {
		this.pointColor = pointColor;
	}

	public Colour getPointStrokeColor() {
		return pointStrokeColor;
	}

	public void setPointStrokeColor(Colour pointStrokeColor) {
		this.pointStrokeColor = pointStrokeColor;
	}

	public Colour getPointHighlightFill() {
		return pointHighlightFill;
	}

	public void setPointHighlightFill(Colour pointHighlightFill) {
		this.pointHighlightFill = pointHighlightFill;
	}

	public Colour getPointHighlightStroke() {
		return pointHighlightStroke;
	}

	public void setPointHighlightStroke(Colour pointHighlightStroke) {
		this.pointHighlightStroke = pointHighlightStroke;
	}

	public Colour getHighlightFill() {
		return highlightFill;
	}

	public void setHighlightFill(Colour highlightFill) {
		this.highlightFill = highlightFill;
	}

	public Colour getHighlightStroke() {
		return highlightStroke;
	}

	public void setHighlightStroke(Colour highlightStroke) {
		this.highlightStroke = highlightStroke;
	}

	public List<Number> getData() {
		return data;
	}

	public void setData(List<Number> data) {
		this.data = data;
	}

	public void setData(int[] data) {
		this.data = new ArrayList<Number>(data.length);
		for (int n: data) {
			this.data.add(n);
		}
	}

	public void setData(float[] data) {
		this.data = new ArrayList<Number>(data.length);
		for (float n: data) {
			this.data.add(n);
		}
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@SuppressWarnings("unchecked")
	private boolean addIfSet(Object f, String fn, StringBuilder sb, boolean i) {
		if (f != null) {
			if (i) {
				sb.append(JSON.SEPN);
			}
			if (f instanceof String) {
				JSON.pair(sb, fn, (String) f);
			} else if (f instanceof Integer) {
				JSON.pair(sb, fn, (Integer) f);
			} else if (f instanceof Float) {
				JSON.pair(sb, fn, (Float) f);
			} else if (f instanceof JSONable) {
				JSON.pair(sb, fn, (JSONable) f);
			} else if (f instanceof List<?>) {
				JSON.pair(sb, fn, (List<Number>) f);
			}
			return true;
		}
		return i;
	}

	@Override
	public void addJSON(StringBuilder sb) {
		boolean init = false;
		sb.append(JSON.OBJ_BEGINN);
		init = addIfSet(fillColor, "fillColor", sb, init);
		init = addIfSet(pointColor, "pointColor", sb, init);
		init = addIfSet(pointStrokeColor, "pointStrokeColor", sb, init);
		init = addIfSet(strokeColor, "strokeColor", sb, init);
		init = addIfSet(pointHighlightFill, "pointHighlightFill", sb, init);
		init = addIfSet(pointHighlightStroke, "pointHighlightStroke", sb, init);
		init = addIfSet(highlightFill, "highlightFill", sb, init);
		init = addIfSet(highlightStroke, "highlightStroke", sb, init);
		init = addIfSet(label, "label", sb, init);
		init = addIfSet(title, "title", sb, init);
		init = addIfSet(data, "data", sb, init);
		sb.append(JSON.OBJ_ENDN);
	}
}
