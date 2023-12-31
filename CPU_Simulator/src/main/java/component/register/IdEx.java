package component.register;

import helper.instruction.Instruction;
import lombok.Data;
import component.unit.Control;
import component.unit.InstructionDecoder;
import component.unit.Unit;

import java.util.HashMap;
import java.util.Map;

import static helper.CodeLoader.ZERO_INSTRUCTION;
import static helper.instruction.Instruction.ZERO_16;
import static helper.instruction.Instruction.ZERO_3;

@Data
public class IdEx implements Register {
    private Map<String, Unit> units;
    private Map<String, Register> registers;
    private Instruction instruction;
    private String readData1;
    private String readData2;
    private String extendedImmediate;
    private String func;
    private String rd;
    private String rt;
    private String pcIncrement;
    private String aluOperation;
    private boolean registerDestination;
    private boolean aluSource;
    private boolean branch;
    private boolean memoryWrite;
    private boolean memoryToRegister;
    private boolean registerWrite;
    private boolean sa;

    public IdEx() {
        units = new HashMap<>();
        registers = new HashMap<>();
        instruction = ZERO_INSTRUCTION;
        readData1 = ZERO_16;
        readData2 = ZERO_16;
        extendedImmediate = ZERO_16;
        func = ZERO_3;
        rd = ZERO_3;
        rt = ZERO_3;
        pcIncrement = ZERO_16;
        aluOperation = ZERO_3;
    }

    @Override
    public void update() {
        instruction = ((IfId) registers.get("IfId")).getInstruction();
        readData1 = ((InstructionDecoder) units.get("InstructionDecoder")).getReadData1();
        readData2 = ((InstructionDecoder) units.get("InstructionDecoder")).getReadData2();
        extendedImmediate = ((InstructionDecoder) units.get("InstructionDecoder")).getExtendedImmediate();
        func = ((InstructionDecoder) units.get("InstructionDecoder")).getFunc();
        rd = ((InstructionDecoder) units.get("InstructionDecoder")).getRd();
        rt = ((InstructionDecoder) units.get("InstructionDecoder")).getRt();
        pcIncrement = ((IfId) registers.get("IfId")).getPcIncrement();
        aluOperation = ((Control) units.get("Control")).getAluOperation();
        registerDestination = ((Control) units.get("Control")).isRegisterDestination();
        aluSource = ((Control) units.get("Control")).isAluSource();
        branch = ((Control) units.get("Control")).isBranch();
        memoryWrite = ((Control) units.get("Control")).isMemoryWrite();
        memoryToRegister = ((Control) units.get("Control")).isMemoryToRegister();
        registerWrite = ((Control) units.get("Control")).isRegisterWrite();
        sa = ((InstructionDecoder) units.get("InstructionDecoder")).isSa();
    }

    @Override
    public void initializeUnits(Map<String, Unit> units) {
        this.units = units;
    }

    @Override
    public void initializeRegisters(Map<String, Register> registers) {
        this.registers = registers;
    }

    @Override
    public String toString() {
        return "IdEx{" +
                "instruction=" + instruction +
                ", readData1='" + readData1 + '\'' +
                ", readData2='" + readData2 + '\'' +
                ", extendedImmediate='" + extendedImmediate + '\'' +
                ", func='" + func + '\'' +
                ", rd='" + rd + '\'' +
                ", rt='" + rt + '\'' +
                ", pcIncrement='" + pcIncrement + '\'' +
                ", aluOperation='" + aluOperation + '\'' +
                ", registerDestination=" + registerDestination +
                ", aluSource=" + aluSource +
                ", branch=" + branch +
                ", memoryWrite=" + memoryWrite +
                ", memoryToRegister=" + memoryToRegister +
                ", registerWrite=" + registerWrite +
                ", sa=" + sa +
                '}' + '\n';
    }
}
