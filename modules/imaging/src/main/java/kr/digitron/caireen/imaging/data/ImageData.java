package kr.digitron.caireen.imaging.data;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;

import kr.digitron.caireen.imaging.data.pixel.PixelData;

public abstract class ImageData<D extends PixelData> {

    private final List<D> data;
    private final int width;
    private final int height;

    public ImageData(final int width, final int height) {
	data = new ArrayList<>(width * height);
	this.width = width;
	this.height = height;
    }

    public int getWidth() {
	return width;
    }

    public int getHeight() {
	return height;
    }

    public D get(final int x, final int y) {
	return data.get(x + y * width);
    }

    public void set(final int x, final int y, final D data) {
	this.data.set(x + y * width, data);
    }

    public BufferedImage generateImage() {
	BufferedImage image = new BufferedImage(width, height, getImageType());
	WritableRaster raster = image.getRaster();
	for (int x = 0; x < image.getWidth(); x++) {
	    for (int y = 0; y < image.getHeight(); y++) {
		int[] color = get(x, y).getDisplayColor();
		for (int b = 0; b < color.length; b++) {
		    raster.setSample(x, y, b, color[b]);
		}
	    }
	}
	return image;
    }

    protected abstract int getImageType();

}
