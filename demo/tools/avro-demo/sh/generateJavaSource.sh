#!/bin/bash
java -jar ~/.m2/repository/org/apache/avro/avro-tools/1.9.2/avro-tools-1.9.2.jar \
compile schema src/main/resources/schema/user.schema src/main/java