package component.register;

import component.unit.Control;
import component.unit.InstructionDecoder;
import helper.instruction.Instruction;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class IdExTest {

    @Test
    void testUpdate() {
        Instruction instruction = new Instruction(Instruction.ZERO_16);
        IfId ifId = new IfId();
        ifId.setInstruction(instruction);
        ifId.setPcIncrement("0000000000000001");

        InstructionDecoder decoder = new InstructionDecoder();
        decoder.setReadData1("0000000000000010");
        decoder.setReadData2("0000000000000011");
        decoder.setExtendedImmediate("0000000000000100");
        decoder.setFunc("101");
        decoder.setRd("010");
        decoder.setRt("011");
        decoder.setSa(true);

        Control control = new Control();
        control.setAluOperation("110");
        control.setRegisterDestination(true);
        control.setAluSource(true);
        control.setBranch(true);
        control.setMemoryWrite(true);
        control.setMemoryToRegister(true);
        control.setRegisterWrite(true);

        IdEx idEx = new IdEx();
        idEx.initializeUnits(Map.of("InstructionDecoder", decoder, "Control", control));
        idEx.initializeRegisters(Map.of("IfId", ifId));
        idEx.update();

        assertEquals(instruction, idEx.getInstruction());
        assertEquals("0000000000000010", idEx.getReadData1());
        assertEquals("0000000000000011", idEx.getReadData2());
        assertEquals("0000000000000100", idEx.getExtendedImmediate());
        assertEquals("101", idEx.getFunc());
        assertEquals("010", idEx.getRd());
        assertEquals("011", idEx.getRt());
        assertEquals("0000000000000001", idEx.getPcIncrement());
        assertEquals("110", idEx.getAluOperation());
        assertTrue(idEx.isRegisterDestination());
        assertTrue(idEx.isAluSource());
        assertTrue(idEx.isBranch());
        assertTrue(idEx.isMemoryWrite());
        assertTrue(idEx.isMemoryToRegister());
        assertTrue(idEx.isRegisterWrite());
        assertTrue(idEx.isSa());
    }
}
