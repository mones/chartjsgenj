package org.mones.chartjsgenj;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * A RGB colour.
 *
 * @author mones
 */
public class Colour implements JSONable, Cloneable {
	private static final float MAX_ALPHA = 1.0f;
	private static final float MIN_ALPHA = 0.0f;
	private int r;
	private int g;
	private int b;
	private float a;

	private static Map<String, int[]> named;

	private static int[][] HTML = new int[][] {
		new int[] {   0,   0,   0, }, /*  0 - black */
		new int[] {   0,   0, 128, }, /*  1 - navy */
		new int[] {   0,   0, 255, }, /*  2 - blue */
		new int[] {   0, 128,   0, }, /*  3 - green */
		new int[] {   0, 128, 128, }, /*  4 - teal */
		new int[] {   0, 255,   0, }, /*  5 - lime */
		new int[] {   0, 255, 255, }, /*  6 - aqua/cyan */
		new int[] { 128,   0,   0, }, /*  7 - maroon */
		new int[] { 128,   0, 128, }, /*  8 - purple */
		new int[] { 128, 128,   0, }, /*  9 - olive */
		new int[] { 128, 128, 128, }, /* 10 - gray/grey */
		new int[] { 255,   0,   0, }, /* 11 - red */
		new int[] { 255,   0, 255, }, /* 12 - magenta/fuchsia */
		new int[] { 255, 255,   0, }, /* 13 - yellow */
		new int[] { 255, 128,   0, }, /* 14 - orange (!= W3C) */
		new int[] { 255, 255, 255, }, /* 15 - white */
	};

	static {
		named = new HashMap<String, int[]>();
		named.put("black",	HTML[0]);
		named.put("navy",	HTML[1]);
		named.put("blue",	HTML[2]);
		named.put("green"	HTML[3]);
		named.put("teal",	HTML[4]);
		named.put("lime",	HTML[5]);
		named.put("aqua",	HTML[6]);
		named.put("cyan",	HTML[6]);
		named.put("maroon",	HTML[7]);
		named.put("purple",	HTML[8]);
		named.put("olive",	HTML[9]);
		named.put("gray",	HTML[10]);
		named.put("grey",	HTML[10]);
		named.put("red",	HTML[11]);
		named.put("magenta",	HTML[12]);
		named.put("fuchsia",	HTML[12]);
		named.put("yellow",	HTML[13]);
		named.put("orange",	HTML[14]);
		named.put("white",	HTML[15]);
	}

	public Colour() {
		this.r = this.g = this.b = 0;
		this.a = MAX_ALPHA;
	}

	public Colour(String color) {
		this.r = this.g = this.b = 0;
		this.a = MAX_ALPHA;
		if (color == null) {
			return;
		}
		if (color.startsWith("#")) {
			if (color.length() == 7) { /* #aabbcc */
				this.r = Integer.parseInt(color.substring(1, 3), 16);
				this.g = Integer.parseInt(color.substring(3, 5), 16);
				this.b = Integer.parseInt(color.substring(5, 7), 16);
			} else if (color.length() == 4) { /* #abc */
				this.r = Integer.parseInt(color.substring(1, 2), 16);
				this.g = Integer.parseInt(color.substring(2, 3), 16);
				this.b = Integer.parseInt(color.substring(3, 4), 16);
				/* http://www.w3.org/TR/CSS2/syndata.html#value-def-color */
				this.r |= (this.r << 4);
				this.g |= (this.g << 4);
				this.b |= (this.b << 4);
			}
		} else {
			int[] rgb = named.get(color.trim().toLowerCase());
			if (rgb != null) {
				this.r = rgb[0];
				this.g = rgb[1];
				this.b = rgb[2];
			}
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			if (obj instanceof Colour) {
				return ((Colour) obj).a == this.a
						&& ((Colour) obj).r == this.r
						&& ((Colour) obj).g == this.g
						&& ((Colour) obj).b == this.b;
			} else if (obj instanceof String) {
				return this.equals(new Colour((String) obj));
			}
		}
		return false;
	}

	public Colour(float a) {
		this.r = this.g = this.b = 0;
		this.setA(a);
	}

	public Colour(int r, int g, int b) {
		this.setR(r);
		this.setG(g);
		this.setB(b);
		this.a = MAX_ALPHA;
	}

	public Colour(int v) {
		v = (v > 255)? 255: ((v < 0)? 0: v);
		this.r = v;
		this.g = v;
		this.b = v;
		this.a = MAX_ALPHA;
	}

	public Colour(int v, float a) {
		v = (v > 255)? 255: ((v < 0)? 0: v);
		this.r = v;
		this.g = v;
		this.b = v;
		this.setA(a);
	}

	public Colour(int r, int g, int b, float a) {
		this.setR(r);
		this.setG(g);
		this.setB(b);
		this.setA(a);
	}

	private static Random random = new Random(System.currentTimeMillis());

	public static Colour random(boolean opaque) {
		return new Colour(
				random.nextInt(256),
				random.nextInt(256),
				random.nextInt(256),
				opaque? MAX_ALPHA: random.nextFloat());
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = (r > 255)? 255: ((r < 0)? 0: r);
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = (g > 255)? 255: ((g < 0)? 0: g);
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = (b > 255)? 255: ((b < 0)? 0: b);
	}

	public float getA() {
		return a;
	}

	public void setA(float a) {
		this.a = (a > MAX_ALPHA)? MAX_ALPHA: ((a < MIN_ALPHA)? MIN_ALPHA: a);
	}

	public String toHTML() throws IllegalStateException {
		if (this.a != MAX_ALPHA) {
			throw new IllegalStateException();
		}
		return String.format("#%02X%02X%02X", this.r, this.g, this.b);
	}

	@Override
	public void addJSON(StringBuilder sb) {
		sb.append(JSON.QUOTE).append("rgba(")
			.append(r).append(JSON.SEP)
			.append(g).append(JSON.SEP)
			.append(b).append(JSON.SEP)
			.append(a).	append(')').append(JSON.QUOTE);
	}

	@Override
	public Colour clone() {
		return new Colour(this.r, this.g, this.b, this.a);
	}
}
