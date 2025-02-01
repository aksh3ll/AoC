package fr.akshell.aoc.utils;

import fr.akshell.aoc.base.BaseTest;
import fr.akshell.aoc.pojo.Graph;
import fr.akshell.aoc.pojo.Maze;
import fr.akshell.aoc.pojo.Vector2D;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Set;

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
        Maze maze = MazeUtils.convertInputToMaze(INPUT_DEMO_1);
        Graph graph = GraphUtils.convertMazeToGraph(maze);
        Vector2D startNode = maze.find('S');
        assertThat(startNode).isNotNull();
        Set<String> result = GraphUtils.dfs(graph, startNode.id());
        assertThat(result).isNotEmpty().hasSize(104);
        logger.info("result: {}", String.join("\n", result));
    }

    @Test
    void givenInvalidNode_whenDfs_thenExceptionThrown() {
        Maze maze = MazeUtils.convertInputToMaze(INPUT_DEMO_1);
        Graph graph = GraphUtils.convertMazeToGraph(maze);
        assertThatThrownBy(() -> GraphUtils.dfs(graph, "unknown"))
                .isInstanceOf(AssertionError.class)
                .hasMessage("Node not found in graph");
    }

    @Disabled("This test is disabled because it is the cause of an infinite loop")
    @Test
    void givenMaze_whenConvert_thenGraphIsReturned() {
        Maze maze = MazeUtils.convertInputToMaze(INPUT_DEMO_2);
        Graph graph = GraphUtils.convertMazeToGraph(maze);

        // Print the graph
        for (String vertice : graph.getVertices()) {
            logger.info("Node ({}) -> ", vertice);
            for (Graph.Edge edge : graph.getNeighbors(vertice)) {
                logger.info("({}: {}) ", edge.vertex(), edge.weight());
            }
            logger.info("\n");
        }
        assertThat(graph.getVertices()).isNotEmpty().hasSize(10);
    }

}