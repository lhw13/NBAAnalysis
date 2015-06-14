package vo;

import java.util.ArrayList;

public class PlayOffListVO {
	public PlayOffListVO(ArrayList<PlayoffVO> playOffW,
			ArrayList<PlayoffVO> playOffE, ArrayList<PlayoffVO> finals) {
		super();
		this.playOffW = playOffW;
		this.playOffE = playOffE;
		this.finals = finals;
	}
	ArrayList<PlayoffVO> playOffW;//西部
	ArrayList<PlayoffVO> playOffE;//东部
	ArrayList<PlayoffVO> finals;//只有第0项有值
	public ArrayList<PlayoffVO> getPlayOffW() {
		return playOffW;
	}
	public void setPlayOffW(ArrayList<PlayoffVO> playOffW) {
		this.playOffW = playOffW;
	}
	public ArrayList<PlayoffVO> getPlayOffE() {
		return playOffE;
	}
	public void setPlayOffE(ArrayList<PlayoffVO> playOffE) {
		this.playOffE = playOffE;
	}
	public ArrayList<PlayoffVO> getFinals() {
		return finals;
	}
	public void setFinals(ArrayList<PlayoffVO> finals) {
		this.finals = finals;
	}
}
