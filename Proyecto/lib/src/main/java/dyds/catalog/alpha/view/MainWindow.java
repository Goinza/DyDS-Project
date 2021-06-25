package dyds.catalog.alpha.view;

import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainWindow extends JFrame {
	
	private JTabbedPane tabbedPane;
	
	public MainWindow() {
		setVisible(true);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    //setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
	    setMinimumSize(new Dimension(400, 400));
	    setPreferredSize(new Dimension(400, 400));
	    tabbedPane = new JTabbedPane();
	    add(tabbedPane);
	}
	
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	
}
