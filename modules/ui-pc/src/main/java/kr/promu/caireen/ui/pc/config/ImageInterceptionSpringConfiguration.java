package kr.promu.caireen.ui.pc.config;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import kr.promu.caireen.imaging.interceptor.FileImageInterceptor;
import kr.promu.caireen.imaging.interceptor.ImageInterceptor;
import kr.promu.caireen.imaging.interceptor.WebcamImageInterceptor;
import kr.promu.caireen.ui.pc.component.label.SelectableWebcamLabel;
import kr.promu.caireen.ui.pc.component.label.SelectableWebcamLabel.WebcamButtonModel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.sarxos.webcam.Webcam;

@Configuration
public class ImageInterceptionSpringConfiguration {

    public ImageInterceptor fileImageInterceptor() {
	return new FileImageInterceptor("c:/_dev/digitron/caireen/working_copy/test_data/optimal-imgs");
    }

    @Bean
    public ImageInterceptor webcamImageInterceptor(final Webcam webcam) {
	return new WebcamImageInterceptor(webcam);
    }

    @Bean
    public Webcam webcam() {
	Webcam webcam = selectWebcam();
	webcam.getDevice().setResolution(selectResolution(webcam.getDevice().getResolutions()));
	return webcam;
    }

    private Webcam selectWebcam() {
	List<Webcam> webcams = Webcam.getWebcams();
	if (webcams.size() == 1) {
	    return Webcam.getDefault();
	} else {
	    JPanel panel = new JPanel(new GridLayout(1, webcams.size()));
	    ButtonGroup group = new ButtonGroup();
	    boolean first = true;
	    for (Webcam webcam : webcams) {
		SelectableWebcamLabel webcamLabel = new SelectableWebcamLabel(webcam);
		panel.add(webcamLabel);
		group.add(webcamLabel);
		if (first) {
		    group.setSelected(webcamLabel.getModel(), true);
		    first = false;
		}
	    }
	    JOptionPane.showMessageDialog(null, panel, "Select Webcam", JOptionPane.QUESTION_MESSAGE);
	    return ((WebcamButtonModel) group.getSelection()).getWebcam();
	}
    }

    private Dimension selectResolution(final Dimension[] dimensions) {
	JComboBox<Dimension> comboBox = new JComboBox<>(dimensions);
	comboBox.setSelectedIndex(1);
	comboBox.setRenderer(new ListCellRenderer<Dimension>() {
	    @Override
	    public Component getListCellRendererComponent(final JList<? extends Dimension> list, final Dimension value, final int index,
		    final boolean isSelected, final boolean cellHasFocus) {
		JLabel label = new JLabel();
		label.setText(value.getWidth() + " × " + value.getHeight());
		label.setOpaque(true);
		label.setForeground(cellHasFocus ? Color.DARK_GRAY : Color.BLACK);
		label.setBackground(isSelected ? Color.LIGHT_GRAY : Color.WHITE);
		return label;
	    };
	});
	JOptionPane.showMessageDialog(null, comboBox, "Select Resolution", JOptionPane.QUESTION_MESSAGE);
	return (Dimension) comboBox.getSelectedItem();
    }
}
