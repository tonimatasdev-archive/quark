package dev.discordmk.quark.reader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.discordmk.quark.DiscordBot;
import dev.discordmk.quark.Quark;
import dev.discordmk.quark.workspaces.IWorkspace;
import dev.discordmk.quark.workspaces.MessageReceived;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ReaderMK {
    protected File jsonFile;

    public ReaderMK(File jsonFile) {
        this.jsonFile = jsonFile;
    }

    public DiscordBot build() {
        JsonObject jsonObject;
        try {
            jsonObject = Quark.gson.fromJson(new FileReader(this.jsonFile), JsonObject.class);
        } catch (FileNotFoundException e) {
            System.out.println("Error on read JSON");
            return null;
        }

        String name = jsonObject.get("name").getAsString();
        String token = jsonObject.get("token").getAsString();

        JsonArray workspacesJson = jsonObject.get("workspaces").getAsJsonArray();
        
        List<IWorkspace> workspaces = new ArrayList<>();
        
        for (JsonElement jsonElement : workspacesJson) {
            JsonObject workspaceJson = jsonElement.getAsJsonObject();
            workspaces.add(readWorkspace(workspaceJson));
        }
        
        return new DiscordBot(token, name, workspaces);
    }
    
    public static IWorkspace readWorkspace(JsonObject workspaceJson) {
        String workspaceType = workspaceJson.get("type").getAsString();
        return switch (workspaceType) {
            case "message_received" -> MessageReceived.readJson(workspaceJson);
            default -> sendErrorReturningNull(workspaceType);
        };
    }
    
    public static IWorkspace sendErrorReturningNull(String workspace) {
        Quark.logger.error("The workspace \"{}\" does not exist.", workspace);
        return null;
    }
}
