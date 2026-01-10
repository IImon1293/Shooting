package shooting;

// 基本構成
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.animation.AnimationTimer;

public class Shooting extends Application {
    // コンテナ
    AnchorPane root;

    // マネージャー
    MenuManager menuManager;
    PlayerManager playerManager;
    TimerManager timerManager;

    @Override
    public void start(Stage stage) throws Exception { // 例外処理
        stage.setTitle("Shooting!");
        stage.setFullScreen(true);

        // コンテナ初期化
        root = new AnchorPane();

        // マネージャーの初期化
        timerManager = new TimerManager();
        playerManager = new PlayerManager(root);
        menuManager = new MenuManager(timerManager, playerManager);
        // 表示
        root.getChildren().addAll(playerManager.getPlayer(), menuManager.getMenuLine(), timerManager.getTimer()); // プレイヤー,メニュー,タイマー
                                                                                                                  // //
        AnchorPane.setTopAnchor(menuManager.getMenuLine(), 10.0);
        AnchorPane.setLeftAnchor(menuManager.getMenuLine(), 10.0);
        AnchorPane.setTopAnchor(timerManager.getTimer(), 10.0); // タイマー用のLabelの位置を調整
        AnchorPane.setRightAnchor(timerManager.getTimer(), 10.0);
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
