module fi.utu.tech.ThreadRunner {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
	requires javafx.base;

    opens fi.utu.tech.ThreadRunner to javafx.fxml;
    opens fi.utu.tech.ThreadRunner.UI to javafx.fxml;
    exports fi.utu.tech.ThreadRunner;
    exports fi.utu.tech.ThreadRunner.UI;
    exports fi.utu.tech.ThreadRunner.dispatchers;
}