/*Waly M Z Karim, wkarim, wkarim@u.rochester.edu, Ehteshamul Haque, ehaque,  ehaque@u.rochester.edu, Kudakwashe Rumawu, krumawu, krumawu@u.rochester.edu*/

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Ghost extends Entity{
	
	BufferedImage[] animations;
	private int aniTick=25, aniSpeed=25, aniIndex=0;
	private float ghostSpeed=-2.5f;
	private static Rectangle2D.Float smallGhost;
	private static Rectangle2D.Float medGhost;
	private static Rectangle2D.Float bigGhost;
	private static Random rand;
	//private static Player player;

	public Ghost(float x, float y, int width, int height) {
		super(x, y, width, height);
		loadAnimations();
		initHitbox(x,y,(int)(20*Game.SCALE),(int)(27*Game.SCALE));
		
		smallGhost=new Rectangle2D.Float(x, y, 20*Game.SCALE, 28*Game.SCALE);
		medGhost=new Rectangle2D.Float(x, y, 40*Game.SCALE, 55*Game.SCALE);
		bigGhost=new Rectangle2D.Float(x, y, 80*Game.SCALE, 111*Game.SCALE);
		
	}

	public void update(){
		updateAnimationTick();
		updatePos();
	}
	
	public void render(Graphics g) {
		g.drawImage(animations[aniIndex], (int)(hitbox.x), (int)(hitbox.y), width, height, null);
		//drawHitbox(g);
	}

	private void updatePos() {
		hitbox.x+=ghostSpeed;
		
	}

	private void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= 5) { 
				aniIndex = 0;
			}

		}
		
	}

	private void loadAnimations() {
		BufferedImage img=LoadSave.GetSpriteAtlas(LoadSave.GHOST);
		animations = new BufferedImage[6];
		for(int i=0; i<6; i++) {
			animations[i]=img.getSubimage(i*20, 0, 20, 28);
		}
		
	}
	public float getGhostSpeed() {
		return ghostSpeed;
	}

	public void setGhostSpeed(float ghostSpeed) {
		this.ghostSpeed = ghostSpeed;
	}
	
	public void multiGhostXPos() {
		this.hitbox.x=hitbox.x+(int)(0.25*Game.GAME_WIDTH);
	}
	
	public static Ghost[][] GhostLoader(Ghost[][] ghosts){
		rand = new Random();

		for(int i=0;i<20;i++) {
			int yRandom=587-rand.nextInt((int)(28*Game.SCALE));
			if(i>=12) {
				int xRandom=rand.nextInt((int)(Game.GAME_WIDTH-Game.GAME_WIDTH/2))+(int)(Game.GAME_WIDTH/2);
				ghosts[0][i]=new Ghost(xRandom, yRandom, (int)(20*Game.SCALE),(int)(28*Game.SCALE));
			}else {
				ghosts[0][i]= new Ghost(Game.GAME_WIDTH,yRandom, (int)(20*Game.SCALE),(int)(28*Game.SCALE));
			}
			ghosts[0][i].setGhostSpeed(ghosts[0][i].getGhostSpeed()-.05f);
		}
		for(int i=0; i<20; i++) {
			int xRandom = rand.nextInt((int)(Game.GAME_WIDTH-Game.GAME_WIDTH*0.25f))+(int)(Game.GAME_WIDTH*0.25f);
			if(i>=14) {
				int yRandom=530-rand.nextInt((int)(28*Game.SCALE));
				ghosts[1][i]=new Ghost(xRandom, yRandom, (int)(40*Game.SCALE),(int)(56*Game.SCALE));
				ghosts[1][i].getHitbox().setRect(medGhost);
			}else {
				int yRandom=587-rand.nextInt((int)(28*Game.SCALE));
				ghosts[1][i]=new Ghost(xRandom, yRandom, (int)(20*Game.SCALE),(int)(28*Game.SCALE));
			}
			ghosts[1][i].setGhostSpeed(ghosts[1][i].getGhostSpeed()-.25f);
		}
		for(int i=0;i<20;i++) {
			int xRandom = rand.nextInt((int)(Game.GAME_WIDTH-Game.GAME_WIDTH*0.5f))+(int)(Game.GAME_WIDTH*0.5f);
			int yRandom=530-rand.nextInt((int)(28*Game.SCALE));
			ghosts[2][i]=new Ghost(xRandom, yRandom, (int)(40*Game.SCALE),(int)(56*Game.SCALE));
			ghosts[2][i].getHitbox().setRect(medGhost);
			
		}
		
		return ghosts;
	}

}
