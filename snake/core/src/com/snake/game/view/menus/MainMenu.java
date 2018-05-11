package com.snake.game.view.menus;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.snake.game.SnakeSmash;
import com.snake.game.view.GameView;

import static com.snake.game.controller.GameController.SCREEN_HEIGHT;
import static com.snake.game.controller.GameController.SCREEN_WIDTH;



public class MainMenu implements Screen {
    private static final int DEFAULT_ICON_WIDTH = 75;
    private static final int DEFAULT_ICON_HEIGHT = 75;
    private static final int DEFAULT_PLAY_WIDTH = 175;
    private static final int DEFAULT_PLAY_HEIGHT = 92;
    private static final int TITLE_WIDTH = 440;
    private static final int TITLE_HEIGHT = 85;
    private static final int TITLE_Y = 550;
    private static final int BEGINNER_Y = 400;
    private static final int INTERMEDIATE_Y = 300;
    private static final int ICON_Y = 100;

    private Viewport viewport;
    protected Stage stage;
    private SpriteBatch batch;
    protected final SnakeSmash game;

    private Texture exitBtn;
    private Texture settingsBtn;
    private Texture beginnerActiveBtn;
    private Texture beginnerInactiveBtn;
    private Texture intermediateInactiveBtn;
    private Texture intermediateActiveBtn;
    private Texture facebookBtn;
    private Texture scoresBtn;
    private Texture title;


    public MainMenu(SnakeSmash game){

        this.game = game;
        exitBtn = new Texture("exitBtn.png");
        settingsBtn = new Texture("settingsBtn.png");
        beginnerActiveBtn = new Texture("beginnerActive.png");
        beginnerInactiveBtn = new Texture("beginnerInactive.png");
        intermediateActiveBtn = new Texture("IntermediateActive.png");
        intermediateInactiveBtn = new Texture("IntermediateInactive.png");
        facebookBtn = new Texture("facebook.png");
        scoresBtn = new Texture("scoresBtn.png");
        title = new Texture("title.png");


    }
    @Override
    public void show() {

    }

    @Override
        public void render(float delta) {

        // Clear the screen
        Gdx.gl.glClearColor(129/255f, 129/255f, 129/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the texture
        game.getBatch().begin();

        int x = SCREEN_WIDTH /2 - DEFAULT_PLAY_WIDTH / 2;
        if(Gdx.input.getX() < x + DEFAULT_PLAY_WIDTH && Gdx.input.getX()> x && SCREEN_HEIGHT - Gdx.input.getY() < BEGINNER_Y + DEFAULT_PLAY_HEIGHT && SCREEN_HEIGHT - Gdx.input.getY() > BEGINNER_Y){
            game.getBatch().draw(beginnerActiveBtn, x, BEGINNER_Y, DEFAULT_PLAY_WIDTH, DEFAULT_PLAY_HEIGHT);
            if(Gdx.input.isTouched()){
                this.dispose();
                game.setScreen(new GameView(game));
            }
        }else{
            game.getBatch().draw(beginnerInactiveBtn, x, BEGINNER_Y, DEFAULT_PLAY_WIDTH, DEFAULT_PLAY_HEIGHT);
        }

        if(Gdx.input.getX() < x + DEFAULT_PLAY_WIDTH && Gdx.input.getX()> x && SCREEN_HEIGHT - Gdx.input.getY() < INTERMEDIATE_Y + DEFAULT_PLAY_HEIGHT && SCREEN_HEIGHT - Gdx.input.getY() > INTERMEDIATE_Y){
            game.getBatch().draw(intermediateActiveBtn, x, INTERMEDIATE_Y, DEFAULT_PLAY_WIDTH, DEFAULT_PLAY_HEIGHT);

        }else{
            game.getBatch().draw(intermediateInactiveBtn, x, INTERMEDIATE_Y, DEFAULT_PLAY_WIDTH, DEFAULT_PLAY_HEIGHT);
        }


        x = SCREEN_WIDTH  - DEFAULT_ICON_WIDTH - 50;
        game.getBatch().draw(exitBtn, x, ICON_Y, DEFAULT_ICON_WIDTH, DEFAULT_ICON_HEIGHT);
        if(Gdx.input.getX() < x + DEFAULT_ICON_WIDTH && Gdx.input.getX()> x && SCREEN_HEIGHT - Gdx.input.getY() < ICON_Y + DEFAULT_ICON_HEIGHT && SCREEN_HEIGHT - Gdx.input.getY() > ICON_Y) {
            if(Gdx.input.isTouched()){
                Gdx.app.exit();
            }
        }

        x = 50;
        game.getBatch().draw(settingsBtn, x, ICON_Y, DEFAULT_ICON_WIDTH, DEFAULT_ICON_HEIGHT);
        if(Gdx.input.getX() < x + DEFAULT_ICON_WIDTH && Gdx.input.getX()> x && SCREEN_HEIGHT - Gdx.input.getY() < ICON_Y + DEFAULT_ICON_HEIGHT && SCREEN_HEIGHT - Gdx.input.getY() > ICON_Y) {
            if(Gdx.input.isTouched()){
                //go to settings menu
            }
        }

        x = x + 25 + DEFAULT_ICON_WIDTH;
        game.getBatch().draw(scoresBtn, x, ICON_Y, DEFAULT_ICON_WIDTH, DEFAULT_ICON_HEIGHT);
        if(Gdx.input.getX() < x + DEFAULT_ICON_WIDTH && Gdx.input.getX()> x && SCREEN_HEIGHT - Gdx.input.getY() < ICON_Y + DEFAULT_ICON_HEIGHT && SCREEN_HEIGHT - Gdx.input.getY() > ICON_Y) {
            if(Gdx.input.isTouched()){
                //go to settings menu
            }
        }

        x = x + 25 + DEFAULT_ICON_WIDTH;
        game.getBatch().draw(facebookBtn, x, ICON_Y, DEFAULT_ICON_WIDTH, DEFAULT_ICON_HEIGHT);
        if(Gdx.input.getX() < x + DEFAULT_ICON_WIDTH && Gdx.input.getX()> x && SCREEN_HEIGHT - Gdx.input.getY() < ICON_Y + DEFAULT_ICON_HEIGHT && SCREEN_HEIGHT - Gdx.input.getY() > ICON_Y) {
            if(Gdx.input.isTouched()){
                //go to settings menu
            }
        }
        game.batch.draw(title, SCREEN_WIDTH / 2 - TITLE_WIDTH / 2, TITLE_Y, TITLE_WIDTH, TITLE_HEIGHT);

        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}