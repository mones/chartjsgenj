package org.mones.chartjsgenj;

import java.util.List;

/**
 * Very simple custom JSON generator to avoid third-party dependencies.
 *
 * @author mones
 */
public class JSON {
	public static final String NL = "\n";
	public static final String FV_SEP = " : ";
	public static final String SEP = ", ";
	public static final String SEPN = ",\n";
	public static final String ARRAY_BEGIN = "[ ";
	public static final String ARRAY_END = " ]";
	public static final String OBJ_BEGINN = " { \n";
	public static final String OBJ_ENDN = "\n } ";
	public static final String OBJ_BEGIN = "{ ";
	public static final String OBJ_END = " }";
	public static final String QUOTE = "\"";
	public static final String VAR = "var ";

	public static void pair(StringBuilder sb, final String field, final String value) {
		sb.append(field)
			.append(FV_SEP)
			.append(QUOTE).append(value).append(QUOTE);
	}

	public static void pair(StringBuilder sb, final String field, final Integer value) {
		sb.append(field)
			.append(FV_SEP)
			.append(value.intValue());
	}

	public static void pair(StringBuilder sb, final String field, final Float value) {
		sb.append(field)
			.append(FV_SEP)
			.append(value.floatValue());
	}

	public static void pair(StringBuilder sb, final String field, final JSONable value) {
		sb.append(field)
			.append(FV_SEP);
		value.addJSON(sb);
	}

	public static void pair(StringBuilder sb, final String field, final List<?> value) {
		sb.append(field)
			.append(FV_SEP)
			.append(ARRAY_BEGIN);
		if (value != null && value.size() > 0) {
			for (Object n: value) {
				if (n instanceof Integer) {
					sb.append(((Integer) n).intValue());
				} else if (n instanceof Float) {
					sb.append(((Float) n).floatValue());
				} else if (n instanceof JSONable){
					sb.append(NL);
					((JSONable) n).addJSON(sb);
				} else {
					sb.append(QUOTE).append(n.toString()).append(QUOTE);
				}
				sb.append(SEP);
			}
			sb.setLength(sb.length() - SEP.length());
		}
		sb.append(ARRAY_END);
	}
}
