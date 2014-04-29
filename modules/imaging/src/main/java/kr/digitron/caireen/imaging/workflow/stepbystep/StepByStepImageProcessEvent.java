package kr.digitron.caireen.imaging.workflow.stepbystep;

import java.awt.image.BufferedImage;

public class StepByStepImageProcessEvent {

    private final long imageId;
    private final String processorName;
    private final BufferedImage image;

    StepByStepImageProcessEvent(final long imageId, final String processor, final BufferedImage image) {
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

    public BufferedImage getImage() {
	return image;
    }
}