package dyds.catalog.alpha.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import dyds.catalog.alpha.presenter.SavePresenter;
import dyds.catalog.alpha.presenter.SearchPresenter;

public class OnlineView {
	
	private JPanel searchPanel;
	protected JButton searchButton;
	protected JTextField titleTextField;
	protected JTextPane extractTextPane;
	protected JButton saveLocallyButton;
	
	private SearchPresenter searchPresenter;
	private SavePresenter savePresenter;

	public OnlineView(MainWindow window, SearchPresenter searchPresenter, SavePresenter savePresenter) {
		JTabbedPane tabbedPane = window.getTabbedPane();
		this.searchPresenter = searchPresenter;
		this.savePresenter = savePresenter;
		
		initializeTab(tabbedPane);
		initializeListeners();
	}
	
	private void initializeTab(JTabbedPane tabbedPane) {
		searchPanel = new JPanel();
	    searchPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
	    tabbedPane.addTab("Search in Wikipedia", searchPanel);
	    titleTextField = new JTextField();	    
	    searchPanel.add(titleTextField, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
	    searchButton = new JButton();
	    searchButton.setText("Search");
	    searchPanel.add(searchButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
	    final JScrollPane scrollPane = new JScrollPane();
	    searchPanel.add(scrollPane, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
	    extractTextPane = new JTextPane();
	    extractTextPane.setContentType("text/html");
	    extractTextPane.setEditable(false);
	    scrollPane.setViewportView(extractTextPane);
	    saveLocallyButton = new JButton();
	    saveLocallyButton.setText("Save article");
	    searchPanel.add(saveLocallyButton, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
	    tabbedPane.repaint();
	}
	
	private void initializeListeners() {	
		searchButton.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent e) { 
				searchPresenter.searchArticle();
			}
		});		
		saveLocallyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				savePresenter.saveLastSearchedArticle();
			}
		});
	}
	
  	public void setWorkingStatus() {
  		for(Component c: this.searchPanel.getComponents()) c.setEnabled(false);
  		extractTextPane.setText("");
  	}

  	public void setWatingStatus() {
  		for(Component c: this.searchPanel.getComponents()) c.setEnabled(true);
  	}
  	
  	public String getTitleText( ) {
  		return titleTextField.getText();
  	}
  
  	public void setExtractText(String text) {
  		extractTextPane.setText(text);
  	}
  	
  	public void throwErrorMessage(String title, String message) {
  		JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
  	}
  	
  	public void throwInfoMessage(String title, String message) {
  		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
  	}
	
}
