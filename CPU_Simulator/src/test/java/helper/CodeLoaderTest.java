package helper;

import helper.instruction.Instruction;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CodeLoaderTest {

    @Test
    void testLoadCode() throws IOException {
        Path temp = Files.createTempFile("program", ".txt");
        Files.writeString(temp, "000_000_000_000_0_000\n001_000_001_0000001");
        List<Instruction> instructions = CodeLoader.loadCode(temp.toString());
        assertEquals(2, instructions.size());
        assertEquals("0000000000000000", instructions.get(0).getInstruction());
        assertEquals("0010000010000001", instructions.get(1).getInstruction());
    }
}
