package presentation.mainui;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.UIManager;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.theme.SubstanceAquaTheme;

import presentation.playerui.PlayerSelectionPanel;
import presentation.teamsui.TeamsFrame;
import presentation.teamsui.TeamsInfoFrame;
import presentation.teamsui.TeamsRankingFrame;

public class MainFrame {

	public static JFrame frame;

	public static JPanel panel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try{
			UIManager.setLookAndFeel(new SubstanceLookAndFeel());      
            if (System.getProperty("substancelaf.useDecorations") == null) {      
                JFrame.setDefaultLookAndFeelDecorated(true);  
                JDialog.setDefaultLookAndFeelDecorated(true);
                /**  
                 * setDefaultLookAndFeelDecorated设置为true或者false     
                 * JDialog也可以使用setDefaultLookAndFeelDecorated    
                 */   
            }      
                
            // 设置当前的主题风格，同样我 们还可以设置当前的按钮形状，水印风格等等      
            SubstanceLookAndFeel.setCurrentTheme(new SubstanceAquaTheme());
			} catch (Exception e){
				e.printStackTrace();
			}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
		initPanels();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initPanels(){
		TeamsFrame window = new TeamsFrame();
		frame.getContentPane().add(TeamsFrame.scrollPane);
		TeamsFrame.scrollPane.setVisible(false);
		
		TeamsInfoFrame window1 = new TeamsInfoFrame();
		frame.getContentPane().add(TeamsInfoFrame.scrollPane);
		TeamsInfoFrame.scrollPane.setVisible(false);
		
		TeamsRankingFrame window2 = new TeamsRankingFrame();
		frame.getContentPane().add(TeamsRankingFrame.scrollPane);
		TeamsRankingFrame.scrollPane.setVisible(false);
		
		new PlayerSelectionPanel();
		frame.getContentPane().add(PlayerSelectionPanel.scrollPane);
		PlayerSelectionPanel.scrollPane.setVisible(false);
		
	}
	
	private void initialize() {
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1000, 600);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("pictures\\NBA.jpg"));
		frame.setTitle("NBA");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 1000, 600);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("NBA球队选择界面");
		btnNewButton.setBounds(65, 34, 159, 55);
		panel.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				try {
					MainFrame.panel.setVisible(false);
					TeamsFrame.scrollPane.setVisible(true);
					TeamsFrame.flag=true;
					MainFrame.frame.setTitle("NBA球队选择");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
			
		});
		
		JButton btnNewButton_1 = new JButton("NBA球队排名界面");
		btnNewButton_1.setBounds(65, 121, 159, 55);
		panel.add(btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				try {
					MainFrame.panel.setVisible(false);
					TeamsRankingFrame.scrollPane.setVisible(true);
					MainFrame.frame.setTitle("NBA球队排名");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
			
		});
		
		JButton btnNewButton_2 = new JButton("NBA球员选择界面");
		btnNewButton_2.setBounds(65, 211, 159, 55);
		panel.add(btnNewButton_2);
		
		btnNewButton_2.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				try {
					MainFrame.panel.setVisible(false);
					PlayerSelectionPanel.scrollPane.setVisible(true);
					MainFrame.frame.setTitle("NBA球员选择");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
			
		});
		
	}
}
