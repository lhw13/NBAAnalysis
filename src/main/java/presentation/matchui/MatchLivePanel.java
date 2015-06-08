package presentation.matchui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import presentation.mainui.MainFrame;
import presentation.mainui.Panels;

public class MatchLivePanel extends JPanel{

	public static JScrollPane scrollPane;
	
	JPanel panelOfBottom = new JPanel();
	
	public static JTextArea jtext;
	
	JButton return_bt;
	
	public static Timer t;
	
	public MatchLivePanel(){
		
		this.setBounds(0, 0, 1000, 600);
		setLayout(null);
		
		//scrollPane============================================
		scrollPane = new JScrollPane(panelOfBottom);
		scrollPane.setBounds(0, 0, 1000, 600);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		add(scrollPane);
		
		panelOfBottom.setLayout(null);
		
		jtext = new JTextArea(10, 20);
		jtext.setLineWrap(true);
		jtext.setWrapStyleWord(true);
		jtext.setVisible(true);
		jtext.setEditable(false);
		
		Font font=new Font("宋体",Font.PLAIN,25);
		jtext.setFont(font);
		
		JScrollPane sp = new JScrollPane(jtext);
		sp.setBounds(150,60,700,500);
		sp.setVisible(true);
		
		panelOfBottom.add(sp);
		
		//返回===================================================
		return_bt = new JButton("返回");
		return_bt.setBounds(30, 20, 100, 30);
		return_bt.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int size = MainFrame.backPanels.size();
				Panels temp = MainFrame.backPanels.get(size-1);
				MainFrame.backPanels.remove(size-1);
				
				MatchLivePanel.scrollPane.setVisible(false);
				MatchSelectionPanel.scrollPane.setVisible(true);
				MainFrame.frame.setTitle("NBA比赛查询");
				MainFrame.currentPanel = Panels.MatchSelectionPanel;
				
				t.cancel();
			}
		});
		panelOfBottom.add(return_bt);
		
		t = new Timer();
		t.schedule(new LiveThread(), 0, 5000);
		
	}
	
	

}
