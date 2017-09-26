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
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "total",
        "limit",
        "page",
        "numPages",
        "feeds"
})
public class Results {

    @JsonProperty("input")
    private String input;
    @JsonProperty("total")
    private Integer total;
    @JsonProperty("limit")
    private Integer limit;
    @JsonProperty("page")
    private Integer page;
    @JsonProperty("numPages")
    private Integer numPages;
    @JsonProperty("feeds")
    private List<Feed> feeds = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * If the status value is MISSINGINPUT or INVALIDINPUT, this field contains the name of the offending field.
     *
     * @return the name of the offending field, if the status value is MISSINGINPUT or INVALIDINPUT
     */
    @JsonProperty("input")
    public String getInput() {
        return input;
    }

    @JsonProperty("input")
    public void setInput(String input) {
        this.input = input;
    }

    /**
     * Returns the total number of feeds found based on the request input. Note that this number may be larger than the number of feeds returned in feeds, based on the values for limit and page.
     *
     * @return the total number of feeds found based on the request input. Note that this number may be larger than the number of feeds returned in feeds, based on the values for limit and page.
     */
    @JsonProperty("total")
    public Integer getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * Returns the maximum number of feeds that can be returned in this response. If the final page is being requested then this number may be larger than the number of feeds returned in feeds. integerDefault:10
     *
     * @return the maximum number of feeds that can be returned in this response. If the final page is being requested then this number may be larger than the number of feeds returned in feeds. integerDefault:10
     */
    @JsonProperty("limit")
    public Integer getLimit() {
        return limit;
    }

    @JsonProperty("limit")
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    /**
     * Returns the page number being requested, based on the maximum number than can be returned from in limit.
     *
     * @return the page number being requested, based on the maximum number than can be returned from in limit.
     */
    @JsonProperty("page")
    public Integer getPage() {
        return page;
    }

    @JsonProperty("page")
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * Returns the number of pages available, based on the total and limit.
     *
     * @return the number of pages available, based on the total and limit.
     */
    @JsonProperty("numPages")
    public Integer getNumPages() {
        return numPages;
    }

    @JsonProperty("numPages")
    public void setNumPages(Integer numPages) {
        this.numPages = numPages;
    }

    /**
     * Returns an array of zero or more feeds
     *
     * @return an array of zero or more feeds
     */
    @JsonProperty("feeds")
    public List<Feed> getFeeds() {
        return feeds;
    }

    @JsonProperty("feeds")
    public void setFeeds(List<Feed> feeds) {
        this.feeds = feeds;
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
        return "Results{" +
                "input='" + input + '\'' +
                ", total=" + total +
                ", limit=" + limit +
                ", page=" + page +
                ", numPages=" + numPages +
                ", feeds=" + feeds +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
