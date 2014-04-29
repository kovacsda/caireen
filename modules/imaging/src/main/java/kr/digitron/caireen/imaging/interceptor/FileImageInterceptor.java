package kr.digitron.caireen.imaging.interceptor;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

import javax.annotation.PreDestroy;
import javax.imageio.ImageIO;

public class FileImageInterceptor extends DefaultImageInterceptor implements Closeable {

    private final Object lock = new Object();

    private final File[] images;
    private int index;

    private Thread captureThread;

    public FileImageInterceptor(final String dir) {
	this.images = new File(dir).listFiles();
	index = 0;
    }

    @Override
    protected void startCapture() {
	synchronized (lock) {
	    captureThread = new Thread(new CaptureRunner(), "FileCaptureThread");
	    captureThread.start();
	}
    }

    @Override
    protected void stopCapture() {
	synchronized (lock) {
	    captureThread.interrupt();
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
		try {
		    fireEvent(ImageIO.read(images[index]));
		    index++;
		    if (index == images.length) {
			index = 0;
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	}
    }
}
