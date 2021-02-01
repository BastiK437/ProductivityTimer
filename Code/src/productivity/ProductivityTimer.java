package productivity;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProductivityTimer extends Application {
    private Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ui/productivity-gui.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Productivity");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();

        controller = fxmlLoader.getController();
    }

    @Override
    public void stop() {
        controller.exitApplication();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
