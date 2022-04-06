#Power Flower Project - LSystem 
var_lib_path=lib/javafx-sdk-17.0.2/
var_path=lib/javafx-sdk-17.0.2/lib/ 
export var_path
javac -Xdiags:verbose -d build src/Main.java src/lsystem/*.java src/window/*.java src/geometric/*.java --module-path $var_path --add-modules javafx.controls,javafx.base,javafx.web,javafx.swing,javafx.graphics,javafx.media,javafx.fxml
java -cp build --module-path $var_path --add-modules javafx.controls,javafx.media,javafx.web,javafx.swing,javafx.graphics,javafx.base -Djava.library.path=$var_lib_path -Dprism.verbose=true Main