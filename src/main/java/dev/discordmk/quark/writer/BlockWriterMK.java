package dev.discordmk.quark.writer;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.discordmk.quark.blocks.IBlock;
import dev.discordmk.quark.blocks.SendMessage;
import dev.discordmk.quark.blocks.statics.IStaticBlock;
import dev.discordmk.quark.blocks.statics.StringBlock;

import java.util.List;

public class BlockWriterMK {
    public static JsonArray writeBlocks(List<IBlock> blocks, List<IStaticBlock> staticBlocks) {
        JsonArray blocksJson = new JsonArray();
        
        for (IBlock block : blocks) {
            JsonObject blockJson = writeBlock(block);
            if (blockJson == null) continue;
            blocksJson.add(blockJson);
        }
        
        for (IStaticBlock staticBlock : staticBlocks) {
            JsonObject staticBlockJson = writeStaticBlock(staticBlock);
            if (staticBlockJson == null) continue;
            blocksJson.add(staticBlockJson);
        }
        
        return blocksJson;
    }
    
    public static JsonObject writeBlock(IBlock block) {
        return switch (block.getType()) {
            case "message_received" -> SendMessage.writeJson((SendMessage) block);
            default -> null;
        };
    }

    public static JsonObject writeStaticBlock(IStaticBlock staticBlock) {
        JsonObject staticBlockJson = new JsonObject();

        staticBlockJson.addProperty("id", staticBlock.getId());
        staticBlockJson.addProperty("type", staticBlock.getType());

        switch (staticBlock.getType()) {
            case "string" -> staticBlockJson.addProperty("value", ((StringBlock) staticBlock).value);
            default -> staticBlockJson = null;
        }
        
        return staticBlockJson;
    }
}
