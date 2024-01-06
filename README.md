# Java 21 Foreign Function & Memory (FFM) API + GraalVM

This repository contains an example of an Java application that uses Java 21's Foreign Function & Memory (FFM) API (third preview) being compiled into native application using GraalVM.

 - Environment
    - Ubuntu 22.04.3 LTS
    - gcc 11.4.0
    - GraalVM for JDK 21.0.1
    - Gradle 8.5

- Compile `libhello.so` library with gcc
    ```
   gcc -o libhello.so -shared hello.c
    ```
- Compile and run Java application with Gradle
    ```
    ./gradlew run
    ```
- Compile and run native application with GraalVM native-image tool
    ```
    ./gradlew nativeRun
    ```
