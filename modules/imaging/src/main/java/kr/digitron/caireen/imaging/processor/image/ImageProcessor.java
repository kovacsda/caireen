package kr.digitron.caireen.imaging.processor.image;

import java.awt.image.BufferedImage;

/**
 * Process image and create a result.
 */
public interface ImageProcessor {

    /**
     * Process image and create a result. The result can be null, if the
     * processor don't have enought inforamtion, to generate the result.
     * 
     * @param image
     *            input image
     * @return output image
     */
    BufferedImage process(BufferedImage image);
}
