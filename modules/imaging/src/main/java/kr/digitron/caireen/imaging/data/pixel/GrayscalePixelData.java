package kr.digitron.caireen.imaging.data.pixel;

public class GrayscalePixelData implements PixelData {

    private byte grayscale;

    public GrayscalePixelData(final int grayscale) {
	setGrayscale(grayscale);
    }

    public void setGrayscale(final int grayscale) {
	this.grayscale = (byte) grayscale;
    }

    public int getGrayscale() {
	return grayscale & 0xFF;
    }

    @Override
    public int[] getDisplayColor() {
	return new int[] { grayscale };
    }
}
