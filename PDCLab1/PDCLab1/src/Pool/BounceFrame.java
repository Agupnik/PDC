package Pool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BounceFrame extends JFrame {
    private final BallCanvas canvas;
    public static final int WIDTH = 500;
    public static final int HEIGHT = 400;
    //pockets coordinates
    int[] xPocketsArray = new int[] {20, 20, WIDTH - 60, WIDTH - 60};
    int[] yPocketsArray = new int[] {20, HEIGHT - 120, 20, HEIGHT - 120};

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Pool.Bounce programm");

        ArrayList<Pocket> pockets = Pocket.PocketFactory(xPocketsArray, yPocketsArray, 4);
        this.canvas = new BallCanvas(pockets);

        System.out.println("In Frame Thread name = "
                + Thread.currentThread().getName());
        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);

        JButton buttonStart = new JButton("Start");
        JButton buttonRedVsBlue = new JButton("Red VS Blue");
        JButton buttonRedAndBlue = new JButton("Red And Blue");
        JButton buttonJoinDemo = new JButton("Join");
        JButton buttonStop = new JButton("Stop");
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Ball b = new Ball(canvas, xPocketsArray, yPocketsArray);
                canvas.add(b);

                BallThread thread = new BallThread(b, null);
                thread.start();
                System.out.println("Thread name = " +
                        thread.getName());
            }
        });
        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonRedVsBlue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                canvas.balls = Ball.priorityBallsFactory(
                        canvas,
                        xPocketsArray,
                        yPocketsArray,
                        15000,
                        1);

                for(int i = 0; i < canvas.balls.size(); i++){
                    BallThread thread = new BallThread(canvas.balls.get(i));
                    thread.start();
                }
            }
        });
        buttonJoinDemo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Ball b1 = new Ball(canvas, xPocketsArray, yPocketsArray, Color.RED);
                Ball b2 = new Ball(canvas, xPocketsArray, yPocketsArray, Color.CYAN);
                Ball b3 = new Ball(canvas, xPocketsArray, yPocketsArray, Color.GREEN);
                Ball b4 = new Ball(canvas, xPocketsArray, yPocketsArray, Color.MAGENTA);
                Ball b5 = new Ball(canvas, xPocketsArray, yPocketsArray, Color.YELLOW);
                Ball b6 = new Ball(canvas, xPocketsArray, yPocketsArray, Color.PINK);
                canvas.add(b1);
                canvas.add(b2);
                canvas.add(b3);
                canvas.add(b4);
                canvas.add(b5);
                canvas.add(b6);

                BallThread lastThread = null;
                for(int i = 0; i < canvas.balls.size(); i++){
                    BallThread thread = new BallThread(canvas.balls.get(i), lastThread);
                    lastThread = thread;
                    thread.start();
                }
            }
        });
        buttonRedAndBlue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < 100; i++){
                    Ball b1 = new Ball(canvas, xPocketsArray, yPocketsArray, Priority.High);
                    Ball b2 = new Ball(canvas, xPocketsArray, yPocketsArray, Priority.Low);
                    BallThread thread1 = new BallThread(b1);
                    BallThread thread2 = new BallThread(b2);
                    canvas.add(b1);
                    canvas.add(b2);
                    thread1.start();
                    thread2.start();
                }
            }
        });
        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonStop);
        buttonPanel.add(buttonRedAndBlue);
        buttonPanel.add(buttonRedVsBlue);
        buttonPanel.add(buttonJoinDemo);

        content.add(buttonPanel, BorderLayout.SOUTH);
    }
}