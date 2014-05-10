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

    public Map<String, ImageTile> data = new HashMap<>();

    public GridLayout layout;
    public JPanel content;

    public ImageTileFrame(final int columns) {
	layout = new GridLayout(1, columns, 5, 5);
	content = new JPanel(layout);
	build();
    }

    private void build() {
	getContentPane().setLayout(new BorderLayout());
	getContentPane().add(new JScrollPane(content, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));
    }

    @Override
    public void addData(final long id, final String processor, final ImageData<?> imageData) {
	if (!data.containsKey(processor)) {
	    final ImageTile tile = new ImageTile(processor, id, imageData.generateImage());
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
		    tile.updateImage(id, imageData.generateImage());
		}
	    });
	}
    }
}
