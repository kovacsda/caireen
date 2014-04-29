package kr.digitron.caireen.imaging.interceptor;

import java.awt.image.BufferedImage;

import kr.digitron.caireen.common.service.DefaultListenableService;

public abstract class DefaultImageInterceptor extends DefaultListenableService<CaptureImageListener, BufferedImage> implements ImageInterceptor {

    private final Object lock = new Object();

    @Override
    public void addListener(final CaptureImageListener listener) {
	synchronized (lock) {
	    super.addListener(listener);
	    if (getListenerCount() == 1) {
		startCapture();
	    }
	}
    }

    @Override
    public boolean removeListener(final CaptureImageListener listener) {
	synchronized (lock) {
	    boolean result = super.removeListener(listener);
	    if (getListenerCount() == 0) {
		stopCapture();
	    }
	    return result;
	}
    }

    @Override
    public void removeAllListener() {
	synchronized (lock) {
	    super.removeAllListener();
	    stopCapture();
	}
    }

    @Override
    protected void callListener(final CaptureImageListener listener, final BufferedImage image) {
	listener.captureImage(image);
    }

    protected abstract void startCapture();

    protected abstract void stopCapture();
}
