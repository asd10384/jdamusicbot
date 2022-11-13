package com.tkrmagid.Utils;

import com.tkrmagid.Commands.HelpCommand;
import com.tkrmagid.Commands.PingCommand;

import java.util.HashMap;

public class CommandUtils {
  public static HashMap<String, Command> commands = new HashMap<>();

  public static void registerCommands() {
    commands.put("help", new HelpCommand());
    commands.put("ping", new PingCommand());
  }
}