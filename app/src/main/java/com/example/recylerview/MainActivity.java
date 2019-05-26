package com.example.recylerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvCategory;
    private ArrayList<President> list = new ArrayList<>();
    private String title = "Mode List";
    final String STATE_TITLE = "state_title";
    final String STATE_LIST = "state_list";
    final String STATE_MODE = "state_mode";
    int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCategory = findViewById(R.id.rvCategosy);
        rvCategory.setHasFixedSize(true);

        list.addAll(PresidentData.getListData());
        showRecycleList();


        if (savedInstanceState == null ){
            setActionBarTitle("Mode List");
            list.addAll(PresidentData.getListData());
            showRecycleList();
            mode = R.id.actionList;
        }else {
            String stateTitle = savedInstanceState.getString(STATE_TITLE);
            ArrayList<President> stateList = savedInstanceState.getParcelableArrayList(STATE_LIST);
            int stateMode = savedInstanceState.getInt(STATE_MODE);
            setActionBarTitle(stateTitle);
            list.addAll(stateList);
            setMode(stateMode);
        }
    }

    private void setMode(int stateMode) {
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_TITLE, getSupportActionBar().getTitle().toString());
        outState.putParcelableArrayList(STATE_LIST, list);
        outState.putInt(STATE_MODE, mode);
    }
    
    private void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    private void showRecycleList(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        ListPresidentAdapter listPresidentAdapter = new ListPresidentAdapter(this);
        listPresidentAdapter.setListPresident(list);
        rvCategory.setAdapter(listPresidentAdapter);
    }

    private void showRecycleGrid(){
        rvCategory.setLayoutManager(new GridLayoutManager(this, 2));
        GridPresidentAdapter gridPresidentAdapter = new GridPresidentAdapter(this);
        gridPresidentAdapter.setListPresident(list);
        rvCategory.setAdapter(gridPresidentAdapter);
    }

    private void showRecyclerCardView(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        CardViewPresidentAdapter cardViewPresidentAdapter = new CardViewPresidentAdapter(this);
        cardViewPresidentAdapter.setListPresident(list);
        rvCategory.setAdapter(cardViewPresidentAdapter);
    }


    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }


    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.actionList:
                setActionBarTitle("Mode List");
                showRecycleList();
                break;

            case R.id.actionGrid:
                setActionBarTitle("Mode Grid");
                showRecycleGrid();
                break;

            case R.id.actionCardView:
                setActionBarTitle("Mode Card View");
                showRecyclerCardView();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
