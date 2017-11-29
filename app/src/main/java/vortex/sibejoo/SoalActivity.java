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

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import api.APIClient;
import api.APIInterface;
import model.LearningLineSoal;
import model.LearningLineSoalResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SoalActivity extends AppCompatActivity {

    static int topikID = 176;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    static String latihanID;
    ArrayList<LearningLineSoal> mSoal;
    TabLayout tabLayout;
    int widthTab;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.

        final Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            latihanID = bundle.getString("latihanID");
        }
        String[] id = latihanID.split("\\.");
//        Toast.makeText(getApplicationContext(), "id "+id[0], Toast.LENGTH_SHORT).show();

        APIInterface apiInterface = APIClient.getURL().create(APIInterface.class);
        retrofit2.Call<LearningLineSoalResponse> call = apiInterface.getLearningLineSoal(id[0]);
        call.enqueue(new Callback<LearningLineSoalResponse>() {
            @Override
            public void onResponse(Call<LearningLineSoalResponse> call, Response<LearningLineSoalResponse> response) {
                ArrayList<LearningLineSoal> soal = (ArrayList<LearningLineSoal>) response.body().getLearningLineSoal();
                mSoal = soal;


                mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

                // Set up the ViewPager with the sections adapter.
                mViewPager = (ViewPager) findViewById(R.id.container);

                setupViewPager(mViewPager);
//                mViewPager.setAdapter(mSectionsPagerAdapter);

                tabLayout = (TabLayout) findViewById(R.id.tabs);

                tabLayout.setupWithViewPager(mViewPager);

//        int myTabLayoutSize = getw;
                tabLayout.post(mTabLayout_config);
            }

            @Override
            public void onFailure(Call<LearningLineSoalResponse> call, Throwable t) {

            }
        });



    }


    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        for (int i=0; i < mSoal.size(); i++){
//            System.out.println("wewewewe"+mBabLearningLine.get(i).getMapel());
            adapter.addFragment(mSoal.get(i).getIdlat());
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
            ViewGroup.LayoutParams mParams = tabLayout.getLayoutParams();
            mParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        }
    };


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_soal, container, false);
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);   --->jangan di hapus, script reload bundle
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
//            Button btnClick = (Button) rootView.findViewById(R.id.btnClick);
//            btnClick.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Bundle bundle = new Bundle();
//                    bundle.putString("topikID", String.valueOf(topikID));
//                    Intent intent = new Intent(getContext(), LearningLineActivity.class);
//                    intent.putExtras(bundle);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
//                }
//            });
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        List<Integer> mFragmentTitleList = new ArrayList<>();
//        List<Integer> mBabIDList = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(int title) {
//            mBabIDList.add(babID);
            mFragmentTitleList.add(title);

        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            System.out.println("kemem "+mFragmentTitleList.size());
            return mFragmentTitleList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
//            switch (position) {
//                case 0:
//                    return "1";
//                case 1:
//                    return "2";
//                case 2:
//                    return "3";
////                case 3:
////                    return "4";
////                case 4:
////                    return "5";
////                case 5:
////                    return "6";
////                case 6:
////                    return "7";
////                case 7:
////                    return "8";
//            }
            String p = String.valueOf(position+1);
            return p;
        }
    }
}
