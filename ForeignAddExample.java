import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
public class ForeignAddExample {
public static void main(String[] args) throws Throwable {
Linker linker = Linker.nativeLinker();
SymbolLookup lookup = SymbolLookup.libraryLookup("add", Arena.ofAuto());
MethodHandle addHandle = linker.downcallHandle(
lookup.find("add").get(),
MethodType.methodType(int.class, int.class, int.class),
FunctionDescriptor.of(ValueLayout.JAVA_INT, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT)
);
int result = (int) addHandle.invoke(10, 20);
System.out.println("Sum: " + result); // Output: Sum: 30
}
}
