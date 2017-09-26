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
        "i",
        "d"
})
public class Urls {

    @JsonProperty("i")
    private String infoUrl;
    @JsonProperty("d")
    private String downloadUrl;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * If available, this contains a URL with additional information about registering or downloading the feed from the provider.
     *
     * @return a URL with additional information about registering or downloading the feed from the provider
     */
    @JsonProperty("i")
    public String getInfoUrl() {
        return infoUrl;
    }

    @JsonProperty("i")
    public void setInfoUrl(String infoUrl) {
        this.infoUrl = infoUrl;
    }

    /**
     * If available, this contains a URL to download the feed directly from the provider (as opposed to downloading from TransitFeeds.com)
     *
     * @return a URL to download the feed directly from the provider (as opposed to downloading from TransitFeeds.com)
     */
    @JsonProperty("d")
    public String getDownloadUrl() {
        return downloadUrl;
    }

    @JsonProperty("d")
    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
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
        return "Urls{" +
                "infoUrl='" + infoUrl + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
