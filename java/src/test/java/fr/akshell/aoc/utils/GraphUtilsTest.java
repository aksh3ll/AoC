package fr.akshell.aoc.utils;

import fr.akshell.aoc.base.BaseTest;
import fr.akshell.aoc.pojo.Graph;
import fr.akshell.aoc.pojo.Maze;
import fr.akshell.aoc.pojo.Vector2D;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class GraphUtilsTest {
    protected static Logger logger = LoggerFactory.getLogger(BaseTest.class);
    private static final String INPUT_DEMO_1 = """
###############
#.......#....E#
#.#.###.#.###.#
#.....#.#...#.#
#.###.#####.#.#
#.#.#.......#.#
#.#.#####.###.#
#...........#.#
###.#.#####.#.#
#...#.....#.#.#
#.#.#.###.#.#.#
#.....#...#.#.#
#.###.#.#.#.#.#
#S..#.....#...#
###############
""";

    private static final String INPUT_DEMO_2 = """
S..#E
.#.#.
.#...
..##.
.....
""";

    @Test
    void givenInput_whenConvert_thenGraphReturned() {
        Maze maze = Maze.of(INPUT_DEMO_1);
        Graph graph = GraphUtils.convertMazeToGraph(maze);
        Vector2D startNode = maze.find('S');
        assertThat(startNode).isNotNull();
        Set<String> result = GraphUtils.dfs(graph, startNode.id());
        assertThat(result).isNotEmpty().hasSize(104);
        logger.info("result: {}", String.join("\n", result));
    }

    @Test
    void givenInvalidNode_whenDfs_thenExceptionThrown() {
        Maze maze = Maze.of(INPUT_DEMO_1);
        Graph graph = GraphUtils.convertMazeToGraph(maze);
        assertThatThrownBy(() -> GraphUtils.dfs(graph, "unknown"))
                .isInstanceOf(AssertionError.class)
                .hasMessage("Node not found in graph");
    }

    @Test
    void givenMaze_whenConvert_thenGraphIsReturned() {
        Maze maze = Maze.of(INPUT_DEMO_2);
        Graph graph = GraphUtils.convertMazeToGraph(maze);

        // Print the graph
        for (String vertice : graph.getVertices()) {
            var edges = graph
                    .getNeighbors(vertice)
                    .stream()
                    .map(edge -> edge.vertex() + " = " + edge.weight())
                    .collect(Collectors.joining(", "));
            logger.info("Node {} -> {}", vertice, edges);
        }
        assertThat(graph.getVertices()).isNotEmpty().hasSize(19);
    }

    @Test
    void givenGraph_whenSearchingPathWithHeldKarp_thenShortestPathReturned() {
        Maze maze = Maze.of(INPUT_DEMO_2);
        Graph graph = GraphUtils.convertMazeToGraph(maze);
        int distance = GraphUtils.heldKarp(graph);
        assertThat(distance).isEqualTo(1073741824);
    }

    @Test
    void givenGraph_whenSearchingPathWithHeldKarpMax_thenLongestPathReturned() {
        Maze maze = Maze.of(INPUT_DEMO_2);
        Graph graph = GraphUtils.convertMazeToGraph(maze);
        int distance = GraphUtils.heldKarpMax(graph);
        assertThat(distance).isEqualTo(-1073741808);
    }
}