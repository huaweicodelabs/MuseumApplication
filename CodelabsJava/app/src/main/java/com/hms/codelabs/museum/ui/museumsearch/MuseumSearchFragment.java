/*
 * Copyright 2020. Huawei Technologies Co., Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hms.codelabs.museum.ui.museumsearch;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.hms.codelabs.museum.R;
import com.hms.codelabs.museum.SharedViewModel;
import com.hms.codelabs.museum.databinding.FragmentMuseumSearchBinding;
import com.hms.codelabs.museum.listeners.PaginationListener;
import com.hms.codelabs.museum.models.Constant;

public class MuseumSearchFragment extends Fragment {

    private SharedViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        FragmentMuseumSearchBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_museum_search, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(requireActivity());

        /*
          Button and permission operations
         */
        MaterialButton searchButton = binding.getRoot().findViewById(R.id.searchButton);
        searchButton.setOnClickListener(v -> {
            boolean permissionGranted = hasPermissions(requireContext(), Constant.SEARCH_PERMISSIONS);
            if (!permissionGranted) {
                requestPermissions(Constant.SEARCH_PERMISSIONS, 10001);
            } else {
                viewModel.page.postValue(1);
                viewModel.museumList.getValue().clear();
                viewModel.searchNearbyMuseums();
            }
        });



        RecyclerView recyclerView = binding.getRoot().findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        //Setting Layout manager for recyclerView
        recyclerView.setLayoutManager(linearLayoutManager);
        MuseumListAdapter museumListAdapter = new MuseumListAdapter(requireContext(), viewModel.museumList.getValue(), viewModel.searchUtils);
        recyclerView.setAdapter(museumListAdapter);

        /*
          Pagination operation
         */
        recyclerView.addOnScrollListener(new PaginationListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                viewModel.isLoading.postValue(true);
                viewModel.page.postValue(viewModel.page.getValue()+1);
                viewModel.searchNearbyMuseums();
            }
            @Override
            public boolean isLastPage() {
                return viewModel.page.getValue() > 19;
            }

            @Override
            public boolean isLoading() {
                return viewModel.isLoading.getValue();
            }
        });

        /*
          observe adapter changes
         */
        viewModel.museumList.observe(getViewLifecycleOwner(), sites -> museumListAdapter.notifyDataSetChanged());


        return binding.getRoot();
    }

    /**
     * the function to check permissions
     * @param context
     * @param permissions
     * @return
     */
    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Request Permission Result
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean permissionGranted = true;
        if (grantResults.length > 0)
            for (int permissionResult : grantResults) {
                if (permissionResult == PackageManager.PERMISSION_DENIED) {
                    permissionGranted = false;
                    break;
                }
            }
        if (permissionGranted) {
            viewModel.page.postValue(1);
            viewModel.museumList.getValue().clear();
            viewModel.searchNearbyMuseums();
        }
        else
            Toast.makeText(requireContext(), getString(R.string.permissions_not_granted), Toast.LENGTH_LONG).show();
    }

}