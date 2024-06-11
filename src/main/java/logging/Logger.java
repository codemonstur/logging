package logging;

import java.util.Arrays;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

public interface Logger {

    public enum MessageType {
        info, warn, error
    }

    void info(String message);
    void warn(String message);
    void error(String message);
    void error(Throwable throwable);

    String version();

    public static String newLogMessage(final MessageType type, final String appVersion, final String message) {
        if (!message.contains("\n"))
            return format("[%s][%s] %s", appVersion, type.name(), message);

        return Arrays.stream(message.split("\n"))
            .map(line -> format("[%s][%s] %s", appVersion, type.name(), line))
            .collect(joining("\n"));
    }
    public static String toFirstStackTraceLine(final Throwable throwable) {
        return format("%s: %s", throwable.getClass().getName(), throwable.getMessage());
    }
    public static String toStackTraceElementLine(final StackTraceElement e) {
        return format("\t\tat %s.%s(%s)", e.getClassName(), e.getMethodName(), getStackTraceParenthesisPart(e));
    }
    public static String getStackTraceParenthesisPart(final StackTraceElement e) {
        if (e.isNativeMethod()) return "Native Method";
        return format("%s:%d", e.getClassName().substring(e.getClassName().lastIndexOf('.')+1), e.getLineNumber());
    }

}
