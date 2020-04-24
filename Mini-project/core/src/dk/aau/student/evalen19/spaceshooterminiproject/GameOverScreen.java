package dk.aau.student.evalen19.spaceshooterminiproject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class GameOverScreen implements Screen {
    public GameState gameState;

    private SpriteBatch batch;
    private OrthographicCamera camera;

    private Texture gameOverScreenImage;

    private BitmapFont text;
    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;

    private Preferences preferences;
    private int highscore;
    private int currentScore;

    public GameOverScreen(GameState gameState){
        this.gameState = gameState;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 720, 1080);

        gameOverScreenImage = new Texture(Gdx.files.internal("GameOverImage.png"));

        text = new BitmapFont();
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Xolonium-Regular.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 64;
        text = fontGenerator.generateFont(fontParameter);
        fontGenerator.dispose();

        preferences = Gdx.app.getPreferences("ScoreSave");
        highscore = preferences.getInteger("Highscore", 0);
        currentScore = preferences.getInteger("Score", 0);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        camera.update();


        batch.begin();
        batch.draw(gameOverScreenImage, 0, 0);
        text.draw(batch, String.valueOf(currentScore), 720 / 2 - 50, 1080 / 2 + 150);
        text.draw(batch, String.valueOf(highscore), 720 / 2 - 50, 1080 / 2 - 50);
        batch.end();

        if (Gdx.input.justTouched()){
            gameState.setScreen(new GameScreen(gameState));
            this.dispose();
        }
    }


    @Override
    public void dispose() {
        text.dispose();
        gameOverScreenImage.dispose();
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
