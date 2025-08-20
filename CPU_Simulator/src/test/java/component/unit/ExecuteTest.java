package component.unit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExecuteTest {

    @Test
    void testRTypeAddition() {
        Execute execute = new Execute();
        execute.setRegisterDestination(true);
        execute.setAluSource(false);
        execute.setReadData1("0000000000000011");
        execute.setReadData2("0000000000000001");
        execute.setFunc("000");
        execute.setAluOperation("000");
        execute.setRd("010");
        execute.setRt("011");
        execute.setPcIncrement("0000000000000100");
        execute.setExtendedImmediate("0000000000000010");
        execute.run();
        assertEquals("010", execute.getRWa());
        assertEquals("0000000000000100", execute.getAluResult());
        assertEquals("0000000000000110", execute.getBranchAddress());
        assertFalse(execute.isZero());
    }

    @Test
    void testImmediateAdditionProducesZero() {
        Execute execute = new Execute();
        execute.setRegisterDestination(false);
        execute.setAluSource(true);
        execute.setReadData1("0000000000000001");
        execute.setExtendedImmediate("1111111111111111"); // -1 in two's complement
        execute.setAluOperation("100"); // addition
        execute.setRd("001");
        execute.setRt("010");
        execute.setPcIncrement("0000000000000000");
        execute.run();
        assertEquals("010", execute.getRWa());
        assertEquals("0000000000000000", execute.getAluResult());
        assertTrue(execute.isZero());
    }
}
