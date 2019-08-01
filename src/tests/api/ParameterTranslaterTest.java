package api;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParameterTranslaterTest {

    @Test
    public void ParameterTranslater_correctly_translates_accessspecifiers() {
        assertEquals(ParameterTranslater.translateAccessSpecifier("1"),"public");
        assertEquals(ParameterTranslater.translateAccessSpecifier("2"),"private");
        assertEquals(ParameterTranslater.translateAccessSpecifier("3"),"protected");
        assertEquals(ParameterTranslater.translateAccessSpecifier("4"),"");
        assertEquals(ParameterTranslater.translateAccessSpecifier("5"),null);
    }

    @Test
    public void ParameterTranslater_correctly_translates_non_access_specifiers() {
        assertEquals(ParameterTranslater.translateNonAccessModifier("1"),"static");
        assertEquals(ParameterTranslater.translateNonAccessModifier("2"),"final");
        assertEquals(ParameterTranslater.translateNonAccessModifier("3"),"abstract");
        assertEquals(ParameterTranslater.translateNonAccessModifier("4"),"");
        assertEquals(ParameterTranslater.translateNonAccessModifier("5"),null);
    }

    @Test
    public void ParameterTranslater_correctly_translates_cardinalities() {
        assertTrue(ParameterTranslater.translateCardinality("1")==0);
        assertTrue(ParameterTranslater.translateCardinality("2")==1);
        assertTrue(ParameterTranslater.translateCardinality("3")==2);
        assertTrue(ParameterTranslater.translateCardinality("4")==3);
        assertTrue(ParameterTranslater.translateCardinality("5")==4);
        assertTrue(ParameterTranslater.translateCardinality("6")==5);
        assertTrue(ParameterTranslater.translateCardinality("7")==6);
        assertTrue(ParameterTranslater.translateCardinality("11")==-1);
        assertTrue(ParameterTranslater.translateCardinality("100")==-1);
        assertTrue(ParameterTranslater.translateCardinality("-1")==-1);
        assertTrue(ParameterTranslater.translateCardinality("-2")==-1);
        assertTrue(ParameterTranslater.translateCardinality("0")==-1);
    }

    @Test
    public void ParameterTranslater_correctly_translates_strings() {
        assertEquals(ParameterTranslater.translateTypedString("static"), "static");
        assertEquals(ParameterTranslater.translateTypedString("s"), "s");
        assertEquals(ParameterTranslater.translateTypedString(""), null);
    }
}