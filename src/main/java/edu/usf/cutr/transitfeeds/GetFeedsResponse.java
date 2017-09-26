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
package edu.usf.cutr.transitfeeds;

import com.fasterxml.jackson.annotation.*;
import edu.usf.cutr.transitfeeds.model.Results;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "status",
        "timestamp",
        "results"
})
/**
 * Encapsulates a response from the TransitFeeds.com GetFeeds API
 * - http://transitfeeds.com/api/swagger/#!/default/getFeeds
 */
public class GetFeedsResponse {

    @JsonProperty("status")
    private String status;
    @JsonProperty("ts")
    private Integer timestamp;
    @JsonProperty("msg")
    private String errorDescription;
    @JsonProperty("results")
    private Results results;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Indicates the success status of this request. The following values are possible:
     * OK - Request was valid.
     * EMPTYKEY - Request was missing API key.
     * MISSINGINPUT - A required request parameter was missing.
     * INVALIDINPUT - A request parameter was invalid.
     *
     * @return the success status of this request
     */
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Return the timestamp (in number of seconds since the epoch (January 1 1970 00:00:00 GMT).
     *
     * @return the timestamp (in number of seconds since the epoch (January 1 1970 00:00:00 GMT).
     */
    @JsonProperty("ts")
    public Integer getTimestamp() {
        return timestamp;
    }

    @JsonProperty("ts")
    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Returns a description of the error, if the status value was not OK.
     *
     * @return description of the error, if the status value was not OK.
     */
    @JsonProperty("msg")
    public String getErrorDescription() {
        return errorDescription;
    }

    @JsonProperty("msg")
    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    /**
     * Returns the requested data for a valid request
     *
     * @return the requested data for a valid request
     */
    @JsonProperty("results")
    public Results getResults() {
        return results;
    }

    @JsonProperty("results")
    public void setResults(Results results) {
        this.results = results;
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
        return "GetFeedsResponse{" +
                "status='" + status + '\'' +
                ", timestamp=" + timestamp +
                ", errorDescription='" + errorDescription + '\'' +
                ", results=" + results +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
