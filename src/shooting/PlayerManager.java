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
  private int playerRad = 50;
  private final double SPAWNPLACE_X = 700.0;
  private final double SPAWNPLACE_Y = 550.0;
  private double playerPlace_X;
  private double playerPlace_Y;
  // 移動速度
  private final int MOVESPEED_X = 15;
  private final int MOVESPEED_Y = 15;

  // キー
  private boolean[] keyFlag = new boolean[4]; // W, S, A, D

  private AnchorPane root;

  public PlayerManager(AnchorPane root) {
    this.root = root;
    // プレイヤー初期化
    playerPlace_X = SPAWNPLACE_X;
    playerPlace_Y = SPAWNPLACE_Y;
    player = new Circle(playerPlace_X, playerPlace_Y, playerRad); // X, Y, 半径
  }

  public Circle getPlayer() {
    return player;
  }

  // プレイヤーは常に動くことができる。
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

    if (playerPlace_X < playerRad) { // プレイヤーが上に飛び出す→プレイヤーの座標が上限より半径分下にある
      playerPlace_X = playerRad;
    } else if (playerPlace_X > currentSceneWidth - playerRad) { // プレイヤーが下に飛び出す→プレイヤーの座標が下限よりプレイヤーの座標が半径分上にある
      playerPlace_X = currentSceneWidth - playerRad;
    }

    if (playerPlace_Y < playerRad) { // プレイヤーが上に飛び出す→プレイヤーの座標が上限より半径分下にある
      playerPlace_Y = playerRad;
    } else if (playerPlace_Y > currentSceneHeight - playerRad) { // プレイヤーが下に飛び出す→プレイヤーの座標が下限よりプレイヤーの座標が半径分上にある
      playerPlace_Y = currentSceneHeight - playerRad;
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
}
