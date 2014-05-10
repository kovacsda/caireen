package kr.digitron.caireen.ui.pc.component.label;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImageTile extends JPanel {

    private static final long serialVersionUID = 1L;

    private final String processor;

    private JLabel label;
    private ImageLabel imageLabel;
    private long time;

    public ImageTile(final String processor) {
	this.processor = processor;
	build();
    }

    public ImageTile(final String processor, final long id, final BufferedImage image) {
	this(processor);
	updateImage(id, image);
    }

    private void build() {
	label = new JLabel();
	imageLabel = new ImageLabel();

	setLayout(new BorderLayout());
	add(label, BorderLayout.NORTH);
	add(imageLabel, BorderLayout.CENTER);
    }

    public void updateImage(final long id, final BufferedImage image) {
	label.setText(processor + " (" + (id - time) + " ms)");
	imageLabel.setImage(image);
	time = id;
    }
}
