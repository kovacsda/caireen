package kr.digitron.caireen.imaging.interceptor;

/**
 * Interceptor to capture images.
 */
public interface ImageInterceptor {

    void addListener(CaptureImageListener listener);

    boolean removeListener(CaptureImageListener listener);

    void removeAllListener();
}
