package component.register;

import component.unit.Execute;
import helper.instruction.Instruction;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ExMemTest {

    @Test
    void testUpdate() {
        Instruction instr = new Instruction(Instruction.ZERO_16);
        IdEx idEx = new IdEx();
        idEx.setInstruction(instr);
        idEx.setReadData2("0000000000000011");
        idEx.setBranch(true);
        idEx.setMemoryWrite(true);
        idEx.setMemoryToRegister(true);
        idEx.setRegisterWrite(true);

        Execute execute = new Execute();
        execute.setBranchAddress("0000000000000010");
        execute.setAluResult("0000000000000100");
        execute.setRWa("010");
        execute.setZero(true);

        ExMem exMem = new ExMem();
        exMem.initializeRegisters(Map.of("IdEx", idEx));
        exMem.initializeUnits(Map.of("Execute", execute));
        exMem.update();

        assertEquals(instr, exMem.getInstruction());
        assertEquals("0000000000000010", exMem.getBranchAddress());
        assertEquals("0000000000000100", exMem.getAluResult());
        assertEquals("010", exMem.getRd());
        assertEquals("0000000000000011", exMem.getReadData2());
        assertTrue(exMem.isBranch());
        assertTrue(exMem.isMemoryWrite());
        assertTrue(exMem.isMemoryToRegister());
        assertTrue(exMem.isRegisterWrite());
        assertTrue(exMem.isZero());
    }
}
