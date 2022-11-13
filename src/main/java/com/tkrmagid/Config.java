package com.tkrmagid;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {
  public static final Dotenv config = Dotenv.configure().load();
}
