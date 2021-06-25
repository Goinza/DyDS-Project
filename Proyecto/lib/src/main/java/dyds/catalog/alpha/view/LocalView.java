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

import dyds.catalog.alpha.presenter.DeleteLocallyPresenter;
import dyds.catalog.alpha.presenter.SelectLocallyPresenter;

public class LocalView {
	
	private JPanel storagePanel;
	private JComboBox comboBox;
	private JTextPane textPane;
	private JButton deleteButton;
	
	private SelectLocallyPresenter selectPresenter;
	private DeleteLocallyPresenter deletePresenter;
	
	public LocalView(MainWindow window, SelectLocallyPresenter selectPresenter, DeleteLocallyPresenter deletePresenter) {
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
	    comboBox = new JComboBox();
	    storagePanel.add(comboBox, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
	    final JScrollPane scrollPane2 = new JScrollPane();
	    storagePanel.add(scrollPane2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
	    textPane = new JTextPane();
	    textPane.setContentType("text/html");
	    textPane.setEditable(false);
	    scrollPane2.setViewportView(textPane);
	    deleteButton = new JButton();
	    deleteButton.setText("Delete!");
	    storagePanel.add(deleteButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
	    tabbedPane.repaint();
	}
	
	private void initializeListeners() {
		  comboBox.addActionListener(new ActionListener() {
			  @Override
			  public void actionPerformed(ActionEvent actionEvent) {
				  if(comboBox.getSelectedIndex() > -1) {
					  selectPresenter.selectEntry(comboBox.getSelectedItem().toString());
				  }				  
			  }
		  });
		  deleteButton.addActionListener(new ActionListener() {
			  @Override
			  public void actionPerformed(ActionEvent actionEvent) {
				  if(comboBox.getSelectedIndex() > -1){
					  deletePresenter.deleteEntry(comboBox.getSelectedItem().toString());
				  }
			  }
		  });
	}
		
  	public void setLocalExtractText(String text) {
  		textPane.setText(text);
  	}
  	
  	public void updateLocalArray(Object[] titlesArray) {
  		comboBox.setModel(new DefaultComboBoxModel(titlesArray));
  	}
  	
  	public void throwErrorMessage(String title, String message) {
  		JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
  	}
  	
  	public void throwInfoMessage(String title, String message) {
  		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
  	}
	
}
