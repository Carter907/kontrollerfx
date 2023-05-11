package carte.toolfx.core

import javafx.application.Platform
import javafx.fxml.Initializable
import javafx.scene.Node
import javafx.scene.Scene
import javafx.stage.Stage
import javafx.stage.Window
import javafx.util.Callback
import kotlinx.coroutines.*
import java.net.URL
import java.util.*

abstract class Controller : Initializable {

    protected lateinit var scene: Scene;
    protected lateinit var window: Window;
    lateinit var stage: Stage;

    private suspend fun setLater(after: () -> Unit = {}) {

        while (stage.scene == null) { delay(100) }
        scene = stage.scene;
        while (scene.window == null) { delay(100) }
        window = scene.window;

        after.invoke();


    }

    override fun initialize(p0: URL?, p1: ResourceBundle?) {
        GlobalScope.launch(Dispatchers.Default) {
            launch {
                setLater();
            }.join();
            Platform.runLater {
                onCreate();
            }

        }

    }


    abstract fun onCreate();


}