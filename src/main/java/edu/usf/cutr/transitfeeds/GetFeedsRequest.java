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
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
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
     * A temporary method to test requests
     * TODO - remove this method and create demo project
     *
     * @param args main program arguments
     */
    public static void main(String[] args) {
        try {
            String apiKey = "76edc18d-54d4-4132-9f53-e8e25be976e7";
            GetFeedsResponse response = new GetFeedsRequest.Builder(apiKey).build().call();
            System.out.println(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        private String mSources;
        private Double mFocusPointLat;
        private Double mFocusPointLon;
        private Double mBoundaryMinLat;
        private Double mBoundaryMinLon;
        private Double mBoundaryMaxLat;
        private Double mBoundaryMaxLon;
        private Integer mSize;

        /**
         * A Builder for making a request to the Pelias Search API
         *
         * @param apiKey the API key to be used in the request
         */
        public Builder(String apiKey) {
            mApiKey = apiKey;
        }

        /**
         * Sets the API endpoint that will be used for the API call.  By default https://search.mapzen.com/v1/search
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
         * Builds the GetFeedsRequest using the specified parameters
         *
         * @return the GetFeedsRequest using the specified parameters
         */
        public GetFeedsRequest build() {
            StringBuilder builder = new StringBuilder();
            builder.append(mApiEndPoint);
            builder.append("?text=");
            builder.append(mLocation);
            builder.append("&key=");
            builder.append(mApiKey);

            if (mSources != null) {
                builder.append("&sources=");
                builder.append(mSources);
            }

            if (mSize != null) {
                builder.append("&size=");
                builder.append(mSize);
            }

            if (mFocusPointLat != null && mFocusPointLon != null) {
                builder.append("&focus.point.lat=");
                builder.append(mFocusPointLat);
                builder.append("&focus.point.lon=");
                builder.append(mFocusPointLon);
            }

            if (mBoundaryMinLat != null) {
                builder.append("&boundary.rect.min_lat=");
                builder.append(mBoundaryMinLat);
                builder.append("&boundary.rect.min_lon=");
                builder.append(mBoundaryMinLon);
                builder.append("&boundary.rect.max_lat=");
                builder.append(mBoundaryMaxLat);
                builder.append("&boundary.rect.max_lon=");
                builder.append(mBoundaryMaxLon);
            }

            return new GetFeedsRequest(builder.toString());
        }
    }
}
