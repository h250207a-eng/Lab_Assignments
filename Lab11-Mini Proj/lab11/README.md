# Library Management System (Lab 11 Mini-Project)

Requires a JDK 17+ (uses `switch` expressions, text blocks, `Stream.toList()`).

    # compile
    mkdir -p out && javac -d out src/*.java test/*.java

    # run the tests (33 checks)
    java -cp out LibraryTests

    # run the app (data folder optional; defaults to ./data)
    java -cp out LibraryApp
    java -cp out LibraryApp mydata

See REPORT.md for the class diagram and design notes.
