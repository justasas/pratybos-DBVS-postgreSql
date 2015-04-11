javac -d classes -cp classes src/futbolas/Main.java src/futbolas/ui/UserInterface.java src/futbolas/sql/WorkSQL.java
java -cp classes;lib/postgresql-9.2-1002.jdbc4.jar futbolas.Main