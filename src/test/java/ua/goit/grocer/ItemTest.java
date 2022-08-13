package ua.goit.grocer;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
class ItemTest {
    @Test
    public void differentObjectsWithIdenticalCodeMustBeEquals(){
        //given
        Item first = new Item("A", 1.5, 0, 0.0);
        Item second = new Item("A", 4.5, 3, 12.0);
        //when
        boolean result = first.equals(second);
        //then
        assertTrue(result);
    }

    @Test
    public void itemMustCorrectlyCastToString(){
        //given
        Item argument = new Item("A", 1.5, 0, 0.0);
        //when
        byte[] result = argument.toString().getBytes(StandardCharsets.UTF_8);
        //then
        String expected = "Item{code='A', price=1.5, promotionPrice=0.0, promotionCount=0}";
        assertArrayEquals(expected.getBytes(StandardCharsets.UTF_8), result);
    }

}