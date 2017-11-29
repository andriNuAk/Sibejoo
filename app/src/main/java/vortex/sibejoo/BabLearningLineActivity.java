package vortex.sibejoo;

import android.content.Intent;
import android.graphics.Point;
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

import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;

import java.util.ArrayList;
import java.util.List;

import adapter.ListTopikLearningLineAdapter;
import api.APIClient;
import api.APIInterface;
import model.BabLearningLine;
import model.BabLearningLineResponse;
import model.TopikLearningLine;
import model.TopikLearningLineResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BabLearningLineActivity extends AppCompatActivity {

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
    static int tingkat;
    static String keterangan;
    ArrayList<BabLearningLine> mBabLearningLine;
    int widthTab;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bab_learning_line);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.


        final Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            tingkat = Integer.valueOf(bundle.getString("tingkatID"));
            keterangan = bundle.getString("keterangan");
        }

        Toast.makeText(getApplicationContext(), "ID "+tingkat, Toast.LENGTH_SHORT).show();

        APIInterface apiInterface = APIClient.getURL().create(APIInterface.class);
        retrofit2.Call<BabLearningLineResponse> call = apiInterface.getBabLearningLine(tingkat, keterangan);
        call.enqueue(new Callback<BabLearningLineResponse>() {
            @Override
            public void onResponse(Call<BabLearningLineResponse> call, Response<BabLearningLineResponse> response) {
                ArrayList<BabLearningLine> babLearningLine = (ArrayList<BabLearningLine>) response.body().getBabLearningLine();
                mBabLearningLine = new ArrayList<BabLearningLine>();
                for (int i=0; i<babLearningLine.size();i++){
                    mBabLearningLine.add(new BabLearningLine(babLearningLine.get(i).getMapel(), babLearningLine.get(i).getJudulBab(), babLearningLine.get(i).getBabID(), babLearningLine.get(i).getStatusAksesLearningLine()));
                }


                mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

                // Set up the ViewPager with the sections adapter.
                mViewPager = (ViewPager) findViewById(R.id.container);

                setupViewPager(mViewPager);

                tabLayout = (TabLayout) findViewById(R.id.tabs);

                tabLayout.setupWithViewPager(mViewPager);

//        int myTabLayoutSize = getw;
                tabLayout.post(mTabLayout_config);
            }

            @Override
            public void onFailure(Call<BabLearningLineResponse> call, Throwable t) {

            }
        });


    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        for (int i=0; i < mBabLearningLine.size(); i++){
            System.out.println("wewewewe"+mBabLearningLine.get(i).getMapel());
            adapter.addFragment(Integer.valueOf(mBabLearningLine.get(i).getBabID()),mBabLearningLine.get(i).getJudulBab());
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bab_learning_line, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final String ARG_ID_NUMBER = "section_number";
        ExpandableHeightListView listTopik;
        ArrayList<TopikLearningLine> mTopik;
        ListTopikLearningLineAdapter topikAdaper;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber, int babID) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putInt(ARG_ID_NUMBER, babID);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_bab_learning_line, container, false);
            listTopik = (ExpandableHeightListView) rootView.findViewById(R.id.listBabLearningLine);
            APIInterface apiInterface = APIClient.getURL().create(APIInterface.class);
            retrofit2.Call<TopikLearningLineResponse> call = apiInterface.getTopikLearningLine(getArguments().getInt(ARG_SECTION_NUMBER));
            call.enqueue(new Callback<TopikLearningLineResponse>() {
                @Override
                public void onResponse(Call<TopikLearningLineResponse> call, Response<TopikLearningLineResponse> response) {
                    ArrayList<TopikLearningLine> topik = (ArrayList<TopikLearningLine>) response.body().getTopikLearningLine();
                    mTopik = topik;
                    if (mTopik != null){
                        topikAdaper = new ListTopikLearningLineAdapter(getActivity(), mTopik);
                        listTopik.setAdapter(topikAdaper);
                        listTopik.setExpanded(true);
                    }
                }

                @Override
                public void onFailure(Call<TopikLearningLineResponse> call, Throwable t) {

                }
            });

            listTopik.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), LearningLineActivity.class);
                    intent.putExtra("topikID", mTopik.get(position).getId().toString());
                    startActivity(intent);
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

        List<String> mFragmentTitleList = new ArrayList<>();
        List<Integer> mBabIDList = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(int babID,String title) {
            mBabIDList.add(babID);
            mFragmentTitleList.add(title);

        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1, mBabIDList.get(position));
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            System.out.println("wewewe count"+mFragmentTitleList.size());
            return mFragmentTitleList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
//            switch (position) {
//                case 0:
//                    return "SECTION 1";
//                case 1:
//                    return "SECTION 2";
//                case 2:
//                    return "SECTION 3";
//            }
            return mFragmentTitleList.get(position);
        }
    }
}
