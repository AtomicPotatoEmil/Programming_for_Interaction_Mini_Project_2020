package dk.aau.student.evalen19.spaceshooterminiproject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HowToScreen implements Screen {
    public GameState gameState;

    private SpriteBatch batch;
    private OrthographicCamera camera;

    private Texture howToImage;

    public HowToScreen(GameState gameState){
        this.gameState = gameState;
    }
    @Override
    public void show() {
        howToImage = new Texture(Gdx.files.internal("howtoScreen.png"));
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 720, 1080);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        camera.update();

        batch.begin();
        batch.draw(howToImage, 0,0);
        batch.end();

        if (Gdx.input.justTouched()){
            gameState.setScreen(new StartScreen(gameState));
        }
    }


    @Override
    public void dispose() {

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
