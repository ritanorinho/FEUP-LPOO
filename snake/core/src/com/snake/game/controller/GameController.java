package com.snake.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.snake.game.controller.entities.SnakeBody;
import com.snake.game.controller.entities.SquareBody;
import com.snake.game.model.GameModel;
import com.snake.game.model.entities.BallModel;
import com.snake.game.model.entities.CoinModel;
import com.snake.game.model.entities.EntityModel;
import com.snake.game.model.entities.SnakeModel;
import com.snake.game.model.entities.SquareModel;

import java.util.ArrayList;
import java.util.List;

public class GameController implements ContactListener{

    private static GameController instance;

    private final World world;
    private final SnakeBody snakeBody;
    private List<BallModel> balls=new ArrayList<BallModel>();
    private ArrayList<SquareBody> squares = new ArrayList<SquareBody>();
    private ArrayList<CoinModel> coinArray=new ArrayList<CoinModel>();
    ArrayList<SquareModel> squaresToRemove = new ArrayList<SquareModel>();
    private float accumulator;
    public float speed;
    public static final int SCREEN_WIDTH = 480;
    public static final int SCREEN_HEIGHT = 720;
    public static final int SNAKE_WIDTH_PIXEL = 17;
    public static final int SNAKE_HEIGHT_PIXEL = 32;
    public static final int SNAKE_WIDTH = SNAKE_WIDTH_PIXEL * 3;
    public static final int SNAKE_HEIGHT = SNAKE_HEIGHT_PIXEL * 3;
    public int coins;

    private GameController() {
        world = new World(new Vector2(0, 0), true);
        snakeBody = new SnakeBody(world, GameModel.getInstance().getSnake());

        List<BallModel> balls = GameModel.getInstance().getBalls();
        world.setContactListener(this);

        coins=0;
    }

    public static GameController getInstance() {
        if (instance == null)
            instance = new GameController();
        return instance;
    }



    public World getWorld() {
        return world;
    }

    public void update(float delta) {
        world.step(delta, 6, 2);

    }
    public void shiftRight(float delta, float speed){
        float x = GameModel.getInstance().getSnake().getX()+ speed* Gdx.graphics.getDeltaTime();
        if (x> 18.7)
            x=(float)18.7;

        GameModel.getInstance().getSnake().setX(x);
    
    }


    public void shiftLeft(float delta, float speed) {
        float x = GameModel.getInstance().getSnake().getX()-speed * Gdx.graphics.getDeltaTime();
        if (x < 0.48)
            x =(float)0.48;
        GameModel.getInstance().getSnake().setX(x);
    }
    public void updateSquares(float delta){

        for (SquareModel square : GameModel.getInstance().getSquares()) {
            square.update(delta,speed);
            if (square.toRemove)
                squaresToRemove.add(square);
        }

    }
    public void detectCollision(float delta){

        for (SquareModel square : GameModel.getInstance().getSquares()) {
            // System.out.println("XSNAKE:::" + GameModel.getInstance().getSnake().getX()+"YS");
            if (square.getY()-2.3< GameModel.getInstance().getSnake().getY() && GameModel.getInstance().getSnake().getX() + 1.9 > square.getX() && GameModel.getInstance().getSnake().getX() < square.getX() + 1.9 && GameModel.getInstance().getSnake().getY()<square.getY()) {
                //System.out.println("square value:::"+square.getValue());
                speed=0;
                if (square.getValue()==0) {
                    squaresToRemove.add(square);
                    speed=9;
                }
                else
                    decrementSquare(delta,square);
            }
        }
        GameModel.getInstance().getSquares().removeAll(squaresToRemove);
    }
    private void decrementSquare(float delta,SquareModel square) {
        square.time_to_decrement-=delta*10;
        System.out.println("time_to_decrement:::"+square.time_to_decrement);
        if (square.time_to_decrement<=0) {
            square.setValue(square.getValue() - 1);
            square.time_to_decrement=2;
        }

    }
    @Override
    public void beginContact(Contact contact) {

        System.out.println("CONTACT");
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
