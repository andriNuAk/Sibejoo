package vortex.sibejoo;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.TextView;

import com.github.paolorotolo.expandableheightlistview.ExpandableHeightListView;

import java.util.ArrayList;

import adapter.ListMapelAdapter;
import api.APIClient;
import api.APIInterface;
import model.MataPelajaran;
import model.MataPelajaranResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MataPelajaranActivity extends AppCompatActivity {

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
    int tingkatID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mata_pelajaran);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.

        final Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            tingkatID = Integer.valueOf(bundle.getString("tingkatID"));
        }



        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        if (tingkatID == 1){
            mViewPager.setCurrentItem(0);
        } else if (tingkatID == 2 ){
            mViewPager.setCurrentItem(1);
        } else if (tingkatID == 3){
            mViewPager.setCurrentItem(2);
        } else if (tingkatID == 4){
            mViewPager.setCurrentItem(3);
        } else if (tingkatID == 5){
            mViewPager.setCurrentItem(4);
        }


    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */

        ArrayList<MataPelajaran> mMataPelajaran;
        ExpandableHeightListView listMapel;
        ListMapelAdapter listMapelAdapter;
        CardView cardListMapel;
        TextView txtText;

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
            View rootView = inflater.inflate(R.layout.fragment_mata_pelajaran, container, false);
            listMapel = (ExpandableHeightListView) rootView.findViewById(R.id.listMapel);
            cardListMapel = (CardView) rootView.findViewById(R.id.cardListMapel);
            txtText = (TextView) rootView.findViewById(R.id.txtText);
            APIInterface apiService = APIClient.getURL().create(APIInterface.class);
            retrofit2.Call<MataPelajaranResponse> call = apiService.getMapel(getArguments().getInt(ARG_SECTION_NUMBER));
            call.enqueue(new Callback<MataPelajaranResponse>() {
                @Override
                public void onResponse(Call<MataPelajaranResponse> call, Response<MataPelajaranResponse> response) {
                    ArrayList<MataPelajaran> mataPelajaren = (ArrayList<MataPelajaran>) response.body().getMataPelajaran();
                    mMataPelajaran = mataPelajaren;
                    System.out.println("eaaaakkkk"+mMataPelajaran);
                    if (mataPelajaren != null || mataPelajaren.isEmpty() == false){
                        listMapelAdapter = new ListMapelAdapter(getContext().getApplicationContext(), mMataPelajaran);
                        listMapel.setAdapter(listMapelAdapter);
                        listMapel.setExpanded(true);
                    }
                }

                @Override
                public void onFailure(Call<MataPelajaranResponse> call, Throwable t) {
                    cardListMapel.setVisibility(View.GONE);
                    txtText.setText("Tidak ada data mata pelajaran");
                }
            });

            listMapel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getContext().getApplicationContext(), MateriActivity.class);
                    intent.putExtra("IdMapel", mMataPelajaran.get(position).getId().toString());
                    startActivity(intent);

//                Toast.makeText(getApplicationContext(), "Id : "+mMataPelajaran.get(position).getId(), Toast.LENGTH_LONG).show();
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

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
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
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SD";
                case 1:
                    return "SMP";
                case 2:
                    return "SMA";
                case 3:
                    return "SMA-IPA";
                case 4:
                    return "SMA-IPS";
            }
            return null;
        }
    }

    @Override
    public void onBackPressed() {
//            BerandaActivity ba = new BerandaActivity();
//            ba.stopRipple();
//            super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), BerandaActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
