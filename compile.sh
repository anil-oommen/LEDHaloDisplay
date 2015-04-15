echo "Compiling to bin"
find ./src -name *.java > sources_list.txt
javac -verbose -classpath .:classes:/opt/pi4j/lib/'*'  -d bin @sources_list.txt