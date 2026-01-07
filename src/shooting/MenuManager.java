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

  public MenuManager(TimerManager timerManager, PlayerManager playerManager) {
    // コンテナ
    menuRoot = new HBox();
    // メニュー
    gameMenu = new Button[4];

    // 0 → Start
    gameMenu[0] = new Button("Start"); // スタート
    gameMenu[0].setFont(new Font(BUTTON_SIZE));
    gameMenu[0].setOnAction(event -> {
      if (timerManager.timerStarted) {
        System.out.printf("timer is already started.\n");
        return;
      }
      System.out.printf("timer is started.\n");
      timerManager.timerStart();
    });
    // 1 → Pause
    gameMenu[1] = new Button("Pause");
    gameMenu[1].setFont(new Font(BUTTON_SIZE));
    gameMenu[1].setOnAction(event -> {
      // TODO ボタンのテキストを Start / Resume に切り替える
      if (isPause == false) {
        isPause = true;
        gameMenu[1].setText("Resume");
      } else {
        isPause = false;
        gameMenu[1].setText("Pause");
      }

      timerManager.timerPause(isPause); // 一時停止・再開
    });
    // 2 → Reset
    gameMenu[2] = new Button("Reset");
    gameMenu[2].setFont(new Font(BUTTON_SIZE));
    gameMenu[2].setOnAction(event -> {
      timerManager.timerReset();
    });
    // 3 → Exit
    gameMenu[3] = new Button("Exit");
    gameMenu[3].setFont(new Font(BUTTON_SIZE));
    gameMenu[3].setOnAction(event -> windowFin());
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
