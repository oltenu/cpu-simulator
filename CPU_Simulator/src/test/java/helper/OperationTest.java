package helper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OperationTest {

    @Test
    void testAddBinary() {
        assertEquals("0000000000000010", Operation.addBinary("0000000000000001", "0000000000000001"));
    }

    @Test
    void testSubtractBinary() {
        assertEquals("0000000000000001", Operation.subtractBinary("0000000000000010", "0000000000000001"));
    }

    @Test
    void testShiftLeftLogic() {
        assertEquals("1000000000000000", Operation.shiftLeftLogic("0100000000000000"));
    }

    @Test
    void testShiftRightLogic() {
        assertEquals("0010000000000000", Operation.shiftRightLogic("0100000000000000"));
    }

    @Test
    void testShiftRightArithmetic() {
        assertEquals("1010000000000000", Operation.shiftRightArithmetic("1100000000000000"));
    }

    @Test
    void testOr() {
        assertEquals("0000000000000011", Operation.OR("0000000000000001", "0000000000000011"));
    }

    @Test
    void testAnd() {
        assertEquals("0000000000000001", Operation.AND("0000000000000001", "0000000000000011"));
    }

    @Test
    void testSetOnLessThan() {
        assertEquals("0000000000000001", Operation.setOnLessThan("0000000000000001", "0000000000000011"));
        assertEquals("0000000000000000", Operation.setOnLessThan("0000000000000100", "0000000000000011"));
    }
}
