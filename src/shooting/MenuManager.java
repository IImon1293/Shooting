package shooting;

import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class MenuManager {
  // コンテナ
  HBox menuRoot;
  // メニュー
  Button[] gameMenu;
  final int BUTTON_SIZE = 30;
  // ボタンのための変数
  boolean isPause = false;
  // 各ボタンの配列
  final int START = 0;
  final int PAUSE = 1;
  final int RESET = 2;
  final int EXIT = 3;

  public MenuManager(TimerManager timerManager, PlayerManager playerManager) {
    // コンテナ
    menuRoot = new HBox();
    // メニュー
    gameMenu = new Button[4];

    // 0 → Start
    gameMenu[START] = new Button("Start"); // スタート
    gameMenu[START].setFont(new Font(BUTTON_SIZE));
    gameMenu[START].setOnAction(event -> {
      if (timerManager.timerStarted) { // タイマーが重複して起動しないようにする
        System.out.printf("timer is already started.\n");
        return;
      }
      System.out.printf("timer is started.\n");
      timerManager.timerStart(isPause); // timerを開始する。
    });
    // 1 → Pause
    gameMenu[PAUSE] = new Button("Pause");
    gameMenu[PAUSE].setFont(new Font(BUTTON_SIZE));
    gameMenu[PAUSE].setOnAction(event -> {
      if (isPause == false) {
        // 一時停止中
        isPause = true;
        gameMenu[PAUSE].setText("Resume");
      } else if (isPause == true || Integer.parseInt(timerManager.timerLb.getText()) <= 0) { // 制限時間が終了したら戻す
        isPause = false;
        gameMenu[PAUSE].setText("Pause");
      } else {
        isPause = false;
        gameMenu[PAUSE].setText("Pause");
      }

      timerManager.timerPause(isPause); // 一時停止・再開
    });
    // 2 → Reset
    gameMenu[RESET] = new Button("Reset");
    gameMenu[RESET].setFont(new Font(BUTTON_SIZE));
    gameMenu[RESET].setOnAction(event -> {
      timerManager.timerReset();
    });
    // 3 → Exit
    gameMenu[EXIT] = new Button("Exit");
    gameMenu[EXIT].setFont(new Font(BUTTON_SIZE));
    gameMenu[EXIT].setOnAction(event -> windowFin());
    menuRoot.getChildren().addAll(gameMenu);
  }

  /* Exit */
  void windowFin() {
    System.out.println("Exit Game");
    System.exit(0);
  }

  public HBox getMenuLine() { // ゲッタ
    return menuRoot;
  }
}
