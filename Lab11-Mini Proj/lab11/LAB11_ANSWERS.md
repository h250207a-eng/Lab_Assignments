# Lab 11 Answers

## Program 11.1: the two `EXPLAIN` comments

**`private AppConfig() { }`: why a private constructor blocks `new`**
Java only lets code *inside* `AppConfig` call a private constructor. Outside code writing `new AppConfig()` gets a compile error, so the only instance that can exist is the one the class builds itself: `INSTANCE`, a `static final` field created once when the class loads. `getInstance()` hands out that one object, which is why `a == b` prints `true`. This is "encapsulation of construction": the class, not its callers, controls how many objects exist.

**`static Notification create(String type)`: why callers depend on the interface**
Callers hold a `Notification` reference and call `send(...)`. They never mention `EmailNotification` or `SmsNotification`, so adding `PushNotification` means one new class and one new `case` in the factory. No calling code changes. Which concrete class runs is decided at run time (polymorphism), and the callers are coupled only to the abstraction. This is the "program to interfaces" part of SOLID.

## Observed output
Both programs were compiled and run, and the output matches the lab's expected output line for line.

## Pattern summary
| Pattern | Problem | Where it appears |
|---|---|---|
| Singleton | One shared instance | `AppConfig` |
| Factory | Create objects without hard-coding classes | `NotificationFactory` |
| Strategy | Swap an algorithm at run time | `Checkout.setStrategy(...)` |
| Observer | Notify many objects of one change | `GradeBook` and its listeners |

Note: Strategy and Observer both use one-method interfaces, which is why lambdas replace the separate classes that Factory needs.

## Gotchas worth knowing for the exam
- Singleton as written is thread-safe because the instance is created during class loading. A lazy version (`if (instance == null) ...`) is **not** thread-safe without extra work.
- In Observer, a listener that throws will stop later listeners from being notified in the simple loop shown.
- `a == b` compares references. That is exactly the right check here, whereas `equals` would be the wrong tool for proving "same instance".
