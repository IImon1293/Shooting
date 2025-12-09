package shooting;

// 基本構成
import javafx.application.Application;
import javafx.stage.Stage;
// レイアウト、コントロール
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle; // player
import javafx.scene.input.KeyCode;
// キーイベント
import javafx.scene.input.KeyEvent;

public class Shooting extends Application {
	// X, Y （座標）
	final int X = 0;
	final int Y = 1;
	// WASD
	final int W = 0;
	final int S = 1;
	final int A = 2;
	final int D = 3;

	// プレイヤー
	Circle player;
	int[] playerPlace = new int[2]; // 0 → X, 1 → Y
	boolean[] keyFlag = new boolean[4]; // W, S, A, D

	@Override
	public void start(Stage stage) throws Exception { // 例外処理
		stage.setTitle("Shooting!");
		stage.setWidth(800);
		stage.setHeight(600);

		playerPlace[X] = 400;
		playerPlace[Y] = 500;
		player = new Circle(playerPlace[X], playerPlace[Y], 35); // X, Y, 半径

		// 表示
		Pane root = new Pane(); // レイアウトコンテナ
		root.getChildren().addAll(player);
		Scene scene = new Scene(root, 300, 200);
		scene.setOnKeyPressed(event -> doKeyAction(event)); // キーイベント
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	void doKeyAction(KeyEvent event) {

		if (event.getCode() == KeyCode.W) { // 前進
			keyFlag[W] = true;
			System.out.println("W"); // !debug
		}
		if (event.getCode() == KeyCode.S) { // 後退
			keyFlag[S] = true;
			System.out.println("S"); // !debug
		}
		if (event.getCode() == KeyCode.A) { // 左
			keyFlag[A] = true;
			System.out.println("A"); // !debug
		}
		if (event.getCode() == KeyCode.D) { // 右
			keyFlag[D] = true;
			System.out.println("D"); // !debug
		}
	}
}
