package com.tkrmagid.Listener;

import com.tkrmagid.Config;
import com.tkrmagid.Utils.Command;
import com.tkrmagid.Utils.CommandUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.ArrayList;

public class OnMessageReceived extends ListenerAdapter {
  @Override
  public void onMessageReceived(MessageReceivedEvent event) {
    if (event.getAuthor().isBot()) return;
    if (event.getMessage().getContentRaw().startsWith(Config.config.get("PREFIX"))) {
      ArrayList<String> split = new ArrayList<>();
      String beheaded = event.getMessage().getContentRaw().trim().substring(Config.config.get("PREFIX").length());
      String[] splitBeheaded = beheaded.split("\\s+");
      for (String s: splitBeheaded) split.add(s);
      String commandName = split.get(0).toLowerCase();
      String[] args = new String[split.size()-1];
      split.subList(1, split.size()).toArray(args);
      if (CommandUtils.commands.containsKey(commandName)) {
        Command command = CommandUtils.commands.get(commandName);
        if (command.messageCheck()) command.messageRun(event, args);
        try {
          Thread.sleep(500);
          event.getMessage().delete();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      } else {
        if (commandName.isBlank() || commandName.isEmpty() || commandName == null || commandName == "") return;
        event.getChannel().sendMessageEmbeds(
            new EmbedBuilder()
                .setDescription("` " + commandName + " ` 이라는 명령어를 찾을수 없습니다.")
                .setFooter(Config.config.get("PREFIX") + "help 를 입력해 명령어를 확인해주세요.")
                .setColor(Color.red)
                .build()
        ).queue(res -> {
          try {
            Thread.sleep(3000);
            res.delete();
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        });
      }
    }
  }
}
