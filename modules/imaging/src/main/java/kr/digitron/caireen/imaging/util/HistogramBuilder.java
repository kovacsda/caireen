package kr.digitron.caireen.imaging.util;

import java.awt.image.BufferedImage;

import kr.digitron.caireen.imaging.util.ProcessUtil.PixelGrayscaleCallBack;

public class HistogramBuilder {

    private final BufferedImage image;
    private int max;
    private int[] data;

    public HistogramBuilder(final BufferedImage image) {
	super();
	this.image = image;
    }

    /**
     * Not thread safe!
     * 
     * @return
     */
    public Histogram build() {
	data = new int[256];
	max = 0;
	ProcessUtil.processGrayscaleImage(new PixelGrayscaleCallBack() {
	    @Override
	    public void call(final int y) {
		if (++data[y] > max) {
		    max = data[y];
		}
	    }
	}, image);
	return new Histogram(max, image.getWidth() * image.getHeight(), data);
    }
}
