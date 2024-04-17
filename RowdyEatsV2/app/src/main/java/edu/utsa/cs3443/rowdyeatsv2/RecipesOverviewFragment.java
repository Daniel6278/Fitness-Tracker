package edu.utsa.cs3443.rowdyeatsv2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.utsa.cs3443.rowdyeatsv2.adapters.RecipesRecyclerViewAdapter;
import edu.utsa.cs3443.rowdyeatsv2.Model.Recipe;

public class RecipesOverviewFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recipes_overview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recipesRV);

        // created new array list..
        ArrayList<Recipe> recyclerDataArrayList = Recipe.getModels();

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
                        RecipesOverviewFragmentDirections.actionRecipesOverviewToRecipeDetails(model.getTitle(),model.getImgId(),model.getRecipeBodyId());

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
