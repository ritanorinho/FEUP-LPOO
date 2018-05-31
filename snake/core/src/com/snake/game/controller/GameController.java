package com.snake.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.snake.game.controller.entities.SnakeBody;
import com.snake.game.model.GameModel;
import com.snake.game.model.entities.BallModel;
import com.snake.game.model.entities.CoinModel;
import com.snake.game.model.entities.NumberModel;
import com.snake.game.model.entities.SquareModel;
import com.snake.game.model.entities.WallModel;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    private static GameController instance;

    private final World world;
    private int score;
    private final SnakeBody snakeBody;
    private List<BallModel> ballsToRemove = new ArrayList<BallModel>();
    public ArrayList<CoinModel> coinsToRemove = new ArrayList<CoinModel>();

    private ArrayList<SquareModel> squaresToRemove = new ArrayList<SquareModel>();
    private ArrayList<WallModel> wallsToRemove = new ArrayList<WallModel>();
    public float speed;
    public float saveSpeed;
    public static final int SCREEN_WIDTH = 480;
    public static final int SCREEN_HEIGHT = 720;
    public static final int SNAKE_WIDTH_PIXEL = 17;
    public static final int SNAKE_HEIGHT_PIXEL = 32;
    public static final int SNAKE_WIDTH = SNAKE_WIDTH_PIXEL * 3;
    public static final int SNAKE_HEIGHT = SNAKE_HEIGHT_PIXEL * 3;
    private int coins;
    public boolean gameOver = false;


    private GameController() {
        world = new World(new Vector2(0, 0), true);
        snakeBody = new SnakeBody(world, GameModel.getInstance().getSnake());
        score = 0;
        List<BallModel> balls = GameModel.getInstance().getBalls();
        coins = 0;
        saveSpeed = speed;
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

    public void shiftRight(float delta, float speed) {
        float x = GameModel.getInstance().getSnake().getX() + (speed *1.5f)* Gdx.graphics.getDeltaTime();

        if (x > 18.7)
            x = (float) 18.7;
        for (SquareModel square :GameModel.getInstance().getSquares()){
            if ( x > square.getX()-2.8f && x<=square.getX()-2.4f && x<square.getX() && square.getY() - 2.8 <= GameModel.getInstance().getSnake().getY() && GameModel.getInstance().getSnake().getY() <= square.getY()+2.8) {
                x = square.getX() - 2.6f;
                System.out.println(x +"___"+square.getX()+"___"+square.getY());
                if (x<0.48){
                    x =0.48f;
                }

            }
        }
        GameModel.getInstance().getSnake().setX(x);


    }


    public void shiftLeft(float delta, float speed) {
        float x = GameModel.getInstance().getSnake().getX() - (speed *1.5f)* Gdx.graphics.getDeltaTime();
        if (x < 0.48)
            x = (float) 0.48;

        for (SquareModel square :GameModel.getInstance().getSquares()){
            if ( x >= square.getX()+ 2f&& x<=square.getX()+2.4f && x>square.getX() && square.getY() - 2.4 <= GameModel.getInstance().getSnake().getY() && GameModel.getInstance().getSnake().getY() <= square.getY()+2.8) {
                x = square.getX() + 2.4f;
                if (x>18.7){
                    x =18.7f;
                }

            }
        }
        GameModel.getInstance().getSnake().setX(x);
}

    public void updateSquares(float delta) {

        for (SquareModel square : GameModel.getInstance().getSquares()) {
            square.update(delta, speed);
            for (NumberModel number : square.numbers){
                number.update(delta,speed);
            }
            if (square.toRemove)
                squaresToRemove.add(square);
        }

    }

    public void updateBalls(float delta){
        for (BallModel ball : GameModel.getInstance().getBalls()){
            ball.update(delta,speed);
            if (ball.toRemove)
                ballsToRemove.add(ball);
        }
    }
    public void updateCoin(float delta){
        for (CoinModel coin : GameModel.getInstance().getCoins()){
            coin.update(delta,speed);
            if (coin.toRemove)
                coinsToRemove.add(coin);
        }
    }
    public void updateWalls(float delta){
        for (WallModel wall : GameModel.getInstance().getWalls()) {
            wall.update(delta,speed);
            if (wall.toRemove)
                wallsToRemove.add(wall);
        }
    }
    public void detectCollisionSquare(float delta) {

        for (SquareModel square : GameModel.getInstance().getSquares()) {

            if (!gameOver && square.getY() - 2.3 < GameModel.getInstance().getSnake().getY() && GameModel.getInstance().getSnake().getX() > square.getX()-2.4 && GameModel.getInstance().getSnake().getX() < square.getX() + 2.4 && GameModel.getInstance().getSnake().getY() < square.getY()) {
                    speed = 0;
                GameModel.getInstance().getSnake().collideWithSquare=true;
                    if (square.getValue() == 0) {
                        squaresToRemove.add(square);
                        speed = saveSpeed;
                        GameModel.getInstance().getSnake().collideWithSquare=false;
                    } else
                        decrementSquare(delta, square);
            }
        }
        GameModel.getInstance().getSquares().removeAll(squaresToRemove);
    }
    public void detectCollisionBalls(float delta){
        for (BallModel ball : GameModel.getInstance().getBalls()) {
            if (!gameOver && ball.getY() -0.2 < GameModel.getInstance().getSnake().getY() && ball.getX() < GameModel.getInstance().getSnake().getX() + 0.8 && GameModel.getInstance().getSnake().getX() < ball.getX() + 0.8 && GameModel.getInstance().getSnake().getY() < ball.getY()) {
                GameModel.getInstance().getSnake().updateSize(1);
                ballsToRemove.add(ball);
            }
        }
        GameModel.getInstance().getBalls().removeAll(ballsToRemove);

    }
    public void detectCollisionCoins(float delta){
        for (CoinModel coin : GameModel.getInstance().getCoins()){

            if (coin.getY()-0.2 < GameModel.getInstance().getSnake().getY() && coin.getX() < GameModel.getInstance().getSnake().getX() + 0.8 && GameModel.getInstance().getSnake().getX() < coin.getX() + 0.8 && GameModel.getInstance().getSnake().getY() < coin.getY()){
                coins++;
                GameModel.getInstance().getSnake().updateSize(5);
                coinsToRemove.add(coin);
            }
        }
        GameModel.getInstance().getCoins().removeAll(coinsToRemove);
    }

    private void decrementSquare(float delta, SquareModel square) {
        square.time_to_decrement -= delta * 10;
        if (square.time_to_decrement <= 0 && GameModel.getInstance().getSnake().getSize()>0) {
            square.setValue(square.getValue() - 1);
            score += 2;
            GameModel.getInstance().calculateNumbers(square);
            square.time_to_decrement = 2;
            GameModel.getInstance().getSnake().updateSize(-1);
            GameModel.getInstance().deleteBallToSnake();
        }
        if (GameModel.getInstance().getSnake().getSize()<=0){
            gameOver = true;
        }
    }

    public void detectCollisionWalls(float delta) {
        for (WallModel wall : GameModel.getInstance().getWalls()) {
            if (wall.getX() + 1 <= GameModel.getInstance().getSnake().getX() && GameModel.getInstance().getSnake().getX()< wall.getX()+1&& wall.getY() <= GameModel.getInstance().getSnake().getY()+3 &&GameModel.getInstance().getSnake().getY()< wall.getY()+10) {
                System.out.println("collide walls");
                GameModel.getInstance().getSnake().setX(wall.getX()+1f);
            }
        }
    }

    public static void restart(){
        instance = null;
    }
    public int getScore(){
        return score;
    }
    public int getCoins() {return coins;}
}
