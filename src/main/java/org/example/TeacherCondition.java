package org.example;

public enum TeacherCondition {
    present, delegation, sick, notPresent;

    public String toString() {
        switch (this) {
            case present: return "present";
            case delegation: return "delegation";
            case sick: return "sick";
            case notPresent: return "not present";
            default: return "No information about Teacher's condition";
        }
    }
}
