package dyds.catalog.alpha.view;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

import dyds.catalog.alpha.presenter.DeletePresenter;
import dyds.catalog.alpha.presenter.SelectPresenter;

public class LocalView {
	
	private JPanel storagePanel;
	protected JComboBox titlesComboBox;
	protected JTextPane extractTextPane;
	protected JButton deleteButton;
	
	private SelectPresenter selectPresenter;
	private DeletePresenter deletePresenter;
	
	public LocalView(MainWindow window, SelectPresenter selectPresenter, DeletePresenter deletePresenter) {
		this.selectPresenter = selectPresenter;
		this.deletePresenter = deletePresenter;
		JTabbedPane tabbedPane = window.getTabbedPane();
		initializeTab(tabbedPane);
		initializeListeners();
	}

	private void initializeTab(JTabbedPane tabbedPane) {
	    storagePanel = new JPanel();
	    storagePanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
	    tabbedPane.addTab("Interact with Stored Info", storagePanel);
	    titlesComboBox = new JComboBox();
	    storagePanel.add(titlesComboBox, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
	    final JScrollPane scrollPane2 = new JScrollPane();
	    storagePanel.add(scrollPane2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
	    extractTextPane = new JTextPane();
	    extractTextPane.setContentType("text/html");
	    extractTextPane.setEditable(false);
	    scrollPane2.setViewportView(extractTextPane);
	    deleteButton = new JButton();
	    deleteButton.setText("Delete!");
	    storagePanel.add(deleteButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
	    tabbedPane.repaint();
	}
	
	private void initializeListeners() {
		  titlesComboBox.addActionListener(new ActionListener() {
			  
			  @Override
			  public void actionPerformed(ActionEvent actionEvent) {
				  selectPresenter.selectEntry(titlesComboBox.getSelectedItem());				  		  
			  }
		  });
		  deleteButton.addActionListener(new ActionListener() {
			  @Override
			  public void actionPerformed(ActionEvent actionEvent) {
				  deletePresenter.deleteEntry(titlesComboBox.getSelectedItem());
			  }
		  });
	}
		
  	public void setLocalExtractText(String text) {
  		extractTextPane.setText(text);
  	}
  	
  	public void updateArticles(Object[] titles) {
  		titlesComboBox.setModel(new DefaultComboBoxModel(titles));
  	}
  	
  	public void throwErrorMessage(String title, String message) {
  		JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
  	}
  	
  	public void throwInfoMessage(String title, String message) {
  		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
  	}
	
}
