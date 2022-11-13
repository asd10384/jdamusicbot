package com.tkrmagid.Listener;

import com.tkrmagid.Utils.Command;
import com.tkrmagid.Utils.CommandUtils;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class OnSlashCommandInteraction extends ListenerAdapter {
  @Override
  public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
    event.deferReply(true).complete();
    String commandName = event.getName();
    if (CommandUtils.commands.containsKey(commandName)) {
      Command command = CommandUtils.commands.get(commandName);
      if (command.slashCheck()) command.slashRun(event);
    }
  }
}
