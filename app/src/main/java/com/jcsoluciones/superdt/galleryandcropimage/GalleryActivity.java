package com.jcsoluciones.superdt.galleryandcropimage;


import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcsoluciones.superdt.R;
import com.jcsoluciones.superdt.authandregister.ProfileImageFragment;
import com.jcsoluciones.superdt.authandregister.RegisterActivity;

import java.io.Serializable;
import java.util.Vector;

public class GalleryActivity extends AppCompatActivity {
    public static  String selectImageProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        AlbumFragment mAlbumFragment= new AlbumFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.gallery_content,mAlbumFragment).commit();
    }

    private static class AlbumFragment extends Fragment{

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_albums_phone_photo,container,false);

            final GridView sdcardimage = (GridView) view.findViewById(R.id.gridview);
            DeviceImageManager.getPhoneAlbums(getActivity(), new OnPhoneImagesObtained() {
                @Override
                public void onComplete(Vector<PhoneAlbum> albums) {
                    final GalleryAdapter adapter=new GalleryAdapter(getActivity(),albums);
                    sdcardimage.setAdapter(adapter);
                    sdcardimage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            PhoneAlbum mPhoneAlbum = (PhoneAlbum) adapter.getItem(position);
                            PhotoFragment mPhotoFragment= new PhotoFragment(mPhoneAlbum);
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.gallery_content,mPhotoFragment).commit();
                        }
                    });
                }

                @Override
                public void onError() {

                }
            });

            return view;
        }
        public  class GalleryAdapter extends BaseAdapter {
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
                    holder.nameAlbum = new TextView(context);
                    //Converting the Row Layout to be used in Grid View
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.cell_layout, parent,false);

                    //You can convert Layout in this Way with the Help of View Stub. View Stub is newer. Read about ViewStub.Inflate
                    // and its parameter.
                    //convertView= ViewStub.inflate(context,R.layout.row,null);

                    convertView.setTag(holder);

                } else {
                    holder = (ViewHolder) convertView.getTag();
                }


                //Setting Image to View Holder Image View.
                holder.picturesView = (ImageView) convertView.findViewById(R.id.img);
                holder.nameAlbum = (TextView) convertView.findViewById(R.id.name_album);
                BitmapFactory.Options op = new BitmapFactory.Options();
                op.inSampleSize = 8;
                holder.picturesView.setImageBitmap(BitmapFactory.decodeFile(albums.get(position).getCoverUri(),op));
                holder.nameAlbum.setText(albums.get(position).getName());
                holder.picturesView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                return convertView;

            }
            // View Holder pattern used for Smooth Scrolling. As View Holder pattern recycle the findViewById() object.
            class ViewHolder {
                private ImageView picturesView;
                private TextView  nameAlbum;
            }
        }
    }

    private static class PhotoFragment extends Fragment{

        private static final String ARG_PARAM1="Photo";

        private static PhoneAlbum mParam1;

        public static PhotoFragment newInstance(PhoneAlbum mParam1) {
            Bundle args = new Bundle();
            PhotoFragment fragment = new PhotoFragment();
            args.putSerializable(ARG_PARAM1, (Serializable) mParam1);
            fragment.setArguments(args);
            return fragment;
        }

        public PhotoFragment(){}

        public PhotoFragment(PhoneAlbum mParam1){
            this.mParam1=mParam1;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if(getArguments()!=null){
                //mParam1 = (PhoneAlbum) getArguments().getSerializable(ARG_PARAM1);
            }
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_albums_phone_photo,container,false);
            GridView sdcardimage = (GridView) view.findViewById(R.id.gridview);
            final PhotosAdapter adapter=new PhotosAdapter(getActivity(),mParam1.getAlbumPhotos());
            sdcardimage.setAdapter(adapter);
            sdcardimage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    PhonePhoto mPhonePhoto = (PhonePhoto) adapter.getItem(position);
                    Intent intent = new Intent(getActivity(),RegisterActivity.class);
                    intent.putExtra(ARG_PARAM1,mPhonePhoto.getPhotoUri());
                    getActivity().startActivityForResult( intent, ProfileImageFragment.SELECT_PICTURE);

                }
            });
            return view;
        }
        public  class PhotosAdapter extends BaseAdapter {
            private Context context;
            private Vector<PhonePhoto> photo;
            public PhotosAdapter(Context localContext,Vector<PhonePhoto> photo) {
                this.photo =photo;
                context = localContext;
            }

            public int getCount() {
                return photo.size();
            }

            public Object getItem(int position) {
                return photo.get(position);
            }

            public long getItemId(int position) {
                return position;
            }

            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder = new ViewHolder();

                if (convertView == null) {
                    holder.picturesView = new ImageView(context);
                    holder.namePhoto = new TextView(context);
                    //Converting the Row Layout to be used in Grid View
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.cell_layout, parent,false);

                    //You can convert Layout in this Way with the Help of View Stub. View Stub is newer. Read about ViewStub.Inflate
                    // and its parameter.
                    //convertView= ViewStub.inflate(context,R.layout.row,null);

                    convertView.setTag(holder);

                } else {
                    holder = (ViewHolder) convertView.getTag();
                }


                //Setting Image to View Holder Image View.
                holder.picturesView = (ImageView) convertView.findViewById(R.id.img);
                holder.namePhoto = (TextView) convertView.findViewById(R.id.name_album);
                BitmapFactory.Options op = new BitmapFactory.Options();
                op.inSampleSize = 8;
                holder.picturesView.setImageBitmap(BitmapFactory.decodeFile (photo.get(position).getPhotoUri(),op));
                holder.namePhoto.setText("");
                holder.picturesView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                return convertView;

            }
            // View Holder pattern used for Smooth Scrolling. As View Holder pattern recycle the findViewById() object.
            class ViewHolder {
                private ImageView picturesView;
                private TextView  namePhoto;
            }
        }
    }


}
