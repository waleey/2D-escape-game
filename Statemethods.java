/*Waly M Z Karim, wkarim, wkarim@u.rochester.edu, Ehteshamul Haque, ehaque,  ehaque@u.rochester.edu, Kudakwashe Rumawu, krumawu, krumawu@u.rochester.edu*/

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface Statemethods {
	public void update();

	public void draw(Graphics g);

	public void mouseClicked(MouseEvent e);

	public void mousePressed(MouseEvent e);

	public void mouseReleased(MouseEvent e);

	public void mouseMoved(MouseEvent e);

	public void keyPressed(KeyEvent e);

	public void keyReleased(KeyEvent e);

}
