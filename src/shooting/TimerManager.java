package shooting;

// TODO ResetしてもPauseの挙動が治らないことを直す

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
  final int TIMER_SIZE = 40; // 文字のサイズ
  final int TIME_LIMIT = 60; // 制限時間

  public TimerManager() { // コンストラクタ
    timerLb = new Label(String.valueOf(TIME_LIMIT)); // 制限時間
    timerLb.setFont(new Font(TIMER_SIZE));
    timerRoot.getChildren().add(timerLb);

    timer = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
      public void handle(ActionEvent actionEvent) {
        timerLb.setText(String.valueOf(Integer.parseInt(timerLb.getText()) - 1));
        timerLb.setFont(new Font(TIMER_SIZE));

        if (Integer.parseInt(timerLb.getText()) <= 0) { // 制限時間が 0秒以下 になったら
          timer.pause();
          timerLb.setText("0"); // ラベルを 0 に固定
          isTimerStarted = false; // 0秒になったら
        }
      }
    }));

    timer.setCycleCount(Timeline.INDEFINITE);
  }

  /* ボタンが押されたときの処理 */
  /* start */
  boolean isTimerStarted = false;
  /* pause */
  boolean isPause = false;

  void timerStart() {
    if (!isTimerStarted && !isPause) {
      isTimerStarted = true;
      timer.play();
    }
  }

  void timerPause() { // 押された回数
    if (!isPause) {
      isPause = true;
      timer.pause(); // timerを一時停止
    } else {
      isPause = false;
      timer.play(); // timerを再開
    }
  }

  // FIXED ポーズ中に→Startを押してリセットするとReset後に自動でタイマーが始まってしまう。
  // TimeLineがStartを押す都度開始されず、コンストラクタで起動時に開始されるようにした。
  void timerReset() {
    isTimerStarted = false;
    isPause = false;

    // timeline
    timer.stop(); // timelineを停止し、再生ヘッドを先頭に戻す
    // label
    timerLb.setText(String.valueOf(TIME_LIMIT));
    timerLb.setFont(new Font(TIMER_SIZE));
  }

  public AnchorPane getTimer() { // 部品のゲッタ
    return timerRoot;
  }
}
