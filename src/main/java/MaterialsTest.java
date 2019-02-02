import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.SimplexSolver;

import java.util.List;

import static java.util.Arrays.asList;
import static org.apache.commons.lang3.ArrayUtils.toObject;
import static org.apache.commons.math3.optim.linear.Relationship.GEQ;
import static org.apache.commons.math3.optim.linear.Relationship.LEQ;
import static org.apache.commons.math3.optim.nonlinear.scalar.GoalType.MAXIMIZE;

public class MaterialsTest {
    public static void main(String[] args) {

        var oFunction = new LinearObjectiveFunction(new double[]{1000, 1800}, 0);

        var constraints = List.of(new LinearConstraint(new double[]{20, 30}, LEQ, 1200),
                new LinearConstraint(new double[]{1, 0}, LEQ, 40),
                new LinearConstraint(new double[]{0, 1}, LEQ, 30),
                new LinearConstraint(new double[]{1, 0}, GEQ, 0),
                new LinearConstraint(new double[]{0, 1}, GEQ, 0));

        var solver = new SimplexSolver().optimize(oFunction, new LinearConstraintSet(constraints), MAXIMIZE);

        System.out.println(solver.getValue());
        asList(toObject(solver.getPoint())).forEach(System.out::println);
    }
}
