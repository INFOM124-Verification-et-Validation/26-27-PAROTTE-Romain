package nl.tudelft.jpacman.level;

class PlayerCollisionsTest extends CollisionMapTest {

    @Override
    CollisionMap createCollisionMap() {
        return new PlayerCollisions();
    }
}
