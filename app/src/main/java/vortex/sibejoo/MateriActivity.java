package vortex.sibejoo;

import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;

import java.util.ArrayList;
import java.util.List;

import adapter.ListMateriAdapter;
import api.APIClient;
import api.APIInterface;
import model.Bab;
import model.BabResponse;
import model.Materi;
import model.MateriResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MateriActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    static int idMapel;
    int widthTab;
    TabLayout tabLayout;
    ArrayList<Bab> mBab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        final Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            idMapel = Integer.valueOf(bundle.getString("IdMapel"));
        }

//        initBab();
        APIInterface apiInterface = APIClient.getURL().create(APIInterface.class);
        retrofit2.Call<BabResponse> call = apiInterface.getBab(idMapel);
        call.enqueue(new Callback<BabResponse>() {
            @Override
            public void onResponse(Call<BabResponse> call, Response<BabResponse> response) {
                ArrayList<Bab> bab = (ArrayList<Bab>) response.body().getBab();
                mBab = new ArrayList<Bab>();
                for (int i=0; i<bab.size();i++){
                    mBab.add(new Bab(bab.get(i).getId(), bab.get(i).getJudulBab()));
                }

//                mSectionsPagerAdapter.initValue(mBab);
//                mBab = bab;
                mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

                // Set up the ViewPager with the sections adapter.
                mViewPager = (ViewPager) findViewById(R.id.container);
//        mViewPager.setAdapter(mSectionsPagerAdapter);
                setupViewPager(mViewPager);

                tabLayout = (TabLayout) findViewById(R.id.tabs);

                tabLayout.setupWithViewPager(mViewPager);

//        int myTabLayoutSize = getw;
                tabLayout.post(mTabLayout_config);
            }

            @Override
            public void onFailure(Call<BabResponse> call, Throwable t) {

            }
        });
//        Syste m.out.println("osas "+mBab.size());


//        tabLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
//            @Override
//            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
//                tabLayout.removeOnLayoutChangeListener(this);
//
//
//            }
//        });





//        if (tabLayout.getWidth() < size.x){
//            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
//        } else {
//            tabLayout.setTabMode(TabLayout.MODE_FIXED);
//        }


        //System.out.println("jumlah bab "+mBab.size());
//        System.out.println("Id dari sebelah = "+idMapel);


    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        for (int i=0; i < mBab.size(); i++){
            adapter.addFragment(Integer.valueOf(mBab.get(i).getId()),mBab.get(i).getJudulBab());
        }

        viewPager.setAdapter(adapter);
    }

    Runnable mTabLayout_config = new Runnable() {
        @Override
        public void run() {
            Display display = getWindowManager().getDefaultDisplay();
            final Point size = new Point();
            display.getSize(size);
            widthTab = tabLayout.getWidth();
            System.out.println("Layout tab : "+tabLayout.getWidth()+" Layout screen : "+size.x);
//            System.out.println("Layout tab babi :"+tabLayout.getWidth());
//            initWidth(tabLayout.getWidth());
            if (widthTab < size.x){
                System.out.println("masuk ke fixed");
                ViewGroup.LayoutParams mParams = tabLayout.getLayoutParams();
                mParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                tabLayout.setTabMode(TabLayout.MODE_FIXED);
                tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            } else {
                System.out.println("masuk ke scroll");
                ViewGroup.LayoutParams mParams = tabLayout.getLayoutParams();
                mParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            }
        }
    };

    public void initBab(){
        final ArrayList<Bab> bab;



//        System.out.println("jumlah taek "+mBab.size());
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_materi, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */

        ExpandableHeightListView listAll, listRoom, listScreen;
        ListMateriAdapter listMateriAdapter;
        ArrayList<Materi> mMateri;
        TextView txtTest;
        static int babID;


        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final String ARG_ID_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber, int mbabID) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putInt(ARG_ID_NUMBER, mbabID);
            fragment.setArguments(args);
//            babID = mbabID;
//            System.out.println("Babi "+babID);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_materi_all, container, false);
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            int section = getArguments().getInt(ARG_SECTION_NUMBER);

            View rootView = inflater.inflate(R.layout.fragment_materi_all, container, false);

            listAll = (ExpandableHeightListView) rootView.findViewById(R.id.listMateriALL);
            APIInterface apiInterface = APIClient.getURL().create(APIInterface.class);
            retrofit2.Call<MateriResponse> call = apiInterface.getMateriBab(idMapel, getArguments().getInt(ARG_SECTION_NUMBER));
            System.out.println("Perkosaa tingpel ="+idMapel+" babid" +getArguments().getInt(ARG_SECTION_NUMBER));
            call.enqueue(new Callback<MateriResponse>() {
                @Override
                public void onResponse(Call<MateriResponse> call, Response<MateriResponse> response) {
                    ArrayList<Materi> materis = (ArrayList<Materi>) response.body().getMateri();
                    mMateri = materis;
                    if (mMateri != null){
                        listMateriAdapter = new ListMateriAdapter(getActivity(), mMateri);
                        listAll.setAdapter(listMateriAdapter);
                        listAll.setExpanded(true);
                    }
                }

                @Override
                public void onFailure(Call<MateriResponse> call, Throwable t) {

                }
            });

            listAll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String linkVideo = mMateri.get(position).getLink();
                    String trueLink = null;
                    String[] arr = linkVideo.split("/");
                    for (int i=0; i< arr.length; i++){
                        System.out.println("Elemen ke "+i+" isinya "+arr[i]);
                        trueLink = arr[i];
                    }

                    if (linkVideo == null || linkVideo.equals("")){
                        Toast.makeText(getContext().getApplicationContext(), "Tidak ada link video", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getContext().getApplicationContext(), VideoServerActivity.class);
                        String videoID = String.valueOf(mMateri.get(position).getVideoID());
                        intent.putExtra("videoID", videoID);
                        intent.putExtra("judulVideo", mMateri.get(position).getJudulVideo());
                        intent.putExtra("link", linkVideo);
                        startActivity(intent);
                    } else {
                        if (arr[2].equals("www.youtube.com")){
//                            Toast.makeText(getContext().getApplicationContext(), "Ini YOutube",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getContext().getApplicationContext(), VideoActivity.class);
                            String videoID = String.valueOf(mMateri.get(position).getVideoID());
                            intent.putExtra("videoID", videoID);
                            intent.putExtra("judulVideo", mMateri.get(position).getJudulVideo());
                            intent.putExtra("link", trueLink);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getContext().getApplicationContext(), "Ini Bukan YOutube",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getContext().getApplicationContext(), VideoServerActivity.class);
                            String videoID = String.valueOf(mMateri.get(position).getVideoID());
                            intent.putExtra("videoID", videoID);
                            intent.putExtra("judulVideo", mMateri.get(position).getJudulVideo());
                            intent.putExtra("link", linkVideo);
                            startActivity(intent);
                        }
                    }


//
//                        Toast.makeText(getContext().getApplicationContext(), ""+mMateri.get(position).getVideoID(),Toast.LENGTH_LONG).show();
                }
            });



            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        ArrayList<Bab> babs;
        List<String> mFragmentTitleList = new ArrayList<>();
        List<Integer> mBabIDList = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(int babID,String title) {
            mBabIDList.add(babID);
            mFragmentTitleList.add(title);

        }

        public void initValue(ArrayList<Bab> babs){
            babs = babs;
            System.out.println("Value "+babs.size());
            for (int i=0; i<babs.size();i++){
                mFragmentTitleList.add(i, babs.get(i).getJudulBab());
            }
            System.out.println("Value title "+mFragmentTitleList.size());
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
//            System.out.println("Babi "+mBabIDList.get(position));
            System.out.println("Babi "+position);
            return PlaceholderFragment.newInstance(position + 1, mBabIDList.get(position));
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            System.out.println("wewewe count"+mFragmentTitleList.size());
            int c = mFragmentTitleList.size();
            return mFragmentTitleList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
//            switch (position) {
//                case 0:
//                    return "ALL";
//                case 1:
//                    return "ROOM";
//                case 2:
//                    return "SCREEN";
//                case 3:
//                    return "ALL";
//                case 4:
//                    return "ROOM";
//                case 5:
//                    return "SCREEN";
//                case 6:
//                    return "ROOM";
//                case 7:
//                    return "SCREEN";
//
//            }
            return mFragmentTitleList.get(position);
        }
    }
}
