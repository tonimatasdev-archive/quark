package dev.discordmk.quark.blocks;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.discordmk.quark.DiscordBot;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;

import java.util.Map;

public class SendMessage extends Block {
    protected String[] variables;
    
    public SendMessage(String id, String[] variables) {
        super(id);
        this.variables = variables;
    }

    @Override
    public Map<String, Object> execute(DiscordBot discordBot, Map<String, Object> values) {
        String text = (String) values.get(variables[0]);
        MessageChannelUnion channel = (MessageChannelUnion) values.get(variables[1]);
        
        channel.sendMessage(text).queue();
        
        return Map.of();
    }

    @Override
    public String getType() {
        return "send_message";
    }

    public static SendMessage readJson(JsonObject blockJson) {
        String id = blockJson.get("id").getAsString();
        JsonArray jsonVariables = blockJson.get("variables").getAsJsonArray();
        String[] variables = new String[blockJson.get("variables").getAsJsonArray().size()];
        
        for (JsonElement jsonElement : jsonVariables) {
            String[] rawVariable = jsonElement.getAsString().split("-");
            variables[Integer.parseInt(rawVariable[0])] = rawVariable[1];
        }
        
        return new SendMessage(id, variables);
    }
    
    public static JsonObject writeJson(SendMessage block) {
        JsonObject blockJson = new JsonObject();
        
        blockJson.addProperty("id", block.id);
        blockJson.addProperty("type", block.getType());

        // TODO: Implement actions
        
        JsonArray variablesJson = new JsonArray();
        
        for (String variable : block.variables) {
            variablesJson.add(variable);
        }
        
        blockJson.add("variables", variablesJson);
        
        return blockJson;
    }
}
