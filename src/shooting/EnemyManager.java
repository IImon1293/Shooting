package shooting;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class EnemyManager extends TimerManager {
  // インスタンス
  private AnchorPane root; // コンテナ
  private Random rd;
  private Timeline enemyTimeline;
  private ArrayList<Rectangle> enemy;
  private PlayerManager playerManager;

  private double enemyPlace_X; // X座標はランダムにするので可変
  private double enemyPlace_Y; // Y座標は固定

  // 敵を生成する間隔
  private final int SPAWN_INTERVAL = 5000; // 単位 : ms
  // 敵を一度に生成する数
  private final int SPAWN_NUM = 5;

  public EnemyManager(TimerManager timer) {
    enemyTimeline = new Timeline();

    if (timer.getIsTimerStarted() && timer.getIsPause()) { // タイマーが進行しているとき
      enemyTimeline.play();
    } else { // タイマーが停止しているとき
      enemyTimeline.pause();
    }

    enemyTimeline = new Timeline(new KeyFrame(Duration.millis(SPAWN_INTERVAL), new EventHandler<ActionEvent>() {
      public void handle(ActionEvent actionEvent) {
        spawnEnemy(playerManager);
      }
    }));
  }

  private void spawnEnemy(PlayerManager playerManager) {
    for (int i = 0; i < SPAWN_NUM; i++) {
      // X座標をRandomで設定
      enemyPlace_X = rd.nextDouble(playerManager.getWindowSize_X()
          - playerManager.getPlayerRad())
          + playerManager.getPlayerRad();
      // Y座標を設定
      // ? コンストラクタでfinalで宣言するべき？
      enemyPlace_Y = playerManager.getWindowSize_Y();
      // 敵を生成する
      Rectangle newEnemy = new Rectangle(enemyPlace_X, enemyPlace_Y);
      enemy.add(newEnemy); // ArrayListに追加
    }
  }
}
