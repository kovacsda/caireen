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
	case DEGREE_90:
	    color = new int[] { grad, grad, 0 };
	    break;
	case DEGREE_135:
	    color = new int[] { 0, grad, 0 };
	    break;
	case DEGREE_45:
	    color = new int[] { 0, 0, grad };
	    break;
	case DEGREE_0:
	    color = new int[] { grad, 0, 0 };
	    break;
	default:
	    color = new int[] { grad, grad, grad };
	    break;
	}
	return color;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (angleClass == null ? 0 : angleClass.hashCode());
	result = prime * result + grad;
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
	EdgePixelData other = (EdgePixelData) obj;
	if (angleClass != other.angleClass) {
	    return false;
	}
	if (grad != other.grad) {
	    return false;
	}
	return true;
    }
}
