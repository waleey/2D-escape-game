/*Waly M Z Karim, wkarim, wkarim@u.rochester.edu, Ehteshamul Haque, ehaque,  ehaque@u.rochester.edu, Kudakwashe Rumawu, krumawu, krumawu@u.rochester.edu*/

import java.awt.Color;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LoadSave {

	public static final String PLAYER_ATLAS = "player_sprites.png";
	public static final String LEVEL_ATLAS = "outside_sprites_custom.png";
	public static final String LEVEL_ONE_DATA = "level_one_data_long_custom.png";
	public static final String MENU_BUTTONS = "button_atlas.png";
	public static final String MENU_BACKGROUND = "menu_background.png";
	public static final String PAUSE_BACKGROUND = "ds.png";
	public static final String SOUND_BUTTONS = "sound_button.png";
	public static final String URM_BUTTONS = "cross.png";
	public static final String VOLUME_BUTTONS = "volume_buttons.png";
	public static final String MENU_BACKGROUND_IMG = "background_menu.png";
	public static final String GHOST = "ghost.png";
	public static final String MEDIPACK ="medipack.png";
	public static final String LEVEL_ONE_TITLE="level01_title.png";
	public static final String LEVEL_TWO_TITLE="level02_title.png";
	public static final String LEVEL_THREE_TITLE="level03_title.png";
	public static final String GAME_OVER = "you+won.png";
	public static final String BG1 = "bg2.jpg";
	public static final String BG2="bg_3.jpg";
	public static final String BG3="bg4.png";
		

	public static BufferedImage GetSpriteAtlas(String fileName) {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
		try {
			img = ImageIO.read(is);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}

	public static int[][] GetLevelData() {
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
		int[][] lvlData = new int[img.getHeight()][img.getWidth()];

		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getRed();
				if (value >= 48)
					value = 0;
				lvlData[j][i] = value;
			}
		return lvlData;

	}
}
