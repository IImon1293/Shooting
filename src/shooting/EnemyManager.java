package shooting;

import java.util.ArrayList;
import javafx.animation.Timeline;
import javafx.scene.shape.Rectangle;

public class EnemyManager {
  private TimerManager timerManager; // タイマーの制御と同期させる

  private Timeline enemyTimeline = new Timeline();
  private ArrayList<Rectangle> enemy = new ArrayList<>();

  public EnemyManager() {
  }

  private void spawnEnemy() {

  }
}
