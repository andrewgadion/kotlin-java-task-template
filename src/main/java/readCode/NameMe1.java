package readCode;

public class NameMe1 {
    public static int nameMePlease(byte[] bytes) {
        if (bytes == null)
            throw new IllegalArgumentException("bytes");

        int result = 0;

        if (bytes.length > 3)
            result = bytes[3] << 24;

        if (bytes.length > 2)
            result |= bytes[2] << 16;

        if (bytes.length > 1)
            result |= bytes[1] << 8;

        if (bytes.length > 0)
            result |= bytes[0];

        return result;
    }
}
