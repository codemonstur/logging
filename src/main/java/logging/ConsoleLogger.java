package logging;

import static java.lang.System.out;

/**
 * The {@code ConsoleLogger} interface.
 */
public interface ConsoleLogger extends TypeLogger {

    /**
     * Returns whether the console logging enabled condition holds.
     *
     * @return whether the condition holds
     */
    default boolean isConsoleLoggingEnabled() {
        return true;
    }

    /**
     * Performs the {@code log} operation.
     *
     * @param message the message
     */
    default void log(final String message) {
        if (isConsoleLoggingEnabled()) {
            synchronized (this) {
                out.println(message);
            }
        }
    }

}
