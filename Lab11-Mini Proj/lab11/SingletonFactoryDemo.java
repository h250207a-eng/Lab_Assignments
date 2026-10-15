// ---------- SINGLETON ----------
class AppConfig {
    private static final AppConfig INSTANCE = new AppConfig();   // created once
    private final String institution = "Example University";

    private AppConfig() { }                       // private constructor blocks 'new' from outside the class

    public static AppConfig getInstance() { return INSTANCE; }
    public String getInstitution() { return institution; }
}

// ---------- FACTORY ----------
interface Notification {
    void send(String message);
}
class EmailNotification implements Notification {
    public void send(String m) { System.out.println("Email: " + m); }
}
class SmsNotification implements Notification {
    public void send(String m) { System.out.println("SMS: " + m); }
}

class NotificationFactory {
    // callers depend on the Notification interface, never on the concrete classes
    static Notification create(String type) {
        return switch (type.toLowerCase()) {
            case "email" -> new EmailNotification();
            case "sms"   -> new SmsNotification();
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        };
    }
}

public class SingletonFactoryDemo {
    public static void main(String[] args) {
        AppConfig a = AppConfig.getInstance();
        AppConfig b = AppConfig.getInstance();
        System.out.println("Same instance? " + (a == b) + " -> " + a.getInstitution());

        NotificationFactory.create("email").send("Lab 11 is open");
        NotificationFactory.create("SMS").send("Lab 11 is open");
    }
}
