package com.tkrmagid.Listener;

import com.tkrmagid.Utils.CommandUtils;
import net.dv8tion.jda.api.events.interaction.component.GenericSelectMenuInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class OnGenericSelectMenuInteraction extends ListenerAdapter {
  @Override
  public void onGenericSelectMenuInteraction(GenericSelectMenuInteractionEvent event) {
    event.deferReply(true).complete();
    String commandName = event.getComponentId();
    if (CommandUtils.commands.containsKey(commandName)) {
      CommandUtils.commands.get(commandName).menuRun(event);
    }
  }
}
