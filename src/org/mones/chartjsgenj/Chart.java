package org.mones.chartjsgenj;

import java.util.ArrayList;
import java.util.List;

/**
 * Container for chart data and generator.
 *
 * @author mones
 */
public class Chart {
	private List<String> labels;
	private List<Dataset> datasets;
	private ChartType chartType;
	private boolean legend;

	public Chart(ChartType type) {
		setType(type);
		this.legend = false;
	}

	public Chart(ChartType type, boolean legend) {
		setType(type);
		this.legend = legend;
	}

	public void setType(ChartType type) {
		this.chartType = type;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public List<Dataset> getDatasets() {
		return datasets;
	}

	public void setDatasets(List<Dataset> datasets) {
		this.datasets = datasets;
	}

	public ChartType getChartType() {
		return chartType;
	}

	public void setChartType(ChartType chartType) {
		this.chartType = chartType;
	}

	public boolean hasLegend() {
		return legend;
	}

	public void setLegend(boolean legend) {
		this.legend = legend;
	}

	public void addDataset(Dataset dataset) {
		if (this.datasets == null) {
			this.datasets = new ArrayList<Dataset>();
		}
		this.datasets.add(dataset);
	}

	/**
	 * Generate script markup for page header.
	 *
	 * @param path Path to script.
	 * @param minimized Use the minimized version.
	 * @return The HTML string.
	 */
	public static String htmlScript(String path, boolean minimized) {
		StringBuilder sb = new StringBuilder("<script src=\"");
		if (path != null && !path.equals("")) {
			sb.append(path.trim());
			if (!path.endsWith("/")) {
				sb.append('/');
			}
		}
		if (minimized)  {
			sb.append("Chart.min.js");
		} else {
			sb.append("Chart.js");
		}
		sb.append("\"></script>\n");
		return sb.toString();
	}

	/**
	 * Generate legend CSS markup for page header.
	 *
	 * @param css Main styles for all chart legends.
	 * @param cssLi Styles for list items.
	 * @param cssSpan Styles for coloured spans.
	 * @return The HTML string.
	 */
	public static String htmlLegendStyle(String css, String cssLi, String cssSpan) {
		StringBuilder sb = new StringBuilder("<style type=\"text/css\">\n");
		final String sep = ", ";
		final String ocls = " {\n";
		final String ccls = "\n}\n";
		// ul containers
		for (ChartType ct: ChartType.values()) {
			sb.append('.').append(ct.chartType().toLowerCase())
				.append("-legend").append(sep);
		}
		sb.setLength(sb.length() - sep.length());
		sb.append(ocls).append(' ').append(css).append(ccls);
		// list items
		for (ChartType ct: ChartType.values()) {
			sb.append('.').append(ct.chartType().toLowerCase())
				.append("-legend li").append(sep);
		}
		sb.setLength(sb.length() - sep.length());
		sb.append(ocls).append(' ').append(cssLi).append(ccls);
		// color spot spans
		for (ChartType ct: ChartType.values()) {
			sb.append('.').append(ct.chartType().toLowerCase())
				.append("-legend li span").append(sep);
		}
		sb.setLength(sb.length() - sep.length());
		sb.append(ocls).append(' ').append(cssSpan).append(ccls);
		sb.append("</style>\n");
		return sb.toString();
	}

	/**
	 * Generate the chart's variable.
	 *
	 * @param varName Name of the variable.
	 * @return The JavaScript string.
	 */
	public String jsDataVar(final String varName) {
		StringBuilder sb = new StringBuilder(JSON.VAR);
		sb.append(varName).append(" = ");
		sb.append(JSON.OBJ_BEGINN);
		JSON.pair(sb, "labels", labels);
		sb.append(JSON.SEPN);
		JSON.pair(sb, "datasets", datasets);
		sb.append(JSON.OBJ_ENDN).append(";\n");
		return sb.toString();
	}

	private static final Object CNTX = "Context";
	private static final Object CHRT = "Chart";
	private static final Object OPTS = "Options";
	private static final Object DATA = "Data";
	private static final Object LEGN = "Legend";

	/**
	 * Generate the chart's context variable.
	 *
	 * @param canvasId Identifier of associated HTML canvas.
	 * @return The JavaScript string.
	 */
	public String jsContextVar(final String canvasId) {
		StringBuilder sb = new StringBuilder(JSON.VAR);
		sb.append(canvasId).append(CNTX)
			.append(" = document.getElementById(\"")
			.append(canvasId).append("\").getContext(\"2d\");")
			.append('\n');
		return sb.toString();
	}

	/**
	 * Generate the chart's variable.
	 *
	 * @param canvasId Identifier of associated HTML canvas.
	 * @param varName Name of the variable.
	 * @param varOptions Name of the chart options variable.
	 * @return The JavaScript string.
	 */
	public String jsChart(final String canvasId, final String varName, final String varOptions) {
		StringBuilder sb = new StringBuilder();
		sb.append(JSON.VAR).append(canvasId).append(CHRT)
			.append(" = new Chart(").append(canvasId).append(CNTX).append(")")
			.append('.').append(chartType.chartType())
			.append('(').append(varName);
		if (chartType == ChartType.POLAR_AREA
				|| chartType == ChartType.PIE
				|| chartType == ChartType.DOUGHNUT) {
			sb.append(".datasets");
		}
		sb.append(", ")
			.append(varOptions).append(");")
			.append('\n');
		return sb.toString();
	}

	/**
	 * Generate the chart's legend.
	 *
	 * @param canvasId Identifier of associated HTML canvas.
	 * @return The JavaScript string.
	 */
	public String jsLegend(String canvasId) {
		StringBuilder sb = new StringBuilder();
		if (!this.legend) {
			sb.append("// ");
		}
		sb.append("document.getElementById(\"").append(canvasId).append(LEGN)
			.append("\").innerHTML = ").append(canvasId).append(CHRT)
			.append(".generateLegend();")
			.append('\n');
		return sb.toString();
	}

	/**
	 * Generates the legend markup.
	 *
	 * @param canvasId Identifier of associated HTML canvas.
	 * @return The HTML string.
	 */
	public String htmlLegend(String canvasId) {
		return HTML.div(canvasId + LEGN);
	}

	/**
	 * Generates the drawing canvas markup.
	 *
	 * @param canvasId Identifier of the HTML canvas.
	 * @param width Width in pixels.
	 * @param height Height in pixels.
	 * @return The HTML string.
	 */
	public String htmlCanvas(String canvasId, int width, int height) {
		return HTML.canvas(canvasId, width, height);
	}

	/**
	 * Generates the complete HTML (and JavaScript) for drawing the chart
	 * on the given canvas id.
	 *
	 * @param id Base identifier.
	 * @param o Options for this chart.
	 * @return The HTML for the chart.
	 */
	public String htmlScript(String id, Options o) {
		StringBuilder sb = new StringBuilder();
		if (o != null) {
			sb.append(o.jsOptionsVar(id + OPTS)).append('\n');
		} else {
			sb.append(JSON.VAR).append(id + OPTS).append(" = null;\n");
		}
		sb.append(this.jsDataVar(id + DATA));
		sb.append(this.jsContextVar(id));
		sb.append(this.jsChart(id, id + DATA, id + OPTS));
		if (this.legend) {
			sb.append(this.jsLegend(id));
		}
		return HTML.script(sb.toString());
	}
}
