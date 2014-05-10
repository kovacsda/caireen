package kr.digitron.caireen.ui.pc.config;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;

import kr.digitron.caireen.imaging.interceptor.ImageInterceptor;
import kr.digitron.caireen.imaging.interceptor.WebcamImageInterceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.sarxos.webcam.Webcam;

@Configuration
public class ImageInterceptionSpringConfiguration {

    @Bean
    public ImageInterceptor webcamImageInterceptor(final Webcam webcam) {
	return new WebcamImageInterceptor(webcam);
    }

    @Bean
    public Webcam webcam() {
	Webcam webcam = Webcam.getDefault();
	JComboBox<Dimension> comboBox = new JComboBox<>(webcam.getDevice().getResolutions());
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
	webcam.getDevice().setResolution((Dimension) comboBox.getSelectedItem());
	return webcam;
    }
}
