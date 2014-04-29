package kr.digitron.caireen.imaging.interceptor;

import java.io.Closeable;
import java.io.IOException;

import javax.annotation.PreDestroy;

import com.github.sarxos.webcam.Webcam;

public class WebcamImageInterceptor extends DefaultImageInterceptor implements Closeable {

    private final Webcam webcam;

    private Thread captureThread;

    public WebcamImageInterceptor(final Webcam webcam) {
	this.webcam = webcam;
    }

    @Override
    protected void startCapture() {
	synchronized (webcam) {
	    webcam.open();
	    captureThread = new Thread(new CaptureRunner(), "WebcamCaptureThread");
	    captureThread.start();
	}
    }

    @Override
    protected void stopCapture() {
	synchronized (webcam) {
	    try {
		captureThread.interrupt();
	    } finally {
		webcam.close();
	    }
	}
    }

    @Override
    @PreDestroy
    public void close() throws IOException {
	stopCapture();
    }

    private final class CaptureRunner implements Runnable {

	@Override
	public void run() {
	    while (!Thread.currentThread().isInterrupted()) {
		fireEvent(webcam.getImage());
	    }
	}
    }
}
