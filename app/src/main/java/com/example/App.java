package com.example;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.SymbolLookup;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;

public class App {

    public static void main(String[] args) throws Throwable {

        String name;
        if (args.length == 0) {
            name = "world";
        } else {
            name = args[0];
        }

        try (Arena arena = Arena.ofConfined()) {
            
            // Allocate off-heap memory for the string
            MemorySegment nameAddress = arena.allocateUtf8String(name);

            // Obtain an instance of the native linker
            Linker linker = Linker.nativeLinker();

            // Load the shared library
            System.loadLibrary("hello");

            // Locate the address of the C function
            SymbolLookup loaderLookup = SymbolLookup.loaderLookup();
            MemorySegment helloAddress = loaderLookup.find("hello").orElseThrow();
        
            // Create a descriptor for the C function
            FunctionDescriptor helloDescriptor = FunctionDescriptor.ofVoid(ValueLayout.ADDRESS);
            
            // Create a downcall handle for the C function
            MethodHandle hello = linker.downcallHandle(helloAddress, helloDescriptor);

            // Call the C function directly from Java
            hello.invokeExact(nameAddress);
        } 
    }

}