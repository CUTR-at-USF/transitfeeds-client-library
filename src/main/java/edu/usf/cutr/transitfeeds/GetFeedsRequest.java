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

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Encapsulates a request to the Mapzen Pelias Search API - https://mapzen.com/documentation/search/search/
 */
public class GetFeedsRequest {

    private static ObjectMapper mMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
    private static ObjectReader mReader = mMapper.readerFor(GetFeedsResponse.class);

    private URL mUrl;

    private GetFeedsRequest(String url) {
        try {
            mUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the Jackson value for "fail on unknown properties" for all GetFeedsRequest instances.  Note that while this
     * method is threadsafe, it does not guarantee that existing instances of GetFeedsRequest using the ObjectReader won't
     * be negatively impacted / interrupted.  Default is false.
     *
     * @param value true if Jackson should fail on unknown properties, false of it should not (default is false)
     */
    public synchronized static void setFailOnUnknownProperties(boolean value) {
        mMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, value);
        mReader = mMapper.readerFor(GetFeedsResponse.class);
    }

    /**
     * Returns the URL that will be used in the API request
     *
     * @return the URL that will be used in the API request
     */
    URL getUrl() {
        return mUrl;
    }

    /**
     * Makes the request to the Pelias Search API, and returns a SearchResponse parsed from the returned JSON
     *
     * @return a SearchResponse parsed from the returned JSON
     * @throws IOException if there is an exception making the request or reading the response from the server
     */
    public GetFeedsResponse call() throws IOException {
        return mReader.readValue(mUrl.openStream());
    }

    public static class Builder {
        private String mApiEndPoint = "http://api.transitfeeds.com/v1/getFeeds";
        private String mApiKey;
        private String mLocation;
        private String mDescendants;
        private Integer mPage;
        private Integer mLimit;
        private String mType;

        /**
         * A Builder for making a request to the TransitFeeds.com GetFeeds API
         *
         * @param apiKey the API key to be used in the request
         */
        public Builder(String apiKey) {
            mApiKey = apiKey;
        }

        /**
         * Sets the API endpoint that will be used for the API call.  By default http://api.transitfeeds.com/v1/getFeeds
         * will be used.
         *
         * @param apiEndpoint the API endpoint to be used in the API call
         * @return this same Builder so Builder calls can be chained
         */
        public Builder setApiEndpoint(String apiEndpoint) {
            mApiEndPoint = apiEndpoint;
            return this;
        }

        /**
         * Sets the unique ID of a location. If specified, feeds will only be returned that belong to this location (and perhaps sub-locations too, depending on the descendants value). You can use the /getLocations API endpoint to determine location IDs.
         *
         * @param location the unique ID of a location. If specified, feeds will only be returned that belong to this location (and perhaps sub-locations too, depending on the descendants value). You can use the /getLocations API endpoint to determine location IDs.
         * @return this same Builder so Builder calls can be chained
         */
        public Builder setLocation(String location) {
            mLocation = location;
            return this;
        }

        /**
         * If a location is specified in location, this flag can be used to control if returned feeds must be assigned directly to the location, or if feeds belonging to sub-locations can also be returned. If 0, then feeds must be assigned directly to the specified location.
         * <p>
         * Default is 1 if parameter isn't provided.
         *
         * @param descendants if a location is specified in location, this flag can be used to control if returned feeds must be assigned directly to the location, or if feeds belonging to sub-locations can also be returned. If 0, then feeds must be assigned directly to the specified location.
         * @return this same Builder so Builder calls can be chained
         */
        public Builder setDescendants(String descendants) {
            mDescendants = descendants;
            return this;
        }

        /**
         * Sets the page number of results to return. For example, if you specify a page of 2 with a limit of 10, then results 11-20 are returned. The number of pages available is included in the response.
         *
         * @param page the page number of results to return. For example, if you specify a page of 2 with a limit of 10, then results 11-20 are returned. The number of pages available is included in the response.
         * @return this same Builder so Builder calls can be chained
         */
        public Builder setPage(int page) {
            mPage = page;
            return this;
        }

        /**
         * Sets the maximum number of results to return.
         *
         * @param limit the maximum number of results to return.
         * @return this same Builder so Builder calls can be chained
         */
        public Builder setLimit(int limit) {
            mLimit = limit;
            return this;
        }

        /**
         * Sets the type of feeds to return. If unspecified, feeds of all types are returned.
         * <p>
         * Known valid values are:
         * gtfs
         * gtfsrealtime
         *
         * @param type the type of feeds to return
         * @return this same Builder so Builder calls can be chained
         */
        public Builder setType(String type) {
            mType = type;
            return this;
        }

        /**
         * Builds the GetFeedsRequest using the specified parameters
         *
         * @return the GetFeedsRequest using the specified parameters
         */
        public GetFeedsRequest build() {
            StringBuilder builder = new StringBuilder();
            builder.append(mApiEndPoint);
            builder.append("?key=");
            builder.append(mApiKey);

            if (mLocation != null) {
                builder.append("&location=");
                builder.append(mLocation);
            }

            if (mDescendants != null) {
                builder.append("&descendants=");
                builder.append(mDescendants);
            }

            if (mPage != null) {
                builder.append("&page=");
                builder.append(mPage);
            }

            if (mLimit != null) {
                builder.append("&limit=");
                builder.append(mLimit);
            }

            if (mType != null) {
                builder.append("&type=");
                builder.append(mType);
            }

            return new GetFeedsRequest(builder.toString());
        }
    }
}
