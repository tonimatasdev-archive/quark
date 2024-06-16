package dev.discordmk.quark;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Quark {
    public static Logger logger = LoggerFactory.getLogger(Quark.class);
    public static Gson gson = new GsonBuilder().create();
}
