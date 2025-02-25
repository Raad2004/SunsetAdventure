package object;
import java.io.*;

import javax.imageio.ImageIO;

public class OBJ_Boots extends SuperObject {
	public OBJ_Boots() {
		name="Boots";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
		}catch(IOException e) {
		e.printStackTrace();
		
		}
	}

}
