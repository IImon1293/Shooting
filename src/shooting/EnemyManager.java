package shooting;

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

  private final int SPAWN_INTERVAL = 2000;
  private final int SPAWN_NUM = 10;
  private final double ENEMY_SIZE = 70;
  private final double MIN_SPEED = 2.0;
  private final double MAX_SPEED = 4.9;

  public EnemyManager(AnchorPane root, PlayerManager playerManager, TimerManager timerManager) {
    this.root = root;
    this.playerManager = playerManager;
    this.rd = new Random();
    this.enemyList = new ArrayList<>();

    enemyTimeline = new Timeline(new KeyFrame(Duration.millis(SPAWN_INTERVAL), event -> {
      if (timerManager.getIsTimerStarted() && !timerManager.getIsPause()) {
        spawnEnemy();
      }
    }));

    enemyTimeline.setCycleCount(Timeline.INDEFINITE);
    enemyTimeline.play();
  }

  // --- 追加: リセット処理 ---
  public void clearEnemies() {
    // 1. Timelineを停止して最初に戻す
    enemyTimeline.stop();

    // 2. 画面上のRectangleをすべて削除
    for (Rectangle e : enemyList) {
      root.getChildren().remove(e);
    }

    // 3. 管理リストを空にする
    enemyList.clear();

    // 4. 再びplay()を呼ぶことで、次のStart時からまた新しい間隔で生成が始まる
    enemyTimeline.play();
  }

  private void spawnEnemy() {
    for (int i = 0; i < SPAWN_NUM; i++) {
      double spawnX = rd.nextDouble() * (playerManager.getWindowSize_X() - ENEMY_SIZE);
      double spawnY = -ENEMY_SIZE;
      Rectangle newEnemy = new Rectangle(spawnX, spawnY, ENEMY_SIZE, ENEMY_SIZE);
      newEnemy.setFill(Color.BLACK);
      double randomSpeed = MIN_SPEED + (MAX_SPEED - MIN_SPEED) * rd.nextDouble();
      newEnemy.setUserData(randomSpeed);
      enemyList.add(newEnemy);
      root.getChildren().add(newEnemy);
    }
  }

  public void updateEnemies() {
    for (int i = 0; i < enemyList.size(); i++) {
      Rectangle e = enemyList.get(i);
      double speed = (double) e.getUserData();
      e.setY(e.getY() + speed);
      Shape intersect = Shape.intersect(playerManager.getPlayer(), e);
      if (intersect.getBoundsInLocal().getWidth() > 0) {
        removeEnemy(e, i);
        i--;
        continue;
      }
      if (e.getY() > root.getHeight()) {
        removeEnemy(e, i);
        i--;
      }
    }
  }

  private void removeEnemy(Rectangle e, int index) {
    root.getChildren().remove(e);
    enemyList.remove(index);
  }
}