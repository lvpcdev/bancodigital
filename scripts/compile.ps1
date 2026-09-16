Remove-Item -Recurse -Force out -ErrorAction SilentlyContinue
New-Item -ItemType Directory -Force out | Out-Null
$files = Get-ChildItem -Recurse src/main/java -Filter *.java | ForEach-Object { $_.FullName }
javac -d out $files
if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }
java -cp out br.edu.fincore.Main
