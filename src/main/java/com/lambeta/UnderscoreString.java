package com.lambeta;

import com.google.common.base.*;
import com.google.common.base.Optional;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.google.common.primitives.Chars;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Joiner.on;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.google.common.base.Predicates.not;
import static com.google.common.base.Splitter.fixedLength;
import static com.google.common.base.Strings.isNullOrEmpty;
import static com.google.common.base.Strings.nullToEmpty;
import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Iterables.toArray;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.String.format;

public class UnderscoreString {

    public static String capitalize(String word) {
        return onCapitalize(word, true);
    }

    private static String onCapitalize(String word, boolean on) {
        String trimWord = nullToEmpty(word).trim();
        return trimWord.isEmpty()
                ? trimWord
                : String.valueOf(on ? Ascii.toUpperCase(trimWord.charAt(0)) : Ascii.toLowerCase(trimWord.charAt(0))) +
                trimWord.substring(1);
    }

    public static String slugify(String s) {
        return trim(dasherize(toAscii(CharMatcher.JAVA_LETTER.negate().replaceFrom(nullToEmpty(s), "-"))), "-");
    }

    private static String toAscii(String str) {
        return stripAccents(str).replaceAll("ß", "ss");
    }

    public static String trim(String word, String match) {
        return CharMatcher.anyOf(match).trimFrom(nullToEmpty(word));
    }

    public static String trim(String word) {
        return CharMatcher.WHITESPACE.trimFrom(nullToEmpty(word));
    }

    public static String ltrim(String word) {
        return CharMatcher.WHITESPACE.trimLeadingFrom(nullToEmpty(word));
    }

    public static String ltrim(String word, String match) {
        return CharMatcher.anyOf(match).trimLeadingFrom(nullToEmpty(word));
    }

    public static String rtrim(String word) {
        return CharMatcher.WHITESPACE.trimTrailingFrom(nullToEmpty(word));
    }

    public static String rtrim(String word, String match) {
        return CharMatcher.anyOf(match).trimTrailingFrom(nullToEmpty(word));
    }

    public static String repeat(String word) {
        return repeat(word, 0);
    }

    public static String repeat(String word, int count) {
        return Strings.repeat(word, count);
    }

    public static <T> String repeat(String word, int count, T insert) {
        String w = nullToEmpty(word);
        String repeat = repeat(w + insert, count);
        return repeat.substring(0, repeat.length() - insert.toString().length());
    }

    public static String decapitalize(String word) {
        return onCapitalize(word, false);
    }

    public static String join(String... args) {
        return on("").skipNulls().join(args);
    }

    public static String reverse(String word) {
        return new StringBuilder(nullToEmpty(word)).reverse().toString();
    }

    public static String clean(String word) {
        return collapseWhitespaces(trim(word));
    }

    public static String[] chop(String word, int fixedLength) {
        Preconditions.checkArgument(fixedLength >= 0, "fixedLength must greater than or equal to zero");
        String w = nullToEmpty(word);
        if (fixedLength == 0) {
            return new String[]{w};
        }
        return toArray(fixedLength(fixedLength).split(w), String.class);
    }

    public static String splice(String word, int start, int length, String replacement) {
        return new StringBuilder(nullToEmpty(word)).replace(start, start + length, replacement).toString();
    }

    public static char pred(char ch) {
        return (char) (ch - 1);
    }

    public static char succ(char ch) {
        return (char) (ch + 1);
    }

    public static String titleize(String sentence) {
        String trimSentence = trim(nullToEmpty(sentence));
        StringBuilder sb = new StringBuilder();
        int length = trimSentence.length();
        boolean capitalizeNext = true;
        for (int i = 0; i < length; i++) {
            char c = trimSentence.charAt(i);
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
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, CharMatcher.anyOf("- ").collapseFrom(trim(nullToEmpty(sentence)), '_'));
    }

    public static String dasherize(String sentence) {
        String to = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, CharMatcher.WHITESPACE.collapseFrom(trim(replaceZeroWidthDelimiterWith(nullToEmpty(sentence), "_")), '-'));
        return cleanBy(to, '-');
    }

    public static String underscored(String sentence) {
        String to = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_UNDERSCORE, CharMatcher.anyOf("- ").collapseFrom(replaceZeroWidthDelimiterWith(trim(nullToEmpty(sentence)), "_"), '_'));
        return cleanBy(to, '_');
    }

    public static String classify(String sentence) {
        return capitalize(camelize(sentence));
    }

    public static String humanize(String sentence) {
        return capitalize(replace(underscored(sentence), '_', ' '));
    }

    public static String surround(String word, String wrap) {
        String wr = nullToEmpty(wrap);
        String w = nullToEmpty(word);
        return format("%s%s%s", wr, w, wr);
    }

    public static String quote(String word) {
        return surround(word, "\"");
    }

    public static String unquote(String word) {
        return unquote(nullToEmpty(word), '"');
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
        return NumberFormat.getInstance().format(new BigDecimal(number).setScale(scale, RoundingMode.FLOOR));
    }

    public static String strRight(String sentence, String separator) {
        if (isNullOrEmpty(separator)) {
            return sentence;
        }
        String s = nullToEmpty(sentence);
        return s.substring(s.indexOf(separator) + separator.length());
    }

    public static String strRightBack(String sentence, String separator) {
        if (isNullOrEmpty(separator)) {
            return sentence;
        }
        String s = nullToEmpty(sentence);

        return s.substring(s.lastIndexOf(separator) + separator.length());
    }

    public static String strLeft(String sentence, String separator) {
        if (isNullOrEmpty(separator)) {
            return sentence;
        }
        String s = nullToEmpty(sentence);
        return strLeftFrom(s, s.indexOf(separator));
    }

    public static String strLeftBack(String sentence, String separator) {
        if (isNullOrEmpty(separator)) {
            return sentence;
        }
        String s = nullToEmpty(sentence);
        return strLeftFrom(s, s.lastIndexOf(separator));
    }

    private static String strLeftFrom(String sentence, int index) {
        return index != -1 ? sentence.substring(0, index) : sentence;
    }

    private static String cleanBy(String to, char separator) {
        return CharMatcher.anyOf(String.valueOf(separator)).collapseFrom(to, separator);
    }

    private static String replace(String sentence, char from, char to) {
        return CharMatcher.anyOf(String.valueOf(from)).replaceFrom(nullToEmpty(sentence), to);
    }

    public static String replaceZeroWidthDelimiterWith(String sentence, String replacement) {
        return nullToEmpty(sentence).replaceAll(format("%s|%s|%s",
                "(?<=[A-Z])(?=[A-Z][a-z])",
                "(?<=[^A-Z])(?=[A-Z])",
                "(?<=[A-Za-z])(?=[^A-Za-z])"), replacement);
    }

    public static String toSentence(String[] strings) {
        checkNotNull(strings, "words should not be null");

        String lastOne = strings[strings.length - 1];
        strings[strings.length - 1] = null;
        return on(", ").skipNulls().join(strings) + " and " + lastOne;
    }

    public static int count(String sentence, String find) {
        String s = nullToEmpty(sentence);
        int nonReplacedLength = s.length();
        int length = s.replace(find, "").length();
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
        return Strings.padStart(nullToEmpty(sentence), count, ch);
    }

    public static String rpad(String sentence, int count) {
        return rpad(sentence, count, ' ');
    }

    public static String rpad(String sentence, int count, char ch) {
        return Strings.padEnd(nullToEmpty(sentence), count, ch);
    }

    public static String lrpad(String sentence, int count) {
        return lrpad(sentence, count, ' ');
    }

    public static String lrpad(String sentence, int count, char ch) {
        String s = nullToEmpty(sentence);
        int padEnd = (count - s.length()) / 2;
        return rpad(lpad(s, count - padEnd, ch), count, ch);
    }

    public static String[] words(String sentence) {
        return Iterables.toArray(Splitter.on(CharMatcher.WHITESPACE).split(CharMatcher.anyOf(" _-").collapseFrom(nullToEmpty(sentence), ' ')), String.class);
    }

    public static String prune(String sentence, int count) {
        String st = nullToEmpty(sentence);
        if (st.length() <= count) {
            return st;
        }
        String[] words = words(st);
        if (words.length == 1) {
            return st;
        }

        int rest = count;
        for (int i = 0; i < words.length; i++) {
            rest = i == 0 ? rest - words[i].length() : rest - words[i].length() - 1;//backspace
            if (rest < 0) {
                if (i == 0) {
                    return rtrim(words[i], ",") + "...";
                }

                words[i - 1] = rtrim(words[i - 1], ",") + "...";
                String[] target = new String[i];
                System.arraycopy(words, 0, target, 0, i);
                return on(' ').join(target);
            }
        }
        return st;
    }

    public static boolean isBlank(String s) {
        return Strings.isNullOrEmpty(s) || CharMatcher.WHITESPACE.matchesAllOf(s);
    }

    public static String replaceAll(String str, String find, String replace) {
        return replaceAll(str, find, replace, false);
    }

    public static String replaceAll(String str, String find, String replace, boolean ignorecase) {
        String findRegex = ignorecase ? "(?i)" + find : find;
        return Pattern.compile(findRegex).matcher(nullToEmpty(str)).replaceAll(replace);
    }

    public static String swapCase(String str) {
        List<Character> chars = Chars.asList(nullToEmpty(str).toCharArray());
        return from(chars).transform(flip()).join(on(""));
    }

    private static Function<Character, String> flip() {
        return new Function<Character, String>() {
            @Override
            public String apply(Character ch) {
                if ('ß' == ch) return "SS";
                return (Character.isUpperCase(ch) ? Character.toLowerCase(ch) : Character.toUpperCase(ch)) + "";
            }
        };
    }

    public static int naturalCmp(String str0, String str1) {
        if (str0 == null) {
            return -1;
        }
        if (str1 == null) {
            return 1;
        }
        if (str0.equals(str1)) {
            return 0;
        }
        Pattern numberRegex = Pattern.compile("(\\.\\d+|\\d+|\\D+)");
        String[] token0 = reseq(numberRegex.matcher(str0));
        String[] token1 = reseq(numberRegex.matcher(str1));

        int minSize = min(token0.length, token1.length);

        for (int i = 0; i < minSize; i++) {
            String a = token0[i];
            String b = token1[i];

            if (!a.equals(b)) {
                double num0, num1;
                try {
                    num0 = Double.parseDouble(a);
                    num1 = Double.parseDouble(b);
                    return num0 > num1 ? 1 : -1;
                } catch (NumberFormatException e) {
                    return a.compareTo(b) > 0 ? 1 : -1;
                }
            }
        }

        if (token0.length != token1.length) {
            return token0.length > token1.length ? 1 : -1;
        }

        return str0.compareTo(str1) > 0 ? 1 : -1;
    }

    private static String[] reseq(Matcher matcher) {
        ArrayList<String> tokens = new ArrayList<>();
        while (matcher.find()) {
            tokens.add(matcher.group());
        }
        return tokens.toArray(new String[tokens.size()]);
    }

    public static String dedent(String str0) {
        String str = nullToEmpty(str0);
        return Pattern.compile(format("^[ \\t]{%d}", indent(str)), Pattern.MULTILINE).matcher(str).replaceAll("");
    }

    private static int indent(String str) {
        if (Strings.isNullOrEmpty(str)) {
            return 0;
        }
        String[] reseq = reseq(Pattern.compile("^[\\s\\t]*", Pattern.MULTILINE).matcher(str));
        int indent = reseq[0].length();
        for (int i = 1; i < reseq.length; i++) {
            indent = min(reseq[i].length(), indent);
        }
        return indent;
    }

    public static String commonPrefix(String s, String s1) {
        return Strings.commonPrefix(s, s1);
    }

    public static String commonPrefix(String s, String s1, boolean ignoreCase) {
        return ignoreCase ? s1.substring(0, commonPrefix(s.toLowerCase(), s1.toLowerCase()).length()) : commonPrefix(s, s1);
    }

    public static String commonSuffix(String s, String s1) {
        return Strings.commonSuffix(s, s1);
    }

    public static String commonSuffix(String s, String s1, boolean ignoreCase) {
        return ignoreCase ? s1.substring(s1.length() - commonSuffix(s.toLowerCase(), s1.toLowerCase()).length()) : commonSuffix(s, s1);
    }

    public static String chopPrefix(String s, String prefix) {
        return s.startsWith(prefix) ? s.substring(prefix.length()) : s;
    }

    public static String chopPrefix(String s, String prefix, boolean ignoreCase) {
        boolean prefixIgnoreCase = ignoreCase && s.toLowerCase().startsWith(prefix.toLowerCase());
        return prefixIgnoreCase ? s.substring(prefix.length()) : chopPrefix(s, prefix);
    }

    public static String chopSuffix(String s, String suffix) {
        return s.endsWith(suffix) ? s.substring(0, s.length() - suffix.length()) : s;
    }

    public static String chopSuffix(String s, String suffix, boolean ignoreCase) {
        boolean suffixIgnoreCase = ignoreCase && s.toLowerCase().endsWith(suffix.toLowerCase());
        return suffixIgnoreCase ? s.substring(0, s.length() - suffix.length()) : chopSuffix(s, suffix);
    }

    public static String screamingUnderscored(String s) {
        return underscored(s).toUpperCase();
    }

    public static String stripAccents(String s) {
        return Normalizer.normalize(nullToEmpty(s), Normalizer.Form.NFD).replaceAll("\\p{InCOMBINING_DIACRITICAL_MARKS}+", "");
    }

    public static String pascalize(String s) {
        return titleize(underscored(s)).replace("_", "");
    }

    public static String translate(String str, HashMap<Character, Character> dictionary) {
        return translate(str, dictionary, Sets.<Character>newHashSet());
    }

    public static String translate(String str, HashMap<Character, Character> dictionary, HashSet<Character> deletedChars) {
        List<Character> chars = Chars.asList(nullToEmpty(str).toCharArray());
        return from(chars).filter(without(deletedChars)).transform(in(dictionary)).filter(without(new HashSet<Character>() {{
            add(null);
        }})).join(on(""));
    }

    private static Function<Character, Character> in(final HashMap<Character, Character> dictionary) {
        return new Function<Character, Character>() {
            @Override
            public Character apply(Character c) {
                return dictionary.containsKey(c) ? dictionary.get(c) : c;
            }
        };
    }

    private static Predicate<Character> without(final HashSet<Character> deletedChars) {
        return not(new Predicate<Character>() {
            @Override
            public boolean apply(Character c) {
                return deletedChars.contains(c);
            }
        });
    }

    public static Optional<String> mixedCase(String s) {
        String ss = nullToEmpty(s);
        boolean isMixedCase = CharMatcher.JAVA_LOWER_CASE.matchesAnyOf(ss) && CharMatcher.JAVA_UPPER_CASE.matchesAnyOf(ss);
        return isMixedCase ? Optional.of(ss) : Optional.<String>absent();
    }

    public static String collapseWhitespaces(String s) {
        return CharMatcher.WHITESPACE.collapseFrom(nullToEmpty(s), ' ');
    }

    public static Optional<String> ascii(String s) {
        List<Character> chars = Chars.asList(nullToEmpty(s).toCharArray());

        return from(chars).transform(isAscii()).allMatch(is(true)) ? Optional.<String>of(s) : Optional.<String>absent();
    }

    private static Predicate<Boolean> is(final boolean b) {
        return new Predicate<Boolean>() {
            @Override
            public boolean apply(Boolean isAscii) {
                return isAscii == b;
            }
        };
    }

    private static Function<Character, Boolean> isAscii() {
        return new Function<Character, Boolean>() {
            @Override
            public Boolean apply(Character c) {
                return (int) c < 128;
            }
        };
    }

    public static String chomp(String s) {
        String ss = nullToEmpty(s);
        if (ss.endsWith("\r\n")) {
            return ss.substring(0, ss.length() - 2);
        }

        if (ss.endsWith("\r") || ss.endsWith("\n")) {
            return ss.substring(0, ss.length() - 1);
        }

        return ss;
    }

    public static boolean startsWith(String s, String prefix) {
        return startsWith(nullToEmpty(s), prefix, false);
    }

    public static boolean startsWith(String s, String prefix, boolean ignoreCase) {
        return ignoreCase ? s.substring(0, min(s.length(), prefix.length())).equalsIgnoreCase(prefix) : s.startsWith(prefix);
    }

    public static boolean endsWith(String s, String suffix) {
        return endsWith(nullToEmpty(s), suffix, false);
    }

    public static boolean endsWith(String s, String suffix, boolean ignoreCase) {
        return ignoreCase ? s.substring(s.length() - min(s.length(), suffix.length())).equalsIgnoreCase(suffix) : s.endsWith(suffix);
    }

    /**
     * https://en.wikipedia.org/wiki/Levenshtein_distance
     */
    public static int levenshtein(String s, String s1) {
        String ss = nullToEmpty(s);
        String ss1 = nullToEmpty(s1);

        if (ss.isEmpty() || ss1.isEmpty()) {
            return max(ss.length(), ss1.length());
        }

        if (ss.equals(ss1)) {
            return 0;
        }

        int[] previousRow = new int[ss1.length() + 1];
        for (int i = 0; i < previousRow.length; i++) {
            previousRow[i] = i; // target prefixes can be reached from empty source prefix by inserting every character
        }

        for (int i = 0; i < ss.length(); i++) {
            int valueOfNextColumn = i + 1; // source prefixes can be transformed into empty string by dropping all characters
            for (int j = 0; j < ss1.length(); j++) {
                int valueOfCurrentColumn = valueOfNextColumn;
                // substitution (d[i][j] + cost)
                int substitutionCost = (ss.charAt(i) == ss1.charAt(j) ? 0 : 1);
                valueOfNextColumn = previousRow[j] + substitutionCost;
                // insertion (d[i+1][j] + 1)
                valueOfNextColumn = min(valueOfNextColumn, valueOfCurrentColumn + 1);
                // deletion (d[i][j+1] + 1)
                valueOfNextColumn = min(valueOfNextColumn, previousRow[j + 1] + 1);

                previousRow[j] = valueOfCurrentColumn;
            }

            previousRow[previousRow.length - 1] = valueOfNextColumn;
        }

        return previousRow[previousRow.length - 1];
    }

    public static int hamming(String s, String s1) {
        String ss = nullToEmpty(s);
        String ss1 = nullToEmpty(s1);
        checkArgument(ss.length() == ss1.length(), "The two strings should be equal length.");

        int count = 0;
        int size = ss.length();

        for (int i = 0; i < size; i++) {
            if (ss.charAt(i) != ss1.charAt(i)) {
                count++;
            }
        }

        return count;
    }

    /**
     * https://en.wikipedia.org/wiki/Longest_common_substring_problem
     */
    public static Set<String> longestCommonSubstring(String s, String s1) {
        String ss = nullToEmpty(s);
        String ss1 = nullToEmpty(s1);
        int[][] lengths = new int[ss.length()][ss1.length()];
        int longestLength = 0;
        Set<String> result = Sets.newHashSet();
        for (int i = 0; i < ss.length(); i++) {
            for (int j = 0; j < ss1.length(); j++) {
                if (ss.charAt(i) == ss1.charAt(j)) {
                    if (i == 0 || j == 0) {
                        lengths[i][j] = 1;
                    } else {
                        lengths[i][j] = lengths[i - 1][j - 1] + 1;
                    }

                    //  swap or append longest common substring.
                    if (lengths[i][j] > longestLength) {
                        longestLength = lengths[i][j];
                        result = Sets.newHashSet(ss.substring(i - longestLength + 1, i + 1));
                    } else if (lengths[i][j] == longestLength) {
                        result.add(ss.substring(i - longestLength + 1, i + 1));
                    }
                } else {
                    lengths[i][j] = 0;
                }
            }
        }
        return result;
    }
}
