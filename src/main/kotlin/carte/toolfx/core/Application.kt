package carte.toolfx.core

import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

inline fun <reified controller: Controller> runFxmlScreen(
    stage: Stage,
    title: String,
    width: Double = 500.0,
    height: Double = 500.0,
    fullScreen: Boolean = false,
    resizable: Boolean = true,

    ) {
    val loader = FXMLLoader(controller::class.java.getResource((controller::class.annotations[0] as Screen).pathToFXML));
    val parent = loader.load<Parent>();

    val controller = loader.getController<controller>()
    controller.stage = stage;


    val scene = Scene(parent, width, height);
    stage.scene = scene;
    stage.title = title
    stage.isFullScreen = fullScreen
    stage.isResizable = resizable
    stage.show();
}