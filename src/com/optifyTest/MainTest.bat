@echo off
cd %0/..
java -classpath "%CLASSPATH%;lib;src;bin;selenium/libs/*.jar;selenium;C:/Program Files/Java/jre7/lib;D:/eclipse/plugins/org.junit_4.8.2.v4_8_2_v20110321-1705/junit.jar;org.eclipse.jdt.launching.JRE_CONTAINER;org.eclipse.jdt.junit.JUNIT_CONTAINER/4;D:/eclipse/plugins/org.hamcrest.core_1.1.0.v20090501071000.jar;D:/eclipse/plugins;" com.optifyTest.test %1
