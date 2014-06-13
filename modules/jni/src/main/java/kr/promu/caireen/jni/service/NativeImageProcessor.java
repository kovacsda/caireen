package kr.promu.caireen.jni.service;

public class NativeImageProcessor {

    static {
	System.load("C:/_dev/digitron/caireen/working_copy/modules/jni/src/main/resources/ImageProcessingJNITest01.dll");
    }

    public native int processImage(byte[] rawData, int width, int height, int bytesPerLine);

}
