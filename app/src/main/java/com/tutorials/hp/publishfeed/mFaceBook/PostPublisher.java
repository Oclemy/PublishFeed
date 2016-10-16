package com.tutorials.hp.publishfeed.mFaceBook;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.entities.Feed;
import com.sromku.simple.fb.listeners.OnPublishListener;
import com.tutorials.hp.publishfeed.mDataObject.MyPost;

/**
 * Created by Oclemmy on 5/4/2016 for ProgrammingWizards Channel and http://www.Camposha.com.
 */
public class PostPublisher {

    Context c;
    SimpleFacebook fb;

    public PostPublisher(Context c, SimpleFacebook fb) {
        this.c = c;
        this.fb = fb;
    }

    OnPublishListener publishListener=new OnPublishListener() {
        @Override
        public void onComplete(String response) {
            super.onComplete(response);
            Toast.makeText(c,"Successfully Published",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onException(Throwable throwable) {
            super.onException(throwable);
            Log.e("Exception",throwable.getMessage());

        }

        @Override
        public void onFail(String reason) {
            super.onFail(reason);
            Log.e("Exception", reason);

        }

        @Override
        public void onThinking() {
            super.onThinking();
            Log.i("Exception", "Thinking");

        }
    };

    public void publishFeed(MyPost myPost)
    {
        Feed feed=new Feed.Builder()
                .setName(myPost.getName())
                .setMessage(myPost.getMessage())
                .setDescription(myPost.getDescription())
                .setLink(myPost.getLink())
                .setPicture(myPost.getImageUrl())
                .build();

        fb.publish(feed,publishListener);
    }
}











