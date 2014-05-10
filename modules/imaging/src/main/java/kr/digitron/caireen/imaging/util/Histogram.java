package kr.digitron.caireen.imaging.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public final class Histogram {

    private final static int HISTOGRAM_HEIGHT = 400;

    private final Map<Integer, Integer> data;
    private final int size;
    private final int max;

    Histogram(final int max, final int[] data) {
	this.max = max;
	size = data.length;
	this.data = new HashMap<Integer, Integer>();
	for (int i = 0; i < data.length; i++) {
	    this.data.put(i, data[i]);
	}
    }

    public int getMax() {
	return max;
    }

    public int getSize() {
	return size;
    }

    public int getData(final int index) {
	return data.get(index);
    }

    public BufferedImage generateImage() {
	BufferedImage image = new BufferedImage(getSize() * 2, HISTOGRAM_HEIGHT, BufferedImage.TYPE_INT_ARGB);
	Graphics graphics = image.getGraphics();
	graphics.setColor(Color.BLACK);
	int max = this.max / 10;
	graphics.drawString("Max : " + max, 20, 20);
	graphics.drawLine(0, 0, 0, HISTOGRAM_HEIGHT);
	graphics.drawLine(0, HISTOGRAM_HEIGHT - 1, getSize() * 2, HISTOGRAM_HEIGHT - 1);
	graphics.setColor(Color.BLUE);
	int previous = HISTOGRAM_HEIGHT - getData(0) * HISTOGRAM_HEIGHT / max;
	for (int i = 1; i < getSize(); i++) {
	    int actual = HISTOGRAM_HEIGHT - getData(i) * HISTOGRAM_HEIGHT / max;
	    graphics.drawLine((i - 1) * 2, previous, i * 2 + 1, actual);
	    previous = actual;
	}
	return image;
    }
}
