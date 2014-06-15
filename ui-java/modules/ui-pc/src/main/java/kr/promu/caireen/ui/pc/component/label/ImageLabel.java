package kr.promu.caireen.ui.pc.component.label;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import kr.promu.caireen.imaging.util.Histogram;
import kr.promu.caireen.imaging.util.HistogramBuilder;

public class ImageLabel extends JLabel {

    private static final long serialVersionUID = 1L;

    private BufferedImage image;

    public ImageLabel() {
	init();
    }

    public ImageLabel(final BufferedImage image) {
	this();
	setImage(image);
    }

    public void setImage(final BufferedImage image) {
	this.image = image;
	setIcon(new ImageIcon(image));
    }

    private void init() {
	addMouseListener(new MouseAdapter() {
	    @Override
	    public void mousePressed(final MouseEvent e) {
		if (image != null) {
		    Histogram histogram = new HistogramBuilder(image).build();
		    JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(histogram.generateImage())));
		}
	    }
	});
    }
}
