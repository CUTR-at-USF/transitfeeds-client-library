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
        "type",
        "t",
        "l",
        "u",
        "latest"
})
public class Feed {

    @JsonProperty("id")
    private String id;
    @JsonProperty("ty")
    private String type;
    @JsonProperty("t")
    private String title;
    @JsonProperty("l")
    private Location location;
    @JsonProperty("u")
    private Urls urls;
    @JsonProperty("latest")
    private Latest latest;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Returns the unique ID for this feed. This is constructed using the ID of the feed's provider and an internal ID. This ID can be used in other calls, such as /getFeedVersions or /getLatestFeedVersion.
     *
     * @return unique ID for this feed. This is constructed using the ID of the feed's provider and an internal ID. This ID can be used in other calls, such as /getFeedVersions or /getLatestFeedVersion.
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the type of feed (such as GTFS or GTFS-realtime). = ['gtfs', 'gtfsrealtime']stringEnum:"gtfs", "gtfsrealtime"
     *
     * @return the type of feed (such as GTFS or GTFS-realtime). = ['gtfs', 'gtfsrealtime']stringEnum:"gtfs", "gtfsrealtime"
     */
    @JsonProperty("ty")
    public String getType() {
        return type;
    }

    @JsonProperty("ty")
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns the title of the feed as it appears on TransitFeeds.com
     *
     * @return the title of the feed as it appears on TransitFeeds.com
     */
    @JsonProperty("t")
    public String getTitle() {
        return title;
    }

    @JsonProperty("t")
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the location to which this feed is assigned. The location ID can subsequently be used in calls to /getFeeds.
     *
     * @return the location to which this feed is assigned. The location ID can subsequently be used in calls to /getFeeds.
     */
    @JsonProperty("l")
    public Location getLocation() {
        return location;
    }

    @JsonProperty("l")
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Returns URLs with additional information about this feed (Optional)
     *
     * @return URLs with additional information about this feed (Optional)
     */
    @JsonProperty("u")
    public Urls getUrls() {
        return urls;
    }

    @JsonProperty("u")
    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    /**
     * If available, returns contains information about the latest version of this feed. This can help to determine if you need to call /getLatestFeedVersion to retrieve a newer version of a feed
     *
     * @return information about the latest version of this feed. This can help to determine if you need to call /getLatestFeedVersion to retrieve a newer version of a feed (Optional)
     */
    @JsonProperty("latest")
    public Latest getLatest() {
        return latest;
    }

    @JsonProperty("latest")
    public void setLatest(Latest latest) {
        this.latest = latest;
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
        return "Feed{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", location=" + location +
                ", urls=" + urls +
                ", latest=" + latest +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
