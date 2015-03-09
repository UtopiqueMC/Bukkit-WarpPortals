package com.mccraftaholics.warpportals.commands;

import com.mccraftaholics.warpportals.bukkit.CommandHandler;
import com.mccraftaholics.warpportals.helpers.Regex;
import com.mccraftaholics.warpportals.helpers.Utils;
import com.mccraftaholics.warpportals.objects.Coords;
import com.mccraftaholics.warpportals.objects.CoordsPY;
import com.mccraftaholics.warpportals.objects.PortalInfo;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class CmdPortalTeleport extends CommandHandlerObject {

    private static final String[] ALIASES = {"wp-portal-teleport", "wp-portal-tp", "wpptp"};
    private static final String PERMISSION = "warpportals.admin.portal.teleport";
    private static final boolean REQUIRES_PLAYER = true;

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
    boolean command(Player player, String[] args, CommandHandler main) {
        if (args.length == 1) {
            if (args[0].matches(Regex.ALPHANUMERIC_NS_TEXT)) {
                try {
                    CoordsPY tpCoords = null;
                    PortalInfo portal = main.mPortalManager.getPortal(args[0]);
                    if (portal != null) {
                        Coords midCrds = portal.blocks.get(portal.blocks.size() > 1 ? portal.blocks.size() / 2 : 0);
                        tpCoords = new CoordsPY(new Coords(midCrds.world, midCrds.x, midCrds.y, midCrds.z));
                        tpCoords.z += 1;
                        for (Coords crd : portal.blocks) {
                            if (tpCoords.equals(crd)) {
                                tpCoords.z += 1;
                                tpCoords.y += 1;
                                tpCoords.x += 1;
                            }
                        }
                    } else
                        player.sendMessage(main.mCC + "\"" + args[0] + "\" is not a valid Portal.");
                    if (tpCoords != null) {
                        Location loc = player.getLocation();
                        Utils.coordsToLoc(tpCoords, loc);
                        player.teleport(loc);
                        player.sendMessage(main.mCC + "Teleported to \"" + args[0] + "\" @ " + tpCoords.toNiceString());
                    } else
                        throw new Exception();
                } catch (Exception e) {
                    player.sendMessage(main.mCC + "Error teleporting to WarpPortal \"" + args[0] + "\"");
                }
            } else
                player.sendMessage(main.mCC + "Command argument must be a valid alpha-numeric WarpPortal name.");
        } else
            return false;
        return true;
    }
}
