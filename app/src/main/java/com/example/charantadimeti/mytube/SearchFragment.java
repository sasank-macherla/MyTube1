package com.example.charantadimeti.mytube;

//import android.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sasankmacherla on 10/17/15.
 */
public class SearchFragment extends Fragment{

    public static final String NAME = SearchFragment.class.getSimpleName();
//    private static final String KEY_INDEX = "index";
//    private static String ID = "";




    private EditText searchInput;
    private ListView videosFound;

    private Handler handler;

    public static SearchFragment newInstance(int tab) {
        Bundle args = new Bundle();
        args.putInt(NAME, tab);
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_search);

        Intent intent = getActivity().getIntent();




    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_search, container, false);

        searchInput = (EditText)view.findViewById(R.id.search_input);
        videosFound = (ListView)view.findViewById(R.id.videos_found);

        handler = new Handler();

        searchInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    searchOnYoutube(v.getText().toString());
                    return false;
                }
                return true;
            }
        });
        //addClickListener();

        return view;
    }




    private List<VideoItem> searchResults;

    private void searchOnYoutube(final String keywords){
        new Thread(){
            public void run(){
                YoutubeConnector yc = new YoutubeConnector(getActivity());
                searchResults = yc.search(keywords);
                handler.post(new Runnable(){
                    public void run(){
                        updateVideosFound();
                    }
                });
            }
        }.start();
    }

    private void updateVideosFound(){
        ArrayAdapter<VideoItem> adapter = new ArrayAdapter<VideoItem>(getActivity().getApplicationContext(), R.layout.video_item, searchResults){
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                if(convertView == null){
                    convertView = getActivity().getLayoutInflater().inflate(R.layout.video_item, parent, false);
                }
                ImageView thumbnail = (ImageView)convertView.findViewById(R.id.video_thumbnail);
                thumbnail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity().getApplicationContext(), PlayerActivity.class);
                        intent.putExtra("VIDEO_ID", searchResults.get(position).getId());
                        startActivity(intent);

                       // ID = searchResults.get(position).getId();

                    }

                });

                TextView title = (TextView)convertView.findViewById(R.id.video_title);
                //TextView description = (TextView)convertView.findViewById(R.id.video_description);
                TextView publishedDate = (TextView)convertView.findViewById(R.id.video_publishedDate);
                TextView noOfViews = (TextView)convertView.findViewById(R.id.NoOfViews);

                final CheckBox checkBox = (CheckBox)convertView.findViewById(R.id.favorites);

                final VideoItem searchResult = searchResults.get(position);

                Picasso.with(getActivity().getApplicationContext()).load(searchResult.getThumbnailURL()).into(thumbnail);
                title.setText(searchResult.getTitle());
                //description.setText(searchResult.getDescription());
                publishedDate.setText(searchResult.getPublishedDate().toString().substring(0,10));
                noOfViews.setText(searchResult.getNoOfViews().toString());


                checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (checkBox.isChecked()) {
                            //searchResult.setFavorite(checkBox.isChecked());
                            Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Added to playlist SJSU-CMPE-277", Toast.LENGTH_LONG);
                            toast.show();
                            //insertToPlaylist(FAVORITE_LIST_ID, searchResult.getId());
                            checkBox.setVisibility(View.INVISIBLE);
                        }
                    }
                });
                return convertView;
            }
        };

        videosFound.setAdapter(adapter);
    }

//    @Override
//    public void onSaveInstanceState(Bundle savedInstanceState) {
//        super.onSaveInstanceState(savedInstanceState);
//        savedInstanceState.putString(KEY_INDEX, String.valueOf(ID));
//    }

//    private void addClickListener(){
//        videosFound.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> av, View v, int pos,
//                                    long id) {
//                Intent intent = new Intent(getActivity().getApplicationContext(), PlayerActivity.class);
//                intent.putExtra("VIDEO_ID", searchResults.get(pos).getId());
//                startActivity(intent);
//            }
//
//        });
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_search, menu);
//        return true;
//    }

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


//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.activity_search, container, false);
//    }
}
