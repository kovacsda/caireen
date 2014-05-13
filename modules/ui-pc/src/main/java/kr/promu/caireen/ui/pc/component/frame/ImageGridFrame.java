package kr.promu.caireen.ui.pc.component.frame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import kr.promu.caireen.imaging.data.ImageData;
import kr.promu.caireen.ui.pc.component.label.ImageLabel;
import kr.promu.caireen.ui.pc.component.util.ComponentUtil;
import kr.promu.caireen.ui.pc.component.util.ComponentUtil.RebuildCallBack;

public class ImageGridFrame extends ImageFrame {

    private static final long serialVersionUID = 1L;

    public List<String> columns = new ArrayList<>();
    public List<String> rows = new ArrayList<>();
    public Map<GridKey, Component> data = new HashMap<>();

    public GridLayout layout = new GridLayout(1, 1, 5, 5);
    public JPanel content = new JPanel(layout);

    public ImageGridFrame() {
	getContentPane().setLayout(new BorderLayout());
	getContentPane().add(new JScrollPane(content, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));
    }

    public void addColumn(final String column) {
	if (!columns.contains(column)) {
	    columns.add(0, column);
	    layout.setColumns(layout.getColumns() + 1);
	    rebuildGui();
	}
    }

    public void addRow(final String row) {
	if (!rows.contains(row)) {
	    rows.add(row);
	    layout.setRows(layout.getRows() + 1);
	    rebuildGui();
	}
    }

    @Override
    public void addData(final long id, final String processor, final ImageData<?> imageData) {
	String stringID = String.valueOf(id);
	addColumn(stringID);
	addRow(processor);
	data.put(new GridKey(stringID, processor), new ImageLabel(imageData.generateImage()));
	rebuildGui();
    }

    protected void rebuildGui() {
	if (EventQueue.isDispatchThread()) {
	    invalidate();
	    buildGui();
	    validate();
	} else {
	    EventQueue.invokeLater(new Runnable() {
		@Override
		public void run() {
		    rebuildGui();
		}
	    });
	}
    }

    protected void buildGui() {
	ComponentUtil.revalidate(content, new RebuildCallBack() {
	    @Override
	    public void call() {
		content.removeAll();
		content.add(new JLabel(""));
		int columnCount = 0;
		for (String column : columns) {
		    if (columnCount++ < 10) {
			content.add(new JLabel(column));
		    }
		}
		for (String row : rows) {
		    content.add(new JLabel(row));
		    columnCount = 0;
		    for (String column : columns) {
			GridKey key = new GridKey(column, row);
			if (data.containsKey(key)) {
			    if (columnCount < 10) {
				content.add(data.get(key));
			    } else {
				data.remove(key);
				columns.remove(column);
			    }
			} else {
			    if (columnCount < 10) {
				content.add(new JLabel("×"));
			    }
			}
			columnCount++;
		    }
		}
	    }
	});
    }

    private final static class GridKey {

	private final String column;
	private final String row;

	public GridKey(final String column, final String row) {
	    this.column = column;
	    this.row = row;
	}

	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + (column == null ? 0 : column.hashCode());
	    result = prime * result + (row == null ? 0 : row.hashCode());
	    return result;
	}

	@Override
	public boolean equals(final Object obj) {
	    if (this == obj) {
		return true;
	    }
	    if (obj == null) {
		return false;
	    }
	    if (getClass() != obj.getClass()) {
		return false;
	    }
	    GridKey other = (GridKey) obj;
	    if (column == null) {
		if (other.column != null) {
		    return false;
		}
	    } else if (!column.equals(other.column)) {
		return false;
	    }
	    if (row == null) {
		if (other.row != null) {
		    return false;
		}
	    } else if (!row.equals(other.row)) {
		return false;
	    }
	    return true;
	}
    }
}
