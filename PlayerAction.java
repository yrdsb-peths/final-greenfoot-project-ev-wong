public class PlayerAction {
    public enum ActionType {
        FOLD, CALL, RAISE, CHECK
    }

    private ActionType type;
    private int amount; // Used for raise amount

    public PlayerAction(ActionType type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    public ActionType getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }
}