package fr.hytashi.frame;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestFrame {

    public static ServerMock SERVER;
    public static Frame PLUGIN;

    @BeforeAll
    public void setUp() {
        // Start the mock server
        SERVER = MockBukkit.mock();
        // Load your plugin
        PLUGIN = MockBukkit.load(Frame.class);
    }

    @AfterAll
    public void tearDown() {
        // Stop the mock server
        MockBukkit.unload();
    }

}
