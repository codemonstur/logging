package logging;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static logging.Logger.MessageType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoggingTest {

    static class TestLogger implements ConsoleLogger {
        public String version() { return "1.0"; }
    }

    @Test
    void newLogMessage_singleLine() {
        assertEquals("[1.0][info] hello", Logger.newLogMessage(info, "1.0", "hello"));
    }

    @Test
    void newLogMessage_multiLine() {
        final String out = Logger.newLogMessage(warn, "1.0", "a\nb");
        assertTrue(out.contains("[1.0][warn] a"));
        assertTrue(out.contains("[1.0][warn] b"));
    }

    @Test
    void toFirstStackTraceLine() {
        final Exception e = new RuntimeException("boom");
        assertEquals(e.getClass().getName() + ": boom", Logger.toFirstStackTraceLine(e));
    }

    @Test
    void toStackTraceElementLine() {
        final StackTraceElement el = new Exception().getStackTrace()[0];
        final String line = Logger.toStackTraceElementLine(el);
        assertTrue(line.startsWith("\t\tat "));
    }

    @Test
    void getStackTraceParenthesisPart() {
        final StackTraceElement el = new Exception().getStackTrace()[0];
        final String part = Logger.getStackTraceParenthesisPart(el);
        assertTrue(part.contains(":"));
    }

    @Test
    void consoleLogger_log() {
        final TestLogger logger = new TestLogger();
        assertTrue(logger.isConsoleLoggingEnabled());
        logger.info("info msg");
        logger.warn("warn msg");
        logger.error("err msg");
        logger.error(new RuntimeException("boom"));
    }
}
