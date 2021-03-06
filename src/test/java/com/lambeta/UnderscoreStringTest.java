package com.lambeta;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UnderscoreStringTest {
    @Test
    public void capitalize() throws Exception {
        assertThat(UnderscoreString.capitalize(" hello "), is("Hello"));
    }

    @Test
    public void slugify() throws Exception {
        assertThat(UnderscoreString.slugify(""), is(""));
        assertThat(UnderscoreString.slugify(null), is(""));
        assertThat(UnderscoreString.slugify(" hello World!"), is("hello-world"));
        assertThat(UnderscoreString.slugify("Un éléphant àß l\'orée du bois"), is("un-elephant-ass-l-oree-du-bois"));
    }

    @Test
    public void count() throws Exception {
        assertThat(UnderscoreString.count("", "l"), is(0));
        assertThat(UnderscoreString.count(null, "l"), is(0));
        assertThat(UnderscoreString.count("Hello world", "l"), is(3));
        assertThat(UnderscoreString.count("Hello world", "Hello"), is(1));
    }

    @Test(expected = NullPointerException.class)
    public void null_to_find_str_should_throw_exception_with_count() throws Exception {
        UnderscoreString.count("hello", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void empty_to_find_str_should_throw_exception_with_count() throws Exception {
        UnderscoreString.count("hello", "");
    }

    @Test
    public void trim() throws Exception {
        assertThat(UnderscoreString.trim(null), is(""));
        assertThat(UnderscoreString.trim(""), is(""));
        assertThat(UnderscoreString.trim(" foo "), is("foo"));
        assertThat(UnderscoreString.trim("foo", "f"), is("oo"));
        assertThat(UnderscoreString.trim("fooff", "f"), is("oo"));
        assertThat(UnderscoreString.trim("_-foo-_", "_-"), is("foo"));
    }

    @Test
    public void ltrim() throws Exception {
        assertThat(UnderscoreString.ltrim(null), is(""));
        assertThat(UnderscoreString.ltrim(""), is(""));
        assertThat(UnderscoreString.ltrim(" foo"), is("foo"));
        assertThat(UnderscoreString.ltrim(" foo "), is("foo "));
        assertThat(UnderscoreString.ltrim("foof", "f"), is("oof"));
        assertThat(UnderscoreString.ltrim("_-foo-_", "_-"), is("foo-_"));
    }

    @Test
    public void rtrim() throws Exception {
        assertThat(UnderscoreString.rtrim(null), is(""));
        assertThat(UnderscoreString.rtrim(""), is(""));
        assertThat(UnderscoreString.rtrim("foo "), is("foo"));
        assertThat(UnderscoreString.rtrim(" foo "), is(" foo"));
        assertThat(UnderscoreString.rtrim("foof", "f"), is("foo"));
        assertThat(UnderscoreString.rtrim("_-foo-_", "_-"), is("_-foo"));
    }

    @Test
    public void repeat() throws Exception {
        assertThat(UnderscoreString.repeat("foo"), is(""));
        assertThat(UnderscoreString.repeat(""), is(""));
        assertThat(UnderscoreString.repeat("foo", 3), is("foofoofoo"));
        assertThat(UnderscoreString.repeat("foo", 3, "*"), is("foo*foo*foo"));
        assertThat(UnderscoreString.repeat("foo", 3, 3), is("foo3foo3foo"));
        assertThat(UnderscoreString.repeat("foo", 3, new Person("ryan", "male")),
                is("fooPerson{name=ryan, gender=male}fooPerson{name=ryan, gender=male}foo"));
    }

    @Test
    public void decapitalize() throws Exception {
        assertThat(UnderscoreString.decapitalize(null), is(""));
        assertThat(UnderscoreString.decapitalize(""), is(""));
        assertThat(UnderscoreString.decapitalize(" Hello "), is("hello"));
        assertThat(UnderscoreString.decapitalize("HELLO"), is("hELLO"));
    }

    @Test
    public void join() throws Exception {
        assertThat(UnderscoreString.join("", "foo", "bar"), is("foobar"));
        assertThat(UnderscoreString.join("", null, "bar"), is("bar"));
    }

    @Test
    public void reverse() throws Exception {
        assertThat(UnderscoreString.reverse(null), is(""));
        assertThat(UnderscoreString.reverse(""), is(""));
        assertThat(UnderscoreString.reverse("foo"), is("oof"));
        assertThat(UnderscoreString.reverse("saippuakauppias"), is("saippuakauppias"));
    }

    @Test
    public void clean() throws Exception {
        assertThat(UnderscoreString.clean(null), is(""));
        assertThat(UnderscoreString.clean(""), is(""));
        assertThat(UnderscoreString.clean(" foo    bar   "), is("foo bar"));
    }

    @Test
    public void chop() throws Exception {
        assertThat(UnderscoreString.chop(null, 2), is(_a("")));
        assertThat(UnderscoreString.chop("", 2), is(_a("")));
        assertThat(UnderscoreString.chop("whitespace", 2), is(_a("wh", "it", "es", "pa", "ce")));
        assertThat(UnderscoreString.chop("whitespace", 3), is(_a("whi", "tes", "pac", "e")));
        assertThat(UnderscoreString.chop("whitespace", 0), is(_a("whitespace")));
    }

    @Test
    public void chopPrefix() throws Exception {
        assertThat(UnderscoreString.chopPrefix(null, "foo"), is(""));
        assertThat(UnderscoreString.chopPrefix("", "foo"), is(""));
        assertThat(UnderscoreString.chopPrefix("foo", "foo"), is(""));
        assertThat(UnderscoreString.chopPrefix("foobar", "foo"), is("bar"));
        assertThat(UnderscoreString.chopPrefix(" foo", " "), is("foo"));
        assertThat(UnderscoreString.chopPrefix("foo", "FOO"), is("foo"));
        assertThat(UnderscoreString.chopPrefix("foo", "FOO", true), is(""));
        assertThat(UnderscoreString.chopPrefix(null, "FOO", true), is(""));
        assertThat(UnderscoreString.chopPrefix("", "FOO", true), is(""));
        assertThat(UnderscoreString.chopPrefix("Åfoo", "Å", true), is("foo"));
    }

    @Test
    public void chopSuffix() throws Exception {
        assertThat(UnderscoreString.chopSuffix(null, "foo"), is(""));
        assertThat(UnderscoreString.chopSuffix("", "foo"), is(""));
        assertThat(UnderscoreString.chopSuffix("foo", "foo"), is(""));
        assertThat(UnderscoreString.chopSuffix("foobar", "bar"), is("foo"));
        assertThat(UnderscoreString.chopSuffix("foo", "FOO"), is("foo"));
        assertThat(UnderscoreString.chopSuffix(null, "FOO", true), is(""));
        assertThat(UnderscoreString.chopSuffix("", "FOO", true), is(""));
        assertThat(UnderscoreString.chopSuffix("foo", "FOO", true), is(""));
        assertThat(UnderscoreString.chopSuffix("foo", "O", true), is("fo"));
    }

    @Test
    public void splice() throws Exception {
        assertThat(UnderscoreString.splice(null, 0, 0, ""), is(""));
        assertThat(UnderscoreString.splice("", 0, 0, ""), is(""));
        assertThat(UnderscoreString.splice("whitespace", 5, 5, "shift"), is("whiteshift"));
        assertThat(UnderscoreString.splice(
                "https://edtsech@bitbucket.org/edtsech/underscore.strings", 30, 7, "epeli"),
                is("https://edtsech@bitbucket.org/epeli/underscore.strings"));
    }

    @Test
    public void pred() throws Exception {
        assertThat(UnderscoreString.pred('b'), is('a'));
        assertThat(UnderscoreString.pred('B'), is('A'));
        assertThat(UnderscoreString.pred(','), is('+'));
        assertThat(UnderscoreString.pred('2'), is('1'));
        assertThat(UnderscoreString.pred('豈'), is(''));
    }

    @Test
    public void succ() throws Exception {
        assertThat(UnderscoreString.succ('a'), is('b'));
        assertThat(UnderscoreString.succ(''), is('豈'));
        assertThat(UnderscoreString.succ('A'), is('B'));
        assertThat(UnderscoreString.succ('+'), is(','));
        assertThat(UnderscoreString.succ('1'), is('2'));
    }

    @Test
    public void titleize() throws Exception {
        assertThat(UnderscoreString.titleize(null), is(""));
        assertThat(UnderscoreString.titleize(""), is(""));
        assertThat(UnderscoreString.titleize("the titleize string method"), is("The Titleize String Method"));
        assertThat(UnderscoreString.titleize("a-dash-separated-string"), is("A-Dash-Separated-String"));
        assertThat(UnderscoreString.titleize("a_dash_separated_string"), is("A_Dash_Separated_String"));
        assertThat(UnderscoreString.titleize("A-DASH-SEPARATED-STRING"), is("A-Dash-Separated-String"));
    }

    @Test
    public void camelize() throws Exception {
        assertThat(UnderscoreString.camelize(null), is(""));
        assertThat(UnderscoreString.camelize(""), is(""));
        assertThat(UnderscoreString.camelize("the_camelize_string_method"), is("theCamelizeStringMethod"));
        assertThat(UnderscoreString.camelize("the_camelize___string_method"), is("theCamelizeStringMethod"));
        assertThat(UnderscoreString.camelize("webkit-transform"), is("webkitTransform"));
        assertThat(UnderscoreString.camelize("webkit----transform"), is("webkitTransform"));
        assertThat(UnderscoreString.camelize(" webkit transform"), is("webkitTransform"));
        assertThat(UnderscoreString.camelize(" webkit     transform "), is("webkitTransform"));
        assertThat(UnderscoreString.camelize("_webkit   _  transform "), is("WebkitTransform"));
    }

    @Test
    public void dasherize() throws Exception {
        assertThat(UnderscoreString.dasherize(null), is(""));
        assertThat(UnderscoreString.dasherize(""), is(""));
        assertThat(UnderscoreString.dasherize("the_dasherize_string_method"), is("the-dasherize-string-method"));
        assertThat(UnderscoreString.dasherize("TheDasherizeStringMethod"), is("the-dasherize-string-method"));
        assertThat(UnderscoreString.dasherize("The_Dasherize-String_-Method"), is("the-dasherize-string-method"));
        assertThat(UnderscoreString.dasherize("the dasherize string method"), is("the-dasherize-string-method"));
        assertThat(UnderscoreString.dasherize("_the dasherize string method"), is("-the-dasherize-string-method"));
    }

    @Test
    public void underscored() throws Exception {
        assertThat(UnderscoreString.underscored(null), is(""));
        assertThat(UnderscoreString.underscored(""), is(""));
        assertThat(UnderscoreString.underscored("the-underscored-string-method"), is("the_underscored_string_method"));
        assertThat(UnderscoreString.underscored("theUnderscoredStringMethod"), is("the_underscored_string_method"));
        assertThat(UnderscoreString.underscored("TheUnderscoredStringMethod"), is("the_underscored_string_method"));
        assertThat(UnderscoreString.underscored("The-Underscored_String_-Method"), is("the_underscored_string_method"));
    }

    @Test
    public void classify() throws Exception {
        assertThat(UnderscoreString.classify(null), is(""));
        assertThat(UnderscoreString.classify(""), is(""));
        assertThat(UnderscoreString.classify("some_class_name"), is("SomeClassName"));
        assertThat(UnderscoreString.classify("my wonderfull class_name"), is("MyWonderfullClassName"));
    }

    @Test
    public void humanize() throws Exception {
        assertThat(UnderscoreString.humanize(null), is(""));
        assertThat(UnderscoreString.humanize(""), is(""));
        assertThat(UnderscoreString.humanize("the humanize string method"), is("The humanize string method"));
        assertThat(UnderscoreString.humanize("the_humanize_string_method"), is("The humanize string method"));
        assertThat(UnderscoreString.humanize("TheHumanizeStringMethod"), is("The humanize string method"));
        assertThat(UnderscoreString.humanize("the humanize_id string method"), is("The humanize id string method"));
    }

    @Test
    public void replaceZeroLengthMatcherWith() throws Exception {
        assertThat(UnderscoreString.replaceZeroWidthDelimiterWith(null, " "), is(""));
        assertThat(UnderscoreString.replaceZeroWidthDelimiterWith("", " "), is(""));
        assertThat(UnderscoreString.replaceZeroWidthDelimiterWith("GL11Version", " "), is("GL 11 Version"));
        assertThat(UnderscoreString.replaceZeroWidthDelimiterWith("May9", " "), is("May 9"));
        assertThat(UnderscoreString.replaceZeroWidthDelimiterWith("lowercase", " "), is("lowercase"));
        assertThat(UnderscoreString.replaceZeroWidthDelimiterWith("Class", " "), is("Class"));
        assertThat(UnderscoreString.replaceZeroWidthDelimiterWith("MyClass", " "), is("My Class"));
        assertThat(UnderscoreString.replaceZeroWidthDelimiterWith("HTML", " "), is("HTML"));
        assertThat(UnderscoreString.replaceZeroWidthDelimiterWith("PDFLoader", " "), is("PDF Loader"));
        assertThat(UnderscoreString.replaceZeroWidthDelimiterWith("AString", " "), is("A String"));
        assertThat(UnderscoreString.replaceZeroWidthDelimiterWith("SimpleXMLParser", " "), is("Simple XML Parser"));

    }

    @Test
    public void surround() throws Exception {
        assertThat(UnderscoreString.surround(null, "\""), is("\"\""));
        assertThat(UnderscoreString.surround("", "\""), is("\"\""));
        assertThat(UnderscoreString.surround("'", "\""), is("\"'\""));
        assertThat(UnderscoreString.surround("foo", "|"), is("|foo|"));
        assertThat(UnderscoreString.surround("foo", ""), is("foo"));
    }

    @Test
    public void quote() throws Exception {
        assertThat(UnderscoreString.quote(null), is("\"\""));
        assertThat(UnderscoreString.quote(""), is("\"\""));
        assertThat(UnderscoreString.quote("foo"), is("\"foo\""));
        assertThat(UnderscoreString.quote("\"foo\""), is("\"\"foo\"\""));
    }

    @Test
    public void unquote() throws Exception {
        assertThat(UnderscoreString.unquote("\"foo"), is("\"foo"));
        assertThat(UnderscoreString.unquote("\"foo\""), is("foo"));
        assertThat(UnderscoreString.unquote("\"\"foo\"\""), is("\"foo\""));
        assertThat(UnderscoreString.unquote("'foo'", '\''), is("foo"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void to_unquote_str_shorter_than_two_should_throw_exception() throws Exception {
        UnderscoreString.unquote("");
    }

    @Test
    public void numberFormat() throws Exception {
        assertThat(UnderscoreString.numberFormat(9000), is("9,000"));
        assertThat(UnderscoreString.numberFormat(9000, 0), is("9,000"));
        assertThat(UnderscoreString.numberFormat(1000000.754, 2), is("1,000,000.75"));
        assertThat(UnderscoreString.numberFormat(1000000.756, 2), is("1,000,000.75"));
    }

    @Test
    public void strRight() throws Exception {
        assertThat(UnderscoreString.strRight(null, "_"), is(""));
        assertThat(UnderscoreString.strRight("", "_"), is(""));
        assertThat(UnderscoreString.strRight("This_is_a_test_string", "_"), is("is_a_test_string"));
        assertThat(UnderscoreString.strRight("This_is_a_test_string", "string"), is(""));
        assertThat(UnderscoreString.strRight("This_is_a_test_string", ""), is("This_is_a_test_string"));
        assertThat(UnderscoreString.strRight("This_is_a_test_string", "-"), is("This_is_a_test_string"));
    }

    @Test
    public void strRightBack() throws Exception {
        assertThat(UnderscoreString.strRightBack(null, "_"), is(""));
        assertThat(UnderscoreString.strRightBack("", "_"), is(""));
        assertThat(UnderscoreString.strRightBack("This_is_a_test_string", "_"), is("string"));
        assertThat(UnderscoreString.strRightBack("This_is_a_test_string", "string"), is(""));
        assertThat(UnderscoreString.strRightBack("This_is_a_test_string", ""), is("This_is_a_test_string"));
        assertThat(UnderscoreString.strRightBack("This_is_a_test_string", "-"), is("This_is_a_test_string"));
    }

    @Test
    public void strLeft() throws Exception {
        assertThat(UnderscoreString.strLeft(null, "_"), is(""));
        assertThat(UnderscoreString.strLeft("", "_"), is(""));
        assertThat(UnderscoreString.strLeft("This_is_a_test_string", "_"), is("This"));
        assertThat(UnderscoreString.strLeft("This_is_a_test_string", "This"), is(""));
        assertThat(UnderscoreString.strLeft("This_is_a_test_string", ""), is("This_is_a_test_string"));
        assertThat(UnderscoreString.strLeft("This_is_a_test_string", "-"), is("This_is_a_test_string"));
    }

    @Test
    public void strLeftBack() throws Exception {
        assertThat(UnderscoreString.strLeftBack(null, "_"), is(""));
        assertThat(UnderscoreString.strLeftBack("", "_"), is(""));
        assertThat(UnderscoreString.strLeftBack("This_is_a_test_string", "_"), is("This_is_a_test"));
        assertThat(UnderscoreString.strLeftBack("This_is_a_test_string", "This"), is(""));
        assertThat(UnderscoreString.strLeftBack("This_is_a_test_string", ""), is("This_is_a_test_string"));
        assertThat(UnderscoreString.strLeftBack("This_is_a_test_string", "-"), is("This_is_a_test_string"));
    }

    @Test
    public void toSentence() throws Exception {
        assertThat(UnderscoreString.toSentence(new String[]{null}), is(""));
        assertThat(UnderscoreString.toSentence(_a("")), is(""));
        assertThat(UnderscoreString.toSentence(_a("Hello", "Welcome")), is("Hello and Welcome"));
        assertThat(UnderscoreString.toSentence(_a("Hello", "Welcome", "good morning")), is("Hello, Welcome and good morning"));
        assertThat(UnderscoreString.toSentence(_a("Hello", "Welcome", "good morning", "good morning")),
                is("Hello, Welcome, good morning and good morning"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void empty_args_should_throw_exception_with_toSentence() throws Exception {
        UnderscoreString.toSentence(new String[]{});
    }

    @Test
    public void truncate() throws Exception {
        assertThat(UnderscoreString.truncate(null, 0, "..."), is("..."));
        assertThat(UnderscoreString.truncate("", 0, "..."), is("..."));
        assertThat(UnderscoreString.truncate("Hello World", 5, "..."), is("Hello..."));
        assertThat(UnderscoreString.truncate("Hello", 5, ""), is("Hello"));
    }

    @Test
    public void lpad() throws Exception {
        assertThat(UnderscoreString.lpad(null, 8), is("        "));
        assertThat(UnderscoreString.lpad("", 8), is("        "));
        assertThat(UnderscoreString.lpad("Hello", 8), is("   Hello"));
        assertThat(UnderscoreString.lpad("Hello", 8, '-'), is("---Hello"));
    }

    @Test
    public void rpad() throws Exception {
        assertThat(UnderscoreString.rpad(null, 8), is("        "));
        assertThat(UnderscoreString.rpad("", 8), is("        "));
        assertThat(UnderscoreString.rpad("Hello", 8), is("Hello   "));
        assertThat(UnderscoreString.rpad("Hello", 8, '-'), is("Hello---"));
    }

    @Test
    public void lrpad() throws Exception {
        assertThat(UnderscoreString.lrpad(null, 8), is("        "));
        assertThat(UnderscoreString.lrpad("", 8), is("        "));
        assertThat(UnderscoreString.lrpad("1", 8), is("    1   "));
        assertThat(UnderscoreString.lrpad("1", 8, '-'), is("----1---"));
    }

    @Test
    public void words() throws Exception {
        assertThat(UnderscoreString.words(null), is(_a("")));
        assertThat(UnderscoreString.words(""), is(_a("")));
        assertThat(UnderscoreString.words("I love you!"), is(_a("I", "love", "you!")));
        assertThat(UnderscoreString.words("I    love   you!"), is(_a("I", "love", "you!")));
        assertThat(UnderscoreString.words("I_love_you!"), is(_a("I", "love", "you!")));
        assertThat(UnderscoreString.words("I-love-you!"), is(_a("I", "love", "you!")));
        assertThat(UnderscoreString.words("I--love--you!"), is(_a("I", "love", "you!")));
    }

    @Test
    public void prune() throws Exception {
        assertThat(UnderscoreString.prune(null, 1), is(""));
        assertThat(UnderscoreString.prune("", 1), is(""));
        assertThat(UnderscoreString.prune("hello, world", 5), is("hello..."));
        assertThat(UnderscoreString.prune("hello, world", 13), is("hello, world"));
        assertThat(UnderscoreString.prune("hello, world world", 17), is("hello, world..."));
        assertThat(UnderscoreString.prune("hello, world", 8), is("hello..."));
        assertThat(UnderscoreString.prune("hello, world", 13), is("hello, world"));
        assertThat(UnderscoreString.prune("hello, welcome to world", 14), is("hello, welcome..."));
        assertThat(UnderscoreString.prune("Hello, cruel world", 15), is("Hello, cruel..."));
        assertThat(UnderscoreString.prune("Hellocruelworld", 5), is("Hellocruelworld"));
    }

    @Test
    public void isBlank() throws Exception {
        assertTrue(UnderscoreString.isBlank(""));
        assertTrue(UnderscoreString.isBlank("  "));
        assertTrue(UnderscoreString.isBlank("\n"));
        assertTrue(UnderscoreString.isBlank(null));
        assertFalse(UnderscoreString.isBlank("a"));
        assertFalse(UnderscoreString.isBlank(" a b "));
    }

    @Test
    public void replaceAll() throws Exception {
        assertThat(UnderscoreString.replaceAll("a", "a", "b"), is("b"));
        assertThat(UnderscoreString.replaceAll("aa", "a", "b"), is("bb"));
        assertThat(UnderscoreString.replaceAll("aca", "a", "b"), is("bcb"));
        assertThat(UnderscoreString.replaceAll("ccc", "a", "b"), is("ccc"));
        assertThat(UnderscoreString.replaceAll("AAa", "a", "b"), is("AAb"));
        assertThat(UnderscoreString.replaceAll("Aa", "a", "b", true), is("bb"));
        assertThat(UnderscoreString.replaceAll("foo bar foo", "foo", "moo"), is("moo bar moo"));
        assertThat(UnderscoreString.replaceAll("foo bar\n foo", "foo", "moo"), is("moo bar\n moo"));
        assertThat(UnderscoreString.replaceAll("foo bar FoO", "foo", "moo", true), is("moo bar moo"));
        assertThat(UnderscoreString.replaceAll("", "a", "b"), is(""));
        assertThat(UnderscoreString.replaceAll(null, "a", "b"), is(""));
    }

    @Test
    public void swapCase() throws Exception {
        assertThat(UnderscoreString.swapCase(null), is(""));
        assertThat(UnderscoreString.swapCase(""), is(""));
        assertThat(UnderscoreString.swapCase("AaBbCcDdEe"), is("aAbBcCdDeE"));
        assertThat(UnderscoreString.swapCase("Hello World"), is("hELLO wORLD"));
        assertThat(UnderscoreString.swapCase("ß"), is("SS"));
        assertThat(UnderscoreString.swapCase("Ååberg"), is("åÅBERG"));
        assertThat(UnderscoreString.swapCase(""), is(""));
        assertThat(UnderscoreString.swapCase(null), is(""));
    }

    @Test
    public void naturalCmp() throws Exception {
        assertThat(UnderscoreString.naturalCmp("abc", "123"), is(1));
        assertThat(UnderscoreString.naturalCmp("123", "abc"), is(-1));
        assertThat(UnderscoreString.naturalCmp("r69", "r9"), is(1));
        assertThat(UnderscoreString.naturalCmp("r9", "r69"), is(-1));
        assertThat(UnderscoreString.naturalCmp("15a123", "15a122"), is(1));
        assertThat(UnderscoreString.naturalCmp("abc", "abc"), is(0));
        assertThat(UnderscoreString.naturalCmp("15ac", "15ac32b"), is(-1));
        assertThat(UnderscoreString.naturalCmp("15.05", "15"), is(1));
        assertThat(UnderscoreString.naturalCmp("abc", null), is(1));
        assertThat(UnderscoreString.naturalCmp(null, "abc"), is(-1));
    }

    @Test
    public void naturalSort() throws Exception {
        String[] arr = new String[]{"foo2", "foo1", "foo10", "foo30", "foo100", "foo10bar"};
        String[] sortedArr = new String[]{"foo1", "foo2", "foo10", "foo10bar", "foo30", "foo100"};
        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String str0, String str1) {
                return UnderscoreString.naturalCmp(str0, str1);
            }
        });

        assertThat(arr, is(sortedArr));
    }

    @Test
    public void dedent() throws Exception {
        assertThat(UnderscoreString.dedent(null), is(""));
        assertThat(UnderscoreString.dedent(""), is(""));
        assertThat(UnderscoreString.dedent("Hello\nWorld"), is("Hello\nWorld"));
        assertThat(UnderscoreString.dedent("    Hello\n  World"), is("  Hello\nWorld"));
        assertThat(UnderscoreString.dedent("  Hello\nWorld"), is("  Hello\nWorld"));
        assertThat(UnderscoreString.dedent("  Hello\n    World"), is("Hello\n  World"));
        assertThat(UnderscoreString.dedent("\t\tHello\tWorld"), is("Hello\tWorld"));
        assertThat(UnderscoreString.dedent("\t\tHello\n\t\tWorld"), is("Hello\nWorld"));
        assertThat(UnderscoreString.dedent("Hello\n\t\tWorld"), is("Hello\n\t\tWorld"));
        assertThat(UnderscoreString.dedent(null), is(""));
        assertThat(UnderscoreString.dedent(""), is(""));
        assertThat(UnderscoreString.dedent("\n"), is("\n"));
    }

    @Test
    public void commonPrefix() throws Exception {
        assertThat(UnderscoreString.commonPrefix("123", "321"), is(""));
        assertThat(UnderscoreString.commonPrefix("123456", "123o8yuidfg"), is("123"));
        assertThat(UnderscoreString.commonPrefix("Åffø123456", "Åfføo8yuidfg"), is("Åffø"));
        assertThat(UnderscoreString.commonPrefix("hello", "Helloo", true), is("Hello"));
        assertThat(UnderscoreString.commonPrefix("Hello", "helloo", true), is("hello"));
        assertThat(UnderscoreString.commonPrefix("Hello", "helloo", false), is(""));
    }

    @Test
    public void commonSuffix() throws Exception {
        assertThat(UnderscoreString.commonSuffix("123", "321"), is(""));
        assertThat(UnderscoreString.commonSuffix("456123", "1414123"), is("123"));
        assertThat(UnderscoreString.commonSuffix("123456Åffø", "o8yuidfgÅffø"), is("Åffø"));
        assertThat(UnderscoreString.commonSuffix("hello", "hello"), is("hello"));
        assertThat(UnderscoreString.commonSuffix("hello", "hellO", true), is("hellO"));
        assertThat(UnderscoreString.commonSuffix("Hello", "hello", true), is("hello"));
    }

    @Test
    public void screamingUnderscored() throws Exception {
        assertThat(UnderscoreString.screamingUnderscored(null), is(""));
        assertThat(UnderscoreString.screamingUnderscored(""), is(""));
        assertThat(UnderscoreString.screamingUnderscored("the-underscored-string-method"), is("THE_UNDERSCORED_STRING_METHOD"));
        assertThat(UnderscoreString.screamingUnderscored("theUnderscoredStringMethod"), is("THE_UNDERSCORED_STRING_METHOD"));
        assertThat(UnderscoreString.screamingUnderscored("TheUnderscoredStringMethod"), is("THE_UNDERSCORED_STRING_METHOD"));
        assertThat(UnderscoreString.screamingUnderscored("The-Underscored_String_-Method"), is("THE_UNDERSCORED_STRING_METHOD"));
        assertThat(UnderscoreString.screamingUnderscored("HTTPRequest"), is("HTTP_REQUEST"));
        assertThat(UnderscoreString.screamingUnderscored("setID"), is("SET_ID"));
        assertThat(UnderscoreString.screamingUnderscored("SCREAMING_UNDERSCORED"), is("SCREAMING_UNDERSCORED"));
    }

    @Test
    public void stripAccents() throws Exception {
        assertThat(UnderscoreString.stripAccents(null), is(""));
        assertThat(UnderscoreString.stripAccents(""), is(""));
        assertThat(UnderscoreString.stripAccents("Et ça sera sa moitié"), is("Et ca sera sa moitie"));
        assertThat(UnderscoreString.stripAccents("ąàáäâãåæăćčĉęèéëêĝĥìíïîĵľńňòóöőôõøśșšŝťțŭùúüűûñÿýçżźž"), is("aaaaaaaæaccceeeeeghiiiijlnnooooooøssssttuuuuuunyyczzz"));
    }

    @Test
    public void pascalize() throws Exception {
        assertThat(UnderscoreString.pascalize(null), is(""));
        assertThat(UnderscoreString.pascalize(""), is(""));
        assertThat(UnderscoreString.pascalize("PascalCase"), is("PascalCase"));
        assertThat(UnderscoreString.pascalize("setID"), is("SetId"));
        assertThat(UnderscoreString.pascalize("HTTPRequest"), is("HttpRequest"));
        assertThat(UnderscoreString.pascalize("underscore_case"), is("UnderscoreCase"));
        assertThat(UnderscoreString.pascalize("SCREAMING_UNDERSCORED_CASE"), is("ScreamingUnderscoredCase"));
    }

    @Test
    public void translate() throws Exception {
        assertThat(UnderscoreString.translate("ababa", new HashMap<Character, Character>(){{put('a', 'b');}}), is("bbbbb"));
        assertThat(UnderscoreString.translate("ababa", new HashMap<Character, Character>(){{put('a', 'b'); put('b', 'a');}}), is("babab"));
        assertThat(UnderscoreString.translate("ababa", new HashMap<Character, Character>(){{put('a', 'b'); put('b', 'a');}}), is("babab"));
        assertThat(UnderscoreString.translate("ababa", new HashMap<Character, Character>(){{put('a', 'b');}}, new HashSet<Character>(){{add('b');}}), is("bbb"));
        assertThat(UnderscoreString.translate("xababa", new HashMap<Character, Character>(){{put('a', null);}}, new HashSet<Character>(){{add('b');}}), is("x"));
    }

    @Test
    public void mixedCase() throws Exception {
        assertFalse(UnderscoreString.mixedCase(null).isPresent());
        assertFalse(UnderscoreString.mixedCase("").isPresent());
        assertFalse(UnderscoreString.mixedCase("1AB").isPresent());
        assertTrue(UnderscoreString.mixedCase("1aB").isPresent());
        assertThat(UnderscoreString.mixedCase("1aB").get(), is("1aB"));
        assertFalse(UnderscoreString.mixedCase("1234").isPresent());
        assertTrue(UnderscoreString.mixedCase("FooBar").isPresent());
        assertThat(UnderscoreString.mixedCase("FooBar").get(), is("FooBar"));
    }

    @Test
    public void collapseWhitespaces() throws Exception {
        assertThat(UnderscoreString.collapseWhitespaces(null), is(""));
        assertThat(UnderscoreString.collapseWhitespaces(""), is(""));
        assertThat(UnderscoreString.collapseWhitespaces("foo    bar    baz"), is("foo bar baz"));
        assertThat(UnderscoreString.collapseWhitespaces("  foo    bar    baz  "), is(" foo bar baz "));
    }

    @Test
    public void ascii() throws Exception {
        assertTrue(UnderscoreString.ascii("ascii").isPresent());
        assertThat(UnderscoreString.ascii("ascii").get(), is("ascii"));
        assertFalse(UnderscoreString.ascii("Et ça sera sa moitié").isPresent());
    }

    @Test
    public void chomp() throws Exception {
        assertThat(UnderscoreString.chomp(""), is(""));
        assertThat(UnderscoreString.chomp(null), is(""));
        assertThat(UnderscoreString.chomp("foo"), is("foo"));
        assertThat(UnderscoreString.chomp("foo\n"), is("foo"));
        assertThat(UnderscoreString.chomp("foo\r\n"), is("foo"));
        assertThat(UnderscoreString.chomp("foo\n\r"), is("foo\n"));
    }

    @Test
    public void startsWith() throws Exception {
        assertTrue(UnderscoreString.startsWith("foo", "foo"));
        assertFalse(UnderscoreString.startsWith("foo", "foobar"));
        assertFalse(UnderscoreString.startsWith("Foo", "foo"));
        assertTrue(UnderscoreString.startsWith("Foo", "foo", true));
        assertFalse(UnderscoreString.startsWith("ß", "SS", true));
        assertFalse(UnderscoreString.startsWith("ß", "ss", true));
    }

    @Test
    public void endsWith() throws Exception {
        assertTrue(UnderscoreString.endsWith("foobar", "bar"));
        assertFalse(UnderscoreString.endsWith("fooBar", "bar"));
        assertTrue(UnderscoreString.endsWith("fooBar", "bar", true));
        assertTrue(UnderscoreString.endsWith("aß", "ß"));
        assertTrue(UnderscoreString.endsWith("aß", "ß", true));
    }

    @Test
    public void levenshtein() throws Exception {
        assertThat(UnderscoreString.levenshtein("Godfather", "Godfather"), is(0));
        assertThat(UnderscoreString.levenshtein("Godfather", "Godfathe"), is(1));
        assertThat(UnderscoreString.levenshtein("Godfather", "odfather"), is(1));
        assertThat(UnderscoreString.levenshtein("Godfather", "godfather"), is(1));
        assertThat(UnderscoreString.levenshtein("Godfather", "Gdfthr"), is(3));
        assertThat(UnderscoreString.levenshtein("seven", "eight"), is(5));
        assertThat(UnderscoreString.levenshtein("因為我是中國人所以我會說中文", "因為我是英國人所以我會說英文"), is(2));
        assertThat(UnderscoreString.levenshtein("lol", null), is(3));
        assertThat(UnderscoreString.levenshtein(null, "lol"), is(3));
    }

    @Test
    public void hamming() throws Exception {
        assertThat(UnderscoreString.hamming("karolin", "kathrin"), is(3));
        assertThat(UnderscoreString.hamming("karolin", "kerstin"), is(3));
        assertThat(UnderscoreString.hamming("1011101", "1001001"), is(2));
        assertThat(UnderscoreString.hamming("2173896", "2233796"), is(3));
        assertThat(UnderscoreString.hamming(null, ""), is(0));
        assertThat(UnderscoreString.hamming("", null), is(0));
    }

    @Test
    public void longestCommonSubstring() throws Exception {
        assertThat(UnderscoreString.longestCommonSubstring("fooquxbar", "foobar"), is(_s("foo", "bar")));
        assertThat(UnderscoreString.longestCommonSubstring("FOOquxbar", "foobar"), is(_s("bar")));
        assertThat(UnderscoreString.longestCommonSubstring("foo", "bar"), is(UnderscoreStringTest.<String>_s()));
        assertThat(UnderscoreString.longestCommonSubstring(null, ""), is(UnderscoreStringTest.<String>_s()));
        assertThat(UnderscoreString.longestCommonSubstring("", null), is(UnderscoreStringTest.<String>_s()));
    }

    private static <T> T[] _a(T... args) {
        return args;
    }

    private static <T> Set<T> _s(T... args) {
        return Sets.newHashSet(args);
    }

}
