package kr.promu.caireen.imaging.workflow;

import kr.promu.caireen.imaging.data.ImageData;

public class ImageProcessEvent {

    private final long time;
    private final String processorName;
    private final ImageData<?> image;

    public ImageProcessEvent(final long time, final String processor, final ImageData<?> image) {
	this.time = time;
	this.processorName = processor;
	this.image = image;
    }

    public long getTime() {
	return time;
    }

    public String getProcessorName() {
	return processorName;
    }

    public ImageData<?> getImage() {
	return image;
    }
}