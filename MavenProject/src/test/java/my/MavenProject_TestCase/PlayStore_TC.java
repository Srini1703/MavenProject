package my.MavenProject_TestCase;

import org.testng.annotations.Test;

import Mobile_Pages.PlayStore;

public class PlayStore_TC {
	
	@Test
	void MyTEst() throws Exception{
		PlayStore ps = new PlayStore();
		ps.pageValidations();
	}
}
