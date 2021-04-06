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
package com.hms.codelabs.museum.ui.museumsearch

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.hms.codelabs.museum.R
import com.hms.codelabs.museum.SharedViewModel
import com.hms.codelabs.museum.databinding.FragmentMuseumSearchBinding
import com.hms.codelabs.museum.listeners.PaginationListener
import com.hms.codelabs.museum.models.Constant

class MuseumSearchFragment : Fragment() {
    private var viewModel: SharedViewModel? = null
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding: FragmentMuseumSearchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_museum_search, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = requireActivity()
        /*
         * Button and permission operations
         */
        val searchButton: MaterialButton = binding.root.findViewById(R.id.searchButton)
        searchButton.setOnClickListener { v: View? ->
            val permissionGranted = hasPermissions(requireContext(), *Constant.SEARCH_PERMISSIONS)
            if (!permissionGranted) {
                requestPermissions(Constant.SEARCH_PERMISSIONS, 10001)
            } else {
                viewModel!!.page.postValue(1)
                viewModel!!.museumList.value!!.clear()
                viewModel!!.searchNearbyMuseums()
            }
        }
        val recyclerView: RecyclerView = binding.root.findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        //Setting Layout manager for recyclerView
        recyclerView.layoutManager = linearLayoutManager
        val museumListAdapter = MuseumListAdapter(requireContext(), viewModel!!.museumList.value!!, viewModel!!.searchUtils)
        recyclerView.adapter = museumListAdapter
        /*
         * Pagination operation
         */
        recyclerView.addOnScrollListener(object : PaginationListener(linearLayoutManager) {
            override fun loadMoreItems() {
                viewModel!!.isLoading.postValue(true)
                viewModel!!.page.postValue(viewModel!!.page.value!! + 1)
                viewModel!!.searchNearbyMuseums()
            }

            override val isLastPage: Boolean
                get() = viewModel!!.page.value!! > 19
            override val isLoading: Boolean
                get() = viewModel!!.isLoading.value!!
        })
        /*
         * observe adapter changes
         */
        viewModel!!.museumList.observe(viewLifecycleOwner, { museumListAdapter.notifyDataSetChanged() })
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
        if (grantResults.isNotEmpty())
            for (permissionResult in grantResults) {
                if (permissionResult == PackageManager.PERMISSION_DENIED) {
                    permissionGranted = false
                    break
                }
            }
        if (permissionGranted) {
            viewModel!!.page.postValue(1)
            viewModel!!.museumList.value!!.clear()
            viewModel!!.searchNearbyMuseums()
        } else Toast.makeText(requireContext(), getString(R.string.permissions_not_granted), Toast.LENGTH_LONG).show()
    }

    companion object {
        /**
         * the function to check permissions
         * @param context
         * @param permissions
         * @return
         */
        fun hasPermissions(context: Context?, vararg permissions: String?): Boolean {
            context?.let {
                for (permission in permissions) {
                    if (ActivityCompat.checkSelfPermission(it, permission!!) != PackageManager.PERMISSION_GRANTED) {
                        return false
                    }
                }
                return true
            }
            return false
        }
    }
}