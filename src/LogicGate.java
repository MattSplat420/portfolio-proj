
/**
 * Enhanced interface for the LogicGate component. Adds functionality on top of
 * LogicGateKernel.
 *
 * Follows the OSU Component discipline.
 *
 * Inherits from LogicGateKernel.
 *
 * @author Nicky Doneski
 */
public interface LogicGate extends LogicGateKernel {

    /**
     * Sets the type of the logic gate (AND, OR, XOR, etc.).
     *
     * @param type
     *            the type of logic gate
     * @updates this
     * @requires type is one of {"AND", "OR", "XOR", "NAND", "NOR"}
     * @ensures this.gateType = type
     */
    void setGateType(String type);

    /**
     * Gets the current type of the logic gate.
     *
     * @return the type of logic gate
     * @ensures output = this.gateType
     */
    String getGateType();
}
