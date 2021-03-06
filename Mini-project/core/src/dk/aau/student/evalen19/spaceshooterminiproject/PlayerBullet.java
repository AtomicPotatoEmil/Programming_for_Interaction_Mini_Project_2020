package dk.aau.student.evalen19.spaceshooterminiproject;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class PlayerBullet {
    private Rectangle hitbox;
    private Texture playerBulletImage;
    private int positionX;
    private int positionY;
    private int width;
    private int height;

    public PlayerBullet(int x, int y, int width, int height){
        this.hitbox = new Rectangle(x, y, width, height);
        this.playerBulletImage = new Texture(Gdx.files.internal("playerBullet.png"));
        this.positionX = x;
        this.positionY = y;
        this.width = width;
        this.height = height;
    }

    public void updateBullet(int speed){
        positionY += speed * Gdx.graphics.getDeltaTime();
        hitbox.y = positionY;
        if (positionY > 1080){
            playerBulletImage.dispose();
        }
    }

    public void drawBullet(SpriteBatch batch){
        batch.draw(getPlayerBulletImage(), getPositionX(), getPositionY(), getWidth(), getHeight());
    }

    public Rectangle getHitbox(){
        return this.hitbox;
    }

    public Texture getPlayerBulletImage(){
        return this.playerBulletImage;
    }

    public int getPositionX(){
        return this.positionX;
    }

    public int getPositionY(){
        return this.positionY;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

}
