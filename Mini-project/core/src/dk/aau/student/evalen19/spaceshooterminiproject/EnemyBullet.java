package dk.aau.student.evalen19.spaceshooterminiproject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class EnemyBullet {
    private Texture enemyBulletImage;
    private Rectangle hitbox;
    private int positionX;
    private int positionY;
    private int width;
    private int height;

    public EnemyBullet(int x, int y, int width, int height){
        this.enemyBulletImage = new Texture(Gdx.files.internal("enemyBullet.png"));
        this.hitbox = new Rectangle(x, y, width, height);
        this.positionX = x;
        this.positionY = y;
        this.width = width;
        this.height = height;
    }

    public void updateBullet(int speed){
        positionY += speed * Gdx.graphics.getDeltaTime();
        hitbox.y = positionY;
        if (positionY > 1080){
            enemyBulletImage.dispose();
        }
    }

    public void despawnHitbox(){
        getHitbox().x = 0;
        getHitbox().y = 0;
        getHitbox().width = 0;
        getHitbox().height = 0;
    }

    public void drawBullet(SpriteBatch batch){
        batch.draw(getEnemyBulletImage(), getPositionX(), getPositionY(), getWidth(), getHeight());
    }

    public Texture getEnemyBulletImage(){
        return this.enemyBulletImage;
    }

    public Rectangle getHitbox(){
        return this.hitbox;
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
