package nl.tudelft.jpacman.npc.ghost;

import java.util.*;

import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.sprite.Sprite;


/**
 * <p>
 * An implementation of the classic Pac-Man ghost Shadow.
 * </p>
 * <p>
 * Nickname: Blinky. As his name implies, Shadow is usually a constant shadow on
 * Pac-Man's tail. When he's not patrolling the top-right corner of the maze,
 * Shadow tries to find the quickest route to Pac-Man's position. Despite the
 * fact that Pinky's real name is Speedy, Shadow is actually the fastest of the
 * ghosts because of when there are only a few pellets left, Blinky drastically
 * speeds up, which can make him quite deadly. In the original Japanese version,
 * his name is Oikake/Akabei.
 * </p>
 * <p>
 * <b>AI:</b> When the ghosts are not patrolling in their home corners (Blinky:
 * top-right, Pinky: top-left, Inky: bottom-right, Clyde: bottom-left), Blinky
 * will attempt to shorten the distance between Pac-Man and himself. If he has
 * to choose between shortening the horizontal or vertical distance, he will
 * choose to shorten whichever is greatest. For example, if Pac-Man is four grid
 * spaces to the left, and seven grid spaces above Blinky, he'll try to move up
 * towards Pac-Man before he moves to the left.
 * </p>
 * <p>
 * Source: http://strategywiki.org/wiki/Pac-Man/Getting_Started
 * </p>
 *
 * @author Jeroen Roosen 
 *
 */
public class Blinky extends Ghost {
    public enum State {
        CHASE,
        CORNER
    }
    private void updateState(){
        phase ++;
        if( (phase%12)<8){
            currentState= State.CHASE;
            return;
        }
        currentState = State.CORNER;
    }

    private long phase=0;
    private State currentState = State.CHASE;
    public State getCurrentState(){return currentState;}

    private Square cornerTarget;
    public Square getCornerTarget(){return cornerTarget;}
    private boolean setTarget=true;
    /**
     * The variation in intervals, this makes the ghosts look more dynamic and
     * less predictable.
     */
    private static final int INTERVAL_VARIATION = 50;

    /**
     * The base movement interval.
     */
    private static final int MOVE_INTERVAL = 250;

    /**
     * Creates a new "Blinky", a.k.a. "Shadow".
     *
     * @param spriteMap
     *            The sprites for this ghost.
     */
    // TODO Blinky should speed up when there are a few pellets left, but he
    // has no way to find out how many there are.
    public Blinky(Map<Direction, Sprite> spriteMap) {
        super(spriteMap, MOVE_INTERVAL, INTERVAL_VARIATION);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * When the ghosts are not patrolling in their home corners (Blinky:
     * top-right, Pinky: top-left, Inky: bottom-right, Clyde: bottom-left),
     * Blinky will attempt to shorten the distance between Pac-Man and himself.
     * If he has to choose between shortening the horizontal or vertical
     * distance, he will choose to shorten whichever is greatest. For example,
     * if Pac-Man is four grid spaces to the left, and seven grid spaces above
     * Blinky, he'll try to move up towards Pac-Man before he moves to the left.
     * </p>
     */
    @Override
    public Optional<Direction> nextAiMove() {
        if(setTarget){setCornerTarget(); setTarget=false;}
        assert hasSquare();

        Square target = null;
        switch (currentState){
            case CHASE:
                target = chaseLogic();
                break;
            case CORNER:
                target= cornerLogic();
                break;
            default:
                System.out.println("Undefined behaviour for "+currentState+" state for Blinky");
                target =chaseLogic();

        }
        //cycle to next phase
        updateState();
        if (target == null) {
            return Optional.empty();
        }
        List<Direction> path = Navigation.shortestPath(getSquare(), target, this);
        if (path != null && !path.isEmpty()) {
            return Optional.ofNullable(path.get(0));
        }
        return Optional.empty();
    }

    private Square cornerLogic(){
        if (getSquare()== cornerTarget){
            return chaseLogic();
        }
        assert cornerTarget != null;
        return cornerTarget;
    }

    private Square chaseLogic(){
        Unit move =Navigation.findNearest(Player.class, getSquare());
        if (move ==null){
            return null;
        }
        assert move.hasSquare();
        return move.getSquare();
    }
    private void setCornerTarget(){
        List<cornerTargetSearchNode> toDo = new ArrayList<>();
        Set<Square> visited = new HashSet<>();

        toDo.add(new cornerTargetSearchNode(getSquare(),0,0));
        cornerTargetSearchNode currentCorner = toDo.get(0);
        while (!toDo.isEmpty()) {
            cornerTargetSearchNode square = toDo.remove(0);
            if (currentCorner.x <= square.x && currentCorner.y <= square.y){
                currentCorner = square;
            }
            visited.add(square.square);
            for (Direction direction : Direction.values()) {
                cornerTargetSearchNode newTarget=null;
                if(square.square.getSquareAt(direction).isAccessibleTo(this)){
                switch (direction){
                    case NORTH -> newTarget= new cornerTargetSearchNode(square.square.getSquareAt(direction),square.x,square.y+1);
                    case EAST -> newTarget= new cornerTargetSearchNode(square.square.getSquareAt(direction),square.x+1,square.y);
                    case WEST -> newTarget= new cornerTargetSearchNode(square.square.getSquareAt(direction),square.x-1,square.y);
                    case SOUTH -> newTarget= new cornerTargetSearchNode(square.square.getSquareAt(direction),square.x,square.y-1);
                }}
                if (newTarget!= null && !visited.contains(newTarget.square) && !toDo.contains(newTarget)) {
                    toDo.add((newTarget));
                }
            }

        }
            cornerTarget = currentCorner.square;
    }
    private class cornerTargetSearchNode {
        public Square square;
        public int x,y;
        cornerTargetSearchNode(Square square, int x, int y){
            this.square = square;
            this.x = x;
            this.y = y;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof cornerTargetSearchNode other)) return false;
            return Objects.equals(this.square, other.square);
        }

        @Override
        public int hashCode() {
            return Objects.hash(square);
        }
    }
}
