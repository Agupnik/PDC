package Pool;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BallCanvas extends JPanel {
    public ArrayList<Ball> balls = new ArrayList<>();
    private final ArrayList<Pocket> pockets;
    private final JLabel label = new JLabel();
    public int ballsInPocket;

    public void add(Ball b){
        this.balls.add(b);
    }

    public BallCanvas(ArrayList<Pocket> pockets){
        label.setText("Balls in pockets: " + ballsInPocket);
        add(label, BorderLayout.SOUTH);
        this.pockets = pockets;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        balls.removeIf(b -> !b.isAlive());
        for(int i=0; i<balls.size(); i++){
            Ball b = balls.get(i);
            b.draw(g2);
        }
        for(int i=0; i<pockets.size(); i++){
            Pocket p = pockets.get(i);
            p.draw(g2);
        }
        label.setText("Balls in pocket: " + ballsInPocket);
        //repaint element to set pocket count
        repaint();
    }
}