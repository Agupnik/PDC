package Pool;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Pocket {
    private static final int XSIZE = 22;
    private static final int YSIZE = 22;
    private final int xCoordinates;
    private final int yCoordinates;


    public Pocket(int x, int y){
        this.xCoordinates = x;
        this.yCoordinates = y;
    }

    public static ArrayList<Pocket> PocketFactory(int[] x_coords, int[] y_coords, int count){
        ArrayList<Pocket> pockets = new ArrayList<>();
        for(int i=0; i<count; i++){
            pockets.add(new Pocket(x_coords[i], y_coords[i]));
        }
        return pockets;
    }

    public void draw (Graphics2D g){
        g.setColor(Color.black);
        g.fill(new Ellipse2D.Double(xCoordinates,yCoordinates,XSIZE,YSIZE));
    }

}
