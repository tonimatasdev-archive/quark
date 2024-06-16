package dev.discordmk.quark.blocks.statics;

public abstract class StaticBlock implements IStaticBlock {
    protected String blockId;
    
    public StaticBlock(String blockId) {
        this.blockId = blockId;
    }

    @Override
    public String getId() {
        return blockId;
    }
}
