package com.kdb.binary.util;

/**
 * Contains methods to convert number systems.
 */
public class Converter {

    /**
     * Base for binary number system
     */
    public static final int BASE_BINARY = 2;

    /**
     * Base for octal number system
     */
    public static final int BASE_OCTAL = 8;

    /**
     * Base for decimal number system
     */
    public static final int BASE_DECIMAL = 10;

    /**
     * Base for hexadecimal number system
     */
    public static final int BASE_HEX = 16;

    /**
     * <p>Converts a number from one number system to another.</p>
     * <p>The {@code srcBase} and {@code targetBase} can be one of the following:</p>
     * <ul>
     *     <li>{@link #BASE_BINARY} - Binary (2)</li>
     *     <li>{@link #BASE_OCTAL} - Octal (8)</li>
     *     <li>{@link #BASE_DECIMAL} - Decimal (10)</li>
     *     <li>{@link #BASE_HEX} - Hexadecimal (16)</li>
     * </ul>
     *
     * @param number     The number in string to convert
     * @param srcBase    The base of the passed number
     * @param targetBase The base of the target/converted number
     * @return The converted number or {@code null} if either the passed number was
     * invalid or the passed bases were invalid.
     */
    public static String convert(String number, final int srcBase, final int targetBase) {

        // Return null, if number string is empty
        if (number == null || number.trim().isEmpty()) {
            return null;
        }

        // Throw exception, if the specified bases are invalid
        if (!isValidBase(srcBase) || !isValidBase(targetBase)) {
            return null;
        }

        try {

            // Parse the number string as long, in passed source base
            long _number = Long.parseLong(number, srcBase);

            // Convert the number in passed target base and return as string
            return Long.toString(_number, targetBase);

        } catch (NumberFormatException e) {
            // The passed number is of invalid format
            return null;
        }

    }

    /**
     * Checks if the specified number system base is valid or not. The base is valid if
     * it one of the <b>{@code BASE_*}</b> constants present in {@link Converter} class.
     *
     * @param base The base to check for validity
     * @return {@code true} if the passed base is valid else {@code false}
     */
    private static boolean isValidBase(final int base) {
        return (base == BASE_BINARY ||
                base == BASE_DECIMAL ||
                base == BASE_OCTAL ||
                base == BASE_HEX);
    }


}
