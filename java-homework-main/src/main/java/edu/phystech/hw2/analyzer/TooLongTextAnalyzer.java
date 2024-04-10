package edu.phystech.hw2.analyzer;

public class TooLongTextAnalyzer implements TextAnalyzer {

    private final int maxLen;

    public TooLongTextAnalyzer(int maxLen) {
        this.maxLen = maxLen;
    }

    @Override
    public Label processText(String text) {
        if (text.length() > this.maxLen) {
            return Label.TOO_LONG;
        }
        return Label.OK;
    }
}
