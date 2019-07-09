#!/bin/bash

.java-buildpack/open_jdk_jre/bin/java -cp . org.springframework.boot.loader.JarLauncher --spring.batch.job.enabled=true
