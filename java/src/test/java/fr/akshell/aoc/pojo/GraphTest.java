package fr.akshell.aoc.pojo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GraphTest {

    private Graph createDummyGraph() {
        Graph graph = new Graph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B", 9);
        return graph;
    }

    @Test
    void givenEmptyGraph_whenAddVertex_thenVertexAdded() {
        Graph graph = createDummyGraph();
        assertThat(graph.getVertices()).containsExactlyInAnyOrder("A", "B");
    }

    @Test
    void givenGraph_whenAddEdge_thenEdgeAdded() {
        Graph graph = createDummyGraph();
        assertThat(graph.getEdge("A", "B")).isNotNull().extracting(Graph.Edge::weight).isEqualTo(9);
    }

    @Test
    void givenGraph_whenQueryingNeighbors_thenNeighborsAreReturned() {
        Graph graph = createDummyGraph();
        graph.addEdge("A", "C", 5);
        assertThat(graph.getNeighbors("A"))
                .hasSize(2)
                .extracting(Graph.Edge::vertex)
                .containsExactlyInAnyOrder("B", "C");
    }
}