package experiment.puzzle;

import java.util.Set;

/**
 * @author : liulei
 **/
public class PuzzleNode<P, M> extends SequentialPuzzleSolver.Node<P, M> {

    PuzzleNode(P pos, M move, SequentialPuzzleSolver.Node<P, M> prev) {
        super(pos, move, prev);
    }
}
