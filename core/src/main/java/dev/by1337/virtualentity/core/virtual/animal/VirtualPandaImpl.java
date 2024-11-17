package dev.by1337.virtualentity.core.virtual.animal;

import dev.by1337.virtualentity.api.entity.PandaGene;
import dev.by1337.virtualentity.api.entity.VirtualEntityType;
import dev.by1337.virtualentity.core.mappings.Mappings;
import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;
import dev.by1337.virtualentity.core.virtual.VirtualAgableMobImpl;

public class VirtualPandaImpl extends VirtualAgableMobImpl implements dev.by1337.virtualentity.api.virtual.animal.VirtualPanda {
    private static final EntityDataAccessor<Integer> UNHAPPY_COUNTER;
    private static final EntityDataAccessor<Integer> SNEEZE_COUNTER;
    private static final EntityDataAccessor<Integer> EAT_COUNTER;
    private static final EntityDataAccessor<Byte> MAIN_GENE_ID;
    private static final EntityDataAccessor<Byte> HIDDEN_GENE_ID;
    private static final EntityDataAccessor<Byte> DATA_ID_FLAGS;

    public VirtualPandaImpl() {
        super(VirtualEntityType.PANDA);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(UNHAPPY_COUNTER, 0);
        this.entityData.define(SNEEZE_COUNTER, 0);
        this.entityData.define(MAIN_GENE_ID, (byte) 0);
        this.entityData.define(HIDDEN_GENE_ID, (byte) 0);
        this.entityData.define(DATA_ID_FLAGS, (byte) 0);
        this.entityData.define(EAT_COUNTER, 0);
    }

    @Override
    public int getUnhappyCounter() {
        return this.entityData.get(UNHAPPY_COUNTER);
    }

    @Override
    public void setUnhappyCounter(int i) {
        this.entityData.set(UNHAPPY_COUNTER, i);
    }

    @Override
    public boolean isSneezing() {
        return this.getFlag(2);
    }

    @Override
    public boolean isSitting() {
        return this.getFlag(8);
    }

    @Override
    public void sit(boolean flag) {
        this.setFlag(8, flag);
    }

    @Override
    public boolean isOnBack() {
        return this.getFlag(16);
    }

    @Override
    public void setOnBack(boolean flag) {
        this.setFlag(16, flag);
    }

    @Override
    public boolean isEating() {
        return this.entityData.get(EAT_COUNTER) > 0;
    }

    @Override
    public void eat(boolean flag) {
        this.entityData.set(EAT_COUNTER, flag ? 1 : 0);
    }

    @Override
    public int getEatCounter() {
        return this.entityData.get(EAT_COUNTER);
    }

    @Override
    public void setEatCounter(int i) {
        this.entityData.set(EAT_COUNTER, i);
    }

    @Override
    public int getSneezeCounter() {
        return this.entityData.get(SNEEZE_COUNTER);
    }

    @Override
    public void setSneezeCounter(int i) {
        this.entityData.set(SNEEZE_COUNTER, i);
    }

    @Override
    public PandaGene getMainGene() {
        return PandaGene.values()[this.entityData.get(MAIN_GENE_ID)];
    }

    @Override
    public void setMainGene(PandaGene pandaGene) {
        if (pandaGene.getId() > 6) {
            pandaGene = PandaGene.NORMAL;
        }
        this.entityData.set(MAIN_GENE_ID, (byte) pandaGene.getId());
    }

    @Override
    public PandaGene getHiddenGene() {
        return PandaGene.values()[this.entityData.get(HIDDEN_GENE_ID)];
    }


    @Override
    public void setHiddenGene(PandaGene pandaGene) {
        if (pandaGene.getId() > 6) {
            pandaGene = PandaGene.NORMAL;
        }
        this.entityData.set(HIDDEN_GENE_ID, (byte) pandaGene.getId());
    }

    private boolean getFlag(int param0) {
        return (this.entityData.get(DATA_ID_FLAGS) & param0) != 0;
    }

    private void setFlag(int param0, boolean param1) {
        byte var3 = this.entityData.get(DATA_ID_FLAGS);
        if (param1) {
            this.entityData.set(DATA_ID_FLAGS, (byte) (var3 | param0));
        } else {
            this.entityData.set(DATA_ID_FLAGS, (byte) (var3 & ~param0));
        }
    }

    static {
        UNHAPPY_COUNTER = Mappings.findAccessor("Panda", "UNHAPPY_COUNTER");
        SNEEZE_COUNTER = Mappings.findAccessor("Panda", "SNEEZE_COUNTER");
        EAT_COUNTER = Mappings.findAccessor("Panda", "EAT_COUNTER");
        MAIN_GENE_ID = Mappings.findAccessor("Panda", "MAIN_GENE_ID");
        HIDDEN_GENE_ID = Mappings.findAccessor("Panda", "HIDDEN_GENE_ID");
        DATA_ID_FLAGS = Mappings.findAccessor("Panda", "DATA_ID_FLAGS");
    }
}