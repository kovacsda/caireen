package kr.promu.caireen.imaging.data.pixel;

public class RGBPixelData implements PixelData {

    private byte red;
    private byte green;
    private byte blue;
    private byte alfa;

    public RGBPixelData(final int argb) {
	setARgb(argb);
    }

    public void setARgb(final int argb) {
	red = (byte) (argb & 0xFF);
	green = (byte) (argb >> 8 & 0xFF);
	blue = (byte) (argb >> 16 & 0xFF);
	alfa = (byte) (argb >> 24 & 0xFF);
    }

    public int getRed() {
	return red & 0xFF;
    }

    public void setRed(final int red) {
	this.red = (byte) red;
    }

    public int getGreen() {
	return green & 0xFF;
    }

    public void setGreen(final int green) {
	this.green = (byte) green;
    }

    public int getBlue() {
	return blue & 0xFF;
    }

    public void setBlue(final int blue) {
	this.blue = (byte) blue;
    }

    public int getAlfa() {
	return alfa & 0xFF;
    }

    public void setAlfa(final int alfa) {
	this.alfa = (byte) alfa;
    }

    @Override
    public int[] getDisplayColor() {
	return new int[] { blue, green, red };
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + alfa;
	result = prime * result + blue;
	result = prime * result + green;
	result = prime * result + red;
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
	RGBPixelData other = (RGBPixelData) obj;
	if (alfa != other.alfa) {
	    return false;
	}
	if (blue != other.blue) {
	    return false;
	}
	if (green != other.green) {
	    return false;
	}
	if (red != other.red) {
	    return false;
	}
	return true;
    }
}
