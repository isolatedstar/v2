clear

lib=./lib
classpath=.

for file in ${lib}/*.jar; 
    do classpath=${classpath}:$file; 
done

$JAVA_HOME/bin/java -Xms256m -Xmx512m -classpath ${classpath} cfca.test.APITest2
