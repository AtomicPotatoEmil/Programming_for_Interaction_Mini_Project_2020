package dk.aau.student.evalen19.spaceshooterminiproject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class PlayerShip {
    private Texture playerImage;
    private Rectangle hurtbox;
    private ParticleEffect boostEffect;
    private int positionX;
    private int positionY;
    private int width;
    private int height;

    public PlayerShip(int x, int y, int width, int height){
        this.playerImage = new Texture(Gdx.files.internal("playership.png"));
        this.hurtbox = new Rectangle(x, y, width, height);
        this.boostEffect = new ParticleEffect();
        boostEffect.load(Gdx.files.internal("boostParticle.p"), Gdx.files.internal(""));
        boostEffect.start();
        this.positionX = x;
        this.positionY = y;
        this.width = width;
        this.height = height;
    }

    public ParticleEffect getBoostEffect(){
        return this.boostEffect;
    }

    public void movePositionX(int speed){
        positionX += speed * Gdx.graphics.getDeltaTime();
        hurtbox.x = positionX;
        if (positionX > 720 - width){
            positionX = 720 - width;
        }
        if (positionX < 0){
            positionX = 0;
        }
    }

    public void movePositionY(int speed){
        positionY += speed * Gdx.graphics.getDeltaTime();
        hurtbox.y = positionY;
        if (positionY > 1080 - height){
            positionY = 1080 - height;
        }
        if (positionY < 0){
            positionY = 0;
        }
    }

    public void drawPlayerShip(SpriteBatch batch){
        boostEffect.update(Gdx.graphics.getDeltaTime());
        boostEffect.setPosition((float)getPositionX() + (width / 2), (float) getPositionY());
        boostEffect.draw(batch);
        batch.draw(getPlayerImage(), getPositionX(), getPositionY(), getWidth(), getHeight());
    }

    public Texture getPlayerImage(){
        return this.playerImage;
    }

    public Rectangle getHurtbox(){
        return this.hurtbox;
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
