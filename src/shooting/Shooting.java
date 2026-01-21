package shooting;

// 基本構成
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.animation.AnimationTimer;

public class Shooting extends Application {
    // コンテナ
    private AnchorPane root;

    // マネージャー
    private MenuManager menuManager;
    private PlayerManager playerManager;
    private TimerManager timerManager;
    private EnemyManager enemyManager;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Shooting!");
        stage.setFullScreen(true);

        // コンテナ
        root = new AnchorPane();

        timerManager = new TimerManager();
        playerManager = new PlayerManager(root);
        menuManager = new MenuManager(timerManager, playerManager);
        enemyManager = new EnemyManager(root, playerManager, timerManager);

        // 表示物の追加
        root.getChildren().addAll(
                playerManager.getPlayer(),
                menuManager.getMenuLine(),
                timerManager.getTimer());

        // レイアウト設定
        AnchorPane.setTopAnchor(menuManager.getMenuLine(), 10.0);
        AnchorPane.setLeftAnchor(menuManager.getMenuLine(), 10.0);
        AnchorPane.setTopAnchor(timerManager.getTimer(), 10.0);
        AnchorPane.setRightAnchor(timerManager.getTimer(), 10.0);

        Scene scene = new Scene(root);

        // キーイベントの登録
        scene.setOnKeyPressed(event -> playerManager.doKeyAction(event));
        scene.setOnKeyReleased(event -> playerManager.releaseKeyAction(event));

        stage.setScene(scene);
        stage.show();

        // ゲームループ
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                // タイマーが開始されており、かつ一時停止中でない場合のみゲームを進行させる
                if (timerManager.getIsTimerStarted() && !timerManager.getIsPause()) {
                    // プレイヤーの移動処理
                    playerManager.gameLoop();

                    // 敵の移動・管理処理（修正後のEnemyManagerに実装したメソッド）
                    enemyManager.updateEnemies();
                }
            }
        }.start();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
