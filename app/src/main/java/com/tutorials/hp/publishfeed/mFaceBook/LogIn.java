package com.tutorials.hp.publishfeed.mFaceBook;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.sromku.simple.fb.Permission;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.listeners.OnLoginListener;
import com.tutorials.hp.publishfeed.mDataObject.MyPost;

import java.util.List;

/**
 * Created by Oclemmy on 5/4/2016 for ProgrammingWizards Channel and http://www.Camposha.com.
 */
public class LogIn {

    SimpleFacebook fb;
    Context c;
    MyPost myPost;

    public LogIn(SimpleFacebook fb, Context c, MyPost myPost) {
        this.fb = fb;
        this.c = c;
        this.myPost = myPost;
    }

    OnLoginListener loginListener=new OnLoginListener() {
        @Override
        public void onLogin(String accessToken, List<Permission> acceptedPermissions, List<Permission> declinedPermissions) {
            Toast.makeText(c,"Successfully Logged In",Toast.LENGTH_SHORT).show();

            new PostPublisher(c,fb).publishFeed(myPost);
        }

        @Override
        public void onCancel() {
            Toast.makeText(c,"Cancelled",Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onException(Throwable throwable) {
            Log.e("Exception",throwable.getMessage());
        }

        @Override
        public void onFail(String reason) {
            Log.i("Fail",reason);

        }
    };

    public void login()
    {
        fb.login(loginListener);
    }
}
