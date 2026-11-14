package logging;

import static logging.Logger.*;
import static logging.Logger.MessageType.*;

/**
 * The {@code TypeLogger} interface.
 */
public interface TypeLogger extends Logger {

    /**
     * Performs the {@code info} operation.
     *
     * @param message the message
     */
    default void info(final String message) {
        log(newLogMessage(info, version(), message));
    }
    /**
     * Performs the {@code warn} operation.
     *
     * @param message the message
     */
    default void warn(final String message) {
        log(newLogMessage(warn, version(), message));
    }
    /**
     * Performs the {@code error} operation.
     *
     * @param message the message
     */
    default void error(final String message) {
        log(newLogMessage(error, version(), message));
    }
    /**
     * Performs the {@code error} operation.
     *
     * @param throwable the throwable
     */
    default void error(final Throwable throwable) {
        final var builder = new StringBuilder()
            .append(newLogMessage(error, version(), toFirstStackTraceLine(throwable)));
        if (throwable.getCause() != null) {
            log(builder.toString());
            error(throwable.getCause());
            return;
        }
        builder.append("\n");
        for (final StackTraceElement e : throwable.getStackTrace()) {
            builder.append(toStackTraceElementLine(e)).append("\n");
        }
        log(builder.toString());
    }

    /**
     * Performs the {@code log} operation.
     *
     * @param message the message
     */
    void log(String message);

}
