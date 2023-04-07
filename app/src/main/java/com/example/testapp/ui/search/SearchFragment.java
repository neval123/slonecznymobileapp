package com.example.testapp.ui.search;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.testapp.Product;
import com.example.testapp.MainActivity;
import com.example.testapp.R;
import com.example.testapp.databinding.FragmentSearchBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchFragment extends Fragment {
    private List<Product> products = new ArrayList<>();
    private FragmentSearchBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater,container, false);
        RelativeLayout layout = binding.getRoot();
        SearchView searchView = layout.findViewById(R.id.search_view);
        ViewGroup insertPoint = (ViewGroup) layout.findViewById(R.id.insert_point_search);
        searchView.setQueryHint("Wyszukaj produkt po nazwie...");
        searchView.setIconified(false);
        TextView searchText = layout.findViewById(R.id.search_text);
        searchText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        searchView.setOnClickListener(view -> {
            searchView.requestFocus();
        });
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                boolean found = false;
                products.clear();
                if(query.length()>2) {
                    insertPoint.removeAllViewsInLayout();
                    int i = 0;
                    for (Product product : MainActivity.getProducts()) {
                        if (product.getName().toUpperCase(Locale.ROOT).contains(query.toUpperCase())) {
                            products.add(product);
                            i++;
                            found = true;
                        }
                    }
                    if (!found) {
                        searchText.setText("Nie znaleziono artykułu o podanej nazwie.");
                    } else {
                        MainActivity.createRows(products, inflater, layout, insertPoint);
                        searchText.setText(i + " wynik/ów dla: " + query);
                    }
                }else{
                    searchText.setText("Wprowadź minimum 3 znaki!");
                }
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
        return layout;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}