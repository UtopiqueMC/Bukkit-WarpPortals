package com.mccraftaholics.warpportals.commands;

import com.mccraftaholics.warpportals.bukkit.CommandHandler;
import com.mccraftaholics.warpportals.objects.PortalInfo;
import org.bukkit.command.CommandSender;

public class CmdPortalDelete extends CommandHandlerObject {

    private static final String[] ALIASES = {"wp-portal-delete", "wppd", "pdelete"};
    private static final String PERMISSION = "warpportals.admin.portal.delete.command";
    private static final boolean REQUIRES_PLAYER = false;

    @Override
    public String getPermission() {
        return PERMISSION;
    }

    @Override
    public String[] getAliases() {
        return ALIASES;
    }

    @Override
    public boolean doesRequirePlayer() {
        return REQUIRES_PLAYER;
    }

    @Override
    boolean command(CommandSender sender, String[] args, CommandHandler main) {
        if (args.length == 1) {
            try {
                PortalInfo portal = main.mPortalManager.getPortal(args[0].trim());
                if (portal != null) {
                    main.mPortalManager.deletePortal(portal.uuid);
                } else {
                    sender.sendMessage(main.mCC + args[0] + " is not a valid Portal name.");
                }
            } catch (Exception e) {
                sender.sendMessage(main.mCC + "Error deleting Portal \"" + args[0] + "\"");
            }
        } else
            return false;
        return true;
    }
}