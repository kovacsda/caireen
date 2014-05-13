package kr.promu.caireen.ui.pc.component.frame;

import javax.swing.JFrame;

import kr.promu.caireen.imaging.data.ImageData;

public abstract class ImageFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    public abstract void addData(final long time, final String processor, final ImageData<?> imageData);
}
