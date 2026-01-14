package shooting;

import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class MenuManager {
  // コンテナ
  private HBox menuRoot;
  // メニュー
  private Button[] gameMenu;
  private final int BUTTON_SIZE = 30;
  // 各ボタンの配列
  private final static int START = 0;
  private final static int PAUSE = 1;
  private final static int RESET = 2;
  private final static int EXIT = 3;

  public MenuManager(TimerManager timerManager, PlayerManager playerManager) {
    // コンテナ
    menuRoot = new HBox();
    // メニュー
    gameMenu = new Button[4];

    // 0 → Start
    gameMenu[START] = new Button("Start"); // スタート
    gameMenu[START].setFont(new Font(BUTTON_SIZE));
    gameMenu[START].setOnAction(event -> {
      // 開始させない
      if (timerManager.getIsTimerStarted() && timerManager.getIsPause()) { // タイマーが開始されている
        return;
      } else {
        timerManager.timerStart(); // timerを開始する。
      }
    });

    // 1 → Pause
    gameMenu[PAUSE] = new Button("Pause");
    gameMenu[PAUSE].setFont(new Font(BUTTON_SIZE));
    gameMenu[PAUSE].setOnAction(event -> {
      if (!timerManager.getIsTimerStarted()) { // タイマーが開始されていないとき
        return;
      } else if (!timerManager.getIsPause()) { // タイマーが開始されていて、一時停止されていない
        gameMenu[PAUSE].setText("Resume");
      } else { // タイマーが開始されていて、一時停止中
        gameMenu[PAUSE].setText("Pause");
      }
      timerManager.timerPause(); // 一時停止・再開
    });
    // 2 → Reset
    gameMenu[RESET] = new Button("Reset");
    gameMenu[RESET].setFont(new Font(BUTTON_SIZE));
    gameMenu[RESET].setOnAction(event -> {
      timerManager.timerReset();
      gameMenu[PAUSE].setText("Pause");
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
