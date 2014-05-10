package kr.digitron.caireen.imaging.data.pixel;

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
	return new int[] { red, green, blue, alfa };
    }
}
