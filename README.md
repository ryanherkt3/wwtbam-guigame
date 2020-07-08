# Who Wants To Be A Millionaire (GUI Game)

A Graphical User Interface (GUI) version of the quiz game Who Wants To Be A Millionaire, written in Java. 

Program created as part of a university project for my Program Design and Construction (PDC) course, 2020.

## How to run this project

**IMPORTANT:** Download the NetBeans IDE first!

1) Download a ZIP of the master branch.

2) Unzip the master branch folder, and open NetBeans.

3) In NetBeans, open the unzipped folder, or drag it to the window.

4) Check the database works (instructions below)

5) Run the file

## Checking the database works 

1) In NetBeans, go to: Services > Databases > JavaDB > (right click JavaDB) > Properties.

2) In Properties, set the database location to the MillionaireGameDB folder. 

3) Now right click JavaDB and click Start Server.

4) Then, expand "jdbc:derby://localhost:1527/MillionaireGameDB" and find the three tables.

5) Right click on any of the tables and click on the options which shows the data.

## Folder list

* MillionaireGameDB: Has files for the embedded database. **DO NOT MODIFY THIS FOLDER**!

* lib: The Java .jar files required for the embedded database to interact with the program.

* nbproject: NetBeans project files.

* src/pdcguiproject: Source files; pdcguiproject is the name of the package.

* test/projecttests: Test case files; projecttests is the package name.
