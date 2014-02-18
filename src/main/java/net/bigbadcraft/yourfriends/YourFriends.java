package main.java.net.bigbadcraft.yourfriends;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class YourFriends extends JavaPlugin{
	
	public final ChatColor TEAL = ChatColor.DARK_AQUA;
	public final ChatColor CYAN = ChatColor.AQUA;
	
	public final String PREFIX = TEAL + "[YourFriends] ";

	/* Configurations */
	public File friends_file;
	public FileConfiguration friends_conf;
	
	public File pending_friends_file;
	public FileConfiguration pending_friends_conf;
	
	public FriendManager friend_manager;
	public ConfigHandler conf_handler;
	
	/* Configuration settings */
	public String notification_sound;
	
	public void onEnable(){
		saveDefaultConfig();
		notification_sound = getConfig().getString(ConfigPath.NOTIFICATION_SOUND);
		friends_file = new File(getDataFolder(), "friends.yml");
		pending_friends_file = new File(getDataFolder(), "pendingfriends.yml");
		conf_handler = new ConfigHandler(this);
		Utils.makeFile(friends_file);
		friends_conf = YamlConfiguration.loadConfiguration(friends_file);
		conf_handler.reloadFriendsConf();
		Utils.makeFile(pending_friends_file);
		pending_friends_conf = YamlConfiguration.loadConfiguration(pending_friends_file);
		conf_handler.reloadPendingConf();
		friend_manager = new FriendManager(this);
		getServer().getPluginManager().registerEvents(new PlayerJoinRegisterNameListener(this), this);
		getCommand("yourfriends").setExecutor(new YourFriendsCommand(this));
	}
}
