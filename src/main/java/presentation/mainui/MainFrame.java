package presentation.mainui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.theme.SubstanceAquaTheme;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Toolkit;

import javax.swing.JButton;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.CardLayout;
import java.util.Timer;
import java.util.TimerTask;

public class MainFrame {
	private JFrame frmNba;

	private Timer timer;
	private final int INITIAL_DELAY = 100;
    private final int PERIOD_INTERVAL = 10;
    private static JLabel lblNewLabel_6;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try{
			
			UIManager.setLookAndFeel(new SubstanceLookAndFeel());      
            if (System.getProperty("substancelaf.useDecorations") == null) {      
                JFrame.setDefaultLookAndFeelDecorated(true);      
                /**  
                 * setDefaultLookAndFeelDecorated设置为true或者false    
                 * 区别如 图2—setDefaultLookAndFeelDecorated 所示    
                 * JDialog也可以使用setDefaultLookAndFeelDecorated    
                 */     
            }      
            /**  
             * 设置全局字体样式，否则中文将产生乱码    
             * initGlobalFontSetting是自定义的方法，在上方    
             */    
            // 设置当前的主题风格，同样我 们还可以设置当前的按钮形状，水印风格等等      
            SubstanceLookAndFeel.setCurrentTheme(new SubstanceAquaTheme());
			} catch (Exception e){
				e.printStackTrace();
			}     

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frmNba.setVisible(true);
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
		timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), 
                INITIAL_DELAY, PERIOD_INTERVAL);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNba = new JFrame();
		frmNba.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\pictures\\NBA.jpg"));
		frmNba.setTitle("NBA");
		frmNba.setBounds(100, 100, 1000, 900);
		frmNba.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNba.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1000, 200);
		panel.setOpaque(false);
		frmNba.getContentPane().add(panel);
		panel.setLayout(null);
		
		lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon("D:\\pictures\\lakers.jpg"));
		lblNewLabel_6.setBounds(400, 0, 600, 200);
		panel.add(lblNewLabel_6);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 200, 1000, 100);
		frmNba.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("D:\\pictures\\up2.jpg"));
		lblNewLabel.setBounds(0, 0, 1000, 100);
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 300, 1000, 560);
		frmNba.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\pictures\\CHI2.jpg"));
		lblNewLabel_1.setBounds(10, 10, 150, 100);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("D:\\pictures\\CLE2.jpg"));
		lblNewLabel_2.setBounds(10, 120, 150, 100);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("D:\\pictures\\DET2.jpg"));
		lblNewLabel_3.setBounds(10, 230, 150, 100);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("D:\\pictures\\IND2.jpg"));
		lblNewLabel_4.setBounds(10, 340, 150, 100);
		panel_2.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon("D:\\pictures\\MIL2.jpg"));
		lblNewLabel_5.setBounds(10, 450, 150, 100);
		panel_2.add(lblNewLabel_5);
	}
	
	private class ScheduleTask extends TimerTask {
        public void run() {
        	lblNewLabel_6.setIcon(new ImageIcon("D:\\pictures\\lakers.jpg"));
        	try {
        		Thread.sleep(3000);
        		} 
        	catch (InterruptedException e) {
        		e.printStackTrace();
        	}
        	lblNewLabel_6.setIcon(new ImageIcon("D:\\pictures\\ROYALTY2.jpg"));
        	try {
        		Thread.sleep(3000);
        		} 
        	catch (InterruptedException e) {
        		e.printStackTrace();
        	}
        	lblNewLabel_6.setIcon(new ImageIcon("D:\\pictures\\WADE2.jpg"));
        	try {
        		Thread.sleep(3000);
        		} 
        	catch (InterruptedException e) {
        		e.printStackTrace();
        	}
            	
        	
        	
        	}
        }

}
