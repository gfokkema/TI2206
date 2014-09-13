# TI2206 - Group 15 (Bubble Shooter)

This team consists of the following students:

* Gerlof Fokkema
* Owen Huang
* Adam Iqbal
* Nando Kartoredjo
* Skip Lentz

## How to start this game.

1. In the root directory, run the following:

   ```
   mvn package
   ```

   This will automatically build the desktop version of the game
2. Then, go to the directory `desktop/target`. Run:

   ```
   java -jar bubbleshooter-desktop-{version}-SNAPSHOT-jar-with-dependencies.jar
   ```

To run the html version of the game:

1. In the root directory, run the following:

   ```
   mvn package -Phtml
   ```

   This will build the HTML5 version of this game. It will compile the source code
   to JavaScript

2. Then go to the directory `html/target`. There you'll find the `webapp` directory.
   Copy the contents of this directory to your server. Then in your preferred
   browser, navigate to `http://localhost/root/dir/of/webapp`
