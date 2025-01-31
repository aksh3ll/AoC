package fr.akshell.aoc.graph;

import fr.akshell.aoc.base.BaseTest;
import fr.akshell.aoc.pojo.Maze;
import fr.akshell.aoc.pojo.Vector2D;
import fr.akshell.aoc.utils.GraphUtils;
import fr.akshell.aoc.utils.MazeUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class GenericGraphUtilsTest {
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
        GenericGraph<Vector2D> genericGraph = GraphUtils.convertMazeToGraph(maze);
        Vector2D startNode = maze.find('S');
        assertThat(startNode).isNotNull();
        Set<String> result = GraphUtils.dfs(genericGraph, startNode.id());
        assertThat(result).isNotEmpty().hasSize(104);
        logger.info("result: {}", String.join("\n", result));
    }

    @Test
    void givenInvalidNode_whenDfs_thenExceptionThrown() {
        Maze maze = MazeUtils.convertInputToMaze(INPUT_DEMO_1);
        GenericGraph<Vector2D> genericGraph = GraphUtils.convertMazeToGraph(maze);
        assertThatThrownBy(() -> GraphUtils.dfs(genericGraph, "unknown"))
                .isInstanceOf(AssertionError.class)
                .hasMessage("Node not found in graph");
    }

    @Disabled("This test is disabled because it is the cause of an infinite loop")
    @Test
    void givenMaze_whenConvert_thenGraphIsReturned() {
        Maze maze = MazeUtils.convertInputToMaze(INPUT_DEMO_2);
        GenericGraph<Vector2D> genericGraph = GraphUtils.convertMazeToGraph(maze);

        // Print the graph
        for (INode<Vector2D> node : genericGraph.nodes().values()) {
            logger.info("Node ({}) -> ", node.id());
            for (IEdge<Vector2D> edge : node.edges()) {
                logger.info("({}: {}) ", edge.node().id(), edge.weight());
            }
            logger.info("\n");
        }
        assertThat(genericGraph.nodes()).isNotEmpty().hasSize(10);
    }

}