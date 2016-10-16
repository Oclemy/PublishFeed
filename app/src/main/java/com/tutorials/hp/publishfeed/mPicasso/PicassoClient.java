package com.tutorials.hp.publishfeed.mPicasso;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tutorials.hp.publishfeed.R;

/**
 * Created by Oclemmy on 5/4/2016 for ProgrammingWizards Channel and http://www.Camposha.com.
 */
public class PicassoClient {

    Context c;
    String url;

    public PicassoClient(Context c, String url) {
        this.c = c;
        this.url = url;
    }

    public boolean downloadImage(ImageView img)
    {
        if(url != null && url.length()>0)
        {
            Picasso.with(c).load(url).placeholder(R.drawable.placeholder).into(img);
            return true;

        }else {
            Picasso.with(c).load(R.drawable.placeholder).into(img);
            return false;
        }
    }

    public String getUrl()
    {
        return url;
    }

}
