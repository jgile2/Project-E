package jgile2.mods.projecte.lib;

import java.util.logging.Level;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

public class LogHelper
{

    private static Logger eeLogger = Logger.getLogger("ProjectE");

    public static void init()
    {
      //  eeLogger.setParent(FMLLog.getLogger());
    }

    public static void log(Level logLevel, Object object)
    {
        eeLogger.log(logLevel, String.valueOf(object));
    }

    public static void severe(Object object)
    {
        log(Level.SEVERE, object);
    }

    public static void debug(Object object)
    {
        log(Level.INFO, String.format("[DEBUG] %s", String.valueOf(object)));
    }

    public static void warning(Object object)
    {
        log(Level.WARNING, object);
    }

    public static void info(Object object)
    {
        log(Level.INFO, object);
    }

    public static void config(Object object)
    {
        log(Level.CONFIG, object);
    }

    public static void fine(Object object)
    {
        log(Level.FINE, object);
    }

    public static void finer(Object object)
    {
        log(Level.FINER, object);
    }

    public static void finest(Object object)
    {
        log(Level.FINEST, object);
    }
}
