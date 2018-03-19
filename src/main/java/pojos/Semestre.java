package pojos;

public enum Semestre {
    S1("Semestre 1 (25€)"),
    S2("5 paniers - Semestre 2 (25€)"),
    S1S2("10 paniers - 2 semestres (45€)");
    private String label;

    Semestre(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}