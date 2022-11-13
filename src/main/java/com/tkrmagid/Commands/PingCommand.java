package com.tkrmagid.Commands;

import com.tkrmagid.Utils.Command;
import com.tkrmagid.Utils.EmbedUtils;
import com.tkrmagid.Utils.MessageUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.GenericSelectMenuInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;

import java.time.Duration;

public class PingCommand implements Command {

  @Override
  public String name() {
    return "ping";
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
    return "PONG!";
  }

  @Override
  public String information() {
    return "pong";
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
  public void slashRun(SlashCommandInteractionEvent event) {
    event.getHook().sendMessageEmbeds(pong()).queue(m -> {
      long ping = Duration.between(event.getInteraction().getTimeCreated(), m.getTimeCreated()).toMillis();
      m.replyEmbeds(
          new EmbedBuilder()
              .setColor(EmbedUtils.embedColor)
              .setTitle("**" + ping/1000 + "**ms")
              .build()
      ).queue(m2 -> MessageUtils.delete(m2, 3));
    });
  }

  @Override
  public void messageRun(MessageReceivedEvent event, String[] args) {
    event.getChannel().sendMessageEmbeds(pong()).queue(m -> {
      long ping = Duration.between(event.getMessage().getTimeCreated(), m.getTimeCreated()).toMillis();
      m.editMessageEmbeds(
          new EmbedBuilder()
              .setColor(EmbedUtils.embedColor)
              .setTitle("**" + ping/1000 + "**ms")
              .build()
      ).queue();
    });
  }

  @Override
  public void menuRun(GenericSelectMenuInteractionEvent<String, StringSelectMenu> event) {}

  MessageEmbed pong() {
    return new EmbedBuilder()
        .setTitle("**PONG!**")
        .setColor(EmbedUtils.embedColor)
        .build();
  }
}
