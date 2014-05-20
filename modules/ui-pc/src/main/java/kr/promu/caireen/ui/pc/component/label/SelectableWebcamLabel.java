package kr.promu.caireen.ui.pc.component.label;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JToggleButton.ToggleButtonModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;

public class SelectableWebcamLabel extends AbstractButton {

    private static final long serialVersionUID = 1L;

    public SelectableWebcamLabel(final Webcam webcam) {
	setModel(new WebcamButtonModel(webcam));
	setLayout(new BorderLayout());
	add(new WebcamPanel(webcam), BorderLayout.CENTER);
	refreshBorder();
	addChangeListener(new ChangeListener() {
	    @Override
	    public void stateChanged(final ChangeEvent e) {
		refreshBorder();
	    }
	});
	addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(final MouseEvent e) {
		getModel().setSelected(true);
	    }
	});
    }

    private void refreshBorder() {
	setBorder(BorderFactory.createLineBorder(isSelected() ? Color.RED : Color.BLACK, 3));
    }

    public final class WebcamButtonModel extends ToggleButtonModel {

	private static final long serialVersionUID = 1L;

	private final Webcam webcam;

	public WebcamButtonModel(final Webcam webcam) {
	    this.webcam = webcam;
	}

	public Webcam getWebcam() {
	    return webcam;
	}
    }
}
