/*
 * Copyright (C) 2017 University of South Florida
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.usf.cutr.transitfeeds.model;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "pid",
        "t",
        "n",
        "lat",
        "lng"
})
public class Location {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("pid")
    private Integer parentId;
    @JsonProperty("t")
    private String titleWithRegion;
    @JsonProperty("n")
    private String title;
    @JsonProperty("lat")
    private Double lat;
    @JsonProperty("lng")
    private Double lng;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("pid")
    public Integer getParentId() {
        return parentId;
    }

    @JsonProperty("pid")
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @JsonProperty("t")
    public String getTitleWithRegion() {
        return titleWithRegion;
    }

    @JsonProperty("t")
    public void setTitleWithRegion(String titleWithRegion) {
        this.titleWithRegion = titleWithRegion;
    }

    @JsonProperty("n")
    public String getTitle() {
        return title;
    }

    @JsonProperty("n")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("lat")
    public Double getLat() {
        return lat;
    }

    @JsonProperty("lat")
    public void setLat(Double lat) {
        this.lat = lat;
    }

    @JsonProperty("lng")
    public Double getLng() {
        return lng;
    }

    @JsonProperty("lng")
    public void setLng(Double lng) {
        this.lng = lng;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", titleWithRegion='" + titleWithRegion + '\'' +
                ", title='" + title + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
