package uitest;

import static org.junit.Assert.*;

import org.junit.Test;

import presentation.mainui.MainFrame;
import presentation.teamsui.TeamsInfoFrame;
import presentation.teamsui.TeamsSelectionFrame;
import console.Console;

public class UITest {
	@Test
	public void uiTestF()
	{
		MainFrame fm = new MainFrame();
		fm.setPlayersRanking();
		TeamsSelectionFrame ft = new TeamsSelectionFrame();
		ft.setTeamsInfo("MEM");
		assertTrue(true);
	}
}
