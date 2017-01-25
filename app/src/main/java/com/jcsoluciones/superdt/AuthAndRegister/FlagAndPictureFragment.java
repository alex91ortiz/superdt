package com.jcsoluciones.superdt.AuthAndRegister;


import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.jcsoluciones.superdt.R;
import com.jcsoluciones.superdt.Shapes.OneCircleFlagView;
import com.jcsoluciones.superdt.Shapes.TwoHorzCircleFlagView;
import com.jcsoluciones.superdt.Shapes.TwoVertCircleFlagView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link FlagAndPictureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FlagAndPictureFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "username";
    private static final String ARG_PARAM2 = "password";
    private static final String ARG_PARAM3 = "email";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String mParam3;


    public FlagAndPictureFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FlagAndPictureFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FlagAndPictureFragment newInstance(String param1, String param2,String param3,int param4) {
        FlagAndPictureFragment fragment = new FlagAndPictureFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_flag_and_picture, container, false);
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.layout_flag_picture);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
        PagerAdapter pagerAdapter = new PagerAdapter(getActivity().getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    OneCircleFragment tab1 = new OneCircleFragment();
                    return tab1;
                case 1:
                    TwoHorzCircleFragment tab2 = new TwoHorzCircleFragment();
                    return tab2;
                case 2:
                    TwoVertCircleFragment tab3 = new TwoVertCircleFragment();
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }

    public static class OneCircleFragment extends Fragment{
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_one_flag_circle,container,false);
            final OneCircleFlagView mCustomCircleFlagView = (OneCircleFlagView) view.findViewById(R.id.FlagCircle);
            final GridView mGridView = (GridView) view.findViewById(R.id.list_item_colors);
            Button mNextbutton = (Button) view.findViewById(R.id.btn_next_one_flag);
            mNextbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mGridView.setDrawingCacheEnabled(true);
                    mGridView.buildDrawingCache(true);
                    Bitmap b = Bitmap.createBitmap(mGridView.getDrawingCache());
                    String path = Environment.getExternalStorageDirectory().toString();
                    OutputStream fOut = null;
                    Integer counter = 0;
                    File file = new File(path, "FitnessGirl"+counter+".jpg"); // the File to save , append increasing numeric counter to prevent files from getting overwritten.
                    try {
                        fOut = new FileOutputStream(file);
                        Bitmap pictureBitmap =  b; // obtaining the Bitmap
                        pictureBitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut); // saving the Bitmap to a file compressed as a JPEG with 85% compression rate
                        fOut.flush(); // Not really required
                        fOut.close(); // do not forget to close the stream
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                   // MediaStore.Images.Media.insertImage(getContentResolver(),file.getAbsolutePath(),file.getName(),file.getName());
                }//
            });
            final AdapterFlagViewColors mAdapterFlagViewColors= new AdapterFlagViewColors(getActivity(), getResources().getStringArray(R.array.flag_colors));
            mGridView.setAdapter(mAdapterFlagViewColors);
            mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mCustomCircleFlagView.changeColor(mAdapterFlagViewColors.getItem(position));

                }
            });
            return view;
        }
    }

    public static class TwoHorzCircleFragment extends Fragment{
        public int color1=0;


        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_two_horz_flag_circle,container,false);
            final TwoHorzCircleFlagView customCircleFlagView = (TwoHorzCircleFlagView) view.findViewById(R.id.FlagCircle);
            GridView mGridView = (GridView) view.findViewById(R.id.list_item_colors);
            Button mNextbutton = (Button) view.findViewById(R.id.btn_next_two_vert_flag);
            final AdapterFlagViewColors mAdapterFlagViewColors= new AdapterFlagViewColors(getActivity(), getResources().getStringArray(R.array.flag_colors));
            mGridView.setAdapter(mAdapterFlagViewColors);
            mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(color1%2==0) {
                        customCircleFlagView.changeColor1(mAdapterFlagViewColors.getItem(position));
                    }else{
                        customCircleFlagView.changeColor2(mAdapterFlagViewColors.getItem(position));
                    }
                    color1++;
                }
            });
            return view;
        }
    }

    public static class TwoVertCircleFragment extends Fragment{
        public int color1=0;
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_two_vert_flag_circle,container,false);
            final TwoVertCircleFlagView customCircleFlagView = (TwoVertCircleFlagView) view.findViewById(R.id.FlagCircle);
            GridView mGridView = (GridView) view.findViewById(R.id.list_item_colors);
            Button mNextbutton = (Button) view.findViewById(R.id.btn_next_two_horz_flag);
            final AdapterFlagViewColors mAdapterFlagViewColors= new AdapterFlagViewColors(getActivity(), getResources().getStringArray(R.array.flag_colors));
            mGridView.setAdapter(mAdapterFlagViewColors);

            mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(color1%2==0) {
                        customCircleFlagView.changeColor1(mAdapterFlagViewColors.getItem(position));
                    }else{
                        customCircleFlagView.changeColor2(mAdapterFlagViewColors.getItem(position));
                    }
                    color1++;
                }
            });
            return view;
        }
    }
}
