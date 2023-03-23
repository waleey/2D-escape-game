/*Waly M Z Karim, wkarim, wkarim@u.rochester.edu, Ehteshamul Haque, ehaque,  ehaque@u.rochester.edu, Kudakwashe Rumawu, krumawu, krumawu@u.rochester.edu*/
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;



public class Player extends Entity {
	private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 25;
	private int playerAction = Constants.PlayerConstants.RUNNING;
	private boolean moving = false, attacking = false;
	private boolean left, up, right, down, jump, duck=false;
	private float playerSpeed = 1.0f * Game.SCALE;
	private int[][] lvlData;
	private float xDrawOffset = 21 * Game.SCALE;
	private float yDrawOffset = 4 * Game.SCALE;
	private float playerY;
	private int playeHealth=50;
	private int playerScore=0;

	// Jumping / Gravity
	private float airSpeed = 0f;
	private float gravity = 0.04f * Game.SCALE;
	private float jumpSpeed = -2.50f * Game.SCALE;
	private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
	private boolean inAir = false;
	private int jumpIndex=0;

	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		loadAnimations();
		initHitbox(x, y, (int) (20 * Game.SCALE), (int) (27 * Game.SCALE));

	}

	public void update() {
		updatePos();
		updateAnimationTick();
		setAnimation();
		playerScore++;
	}

	public void render(Graphics g, int lvlOffset) {
		g.drawImage(animations[playerAction][aniIndex], (int) (hitbox.x - xDrawOffset) - lvlOffset, (int) (playerY - yDrawOffset), width, height, null);
		g.setColor(Color.GREEN);
		g.drawString("Player Score:"+String.valueOf(playerScore), Game.GAME_WIDTH-300, 100);
		g.drawString("Player Health:"+String.valueOf(playeHealth), Game.GAME_WIDTH-300, 120);
	}

	private void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= Constants.PlayerConstants.GetSpriteAmount(playerAction)) {
				aniIndex = 0;
				attacking = false;
			}

		}

	}

	private void setAnimation() {
		int startAni = playerAction;

		if (moving)
			playerAction = Constants.PlayerConstants.RUNNING;
		else
			playerAction = Constants.PlayerConstants.RUNNING;

		if (inAir) {
			if (airSpeed < 0)
				playerAction = Constants.PlayerConstants.JUMP;
			else
				playerAction = Constants.PlayerConstants.FALLING;
		}

		if (attacking)
			playerAction = Constants.PlayerConstants.ATTACK_1;
		
		if(duck) {
			aniIndex=0;
			playerAction=Constants.PlayerConstants.GROUND;
		}

		if (startAni != playerAction)
			resetAniTick();
	}

	public void resetAniTick() {
		aniTick = 0;
		aniIndex = 0;
	}

	private void updatePos() {
		moving = false;

		if (jump)
			jump();
		
		if(duck) {
			duck();
			jumpIndex++;
		}  else {
			playerY=hitbox.y;
			hitbox.height=28*Game.SCALE;
			if(jumpIndex>0) {
				hitbox.y=hitbox.y-hitbox.height/2;
				jumpIndex=0;
				yDrawOffset=0;
			}
			
		}

		if (!inAir)
			if ((!left && !right) || (right && left))
				return;

		float xSpeed = 0;

		if (left)
			xSpeed -= playerSpeed;
		if (right)
			xSpeed += playerSpeed;

		if (!inAir)
			if (!HelpMethods.IsEntityOnFloor(hitbox, lvlData))
				inAir = true;

		if (inAir) {
			if (HelpMethods.CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
				hitbox.y += airSpeed;
				airSpeed += gravity;
				updateXPos(xSpeed);
			} else {
				hitbox.y = HelpMethods.GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
				if (airSpeed > 0)
					resetInAir();
				else
					airSpeed = fallSpeedAfterCollision;
				updateXPos(xSpeed);
			}

		} else
			updateXPos(xSpeed);
		moving = true;
	}

	private void duck() {
			playerY=587;
			updateHitbox();
	}

	private void jump() {
		if (inAir)
			return;
		inAir = true;
		airSpeed = jumpSpeed;

	}
	

	private void resetInAir() {
		inAir = false;
		airSpeed = 0;

	}

	private void updateXPos(float xSpeed) {
		if (HelpMethods.CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
			hitbox.x += xSpeed;
		} else {
			hitbox.x = HelpMethods.GetEntityXPosNextToWall(hitbox, xSpeed);
		}

	}

	private void loadAnimations() {

		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);

		animations = new BufferedImage[9][6];
		for (int j = 0; j < animations.length; j++)
			for (int i = 0; i < animations[j].length; i++)
				animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);

	}

	public void loadLvlData(int[][] lvlData) {
		this.lvlData = lvlData;
		if (!HelpMethods.IsEntityOnFloor(hitbox, lvlData))
			inAir = true;

	}

	public void resetDirBooleans() {
		left = false;
		right = false;
		up = false;
		down = false;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public void setJump(boolean jump) {
		this.jump = jump;
	}

	public boolean isDuck() {
		return duck;
	}

	public void setDuck(boolean duck) {
		this.duck = duck;
	}

	public int getPlayeHealth() {
		return playeHealth;
	}

	public void setPlayeHealth(int playeHealth) {
		this.playeHealth = playeHealth;
	}
	public int getPlayerAction() {
		return playerAction;
	}

	public void setPlayerAction(int playerAction) {
		this.playerAction = playerAction;
	}

	public void setPlayerScore(int playerScore) {
		this.playerScore=playerScore;
		
	}

	public void setInAir(boolean inAir) {
		this.inAir =inAir;
		
	}

}