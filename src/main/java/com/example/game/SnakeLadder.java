package com.example.game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeLadder extends  Application {
     public static final int tileSize=40,widtvbh=10, height=10 ;
     public  static final int buttonLine=tileSize*height+50, infoLine=buttonLine-30;
     private static Dice dice=new Dice();
     private Player playerOne, playerTwo;
     private boolean gameStarted=false, playerOneTurn=false, playerTwoTurn=false;
//    @org.jetbrains.annotations.NotNull
    private Pane createContent(){
        Pane root=new Pane();

        root.setPrefSize(width*tileSize, height*tileSize+100);


        for (int i = 0; i < height; i++) {
            for(int j=0; j<width; j++){

                // creating 100 tiles to fit into pane
                Tile tile=new Tile(tileSize);
                tile.setTranslateX(j*tileSize); //setTransateX from where our X coordinate is start
                tile.setTranslateY(i*tileSize); //setTransateY from where our Y coordinate is start
                root.getChildren().add(tile);    // add every tile to pane
            }
        }
        // using Image class so we can use image
        Image img=new Image("C:\\Users\\Gobind Kushwaha\\IdeaProjects\\Game\\src\\main\\snkLDR.png");
        ImageView board= new ImageView();   // imageView hamari image ko dikhayega

        // set image on Board
        board.setImage(img);
        board.setFitHeight(tileSize*height);
        board.setFitWidth(tileSize*width);


        // creating buttons for oper ating the game
        Button playerOneButton=new Button("Player One"); // p1 button
        Button playerTwoButton=new Button("Player Two"); // p2 button
        Button startButton=new Button("Start");          // start button

        // we place all buttons after board section end
        playerOneButton.setTranslateY(buttonLine);     // bottom right
        playerOneButton.setTranslateX(25);
        playerOneButton.setDisable(true);
        playerTwoButton.setTranslateY(buttonLine);      //bottom left
        playerTwoButton.setTranslateX(295);
        playerTwoButton.setDisable(true);
        startButton.setTranslateY(buttonLine);             // center
        startButton.setTranslateX(170);

        //labling the button so we can know what kind of work a button going to do
        Label playerOneLable=new Label("");
        Label playerTwoLable=new Label("");
        Label diceLable=new Label("Stat the Game");

        // where our labling text is set
        playerOneLable.setTranslateY(infoLine);
        playerOneLable.setTranslateX(25);
        playerTwoLable.setTranslateY(infoLine);
        playerTwoLable.setTranslateX(295);
        diceLable.setTranslateY(infoLine);
        diceLable.setTranslateX(170);

        // initialised Player class object i. e. both the players
        playerOne =new Player(tileSize, Color.BLUE, "Amit");
        playerTwo=new Player(tileSize-5, Color.RED, "Suresh");

        // player Action for p
        // when we press player One buttons...  follwed action performed

        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (gameStarted){
                    if(playerOneTurn){
                      //  dice rolled , basis on number appeared on dice , player move
                        int diceVal=dice.getrolledDiceValue();
                        diceLable.setText("Dice value: "+ diceVal);
                        playerOne.movePlayer(diceVal);
                        // winning condition
                        // if Player one win
                        if(playerOne.isWinner()){
                            diceLable.setText("Winner is "+ playerOne.getName());

                            playerOneTurn=false;
                            playerOneButton.setDisable(true);
                            playerOneLable.setText("");

                            playerTwoTurn=true;
                            playerTwoButton.setDisable(true);
                            playerTwoLable.setText("");

                            startButton.setDisable(false);
                            startButton.setText("Restart Game" );
                            gameStarted=false;
                        }
                        else{
                            // after Player one turn ... now we disable the turn of player one
                            // so his turn not repeat
                            playerOneTurn=false;
                            playerOneButton.setDisable(true);
                            playerOneLable.setText("");

                            playerTwoTurn=true;
                            playerTwoButton.setDisable(false);
                            playerTwoLable.setText("your turn "+playerTwo.getName());

                        }


                    }
                }
            }
        });

        // now for p2
        // action on pressing Player two buttoon

        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (gameStarted){
                    if(playerTwoTurn){
                        //  dice rolled , basis on number appeared on dice , player move

                        int diceVal=dice.getrolledDiceValue();
                        diceLable.setText("Dice value: "+ diceVal);
                        playerTwo.movePlayer(diceVal);
                        // winning condition
                        // if player two win
                        if(playerTwo.isWinner()){
                            diceLable.setText("Winner is "+ playerTwo.getName());

                            playerOneTurn=false;
                            playerOneButton.setDisable(true);
                            playerOneLable.setText("");

                            playerTwoTurn=true;
                            playerTwoButton.setDisable(true);
                            playerTwoLable.setText("");

                            startButton.setDisable(false);
                            startButton.setText("Restart Game");

                        }
                      else{
                          //after p2 turn , we disable p2 turn so p1 can move
                            playerOneTurn=true;
                            playerOneButton.setDisable(false);
                            playerOneLable.setText("your turn "+ playerOne.getName());

                            playerTwoTurn=false;
                            playerTwoButton.setDisable(true);
                            playerTwoLable.setText("");
                        }

                    }

                }


            }
        });

        // startButton Action
        // on clicking start Button certain actions will be performed

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted=true;
                diceLable.setText("Game Started");
                startButton.setDisable(true);

                playerOneTurn=true;
                playerOneLable.setText("your turn "+playerOne.getName());
                playerOneButton.setDisable(false);
                playerOne.startingPosition();


                playerTwoTurn=false;
                playerTwoLable.setText("");
                playerTwoButton.setDisable(true);
                playerTwo.startingPosition();
            }
        });


        // now we add all objects to pane...
        // so pane that is a container ,  root is object of pane so we  add to root

        root.getChildren().addAll(board,startButton, playerTwoButton, playerOneButton,
                playerOneLable,playerTwoLable,diceLable ,
                playerOne.getCoin() , playerTwo.getCoin()
                );

        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SnakeLadder.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake Ladder");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}