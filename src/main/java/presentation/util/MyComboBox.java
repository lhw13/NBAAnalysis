package presentation.util;

import java.awt.Dimension;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

@SuppressWarnings({ "serial", "rawtypes" })
public class MyComboBox extends JComboBox {

	private int popupWidth;

	@SuppressWarnings("unchecked")
	public MyComboBox(DefaultComboBoxModel model) {
		super(model);
	}

	public Dimension getPopupSize() {
		Dimension size = getSize();
		// reset size.
		if (popupWidth < 1) {
			popupWidth = size.width;
		}
		return new Dimension(popupWidth, size.height);
	}

}
