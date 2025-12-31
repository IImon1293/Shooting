package shooting;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class TimerManager {
  AnchorPane timerRoot = new AnchorPane();
  Label timerLb;
  Timeline timer;
  final int TIMER_SIZE = 40;

  public TimerManager() { // コンストラクタ
    timerLb = new Label("3"); // 制限時間（60） // ! debug
    timerLb.setFont(new Font(TIMER_SIZE));
    timerRoot.getChildren().add(timerLb);
  }

  /* ボタンが押されたときの処理 */
  /* start */
  boolean timerStarted = false;

  void timerStart() {
    System.out.println("タイマー開始");
    timerStarted = true;
    timer = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
      public void handle(ActionEvent actionEvent) {
        timerLb.setText(String.valueOf(Integer.parseInt(timerLb.getText()) - 1));
        timerLb.setFont(new Font(TIMER_SIZE));

        if (Integer.parseInt(timerLb.getText()) <= 0) { // 制限時間が 0秒以下 になったら
          timer.stop();
          System.out.println("制限時間が終了しました。");
        }
      }
    }));
    timer.setCycleCount(Timeline.INDEFINITE);
    timer.play();
  }

  void timerPause() {
    timer.pause();
  }

  public AnchorPane getTimer() { // 部品のゲッタ
    return timerRoot;
  }
}
