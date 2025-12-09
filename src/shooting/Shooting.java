package shooting;


// 基本構成
import javafx.application.Application;
import javafx.stage.Stage;
// レイアウト、コントロール
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;	// player
import javafx.scene.input.KeyCode;
// キーイベント
import javafx.scene.input.KeyEvent;

public class Shooting extends Application{
	// 配列のための変数
	final int X = 0;
	final int Y = 1;
	
	// キャラクター
	int[] playerPlace = new int[2];	// x, y
	Circle player;
	
	@Override
	public void start(Stage stage) throws Exception {	// 例外処理
		stage.setTitle("Shooting!");
		stage.setWidth(800);
		stage.setHeight(600);
		
		playerPlace[X] = 400;
		playerPlace[Y] = 500;
		player = new Circle(playerPlace[X],playerPlace[Y] , 35); // X, Y, 半径
		
		// 表示
		Pane root = new Pane();	// レイアウトコンテナ
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
		final int W = 0;
		final int S = 1;
		final int A = 2;
		final int D = 3;
		boolean[] keyFlag = new boolean[4];
		
		for (int i = 0; i < 4; i++) {
			keyFlag[i] = false;
		}
		
		if (event.getCode() == KeyCode.W) {	// 前進
			keyFlag[W] = true;
		}
		if (event.getCode() == KeyCode.S) {	// 後退
			keyFlag[S] = true;
		}
		if (event.getCode() == KeyCode.A) {	// 左
			keyFlag[A] = true;
		}
		if (event.getCode() == KeyCode.D) {	// 右
			keyFlag[D] = true;
		}
	}
}
