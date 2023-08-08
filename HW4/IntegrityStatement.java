
public final class IntegrityStatement {
    public static String signature() {
        String names = "Tzur Shalom and Inbar Ben Chaim"; // <- Fill in your names here!
        if (names.length() == 0) {
            throw new UnsupportedOperationException("Tzur and Inbar");
        }
        return names;
    }
}
