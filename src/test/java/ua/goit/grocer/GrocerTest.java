package ua.goit.grocer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.MissingResourceException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GrocerTest {
    private final Repository repository = code -> switch (code) {
        case "A" -> Optional.of(new Item("A", 1.25, 3, 3.00));
        case "B" -> Optional.of(new Item("B", 4.25, 0, 0.00));
        case "C" -> Optional.of(new Item("C", 1.00, 6, 5.00));
        case "D" -> Optional.of(new Item("D", 0.75, 0, 0.00));
        default -> Optional.empty();
    };

    private final Grocer grocer = new Grocer(repository);

    @Test
    public void grocerShouldReturnZeroForEmptyString() {
        //given
        String arguments = "";
        //when
        Double result = grocer.getTotalPrice(arguments);
        //then
        assertEquals(0, result);
    }

    @Test
    public void grocerMustTrowNullPointerExceptionForNullOrder() {
        //given
        String arguments = null;
        //when
        Executable result = () -> grocer.getTotalPrice(arguments);
        //then
        assertThrows(NullPointerException.class, result);
    }

    @Test
    public void grocerShouldReturnCorrectValueForOneItemInOrder() {
        //given
        String arguments = "A";
        //when
        Double result = grocer.getTotalPrice(arguments);
        //then
        assertEquals(1.25, result);
    }

    @Test
    public void grocerShouldTrowMissingResourceExceptionWithItemMissileInDatabase() {
        //given
        String arguments = "AE";
        //when
        Executable result = () -> grocer.getTotalPrice(arguments);
        //then
        assertThrows(MissingResourceException.class, result);
    }

    @Test
    public void grocerShouldReturnCorrectValueForPromotionalCountOfItemPlusOneInOrder() {
        //given
        String arguments = "AAAA";
        //when
        Double result = grocer.getTotalPrice(arguments);
        //then
        assertEquals(4.25, result);
    }

    @Test
    public void grocerShouldReturnCorrectValueForTwoDifferentItemsInOrder() {
        //given
        String arguments = "AB";
        //when
        Double result = grocer.getTotalPrice(arguments);
        //then
        assertEquals(5.5, result);
    }

    @Test
    public void grocerShouldReturnCorrectValueForManyItemsInOrder() {
        //given
        String arguments = "ABCDABA";
        //when
        Double result = grocer.getTotalPrice(arguments);
        //then
        assertEquals(13.25, result);
    }


}