package shooting;

// 基本構成
import javafx.application.Application;
import javafx.stage.Stage;
// レイアウト、コントロール
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;	// player

public class Shooting extends Application{
	// 構造
	VBox root;
	// キャラクター
	Circle player;
	
	@Override
	public void start(Stage stage) throws Exception {	// 例外処理
		stage.setTitle("Shooting!");
		stage.setWidth(800);
		stage.setHeight(600);
		
		player = new Circle(400, 500, 35); // X, Y, 半径
		
		// 表示
		Pane root = new Pane();	// レイアウトコンテナ
		root.getChildren().addAll(player);
		Scene scene = new Scene(root, 300, 200);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
