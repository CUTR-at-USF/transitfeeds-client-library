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
        "ts"
})
public class Latest {

    @JsonProperty("ts")
    private Integer timestamp;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Returns the timestamp of the latest feed version (in number of seconds since the epoch (January 1 1970 00:00:00 GMT)
     *
     * @return the timestamp of the latest feed version (in number of seconds since the epoch (January 1 1970 00:00:00 GMT)
     */
    @JsonProperty("ts")
    public Integer getTimestamp() {
        return timestamp;
    }

    @JsonProperty("ts")
    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
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
        return "Latest{" +
                "timestamp=" + timestamp +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
