package com.jcsoluciones.superdt.utilities;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import com.jcsoluciones.superdt.R;

import java.util.Vector;

public class GalleryActivity extends AppCompatActivity {
    private Cursor cursor;
    private int columnIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        final GridView sdcardimage = (GridView) findViewById(R.id.gridview);
        DeviceImageManager.getPhoneAlbums(this, new OnPhoneImagesObtained() {
            @Override
            public void onComplete(Vector<PhoneAlbum> albums) {
                GalleryAdapter adapter=new GalleryAdapter(getApplication(),albums);
                sdcardimage.setAdapter(adapter);
            }

            @Override
            public void onError() {

            }
        });

    }


    public class GalleryAdapter extends BaseAdapter {
        private Context context;
        private Vector<PhoneAlbum> albums;
        public GalleryAdapter(Context localContext,Vector<PhoneAlbum> albums) {
            this.albums =albums;
            context = localContext;
        }

        public int getCount() {
            return albums.size();
        }

        public Object getItem(int position) {
            return albums.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = new ViewHolder();

            if (convertView == null) {
                holder.picturesView = new ImageView(context);
                //Converting the Row Layout to be used in Grid View
                convertView = getLayoutInflater().inflate(R.layout.cell_layout, parent, false);

                //You can convert Layout in this Way with the Help of View Stub. View Stub is newer. Read about ViewStub.Inflate
                // and its parameter.
                //convertView= ViewStub.inflate(context,R.layout.row,null);

                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }


            //Setting Image to View Holder Image View.
            holder.picturesView = (ImageView) convertView.findViewById(R.id.img);
            holder.picturesView.setImageBitmap(BitmapFactory.decodeFile(albums.get(position).getCoverUri()));
            holder.picturesView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            return convertView;

        }
        // View Holder pattern used for Smooth Scrolling. As View Holder pattern recycle the findViewById() object.
        class ViewHolder {
            private ImageView picturesView;
        }
    }
}
