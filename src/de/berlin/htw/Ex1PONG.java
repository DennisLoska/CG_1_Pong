package de.berlin.htw;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.Display;

public class Ex1PONG {

    private static int width = 800;
    private static int height = 600;

    public static void main(String[] args) {
        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(1);
        }

        GL11.glPointSize(5);
        GL11.glBegin(GL11.GL_POINTS);
        GL11.glColor3f(1, 0, 1);

        GL11.glVertex2f(0, 0);
        GL11.glVertex2f(3, 4.5f);
        GL11.glEnd();

        while (!Display.isCloseRequested()) Display.update();

    }
}
