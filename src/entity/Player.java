package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import my2dGame.GamePanel;
import my2dGame.KeyHandler;

public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyH;
	public final int screenX;
	public final int screenY;
	public int hasKey=0;
	public String playerName;

	public Player(GamePanel gp, KeyHandler keyH, String playerName) {
		this.gp = gp;
		this.keyH = keyH;
		this.playerName = playerName;

		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX= solidArea.x;
		solidAreaDefaultY=solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;

		setDefaultValues();
		getPlayerImage();

	}

	public void setDefaultValues() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 4;
		direction = "down";

	}

	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/up1_p.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/up2_p.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/down1_p.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/down2_p.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1_p.PNG"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2_p.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1_p.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2_p.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void update() {
		if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
				|| keyH.rightPressed == true) {

			if (keyH.upPressed) {
				direction = "up";

			} else if (keyH.downPressed) {
				direction = "down";

			} else if (keyH.leftPressed) {
				direction = "left";

			} else if (keyH.rightPressed) {
				direction = "right";

			}

			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			 int objIndex= gp.cChecker.checkObject(this, true);
			 pickUpObject(objIndex);

			if (collisionOn == false) {
				switch (direction) {
				case "up":
					worldY -= speed;

					break;
				case "down":
					worldY += speed;

					break;
				case "left":
					worldX -= speed;

					break;
				case "right":
					worldX += speed;
					break;

				}
			}
			spriteCounter++;
			if (spriteCounter > 12) {
				if (spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}

	}
	
	public void pickUpObject(int i) {
		
		if(i!=999) {
			String objectName=gp.obj[i].name;
			
			
			switch(objectName) {
			case "Key":
				gp.playSE(1);
				hasKey++;
				gp.obj[i]=null;
				gp.ui.showMessage("You got a key!");
				break;
				
			case "Door":
				if(hasKey > 0) {
					gp.playSE(3);
					gp.obj[i]=null;
					hasKey--;
					gp.ui.showMessage("You opened the door!");
				}
				else {
					gp.ui.showMessage("You need a key!");
				}
				System.out.println("Key:"+ hasKey);
				break;
				
			case "Boots":
				gp.playSE(2);
				speed +=2;
				gp.obj[i]=null;
				gp.ui.showMessage("Speed up!");
				break;
				
			case "Chest":
				gp.ui.gameFinished=true;
				gp.stopMusic();
				gp.playSE(4);
				break;
			
			}
		}
		
	}

	public void draw(Graphics2D g2) {

		BufferedImage image = null;
		switch (direction) {

		case "up":
			if (spriteNum == 1) {
				image = up1;
			}
			if (spriteNum == 2) {
				image = up2;
			}

			break;

		case "down":
			if (spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down2;
			}

			break;
		case "left":
			if (spriteNum == 1) {
				image = left1;
			}
			if (spriteNum == 2) {
				image = left2;
			}
			break;

		case "right":
			if (spriteNum == 1) {
				image = right1;
			}
			if (spriteNum == 2) {
				image = right2;
			}
			break;
		}

		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		
		g2.setColor(Color.WHITE);
		g2.drawString(playerName, screenX, screenY - 10);

	}

}
