package aarnav100.developer.rajafair;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
//                new String[]{"Canteen","Registration","Entrance","Exit","Hackathon","Exhibition","Break zone"}

public class ShopNavigate extends AppCompatActivity {
    private ArrayList<String> places;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_navigate);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        places=new ArrayList<>();
        places.add("Canteen");
        places.add("Registration");
        places.add("Entrance");
        places.add("Exit");
        places.add("Hackathon");
        places.add("Exhibition");
        places.add("Break zone");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        android.support.v7.widget.SearchView mSearchView =
                (android.support.v7.widget.SearchView) MenuItemCompat.getActionView(searchItem);
        android.support.v7.widget.SearchView.SearchAutoComplete searchAutoComplete = (android.support.v7.widget.SearchView.SearchAutoComplete)
                mSearchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoComplete.setAdapter(new MyArrayAdapter(
                this,R.layout.place_name,places
        ));
        return true;
    }
}
