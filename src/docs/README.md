# logging

Small helper classes for logging — a tiny interface hierarchy you can mix into an
application class without pulling in a logging framework. No external
dependencies.

## Classes

| Class | Purpose |
| --- | --- |
| `Logger` | Core interface: `info`, `warn`, `error(String)`, `error(Throwable)`, `version()`. |
| `TypeLogger` | Extends `Logger` with default implementations that format each level and render full recursive stack traces; subclasses implement `log(String)`. |
| `ConsoleLogger` | Extends `TypeLogger`; writes to `System.out` with an overridable `isConsoleLoggingEnabled()` toggle. |

## Formatting

Static helpers on `Logger` produce consistent message lines:

- `newLogMessage(...)` — `[version][type] message`
- `toFirstStackTraceLine(Throwable)` — the first `at ...` line of a stack trace.
- `toStackTraceElementLine(...)` — formatted single frame.

## Usage

```java
public class App extends ConsoleLogger {
    @Override
    public String version() {
        return "1.0.0";
    }

    void run() {
        info("starting up");
        try {
            risky();
        } catch (Exception e) {
            error(e);
        }
    }
}
```

## Dependency

```xml
<dependency>
  <groupId>com.github.codemonstur</groupId>
  <artifactId>logging</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Building

```sh
make build
```

## License

MIT
