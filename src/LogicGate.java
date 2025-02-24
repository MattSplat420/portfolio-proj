import components.map.Map;
import components.map.Map1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * The LogicGate class represents a logic gate that can perform various logic
 * operations on a set of boolean inputs. The gate can be of type AND, OR, NOT,
 * or XOR.
 */
public class LogicGate {

    /**
     * Enum representing different types of logic gates.
     */
    public enum GateType {
        AND, OR, NOT, XOR
    }

    /**
     * The current type of the logic gate.
     */
    private GateType currentType;
    /**
     * A map that associates each GateType with its corresponding
     * LogicOperation. This map is used to store and retrieve the logic
     * operations for different types of gates.
     */
    private final Map<GateType, LogicOperation> gateOperations;

    /**
     * Constructs a LogicGate with the specified initial type.
     *
     * @param initialType
     *            the initial type of the logic gate
     */
    public LogicGate(GateType initialType) {
        this.currentType = initialType;
        this.gateOperations = new Map1L<>();
        this.initializeOperations();
    }

    /**
     * This interface represents a logical operation that can be executed with a
     * variable number of boolean inputs.
     */
    private interface LogicOperation {
        /**
         * Executes the logic gate operation with the given boolean inputs.
         *
         * @param inputs
         *            the boolean inputs for the logic gate operation
         * @return the result of the logic gate operation
         */
        boolean execute(boolean... inputs);
    }

    /**
     * Initializes the gate operations for different types of logic gates.
     *
     * <p>
     * This method adds the following operations to the gateOperations list:
     * </p>
     * <ul>
     * <li><b>AND</b>: Returns true if all inputs are true and there is at least
     * one input.</li>
     * <li><b>OR</b>: Returns true if any input is true and there is at least
     * one input.</li>
     * <li><b>NOT</b>: Returns the negation of the single input. Only valid for
     * a single input.</li>
     * <li><b>XOR</b>: Returns true if exactly one of the two inputs is true.
     * Only valid for two inputs.</li>
     * </ul>
     */
    private void initializeOperations() {
        this.gateOperations.add(GateType.AND,
                inputs -> inputs.length > 0 && this.allTrue(inputs));
        this.gateOperations.add(GateType.OR,
                inputs -> inputs.length > 0 && this.anyTrue(inputs));
        this.gateOperations.add(GateType.NOT,
                inputs -> inputs.length == 1 && !inputs[0]);
        this.gateOperations.add(GateType.XOR,
                inputs -> inputs.length == 2 && (inputs[0] ^ inputs[1]));
    }

    /**
     * Checks if all the boolean inputs are true.
     *
     * @param inputs
     *            a variable number of boolean inputs
     * @return {@code true} if all inputs are true, {@code false} otherwise
     */
    private boolean allTrue(boolean... inputs) {
        for (boolean input : inputs) {
            if (!input) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if any of the provided boolean inputs are true.
     *
     * @param inputs
     *            a variable number of boolean values to be checked
     * @return true if any of the inputs are true, false otherwise
     */
    private boolean anyTrue(boolean... inputs) {
        for (boolean input : inputs) {
            if (input) {
                return true;
            }
        }
        return false;
    }

    /**
     * Evaluates the logic gate operation based on the current type and the
     * provided inputs.
     *
     * @param inputs
     *            the boolean inputs to be evaluated by the logic gate
     * @return the result of the logic gate operation as a boolean
     */
    public final boolean evaluate(boolean... inputs) {
        return this.gateOperations.value(this.currentType).execute(inputs);
    }

    /**
     * Sets the type of the logic gate.
     *
     * @param type
     *            the type of the logic gate to set
     */
    public final void setGateType(GateType type) {
        this.currentType = type;
    }

    @Override
    public final String toString() {
        return "LogicGate: " + this.currentType;
    }

    /**
     * Evaluates multiple sets of boolean inputs and returns the results.
     *
     * @param inputSets
     *            an array of boolean arrays, where each boolean array
     *            represents a set of inputs to be evaluated.
     *
     * @return a boolean array containing the evaluation results for each set of
     *         inputs.
     */
    public final boolean[] batchEvaluate(boolean[][] inputSets) {
        boolean[] results = new boolean[inputSets.length];
        for (int i = 0; i < inputSets.length; i++) {
            results[i] = this.evaluate(inputSets[i]);
        }
        return results;
    }

    /**
     * Toggles the current gate type to the next available type in the sequence.
     * The sequence is determined by the order of the GateType enum values. When
     * the end of the sequence is reached, it wraps around to the first type.
     */
    public final void toggleGateType() {
        GateType[] values = GateType.values();
        int nextIndex = (this.currentType.ordinal() + 1) % values.length;
        this.currentType = values[nextIndex];
    }

    /**
     * The main method demonstrates the usage of the LogicGate class with
     * different gate types. It creates a LogicGate object, sets its type,
     * evaluates it with different inputs, and prints the results.
     *
     * @param args
     *            Command line arguments (not used).
     */
    public static void main(String[] args) {
        LogicGate gate = new LogicGate(GateType.AND);
        SimpleWriter out = new SimpleWriter1L();
        out.println(gate);
        out.println("AND Gate: " + gate.evaluate(true, true)); // true
        out.println("AND Gate: " + gate.evaluate(true, false)); // false

        gate.setGateType(GateType.OR);
        out.println(gate);
        out.println("OR Gate: " + gate.evaluate(true, false)); // true

        gate.setGateType(GateType.NOT);
        out.println(gate);
        out.println("NOT Gate: " + gate.evaluate(true)); // false

        gate.setGateType(GateType.XOR);
        out.println(gate);
        out.println("XOR Gate: " + gate.evaluate(true, false)); // true

        gate.toggleGateType();
        out.println("After toggling: " + gate);
        out.close();
    }
}
