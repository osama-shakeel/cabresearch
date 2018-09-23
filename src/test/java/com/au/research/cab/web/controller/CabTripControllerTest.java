package com.au.research.cab.web.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.au.research.cab.CabResearchApplication;
import com.au.research.cab.model.CabTripCount;
import com.au.research.cab.service.CabTripServiceIF;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CabResearchApplication.class)
@WebAppConfiguration
public class CabTripControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private CabTripController cabTripController;

    @Mock
    private CabTripServiceIF cabTripService;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(cabTripController, "cabTripService", cabTripService);
        mockMvc = MockMvcBuilders.standaloneSetup(cabTripController).build();
    }

    /**
     * Test getting cab trip count of a single cab
     * for a certain pickup date.
     * CabTripCount instance JSON is expected to be returned.
     */
    @Test
    public void testGetCabTripCount() throws Exception {
        // Prepare Test Data
        String medallion = "D7D598CD99978BD012A87A76A7C891B7";
        //5455D5FF2BD94D10B304A15D4B7F2735
        String pickupDate = "20131201";
        LocalDate localDate = LocalDate.from(formatter.parse(pickupDate));
        long tripCount = 3L;
        CabTripCount cabTripCount = new CabTripCount(medallion, pickupDate, tripCount);

        // Mock behaviour
        Mockito.when(cabTripService.getCabDayTripCount(medallion, localDate)).thenReturn(cabTripCount);

        // Execute method to test
        mockMvc.perform(
                get("/api/cabTrips/{medallion}/count", medallion)
                        .param("pickupDate", pickupDate))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(cabTripCount)));
    }

    /**
     * Test getting latest cab trip count of a single cab
     * for a certain pickup date.
     * CabTripCount instance JSON is expected to be returned.
     */
    @Test
    public void testGetLatestCabTripCount() throws Exception {
        // Prepare Test Data
        String medallion = "D7D598CD99978BD012A87A76A7C891B7";
        //5455D5FF2BD94D10B304A15D4B7F2735
        String pickupDate = "20131201";
        LocalDate localDate = LocalDate.from(formatter.parse(pickupDate));
        long tripCount = 3L;
        CabTripCount cabTripCount = new CabTripCount(medallion, pickupDate, tripCount);

        // Mock behaviour
        Mockito.when(cabTripService.getLatestCabDayTripCount(medallion, localDate)).thenReturn(cabTripCount);

        // Execute method to test
        mockMvc.perform(
                get("/api/cabTrips/{medallion}/count/latest", medallion)
                        .param("pickupDate", pickupDate))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(cabTripCount)));
    }

    /**
     * Test getting cab trip counts of a 2 cabs
     * for a certain pickup date.
     * CabTripCount instances JSON is expected to be returned.
     */
    @Test
    public void testGetCabTripsCounts() throws Exception {
        // Prepare Test Data
        String pickupDate = "20131201";
        String cabA = "D7D598CD99978BD012A87A76A7C891B7";
        String cabB = "5455D5FF2BD94D10B304A15D4B7F2735";
        LocalDate localDate = LocalDate.from(formatter.parse(pickupDate));
        List<CabTripCount> cabTripCounts = Arrays.asList(
                new CabTripCount(cabA, pickupDate, 1L),
                new CabTripCount(cabB, pickupDate, 3L));

        // Mock behaviour
        Mockito.when(cabTripService.getCabDayTripCount(cabA, localDate))
                .thenReturn(new CabTripCount(cabA, pickupDate, 1L));
        Mockito.when(cabTripService.getCabDayTripCount(cabB, localDate))
                .thenReturn(new CabTripCount(cabB, pickupDate, 3L));

        // Execute method to test
        mockMvc.perform(
                get("/api/cabTrips/count")
                        .param("pickupDate", pickupDate)
                        .param("medallion", cabA, cabB))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(cabTripCounts)));
    }

    /**
     * Test getting latest cab trip counts of a 2 cabs
     * for a certain pickup date.
     * CabTripCount instances JSON is expected to be returned.
     */
    @Test
    public void testGetLatestCabTripsCounts() throws Exception {
        // Prepare Test Data
        String pickupDate = "20131201";
        String cabA = "D7D598CD99978BD012A87A76A7C891B7";
        String cabB = "5455D5FF2BD94D10B304A15D4B7F2735";
        LocalDate localDate = LocalDate.from(formatter.parse(pickupDate));
        List<CabTripCount> cabTripCounts = Arrays.asList(
                new CabTripCount(cabA, pickupDate, 1L),
                new CabTripCount(cabB, pickupDate, 3L));

        // Mock behaviour
        Mockito.when(cabTripService.getLatestCabDayTripCount(cabA, localDate))
                .thenReturn(new CabTripCount(cabA, pickupDate, 1L));
        Mockito.when(cabTripService.getLatestCabDayTripCount(cabB, localDate))
                .thenReturn(new CabTripCount(cabB, pickupDate, 3L));

        // Execute method to test
        mockMvc.perform(
                get("/api/cabTrips/count/latest")
                        .param("pickupDate", pickupDate)
                        .param("medallion", cabA, cabB))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(cabTripCounts)));
    }

    /**
     * Tests deleting all cache contents of Cab trip counts.
     * Http response 204 is expected.
     * @throws Exception
     */
    @Test
    public void testDeleteCabTripCountCacheContents() throws Exception {
        // Execute method to test
        mockMvc.perform(
                delete("/api/cabTrips/cache/contents"))
                .andExpect(status().isNoContent());
    }
}
