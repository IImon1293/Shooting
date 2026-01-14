package shooting;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.util.Duration;

// ? https://docs.oracle.com/javase/jp/8/javafx/api/javafx/animation/Animation.html#getStatus--

public class TimerManager {
  private AnchorPane timerRoot = new AnchorPane();
  private Label timerLb;
  private Timeline timer;
  private final int TIMER_SIZE = 40; // 文字のサイズ
  private final int TIME_LIMIT = 60; // 制限時間
  private int nowTime = TIME_LIMIT; // 現在の時間

  public TimerManager() { // コンストラクタ
    timerLb = new Label(String.valueOf(TIME_LIMIT)); // 制限時間
    timerLb.setFont(new Font(TIMER_SIZE));
    timerRoot.getChildren().add(timerLb);

    timer = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
      // https://docs.oracle.com/javase/jp/8/javafx/api/javafx/event/EventHandler.html
      // EventHandlerのインターフェース
      public void handle(ActionEvent actionEvent) {
        nowTime -= 1;
        timerLb.setText(String.valueOf(nowTime));

        if (nowTime <= 0) { // 制限時間が 0秒以下 になったら
          timer.pause();
          timerLb.setText("0"); // ラベルを 0 に固定
          isTimerStarted = false; // 0秒になったら
        }
      }
    }));

    timer.setCycleCount(Timeline.INDEFINITE); // 繰り返し無限
  }

  /* ボタンが押されたときの処理 */
  /* start */
  private boolean isTimerStarted = false;
  /* pause */
  private boolean isPause = false;

  void timerStart() {
    if (!isTimerStarted && !isPause) {
      isTimerStarted = true;
      timer.play();
    }
  }

  void timerPause() { // 押された回数
    if (!isTimerStarted) { // タイマーが開始されていないとき
      return; // 無効
    } else if (isTimerStarted && !isPause) { // タイマーが開始されていて、一時停止されていないとき
      isPause = true;
      timer.pause(); // timerを一時停止
    } else { // タイマーが開始されていて、一時停止中のとき
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
    // 秒数をリセット
    nowTime = TIME_LIMIT;
    // label
    timerLb.setText(String.valueOf(nowTime));
    timerLb.setFont(new Font(TIMER_SIZE));
  }

  public boolean getIsTimerStarted() {
    return isTimerStarted;
  }

  public boolean getIsPause() {
    return isPause;
  }

  public AnchorPane getTimer() { // 部品のゲッタ
    return timerRoot;
  }

  public Timeline getTimerTimeline() { // EnemyManagerとTimeLineを共通化させる
    return timer;
  }
}
