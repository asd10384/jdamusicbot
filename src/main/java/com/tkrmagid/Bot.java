package com.tkrmagid;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

public class Bot {
  private ShardManager shardManager;
  private final DefaultShardManagerBuilder builder;
  public Bot() {
    this.builder = DefaultShardManagerBuilder.createDefault(Config.config.get("TOKEN"));

    this.builder.enableIntents(
        GatewayIntent.MESSAGE_CONTENT,
        GatewayIntent.GUILD_MESSAGES,
        GatewayIntent.GUILD_MESSAGE_REACTIONS,
        GatewayIntent.GUILD_MEMBERS
//        GatewayIntent.GUILD_VOICE_STATES
    );
    this.builder.setMemberCachePolicy(MemberCachePolicy.ALL);
    this.builder.setChunkingFilter(ChunkingFilter.ALL);
//    this.builder.enableCache(CacheFlag.VOICE_STATE);

    this.builder.setStatus(OnlineStatus.ONLINE);
    this.builder.setActivity(Activity.playing("코드수정"));
  }

  public DefaultShardManagerBuilder getBuilder() {
    return builder;
  }

  public void setShardManager(ShardManager shardManager) {
    this.shardManager = shardManager;
  }

  public void setEventListener(ListenerAdapter listener) {
    this.shardManager.addEventListener(listener);
  }
}
