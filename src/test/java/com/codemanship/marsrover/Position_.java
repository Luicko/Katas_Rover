package com.codemanship.marsrover;

import org.junit.Test;
import refactor.SimpleViewPoint.Position;

import static org.junit.Assert.assertEquals;
import static refactor.SimpleViewPoint.Heading.*;

public class Position_ {

    @Test
    public void should_calculate_forward_position() {
        assertEquals(new Position(-1,0), new Position(0,0).forward(NORTH).forward(WEST).forward(SOUTH));
    }
    @Test
    public void should_calculate_backward_position(){
        assertEquals(new Position(-1,-1), new Position(0,0).backward(NORTH).backward(EAST));
    }
}