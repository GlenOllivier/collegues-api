package dev.tpjava.colleguesapi.exception;

import java.util.HashMap;
import java.util.Map;

public class CollegueInvalideException extends RuntimeException {
    private Map<String, String> causes;

    public CollegueInvalideException() {
      causes = new HashMap<>();
    }

    public CollegueInvalideException(Map<String, String> causes) {
        this.causes = causes;
    }

    public Map<String, String> getCauses() {
        return causes;
    }

    public void setCauses(Map<String, String> causes) {
        this.causes = causes;
    }
}
