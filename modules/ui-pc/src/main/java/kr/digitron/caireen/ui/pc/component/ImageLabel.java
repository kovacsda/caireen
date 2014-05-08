package kr.digitron.caireen.ui.pc.component;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import kr.digitron.caireen.imaging.util.Histogram;
import kr.digitron.caireen.imaging.util.HistogramBuilder;

public class ImageLabel extends JLabel {

    private static final long serialVersionUID = 1L;

    private final BufferedImage bufferedImage;

    public ImageLabel(final BufferedImage bufferedImage) {
	super(new ImageIcon(bufferedImage));
	this.bufferedImage = bufferedImage;
	init();
    }

    private void init() {
	addMouseListener(new MouseAdapter() {
	    @Override
	    public void mousePressed(final MouseEvent e) {
		Histogram histogram = new HistogramBuilder(bufferedImage).build();
		JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(histogram.generateImage())));
	    }
	});
    }
}
