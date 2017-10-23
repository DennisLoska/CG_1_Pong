package de.berlin.htw;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.Display;

public class PongGame {

    private int width = 800;
    private int height = 600;

    //starting the APP
    public static void main(String[] args) {
        PongGame app = new PongGame();
        app.run();
    }

    //APP running
    private void run() {
        setup();

        while (update())
            draw();
        finish();
    }

    private void setup() {
        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.create();
        } catch (LWJGLException e) {
            //TODO handle exception
            e.printStackTrace();
            System.exit(1);
        }
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();//Einheitsmatrix
        GL11.glOrtho(0, width, 0, height, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }

    private boolean update() {
        //Infinite loop
        boolean end = Display.isCloseRequested();
        Display.update();
        Display.sync(30);
        return !end;
    }

    private void draw() {
        GL11.glClearColor(0.3f, 0.3f, 0.3f, 1);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    private void finish() {
        System.out.println("Thanks for playing!");
        System.exit(0);
    }

    private void drawRect(int xs, int ys, int xe, int ye) {
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(xs, ys);
        GL11.glVertex2f(xs, ye);
        GL11.glVertex2f(xe, ye);
        GL11.glVertex2f(xe, ys);
        GL11.glEnd();
    }
}
