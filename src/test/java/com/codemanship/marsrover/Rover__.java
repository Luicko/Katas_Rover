package com.codemanship.marsrover;

import org.junit.Test;
import refactor.Obstacle;
import refactor.Rover;
import refactor.Rover.Position;

import static org.assertj.core.api.Assertions.assertThat;
import static refactor.Rover.Heading.*;
import static refactor.Rover.Order.*;

public class Rover__ {

    @Test
    public void could_be_initialized_with_new_constructors(){
        assertThat(new Rover(North, new Position(1,1)).position()).isEqualTo(new Position(1,1));
        assertThat(new Rover(North, 4, 4).position()).isEqualTo(new Position(4,4));
    }

    @Test
    public void could_be_initialized_using_legacy_constructor(){
        assertThat(new Rover("N",5,5).heading()).isEqualTo(North);
        assertThat(new Rover("North",3,-1).heading()).isEqualTo(North);
        assertThat(new Rover("North",0,2).position()).isEqualTo(new Position(0,2));
    }

    @Test
    public void could_turn_left(){
        Rover rover = new Rover(North, new Position(3,3));
        rover.go(Left);
        assertThat(rover.heading()).isEqualTo(West);
        assertThat(rover.position()).isEqualTo(new Position(3,3));
    }

    @Test
    public void could_turn_right() {
        Rover rover = new Rover(East, new Position(5, 1));
        rover.go(Right);
        assertThat(rover.heading()).isEqualTo(South);
        assertThat(rover.position()).isEqualTo(new Position(5,1));
    }

    @Test
    public void could_go_forward() {
        Rover rover = new Rover(South, new Position(-1, 1));
        rover.go(Forward);
        assertThat(rover.heading()).isEqualTo(South);
        assertThat(rover.position()).isEqualTo(new Position(-1,0));
    }

    @Test
    public void could_go_backward() {
        Rover rover = new Rover(West, new Position(-4, 4));
        rover.go(Backward);
        assertThat(rover.heading()).isEqualTo(West);
        assertThat(rover.position()).isEqualTo(new Position(-3,4));
    }

    @Test
    public void could_execute_many_orders() {
        Rover rover = new Rover(West, new Position(3, 1));
        rover.go(Backward, Left, Forward, Right, Forward);
        assertThat(rover.heading()).isEqualTo(West);
        assertThat(rover.position()).isEqualTo(new Position(3,0));
    }

    @Test
    public void could_execute_legacy_instructions() {
        Rover rover = new Rover(West, new Position(3, 1));
        rover.go("BLFRF");
        assertThat(rover.heading()).isEqualTo(West);
        assertThat(rover.position()).isEqualTo(new Position(3,0));
    }

    @Test
    public void could_ignore_legacy_instructions() {
        Rover rover = new Rover(West, new Position(3, 1));
        rover.go("BL*FRF");
        assertThat(rover.heading()).isEqualTo(West);
        assertThat(rover.position()).isEqualTo(new Position(3,0));
    }

    @Test
    public void could_not_move_forward_if_there_is_an_obstacle(){
        Rover rover = new Rover(North, new Position(1, 1));
        Obstacle obstacle = new Obstacle(new Position(1,2));
        rover.addObstacle(obstacle);
        rover.go(Forward);
        assertThat(rover.heading()).isEqualTo(North);
        assertThat(rover.position()).isEqualTo(new Position(1,1));
    }

    @Test
    public void could_not_move_backward_if_there_is_an_obstacle(){
        Rover rover = new Rover(South, new Position(-1, 3));
        Obstacle obstacle = new Obstacle(new Position(0,3));
        rover.addObstacle(obstacle);
        rover.go(Right, Backward);
        assertThat(rover.heading()).isEqualTo(West);
        assertThat(rover.position()).isEqualTo(new Position(-1,3));
    }
}