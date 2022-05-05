package P3;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FriendshipGraphTest {
    final FriendshipGraph graph = new FriendshipGraph();
    Person rachel = new Person("Rachel");
    Person ross = new Person("Ross");
    Person ben = new Person("Ben");
    Person kramer = new Person("Kramer");

    @Test
    public void addVertexTest() throws Exception{
        graph.addVertex(rachel);
        assertEquals(1, FriendshipGraph.flag);
        graph.addVertex(ross);
        assertEquals(1, FriendshipGraph.flag);
        graph.addVertex(ben);
        assertEquals(1, FriendshipGraph.flag);
        graph.addVertex(kramer);
        assertEquals(1, FriendshipGraph.flag);
        graph.addVertex(kramer);
        assertEquals(0, FriendshipGraph.flag);
    }

    @Test
    public void addEdgeTest() {
        graph.addVertex(rachel);
        graph.addVertex(ross);
        graph.addVertex(ben);
        graph.addVertex(kramer);
        graph.addEdge(rachel, ross);
        assertEquals(1, FriendshipGraph.flag);
        graph.addEdge(ross, rachel);
        assertEquals(1, FriendshipGraph.flag);
        graph.addEdge(ross, ben);
        assertEquals(1, FriendshipGraph.flag);
        graph.addEdge(ben, ross);
        assertEquals(1, FriendshipGraph.flag);
    }

    @Test
    public void getDistanceTest() {
        graph.addVertex(rachel);
        graph.addVertex(ross);
        graph.addVertex(ben);
        graph.addVertex(kramer);
        graph.addEdge(rachel, ross);
        graph.addEdge(ross, rachel);
        graph.addEdge(ross, ben);
        graph.addEdge(ben, ross);
        assertEquals(1,graph.getDistance(rachel, ross));
        assertEquals(2,graph.getDistance(rachel, ben));
        assertEquals(-1,graph.getDistance(rachel, kramer));
    }
}