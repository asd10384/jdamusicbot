package com.tkrmagid.Utils;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.GenericSelectMenuInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;

public interface Command {
  String name();
  Boolean visible();
  default Boolean messageCheck() { return false; };
  default Boolean slashCheck() { return false; };
  String description();
  String information();
  SlashCommandData commandData();
  MessageEmbed help();
  void slashRun(SlashCommandInteractionEvent event);
  void messageRun(MessageReceivedEvent event, String[] args);
  void menuRun(GenericSelectMenuInteractionEvent<String, StringSelectMenu> event);
}
