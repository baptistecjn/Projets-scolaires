#!/bin/bash

OUT_DIR="bin"
LIB_ANTLR="src/main/while_compiler/code_adresse/lib/antlr-3.5.3-complete-no-st3.jar"
MAIN_JAVA="src/main/while_compiler/Main.java"
DEFAULT_TEST="src/main/while_compiler/exemple.txt"
MAIN_BACKEND="src/main/Backend/MainBackend.java"
TRANSLATOR="src/main/Backend/Translator.java"
SRC_FILE="src/main/Backend/main.cpp"
BINARY_NAME="$OUT_DIR/main"
SRC_RUNTIME="src/main/Backend/while_runtime.cpp"

mkdir -p "$OUT_DIR"

echo "--- [1/2] Compilation en cours... ---"

javac -d "$OUT_DIR" -cp "$LIB_ANTLR" -sourcepath src/main "$MAIN_JAVA"
javac -d "$OUT_DIR" "$MAIN_BACKEND" "$TRANSLATOR"

if [ $? -eq 0 ]; then
    echo "✅ Compilation Réussie."
    echo "--- [2/2] Exécution... ---"

    INPUT_FILE="${1:-$DEFAULT_TEST}"

    echo "📂 Fichier analysé : $INPUT_FILE"
    echo "-----------------------------------"

    java -cp "$OUT_DIR:$LIB_ANTLR" while_compiler.Main "$INPUT_FILE"
    java -cp "$OUT_DIR" Backend.MainBackend

   g++ -o "$BINARY_NAME" "$SRC_FILE" "$SRC_RUNTIME"
   if [ $? -eq 0 ]; then
    echo "✅ Compilation Réussie."
    echo "--- [2/2] Exécution... ---"
    ./"$BINARY_NAME"
    fi
else
    echo "Erreur de Compilation."
    exit 1
fi
