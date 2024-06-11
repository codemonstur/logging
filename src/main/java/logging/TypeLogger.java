package logging;

import static logging.Logger.*;
import static logging.Logger.MessageType.*;

public interface TypeLogger extends Logger {

    default void info(final String message) {
        log(newLogMessage(info, version(), message));
    }
    default void warn(final String message) {
        log(newLogMessage(warn, version(), message));
    }
    default void error(final String message) {
        log(newLogMessage(error, version(), message));
    }
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

    void log(String message);

}
