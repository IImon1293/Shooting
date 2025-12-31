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

  public MenuManager(TimerManager timerManager) {
    // コンテナ
    menuRoot = new HBox();
    // メニュー
    gameMenu = new Button[4];

    // 0 → Start
    gameMenu[0] = new Button("Start"); // スタート
    gameMenu[0].setFont(new Font(BUTTON_SIZE));
    gameMenu[0].setOnAction(event -> {
      if (timerManager.timerStarted) {
        System.out.printf("タイマーは開始されている。\n");
        return;
      }
      System.out.printf("タイマーが開始された\n");
      timerManager.timerStart();
    });
    // 1 → Pause
    gameMenu[1] = new Button("Pause");
    gameMenu[1].setFont(new Font(BUTTON_SIZE));
    gameMenu[1].setOnAction(event -> {
      timerManager.timerPause();
    });
    // 2 → Reset
    gameMenu[2] = new Button("Reset");
    gameMenu[2].setFont(new Font(BUTTON_SIZE));
    gameMenu[2].setOnAction(event -> {
      // TODO: ゲームをリセット
    });
    // 3 → Exit
    gameMenu[3] = new Button("Exit");
    gameMenu[3].setFont(new Font(BUTTON_SIZE));
    gameMenu[3].setOnAction(event -> windowFin());
    menuRoot.getChildren().addAll(gameMenu);
  }

  /* Exit */
  void windowFin() {
    System.out.println("ゲームを終了");
    System.exit(0);
  }

  public HBox getMenuLine() { // ゲッタ
    return menuRoot;
  }
}
