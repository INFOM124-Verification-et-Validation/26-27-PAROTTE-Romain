package nl.tudelft.jpacman.level;

class DefaultPlayerInteractionMapTest extends CollisionMapTest {

    @Override
    CollisionMap createCollisionMap() {
        return new DefaultPlayerInteractionMap();
    }
}
