package component.register;

import component.unit.InstructionFetch;
import helper.instruction.Instruction;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class IfIdTest {

    @Test
    void testUpdate() {
        InstructionFetch fetch = new InstructionFetch();
        fetch.setInstruction(new Instruction(Instruction.ZERO_16));
        fetch.setPcIncrement("0000000000000011");
        IfId ifId = new IfId();
        ifId.initializeUnits(Map.of("InstructionFetch", fetch));
        ifId.initializeRegisters(Map.of());
        ifId.update();
        assertEquals(fetch.getInstruction(), ifId.getInstruction());
        assertEquals("0000000000000011", ifId.getPcIncrement());
    }
}
