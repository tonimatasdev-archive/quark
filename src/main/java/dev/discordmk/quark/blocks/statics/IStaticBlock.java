package dev.discordmk.quark.blocks.statics;

import java.util.Map;

public interface IStaticBlock {
    Map<String, Object> execute();

    String getType();
    
    String getId();
}
