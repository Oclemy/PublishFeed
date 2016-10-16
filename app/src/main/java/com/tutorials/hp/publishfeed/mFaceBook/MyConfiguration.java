package com.tutorials.hp.publishfeed.mFaceBook;

import com.sromku.simple.fb.Permission;
import com.sromku.simple.fb.SimpleFacebookConfiguration;

/**
 * Created by Oclemmy on 5/4/2016 for ProgrammingWizards Channel and http://www.Camposha.com.
 */
public class MyConfiguration {

    Permission[] permissions=new Permission[]{Permission.EMAIL,Permission.USER_PHOTOS,Permission.PUBLISH_ACTION};
    static final String APP_ID="";

    public SimpleFacebookConfiguration getMyConfigs()
    {
        SimpleFacebookConfiguration configs=new SimpleFacebookConfiguration.Builder()
                .setAppId(APP_ID)
                .setNamespace("publishfeed")
                .setPermissions(permissions)
                .build();


        return configs;
    }

}
