package com.snake.game.model;

import com.badlogic.gdx.Gdx;
import com.snake.game.model.entities.BallModel;
import com.snake.game.model.entities.CoinModel;
import com.snake.game.model.entities.EntityModel;
import com.snake.game.model.entities.SnakeModel;
import com.snake.game.model.entities.SquareModel;

import java.util.ArrayList;
import java.util.List;

public class GameModel {

    private static GameModel instance;
    private SnakeModel snake;
    private List<BallModel> balls;
    private List<SquareModel> squares;
    private List<CoinModel> coins;

    public static GameModel getInstance(){
        if (instance==null)
            instance = new GameModel();
        return instance;
    }
    public GameModel(){
        snake = new SnakeModel(20,10,10,0);
        balls= new ArrayList<BallModel>();
        squares = new ArrayList<SquareModel>();
        coins = new ArrayList<CoinModel>();
    }

    public SnakeModel getSnake(){
        return snake;
    }
    public List<BallModel> getBalls(){
        return balls;
    }
    public List<SquareModel> getSquares() {
        return squares;
    }
    public List<CoinModel> getCoins(){
        return coins;
    }
    public void createSquare (float x, float y, int value,EntityModel.ModelType model) {
        SquareModel square;
        square = new SquareModel(x, y, 0,value, model);
        squares.add(square);

    }
    public void remove (EntityModel m1){
        if (m1 instanceof SquareModel)
            squares.remove(m1);
        else if (m1 instanceof BallModel)
            balls.remove(m1);
        else if (m1 instanceof CoinModel)
            coins.remove(m1);
    }


    public void update(float delta,float speed) {
         snake.setY((float) (snake.getY()+speed* Gdx.graphics.getDeltaTime()));

    }
}
