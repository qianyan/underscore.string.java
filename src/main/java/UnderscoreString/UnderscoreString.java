package UnderscoreString;

import com.google.common.base.*;
import com.google.common.collect.Iterables;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.collect.Iterables.toArray;

public class UnderscoreString {
    private static final Pattern BEFORE_UPPER_CASE = Pattern.compile("(?=\\p{Upper})");

    public static String capitalize(String word) {
        return onCapitalize(word, true);
    }

    private static String onCapitalize(String word, boolean on) {
        String trimWord = word.trim();
        return (trimWord.isEmpty())
                ? trimWord
                : new StringBuilder(trimWord.length())
                .append(on ? Ascii.toUpperCase(trimWord.charAt(0)) : Ascii.toLowerCase(trimWord.charAt(0)))
                .append(trimWord.substring(1))
                .toString();
    }

    public static String slugify(String s) {
        String s1 = CharMatcher.JAVA_LETTER.negate().removeFrom(s);
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN, s1);
    }

    public static String trim(String word, String match) {
        return CharMatcher.anyOf(match).trimFrom(word);
    }

    public static String trim(String word) {
        return CharMatcher.WHITESPACE.trimFrom(word);
    }

    public static String ltrim(String word) {
        return CharMatcher.WHITESPACE.trimLeadingFrom(word);
    }

    public static String ltrim(String word, String match) {
        return CharMatcher.anyOf(match).trimLeadingFrom(word);
    }

    public static String rtrim(String word) {
        return CharMatcher.WHITESPACE.trimTrailingFrom(word);
    }

    public static String rtrim(String word, String match) {
        return CharMatcher.anyOf(match).trimTrailingFrom(word);
    }

    public static String repeat(String word) {
        return repeat(word, 0);
    }

    public static String repeat(String word, int count) {
        return Strings.repeat(word, count);
    }

    public static <T> String repeat(String word, int count, T insert) {
        String repeat = repeat(word + insert, count);
        return repeat.substring(0, repeat.length() - insert.toString().length());
    }

    public static String decapitalize(String word) {
        return onCapitalize(word, false);
    }

    public static String join(String... args) {
        return Joiner.on("").skipNulls().join(args);
    }

    public static String reverse(String word) {
        return new StringBuilder(word).reverse().toString();
    }

    public static String clean(String word) {
        return CharMatcher.WHITESPACE.collapseFrom(trim(word), ' ');
    }

    public static String[] chop(String word, int fixedLength) {
        Preconditions.checkArgument(fixedLength >= 0, "fixedLength must greater than or equal to zero");
        if (fixedLength == 0) return new String[]{word};
        return toArray(Splitter.fixedLength(fixedLength).split(word), String.class);
    }

    public static String splice(String word, int start, int length, String replacement) {
        return new StringBuilder(word).replace(start, start + length, replacement).toString();
    }

    public static char pred(char ch) {
        return (char) (ch - 1);
    }

    public static char succ(char ch) {
        return (char) (ch + 1);
    }

    public static String titleize(String sentence) {
        String trimedSentence = trim(sentence);
        StringBuilder sb = new StringBuilder();
        int length = trimedSentence.length();
        boolean capitalizeNext = true;
        for (int i = 0; i < length; i++) {
            char c = trimedSentence.charAt(i);
            if (CharMatcher.anyOf("_- ").matches(c)) {
                sb.append(c);
                capitalizeNext = true;
            } else if (capitalizeNext) {
                sb.append(Character.toTitleCase(c));
                capitalizeNext = false;
            } else {
                sb.append(Character.toLowerCase(c));
            }
        }
        return sb.toString();
    }

    public static String camelize(String sentence) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, CharMatcher.anyOf("- ").collapseFrom(trim(sentence), '_'));
    }

    public static String dasherize(String sentence) {
        String to = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, CharMatcher.WHITESPACE.collapseFrom(trim(upperBy_(sentence)), '-'));
        return cleanBy(to, '-');
    }

    public static String underscored(String sentence) {
        String to = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_UNDERSCORE, CharMatcher.anyOf("- ").collapseFrom((upperBy_(trim(sentence))), '_'));
        return cleanBy(to, '_');
    }

    public static String classify(String sentence) {
        return capitalize(camelize(sentence));
    }

    public static String humanize(String sentence) {
        return capitalize(replace(underscored(sentence), '_', ' '));
    }

    public static String surround(String word, String wrap) {
        return String.format("%s%s%s", wrap, word, wrap);
    }

    public static String quote(String word) {
        return surround(word, "\"");
    }

    public static String unquote(String word) {
        return unquote(word, '"');
    }

    public static String unquote(String word, char match) {
        if (word.charAt(0) == match && word.charAt(word.length() - 1) == match) {
            return word.substring(1, word.length() - 1);
        }
        return word;
    }

    public static String numberFormat(double number) {
        return numberFormat(number, 0);
    }

    public static String numberFormat(double number, int scale) {
        return NumberFormat.getInstance().format(new BigDecimal(number).setScale(scale, 3));
    }

    public static String strRight(String sentence, String separator) {
        return sentence.substring(sentence.indexOf(separator) + separator.length());
    }

    public static String strRightBack(String sentence, String separator) {
        if (isNullOrEmpty(separator)) {
            return sentence;
        }
        return sentence.substring(sentence.lastIndexOf(separator) + separator.length());
    }

    public static String strLeft(String sentence, String separator) {
        if (isNullOrEmpty(separator)) {
            return sentence;
        }
        return strLeftFrom(sentence, sentence.indexOf(separator));
    }

    public static String strLeftBack(String sentence, String separator) {
        return strLeftFrom(sentence, sentence.lastIndexOf(separator));
    }

    private static String strLeftFrom(String sentence, int index) {
        return index != -1 ? sentence.substring(0, index) : sentence;
    }

    private static String cleanBy(String to, char underscore) {
        return CharMatcher.anyOf(String.valueOf(underscore)).collapseFrom(to, underscore);
    }

    private static String replace(String sentence, char from, char to) {
        return CharMatcher.anyOf(String.valueOf(from)).replaceFrom(sentence, to);
    }

    private static String upperBy_(String sentence) {
        return Joiner.on('_').join(Splitter.on(BEFORE_UPPER_CASE).split(sentence));
    }

    public static String toSentence(String[] strings) {
        checkNotNull(strings, "words should not be null");

        String lastOne = strings[strings.length - 1];
        strings[strings.length - 1] = null;
        return Joiner.on(", ").skipNulls().join(strings) + " and " + lastOne;
    }

    public static int count(String sentence, String find) {
        int nonReplacedLength = sentence.length();
        int length = sentence.replace(find, "").length();
        return (nonReplacedLength - length) / find.length();
    }

    public static String truncate(String sentence, int position, String pad) {
        checkState(sentence.length() >= position);
        return splice(sentence, position, sentence.length() - position + 1, pad);
    }

    public static String lpad(String sentence, int count) {
        return lpad(sentence, count, ' ');
    }

    public static String lpad(String sentence, int count, char ch) {
        return Strings.padStart(sentence, count, ch);
    }

    public static String rpad(String sentence, int count) {
        return rpad(sentence, count, ' ');
    }

    public static String rpad(String sentence, int count, char ch) {
        return Strings.padEnd(sentence, count, ch);
    }

    public static String lrpad(String sentence, int count) {
        return lrpad(sentence, count, ' ');
    }

    public static String lrpad(String sentence, int count, char ch) {
        int padEnd = (count - sentence.length()) / 2;
        return rpad(lpad(sentence, count - padEnd, ch), count, ch);
    }

    public static String[] words(String sentence) {
        return Iterables.toArray(Splitter.on(CharMatcher.WHITESPACE).split(CharMatcher.anyOf(" _-").collapseFrom(sentence, ' ')), String.class);
    }
}
