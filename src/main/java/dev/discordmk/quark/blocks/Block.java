package dev.discordmk.quark.blocks;

public abstract class Block implements IBlock {
    protected String id;
    
    public Block(String id) {
        this.id = id;
    }
}
