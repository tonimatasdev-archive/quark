package dev.discordmk.quark.writer;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.discordmk.quark.workspaces.IWorkspace;
import dev.discordmk.quark.workspaces.MessageReceived;

import java.util.List;

public class WriterMK {
    public static JsonObject build(String name, String token, List<IWorkspace> workspaces) {
        JsonObject mainJson = new JsonObject();

        mainJson.addProperty("name", name);
        mainJson.addProperty("token", token);
        
        JsonArray workspacesJson = new JsonArray();
        
        for (IWorkspace workspace : workspaces) {
            JsonObject workspaceJson = writeWorkspace(workspace);
            
            if (workspaceJson == null) continue;
            
            workspacesJson.add(workspaceJson);
        }
        
        mainJson.add("workspaces", workspacesJson);
        
        return mainJson;
    }
    
    public static JsonObject writeWorkspace(IWorkspace workspace) {
        return switch (workspace.getType()) {
            case "message_received" -> MessageReceived.writeJson((MessageReceived) workspace);
            default -> null;
        };
    }
}
