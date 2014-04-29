package kr.digitron.caireen.imaging.interceptor;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class DefaultImageInterceptor implements ImageInterceptor {

    private final List<CaptureImageListener> listeners = new ArrayList<>();

    @Override
    public void addListener(final CaptureImageListener listener) {
	synchronized (listeners) {
	    if (listeners.size() == 0) {
		startCapture();
	    }
	    listeners.add(listener);
	}
    }

    @Override
    public boolean removeListener(final CaptureImageListener listener) {
	synchronized (listeners) {
	    boolean result = listeners.remove(listener);
	    if (listeners.size() == 0) {
		stopCapture();
	    }
	    return result;
	}
    }

    @Override
    public void removeAllListener() {
	synchronized (listeners) {
	    listeners.clear();
	    stopCapture();
	}
    }

    protected void fireEvent(final BufferedImage image) {
	for (CaptureImageListener listener : listeners) {
	    listener.captureImage(image);
	}
    }

    protected abstract void startCapture();

    protected abstract void stopCapture();
}
