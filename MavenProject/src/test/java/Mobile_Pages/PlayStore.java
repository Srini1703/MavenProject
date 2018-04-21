package Mobile_Pages;

import OR.OR_PlayStore;
import io.appium.java_client.MobileElement;

public class PlayStore extends OR_PlayStore 
{
	public void pageValidations() throws Exception
	{
		System.out.println(s1);
		//OR_PlayStore.getClass().getAnnotations()[0];
		String temp = PS_Games.toString();
		System.out.println(temp);
		if(temp.contains("<<<>>>"))
			temp.replace("<<<>>>", "SSS");
	}
}
