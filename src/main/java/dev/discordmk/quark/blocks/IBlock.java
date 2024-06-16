package dev.discordmk.quark.blocks;

import dev.discordmk.quark.DiscordBot;

import java.util.Map;

public interface IBlock {
    Map<String, Object> execute(DiscordBot discordBot, Map<String, Object> values);
}
