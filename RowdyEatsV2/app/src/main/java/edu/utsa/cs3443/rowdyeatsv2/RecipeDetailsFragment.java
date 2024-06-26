package edu.utsa.cs3443.rowdyeatsv2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.Navigation;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import edu.utsa.cs3443.rowdyeatsv2.Model.Recipe;

public class RecipeDetailsFragment extends Fragment {

    public RecipeDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        RecipeDetailsFragmentArgs args = RecipeDetailsFragmentArgs.fromBundle(getArguments());

        Recipe model = args.getModel();

        Toolbar recipeDetailsPageToolbar = view.findViewById(R.id.toolbar);
        recipeDetailsPageToolbar.setTitle(model.getTitle());
        ImageView recipeDetailsPagePicture = view.findViewById(R.id.recipePicture);
        recipeDetailsPagePicture.setImageResource(model.getImgId());
        TextView recipeDetailsPageBody = view.findViewById(R.id.recipeBody);
        recipeDetailsPageBody.setText(HtmlCompat.fromHtml(getString(model.getRecipeBodyId()),0)); // html rich text
        recipeDetailsPageBody.setMovementMethod(LinkMovementMethod.getInstance());

        recipeDetailsPageToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                Navigation.findNavController(view).navigate(R.id.action_recipeDetails_to_recipesOverview);
            }
        });
    }
}