package dk.aau.student.evalen19.spaceshooterminiproject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class StandardEnemyShip {
    private Texture standardEnemyShipImage;
    private Rectangle hurtbox;
    private ParticleEffect enemyBoostEffect;
    private ParticleEffect explosionEffect;
    private boolean isAlive;
    private int positionX;
    private int positionY;
    private int width;
    private int height;

    public StandardEnemyShip(int x, int y, int width, int height, boolean status){
        this.standardEnemyShipImage = new Texture(Gdx.files.internal("standardEnemy.png"));
        this.hurtbox = new Rectangle(x, y, width, height);
        this.enemyBoostEffect = new ParticleEffect();
        enemyBoostEffect.load(Gdx.files.internal("enemyBoostParticle.p"), Gdx.files.internal(""));
        enemyBoostEffect.getEmitters().first().setPosition(-1000, -1000);
        enemyBoostEffect.start();
        this.explosionEffect = new ParticleEffect();
        explosionEffect.load(Gdx.files.internal("enemyExplosion.p"), Gdx.files.internal(""));
        explosionEffect.getEmitters().first().setPosition(-1000, -1000);
        explosionEffect.start();
        this.positionX = x;
        this.positionY = y;
        this.width = width;
        this.height = height;
        this.isAlive = status;
    }

    public void movePositionY(int speed){
        positionY += speed * Gdx.graphics.getDeltaTime();
        hurtbox.y = positionY;
    }

    public void drawStandardEnemyShip(SpriteBatch batch){
        if (getIsAlive()) {
            enemyBoostEffect.update(Gdx.graphics.getDeltaTime());
            enemyBoostEffect.setPosition((float) getPositionX() + (getWidth() / 2), (float) getPositionY() + getHeight());
            enemyBoostEffect.draw(batch);
            batch.draw(getStandardEnemyShipImage(), getPositionX(), getPositionY(), getWidth(), getHeight());
        }else{
            explosionEffect.update(Gdx.graphics.getDeltaTime());
            explosionEffect.setPosition(getPositionX() +  (getWidth() / 2), getPositionY() + (getHeight() / 2));
            explosionEffect.draw(batch);
        }
    }

    public Texture getStandardEnemyShipImage(){
        return this.standardEnemyShipImage;
    }

    public Rectangle getHurtbox(){
        return this.hurtbox;
    }

    public ParticleEffect getEnemyBoostEffect(){
        return this.enemyBoostEffect;
    }

    public ParticleEffect getExplosionEffect(){
        return this.explosionEffect;
    }

    public boolean isDespawned(){
        return enemyBoostEffect.isComplete();
    }

    public void setIsAlive(boolean status){
        this.isAlive = status;
    }

    public boolean getIsAlive(){
        return this.isAlive;
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