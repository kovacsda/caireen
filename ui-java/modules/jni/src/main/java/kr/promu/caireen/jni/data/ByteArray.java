package kr.promu.caireen.jni.data;

public class ByteArray {

    private final int length;
    private final byte[] dataArray;

    public ByteArray(final int length) {
	this.length = length;
	dataArray = new byte[length];
    }

    public void setData(final int offset, final byte data) {
	dataArray[offset] = data;
    }

    public void setData(final int offset, final byte[] dataArray) {
	for (int i = 0; i < dataArray.length; i++) {
	    setData(offset + i, dataArray[i]);
	}
    }

    public void setData(final int offset, final int data) {
	dataArray[offset] = (byte) data;
	dataArray[offset + 1] = (byte) (data >> 8);
	dataArray[offset + 2] = (byte) (data >> 16);
	dataArray[offset + 3] = (byte) (data >> 24);
    }

    public byte[] getDataArray() {
	return dataArray;
    }
}
