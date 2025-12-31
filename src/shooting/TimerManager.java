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
  final int TIME_LIMIT = 3; // 制限時間 TODO 本番は 60 秒に直す

  public TimerManager() { // コンストラクタ
    timerLb = new Label(String.valueOf(TIME_LIMIT)); // 制限時間
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
          timer.pause();
          timerLb.setText("0"); // ラベルを 0 に固定
          System.out.println("timeup");
        }
      }
    }));
    timer.setCycleCount(Timeline.INDEFINITE);
    timer.play();
  }

  // TODO 作業中
  void timerPause(boolean isPause) { // 押された回数
    if (isPause) {
      timer.pause(); // timerを一時停止
      System.out.printf("Pause\n");
    } else {
      timer.play(); // timerを再開
      System.out.printf("Resume\n");
    }
  }

  void timerReset() {
    // timeline
    timer.stop(); // timelineを停止し、再生ヘッドを先頭に戻す
    // label
    timerLb.setText(String.valueOf(TIME_LIMIT));
    timerLb.setFont(new Font(TIMER_SIZE));
    timerStarted = false;
    System.out.printf("timer Reset\n");
  }

  public AnchorPane getTimer() { // 部品のゲッタ
    return timerRoot;
  }
}
