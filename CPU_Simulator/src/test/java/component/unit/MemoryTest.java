package component.unit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemoryTest {

    @Test
    void testMemoryWriteAndRead() {
        Memory memory = new Memory();
        memory.setAluResultIn("0000000000000100");
        memory.setReadData2("0000000000001010");
        memory.setMemoryWrite(true);
        memory.run();
        assertEquals("0000000000001010", memory.getMemory()[4]);
        assertEquals("0000000000000100", memory.getAluResultOut());
        assertEquals("0000000000001010", memory.getMemoryData());
    }

    @Test
    void testMemoryReadWithoutWrite() {
        Memory memory = new Memory();
        memory.getMemory()[2] = "0000000000001111";
        memory.setAluResultIn("0000000000000010");
        memory.setReadData2("0000000000000000");
        memory.setMemoryWrite(false);
        memory.run();
        assertEquals("0000000000001111", memory.getMemoryData());
    }
}
