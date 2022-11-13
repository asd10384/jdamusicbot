package com.tkrmagid.Commands;

import com.tkrmagid.Utils.Command;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.GenericSelectMenuInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;

public class ExampleCommand implements Command {
  @Override
  public String name() {
    return "example";
  }

  @Override
  public Boolean visible() {
    return true;
  }

  @Override
  public Boolean messageCheck() {
    return true;
  }
  @Override
  public Boolean slashCheck() {
    return true;
  }

  @Override
  public String description() {
    return "예시 명령어";
  }

  @Override
  public String information() {
    return description();
  }

  @Override
  public SlashCommandData commandData() {
    return Commands.slash(name(), information());
  }

  @Override
  public MessageEmbed help() {
    return null;
  }

  @Override
  public void slashRun(SlashCommandInteractionEvent event) {}

  @Override
  public void messageRun(MessageReceivedEvent event, String[] args) {}

  @Override
  public void menuRun(GenericSelectMenuInteractionEvent<String, StringSelectMenu> event) {}
}