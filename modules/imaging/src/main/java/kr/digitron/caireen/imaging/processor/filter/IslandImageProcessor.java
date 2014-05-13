package kr.digitron.caireen.imaging.processor.filter;

import kr.digitron.caireen.imaging.data.ImageData;
import kr.digitron.caireen.imaging.data.pixel.PixelData;

public class IslandImageProcessor<T extends PixelData> extends AreaFilterImageProcessor<T> {

    private final int neigborCount;

    public IslandImageProcessor(final int neigborCount, final int aroundSize) {
	super(aroundSize);
	this.neigborCount = neigborCount;
    }

    @Override
    protected boolean areaFilter(final int x, final int y, final ImageData<T> image) {
	boolean drop;
	int count = 0;
	T defaultPixel = image.getDefaultPixel();
	for (int dx = -getAroundSize(); dx <= getAroundSize(); dx++) {
	    for (int dy = -getAroundSize(); dy <= getAroundSize(); dy++) {
		if (!defaultPixel.equals(image.get(x + dx, y + dy))) {
		    count++;
		}
	    }
	}
	drop = count - 1 < neigborCount;
	return drop;
    }

    @Override
    public String getName() {
	return super.getName() + " (" + neigborCount + "," + getAroundSize() + ")";
    }
}
