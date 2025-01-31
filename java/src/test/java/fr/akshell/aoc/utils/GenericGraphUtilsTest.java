package fr.akshell.aoc.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import fr.akshell.aoc.base.BaseTest;
import fr.akshell.aoc.pojo.GenericGraph;
import fr.akshell.aoc.pojo.INode;
import fr.akshell.aoc.pojo.Maze;
import fr.akshell.aoc.pojo.Vector2D;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Set;

@SpringBootTest
class GenericGraphUtilsTest {
    protected static Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);
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

    @Test
    public void givenInput_whenConvert_thenGraphReturned() {
        Maze maze = MazeUtils.convertInputToMaze(INPUT_DEMO_1);
        GenericGraph<Vector2D> genericGraph = GraphUtils.convertMazeToGraph(maze.grid());
        Vector2D startNode = maze.find('S');
        assertThat(startNode).isNotNull();
        Set<String> result = GraphUtils.dfs(genericGraph, startNode.id());
        LOGGER.info("result: {}", String.join("\n", result));
    }

    @Test void givenInvalidNode_whenDfs_thenExceptionThrown() {
        Maze maze = MazeUtils.convertInputToMaze(INPUT_DEMO_1);
        GenericGraph<Vector2D> genericGraph = GraphUtils.convertMazeToGraph(maze.grid());
        assertThatThrownBy(() -> GraphUtils.dfs(genericGraph, "unknown"))
                .isInstanceOf(AssertionError.class)
                .hasMessage("Node not found in graph");
    }

    @Test
    void givenMaze_whenConvert_thenGraphIsReturned() {
        char[][] maze = {
                {'S', '.', '.', '#', 'E'},
                {'.', '#', '.', '#', '.'},
                {'.', '#', '.', '.', '.'},
                {'.', '.', '#', '#', '.'},
                {'.', '.', '.', '.', '.'}
        };

        GenericGraph<Vector2D> genericGraph = GraphUtils.convertMazeToGraph(maze);

        // Print the graph
        for (INode<Vector2D> node : genericGraph.nodes().values()) {
            LOGGER.info("Node ({}) -> ", node.id());
            for (INode<Vector2D> neighbor : node.neighbors()) {
                LOGGER.info("({}) ", neighbor.id());
            }
            LOGGER.info("\n");
        }
    }

}