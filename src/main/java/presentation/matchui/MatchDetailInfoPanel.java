package presentation.matchui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JButton;

import presentation.mainui.MainFrame;
import presentation.mainui.Panels;
import presentation.teamsui.TeamsInfoFrame;
import presentation.teamsui.TeamsSelectionFrame;

import javax.swing.JLabel;

public class MatchDetailInfoPanel extends JPanel {
	public static JScrollPane scrollPane;
	JPanel panelOfBottom = new JPanel();
	private JTable table;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JTable table_1;
	private JTable table_2;
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	
	public TableColumnModel getColumn(JTable table, int[] width) {  
	    TableColumnModel columns = table.getColumnModel();  
	    for (int i = 0; i < width.length; i++) {  
	        TableColumn column = columns.getColumn(i);  
	        column.setPreferredWidth(width[i]);  
	    }  
	    return columns;  
	}  
	
	public MatchDetailInfoPanel(ImageIcon ii[], DefaultTableModel model1, DefaultTableModel model2, DefaultTableModel model3) {
		this.setBounds(0, 0, 1000, 600);
		setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(panelOfBottom);
		scrollPane.setBounds(0, 0, 990, 600);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		add(scrollPane);
		
		panelOfBottom.setPreferredSize(new Dimension(1000, 1000));
		panelOfBottom.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(100, 300, 800, 300);
		panelOfBottom.add(scrollPane_1);
		
		table = new JTable();
		table.setModel(model1);
		scrollPane_1.setViewportView(table);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(100, 650, 800, 300);
		panelOfBottom.add(scrollPane_2);
		
		table_1 = new JTable();
		table_1.setModel(model2);
		scrollPane_2.setViewportView(table_1);
		
		table_2 = new JTable();
		table_2.setModel(model3);
		int[] width={50,25,60,50,30,30,30,30};
		table_2.setColumnModel(getColumn(table_2, width));
		
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(250, 100, 500, 100);
		scrollPane_3.setViewportView(table_2);
		panelOfBottom.add(scrollPane_3);
		
		btnNewButton = new JButton("返回");
		btnNewButton.setBounds(450, 250, 120, 25);
		panelOfBottom.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(50, 50, 200, 150);
		lblNewLabel.setIcon(ii[0]);
		panelOfBottom.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(750, 50, 200, 150);
		lblNewLabel_1.setIcon(ii[1]);
		panelOfBottom.add(lblNewLabel_1);
		
		btnNewButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					MatchDetailInfoPanel.scrollPane.setVisible(false);
					MatchDetailInfoPanel.scrollPane=null;
					if(MatchSelectionPanel.scrollPane!=null){
						MatchSelectionPanel.scrollPane.setVisible(true);
						MainFrame.currentPanel = Panels.MatchSelectionInfoPanel;
					}else{
						TeamsInfoFrame.scrollPane.setVisible(true);
						MainFrame.currentPanel = Panels.TeamsInfoFrame;
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		});
	}
}