package fr.will33.neisisminage.test.manager;


import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import fr.will33.neisisminage.NeisisMinagePlugin;
import fr.will33.neisisminage.manager.PlayerManager;
import fr.will33.neisisminage.models.NNPlayer;
import fr.will33.neisisminage.test.fixture.ConfigManagerFixture;
import fr.will33.neisisminage.test.fixture.NNPlayerFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class PlayerManagerTest {
    private ServerMock server;
    @Mock
    private NeisisMinagePlugin plugin;
    private PlayerManager playerManager;
    private UUID playerUUID = UUID.randomUUID();

    @BeforeEach
    public void setUp()
    {
        if(!MockBukkit.isMocked()) {
            this.server = MockBukkit.mock();
        }
        playerManager = new PlayerManager(ConfigManagerFixture.getDefault(), null);
    }

    @Test
    public void doitRetournerLeBonXPRequisPourLeNiveau1(){
        int xpRequire = this.playerManager.getXPRequire(1);

        assertEquals(xpRequire, 100);
    }

    @Test
    public void doitRetournerLeBonXPRequisPourLeNiveau5(){
        int xpRequire = this.playerManager.getXPRequire(5);

        assertEquals(xpRequire, 1500);
    }

    @Test
    public void doitRetournerLeBonXPRequisPourLeNiveau8(){
        int xpRequire = this.playerManager.getXPRequire(8);

        assertEquals(xpRequire, 3600);
    }

    @Test
    public void doitPasserNiveau1(){
        NNPlayer nnPlayer = NNPlayerFixture.getFixture(playerUUID, 0, 99, 0);
        this.playerManager.addProgression(nnPlayer, 1, 0);

        assertEquals(nnPlayer.getLevel(), 1);
    }

    @Test
    public void doitPasserNiveau3(){
        NNPlayer nnPlayer = NNPlayerFixture.getFixture(playerUUID, 0, 300, 2);
        this.playerManager.addProgression(nnPlayer, 600, 0);

        assertEquals(nnPlayer.getLevel(), 3);
    }

}
