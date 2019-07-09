```
./mvnw clean package -DskipTests=true && cf push hello-batch -p target/hello-spring-batch-0.0.1-SNAPSHOT.jar -i 0 

cf run-task hello-batch "find . -name '*.sh'"
cf run-task hello-batch "cat ./BOOT-INF/classes/run.sh"
cf run-task hello-batch "./BOOT-INF/classes/run.sh"
```