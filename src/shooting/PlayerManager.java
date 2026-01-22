package shooting;

import javafx.scene.shape.Circle;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class PlayerManager {
  // WASD
  private final int W = 0;
  private final int S = 1;
  private final int A = 2;
  private final int D = 3;

  // プレイヤー
  private Circle player;
  private final int PLAYER_RAD = 90;
  private final double SPAWNPLACE_X = 700.0;
  private final double SPAWNPLACE_Y = 550.0;
  private double playerPlace_X;
  private double playerPlace_Y;
  // 移動速度
  private final int MOVESPEED_X = 30;
  private final int MOVESPEED_Y = 30;

  // キー
  private boolean[] keyFlag = new boolean[4]; // W, S, A, D

  private AnchorPane root;

  public PlayerManager(AnchorPane root) {
    this.root = root;
    // プレイヤー初期化
    playerPlace_X = SPAWNPLACE_X;
    playerPlace_Y = SPAWNPLACE_Y;
    player = new Circle(playerPlace_X, playerPlace_Y, PLAYER_RAD); // X, Y, 半径
  }

  public Circle getPlayer() {
    return player;
  }

  public void gameLoop() {
    /* 移動 */
    // WASD 参考 : https://nompor.com/2018/01/18/post-2761/
    // 現在のシーンの大きさを取得
    double currentSceneWidth = root.getWidth();
    double currentSceneHeight = root.getHeight();

    if (keyFlag[W]) { // 上
      playerPlace_Y -= MOVESPEED_Y;
    }
    if (keyFlag[S]) { // 下
      playerPlace_Y += MOVESPEED_Y;
    }
    if (keyFlag[A]) {
      playerPlace_X -= MOVESPEED_X;
    }
    if (keyFlag[D]) {
      playerPlace_X += MOVESPEED_X;
    }

    if (playerPlace_X < PLAYER_RAD) { // プレイヤーが上に飛び出す→プレイヤーの座標が上限より半径分下にある
      playerPlace_X = PLAYER_RAD;
    } else if (playerPlace_X > currentSceneWidth - PLAYER_RAD) { // プレイヤーが下に飛び出す→プレイヤーの座標が下限よりプレイヤーの座標が半径分上にある
      playerPlace_X = currentSceneWidth - PLAYER_RAD;
    }

    if (playerPlace_Y < PLAYER_RAD) { // プレイヤーが上に飛び出す→プレイヤーの座標が上限より半径分下にある
      playerPlace_Y = PLAYER_RAD;
    } else if (playerPlace_Y > currentSceneHeight - PLAYER_RAD) { // プレイヤーが下に飛び出す→プレイヤーの座標が下限よりプレイヤーの座標が半径分上にある
      playerPlace_Y = currentSceneHeight - PLAYER_RAD;
    }

    // プレイヤー座標更新
    player.setCenterX(playerPlace_X);
    player.setCenterY(playerPlace_Y);
  }

  public void doKeyAction(KeyEvent event) {
    switch (event.getCode()) {
      case W:
        keyFlag[W] = true;
        break;
      case S:
        keyFlag[S] = true;
        break;
      case A:
        keyFlag[A] = true;
        break;
      case D:
        keyFlag[D] = true;
        break;
      default:
        break;
    }
  }

  // キー離したとき
  public void releaseKeyAction(KeyEvent event) {
    switch (event.getCode()) {
      case W:
        keyFlag[W] = false;
        break;
      case S:
        keyFlag[S] = false;
        break;
      case A:
        keyFlag[A] = false;
        break;
      case D:
        keyFlag[D] = false;
        break;
      default:
        break;
    }
  }

  // プレイヤーの大きさを渡す
  public double getPlayerRad() {
    return (double) PLAYER_RAD;
  }

  // 画面の大きさを取得する
  public double getWindowSize_X() {
    return root.getWidth();
  }

  public double getWindowSize_Y() {
    return root.getHeight();
  }
}
