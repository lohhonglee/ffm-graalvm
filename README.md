# Java 21 Foreign Function & Memory (FFM) API + GraalVM

This repository contains an example of an Java application that uses JDK 21's Foreign Function & Memory (FFM) API (third preview) being compiled into native application using GraalVM.

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
## Error during native-image compilation
Submitted the issue to GraalVM GitHub project: https://github.com/oracle/graal/issues/8115

```
$ ./gradlew nativeRun

> Task :app:generateResourcesConfigFile
[native-image-plugin] Resources configuration written into /home/hlloh/ffm-graalvm/app/build/native/generated/generateResourcesConfigFile/resource-config.json

> Task :app:nativeCompile
[native-image-plugin] Args are: [-cp, /home/hlloh/ffm-graalvm/app/build/libs/app.jar, --no-fallback, --verbose, -Ob, -o, /home/hlloh/ffm-graalvm/app/build/native/nativeCompile/app, -J--enable-preview, -J--enable-native-access=ALL-UNNAMED, -H:ConfigurationFileDirectories=/home/hlloh/ffm-graalvm/app/build/native/generated/generateResourcesConfigFile, --features=com.example.ForeignRegistrationFeature, com.example.App]
[native-image-plugin] GraalVM Toolchain detection is enabled
[native-image-plugin] GraalVM uses toolchain detection. Selected:
[native-image-plugin]    - language version: 21
[native-image-plugin]    - vendor: Oracle
[native-image-plugin]    - runtime version: 21.0.1+12-jvmci-23.1-b19
...
========================================================================================================================
GraalVM Native Image: Generating 'app' (executable)...
========================================================================================================================
For detailed information and explanations on the build output, visit:
https://github.com/oracle/graal/blob/master/docs/reference-manual/native-image/BuildOutput.md
------------------------------------------------------------------------------------------------------------------------

[1/8] Initializing...                                                                                    (0.0s @ 0.06GB
Error: ImageSingletons do not contain key org.graalvm.nativeimage.impl.RuntimeForeignAccessSupport
------------------------------------------------------------------------------------------------------------------------
                        0.2s (5.7% of total time) in 12 GCs | Peak RSS: 0.51GB | CPU load: 3.47
========================================================================================================================
Finished generating 'app' in 2.9s.
com.oracle.svm.driver.NativeImage$NativeImageError
        at org.graalvm.nativeimage.driver/com.oracle.svm.driver.NativeImage.showError(NativeImage.java:2336)
        at org.graalvm.nativeimage.driver/com.oracle.svm.driver.NativeImage.build(NativeImage.java:1919)
        at org.graalvm.nativeimage.driver/com.oracle.svm.driver.NativeImage.performBuild(NativeImage.java:1878)
        at org.graalvm.nativeimage.driver/com.oracle.svm.driver.NativeImage.main(NativeImage.java:1852)
        at java.base@21.0.1/java.lang.invoke.LambdaForm$DMH/sa346b79c.invokeStaticInit(LambdaForm$DMH)

> Task :app:nativeCompile FAILED

FAILURE: Build failed with an exception.
```
