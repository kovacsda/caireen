package kr.promu.caireen.jni.service;

public class ImageProcessor {

    public native int processImage(byte[] rawData, int width, int height, int bytesPerLine);
}
