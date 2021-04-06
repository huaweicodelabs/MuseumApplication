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
package com.hms.codelabs.museum.models;


public class Exhibit {
    private int exhibitID;

    private String exhibitName;

    private String exhibitDescription;

    private int exhibitImage;

    private String museumName;

    private String category;

    public Exhibit(int exhibitID, String exhibitName, String exhibitDescription, int exhibitImage, String museumName, String category) {
        this.exhibitID = exhibitID;
        this.exhibitName = exhibitName;
        this. exhibitDescription = exhibitDescription;
        this.exhibitImage = exhibitImage;
        this.museumName = museumName;
        this.category = category;
    }
    public Exhibit(){}

    public void setExhibitID(Integer exhibitID) {
        this.exhibitID = exhibitID;
    }

    public Integer getExhibitID() {
        return exhibitID;
    }

    public void setExhibitName(String exhibitName) {
        this.exhibitName = exhibitName;
    }

    public String getExhibitName() {
        return exhibitName;
    }

    public void setExhibitDescription(String exhibitDescription) {
        this.exhibitDescription = exhibitDescription;
    }

    public String getExhibitDescription() {
        return exhibitDescription;
    }

    public void setExhibitImage(int exhibitImage) {
        this.exhibitImage = exhibitImage;
    }

    public int getExhibitImage() {
        return exhibitImage;
    }

    public void setMuseumName(String museumName) {
        this.museumName = museumName;
    }

    public String getMuseumName() {
        return museumName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

}
