package kr.promu.caireen.jni.service;

public class NativeImageProcessor {

    static {
	try {
	    try {
		System.load("C:/_dev/digitron/caireen/working_copy/ui-java/modules/jni/src/native/lib/ImageProcessingJNITest01.dll");
	    } catch (Error e) {
		e.printStackTrace();
		System.load("ImageProcessingJNITest01.dll");
	    }
	} catch (Error e) {
	    e.printStackTrace();
	    System.load("C:/ImageProcessingJNITest01.dll");
	}
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
