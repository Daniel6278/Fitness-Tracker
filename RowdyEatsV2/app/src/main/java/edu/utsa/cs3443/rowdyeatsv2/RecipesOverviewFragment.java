package edu.utsa.cs3443.rowdyeatsv2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.utsa.cs3443.rowdyeatsv2.adapters.RecipesRecyclerViewAdapter;
import edu.utsa.cs3443.rowdyeatsv2.model.Recipe;

public class RecipesOverviewFragment extends Fragment {

    private void initRecipesModels(ArrayList<Recipe> list) {
        // add data to array list
        list.add(new Recipe("Sushi",R.drawable.recipe_sushi, R.string.recipe_sushi));
        list.add(new Recipe("Breakfast Sandwich",R.drawable.recipe_bkfst_sandwich, R.string.recipe_bkfst_sandwich));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recipes_overview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recipesRV);

        // created new array list..
        ArrayList<Recipe> recyclerDataArrayList = new ArrayList<>();

        initRecipesModels(recyclerDataArrayList);

        // added data from arraylist to adapter class.
        RecipesRecyclerViewAdapter adapter = new RecipesRecyclerViewAdapter(recyclerDataArrayList, view.getContext());

        // onclick
        adapter.setOnClickListener(new RecipesRecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(int position, Recipe model) {
                /*
                Bundle result = new Bundle();
                result.putString("heading", model.getTitle());
                result.putInt("body", model.getRecipeBodyId());
                getParentFragmentManager().setFragmentResult("fillRecipeDetails", result);
                */

                edu.utsa.cs3443.rowdyeatsv2.RecipesOverviewFragmentDirections.ActionRecipesOverviewToRecipeDetails action =
                        RecipesOverviewFragmentDirections.actionRecipesOverviewToRecipeDetails(model.getTitle(),model.getImgid(),model.getRecipeBodyId());

                Navigation.findNavController(view).navigate(action);
            }
        });

        // setting grid layout manager to implement grid view.
        // in this method '2' represents number of columns to be displayed in grid view.
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(),2);

        // at last set adapter to recycler view.
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
