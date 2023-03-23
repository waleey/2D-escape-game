/*Waly M Z Karim, wkarim, wkarim@u.rochester.edu, Ehteshamul Haque, ehaque,  ehaque@u.rochester.edu, Kudakwashe Rumawu, krumawu, krumawu@u.rochester.edu*/


import java.awt.Color;

import java.util.Random;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;



public class Playing extends State implements Statemethods {
	private Player player;
	private LevelManager levelManager;
	private PauseOverlay pauseOverlay;
	private GameOverOverlay gameOverOverlay;
	private boolean gameOver = false;
	private boolean paused = false;
	BufferedImage[][] levelTitle;
	BufferedImage bg1, bg2,bg3;

	
	Ghost[][] ghosts;
	private int ghostIndex=0;
	private int ghostLevelIndex=0;
	private boolean multiGhostMode=false;
	private boolean playerHealthLock=false;
	
	Medipack[] medipacks;
	private int mediIndex=0;
	private boolean mediActive=false; 
	private boolean mediLock=false;
	private boolean mediDisappear=false;

	private int xLvlOffset;
	private int leftBorder = (int) (0.2 * Game.GAME_WIDTH);
	private int rightBorder = (int) (0.8 * Game.GAME_WIDTH);
	private int lvlTilesWide = LoadSave.GetLevelData()[0].length;
	private int maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
	private int maxLvlOffsetX = maxTilesOffset * Game.TILES_SIZE;

	public Playing(Game game) {
		super(game);
		initClasses();
		SoundHandler.RunMusic("menu.wav");
		
	}

	private void initClasses() {
		levelManager = new LevelManager(game);
		player = new Player(100, 200, (int) (64 * Game.SCALE), (int) (40 * Game.SCALE));
		player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
		ghosts = Ghost.GhostLoader(new Ghost[3][20]);
		medipacks=Medipack.MediLoader(new Medipack[5]);
		pauseOverlay = new PauseOverlay(this);
		gameOverOverlay= new GameOverOverlay(this);
		getLevelTitle();
		loadBg();
		
	}

	@Override
	public void update() {
		//ghostLevelIndex=2; //testing purpose. Will be removed
		if (!paused && !gameOver) {
			levelManager.update();
			checkHealthUpgrade();
			player.update();
			checkCloseToBorder();
			
			ghosts[ghostLevelIndex][ghostIndex].update();
			if(multiGhostMode)
				if(ghosts[ghostLevelIndex][ghostIndex].getHitbox().x<=(int)(0.35*Game.GAME_WIDTH))
					ghosts[ghostLevelIndex][ghostIndex+1].update();
			updateGhostIndex();
			checkEatenByGhost();
			
			updateMedipack();
			
			setMedipackRendering();
			
		} else if(paused){
			resetPlaying();
			pauseOverlay.update();

			
		} else if(gameOver) {
			resetPlaying();
			gameOverOverlay.update();

			
		}
		
	}
	
	private void updateMedipack() {
		if((ghostLevelIndex==0&&ghostIndex==3)||(ghostLevelIndex==0&&ghostIndex==15)||(ghostLevelIndex==1&&ghostIndex==4)||(ghostLevelIndex==1&&ghostIndex==19)||(ghostLevelIndex==1&&ghostIndex==15)||(ghostLevelIndex==2&&ghostIndex==7)||(ghostLevelIndex==2&&ghostIndex==13)||(ghostLevelIndex==2&&ghostIndex==19)) {
			mediActive=true;
			if(medipacks[mediIndex].getHitbox().x<=0) {
				if(mediIndex<4) {
					mediIndex++;
				}
				mediActive=false;
			}
		}
		if(mediActive)
			medipacks[mediIndex].update();	
	}

	public void updateGhostIndex() {
		if(ghostLevelIndex>=2) {
			if(ghostIndex<18)
				multiGhostMode=true;
			else
				multiGhostMode=false;
		}
		if(ghosts[ghostLevelIndex][ghostIndex].getHitbox().x<=0) {
			if(ghostIndex<ghosts[ghostLevelIndex].length-1) {
				ghostIndex++;
			}else {
				if(ghostLevelIndex<ghosts.length-1) {
					ghostLevelIndex++;
					ghostIndex=0;
				}else {
					gameOver=true;
					multiGhostMode=false;
				}
			}
		}
	}
	public void checkEatenByGhost(){
		if(HelpMethods.DoesCollide(ghosts[ghostLevelIndex][ghostIndex].getHitbox(),player.getHitbox())) {
			if(!playerHealthLock) {
				player.setPlayeHealth(player.getPlayeHealth()-1);
				playerHealthLock=true;
			}
			if(player.getPlayeHealth()<=0) {
				paused=!paused;
			}
		}else {
			playerHealthLock=false;
		}
	}
	
	public void checkHealthUpgrade() {
		if(HelpMethods.DoesCollide(medipacks[mediIndex].getHitbox(),player.getHitbox())) {
			if(!mediLock) {
				player.setPlayeHealth(5);
				mediLock=true;
			}
		}else {
			mediLock=false;
		}
	}

	private void checkCloseToBorder() {
		int playerX = (int) player.getHitbox().x;
		int diff = playerX - xLvlOffset;

		if (diff > rightBorder)
			xLvlOffset += diff - rightBorder;
		else if (diff < leftBorder)
			xLvlOffset += diff - leftBorder;

		if (xLvlOffset > maxLvlOffsetX)
			xLvlOffset = maxLvlOffsetX;
		else if (xLvlOffset < 0)
			xLvlOffset = 0;

	}

	@Override
	public void draw(Graphics g) {
		switch(ghostLevelIndex) {
		case 0:
			g.drawImage(bg1, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
			break;
		case 1:
			g.drawImage(bg3, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
			break;
		case 2:
			g.drawImage(bg2, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
			break;
			
		}
		//g.drawImage(bg, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
		levelManager.draw(g, xLvlOffset);
		player.render(g, xLvlOffset);
		
		ghosts[ghostLevelIndex][ghostIndex].render(g);	
		if(multiGhostMode) {
			if(ghosts[ghostLevelIndex][ghostIndex].getHitbox().x<=(int)(0.35*Game.GAME_WIDTH)) {
				ghosts[ghostLevelIndex][ghostIndex+1].render(g);
				}	
			}
		
		if(mediActive) {
			if(!mediDisappear)
				medipacks[mediIndex].render(g);
		}
		
		switch (ghostLevelIndex){
		case 0:
			g.drawImage(levelTitle[0][0], 100,100, (int)(160*Game.SCALE),(int)(20*Game.SCALE),null);
			break;
		case 1:
			g.drawImage(levelTitle[1][0], 100,100, (int)(160*Game.SCALE),(int)(20*Game.SCALE),null);
			break;
		case 2: 
			g.drawImage(levelTitle[2][0], 100,100, (int)(160*Game.SCALE),(int)(20*Game.SCALE),null);
			break;
		default:
			gameOver=!gameOver;			
		}
		if (paused) {
			g.setColor(new Color(0, 0, 0, 150));
			g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
			pauseOverlay.draw(g);
		}
		else if(gameOver) {
			g.setColor(new Color(0, 0, 0, 150));
			g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
			gameOverOverlay.draw(g);
		}
	}
	
	public void setMedipackRendering() {
		if(HelpMethods.DoesCollide(medipacks[mediIndex].getHitbox(),player.getHitbox())) {
			mediDisappear=true;
		}else if(medipacks[mediIndex].getHitbox().x<-25) {
			mediDisappear=false;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			player.setAttacking(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			player.setLeft(true);
			break;
		case KeyEvent.VK_RIGHT:
			player.setRight(true);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(true);
			break;
		case KeyEvent.VK_DOWN:
			player.setDuck(true);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			player.setLeft(false);
			break;
		case KeyEvent.VK_RIGHT:
			player.setRight(false);
			break;
		case KeyEvent.VK_DOWN:
			player.setDuck(false);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(false);
			break;
		}

	}
	public void resetPlaying() {

		player.resetDirBooleans();
		player.getHitbox().x=100;
		player.getHitbox().y=400;
		player.setPlayeHealth(5);
		player.setPlayerScore(0);
		playerHealthLock=false;
		player.setInAir(true);
		
		ghostIndex=0;
		ghostLevelIndex=0;
		multiGhostMode=false;
		ghosts = Ghost.GhostLoader(new Ghost[3][20]);

		mediIndex=0;
		mediLock=false;
		mediDisappear=false;
		mediActive=false;
	}

	public void mouseDragged(MouseEvent e) {
		if (paused)
			pauseOverlay.mouseDragged(e);
		else if(gameOver)
			gameOverOverlay.mouseDragged(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (paused)
			pauseOverlay.mousePressed(e);
		else if(gameOver)
			gameOverOverlay.mousePressed(e);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (paused)
			pauseOverlay.mouseReleased(e);
		else if(gameOver)
			gameOverOverlay.mouseReleased(e);

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (paused)
			pauseOverlay.mouseMoved(e);
		else if(gameOver)
			gameOverOverlay.mouseMoved(e);

	}

	public void unpauseGame() {
		paused = false;
	}
	
	public void notGameOver() {
		gameOver=false;
	}

	public void windowFocusLost() {
		player.resetDirBooleans();
	}

	public Player getPlayer() {
		return player;
	}
	public int getGhostLevelIndex() {
		return ghostLevelIndex;
	}

	public void setGhostLevelIndex(int ghostLevelIndex) {
		this.ghostLevelIndex = ghostLevelIndex;
	}

	public boolean getGameOver() {
		return gameOver;
	}
	public void getLevelTitle(){
		BufferedImage levelOne = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ONE_TITLE);
		BufferedImage levelTwo = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_TWO_TITLE);
		BufferedImage levelThree = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_THREE_TITLE);
		
		levelTitle=new BufferedImage[3][1];
	
		levelTitle[0][0]=levelOne.getSubimage(125, 195, 90, 15);
		levelTitle[1][0]=levelTwo.getSubimage(125, 195, 80, 15);
		levelTitle[2][0]=levelThree.getSubimage(125, 195, 65,15);
			
		
			
	}
	public void loadBg() {
		bg1 = LoadSave.GetSpriteAtlas(LoadSave.BG1);
		bg2 = LoadSave.GetSpriteAtlas(LoadSave.BG2);
		bg3=LoadSave.GetSpriteAtlas(LoadSave.BG3);
	}
	

}
