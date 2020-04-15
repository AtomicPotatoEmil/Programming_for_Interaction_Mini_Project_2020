package dk.aau.student.evalen19.spaceshooterminiproject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScrollingBackground {
    private Texture background;
    private Texture secondBackground;
    private int positionX;
    private int positionY;


    public ScrollingBackground(int x, int y){
        this.background = new Texture(Gdx.files.internal("background.png"));
        this.secondBackground = new Texture(Gdx.files.internal("background.png"));
        this.positionX = x;
        this.positionY = y;
    }

    public void drawBackground(SpriteBatch batch, int width, int height){
        batch.draw(getBackground(), getPositionX(), getPositionY(), width, height);
        batch.draw(getSecondBackground(), getPositionX(), getPositionY() + height, width, height);
    }

    public void updateBackgrounds(int speed, int minHeight){
        positionY -= speed * Gdx.graphics.getDeltaTime();
        if (positionY < minHeight){
            positionY = 0;
        }
    }

    public Texture getBackground(){
        return this.background;
    }

    public Texture getSecondBackground(){
        return this.secondBackground;
    }

    public int getPositionX() {
        return this.positionX;
    }

    public int getPositionY(){
        return this.positionY;
    }
}
