package shooting;

import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class MenuManager {
  HBox hb;
  Button[] gameMenu;
  final int BUTTON_SIZE = 30;

  public MenuManager(Shooting shooting) {
    // コンテナ
    hb = new HBox();
    // メニュー
    gameMenu = new Button[4];

    // 0 → Start
    gameMenu[0] = new Button("Start"); // スタート
    gameMenu[0].setFont(new Font(BUTTON_SIZE));
    gameMenu[0].setOnAction(event -> {
      timerStart();
    });
    // 1 → Pause
    gameMenu[1] = new Button("Pause");
    gameMenu[1].setFont(new Font(BUTTON_SIZE));
    gameMenu[1].setOnAction(event -> {
      // TODO: ゲームをポーズ
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
    hb.getChildren().addAll(gameMenu);
  }

  /* ボタンが押されたときの処理 */
  /* start */
  void timerStart() {
    System.out.println("タイマー開始");
  }

  /* Exit */
  void windowFin() {
    System.out.println("ゲームを終了");
    System.exit(0);
  }

  public HBox getMenuBox() { // ゲッタ
    return hb;
  }
}
