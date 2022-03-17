package be.intecbrussel.model;

public enum Salutation {
    MR("Mr."), MRS("Mrs."), UNKNOWN("Unknown");

    public final String label;

    private Salutation(String label) {
        this.label = label;
    }
}


