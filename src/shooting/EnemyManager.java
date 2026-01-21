package shooting;

// Pass to Mac@IImon

import java.util.ArrayList;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import javafx.scene.paint.Color;

public class EnemyManager {
  private AnchorPane root;
  private Random rd;
  private Timeline enemyTimeline;
  private ArrayList<Rectangle> enemyList;
  private PlayerManager playerManager;

  // 出現間隔 (ms)
  private final int SPAWN_INTERVAL = 2000;
  // 一度に出現する数
  private final int SPAWN_NUM = 10;

  // 敵の大きさ (30の1.5倍 = 45、以前のコードでは70に設定されていたため、適宜調整してください)
  private final double ENEMY_SIZE = 70;

  // 速度の設定
  private final double MIN_SPEED = 2.0;
  // 最大速度 (元が7.0の場合、0.7倍で4.9)
  private final double MAX_SPEED = 4.9;

  public EnemyManager(AnchorPane root, PlayerManager playerManager, TimerManager timerManager) {
    this.root = root;
    this.playerManager = playerManager;
    this.rd = new Random();
    this.enemyList = new ArrayList<>();

    // 敵生成のタイムライン設定
    enemyTimeline = new Timeline(new KeyFrame(Duration.millis(SPAWN_INTERVAL), event -> {
      // タイマーが開始されており、かつ一時停止中でない場合のみ生成
      if (timerManager.getIsTimerStarted() && !timerManager.getIsPause()) {
        spawnEnemy();
      }
    }));

    enemyTimeline.setCycleCount(Timeline.INDEFINITE);
    enemyTimeline.play();
  }

  /**
   * 敵を生成し、画面とリストに追加する
   */
  private void spawnEnemy() {
    for (int i = 0; i < SPAWN_NUM; i++) {
      // X座標をウィンドウ幅に合わせてランダム設定
      double spawnX = rd.nextDouble() * (playerManager.getWindowSize_X() - ENEMY_SIZE);
      // Y座標は画面上端（外側）から出現
      double spawnY = -ENEMY_SIZE;

      Rectangle newEnemy = new Rectangle(spawnX, spawnY, ENEMY_SIZE, ENEMY_SIZE);
      newEnemy.setFill(Color.BLACK);

      // 個別のスピードをランダムに設定して保持
      double randomSpeed = MIN_SPEED + (MAX_SPEED - MIN_SPEED) * rd.nextDouble();
      newEnemy.setUserData(randomSpeed);

      enemyList.add(newEnemy);
      root.getChildren().add(newEnemy);
    }
  }

  /**
   * 敵の移動、当たり判定、画面外消去を処理する
   */
  public void updateEnemies() {
    for (int i = 0; i < enemyList.size(); i++) {
      Rectangle e = enemyList.get(i);

      // 各敵が持つ個別の速度を取得
      double speed = (double) e.getUserData();

      // 移動処理（落下）
      e.setY(e.getY() + speed);

      // プレイヤーとの当たり判定
      Shape intersect = Shape.intersect(playerManager.getPlayer(), e);
      if (intersect.getBoundsInLocal().getWidth() > 0) {
        // 衝突したら敵を消す
        removeEnemy(e, i);
        i--;
        continue;
      }

      // 画面下端に出た場合の消去判定
      if (e.getY() > root.getHeight()) {
        removeEnemy(e, i);
        i--;
      }
    }
  }

  public void clearEnemies() {
    for (Rectangle e : enemyList) { // <-0
      root.getChildren().remove(e);
    }
    enemyList.clear();
  }

  private void removeEnemy(Rectangle e, int index) {
    root.getChildren().remove(e);
    enemyList.remove(index);
  }
}
