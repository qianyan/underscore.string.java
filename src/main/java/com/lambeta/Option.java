package com.lambeta;

public final class Option {
    private int width;
    private char separator;
    private boolean cut;
    private boolean trailingSpaces;
    private boolean preserveSpaces;

    public static Builder builder() {
        return new Builder();
    }

    public int getWidth() {
        return width;
    }

    public char getSeparator() {
        return separator;
    }

    public boolean isCut() {
        return cut;
    }

    public boolean isTrailingSpaces() {
        return trailingSpaces;
    }

    public boolean isPreserveSpaces() {
        return preserveSpaces;
    }

    public static class Builder {
        private int width = 75;
        private char separator = '\n';
        private boolean cut;
        private boolean trailingSpaces;
        private boolean preserveSpaces;

        public Builder withWidth(int width) {
            this.width = width;
            return this;
        }

        public Builder withSeparator(char seperator) {
            this.separator = seperator;
            return this;
        }

        public Builder withCut(boolean cut) {
            this.cut = cut;
            return this;
        }

        public Builder withTrailingSpaces(boolean trailingSpaces) {
            this.trailingSpaces = trailingSpaces;
            return this;
        }

        public Builder withPreserveSpaces(boolean preserveSpaces) {
            this.preserveSpaces = preserveSpaces;
            return this;
        }

        public Option build() {
            Option option = new Option();
            option.width = this.width;
            option.separator = this.separator;
            option.cut = this.cut;
            option.trailingSpaces = this.trailingSpaces;
            option.preserveSpaces = this.preserveSpaces;

            return option;
        }
    }
}
