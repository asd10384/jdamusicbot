package com.tkrmagid.Listener;

import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class OnReady extends ListenerAdapter {
  @Override
  public void onReady(ReadyEvent event) {
    System.out.println(event.getJDA().getSelfUser().getAsTag() + " 봇 켜짐");
  }
}
