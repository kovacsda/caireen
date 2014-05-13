package kr.digitron.caireen.ui.pc.component.frame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import kr.digitron.caireen.imaging.data.ImageData;
import kr.digitron.caireen.ui.pc.component.label.ImageTile;
import kr.digitron.caireen.ui.pc.component.util.ComponentUtil;
import kr.digitron.caireen.ui.pc.component.util.ComponentUtil.RebuildCallBack;

public class ImageTileFrame extends ImageFrame {

    private static final long serialVersionUID = 1L;

    private final Map<String, ImageTile> data = new HashMap<>();

    private final GridLayout layout;
    private final JPanel content;
    private long lastDataTime;

    public ImageTileFrame(final int columns, final boolean useScroll) {
	layout = new GridLayout(1, columns, 5, 5);
	content = new JPanel(layout);
	build(useScroll);
    }

    private void build(final boolean useScroll) {
	getContentPane().setLayout(new BorderLayout());
	if (useScroll) {
	    getContentPane().add(new JScrollPane(content, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS),
		    BorderLayout.CENTER);
	} else {
	    getContentPane().add(content, BorderLayout.CENTER);
	}
    }

    @Override
    public void addData(final long time, final String processor, final ImageData<?> imageData) {
	final long processTime = time - lastDataTime;
	if (!data.containsKey(processor)) {
	    final ImageTile tile = new ImageTile(processor, processTime, imageData.generateImage());
	    data.put(processor, tile);
	    ComponentUtil.revalidate(content, new RebuildCallBack() {
		@Override
		public void call() {
		    if (layout.getRows() * layout.getColumns() <= data.size()) {
			layout.setRows(layout.getRows() + 1);
		    }
		    content.add(tile);
		}
	    });
	} else {
	    final ImageTile tile = data.get(processor);
	    ComponentUtil.revalidate(tile, new RebuildCallBack() {
		@Override
		public void call() {
		    tile.updateImage(processTime, imageData.generateImage());
		}
	    });
	}
	lastDataTime = time;
    }
}
