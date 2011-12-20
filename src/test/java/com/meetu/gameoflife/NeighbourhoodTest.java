package com.meetu.gameoflife;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class NeighbourhoodTest {

    @Test
    public void neighbourhood_should_contain_8_neighbours() throws Exception {
        Universe grid = mock(Grid.class);
        Neighbourhood neighbourhood = new Neighbourhood(grid, new Coordinate(0, 0));
        assertEquals(8, neighbourhood.neighboursCount());
    }

    @Test
    public void neighbourhood_should_check_8_coordinates_when_asked_for_live_count() throws Exception {
        Universe grid = mock(Grid.class);
        Neighbourhood neighbourhood = new Neighbourhood(grid, new Coordinate(0, 0));
        neighbourhood.liveCount();
        verify(grid, times(8)).isAlive(isA(Coordinate.class));
    }


}