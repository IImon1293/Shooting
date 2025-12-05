package shooting;

// 基本構成
import javafx.application.Application;
import javafx.stage.Stage;
// レイアウト、コントロール
import javafx.scene.control.ComboBox;	// Start, Pause, Reset, Exit
import javafx.scene.shape.Circle;	// player

public class Shooting extends Application{
	// キャラクター
	Circle player;
	ComboBox<String> menu;
	
	@Override
	public void start(Stage stage) throws Exception {	// 例外処理
		stage.setTitle("Shooting!");
		stage.setWidth(800);
		stage.setHeight(600);
		
		stage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
