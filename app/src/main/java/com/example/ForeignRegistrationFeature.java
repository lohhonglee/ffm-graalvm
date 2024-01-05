package com.example;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.ValueLayout;

import org.graalvm.nativeimage.hosted.Feature;
import org.graalvm.nativeimage.hosted.RuntimeForeignAccess;

/**
 * Register descriptors to the native-image tool using a custom Feature
 * https://www.graalvm.org/latest/reference-manual/native-image/dynamic-features/foreign-interface/
 */
class ForeignRegistrationFeature implements Feature {
    public void duringSetup(DuringSetupAccess access) {
        RuntimeForeignAccess.registerForDowncall(FunctionDescriptor.ofVoid(ValueLayout.ADDRESS));
    }
}