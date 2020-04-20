package dk.aau.student.evalen19.spaceshooterminiproject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;

public class GameScreen implements Screen {
    public GameState gameState;

    private SpriteBatch batch;
    private OrthographicCamera camera;

    private ScrollingBackground background;

    private PlayerShip playerShip;
    private PlayerController playerController;
    private ArrayList<PlayerBullet> playerBullets;
    private ArrayList<PlayerBullet> playerBulletsToRemove;

    private ArrayList<StandardEnemyShip> standardEnemyShips;
    private ArrayList<StandardEnemyShip> standardEnemyShipsToRemove;
    private int standardEnemySpawnTime = MathUtils.random(1, 30);

    private ArrayList<EnemyBullet> enemyBullets;
    private ArrayList<EnemyBullet> enemyBulletsToRemove;

    private ArrayList<Meteor> meteors;
    private ArrayList<Meteor> meteorsToRemove;
    private int meteorRightSpawnTime = MathUtils.random(1, 30);
    private int meteorLeftSpawnTime = MathUtils.random(1, 30);

    public GameScreen(GameState gameState){
        this.gameState = gameState;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 720, 1080);

        background = new ScrollingBackground(0, 0);

        playerShip = new PlayerShip(300, 300, 64, 64, true);
        playerController = new PlayerController();
        playerBullets = new ArrayList<PlayerBullet>();
        playerBulletsToRemove = new ArrayList<PlayerBullet>();

        standardEnemyShips = new ArrayList<StandardEnemyShip>();
        standardEnemyShipsToRemove = new ArrayList<StandardEnemyShip>();

        enemyBullets = new ArrayList<EnemyBullet>();
        enemyBulletsToRemove = new ArrayList<>();

        meteors = new ArrayList<Meteor>();
        meteorsToRemove = new ArrayList<Meteor>();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        camera.update();
        background.updateBackgrounds(300, -1080);
        playerController.updateSensor();
        standardEnemySpawnTime -= Gdx.graphics.getDeltaTime();
        if (standardEnemySpawnTime <= 0){
            standardEnemyShips.add(new StandardEnemyShip(MathUtils.random(0, 720 - 64), 1080, 64, 64, true));
            standardEnemySpawnTime = MathUtils.random(1, 200);
        }
        meteorRightSpawnTime -= Gdx.graphics.getDeltaTime();
        if (meteorRightSpawnTime <= 0){
            meteors.add(new Meteor(-64, MathUtils.random(0, 1080 - 64), 64, 64, true));
            meteorRightSpawnTime = MathUtils.random(150, 200);
        }
        meteorLeftSpawnTime -= Gdx.graphics.getDeltaTime();
        if (meteorLeftSpawnTime <= 0){
            meteors.add(new Meteor(1080 + 64, MathUtils.random(0, 1080 - 64), 64, 64, false));
            meteorLeftSpawnTime = MathUtils.random(150, 200);
        }

        if (playerController.getAccelerationZ() > 8){
            playerShip.movePositionY(300);
        }
        if (playerController.getAccelerationZ() < 6){
            playerShip.movePositionY(-300);
        }
        if (playerController.getAccelerationX() < 1){
            playerShip.movePositionX(300);
        }
        if (playerController.getAccelerationX() > -1){
            playerShip.movePositionX(-300);
        }
        if (Gdx.input.justTouched()){
            playerBullets.add(new PlayerBullet(playerShip.getPositionX() + 9, playerShip.getPositionY() + 40, 16, 16));
            playerBullets.add(new PlayerBullet(playerShip.getPositionX() + 40, playerShip.getPositionY() + 40, 16, 16));
        }
        for (PlayerBullet bullet : playerBullets){
            bullet.updateBullet(600);
            if (bullet.getPositionY() > 1080){
                bullet.getPlayerBulletImage().dispose();
                playerBulletsToRemove.add(bullet);
            }
            for (StandardEnemyShip standardEnemy : standardEnemyShips){
                if (bullet.getHitbox().overlaps(standardEnemy.getHurtbox())){
                    standardEnemy.setIsAlive(false);
                    standardEnemy.despawnHurtbox();
                    standardEnemy.getStandardEnemyShipImage().dispose();
                    standardEnemy.getEnemyBoostEffect().dispose();
                    bullet.getPlayerBulletImage().dispose();
                    playerBulletsToRemove.add(bullet);
                }
            }
        }

        for (StandardEnemyShip ship : standardEnemyShips){
            ship.movePositionY(-200);
            if (ship.getPositionY() < -64){
                standardEnemyShipsToRemove.add(ship);
                ship.getStandardEnemyShipImage().dispose();
                ship.getEnemyBoostEffect().dispose();
            }
            if (ship.isDespawned()){
                standardEnemyShipsToRemove.add(ship);
                ship.getExplosionEffect().dispose();
            }
            ship.shootTimer();
            if (ship.getIsShooting() && ship.getIsAlive()){
                enemyBullets.add(new EnemyBullet(ship.getPositionX() + 32, ship.getPositionY() - 32, 16, 32));
            }
        }

        for (EnemyBullet bullet : enemyBullets){
            bullet.updateBullet(-300);
            if (bullet.getPositionY() < -32){
                bullet.getEnemyBulletImage().dispose();
                enemyBulletsToRemove.add(bullet);
            }
            if (bullet.getHitbox().overlaps(playerShip.getHurtbox())){
                bullet.getEnemyBulletImage().dispose();
                enemyBulletsToRemove.add(bullet);
                playerShip.setAlive(false);
            }
        }

        for (Meteor meteor : meteors){
            meteor.updateMeteor(200);
            if (meteor.getPositionX() < -100 || meteor.getPositionX() > 1180){
                meteor.getMeteorImage().dispose();
                meteor.getFlame().dispose();
                meteorsToRemove.add(meteor);
            }
            if (meteor.getCollisionbox().overlaps(playerShip.getHurtbox())){
                playerShip.setAlive(false);
            }
        }


        playerBullets.removeAll(playerBulletsToRemove);
        standardEnemyShips.removeAll(standardEnemyShipsToRemove);
        enemyBullets.removeAll(enemyBulletsToRemove);
        meteors.removeAll(meteorsToRemove);

        batch.begin();
        background.drawBackground(batch, 1080, 1080);
        for (Meteor meteor : meteors){
            meteor.drawMeteor(batch);
        }
        for (PlayerBullet bullet : playerBullets){
            bullet.drawBullet(batch);
        }
        for (EnemyBullet bullet : enemyBullets){
            bullet.drawBullet(batch);
        }

        for (StandardEnemyShip ship : standardEnemyShips){
            ship.drawStandardEnemyShip(batch);
        }
        playerShip.drawPlayerShip(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.getBackground().dispose();
        background.getSecondBackground().dispose();
        playerShip.getPlayerImage().dispose();
        playerShip.getBoostEffect().dispose();

    }




    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}


}
