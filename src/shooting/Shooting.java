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
	final int WINDOW_WIDTH = 1000;
	final int WINDOW_HEIGHT = 800;
	// プレイヤー
	Circle player;
	int[] playerPlace = new int[2]; // 0 → X, 1 → Y
	int playerRad = 50;
	boolean[] keyFlag = new boolean[4]; // W, S, A, D
	// コンテナ
	Pane root;

	@Override
	public void start(Stage stage) throws Exception { // 例外処理
		stage.setTitle("Shooting!");

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
		root = new Pane();
		root.getChildren().addAll(player);
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
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
		// WASD 参考 : https://nompor.com/2018/01/18/post-2761/
		// 現在のシーンの大きさを取得
		double currentSceneWidth = root.getWidth();
		double currentSceneHeight = root.getHeight();

		final int[] moveSpeed = new int[2];
		moveSpeed[X] = 15;
		moveSpeed[Y] = 15;

		if (keyFlag[W]) { // 上
			playerPlace[Y] -= moveSpeed[Y];
		}
		if (keyFlag[S]) { // 下
			playerPlace[Y] += moveSpeed[Y];
		}
		if (keyFlag[A]) {
			playerPlace[X] -= moveSpeed[X];
		}
		if (keyFlag[D]) {
			playerPlace[X] += moveSpeed[X];
		}

		if (playerPlace[X] < playerRad) { // プレイヤーが上に飛び出す→プレイヤーの座標が上限より半径分下にある
			playerPlace[X] = playerRad;
		} else if (playerPlace[X] > currentSceneWidth - playerRad) { // プレイヤーが下に飛び出す→プレイヤーの座標が下限よりプレイヤーの座標が半径分上にある
			playerPlace[X] = (int) currentSceneWidth - playerRad;
		}

		if (playerPlace[Y] < playerRad) { // プレイヤーが上に飛び出す→プレイヤーの座標が上限より半径分下にある
			playerPlace[Y] = playerRad;
		} else if (playerPlace[Y] > currentSceneHeight - playerRad) { // プレイヤーが下に飛び出す→プレイヤーの座標が下限よりプレイヤーの座標が半径分上にある
			playerPlace[Y] = (int) currentSceneHeight - playerRad;
		}

		System.out.printf("X → %d, Y → %d\n", playerPlace[X], playerPlace[Y]);
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