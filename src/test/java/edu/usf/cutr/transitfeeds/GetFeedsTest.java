/*
* Copyright (C) 2016 University of South Florida, Sean J. Barbeau (sjbarbeau@gmail.com)
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

import edu.usf.cutr.transitfeeds.model.*;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Tests for GetFeedsRequest and SearchResponse
 */
public class GetFeedsTest extends TestCase {

    private static final String GET_FEEDS_ENDPOINT = "https://github.com/CUTR-at-USF/transitfeeds-client-library/raw/master/src/test/resources/get-feeds.json";
    private static final String GET_FEEDS_ENDPOINT_REALTIME = "https://github.com/CUTR-at-USF/transitfeeds-client-library/raw/master/src/test/resources/get-feeds-realtime.json";
    private static final String API_KEY = "dummyApiKey";

    @Override
    protected void setUp() {
        // For tests, make sure that we can parse all known properties
        GetFeedsRequest.setFailOnUnknownProperties(true);
    }

    @Test
    public void testGetFeeds() throws IOException {
        GetFeedsRequest request = new GetFeedsRequest.Builder(API_KEY)
                .setApiEndpoint(GET_FEEDS_ENDPOINT)
                .build();

        assertEquals("https://github.com/CUTR-at-USF/transitfeeds-client-library/raw/master/src/test/resources/" +
                        "get-feeds.json?key=dummyApiKey",
                request.getUrl().toString());

        GetFeedsResponse response = request.call();

        assertEquals("OK", response.getStatus());
        assertEquals(Integer.valueOf(1506443652), response.getTimestamp());

        Results results = response.getResults();

        assertEquals(Integer.valueOf(858), results.getTotal());
        assertEquals(Integer.valueOf(10), results.getLimit());
        assertEquals(Integer.valueOf(1), results.getPage());
        assertEquals(Integer.valueOf(86), results.getNumPages());

        List<Feed> feeds = results.getFeeds();
        Feed feed = feeds.get(0);

        assertEquals("karlsruher-verkehrsverbundes/896", feed.getId());
        assertEquals("gtfs", feed.getType());
        assertEquals("KVV GTFS", feed.getTitle());

        Location location = feed.getLocation();

        assertEquals(Integer.valueOf(622), location.getId());
        assertEquals(Integer.valueOf(168), location.getParentId());
        assertEquals("Karlsruhe", location.getTitle());
        assertEquals("Karlsruhe, Germany", location.getTitleWithRegion());
        assertEquals(49.00689d, location.getLat());
        assertEquals(8.403653d, location.getLng());

        Urls urls = feed.getUrls();
        assertEquals("https://www.kvv.de/fahrplanauskunft/fahrplanauskunft-efa/opendata.html", urls.getInfoUrl());
        assertEquals("http://213.144.24.66/GTFS/google_transit.zip", urls.getDownloadUrl());

        Latest l = feed.getLatest();
        assertEquals(Integer.valueOf(1506430166), l.getTimestamp());
    }

    @Test
    public void testRequestParameters() throws IOException {
        GetFeedsRequest request = new GetFeedsRequest.Builder(API_KEY)
                .setApiEndpoint(GET_FEEDS_ENDPOINT)
                .setType("gtfs")
                .setDescendants("1")
                .setLimit(10)
                .setLocation("1")
                .setPage(1)
                .build();

        assertEquals("https://github.com/CUTR-at-USF/transitfeeds-client-library/raw/master/src/test/resources/" +
                        "get-feeds.json" +
                        "?key=dummyApiKey" +
                        "&location=1" +
                        "&descendants=1" +
                        "&page=1" +
                        "&limit=10" +
                        "&type=gtfs",
                request.getUrl().toString());
    }
}
