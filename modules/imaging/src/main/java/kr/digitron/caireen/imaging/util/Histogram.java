package kr.digitron.caireen.imaging.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class Histogram {

    private final static int HISTOGRAM_HEIGHT = 400;

    private final Map<Integer, Integer> data;
    private final int count;
    private final int size;
    private final int max;
    private final int viewMax;

    Histogram(final int max, final int count, final int[] data) {
	this.max = max;
	this.count = count;
	size = data.length;
	this.data = new HashMap<Integer, Integer>();
	for (int i = 0; i < data.length; i++) {
	    this.data.put(i, data[i]);
	}
	viewMax = generateViewMax(max, data);
    }

    private static int generateViewMax(final int max, final int[] data) {
	Arrays.sort(data);
	int index = 0;
	for (int i = 0; i < data.length; i++) {
	    if (data[i] > 0) {
		index = i;
		break;
	    }
	}
	index = index + (data.length - index) * 9 / 10;
	int border = data[index];
	return max / 3 < border ? border : border * 3;
    }

    public int getMax() {
	return max;
    }

    public int getSize() {
	return size;
    }

    public int getCount() {
	return count;
    }

    public int getData(final int index) {
	return data.get(index);
    }

    public BufferedImage generateImage() {
	BufferedImage image = new BufferedImage(getSize() * 2, HISTOGRAM_HEIGHT + 1, BufferedImage.TYPE_INT_ARGB);
	Graphics graphics = image.getGraphics();
	for (int i = 0; i < getSize(); i++) {
	    if (getData(i) == 0) {
		graphics.setColor(Color.RED);
		graphics.drawRect(i * 2, HISTOGRAM_HEIGHT - 1, 1, 1);
	    } else {
		graphics.setColor(Color.GREEN);
		int actual = HISTOGRAM_HEIGHT - getData(i) * HISTOGRAM_HEIGHT / viewMax;
		graphics.drawRect(i * 2, actual, 1, HISTOGRAM_HEIGHT - actual);
	    }
	}
	graphics.setColor(Color.BLACK);
	graphics.drawString("View Max : " + viewMax, 80, 20);
	graphics.drawString("Count : " + count, 80, 40);
	graphics.drawString("Max : " + max, 80, 60);
	graphics.drawLine(0, 0, 0, HISTOGRAM_HEIGHT);
	graphics.drawLine(0, HISTOGRAM_HEIGHT, getSize() * 2, HISTOGRAM_HEIGHT);
	return image;
    }
}
