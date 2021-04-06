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
package com.hms.codelabs.museum.ui.virtualguide

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hms.codelabs.museum.R
import com.hms.codelabs.museum.SharedViewModel
import com.hms.codelabs.museum.databinding.FragmentVirtualGuideBinding
import com.hms.codelabs.museum.models.Constant

class VirtualGuideFragment : Fragment() {
    private var viewModel: SharedViewModel? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding: FragmentVirtualGuideBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_virtual_guide, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = requireActivity()
        /*
         * Start Scanning Function
         */
        val permissionGranted = hasPermissions(requireContext(), *Constant.GUIDE_PERMISSIONS)
        if (!permissionGranted) {
            requestPermissions(Constant.GUIDE_PERMISSIONS, 10001)
        } else {
            viewModel!!.guideUtils.startScanning(requireActivity())
        }
        return binding.root
    }

    /**
     * Request Permission Result
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        var permissionGranted = true
        if (grantResults.isNotEmpty()) for (permissionResult in grantResults) {
            if (permissionResult == PackageManager.PERMISSION_DENIED) {
                permissionGranted = false
                break
            }
        }
        if (permissionGranted) {
            viewModel!!.guideUtils.startScanning(requireActivity())
        } else Toast.makeText(requireContext(), getString(R.string.permissions_not_granted), Toast.LENGTH_LONG).show()
    }

    companion object {
        /**
         * Check Permission Function
         * @param context
         * @param permissions
         * @return
         */
        fun hasPermissions(context: Context?, vararg permissions: String?): Boolean {
            context?.let {
                for (permission in permissions) {
                    if (ActivityCompat.checkSelfPermission(context, permission!!) != PackageManager.PERMISSION_GRANTED) {
                        return false
                    }
                }
                return true
            }
            return false
        }
    }
}