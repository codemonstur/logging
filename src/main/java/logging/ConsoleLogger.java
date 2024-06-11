package logging;

import static java.lang.System.out;

public interface ConsoleLogger extends TypeLogger {

    default boolean isConsoleLoggingEnabled() {
        return true;
    }

    default void log(final String message) {
        if (isConsoleLoggingEnabled()) {
            synchronized (this) {
                out.println(message);
            }
        }
    }

}
