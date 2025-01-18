package fr.akshell.aoc.pojo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


@SpringBootTest
public class GenericGraphTest {

    @Test
    public void givenNodes_whenCreateGraph_thenGraphReturned() {
        var pt1 = new Vector2D(0, 0);
        var pt2 = new Vector2D(0, 1);
        var pt3 = new Vector2D(1, 0);
        var pt4 = new Vector2D(1, 1);
        GenericGraph<Vector2D> genericGraph = GenericGraph.of(List.of(pt1, pt2, pt3, pt4));
        genericGraph.addEdge(pt1, pt2);
        genericGraph.addEdge(pt1, pt3);
        genericGraph.addEdge(pt2, pt4);
        genericGraph.addEdge(pt3, pt4);
        genericGraph.addEdge(pt1, pt4, true);
        assertThat(genericGraph)
                .isNotNull()
                .extracting(GenericGraph::nodeCount, GenericGraph::edgeCount)
                .containsExactly(4, 6);
        assertThat(genericGraph.getNode(pt1.id()))
                .isNotNull()
                .extracting("content")
                .isEqualTo(pt1);
    }

    @Test
    void givenUnknownNode_whenAddingEdge_thenExceptionThrown() {
        var pt1 = new Vector2D(0, 0);
        var pt2 = new Vector2D(0, 1);
        GenericGraph<Vector2D> genericGraph = GenericGraph.of(List.of(pt1));
        assertThatThrownBy(() -> genericGraph.addEdge(pt1, pt2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Node not found in graph");
        assertThatThrownBy(() -> genericGraph.addEdge(pt2, pt1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Node not found in graph");
    }

    @Test
    void givenNullVector_whenAddingEdge_thenExceptionThrown() {
        GenericGraph<Vector2D> genericGraph = GenericGraph.of();
        Vector2D pt1 = null;
        var pt2 = new Vector2D(0, 1);
        assertThatThrownBy(() -> genericGraph.addEdge(pt1, pt2))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("c1 is marked non-null but is null");
        assertThatThrownBy(() -> genericGraph.addEdge(pt2, pt1))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("c2 is marked non-null but is null");
        assertThatThrownBy(() -> genericGraph.addEdge(pt1, pt2, true))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("c1 is marked non-null but is null");
        assertThatThrownBy(() -> genericGraph.addEdge(pt2, pt1, true))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("c2 is marked non-null but is null");
    }
}
