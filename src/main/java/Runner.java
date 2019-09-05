import javafx.application.Application;
import javafx.stage.Stage;
import ui.MainWindow;

public class Runner extends Application {
    @Override
    public void start(Stage primaryStage) {
        new MainWindow(primaryStage);
    }
}
