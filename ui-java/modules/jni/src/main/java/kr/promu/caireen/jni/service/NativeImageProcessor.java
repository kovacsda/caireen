package kr.promu.caireen.jni.service;

public class NativeImageProcessor {

    static {
	System.load("C:/_dev/digitron/caireen/working_copy/modules/jni/src/main/resources/ImageProcessingJNITest01.dll");
    }

    /**
     * 
     * @return
     */
    public native int getVersion();

    /**
     * 
     * @param param
     * @return
     */
    public native byte[] request(byte[] param);

    /**
     * 
     * @param callID
     * @param result
     */
    public native void answer(byte[] callID, byte[] result);

    /**
     * 
     * @param result
     */
    public native void notify(byte[] result);

}
