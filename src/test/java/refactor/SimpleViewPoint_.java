package refactor;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static refactor.SimpleViewPoint.Heading.*;

public class SimpleViewPoint_ {

    @Test
    public void could_be_initialized_with_new_constructors(){
        assertThat(new SimpleViewPoint(NORTH, new SimpleViewPoint.Position(1,1)).getPosition()).isEqualTo(new SimpleViewPoint.Position(1,1));
        assertThat(new SimpleViewPoint(NORTH, 4, 4).getPosition()).isEqualTo(new SimpleViewPoint.Position(4,4));
    }

    @Test
    public void could_be_initialized_using_legacy_constructor(){
        assertThat(new SimpleViewPoint("N",5,5).getHeading()).isEqualTo(NORTH);
        assertThat(new SimpleViewPoint("North",3,-1).getHeading()).isEqualTo(NORTH);
        assertThat(new SimpleViewPoint("North",0,2).getPosition()).isEqualTo(new SimpleViewPoint.Position(0,2));
    }

    @Test
    public void could_turn_left(){
        // Given
        SimpleViewPoint simpleViewPoint = new SimpleViewPoint(NORTH, new SimpleViewPoint.Position(3, 3));

        // When
        simpleViewPoint.turnLeft();

        // Then
        assertThat(simpleViewPoint.getHeading()).isEqualTo(WEST);
        assertThat(simpleViewPoint.getPosition()).isEqualTo(new SimpleViewPoint.Position(3,3));
    }

    @Test
    public void could_turn_right() {
        // Given
        SimpleViewPoint simpleViewPoint = new SimpleViewPoint(EAST, new SimpleViewPoint.Position(5, 1));

        // When
        simpleViewPoint.turnRight();

        //Then
        assertThat(simpleViewPoint.getHeading()).isEqualTo(SOUTH);
        assertThat(simpleViewPoint.getPosition()).isEqualTo(new SimpleViewPoint.Position(5,1));
    }

    @Test
    public void could_go_forward() {
        // Given
        SimpleViewPoint simpleViewPoint = new SimpleViewPoint(SOUTH, new SimpleViewPoint.Position(-1, 1));

        // When
        simpleViewPoint.forward();

        // Then
        assertThat(simpleViewPoint.getHeading()).isEqualTo(SOUTH);
        assertThat(simpleViewPoint.getPosition()).isEqualTo(new SimpleViewPoint.Position(-1,0));
    }

    @Test
    public void could_not_move_forward_if_there_is_an_obstacle(){
        // Given
        SimpleViewPoint viewPoint = new SimpleViewPoint(NORTH, new SimpleViewPoint.Position(1, 1));
        Obstacle obstacle = new Obstacle(new SimpleViewPoint.Position(1,2));
        viewPoint.addObstacle(obstacle);

        // When
        viewPoint.forward();

        // Then
        assertThat(viewPoint.getHeading()).isEqualTo(NORTH);
        assertThat(viewPoint.getPosition()).isEqualTo(new SimpleViewPoint.Position(1,1));
    }

    @Test
    public void could_go_backward() {
        // Given
        SimpleViewPoint simpleViewPoint = new SimpleViewPoint(WEST, new SimpleViewPoint.Position(-4, 4));

        // When
        simpleViewPoint.backward();

        // Then
        assertThat(simpleViewPoint.getHeading()).isEqualTo(WEST);
        assertThat(simpleViewPoint.getPosition()).isEqualTo(new SimpleViewPoint.Position(-3,4));
    }

    @Test
    public void could_not_move_backward_if_there_is_an_obstacle(){
        // Given
        SimpleViewPoint viewPoint = new SimpleViewPoint(WEST, new SimpleViewPoint.Position(-1, 3));
        Obstacle obstacle = new Obstacle(new SimpleViewPoint.Position(0,3));
        viewPoint.addObstacle(obstacle);

        // When
        viewPoint.backward();

        // Then
        assertThat(viewPoint.getHeading()).isEqualTo(WEST);
        assertThat(viewPoint.getPosition()).isEqualTo(new SimpleViewPoint.Position(-1,3));
    }
}
