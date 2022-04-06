PATH_JAVAFX = lib/javafx-sdk-17.0.2/lib/
MODULES     = --add-modules javafx.controls,javafx.media,javafx.web,javafx.swing,javafx.graphics,javafx.base
all:
	@echo "\033[0;33m\e[1mCHARGEMENT, VEUILLEZ PATIENTER ..\033[0;32m"
	@export PATH_JAVAFX
	@javac -Xdiags:verbose -d build src/Main.java src/lsystem/*.java src/window/*.java src/geometric/*.java --module-path $(PATH_JAVAFX) $(MODULES)
	@java -cp build --module-path $(PATH_JAVAFX) $(MODULES) -Djava.library.path=$(PATH_JAVAFX) -Dprism.verbose=true Main
	@echo "\n\e[1mGénerateur de flore video-ludique\033[0;32m\n----------------------------------\nApplication réalisée avec swing et la librairie JavaFX\n\nAuteurs:\e[1m\n\tRomain Andres \n\tMarta Boshkovska \n\tSara Sale \n\tRonan Carre\n\n\033[0;32mEncradant de TP: \e[1mMeddouri Nida\n\nA bientôt !"

test:
	@echo "\033[0;33m\e[1mCHARGEMENT DES TESTS ..\033[0;32m"
	@export PATH_JAVAFX
	@javac -Xdiags:verbose -d build src/Tests.java src/lsystem/*.java src/window/*.java src/geometric/*.java --module-path $(PATH_JAVAFX) $(MODULES)
	@java -cp build --module-path $(PATH_JAVAFX) $(MODULES) -Djava.library.path=$(PATH_JAVAFX) -Dprism.verbose=true Tests