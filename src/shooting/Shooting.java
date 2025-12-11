package shooting;

// 基本構成
import javafx.application.Application;
import javafx.stage.Stage;
// レイアウト、コントロール
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.control.ComboBox;
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
	final int WINDOW_WIDTH = 800;
	final int WINDOW_HEIGHT = 600;
	// プレイヤー
	Circle player;
	int[] playerPlace = new int[2]; // 0 → X, 1 → Y
	int playerRad;
	// キーのフラグ
	boolean[] keyFlag = new boolean[4]; // W, S, A, D

	@Override
	public void start(Stage stage) throws Exception { // 例外処理
		stage.setTitle("Shooting!");
		stage.setWidth(WINDOW_WIDTH);
		stage.setHeight(WINDOW_HEIGHT);

		// メニュー
		ComboBox<String> gameMenuCmb = new ComboBox<>(); // ゲームメニュー（start, exit, pause, reset）
		gameMenuCmb.getItems().addAll("Start", "Pause", "Reset", "Exit");

		playerPlace[X] = 400;
		playerPlace[Y] = 500;
		playerRad = 35;

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
		root.getChildren().addAll(player, gameMenuCmb);
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
		moveSpeed[X] = 7;
		moveSpeed[Y] = 5;

		if (keyFlag[W] && playerRad < playerPlace[Y]) { // プレイヤー全体が画面に収まるようにする
			playerPlace[Y] -= moveSpeed[Y];
		}
		if (keyFlag[S] && playerPlace[Y] < WINDOW_HEIGHT - playerRad) {
			playerPlace[Y] += moveSpeed[Y];
		}
		if (keyFlag[A] && playerRad < playerPlace[X]) {
			playerPlace[X] -= moveSpeed[X];
		}
		if (keyFlag[D] && playerPlace[X] < WINDOW_WIDTH - playerRad) {
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
