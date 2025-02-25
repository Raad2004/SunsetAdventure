package object;
import java.io.*;

import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject {
	public OBJ_Key() {
		name="Key";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
		}catch(IOException e) {
		e.printStackTrace();
		
		}
	}

}
