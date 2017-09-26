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

import edu.usf.cutr.transitfeeds.model.Results;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;

/**
 * Tests for GetFeedsRequest and SearchResponse
 */
public class GetFeedsTest extends TestCase {

    private static final String SIMPLE_SEARCH_ENDPOINT = "https://raw.githubusercontent.com/CUTR-at-USF/transitfeeds-client-library/master/src/test/resources/get-feeds.json";
    private static final String SEARCH_WITH_FOCUS_ENDPOINT = "https://raw.githubusercontent.com/CUTR-at-USF/transitfeeds-client-library/master/src/test/resources/search-with-focus.json";
    private static final String API_KEY = "dummyApiKey";
    private static final String TEXT = "subway";

    @Override
    protected void setUp() {
        // For tests, make sure that we can parse all known properties
        GetFeedsRequest.setFailOnUnknownProperties(true);
    }

    @Test
    public void testSimpleSearch() throws IOException {
        GetFeedsRequest request = new GetFeedsRequest.Builder(API_KEY)
                .setApiEndpoint(SIMPLE_SEARCH_ENDPOINT)
                .build();

        assertEquals("https://raw.githubusercontent.com/CUTR-at-USF/transitfeeds-client-library/master/src/test/resources/" +
                        "get-feeds.json",
                request.getUrl().toString());

        GetFeedsResponse response = request.call();

        assertEquals("OK", response.getStatus());
        assertEquals(Integer.valueOf(1506443652), response.getTimestamp());

        Results results = response.getResults();

        assertEquals(Integer.valueOf(858), results.getTotal());
        assertEquals(Integer.valueOf(10), results.getLimit());
        assertEquals(Integer.valueOf(1), results.getPage());

        // TODO - continue at numPages

//        Point p;
//        Feature[] f = response.getFeatures();
//
//        p = (Point) f[0].getGeometry();
//        assertEquals(35.798729, p.getCoordinates().getLatitude());
//        assertEquals(-117.872003, p.getCoordinates().getLongitude());
//        assertEquals("node:2357470967", (String) f[0].getProperties().get("id"));
//        assertEquals("venue", f[0].getProperties().get("layer"));
//        assertEquals("Subway", f[0].getProperties().get("name"));
//        assertEquals(0.6, f[0].getProperties().get("confidence"));
//        assertEquals("United States", f[0].getProperties().get("country"));
//        assertEquals("California", f[0].getProperties().get("region"));
//        assertEquals("Inyo County", f[0].getProperties().get("county"));
//        assertEquals("Subway, Pearsonville, CA, USA", f[0].getProperties().get("label"));
//
//        p = (Point) f[1].getGeometry();
//        assertEquals(-37.708951, p.getCoordinates().getLatitude());
//        assertEquals(144.961816, p.getCoordinates().getLongitude());
//        assertEquals("node:2321936555", (String) f[1].getProperties().get("id"));
//        assertEquals("venue", f[1].getProperties().get("layer"));
//        assertEquals("Subway", f[1].getProperties().get("name"));
//        assertEquals(0.6, f[1].getProperties().get("confidence"));
//        assertEquals("Australia", f[1].getProperties().get("country"));
//        assertEquals("Victoria", f[1].getProperties().get("region"));
//        assertEquals("Moreland (C)", f[1].getProperties().get("county"));
//        assertEquals("Fawkner", f[1].getProperties().get("localadmin"));
//        assertEquals("Fawkner", f[1].getProperties().get("locality"));
//        assertEquals("Fawkner", f[1].getProperties().get("neighbourhood"));
//        assertEquals("Subway, Fawkner, Victoria, Australia", f[1].getProperties().get("label"));
//
//        Float[] bbox = response.getBbox();
//        assertEquals(-122.33372F, bbox[0]);
//        assertEquals(-37.708951F, bbox[1]);
//        assertEquals(152.91174F, bbox[2]);
//        assertEquals(64.166014F, bbox[3]);
    }

    @Test
    public void testRequestParameters() throws IOException {
        // TODO - convert to GetFeeds

//        GetFeedsRequest request = new GetFeedsRequest.Builder(API_KEY, TEXT)
//                .setApiEndpoint(SEARCH_WITH_FOCUS_ENDPOINT)
//                .setFocusPoint(28.061062d, -82.4132d)
//                .setSources("osm")
//                .setSize(35)
//                .setBoundaryRect(27.959868, -82.515286, 28.131471, -82.367646)
//                .build();
//
//        assertEquals("https://raw.githubusercontent.com/CUTR-at-USF/transitfeeds-client-library/master/src/test/resources/" +
//                        "search-with-focus.json?text=subway&api_key=dummyApiKey&sources=osm&size=35" +
//                        "&focus.point.lat=28.061062&focus.point.lon=-82.4132" +
//                        "&boundary.rect.min_lat=27.959868&boundary.rect.min_lon=-82.515286" +
//                        "&boundary.rect.max_lat=28.131471&boundary.rect.max_lon=-82.367646",
//                request.getUrl().toString());
//
//        // Test space in text parameter
//        request = new GetFeedsRequest.Builder(API_KEY, "burger king")
//                .setApiEndpoint(SEARCH_WITH_FOCUS_ENDPOINT)
//                .setFocusPoint(28.061062d, -82.4132d)
//                .setSources("osm")
//                .setSize(35)
//                .setBoundaryRect(27.959868, -82.515286, 28.131471, -82.367646)
//                .build();
//
//        assertEquals("https://raw.githubusercontent.com/CUTR-at-USF/transitfeeds-client-library/master/src/test/resources/" +
//                        "search-with-focus.json?text=burger+king&api_key=dummyApiKey&sources=osm&size=35" +
//                        "&focus.point.lat=28.061062&focus.point.lon=-82.4132" +
//                        "&boundary.rect.min_lat=27.959868&boundary.rect.min_lon=-82.515286" +
//                        "&boundary.rect.max_lat=28.131471&boundary.rect.max_lon=-82.367646",
//                request.getUrl().toString());

    }
}
