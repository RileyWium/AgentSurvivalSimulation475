package cpsc475TermProject;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import jason.asSyntax.*;
import jason.environment.*;
import jason.environment.grid.GridWorldView;
import jason.environment.grid.Location;

public class View extends GridWorldView
{

	private static final long serialVersionUID = 1L;
	Model model;
	
	public View(Model model)
	{
		super(model, "My View", 700);
		this.model = model;
		setVisible(true);
		Font defaultFont = new Font("Arial", Font.BOLD, 15);
		repaint();
		
		//Set the screen size to the window size.
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int)screenSize.getWidth(),(int)screenSize.getHeight());
		
		Graphics G = getGraphics();
		Canvas C  = getCanvas();
		
		C.addMouseListener(new  MouseListener() {
			
			public void mouseReleased(MouseEvent e) 	{}
			public void mousePressed(MouseEvent e) 	{}
			public void mouseExited(MouseEvent e) 	{}
			public void mouseEntered(MouseEvent e) 	{}
			public void mouseClicked(MouseEvent e) 	{

				//TODO: Add ability to place plants in environment.
				
			}
		});
	}
	
	@Override
	public void draw(java.awt.Graphics g, int x, int y, int object) {
		if(object == model.PLANT) {
			BufferedImage img;
			try {
				img = ImageIO.read(getClass().getResourceAsStream("plant.png"));
				g.drawImage(img,x*cellSizeW,y*cellSizeH,this);
			} catch (Exception e) {
				e.printStackTrace();
			}
			super.draw(g, x, y, 8);
		}
		if(object == model.VILLAGE) {
			BufferedImage img;
			try {
				img = ImageIO.read(getClass().getResourceAsStream("hut.png"));
				g.drawImage(img,x*cellSizeW,y*cellSizeH,this);
			} catch (Exception e) {
				e.printStackTrace();
			}
			super.draw(g, x, y, 16);
		}
		
	}
	
	
	@Override
	public void drawAgent(java.awt.Graphics g, int x, int y, java.awt.Color c, int id) {
		if (id == 0 || id == 1 || id == 2) {
			BufferedImage img;
			try {
				img = ImageIO.read(getClass().getResourceAsStream("Villager.png"));
				g.drawImage(img,x*cellSizeW,y*cellSizeH,this);
			} catch (Exception e) {
				e.printStackTrace();
			}
			super.drawAgent(g, x, y, new Color(1f,1f,1f,0f ), id);

		}
		else if(id == 3 || id == 4 || id == 5){
			BufferedImage img;
			try {
				img = ImageIO.read(getClass().getResourceAsStream("deer.png"));
				g.drawImage(img,x*cellSizeW,y*cellSizeH,this);
			} catch (Exception e) {
				e.printStackTrace();
			}
			super.drawAgent(g, x, y, new Color(1f,1f,1f,0f ), id);
		}
	}
	
}
