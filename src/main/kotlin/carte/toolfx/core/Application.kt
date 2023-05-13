package carte.toolfx.core

import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

inline fun <reified controller : Controller> runFxmlScreen(
    stage: Stage,
    title: String,
    width: Double = 500.0,
    height: Double = 500.0,
    fullScreen: Boolean = false,
    resizable: Boolean = true,
    css: String = "",

    ) {
    var loader: FXMLLoader;
    try {
        loader =
            FXMLLoader(controller::class.java.getResource((controller::class.annotations[0] as Screen).pathToFXML));

    } catch (e: ArrayIndexOutOfBoundsException) {
        throw IllegalArgumentException("controller class must be annotated with @Screen")
    }
    val parent = loader.load<Parent>();

    val controller = loader.getController<controller>()
    controller.stage = stage;


    val scene = Scene(parent, width, height);

    stage.scene = scene;
    if (css.isNotEmpty()) {
        scene.stylesheets.add(controller::class.java.getResource(css).toExternalForm())
    }

    stage.title = title
    stage.isFullScreen = fullScreen
    stage.isResizable = resizable
    stage.show();
}

inline fun <reified element : Controller> runFxmlElement(
    context: Controller,
    noinline afterCreated: element.() -> Unit
): element {
    var loader: FXMLLoader;
    try {
        loader = FXMLLoader(element::class.java.getResource((element::class.annotations[0] as Element).pathToFXML))
    } catch (e: ArrayIndexOutOfBoundsException) {
        throw IllegalArgumentException("element class must be annotated with @Element")
    }
    loader.load<Node>()

    val controller = loader.getController<element>()
    controller.stage = context.stage;
    controller.runAfterCreated = afterCreated as (Controller.() -> Unit)


    return controller;
}