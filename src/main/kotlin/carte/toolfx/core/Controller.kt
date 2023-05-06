package carte.toolfx.core

import javafx.fxml.Initializable
import javafx.scene.Node
import javafx.scene.Scene
import javafx.util.Callback
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

abstract class Controller: Initializable {

    protected lateinit var scene: Scene;

    fun setLater(node: Node, after: () -> Unit) {

        GlobalScope.launch {
            while (node.scene == null) { delay(100) }

            scene = node.scene;

            after.invoke();

        }

    }



}