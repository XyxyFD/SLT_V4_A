package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player('X');
    }

    @Test
    public void testPlayerInitialization() {

        assertEquals('X', player.getMarker());
    }



    public void playerNotNull(){
        assertNotNull(player);
    }
}
