package com.kdb.binary;

import com.kdb.binary.util.Converter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit Testing for the {@link Converter} class.
 */
public class ConverterTest {

    // Binary to others
    // ----------------
    @Test
    public void convert_binaryToDecimal() {
        final int src = Converter.BASE_BINARY;
        final int target = Converter.BASE_DECIMAL;

        assertEquals("2", Converter.convert("10", src, target));
        assertEquals("-10", Converter.convert("-1010", src, target));
    }

    @Test
    public void convert_binaryToOctal() {
        final int src = Converter.BASE_BINARY;
        final int target = Converter.BASE_OCTAL;

        assertEquals("14", Converter.convert("1100", src, target));
        assertEquals("-12", Converter.convert("-1010", src, target));
    }

    @Test
    public void convert_binaryToHex() {
        final int src = Converter.BASE_BINARY;
        final int target = Converter.BASE_HEX;

        assertEquals("f", Converter.convert("1111", src, target));
        assertEquals("-2b", Converter.convert("-101011", src, target));
    }

    // Decimal to others
    // -----------------
    @Test
    public void convert_decimalToBinary() {
        final int src = Converter.BASE_DECIMAL;
        final int target = Converter.BASE_BINARY;

        assertEquals("10", Converter.convert("2", src, target));
        assertEquals("-1010", Converter.convert("-10", src, target));
    }

    @Test
    public void convert_decimalToOctal() {
        final int src = Converter.BASE_DECIMAL;
        final int target = Converter.BASE_OCTAL;

        assertEquals("14", Converter.convert("12", src, target));
        assertEquals("-12", Converter.convert("-10", src, target));
    }

    @Test
    public void convert_decimalToHex() {
        final int src = Converter.BASE_DECIMAL;
        final int target = Converter.BASE_HEX;

        assertEquals("f", Converter.convert("15", src, target));
        assertEquals("-2b", Converter.convert("-43", src, target));
    }

    // Octal to others
    // ---------------
    @Test
    public void convert_octalToBinary() {
        final int src = Converter.BASE_OCTAL;
        final int target = Converter.BASE_BINARY;

        assertEquals("1100", Converter.convert("14", src, target));
        assertEquals("-1010", Converter.convert("-12", src, target));
    }

    @Test
    public void convert_octalToDecimal() {
        final int src = Converter.BASE_OCTAL;
        final int target = Converter.BASE_DECIMAL;

        assertEquals("12", Converter.convert("14", src, target));
        assertEquals("-10", Converter.convert("-12", src, target));
    }

    @Test
    public void convert_octalToHex() {
        final int src = Converter.BASE_OCTAL;
        final int target = Converter.BASE_HEX;

        assertEquals("d", Converter.convert("15", src, target));
        assertEquals("-10", Converter.convert("-20", src, target));
    }

    // Hexadecimal to others
    // ---------------------
    @Test
    public void convert_hexToBinary() {
        final int src = Converter.BASE_HEX;
        final int target = Converter.BASE_BINARY;

        assertEquals("1111", Converter.convert("f", src, target));
        assertEquals("-101011", Converter.convert("-2b", src, target));
    }

    @Test
    public void convert_hexToDecimal() {
        final int src = Converter.BASE_HEX;
        final int target = Converter.BASE_DECIMAL;

        assertEquals("15", Converter.convert("f", src, target));
        assertEquals("-43", Converter.convert("-2b", src, target));
    }

    @Test
    public void convert_hexToOctal() {
        final int src = Converter.BASE_HEX;
        final int target = Converter.BASE_OCTAL;

        assertEquals("15", Converter.convert("d", src, target));
        assertEquals("-20", Converter.convert("-10", src, target));
    }

}