package org.sumon.rvwithfilter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Product> productList;
    private RvAdapter mAdapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        productList = new ArrayList<>();

        fetchContacts("Giraffe", 1);
        fetchContacts("Tiger", 2);
        fetchContacts("Rhinoceros", 3);
        fetchContacts("Cat", 4);
        fetchContacts("Dog", 5);
        fetchContacts("Bird", 6);
        fetchContacts("Lion", 7);
        fetchContacts("Elephant", 8);
        fetchContacts("Bear", 9);
        fetchContacts("Cattle", 10);
        fetchContacts("Wolf", 11);
        fetchContacts("Rabbit", 12);
        fetchContacts("Snakes", 13);
        fetchContacts("Whales", 14);
        fetchContacts("Monkey", 15);
        
        mAdapter = new RvAdapter(this, productList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });

    }

    private void fetchContacts(String name, int no) {
        productList.add(new Product(name, String.valueOf(no)));
    }


    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }
}
