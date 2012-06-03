package exercise_09;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.math.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class RecursiveTriangles extends JPanel{

	public void drawTriangle(Graphics g, int[] xPoints, int[] yPoints, int repeats ) {
		repeats--;
		if(repeats != 0) {
			switch (repeats) {
			case 1:
				g.setColor(new Color(130,0,200));
				break;
			case 2:
				g.setColor(new Color(200,0,160));
				break;
			case 3:
				g.setColor(new Color(255,0,130));
				break;
			case 4:
				g.setColor(new Color(255,0,0));
				break;
			case 5:
				g.setColor(new Color(255,170,0));
				break;
			case 6:
				g.setColor(new Color(255,255,0));
				break;
			}
			int[] newXPoints = {(xPoints[0]+xPoints[1])/2, (xPoints[2]+xPoints[1])/2, (xPoints[0]+xPoints[2])/2 };
			int[] newYPoints = {(yPoints[0]+yPoints[1])/2, (yPoints[2]+yPoints[1])/2, (yPoints[0]+yPoints[2])/2 };
			g.fillPolygon(newXPoints, newYPoints, xPoints.length);
			int[] xFirst = {newXPoints[0], xPoints[1], newXPoints[1]};
			int[] yFirst = {newYPoints[0], yPoints[1], newYPoints[1]};
			int[] xSecond = {xPoints[0], newXPoints[0], newXPoints[2]};
			int[] ySecond = {yPoints[0], newYPoints[0], newYPoints[2]};
			int[] xThird = {newXPoints[2], newXPoints[1], xPoints[2]};
			int[] yThird = {newYPoints[2], newYPoints[1], yPoints[2]};
			drawTriangle(g, xFirst, yFirst, repeats);
			drawTriangle(g, xSecond, ySecond, repeats);
			drawTriangle(g, xThird, yThird, repeats);
		}
	}
	
	public void paint(Graphics g) {
		int width = this.getSize().width;
		int height = this.getSize().height;
		int tWidth = (int) (height*2*(1/Math.sqrt(3)));
		int xPoints[] = {width/2 - tWidth/2, width/2, width/2 + tWidth/2};
		int yPoints[] = {height, 0, height};
		if(width < height*1.15) {
			int tHeight = (int) (width*0.5*Math.sqrt(3));
			xPoints[0] = 0;
			xPoints[1] = width/2;
			xPoints[2] = width;
			yPoints[0] = height/2 + tHeight/2;
			yPoints[1] = height/2 - tHeight/2;
			yPoints[2] = height/2 + tHeight/2;
		}
		g.setColor(Color.black);
		g.fillPolygon(xPoints, yPoints, xPoints.length);
		g.setColor(Color.white);
		drawTriangle(g, xPoints, yPoints, 7);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new RecursiveTriangles());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
		frame.setVisible(true);
	}

}