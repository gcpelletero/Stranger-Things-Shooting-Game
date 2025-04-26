# 🎮 Stranger Things Shooting Game — Setup and Run Instructions

## 📥 1. Download and Install Requirements
- **Java Development Kit (JDK) version 17 or higher**
(Recommended: Download JDK 22)
- **JavaFX SDK version 24.0.1**
Download JavaFX 24.0.1 here

    ✅ Make sure to extract the JavaFX SDK ZIP file somewhere you can find it.
    Example path: ```D:\Downloads\openjfx-24.0.1_windows-x64_bin-sdk\javafx-sdk-24.0.1\```

## 📂 2. Clone the Repository
Open a terminal or PowerShell and run:
```git clone https://github.com/gcpelletero/Stranger-Things-Shooting-Game.git```

Then go inside the project folder:
```cd "Stranger Things Shooting Game/src"```

## ⚙️ 3. Compile the Java Files
Compile the game source code using the JavaFX libraries.
```javac --class-path "D:/Downloads/openjfx-24.0.1_windows-x64_bin-sdk/javafx-sdk-24.0.1/lib/*" main/*.java strangerthings/*.java```
🔵 Change the path if your JavaFX SDK is saved somewhere else!

## 🚀 4. Run the Game
Finally, run the main game launcher:
```java --module-path "D:/Downloads/openjfx-24.0.1_windows-x64_bin-sdk/javafx-sdk-24.0.1/lib" --add-modules javafx.controls,javafx.fxml main.Main```

The Game should now open!

## 📢 Notes
- If you're using **VS Code**, make sure you have these extensions installed:
    - Extension: "Language Support for Java™" (by Red Hat)
    - Extension: "Debugger for Java"
- Always match your JavaFX SDK version with your JDK version range.
- The game window might take a few seconds to load after running.

## 🛠️ Troubleshooting
Problem | Solution
- ```error: module not found: javafx.controls ```
Check your ```--module-path``` points to the correct ```javafx-sdk/lib``` folder
- ```error: package strangerthings does not exist```
Make sure you're inside the ```src``` folder and compiled all ```.java``` files
- Game not launching 
Verify that you downloaded both JDK and JavaFX properly





