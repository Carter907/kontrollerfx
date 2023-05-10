module carte.controllerfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires kotlinx.coroutines.core;
    requires transitive kotlin.reflect;


    exports carte.toolfx.core;

}