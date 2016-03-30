package Laba6;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Ihor on 28.02.2016.
 */
public class Display {

    private String title;
    private int width, height;

    private JFrame jFrame;
    private Canvas canvas;

    public Display(String title, int width, int height) {

        this.title = title;
        this.width = width;
        this.height = height;

        jFrame = new JFrame(title);
        jFrame.setSize(width, height);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setVisible(true);

        jFrame.add(canvas);
        jFrame.pack();

    }

    public Canvas getCanvas() {
        return canvas;
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
