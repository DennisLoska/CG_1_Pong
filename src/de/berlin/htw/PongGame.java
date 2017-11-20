package de.berlin.htw;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.Display;

public class PongGame {

    private int width = 800;
    private int height = 600;
    private int yPosP1 = 20;
    private int yPosP2 = 210;
    private long lastTime = getTime();
    private float timeDiff;
    private float ySpeed = 0.5f;
    private float ballspeed = 5;
    private int ballX = 395;
    private int ballY = 295;
    private int ballX2 = 405;
    private int ballY2 = 305;
    private int ballDirX = 1;
    private int ballDirY = 1;

    //starting the game
    public static void main(String[] args) {
        PongGame pong = new PongGame();
        pong.run();
    }

    //game running until exit-condition
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
        //creates an coordinate system having (0,0) in the bottom left corner
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();//Einheitsmatrix
        GL11.glOrtho(0, width, 0, height, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }

    private boolean update() {
        //Infinite loop
        long time = getTime();
        timeDiff = time - lastTime;//delta in between frames
        lastTime = time;

        getInput();
        movePlayer();
        moveBall();

        Display.update();
        Display.sync(60);
        return !Display.isCloseRequested();
    }

    private void draw() {
        //clears the display before each action and paints it!
        GL11.glClearColor(0.3f, 0.3f, 0.3f, 1);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        //Players
        drawRect(20, yPosP1, 70, yPosP2);
        drawRect(730, 20, 780, 210);
        //Ball
        drawRect(ballX, ballY, ballX2, ballY2);
    }

    private void drawRect(int xs, int ys, int xe, int ye) {
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glColor3f(0.4f, 0.75f, 0.2f);
        GL11.glVertex2f(xs, ys);
        GL11.glVertex2f(xs, ye);
        GL11.glVertex2f(xe, ye);
        GL11.glVertex2f(xe, ys);
        GL11.glEnd();
    }

    private void getInput() {
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
            Display.destroy();
    }

    private void movePlayer() {
        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            if (yPosP1 > 0) {
                yPosP1 -= ySpeed * timeDiff;
                yPosP2 -= ySpeed * timeDiff;
            }
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            if (yPosP2 < height) {
                yPosP1 += ySpeed * timeDiff;
                yPosP2 += ySpeed * timeDiff;
            }
        }
    }

    private void moveBall() {
        if (ballX > width || ballX < 0)
            ballDirX *= -1;
        if (ballY > height || ballY < 0)
            ballDirY *= -1;
        ballX += ballspeed * ballDirX;
        ballY += ballspeed * ballDirY;
        ballX2 += ballspeed * ballDirX;
        ballY2 += ballspeed * ballDirY;
    }

    private long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    //Ends the game
    private void finish() {
        Display.destroy();
        System.out.println("Thanks for playing!");
        System.exit(0);
    }


}
