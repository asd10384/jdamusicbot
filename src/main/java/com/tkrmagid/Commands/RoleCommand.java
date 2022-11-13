package com.tkrmagid.Commands;

import com.tkrmagid.Utils.Command;
import com.tkrmagid.Utils.MessageUtils;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.GenericSelectMenuInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;

import java.util.Objects;

public class RoleCommand implements Command {
  @Override
  public String name() {
    return "역할";
  }

  @Override
  public Boolean visible() {
    return true;
  }

  @Override
  public Boolean messageCheck() {
    return false;
  }
  @Override
  public Boolean slashCheck() {
    return false;
  }

  @Override
  public SlashCommandData commandData() {
    return null;
  }

  @Override
  public String description() {
    return "특정 명령어 사용가능한 역할 설정";
  }

  @Override
  public String information() {
    return description();
  }

  @Override
  public MessageEmbed help() {
    return null;
  }

  @Override
  public void slashRun(SlashCommandInteractionEvent event) {}

  @Override
  public void messageRun(MessageReceivedEvent event, String[] args) {
    if (MessageUtils.permission(Objects.requireNonNull(event.getMember()))) {
      event.getChannel().sendMessageEmbeds(MessageUtils.permissionEmbed()).queue(m -> MessageUtils.delete(m, 1));
    }
  }

  @Override
  public void menuRun(GenericSelectMenuInteractionEvent<String, StringSelectMenu> event) {}
}
