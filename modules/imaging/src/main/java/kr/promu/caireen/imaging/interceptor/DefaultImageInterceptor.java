package kr.promu.caireen.imaging.interceptor;

import java.awt.image.BufferedImage;

import kr.promu.caireen.common.service.DefaultObservableService;
import kr.promu.caireen.common.service.ServiceObserver;

public abstract class DefaultImageInterceptor extends DefaultObservableService<BufferedImage> implements ImageInterceptor {

    private final Object lock = new Object();

    @Override
    public void registerObserver(final ServiceObserver<BufferedImage> listener) {
	synchronized (lock) {
	    super.registerObserver(listener);
	    if (getListenerCount() == 1) {
		startCapture();
	    }
	}
    }

    @Override
    public boolean unregisterObserver(final ServiceObserver<BufferedImage> listener) {
	synchronized (lock) {
	    boolean result = super.unregisterObserver(listener);
	    if (getListenerCount() == 0) {
		stopCapture();
	    }
	    return result;
	}
    }

    @Override
    public void unregisterAllObserver() {
	synchronized (lock) {
	    super.unregisterAllObserver();
	    stopCapture();
	}
    }

    protected abstract void startCapture();

    protected abstract void stopCapture();
}
