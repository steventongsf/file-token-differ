# file-symbol-differ
This project is a command line tool written in Java and build with Maven.  The jar that is created is an executable jar that contains all its dependencies.  This tool was created to help validate that formatting updaters have not changed any of the symbols in code files. Examples of formatting updaters include:
* Eclipse IDEs
* Intellij IDEs
* Maven and Gradle formatting plugins
* command line formatting updaters

This tool is used to check a baseline (unaltered tree of files) against a set of modified files and to produce some output indicating whether or any tokens in files have been modified rather than just formatting modifications (whitespace, new lines, etc).

To run, execute the following

prompt> java -jar <jar name> [directory 1] [directory 2]
  
where [directory 1] represents a baseline directory and [directory 2] is the modified directory.

Example:

prompt> java -jar ./original-source/src/main/java ./modified-source/src/main/java

