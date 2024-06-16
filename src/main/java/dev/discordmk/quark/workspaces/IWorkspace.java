package dev.discordmk.quark.workspaces;

import dev.discordmk.quark.DiscordBot;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public interface IWorkspace {
    ListenerAdapter build(DiscordBot discordBot);
}
