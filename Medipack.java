/*Waly M Z Karim, wkarim, wkarim@u.rochester.edu, Ehteshamul Haque, ehaque,  ehaque@u.rochester.edu, Kudakwashe Rumawu, krumawu, krumawu@u.rochester.edu*/

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Medipack extends Entity{
	
	BufferedImage[] animations;
	private float medipackSpeed=-1.0f;
	private int aniTick=25,aniSpeed=25,aniIndex=0;
	private static Random rand;
	
	public Medipack(float x, float y, int width, int height) {
		super(x, y, width, height);
		loadAnimations();
		initHitbox(x,y,(int)(16*Game.SCALE),(int)(16*Game.SCALE));
		
		
	}
	
	public void update() {
		updateAnimationTick();
		updatePos();
	}
	
	public void render(Graphics g) {
		g.drawImage(animations[aniIndex],(int)(hitbox.x),(int)(hitbox.y),width, height, null);
		//drawHitbox(g);
	}

	private void updateAnimationTick() {
		aniTick++;
		if(aniTick>=aniSpeed) {
			aniTick=0;
			aniIndex++;
			if(aniIndex>=5) {
				aniIndex=0;
			}
		}
	}

	private void updatePos() {
		hitbox.x+=medipackSpeed;
		
	}

	private void loadAnimations() {
		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.MEDIPACK);
		animations = new BufferedImage[6];
		for(int i=0; i<6; i++) {
			animations[i]=img.getSubimage(i*8, 0, 8, 8);
		}
		
	}
	
	public static Medipack[] MediLoader(Medipack[] medipack) {
		rand= new Random();
		int yRandom= 520-rand.nextInt((int)(28*Game.SCALE));
		for(int i=0;i<5;i++) {
			medipack[i]=new Medipack(Game.GAME_WIDTH,yRandom, (int)(16*Game.SCALE),(int)(16*Game.SCALE));
		}
		return medipack;
	}

}
