package com.example.testapp.ui.other;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.testapp.Product;
import com.example.testapp.MainActivity;
import com.example.testapp.R;
import com.example.testapp.databinding.FragmentOtherBinding;

import java.util.ArrayList;
import java.util.List;


public class OtherFragment extends Fragment {
    private List<Product> examples = new ArrayList<>();
    private FragmentOtherBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOtherBinding.inflate(inflater,container, false);
        RelativeLayout layout = binding.getRoot();

        ViewGroup insertPoint = (ViewGroup) layout.findViewById(R.id.insert_point_other);

        for(Product item: MainActivity.getProducts()){
            if(item.getTag().equals("other")){
                examples.add(item);
            }
        }
            MainActivity.createRows(examples, inflater, layout, insertPoint);
        return layout;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}