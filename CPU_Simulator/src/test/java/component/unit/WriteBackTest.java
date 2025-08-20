package component.unit;

import helper.instruction.Instruction;
import helper.instruction.InstructionStage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WriteBackTest {

    @Test
    void testResultFromAlu() {
        WriteBack writeBack = new WriteBack();
        Instruction instr = new Instruction(Instruction.ZERO_16);
        writeBack.setInstruction(instr);
        writeBack.setAluRes("0000000000000011");
        writeBack.setMemoryData("0000000000000100");
        writeBack.setMemoryToRegister(false);
        writeBack.run();
        assertEquals("0000000000000011", writeBack.getResult());
        writeBack.finishInstruction();
        assertEquals(InstructionStage.FINISHED, instr.getInstructionStage());
    }

    @Test
    void testResultFromMemory() {
        WriteBack writeBack = new WriteBack();
        Instruction instr = new Instruction(Instruction.ZERO_16);
        writeBack.setInstruction(instr);
        writeBack.setAluRes("0000000000000011");
        writeBack.setMemoryData("0000000000000100");
        writeBack.setMemoryToRegister(true);
        writeBack.run();
        assertEquals("0000000000000100", writeBack.getResult());
    }
}
