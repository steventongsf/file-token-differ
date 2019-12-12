# file-symbol-differ
This tool was created to help validate that formatting updaters have not changed any of the symbols in code files. Examples of formatting updaters include:
* Various IDEs such as Eclipse, Intellij, Visual Studio
* Build tool plugins such as those for Maven, Gradle, etc
* Various command line formatting updaters

The tool is used to check a baseline (unaltered tree of files) against a set of formatter-modified files and to produce some output indicating whether or any tokens in files have been modified or not.

This command line tool is written in Java and build with Maven.  The jar that is created is an executable jar that contains all its dependencies.  


To run, execute the following

prompt> java -jar <jar name> [directory 1] [directory 2]
  
where [directory 1] represents a baseline directory and [directory 2] is the modified directory.

Example:

prompt> java -jar ./original-source/src/main/java ./modified-source/src/main/java

