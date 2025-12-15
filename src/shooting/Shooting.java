package shooting;

// 基本構成
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
// レイアウト、コントロール
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.*;
import javafx.scene.shape.Circle; // player
import javafx.animation.Timeline; // timer
import javafx.event.EventHandler; // timer
import javafx.util.Duration; // timer
// キーイベント
import javafx.scene.input.KeyEvent;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;

public class Shooting extends Application {
    // X, Y （座標）
    final int X = 0;
    final int Y = 1;
    // WASD
    final int W = 0;
    final int S = 1;
    final int A = 2;
    final int D = 3;

    // ボタン（メニュー）
    final int BUTTON_SIZE = 30;
    HBox hb;
    Button[] gameMenu;

    // タイマー
    Label lb  = new Label("0");

    // プレイヤー
    Circle player;
    int[] playerPlace = new int[2]; // 0 → X, 1 → Y
    int playerRad = 50;
    // キー
    boolean[] keyFlag = new boolean[4]; // W, S, A, D

    // コンテナ
    Pane root;
    Timeline limitTimer;

    @Override
    public void start(Stage stage) throws Exception { // 例外処理
        stage.setTitle("Shooting!");
        stage.setFullScreen(true);

        // メニュー
        hb = new HBox();
        gameMenu = new Button[4];
        gameMenu[0] = new Button("Start");
        gameMenu[0].setFont(new Font(BUTTON_SIZE));
        // 1 → Pause
        gameMenu[1] = new Button("Pause");
        gameMenu[1].setFont(new Font(BUTTON_SIZE));
        // 2 → Reset
        gameMenu[2] = new Button("Reset");
        gameMenu[2].setFont(new Font(BUTTON_SIZE));
        // 3 → Exit
        gameMenu[3] = new Button("Exit");
        gameMenu[3].setFont(new Font(BUTTON_SIZE));
        gameMenu[3].setOnAction(event -> windowFin());
        hb.getChildren().addAll(gameMenu);

        // ! タイマー(作業中)
        // 参考 : https://www.xmisao.com/2014/09/25/javafx-timer.html
        limitTimer = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                lb.setText(String.valueOf(Integer.parseInt(lb.getText()) + 1));
            }
        }));
        limitTimer.setCycleCount(Timeline.INDEFINITE);
        limitTimer.play();

        // プレイヤー
        playerPlace[X] = 700;
        playerPlace[Y] = 550;
        player = new Circle(playerPlace[X], playerPlace[Y], playerRad); // X, Y, 半径

        // ゲームループ
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameLoop();
                // プレイヤー座標更新
                player.setCenterX(playerPlace[X]);
                player.setCenterY(playerPlace[Y]);
            }
        }.start();

        // 表示
        root = new Pane();
        root.getChildren().addAll(player, hb, lb);
        Scene scene = new Scene(root);
        // キーイベント
        scene.setOnKeyPressed(event -> doKeyAction(event));
        scene.setOnKeyReleased(event -> releaseKeyAction(event));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    /* メソッド */
    // ボタン
    // Exit
    void windowFin() {
        System.exit(0);
    }

    void gameLoop() {
        /* 移動 */
        // WASD 参考 : https://nompor.com/2018/01/18/post-2761/
        // 現在のシーンの大きさを取得
        double currentSceneWidth = root.getWidth();
        double currentSceneHeight = root.getHeight();

        final int[] moveSpeed = new int[2];
        moveSpeed[X] = 15;
        moveSpeed[Y] = 15;

        if (keyFlag[W]) { // 上
            playerPlace[Y] -= moveSpeed[Y];
        }
        if (keyFlag[S]) { // 下
            playerPlace[Y] += moveSpeed[Y];
        }
        if (keyFlag[A]) {
            playerPlace[X] -= moveSpeed[X];
        }
        if (keyFlag[D]) {
            playerPlace[X] += moveSpeed[X];
        }

        if (playerPlace[X] < playerRad) { // プレイヤーが上に飛び出す→プレイヤーの座標が上限より半径分下にある
            playerPlace[X] = playerRad;
        } else if (playerPlace[X] > currentSceneWidth - playerRad) { // プレイヤーが下に飛び出す→プレイヤーの座標が下限よりプレイヤーの座標が半径分上にある
            playerPlace[X] = (int) currentSceneWidth - playerRad;
        }

        if (playerPlace[Y] < playerRad) { // プレイヤーが上に飛び出す→プレイヤーの座標が上限より半径分下にある
            playerPlace[Y] = playerRad;
        } else if (playerPlace[Y] > currentSceneHeight - playerRad) { // プレイヤーが下に飛び出す→プレイヤーの座標が下限よりプレイヤーの座標が半径分上にある
            playerPlace[Y] = (int) currentSceneHeight - playerRad;
        }
        System.out.printf("X → %d, Y → %d\n", playerPlace[X], playerPlace[Y]);
    }

    void doKeyAction(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                keyFlag[W] = true;
                break;
            case S:
                keyFlag[S] = true;
                break;
            case A:
                keyFlag[A] = true;
                break;
            case D:
                keyFlag[D] = true;
                break;
            default:
                break;
        }
    }

    // キー離したとき
    void releaseKeyAction(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                keyFlag[W] = false;
                break;
            case S:
                keyFlag[S] = false;
                break;
            case A:
                keyFlag[A] = false;
                break;
            case D:
                keyFlag[D] = false;
                break;
            default:
                break;
        }
    }
}