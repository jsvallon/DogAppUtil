package com.example.dogapputil.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.dogapputil.R;
import com.example.dogapputil.viewModel.ListDogViewModel;
import java.util.ArrayList;
import java.util.List;


public class ListDogFragment extends Fragment {


    private DogListAdapter  dogListAdapter = new DogListAdapter(new ArrayList<>(), false);
    private ListDogViewModel viewModel;

    RecyclerView listDogs;
    SwipeRefreshLayout refresh;
    TextView listError;
    ProgressBar progressView;
    View view;

    public ListDogFragment() {
        // Required empty public constructor
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(ListDogViewModel.class);
        viewModel.updateDogList();

        listDogs.setLayoutManager(new LinearLayoutManager(getContext()));
        listDogs.setAdapter(dogListAdapter);
        listDogs.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL));


       setHasOptionsMenu(true);
        updateUI();
        updateList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_list_dog, container, false);
        listDogs = view.findViewById(R.id.listDogs);
        listError = view.findViewById(R.id.listError);
        progressView = view.findViewById(R.id.progressView);
        refresh = view.findViewById(R.id.refresh);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search : {

            }
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateUI() {
        refresh.setOnRefreshListener(() -> {
            listError.setVisibility(View.GONE);
            listDogs.setVisibility(View.GONE);
            progressView.setVisibility(View.VISIBLE);

            viewModel.updateDogList();
            refresh.setRefreshing(false);
        });
    }

    private void updateList() {
        viewModel.dogs.observe(this, updateDogsList -> {
            if(updateDogsList !=null && updateDogsList instanceof List) {
                listDogs.setVisibility(View.VISIBLE);
                dogListAdapter.updateDogList(updateDogsList);
            }
        });


        viewModel.error.observe(this, isError-> {
            if(isError !=null && isError instanceof Boolean) {
                listError.setVisibility(isError? View.VISIBLE : View.GONE);
            }
        });

        viewModel.loading.observe(this, isLoading-> {
            if(isLoading !=null && isLoading instanceof Boolean) {
                progressView.setVisibility(isLoading ? View.VISIBLE : View.GONE);

                if(isLoading) {
                    listDogs.setVisibility(View.GONE);
                    listError.setVisibility(View.GONE);
                }
            }
        });
    }
}