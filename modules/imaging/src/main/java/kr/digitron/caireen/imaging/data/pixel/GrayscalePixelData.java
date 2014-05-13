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

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + grayscale;
	return result;
    }

    @Override
    public boolean equals(final Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	GrayscalePixelData other = (GrayscalePixelData) obj;
	if (grayscale != other.grayscale) {
	    return false;
	}
	return true;
    }
}
