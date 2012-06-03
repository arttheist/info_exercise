package exercise_09;
import java.applet.Applet;
import java.awt.*;

public class RecursiveTriangles extends Applet {
	
	public void paint(Graphics g) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int appletHeight = dim.height - 130;
		int appletWidth = dim.width - 20;
		this.resize(appletWidth, appletHeight);
		int[] xPoints = new int[] 
				{50, 100, 150};
		int[] yPoints = new int[] 
				{100, 25, 100};
		 g.drawPolygon(xPoints,yPoints,3);
		 g.fillPolygon (xPoints, yPoints, 3);
	}

}