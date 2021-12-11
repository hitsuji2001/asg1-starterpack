#!/bin/bash

SRC_FILES="Book/*.java Menu/*.java Main/*.java"
CLASS_FILES="bin/"

echo "Compiling..."

javac $SRC_FILES -d bin/

if [ $? -ne 0 ]
then
    echo "ERROR: Complile failed"
    exit 1
else
    java -cp $CLASS_FILES Main.Main
    exit 0
fi
