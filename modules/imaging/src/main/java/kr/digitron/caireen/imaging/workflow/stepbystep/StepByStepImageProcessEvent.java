package kr.digitron.caireen.imaging.workflow.stepbystep;

import kr.digitron.caireen.imaging.data.ImageData;

public class StepByStepImageProcessEvent {

    private final long imageId;
    private final String processorName;
    private final ImageData<?> image;

    StepByStepImageProcessEvent(final long imageId, final String processor, final ImageData<?> image) {
	this.imageId = imageId;
	this.processorName = processor;
	this.image = image;
    }

    public long getImageId() {
	return imageId;
    }

    public String getProcessorName() {
	return processorName;
    }

    public ImageData<?> getImage() {
	return image;
    }
}