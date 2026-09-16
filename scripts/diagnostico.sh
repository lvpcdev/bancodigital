#!/usr/bin/env bash
set -e
rm -rf out-test && mkdir -p out-test
find src/main/java src/test/java -name '*.java' -print0 | xargs -0 javac -d out-test
java -cp out-test br.edu.fincore.DiagnosticChecks
