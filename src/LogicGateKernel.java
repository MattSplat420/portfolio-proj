import components.standard.Standard;

/**
 * Kernel interface for the LogicGate component.
 *
 * @author Nicky Doneski
 */
public interface LogicGateKernel extends Standard<LogicGate> {

    /**
     * Sets this gate to have the given input values.
     *
     * @param inputA
     *            the first input value
     * @param inputB
     *            the second input value
     * @updates this
     * @requires inputA != null and inputB != null
     * @ensures this.inputs = {inputA, inputB}
     */
    void setInputs(boolean inputA, boolean inputB);

    /**
     * Returns the output value of this gate based on current inputs.
     *
     * @return the output value
     * @ensures output = this.output
     */
    boolean getOutput();
}
