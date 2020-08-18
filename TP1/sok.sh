mvn clean -Dmaven.test.skip=true package appassembler:assemble > /dev/null 2>&1
./main/target/appassembler/bin/sokoban $@
