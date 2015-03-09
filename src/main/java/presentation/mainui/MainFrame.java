package presentation.mainui;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.theme.SubstanceAquaTheme;

import presentation.teamsui.CHIFrame;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.CardLayout;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

public class MainFrame {
	public JFrame frmNba;

	private Timer timer;
	private final int INITIAL_DELAY = 100;
    private final int PERIOD_INTERVAL = 10;
    private static JLabel changeLabel;
    private final JLabel GIFLabel1 = new JLabel("");
    
    private static JLabel lblNewLabel_1;
    private static JLabel lblNewLabel_2;
    private static JLabel lblNewLabel_3;
    private static JLabel lblNewLabel_4;
    private static JLabel lblNewLabel_5;
    
    public static boolean flag=true;
    
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
		frmNba.setIconImage(Toolkit.getDefaultToolkit().getImage("pictures\\NBA.jpg"));
		frmNba.setTitle("NBA");
		frmNba.setBounds(100, 100, 1500, 1000);
		frmNba.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNba.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(440, 100, 600, 200);
		panel.setOpaque(false);
		frmNba.getContentPane().add(panel);
		panel.setLayout(null);
		
		changeLabel = new JLabel("");
		changeLabel.setIcon(new ImageIcon("pictures\\Kobe.jpg"));
		changeLabel.setBounds(0, 0, 600, 200);
		panel.add(changeLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(250, 300, 1000, 100);
		frmNba.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel midLabel = new JLabel("");
		midLabel.setIcon(new ImageIcon("pictures\\middle.jpg"));
		midLabel.setBounds(0, 0, 1000, 100);
		panel_1.add(midLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(250, 400, 1000, 550);
		frmNba.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("pictures\\CHI.jpg"));
		lblNewLabel_1.setBounds(10, 10, 150, 100);
		panel_2.add(lblNewLabel_1);
		lblNewLabel_1.addMouseListener(new MouseListener(){

			public void mouseClicked(java.awt.event.MouseEvent e) {
				try {
					frmNba.setVisible(false);
					CHIFrame window = new CHIFrame();
					window.chiFrame.setVisible(true);
					MainFrame.flag=false;
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_1.setIcon(new ImageIcon("pictures\\CHI2.jpg"));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_1.setIcon(new ImageIcon("pictures\\CHI.jpg"));
			}});
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("pictures\\CLE.jpg"));
		lblNewLabel_2.setBounds(10, 120, 150, 100);
		panel_2.add(lblNewLabel_2);
		lblNewLabel_2.addMouseListener(new MouseListener(){

			public void mouseClicked(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_2.setIcon(new ImageIcon("pictures\\CLE2.jpg"));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_2.setIcon(new ImageIcon("pictures\\CLE.jpg"));
			}});
		
		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("pictures\\DET.jpg"));
		lblNewLabel_3.setBounds(10, 230, 150, 100);
		panel_2.add(lblNewLabel_3);
		lblNewLabel_3.addMouseListener(new MouseListener(){

			public void mouseClicked(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_3.setIcon(new ImageIcon("pictures\\DET2.jpg"));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_3.setIcon(new ImageIcon("pictures\\DET.jpg"));
			}});
		
		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("pictures\\IND.jpg"));
		lblNewLabel_4.setBounds(10, 340, 150, 100);
		panel_2.add(lblNewLabel_4);
		lblNewLabel_4.addMouseListener(new MouseListener(){

			public void mouseClicked(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_4.setIcon(new ImageIcon("pictures\\IND2.jpg"));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_4.setIcon(new ImageIcon("pictures\\IND.jpg"));
			}});
		
		lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon("pictures\\MIL.jpg"));
		lblNewLabel_5.setBounds(10, 450, 150, 100);
		panel_2.add(lblNewLabel_5);
		lblNewLabel_5.addMouseListener(new MouseListener(){

			public void mouseClicked(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseEntered(java.awt.event.MouseEvent e) {
				lblNewLabel_5.setIcon(new ImageIcon("pictures\\MIL2.jpg"));
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				lblNewLabel_5.setIcon(new ImageIcon("pictures\\MIL.jpg"));
			}});
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(250, 180, 160, 120);
		frmNba.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		GIFLabel1.setIcon(new ImageIcon("pictures\\Jordan.jpg"));
		GIFLabel1.setBounds(0, 0, 160, 120);
		panel_3.add(GIFLabel1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(1070, 167, 180, 133);
		frmNba.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		JLabel GIFLabel2 = new JLabel("");
		GIFLabel2.setIcon(new ImageIcon("pictures\\KobeGIF.jpg"));
		GIFLabel2.setBounds(0, 0, 180, 133);
		panel_4.add(GIFLabel2);
	}
	
	//图片循环切换线程
	private class ScheduleTask extends TimerTask {
		
        public void run() {
        	while(flag){
        		changeLabel.setIcon(new ImageIcon("pictures\\Kobe.jpg"));
            	try {
            		Thread.sleep(3000);
            		} 
            	catch (InterruptedException e) {
            		e.printStackTrace();
            	}
            	changeLabel.setIcon(new ImageIcon("pictures\\ROYALTY.jpg"));
            	try {
            		Thread.sleep(3000);
            		} 
            	catch (InterruptedException e) {
            		e.printStackTrace();
            	}
            	changeLabel.setIcon(new ImageIcon("pictures\\WADE.jpg"));
            	try {
            		Thread.sleep(3000);
            		} 
            	catch (InterruptedException e) {
            		e.printStackTrace();
            	}
    		}
        	
            	
        	
        	
        	}
        }

}
