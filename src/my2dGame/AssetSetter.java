
package my2dGame;

import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        // Keys
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 24 * gp.tileSize;
        gp.obj[0].worldY = 15 * gp.tileSize;
        
        
        
        gp.obj[1] = new OBJ_Key();
        gp.obj[1].worldX = 27 * gp.tileSize;
        gp.obj[1].worldY = 46 * gp.tileSize;
        
        gp.obj[8] = new OBJ_Key();
        gp.obj[8].worldX = 22 * gp.tileSize;
        gp.obj[8].worldY = 52 * gp.tileSize;



   
        // Doors
        gp.obj[2] = new OBJ_Door();
        gp.obj[2].worldX = 12 * gp.tileSize;
        gp.obj[2].worldY = 30 * gp.tileSize;
        
        
        
        gp.obj[7] = new OBJ_Door();
        gp.obj[7].worldX = 24 * gp.tileSize;
        gp.obj[7].worldY = 26 * gp.tileSize;
        
        
        gp.obj[3] = new OBJ_Door();
        gp.obj[3].worldX = 12 * gp.tileSize;
        gp.obj[3].worldY = 27 * gp.tileSize;
        
        
        
        

      

        // Chests
        gp.obj[4] = new OBJ_Chest();
        gp.obj[4].worldX = 12 * gp.tileSize;
        gp.obj[4].worldY = 33 * gp.tileSize;

    
        
        
        gp.obj[6] = new OBJ_Boots();
        gp.obj[6].worldX = 12 * gp.tileSize;
        gp.obj[6].worldY = 29 * gp.tileSize;
        
    }
}
