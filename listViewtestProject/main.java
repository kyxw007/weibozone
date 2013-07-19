package kyxw007;

import java.util.List;

import kyxw007.zone.current.currentLine;

public class main {
	public static void main(String[] args) {
		qzone zone = new qzone("515779871","b2840255");
		List<kyxw007.qzone.current.currentLine> curr = zone.getCurrent(20);
		for(int i = 0;i<curr.size();i++){
			kyxw007.qzone.current.currentLine currline = curr.get(i);
			System.out.print("\n#######################\n");
			System.out.print("\n"+currline.useName);
			System.out.print("\n"+currline.status);
			
		}

	}

}
