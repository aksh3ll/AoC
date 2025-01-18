package fr.akshell.aoc.utils;

import fr.akshell.aoc.pojo.Graph;
import fr.akshell.aoc.pojo.INode;
import fr.akshell.aoc.pojo.Maze;
import fr.akshell.aoc.pojo.Vector2D;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class GraphUtilsTest {
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
        Graph<Vector2D> graph = GraphUtils.convertMazeToGraph(maze.grid());
        Vector2D startNode = maze.find('S');
        Set<String> result = GraphUtils.dfs(graph, startNode.id());
        System.out.println(result);
    }

    @Test void givenInvalidNode_whenDfs_thenExceptionThrown() {
        Maze maze = MazeUtils.convertInputToMaze(INPUT_DEMO_1);
        Graph<Vector2D> graph = GraphUtils.convertMazeToGraph(maze.grid());
        assertThatThrownBy(() -> GraphUtils.dfs(graph, "unknown"))
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

        Graph<Vector2D> graph = GraphUtils.convertMazeToGraph(maze);

        // Print the graph
        for (INode<Vector2D> node : graph.nodes().values()) {
            System.out.print("Node (" + node.id() + ") -> ");
            for (INode<Vector2D> neighbor : node.neighbors()) {
                System.out.print("(" + neighbor.id() + ") ");
            }
            System.out.println();
        }
    }

}