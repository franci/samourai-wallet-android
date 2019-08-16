package com.samourai.wallet.util;

import android.app.Activity;
import android.content.res.Configuration;

import java.util.Locale;

public class LocaleUtil
{
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private static volatile Locale systemLocale=null;
    private static volatile Locale settingsLocale=null;
    private static Locale getSettingsLocale(Activity activity)
    {
        if(systemLocale==null)
        {
            systemLocale=Locale.getDefault();
        }
        if(settingsLocale==null)
        {
            String uiLocale = PrefsUtil.getInstance(activity).getValue(PrefsUtil.UI_LOCALE,"");
            if(uiLocale!=null && !uiLocale.isEmpty())
            {
                String[] code = uiLocale.split("_");
                String language = code.length>0 ? code[0] : "";
                String country  = code.length>1 ? code[1] : "";
                return settingsLocale=new Locale(language, country);
            }
            settingsLocale = systemLocale;
        }
        return settingsLocale;
    }
    public static void resetSettingsLocale()
    {
        settingsLocale = null;
    }
    public static void updateLocalForSettings(Activity activity)
    {
        Locale locale = getSettingsLocale(activity);
        if(!Locale.getDefault().equals(locale))
        {
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            activity.getBaseContext().getResources().updateConfiguration(config, activity.getBaseContext().getResources().getDisplayMetrics());
        }
    }
}
