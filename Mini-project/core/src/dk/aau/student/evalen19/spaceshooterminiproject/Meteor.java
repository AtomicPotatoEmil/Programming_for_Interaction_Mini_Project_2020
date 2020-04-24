package dk.aau.student.evalen19.spaceshooterminiproject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;



public class Meteor {
    private Texture meteorImage;
    private Rectangle collisionbox;
    private ParticleEffect flame;
    private boolean right;
    private int positionX;
    private int positionY;
    private int width;
    private int height;

    public Meteor(int x, int y, int width, int height, boolean isRight){
        this.meteorImage = new Texture(Gdx.files.internal("Stone.png"));
        this.collisionbox = new Rectangle(x, y, width, height);
        this.flame = new ParticleEffect();
        flame.load(Gdx.files.internal("meteorFlame.p"), Gdx.files.internal(""));
        flame.getEmitters().first().setPosition(-1000, -1000);
        flame.start();
        this.right = isRight;
        this.positionX = x;
        this.positionY = y;
        this.width = width;
        this.height = height;
    }

    public void updateMeteor(int speed){
        if (isRight()){
            positionX += speed * Gdx.graphics.getDeltaTime();
        }else{
            positionX -= speed * Gdx.graphics.getDeltaTime();
        }
        collisionbox.x = positionX;
        if (positionX > 1180){
            meteorImage.dispose();
            flame.dispose();
        }
        if (positionX < -100){
            meteorImage.dispose();
            flame.dispose();
        }
    }

    public void drawMeteor(SpriteBatch batch){
        flame.update(Gdx.graphics.getDeltaTime());
        flame.setPosition((float) getPositionX() + (getWidth() / 2), (float) getPositionY() + 32);
        flame.draw(batch);
        batch.draw(getMeteorImage(), getPositionX(), getPositionY(), getWidth(), getHeight());
    }

    public Texture getMeteorImage(){
        return this.meteorImage;
    }

    public Rectangle getCollisionbox(){
        return this.collisionbox;
    }

    public ParticleEffect getFlame(){
        return this.flame;
    }

    public boolean isRight(){
        return this.right;
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
