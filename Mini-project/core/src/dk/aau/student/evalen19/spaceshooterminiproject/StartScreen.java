package dk.aau.student.evalen19.spaceshooterminiproject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StartScreen implements Screen {
    public GameState gameState;

    private SpriteBatch batch;
    private OrthographicCamera camera;

    private ScrollingBackground background;
    private Texture playButtonImage;
    private Texture howToButtonImage;


    public StartScreen(GameState gameState){
        this.gameState = gameState;
    }
    @Override
    public void show() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 720, 1080);

        background = new ScrollingBackground(0, 0);

        playButtonImage = new Texture(Gdx.files.internal("playButton.png"));
        howToButtonImage = new Texture(Gdx.files.internal("howtoButton.png"));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        camera.update();

        background.updateBackgrounds(300, -1080);

        batch.begin();
        background.drawBackground(batch, 1080, 1080);

        batch.draw(playButtonImage, 720 / 2 - 180, 700, 360, 140);
        batch.draw(howToButtonImage, 720 / 2 - 230, 300, 460, 140);

        batch.end();

        if (Gdx.input.justTouched() && Gdx.input.getX() > 720 / 2 - 180 && Gdx.input.getX() < 720 / 2 + 360 + 180 && Gdx.input.getY() < 700 + 80 && Gdx.input.getY() > 700 - 140 - 80){
            gameState.setScreen(new GameScreen(gameState));
            this.dispose();
        }
        if (Gdx.input.justTouched() && Gdx.input.getX() > 720 / 2 - 230 && Gdx.input.getX() < 720 / 2 + 460 + 230 && Gdx.input.getY() > 1080 + 300 && Gdx.input.getY() < 1080 + 600){
            gameState.setScreen(new HowToScreen(gameState));
            gameState.dispose();
        }
    }

    @Override
    public void dispose() {
        playButtonImage.dispose();
        howToButtonImage.dispose();
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
