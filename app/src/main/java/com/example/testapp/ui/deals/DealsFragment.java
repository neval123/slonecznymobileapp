package com.example.testapp.ui.deals;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.testapp.Product;
import com.example.testapp.MainActivity;
import com.example.testapp.R;
import com.example.testapp.databinding.FragmentDealsBinding;

import java.util.ArrayList;
import java.util.List;


public class DealsFragment extends Fragment {

    private FragmentDealsBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDealsBinding.inflate(inflater,container, false);
        List<Product> products = new ArrayList<>();
        RelativeLayout layout = binding.getRoot();
        TextView welcomeText = layout.findViewById(R.id.welcome_text);
        welcomeText.setText("Witamy w Słonecznym!");
        welcomeText.setPadding(0,50,0,0);
        Bitmap sloneczny = BitmapFactory.decodeResource(getResources(), R.drawable.sloneczny);
        ImageView image = layout.findViewById(R.id.welcome_image);
        image.setImageBitmap(sloneczny);
        image.setPadding(40,40,40,0);
        ViewGroup insertPoint = (ViewGroup) layout.findViewById(R.id.insert_point_deals);
        insertPoint.removeAllViewsInLayout();

        MainActivity.createRows(products, inflater, layout, insertPoint);
        return layout;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}

