package helper.instruction;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InstructionTest {

    @Test
    void testGlobalIdReset() {
        Instruction.resetGlobalId();
        Instruction i1 = new Instruction(Instruction.ZERO_16);
        Instruction i2 = new Instruction(Instruction.ZERO_16);
        assertEquals(1, i1.getId());
        assertEquals(2, i2.getId());
        Instruction.resetGlobalId();
        Instruction i3 = new Instruction(Instruction.ZERO_16);
        assertEquals(1, i3.getId());
    }

    @Test
    void testDefaultStage() {
        Instruction instr = new Instruction(Instruction.ZERO_16);
        assertEquals(InstructionStage.FETCH, instr.getInstructionStage());
        instr.setInstructionStage(InstructionStage.EXECUTE);
        assertEquals(InstructionStage.EXECUTE, instr.getInstructionStage());
    }
}
