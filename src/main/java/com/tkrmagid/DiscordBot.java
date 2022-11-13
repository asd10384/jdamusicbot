package com.tkrmagid;

import com.tkrmagid.Listener.*;
import com.tkrmagid.Utils.CommandUtils;

public class DiscordBot {
  public static void main(String[] args) {
    Bot bot = new Bot();
    bot.setShardManager(bot.getBuilder().build());

    // EventListeners
    bot.setEventListener(new OnReady());
    bot.setEventListener(new OnGuildReady());
    bot.setEventListener(new OnMessageReceived());
    bot.setEventListener(new OnSlashCommandInteraction());
    bot.setEventListener(new OnGenericSelectMenuInteraction());

    // registerCommands
    CommandUtils.registerCommands();
  }
}
// mysql 연결부터