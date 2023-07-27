package fr.will33.neisisminage.test.fixture;

import fr.will33.neisisminage.models.NNPlayer;

import java.util.UUID;

public class NNPlayerFixture {

    public static NNPlayer getFixture(int points, int totalXP, int level){
        NNPlayer nnPlayer = new NNPlayer(UUID.randomUUID());
        nnPlayer.setPoints(points);
        nnPlayer.setTotalXP(totalXP);
        nnPlayer.setLevel(level);
        return nnPlayer;
    }

}
