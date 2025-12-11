package shooting;

// 基本構成
import javafx.application.Application;
import javafx.stage.Stage;
// レイアウト、コントロール
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle; // player
// キーイベント
import javafx.scene.input.KeyEvent;
import javafx.animation.AnimationTimer;

public class Shooting extends Application {
	// X, Y （座標）
	final int X = 0;
	final int Y = 1;
	// WASD
	final int W = 0;
	final int S = 1;
	final int A = 2;
	final int D = 3;

	// ウィンドウ
	final int WINDOW_WIDTH = 1920;
	final int WINDOW_HEIGHT = 800;
	// プレイヤー
	Circle player;
	int[] playerPlace = new int[2]; // 0 → X, 1 → Y
	int playerRad = 50;
	boolean[] keyFlag = new boolean[4]; // W, S, A, D

	@Override
	public void start(Stage stage) throws Exception { // 例外処理
		stage.setTitle("Shooting!");
		stage.setWidth(WINDOW_WIDTH);
		stage.setHeight(WINDOW_HEIGHT);

		playerPlace[X] = 400;
		playerPlace[Y] = 500;
		player = new Circle(playerPlace[X], playerPlace[Y], playerRad); // X, Y, 半径

		// ゲームループ
		new AnimationTimer() {
			@Override
			public void handle(long now) {
				gameLoop();
				// プレイヤー座標更新
				player.setCenterX(playerPlace[X]);
				player.setCenterY(playerPlace[Y]);
			}
		}.start();

		// 表示
		Pane root = new Pane(); // レイアウトコンテナ
		root.getChildren().addAll(player);
		Scene scene = new Scene(root, 300, 200);
		// キーイベント
		scene.setOnKeyPressed(event -> doKeyAction(event));
		scene.setOnKeyReleased(event -> releaseKeyAction(event));
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	// 移動
	void gameLoop() {
		// 参考 : https://nompor.com/2018/01/18/post-2761/
		final int[] moveSpeed = new int[2];
		moveSpeed[X] = 15;
		moveSpeed[Y] = 15;

		if (keyFlag[W] && 0 < playerPlace[Y] - playerRad) { // 上
			playerPlace[Y] -= moveSpeed[Y];
		}
		if (keyFlag[S] && playerPlace[Y] + playerRad + playerRad/2 < WINDOW_HEIGHT) { // 下
			playerPlace[Y] += moveSpeed[Y];
			System.out.println(playerPlace[Y]);
		}
		if (keyFlag[A]) {
			playerPlace[X] -= moveSpeed[X];
		}
		if (keyFlag[D]) {
			playerPlace[X] += moveSpeed[X];
		}
	}

	void doKeyAction(KeyEvent event) {
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
	void releaseKeyAction(KeyEvent event) {
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