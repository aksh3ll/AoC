package fr.akshell.aoc.pojo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


@SpringBootTest
public class GraphTest {

    @Test
    public void givenNodes_whenCreateGraph_thenGraphReturned() {
        var pt1 = new Vector2D(0, 0);
        var pt2 = new Vector2D(0, 1);
        var pt3 = new Vector2D(1, 0);
        var pt4 = new Vector2D(1, 1);
        Graph<Vector2D> graph = Graph.of(List.of(pt1, pt2, pt3, pt4));
        graph.addEdge(pt1, pt2);
        graph.addEdge(pt1, pt3);
        graph.addEdge(pt2, pt4);
        graph.addEdge(pt3, pt4);
        graph.addEdge(pt1, pt4, true);
        assertThat(graph)
                .isNotNull()
                .extracting(Graph::nodeCount, Graph::edgeCount)
                .containsExactly(4, 6);
        assertThat(graph.getNode(pt1.id()))
                .isNotNull()
                .extracting("content")
                .isEqualTo(pt1);
    }

    @Test
    void givenUnknownNode_whenAddingEdge_thenExceptionThrown() {
        var pt1 = new Vector2D(0, 0);
        var pt2 = new Vector2D(0, 1);
        Graph<Vector2D> graph = Graph.of(List.of(pt1));
        assertThatThrownBy(() -> graph.addEdge(pt1, pt2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Node not found in graph");
        assertThatThrownBy(() -> graph.addEdge(pt2, pt1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Node not found in graph");
    }

    @Test
    void givenNullVector_whenAddingEdge_thenExceptionThrown() {
        Graph<Vector2D> graph = Graph.of();
        Vector2D pt1 = null;
        var pt2 = new Vector2D(0, 1);
        assertThatThrownBy(() -> graph.addEdge(pt1, pt2))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("c1 is marked non-null but is null");
        assertThatThrownBy(() -> graph.addEdge(pt2, pt1))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("c2 is marked non-null but is null");
        assertThatThrownBy(() -> graph.addEdge(pt1, pt2, true))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("c1 is marked non-null but is null");
        assertThatThrownBy(() -> graph.addEdge(pt2, pt1, true))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("c2 is marked non-null but is null");
    }
}
