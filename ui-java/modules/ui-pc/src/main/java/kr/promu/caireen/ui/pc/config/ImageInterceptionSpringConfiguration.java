package kr.promu.caireen.ui.pc.config;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import kr.promu.caireen.common.interceptor.FileImageInterceptor;
import kr.promu.caireen.common.interceptor.ImageInterceptor;
import kr.promu.caireen.common.interceptor.WebcamImageInterceptor;
import kr.promu.caireen.ui.pc.component.label.SelectableWebcamLabel;
import kr.promu.caireen.ui.pc.component.label.SelectableWebcamLabel.WebcamButtonModel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.sarxos.webcam.Webcam;

@Configuration
public class ImageInterceptionSpringConfiguration {

    @Bean
    public ImageInterceptor imageInterceptor() {
	JComboBox<String> comboBox = new JComboBox<>(new String[] { "Webcam", "File" });
	comboBox.setSelectedIndex(0);
	comboBox.setRenderer(new ListCellRenderer<String>() {
	    @Override
	    public Component getListCellRendererComponent(final JList<? extends String> list, final String value, final int index,
		    final boolean isSelected, final boolean cellHasFocus) {
		JLabel label = new JLabel();
		label.setText(value);
		label.setOpaque(true);
		label.setForeground(cellHasFocus ? Color.DARK_GRAY : Color.BLACK);
		label.setBackground(isSelected ? Color.LIGHT_GRAY : Color.WHITE);
		return label;
	    };
	});
	JOptionPane.showMessageDialog(null, comboBox, "Select Resolution", JOptionPane.QUESTION_MESSAGE);
	return comboBox.getSelectedItem().equals("File") ? fileImageInterceptor() : webcamImageInterceptor(webcam());
    }

    public ImageInterceptor fileImageInterceptor() {
	JFileChooser chooser = new JFileChooser();
	chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	chooser.setMultiSelectionEnabled(false);
	if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	    return new FileImageInterceptor(chooser.getSelectedFile().getAbsolutePath());
	} else {
	    throw new IllegalStateException("User Cancel");
	}
    }

    public ImageInterceptor webcamImageInterceptor(final Webcam webcam) {
	return new WebcamImageInterceptor(webcam);
    }

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
