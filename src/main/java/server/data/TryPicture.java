package server.data;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
public class TryPicture {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Picture pic=new Picture();
		JFrame jf=new JFrame();
		JPPNG jp=new JPPNG();
		jp.setImage(pic.getPlayerPortrait("Aaron Brooks"));
		jf.setSize(200, 200);
		jf.add(jp);
		jf.setVisible(true);
	}
	
	static class JPPNG extends JPanel{
		public JPPNG() {
			this.setOpaque(false);
		}
		Image i;
		public void setImage(Image i) {
			this.i = i;
		}
		@Override
		public void paint(Graphics g) {
			g.drawImage(i, 0, 0, null);
			super.paint(g);
		}
	}

}
