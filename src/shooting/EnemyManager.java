// getKeyFrames public final ObservableList<KeyFrame>getKeyFrames()このTimelineのKeyFramesを返します。

package shooting;

// root
import javafx.scene.layout.Pane;
// Animation
import javafx.animation.KeyFrame;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
// Enemy
import javafx.scene.shape.Rectangle;
import java.util.Random;

import javax.swing.Action;

public class EnemyManager {
  private Pane enemyRoot = new Pane();

  private Rectangle[] enemy = new Rectangle[100];
  private final double ENEMYSIZE = 20.0;
  private int[][] enemyPlace = new int[2][100];
  private final int X = 0;
  private final int Y = 1;

  private final int SPAWN_ENENY_VALUE = 5; // enemySpawn()で呼び出される敵の数

  private int nowEnemy = 0; // nowEnemy番までの敵が発生した

  public EnemyManager(TimerManager timerManager, MenuManager manuManager) {
    Timeline timeline = timerManager.getTimerTimeline();
    KeyFrame spawnFrame1 = new KeyFrame(Duration.seconds(10), e -> {
      enemySpawn(new Random());
    });

    KeyFrame spawnFrame2 = new KeyFrame(Duration.seconds(20), e -> {
      enemySpawn(new Random());
    });

    timeline.getKeyFrames().addAll(spawnFrame1, spawnFrame2);
  }

  // 呼び出されるごとにenemyを5体ランダムな位置にさせる
  void enemySpawn(Random random) {
    // 現在のシーンの大きさを取得
    double currentSceneWidth = enemyRoot.getWidth();
    double currentSceneHeight = enemyRoot.getHeight();

    for (int i = nowEnemy; i < nowEnemy + SPAWN_ENENY_VALUE; i++) { // ? nowEnemuの数をforの中で変えるとiの値も変わる？
      enemyPlace[X][i] = random.nextInt((int) currentSceneWidth - (int) ENEMYSIZE) + (int) ENEMYSIZE;
      enemyPlace[Y][i] = random.nextInt((int) currentSceneHeight - (int) ENEMYSIZE) + (int) ENEMYSIZE;

      enemy[i] = new Rectangle(ENEMYSIZE, ENEMYSIZE);
      enemy[i].setX(enemyPlace[X][i]);
      enemy[i].setY(enemyPlace[Y][i]);
      nowEnemy++;

      System.out.printf("spawned enemy => %d\n", i); // 何番のenemyがスポーンしたか // ! remove code
    }
  }

  public Pane getEnemyRoot() {
    return enemyRoot;
  }
}
