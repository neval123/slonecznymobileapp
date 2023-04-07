package com.example.testapp.ui.drink;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.testapp.Product;
import com.example.testapp.MainActivity;
import com.example.testapp.R;
import com.example.testapp.databinding.FragmentDrinkBinding;

import java.util.ArrayList;
import java.util.List;

public class DrinkFragment extends Fragment {
    private List<Product> products = new ArrayList<>();
    private FragmentDrinkBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDrinkBinding.inflate(inflater,container, false);
        RelativeLayout layout = binding.getRoot();
        ViewGroup insertPoint = (ViewGroup) layout.findViewById(R.id.insert_point_drink);
        for(Product item: MainActivity.getProducts()){
            if(item.getTag().equals("drinks_cold")){
                products.add(item);
            }
        }
            MainActivity.createRows(products, inflater, layout, insertPoint);
        return layout;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}