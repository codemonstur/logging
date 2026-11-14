package logging;

import java.util.Arrays;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

/**
 * The {@code Logger} interface.
 */
public interface Logger {

    /**
     * The {@code MessageType} enum.
     */
    public enum MessageType {
        /**
         * The {@code info} constant.
         */
        info, /**
     * The {@code warn} constant.
     */
    warn, /**
     * The {@code error} constant.
     */
    error
    }

    /**
     * Performs the {@code info} operation.
     *
     * @param message the message
     */
    void info(String message);
    /**
     * Performs the {@code warn} operation.
     *
     * @param message the message
     */
    void warn(String message);
    /**
     * Performs the {@code error} operation.
     *
     * @param message the message
     */
    void error(String message);
    /**
     * Performs the {@code error} operation.
     *
     * @param throwable the throwable
     */
    void error(Throwable throwable);

    /**
     * Returns the result of {@code version}.
     *
     * @return the result
     */
    String version();

    /**
     * Returns the result of {@code newLogMessage}.
     *
     * @param type the type
     * @param appVersion the app version
     * @param message the message
     * @return the result
     */
    public static String newLogMessage(final MessageType type, final String appVersion, final String message) {
        if (!message.contains("\n"))
            return format("[%s][%s] %s", appVersion, type.name(), message);

        return Arrays.stream(message.split("\n"))
            .map(line -> format("[%s][%s] %s", appVersion, type.name(), line))
            .collect(joining("\n"));
    }
    /**
     * Returns the result of {@code toFirstStackTraceLine}.
     *
     * @param throwable the throwable
     * @return the result
     */
    public static String toFirstStackTraceLine(final Throwable throwable) {
        return format("%s: %s", throwable.getClass().getName(), throwable.getMessage());
    }
    /**
     * Returns the result of {@code toStackTraceElementLine}.
     *
     * @param e the e
     * @return the result
     */
    public static String toStackTraceElementLine(final StackTraceElement e) {
        return format("\t\tat %s.%s(%s)", e.getClassName(), e.getMethodName(), getStackTraceParenthesisPart(e));
    }
    /**
     * Returns the result of {@code getStackTraceParenthesisPart}.
     *
     * @param e the e
     * @return the result
     */
    public static String getStackTraceParenthesisPart(final StackTraceElement e) {
        if (e.isNativeMethod()) return "Native Method";
        return format("%s:%d", e.getClassName().substring(e.getClassName().lastIndexOf('.')+1), e.getLineNumber());
    }

}
