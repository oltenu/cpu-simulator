package component.unit;

import helper.instruction.Instruction;
import component.register.IfId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ControlTest {
    private Control control;

    @BeforeEach
    void setUp() {
        control = new Control();
        control.initializeRegisters(Map.of("IfId", new IfId()));
    }

    @ParameterizedTest
    @MethodSource("controlCases")
    void testControlSignals(String opcode, boolean regDest, boolean extOp, boolean branch,
                            boolean jump, boolean aluSource, boolean memWrite, boolean memToReg,
                            boolean regWrite, String aluOp) {
        IfId ifId = (IfId) control.getRegisters().get("IfId");
        ifId.setInstruction(new Instruction(opcode + "0000000000000"));
        control.update();
        control.run();
        assertEquals(regDest, control.isRegisterDestination());
        assertEquals(extOp, control.isExtendedOperation());
        assertEquals(branch, control.isBranch());
        assertEquals(jump, control.isJump());
        assertEquals(aluSource, control.isAluSource());
        assertEquals(memWrite, control.isMemoryWrite());
        assertEquals(memToReg, control.isMemoryToRegister());
        assertEquals(regWrite, control.isRegisterWrite());
        assertEquals(aluOp, control.getAluOperation());
    }

    private static Stream<Arguments> controlCases() {
        return Stream.of(
                Arguments.of("000", true, false, false, false, false, false, false, true, "000"),
                Arguments.of("001", false, true, false, false, true, false, false, true, "100"),
                Arguments.of("010", false, true, false, false, true, false, true, true, "100"),
                Arguments.of("011", false, true, false, false, true, true, false, false, "100"),
                Arguments.of("100", false, true, true, false, false, false, false, false, "001"),
                Arguments.of("101", false, false, false, false, true, false, false, true, "101"),
                Arguments.of("110", false, true, false, false, true, false, false, true, "110"),
                Arguments.of("111", false, false, false, true, false, false, false, false, "000")
        );
    }
}
