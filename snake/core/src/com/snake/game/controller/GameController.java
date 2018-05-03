package com.snake.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.snake.game.controller.entities.SnakeBody;
import com.snake.game.controller.entities.SquareBody;
import com.snake.game.model.GameModel;
import com.snake.game.model.entities.BallModel;

import java.util.ArrayList;
import java.util.List;

public class GameController implements ContactListener {

    private static GameController instance;

    private final World world;
    private final SnakeBody snakeBody;
    private List<BallModel> ballToAdd= new ArrayList<BallModel>();
    private List <SquareBody> squaresToAdd= new ArrayList<SquareBody>();

    private float timeToNextShoot;
    private GameController(){
        world= new World(new Vector2(0,0),true);
        snakeBody = new SnakeBody(world, GameModel.getInstance().getSnake());
        List<BallModel> balls = GameModel.getInstance().getBalls();

        world.setContactListener(this);
    }

    public static GameController getInstance(){
        if (instance==null)
            instance = new GameController();
        return instance;
    }
    @Override
    public void beginContact(Contact contact) {

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
