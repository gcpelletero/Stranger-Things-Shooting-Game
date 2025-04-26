package strangerthings;
//GAME SCENE



import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Label;
import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//class that manages the scenes and UI elements of the game

public class Game {

	private Stage stage;
	private Scene splashScene;		// the splash scene
	private Scene gameScene;
	private Group root;
	private Canvas canvas;			// the canvas where the animation happens
	private ImageView view;
	private GraphicsContext graphics;
	private Image background;

    private Image about1 = new Image("aboutus.png", Game.WINDOW_WIDTH,Game.WINDOW_HEIGHT, false, false);
    private Image instructions1 = new Image("instruct.png", Game.WINDOW_WIDTH,Game.WINDOW_HEIGHT, false, false);



	private Image a = new Image("INSTRUCTIONS1.png");

	// Constants for window dimensions and button positions
	public static final int WINDOW_HEIGHT = 500;
	public static final int WINDOW_WIDTH = 800;
	public static final int STARTGAME_BUTTON_X = 10;
	public static final int STARTGAME_BUTTON_Y = 100;

    public static final int STARTGAME_BUTTON_WIDTH = 100;
    public static final int STARTGAME_BUTTON_HEIGHT = 20;

    public static final int INSTRUCTION_BUTTON_WIDTH = 100;
    public static final int INSTRUCTION_BUTTON_HEIGHT = 20;

    public static final int ABOUTUS_BUTTON_X = 300;
    public static final int ABOUTUS_BUTTON_Y = -200;
    public static final int ABOUTUS_BUTTON_WIDTH = 100;
    public static final int ABOUTUS_BUTTON_HEIGHT = 20;

    public static final int BACK_BUTTON_X = 320;
    public static final int BACK_BUTTON_Y = -190;
    public static final int BACK_BUTTON_WIDTH = 50;
    public static final int BACK_BUTTON_HEIGHT = 20;

    Font pixelfont;

	// Game constructor initializes scene and graphics components
	public Game() {
		this.root = new Group();
		this.canvas = new Canvas( Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT );
        this.gameScene = new Scene( root );
		this.background = new Image("background.png", Game.WINDOW_WIDTH,Game.WINDOW_HEIGHT, false, false);
		this.view = new ImageView();
		this.graphics = canvas.getGraphicsContext2D();
        this.root.getChildren().add( this.canvas );
	}

	// Sets the main stage and initializes the splash scene
	public void setStage(Stage stage) {
		this.stage = stage;
		stage.setTitle( "Stranger Things" );

		this.initSplash(stage);	// initializes the Splash Screen with the New Game button

		stage.setScene( this.splashScene );
		stage.show();
	}

	// Initializes the splash scene with buttons for New Game, About Us, and How to Play
	private void initSplash(Stage stage) {
		StackPane root = new StackPane();
        root.getChildren().addAll(this.createCanvas(),this.createVBox());
        this.splashScene = new Scene(root);
	}

	// Creates a canvas for rendering background images
	private Canvas createCanvas() {
    	Canvas canvas = new Canvas(Game.WINDOW_WIDTH,Game.WINDOW_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Image bg = new Image("background.png");
        gc.drawImage(bg, 0, 0);
        return canvas;
    } //method directly draws background image onto a Canvas, therefore more control over the rendering process
	  //Canvas for image rendering, animations, additional graphical elements
	  //ImageView for static background image with UI elements on top

	// Creates a VBox with buttons for New Game, About Us, and How to Play
    private VBox createVBox() {
    	VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        Image imagestart = new Image("new.png", STARTGAME_BUTTON_WIDTH, STARTGAME_BUTTON_HEIGHT, false, false);
        ImageView view = new ImageView(imagestart);

        Image imageinst = new Image("about.png", INSTRUCTION_BUTTON_WIDTH, INSTRUCTION_BUTTON_HEIGHT, false, false);
        ImageView view2 = new ImageView(imageinst);

        Image imageab = new Image("instructions.png", ABOUTUS_BUTTON_WIDTH, ABOUTUS_BUTTON_HEIGHT, false, false);
        ImageView view3 = new ImageView(imageab);

        Button b1 = new Button();
        b1.setBackground(null);
        b1.setGraphic(view);
        b1.setTranslateX(STARTGAME_BUTTON_X);
        b1.setTranslateY(STARTGAME_BUTTON_Y);

        Button b2 = new Button();
        b2.setBackground(null);
        b2.setGraphic(view2);
        b2.setTranslateX(STARTGAME_BUTTON_X);
        b2.setTranslateY(STARTGAME_BUTTON_Y);

        Button b3 = new Button();
        b3.setBackground(null);
        b3.setGraphic(view3);
        b3.setTranslateX(STARTGAME_BUTTON_X);
        b3.setTranslateY(STARTGAME_BUTTON_Y);

        vbox.getChildren().addAll(b1,b2,b3);

        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                setGame(stage);		//changes the scene into the game scene
            };
        });

        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	aboutUs(stage);		//changes the scene into the developers of the game
            };
        });

        b3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	howToPlay(stage); //changes the scene into the instructions of the game
            };
        });
        return vbox;
    }

	// Sets the game scene and starts the GameTimer
	void setGame(Stage stage) {
        stage.setScene( this.gameScene );

        GraphicsContext gc = this.canvas.getGraphicsContext2D();	//we will pass this gc to be able to draw on this Game's canvas

        GameTimer gameTimer = new GameTimer(gameScene, gc);
        gameTimer.start();			// this internally calls the handle() method of our GameTimer

	}

	// Displays the How to Play scene
	private void howToPlay(Stage stage) {

	    StackPane root = new StackPane(); //create new stackPane for how to play scene

	    //background
	    Scene sceneInstructions = new Scene(root);
	    ImageView instructionView = new ImageView(instructions1);
	    root.getChildren().add(instructionView);
	    stage.setScene(sceneInstructions);
	    stage.show();

	    // Create a VBox to organize UI elements vertically
	    VBox vbox = new VBox();
	    vbox.setAlignment(Pos.CENTER);
	    vbox.setPadding(new Insets(10));
	    vbox.setSpacing(8);

        Image imagestart = new Image("back.png", BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT, false, false);
        ImageView view4 = new ImageView(imagestart);

	    //button for navigation
	    Button backButton = new Button();

        backButton.setBackground(null);
        backButton.setGraphic(view4);
        backButton.setTranslateX(BACK_BUTTON_X);
        backButton.setTranslateY(BACK_BUTTON_Y);

	    vbox.getChildren().addAll(backButton);

	    GraphicsContext gc = this.canvas.getGraphicsContext2D();
	    gc.drawImage(a, 400, 400);

	    backButton.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent e) {
	            //return to the main menu when the Back button is clicked
	            initSplash(stage);
	            stage.setScene(splashScene); //set the splashScene with a new StackPane
	        }
	    });

	    root.getChildren().setAll(instructionView, vbox); //set content of the StackPane

	}

	// Displays the About Us scene
	private void aboutUs(Stage stage) {
	    StackPane root = new StackPane(); //create new stackPane for how to play scene

	    //background
	    Scene sceneInstructions = new Scene(root);
	    ImageView instructionView = new ImageView(about1);
	    root.getChildren().add(instructionView);
	    stage.setScene(sceneInstructions);
	    stage.show();

	    // Create a VBox to organize UI elements vertically
	    VBox vbox = new VBox();
	    vbox.setAlignment(Pos.CENTER);
	    vbox.setPadding(new Insets(10));
	    vbox.setSpacing(8);

        Image imagestart = new Image("back.png", BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT, false, false);
        ImageView view5 = new ImageView(imagestart);

	    //button for navigation
	    Button backButton = new Button();

        backButton.setBackground(null);
        backButton.setGraphic(view5);
        backButton.setTranslateX(BACK_BUTTON_X);
        backButton.setTranslateY(BACK_BUTTON_Y);

	    vbox.getChildren().addAll(backButton);

	    backButton.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent e) {
	            //return to the main menu when the Back button is clicked
	            initSplash(stage);
	            stage.setScene(splashScene); //set the splashScene with a new StackPane
	        }
	    });

	    root.getChildren().setAll(instructionView, vbox); //set content of the StackPane
	}


}
