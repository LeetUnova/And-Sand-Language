@echo off
javac src/*.java
jar -cvfm andsandinterpreter.jar manifest.mf src/*.class
del "src\\*.class"