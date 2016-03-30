package Laba6;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

/**
 * Created by Ihor on 28.02.2016.
 */
public class RandomLinesAndDots implements Runnable{

    private String title;
    private int width, height;

    private Display display;
    private BufferStrategy bufferStrategy;
    private Graphics graphics;

    private int x11, y11, x12, y12, x21, y21, x22, y22, x31, y31, x32, y32;
    private int a1, b1, p1, a2, b2, p2, a3, b3, p3;
    private int dots;
    private int[] x;
    private int[] y;

    private Thread thread;
    private boolean running;

    public RandomLinesAndDots(String title, int width, int height) {

        this.title = title;
        this.width = width;
        this.height = height;

        Random random = new Random();

        // Line 1
        x11 = 0;
        y11 = random.nextInt(height);
        x12 = width;
        y12 = random.nextInt(height);
        a1 = y11 - y12;
        b1 = x12 - x11;
        p1 = x11 * y12 - y11 * x12;

        // Line 2
        x21 = random.nextInt(width);
        y21 = 0;
        x22 = width;
        y22 = height;
        a2 = y21 - y22;
        b2 = x22 - x21;
        p2 = x21 * y22 - y21 * x22;

        // Line 3
        x31 = 0;
        y31 = random.nextInt(height);
        x32 = width;
        y32 = random.nextInt(width);
        a3 = y31 - y32;
        b3 = x32 - x31;
        p3 = x31 * y32 - y31 * x32;

        // Dots
        dots = (width + height) / 2;
        x = new int[dots];
        y = new int[dots];
        for (int i = 0; i < dots; i++) {
            x[i] = random.nextInt(width - 1);
            y[i] = random.nextInt(height - 1);
        }

    }

    private void init() {
        display = new Display(title, width, height);
    }

    private void render() {

        bufferStrategy = display.getCanvas().getBufferStrategy();
        if (bufferStrategy == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        graphics = bufferStrategy.getDrawGraphics();

        graphics.setColor(Color.black);

        graphics.drawLine(x11, y11, x12, y12);
        graphics.drawLine(x21, y21, x22, y22);
        graphics.drawLine(x31, y31, x32, y32);

        drawDots();

        bufferStrategy.show();
        graphics.dispose();

    }

    public void run() {

        init();

        while (running) {

            render();

        }

        stop();

    }

    public synchronized void start() {
        if (running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop() {
        if (!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void drawDots() {

        for (int i = 0; i < dots; i++) {

            int l1 = a1*x[i] + b1*y[i] + p1;
            int l2 = a2*x[i] + b2*y[i] + p2;
            int l3 = a3*x[i] + b3*y[i] + p3;

            if (l1 >= 0) {
                if (l2 >= 0) {
                    if (l3 >= 0) {
                        graphics.setColor(Color.black);
                    }
                    else {
                        graphics.setColor(Color.blue);
                    }
                }
                else {
                    if (l3 >= 0) {
                        graphics.setColor(Color.red);
                    }
                    else {
                        graphics.setColor(Color.green);
                    }
                }
            }
            else {
                if (l2 >= 0) {
                    if (l3 >= 0) {
                        graphics.setColor(Color.yellow);
                    }
                    else {
                        graphics.setColor(Color.cyan);
                    }
                }
                else {
                    if (l3 >= 0) {
                        graphics.setColor(Color.magenta);
                    }
                    else {
                        graphics.setColor(Color.orange);
                    }
                }
            }

            graphics.fillRect(x[i], y[i], 3, 3);

        }

    }

    public String getTitle() {
        return title;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
