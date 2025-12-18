package shooting;

// 基本構成
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
// レイアウト、コントロール
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.text.*;
import javafx.animation.Timeline; // timer
import javafx.event.EventHandler; // timer
import javafx.util.Duration; // timer
// キーイベント
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.animation.KeyFrame;

public class Shooting extends Application {
    // X, Y （座標）
    final int X = 0;
    final int Y = 1;

    // タイマー
    Label lb;
    final int TIMER_SIZE = 40;

    // コンテナ
    AnchorPane root;
    Timeline limitTimer;

    // マネージャー
    MenuManager menuManager;
    PlayerManager playerManager;

    @Override
    public void start(Stage stage) throws Exception { // 例外処理
        stage.setTitle("Shooting!");
        stage.setFullScreen(true);

        // コンテナ初期化
        root = new AnchorPane();

        // マネージャーの初期化
        menuManager = new MenuManager(this);
        playerManager = new PlayerManager(root);

        // タイマー
        // 参考 : https://www.xmisao.com/2014/09/25/javafx-timer.html
        lb = new Label("3"); // タイマーを60秒から開始 // TODO デバッグのために60秒を3秒に変更してるので戻す
        lb.setFont(new Font(TIMER_SIZE));
        limitTimer = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                lb.setText(String.valueOf(Integer.parseInt(lb.getText()) - 1));
                lb.setFont(new Font(TIMER_SIZE));

                if (lb.getText().equals("0")) {
                    limitTimer.stop();
                    System.out.println("制限時間が終了しました。");
                    // TODO スコアを表示させる
                }
            }
        }));
        limitTimer.setCycleCount(Timeline.INDEFINITE);
        limitTimer.play();

        // 表示
        root.getChildren().addAll(playerManager.getPlayer(), menuManager.getMenuBox(), lb); // プレイヤー, ボタン, タイマー
        // メニューは左上、タイマーは右上に固定
        AnchorPane.setTopAnchor(menuManager.getMenuBox(), 10.0);
        AnchorPane.setLeftAnchor(menuManager.getMenuBox(), 10.0);
        AnchorPane.setTopAnchor(lb, 10.0);
        AnchorPane.setRightAnchor(lb, 10.0);
        Scene scene = new Scene(root);
        // キーイベント
        scene.setOnKeyPressed(event -> playerManager.doKeyAction(event));
        scene.setOnKeyReleased(event -> playerManager.releaseKeyAction(event));
        stage.setScene(scene);
        stage.show();

        // ゲームループ
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                playerManager.gameLoop();
            }
        }.start();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
