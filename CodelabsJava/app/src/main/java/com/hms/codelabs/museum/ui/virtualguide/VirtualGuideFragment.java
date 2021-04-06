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
package com.hms.codelabs.museum.ui.virtualguide;

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

import com.hms.codelabs.museum.R;
import com.hms.codelabs.museum.SharedViewModel;
import com.hms.codelabs.museum.databinding.FragmentVirtualGuideBinding;
import com.hms.codelabs.museum.models.Constant;

public class VirtualGuideFragment extends Fragment {

    private SharedViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FragmentVirtualGuideBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_virtual_guide, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(requireActivity());


        /*
          Start Scanning Function
         */
        boolean permissionGranted = hasPermissions(requireContext(), Constant.GUIDE_PERMISSIONS);
        if (!permissionGranted) {
            requestPermissions(Constant.GUIDE_PERMISSIONS, 10001);
        } else {
            viewModel.guideUtils.startScanning(requireActivity());
        }

        return binding.getRoot();
    }

    /**
     * Check Permission Function
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
            viewModel.guideUtils.startScanning(requireActivity());
        }
        else
            Toast.makeText(requireContext(), getString(R.string.permissions_not_granted), Toast.LENGTH_LONG).show();
    }
}