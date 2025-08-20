package component.unit;

import component.register.ExMem;
import component.register.IfId;
import helper.instruction.Instruction;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class InstructionFetchTest {

    @Test
    void testBranchFetchesCorrectInstruction() {
        InstructionFetch fetch = new InstructionFetch();
        IfId ifId = new IfId();
        ifId.setInstruction(new Instruction(Instruction.ZERO_16));
        ifId.setPcIncrement("0000000000000000");
        ExMem exMem = new ExMem();
        exMem.setBranchAddress("0000000000000001");
        exMem.setBranch(true);
        exMem.setZero(true);
        Control control = new Control();
        control.setJump(false);

        fetch.initializeRegisters(Map.of("IfId", ifId, "ExMem", exMem));
        fetch.initializeUnits(Map.of("Control", control));

        List<Instruction> list = Arrays.asList(
                new Instruction("0000000000000000"),
                new Instruction("0000000000000001"),
                new Instruction("0000000000000010")
        );
        fetch.setInstructions(list);
        fetch.update();
        fetch.run();
        assertEquals(list.get(1), fetch.getInstruction());
    }

    @Test
    void testJumpFetchesCorrectInstruction() {
        InstructionFetch fetch = new InstructionFetch();
        IfId ifId = new IfId();
        ifId.setInstruction(new Instruction("1110000000000011"));
        ifId.setPcIncrement("0000000000000000");
        ExMem exMem = new ExMem();
        exMem.setBranch(false);
        exMem.setZero(false);
        Control control = new Control();
        control.setJump(true);

        fetch.initializeRegisters(Map.of("IfId", ifId, "ExMem", exMem));
        fetch.initializeUnits(Map.of("Control", control));

        List<Instruction> list = Arrays.asList(
                new Instruction("0000000000000000"),
                new Instruction("0000000000000001"),
                new Instruction("0000000000000010"),
                new Instruction("0000000000000011")
        );
        fetch.setInstructions(list);
        fetch.update();
        fetch.run();
        assertEquals(list.get(3), fetch.getInstruction());
    }
}
