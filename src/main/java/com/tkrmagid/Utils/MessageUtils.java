package com.tkrmagid.Utils;

import com.tkrmagid.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class MessageUtils {
  public static void delete(Message message, Integer time) {
    try {
      Thread.sleep(1000L * time);
      message.delete().queue();
    } catch (InterruptedException ignored) {}
  }
  public static void delete(Message message, Integer time, Boolean selfTime) {
    try {
      if (selfTime) Thread.sleep(time);
      else Thread.sleep(1000L * time);
      message.delete().queue();
    } catch (InterruptedException ignored) {}
  }

  public static Boolean permission(Member member) {
    if (member.getId().equals(Config.config.get("ADMIN_ID"))) return true;
    return member.getPermissions().equals(Permission.ADMINISTRATOR);
  }

  public static MessageEmbed permissionEmbed() {
    return new EmbedBuilder().setColor(EmbedUtils.embedColor)
        .setDescription("이 명령어를 사용할\n권한이 없습니다.").build();
  }
}
