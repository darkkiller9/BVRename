package play.bvrename.com;

import static play.bvrename.com.Utils.getTranslation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.enchantments.Enchantment;


public class Methods {
    public static SimpleRename plugin;
    public Methods(SimpleRename instance) {
        plugin = instance;
    }
    //Rename
    public static void setName(Player player, String name){
      ItemStack item = player.getItemInHand(); 
      ItemMeta itemStackMeta = item.getItemMeta();
      itemStackMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));           
      item.setItemMeta(itemStackMeta);
    }
    //Set a one line lore
    public static void setLore(Player player, String name){
        String[] splittedName = name.split("/n");    
        ItemStack itemStack = player.getItemInHand();
        List<String> lore = new ArrayList();
        for(String s : splittedName){
        lore.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        ItemMeta itemStackMeta = itemStack.getItemMeta();
        itemStackMeta.setLore(lore);
        itemStack.setItemMeta(itemStackMeta);
    }
    //Add a new lore line
    public static void addLore(Player player, String name){
        String[] splittedName = name.split("/n");    
        ItemStack itemStack = player.getItemInHand();
        List<String> lore = itemStack.getItemMeta().getLore();
        if(lore == null){
            setLore(player, name);
        }
        else {
        for(String s : splittedName){
        lore.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        ItemMeta itemStackMeta = itemStack.getItemMeta();
        itemStackMeta.setLore(lore);
        itemStack.setItemMeta(itemStackMeta);
        }
        }
    // Set book author
    public static void setBookAuthor(Player player, String name){
          ItemStack liburua = player.getItemInHand();
          BookMeta meta = (BookMeta) liburua.getItemMeta();
          meta.setAuthor(name);
          liburua.setItemMeta(meta);
    }
    // Set book title
    public static void setBookTitle(Player player, String name){
          ItemStack liburua = player.getItemInHand();
          BookMeta meta = (BookMeta) liburua.getItemMeta();
          meta.setTitle(name);
          liburua.setItemMeta(meta);
    }
    // Set book title
    public static void unSignBook(Player player){
        ItemStack liburua = player.getItemInHand();
        BookMeta metaZaharra = (BookMeta) liburua.getItemMeta();
        ItemStack sinatugabea = new ItemStack(Material.BOOK_AND_QUILL, 1);
        BookMeta metaBerria = (BookMeta) sinatugabea.getItemMeta();
        metaBerria.setPages(metaZaharra.getPages());
        sinatugabea.setItemMeta(metaBerria);
        player.getInventory().setItem(player.getInventory().getHeldItemSlot(),sinatugabea);
    }
    // Clear Meta
    public static void clearItem(Player player){
        ItemStack item = player.getItemInHand();
        int slot = player.getInventory().getHeldItemSlot();
        item.setItemMeta(null);
        player.getInventory().removeItem(item);
        player.getInventory().setItem(slot, item);
    }
    // Duplicate item
    public static void duplicateItem(Player player, int amount){
        ItemStack item = player.getItemInHand();      
        player.getInventory().removeItem(item);
        int amountInHand = item.getAmount();
        int result = amountInHand*amount;
        item.setAmount(result);
        player.getInventory().addItem(item);
        player.sendMessage(ChatColor.GREEN+(getTranslation("10")));
    }
    // Get amount
    public static void getAmount(Player player, int amount){
        ItemStack item = player.getItemInHand();      
        player.getInventory().removeItem(item);
        item.setAmount(amount);
        player.getInventory().addItem(item);
        player.sendMessage(ChatColor.GREEN+(getTranslation("10")));
    }
    //Copy / paste
    public static HashMap<String, ItemMeta > copy = new HashMap<String, ItemMeta>();
        // Copy
    public static void copyMeta(Player player){
        ItemStack item = player.getItemInHand();
        ItemMeta meta = item.getItemMeta();
        copy.put(player.getName(), meta);
        player.sendMessage(ChatColor.GREEN+(getTranslation("11")));
    }
        // Paste
    public static void pasteMeta(Player player){
        ItemStack item1 = player.getItemInHand();
        int slot = player.getInventory().getHeldItemSlot();
        ItemMeta MetaData = copy.get(player.getName());  
        item1.setItemMeta(MetaData);
        player.getInventory().removeItem(item1);
        player.getInventory().setItem(slot, item1);
    }
    //Update
    public static void updatePlugin(){
       // Updater updater = new Updater(this, 75680, getFile(), Updater.UpdateType.NO_VERSION_CHECK, true);
    }
    //Help info
    public static void helpInfo(Player sender, String version1){
        sender.sendMessage(ChatColor.GOLD + "§4§lRename§8 » §f" + " v" + ChatColor.GREEN + version1);
        sender.sendMessage(ChatColor.WHITE + "/rename or /setname");
        sender.sendMessage(ChatColor.WHITE + "/relore or /setlore");     
        sender.sendMessage(ChatColor.WHITE + "/addlore");
        sender.sendMessage(ChatColor.WHITE + "/sr characters");
        sender.sendMessage(ChatColor.WHITE + "/sr clear");
        sender.sendMessage(ChatColor.WHITE + "/sr book setAuthor/setTitle/unSign");
        sender.sendMessage(ChatColor.WHITE + "/sr getskull");
        sender.sendMessage(ChatColor.WHITE + "/sr copy/paste");
        sender.sendMessage(ChatColor.WHITE + "/sr duplicate");
        sender.sendMessage(ChatColor.WHITE + "/sr getamount");
        sender.sendMessage(ChatColor.WHITE + "/sr reload");
        sender.sendMessage(ChatColor.WHITE + "/removelore <lineN>"+ ChatColor.RED + " NEW!");
        sender.sendMessage(ChatColor.WHITE + "/sr hideflags" + ChatColor.RED + " NEW!");
    }
    //Get Skull
    public  static void getSkull(Player p,String owner){
              ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1);
              skull.setDurability((short)3);
              SkullMeta meta = (SkullMeta)skull.getItemMeta();
             // meta.setDisplayName(name);
              meta.setOwner(owner);
              skull.setItemMeta(meta);
              p.getInventory().addItem(skull);
    }
    //Rename mobs
    public static void renameMobs(Player p, String name){
        Listeners.mobs.put(p, name);
        p.sendMessage(ChatColor.GREEN+(getTranslation("17")));
    }
    public static void glowItem(Player p){
      ItemStack item = p.getItemInHand(); 
      ItemMeta itemStackMeta = item.getItemMeta(); 
      itemStackMeta.addEnchant(Enchantment.LURE, 0, true);
      itemStackMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
      item.setItemMeta(itemStackMeta);
      p.updateInventory();
      p.sendMessage(ChatColor.GREEN+(getTranslation("5")));
    }
    public static void hideFlags(Player p){
      ItemStack item = p.getItemInHand(); 
      ItemMeta itemStackMeta = item.getItemMeta(); 
      itemStackMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
      itemStackMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
      itemStackMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
      item.setItemMeta(itemStackMeta);
      p.updateInventory();
      p.sendMessage(ChatColor.GREEN+(getTranslation("5")));
    }
    public static void removeLore(Player p, int n){
      ItemStack item = p.getItemInHand(); 
      ItemMeta itemStackMeta = item.getItemMeta(); 
      if(itemStackMeta.hasLore() && (n != -1)){
        List<String> list =itemStackMeta.getLore();
        if(list.size() >= (n +1)){
            list.remove(n);
            itemStackMeta.setLore(list);
        } 
      }else{
          itemStackMeta.setLore(null);
      }
      item.setItemMeta(itemStackMeta);
      p.updateInventory();
      p.sendMessage(ChatColor.GREEN+(getTranslation("5")));
    }
    
    
    
}