package shooting;

import java.sql.Time;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class EnemyManager {
  // インスタンス
  private Timeline enemyTimeline = new Timeline();
  private ArrayList<Rectangle> enemy = new ArrayList<>();

  // 敵を生成する間隔
  private

  public EnemyManager(TimerManager timer) {
    if (timer.getIsTimerStarted() && timer.getIsPause()) { // タイマーが進行しているとき
      enemyTimeline.play();
    } else {
      enemyTimeline.pause();
    }

    enemyTimeline = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
      public void handle(ActionEvent actionEvent) {

      }
    }));
  }

  private void spawnEnemy() {

  }
}
