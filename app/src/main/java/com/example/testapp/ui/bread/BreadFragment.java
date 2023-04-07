package com.example.testapp.ui.bread;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.testapp.Product;
import com.example.testapp.MainActivity;
import com.example.testapp.R;
import com.example.testapp.databinding.FragmentBreadBinding;

import java.util.ArrayList;
import java.util.List;


public class BreadFragment extends Fragment {
    private FragmentBreadBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        List<Product> examples = new ArrayList<>();
        binding = FragmentBreadBinding.inflate(inflater,container, false);
        RelativeLayout layout = binding.getRoot();
        for(Product item: MainActivity.getProducts()){
            if(item.getTag().equals("bread")){
                examples.add(item);
            }
        }
        ViewGroup insertPoint = (ViewGroup) layout.findViewById(R.id.insert_point_bread);
        MainActivity.createRows(examples, inflater, layout, insertPoint);
        return layout;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}