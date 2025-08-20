package component.register;

import component.unit.Memory;
import helper.instruction.Instruction;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MemWbTest {

    @Test
    void testUpdate() {
        Instruction instr = new Instruction(Instruction.ZERO_16);
        ExMem exMem = new ExMem();
        exMem.setInstruction(instr);
        exMem.setAluResult("0000000000000100");
        exMem.setRd("010");
        exMem.setMemoryToRegister(true);
        exMem.setRegisterWrite(true);

        Memory memory = new Memory();
        memory.setAluResultOut("0000000000000100");
        memory.setMemoryData("0000000000000011");

        MemWb memWb = new MemWb();
        memWb.initializeRegisters(Map.of("ExMem", exMem));
        memWb.initializeUnits(Map.of("Memory", memory));
        memWb.update();

        assertEquals(instr, memWb.getInstruction());
        assertEquals("0000000000000100", memWb.getAluResult());
        assertEquals("0000000000000011", memWb.getMemoryData());
        assertEquals("010", memWb.getRd());
        assertTrue(memWb.isMemoryToRegister());
        assertTrue(memWb.isRegisterWrite());
    }
}
