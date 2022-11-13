package com.tkrmagid.Listener;

import com.tkrmagid.Utils.CommandUtils;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

import java.util.ArrayList;
import java.util.List;

public class OnGuildReady extends ListenerAdapter {
  @Override
  public void onGuildReady(GuildReadyEvent event) {
    List<CommandData> commandData = new ArrayList<>();
    CommandUtils.commands.forEach((key, cmd) -> {
      if (cmd.visible() && cmd.slashCheck()) commandData.add(cmd.commandData());
    });
    event.getGuild().updateCommands().addCommands(commandData).complete();
    System.out.println(event.getGuild().getName() + " 서버 slash 명령어 리로드 완료");
  }
}
