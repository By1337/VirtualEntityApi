package dev.by1337.virtualentity.api.virtual.animal;

import dev.by1337.virtualentity.api.entity.PandaGene;
import dev.by1337.virtualentity.api.virtual.VirtualAgeableMob;

public interface VirtualPanda extends VirtualAgeableMob {
    int getUnhappyCounter();

    void setUnhappyCounter(int i);

    boolean isSneezing();

    boolean isSitting();

    void sit(boolean flag);

    boolean isOnBack();

    void setOnBack(boolean flag);

    boolean isEating();

    void eat(boolean flag);

    int getEatCounter();

    void setEatCounter(int i);

    int getSneezeCounter();

    void setSneezeCounter(int i);

    PandaGene getMainGene();

    void setMainGene(PandaGene pandaGene);

    PandaGene getHiddenGene();

    void setHiddenGene(PandaGene pandaGene);
}
