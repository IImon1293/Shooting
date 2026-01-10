// getKeyFrames
// public final ObservableList<KeyFrame> getKeyFrames()
// このTimelineのKeyFramesを返します。

// package shooting;

// // root
// import javafx.scene.layout.Pane;
// // Animation
// import javafx.animation.KeyFrame;
// import javafx.util.Duration;
// import javafx.animation.Timeline;
// import javafx.event.ActionEvent;
// // Enemy
// import javafx.scene.shape.Rectangle;
// import java.util.Random;

// import javax.swing.Action;

// public class EnemyManager {
// Pane enemyRoot = new Pane();

// Rectangle[] enemy = new Rectangle[99]; // 敵(0 ~ 99 = 100体)
// int[][] enemyPlace = new int[2][99]; // 敵の座標([XY][敵番号])
// final int X = 0;
// final int Y = 1;
// int nowEnemy = 0; // nowEnemy番までの敵が発生した

// public EnemyManager(TimerManager timerManager, MenuManager manuManager,
// Random rd) {
// // TODO TimeLineでSpawnするようにする。
// if (timerManager.timerStarted && !manuManager.isPause) { //
// タイマーが開始されていて、ポーズ状態ではないとき

// }

// }

// // 呼び出されるごとにenemyを5体ランダムな位置にさせる
// void enemySpawn(Random random, ActionEvent e) {
// for (int i = nowEnemy; i < nowEnemy + 5; i++) { // ?
// nowEnemuの数をforの中で変えるとiの値も変わる？
// enemyPlace[X][i] = random.nextInt();
// enemyPlace[Y][i] = random.nextInt();

// enemy[i].setX(enemyPlace[X][i]);
// enemy[i].setY(enemyPlace[Y][i]);
// nowEnemy++;

// System.out.printf("spawned enemy => %d\n", i); // 何番のenemyがスポーンしたか // !
// remove code
// }

// }
// }
