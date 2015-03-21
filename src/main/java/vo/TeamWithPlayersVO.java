package vo;

import java.util.ArrayList;

public class TeamWithPlayersVO {
	public TeamWithPlayersVO(TeamVO team, ArrayList<PlayerVO> players) {
		super();
		this.team = team;
		this.players = players;
	}

	TeamVO team;
	ArrayList<PlayerVO> players = new ArrayList<PlayerVO>();

	public TeamVO getTeam() {
		return team;
	}

	public ArrayList<PlayerVO> getPlayers() {
		return players;
	}
}
