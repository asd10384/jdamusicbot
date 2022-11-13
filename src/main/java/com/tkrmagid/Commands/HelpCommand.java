package com.tkrmagid.Commands;

import com.tkrmagid.Config;
import com.tkrmagid.Utils.Command;
import com.tkrmagid.Utils.CommandUtils;
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

import java.awt.*;
import java.util.Arrays;

public class HelpCommand implements Command {
  private final String prefix = Config.config.get("PREFIX");
  @Override
  public String name() {
    return "help";
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
    return "명령어 확인";
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
  public void slashRun(SlashCommandInteractionEvent event) {
    event.getHook().sendMessageEmbeds(Arrays.asList(makeHelp())).addActionRow(makeMenu()).queue();
  }

  @Override
  public void messageRun(MessageReceivedEvent event, String[] args) {
    event.getChannel().sendMessageEmbeds(Arrays.asList(makeHelp())).addActionRow(makeMenu()).queue(m -> MessageUtils.delete(m, 20));
  }

  @Override
  public void menuRun(GenericSelectMenuInteractionEvent<String, StringSelectMenu> event) {
    if (CommandUtils.commands.containsKey(event.getValues().get(0))) {
      Command command = CommandUtils.commands.get(event.getValues().get(0));
      EmbedBuilder embed = new EmbedBuilder().setColor(EmbedUtils.embedColor);
      if (command.help() != null) {
        event.getHook().sendMessageEmbeds(command.help()).queue();
      } else {
        embed.setTitle("` " + prefix + command.name() + " 도움말 `")
            .setDescription("이름: " + command.name() + "\n설명: " + command.description());
        if (command.slashCheck()) embed.setTitle("` /" + command.name() + " 도움말 `");
        event.getHook().sendMessageEmbeds(embed.build()).queue();
      }
    } else {
      EmbedBuilder embed = new EmbedBuilder()
          .setColor(Color.red)
          .setTitle("명령어를 찾을수 없음");
      event.getHook().sendMessageEmbeds(embed.build()).queue();
    }
  }

  MessageEmbed[] makeHelp() {
    EmbedBuilder slhEmbed = new EmbedBuilder()
        .setTitle("` slash (/) 도움말 `")
        .setDescription("명령어\n명령어 설명")
        .setColor(EmbedUtils.embedColor);
    EmbedBuilder msgEmbed = new EmbedBuilder()
        .setTitle("` 기본 (" + prefix + ") 도움말 `")
        .setDescription("명령어\n명령어 설명")
        .setFooter("PREFIX: " + prefix)
        .setColor(EmbedUtils.embedColor);
    CommandUtils.commands.forEach((key, cmd) -> {
      if (cmd.visible()) {
        if (cmd.slashCheck()) slhEmbed.addField(
            "**/" + cmd.name() + "**",
            cmd.description(),
            true
        );
        if (cmd.messageCheck()) msgEmbed.addField(
            "**" + prefix + cmd.name() + "**",
            cmd.description(),
            true
        );
      }
    });
    return new MessageEmbed[]{ slhEmbed.build(), msgEmbed.build()};
  }

  StringSelectMenu makeMenu() {
    StringSelectMenu.Builder menu = StringSelectMenu.create("menu:class")
        .setId("help")
        .setPlaceholder("명령어를 선택해주세요.");
    CommandUtils.commands.forEach((key, cmd) -> {
      if (cmd.visible()) menu.addOption("/"+cmd.name(), cmd.name(), cmd.description());
    });
    return menu.build();
  }
}