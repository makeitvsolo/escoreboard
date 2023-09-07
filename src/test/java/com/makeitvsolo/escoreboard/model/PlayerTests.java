package com.makeitvsolo.escoreboard.model;

import com.makeitvsolo.escoreboard.core.unique.Unique;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

@DisplayName("Player")
public class PlayerTests {
    private Player player;
    @Mock
    private Unique<UUID> playerId;

    private AutoCloseable closeable;

    @BeforeEach
    public void beforeEach() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void afterEach() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("can be registered")
    public void canBeRegistered() {
        var playerName = "Player Name";
        Mockito.when(playerId.unique())
                .thenReturn(UUID.randomUUID());

        player = Player.register(playerId, playerName);

        Assertions.assertEquals(playerId.unique(), player.id());
        Assertions.assertEquals(playerName, player.name());
    }
}
