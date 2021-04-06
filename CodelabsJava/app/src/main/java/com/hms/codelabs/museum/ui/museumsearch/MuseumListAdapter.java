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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hms.codelabs.museum.models.Constant;
import com.hms.codelabs.museum.utils.SearchUtils;
import com.huawei.hms.site.api.model.Site;

import java.text.DecimalFormat;
import java.util.ArrayList;
import com.hms.codelabs.museum.R;

/**
 * Museum list adapter
 */
public class MuseumListAdapter extends RecyclerView.Adapter<MuseumListAdapter.ViewHolder> {

    private final LayoutInflater layoutInflater;
    private final ArrayList<Site> museumList;
    private final SearchUtils searchUtils;

    private static final DecimalFormat df2 = new DecimalFormat("#.##");

    public MuseumListAdapter(Context context , ArrayList<Site> list, SearchUtils searchUtils){
        this.layoutInflater =  LayoutInflater.from(context);
        this.searchUtils = searchUtils;

        list.sort((site1, site2) -> (int) (site1.getDistance() - site2.getDistance()));
        museumList = list;
    }
    @NonNull
    @Override
    public MuseumListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.museum_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.museum_name.setText(museumList.get(position).name);
        holder.museum_description.setText(museumList.get(position).getFormatAddress());
        holder.distance.setText(getDistance(museumList.get(position)));

        holder.button.setOnClickListener(v -> searchUtils.addBarrierToAwarenessKit(museumList.get(position), Constant.AWARENESS_BARRIER_RADIUS, Constant.AWARENESS_BARRIER_DURATION));
    }
    @Override
    public int getItemCount() {
        return museumList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        final TextView museum_name;
        final TextView museum_description;
        final TextView distance;
        final ImageView button;

        public ViewHolder(View view) {
            super(view);

            museum_name = view.findViewById(R.id.museumName_Row);
            museum_description  = view.findViewById(R.id.museumDescription_Row);
            distance = view.findViewById(R.id.distance_Row);
            button = view.findViewById(R.id.navigate_button);
        }
    }
    public String getDistance(Site data) {
        double distance = data.getDistance();
        if (distance > 1000)
            return (df2.format(distance / 1000) + " km");
        else
            return (df2.format(distance) + " m");
    }
}
