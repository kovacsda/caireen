package kr.digitron.caireen.imaging.data.pixel;

import kr.digitron.caireen.imaging.util.PointUtil.AngleClass;

public class EdgePixelData implements PixelData {

    private byte grad;
    private AngleClass angleClass;

    public EdgePixelData(final int grad, final AngleClass angleClass) {
	setGrad(grad);
	setAngleClass(angleClass);
    }

    public void setGrad(final int grad) {
	this.grad = (byte) grad;
    }

    public int getGrad() {
	return grad & 0xFF;
    }

    public void setAngleClass(final AngleClass angleClass) {
	this.angleClass = angleClass;
    }

    public AngleClass getAngleClass() {
	return angleClass;
    }

    @Override
    public int[] getDisplayColor() {
	int[] color;
	switch (angleClass) {
	case ANGLE_CLASS_90:
	    color = new int[] { grad, grad, 0 };
	    break;
	case ANGLE_CLASS_135:
	    color = new int[] { 0, grad, 0 };
	    break;
	case ANGLE_CLASS_45:
	    color = new int[] { 0, 0, grad };
	    break;
	case ANGLE_CLASS_0:
	    color = new int[] { grad, 0, 0 };
	    break;
	default:
	    color = new int[] { grad, grad, grad };
	    break;
	}
	return color;
    }
}
