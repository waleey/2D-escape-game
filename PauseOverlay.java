/*Waly M Z Karim, wkarim, wkarim@u.rochester.edu, Ehteshamul Haque, ehaque,  ehaque@u.rochester.edu, Kudakwashe Rumawu, krumawu, krumawu@u.rochester.edu*/
import java.awt.Graphics;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;



public class PauseOverlay {

	private Playing playing;
	private BufferedImage backgroundImg;
	private int bgX, bgY, bgW, bgH;
	private UrmButton menuB, exitB;

	public PauseOverlay(Playing playing) {
		this.playing = playing;
		loadBackground();
		createUrmButtons();
		}

	private void createUrmButtons() {
		int menuX = (int) (313 * Game.SCALE);
		int unpauseX = (int) (462 * Game.SCALE);
		int bY = (int) (325 * Game.SCALE);

		menuB = new UrmButton(menuX, bY, Constants.UI.URMButtons.URM_SIZE, Constants.UI.URMButtons.URM_SIZE, 2);
		exitB = new UrmButton(unpauseX, bY, Constants.UI.URMButtons.URM_SIZE, Constants.UI.URMButtons.URM_SIZE, 0);

	}

	private void loadBackground() {
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
		bgW = (int) (backgroundImg.getWidth() * Game.SCALE);
		bgH = (int) (backgroundImg.getHeight() * Game.SCALE);
		bgX = Game.GAME_WIDTH / 2 - bgW / 2;
		bgY = (int) (25 * Game.SCALE);

	}

	public void update() {

		menuB.update(); 
		exitB.update();

	}

	public void draw(Graphics g) {
		g.drawImage(backgroundImg, bgX, bgY, bgW, bgH, null);
		menuB.draw(g);
		exitB.draw(g);
	}

	public void mouseDragged(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

		if (isIn(e, menuB))
			menuB.setMousePressed(true);
		else if (isIn(e, exitB))
			exitB.setMousePressed(true);
	}

	public void mouseReleased(MouseEvent e) {
		if (isIn(e, menuB)) {
			if (menuB.isMousePressed()) {
				Gamestate.state = Gamestate.MENU;
				playing.unpauseGame(); //reset everything here
			}
		} else if (isIn(e, exitB)) {
			if (exitB.isMousePressed()) {
				playing.unpauseGame();
				System.exit(0);
			}
			
		}


		menuB.resetBools();
		exitB.resetBools();

	}

	public void mouseMoved(MouseEvent e) {
		menuB.setMouseOver(false);
		exitB.setMouseOver(false);
		
		if (isIn(e, menuB))
			menuB.setMouseOver(true);
		else if (isIn(e, exitB))
			exitB.setMouseOver(true);

	}

	private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}

}
