package MyProject.MavenProject;

import org.testng.annotations.Test;

public  class Demo {
	String s="CTSABC";
	StringBuilder sb = new StringBuilder(s);
	
	@Test
	void test() {
		System.out.println(sb.reverse());
		String ss = sb.substring(0, 2);
		System.out.println(ss);
	}
}


