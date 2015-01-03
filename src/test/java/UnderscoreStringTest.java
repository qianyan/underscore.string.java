import UnderscoreString.UnderscoreString;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UnderscoreStringTest {
    @Test
    public void capitalize() throws Exception {
        assertThat(UnderscoreString.capitalize(" hello "), is("Hello"));
    }

    @Test
    public void slugify() throws Exception {
        assertThat(UnderscoreString.slugify(" hello World!"), is("hello-world"));
    }

    @Test
    public void trim() throws Exception {
        assertThat(UnderscoreString.trim(" foo "), is("foo"));
        assertThat(UnderscoreString.trim("foo", "f"), is("oo"));
        assertThat(UnderscoreString.trim("fooff", "f"), is("oo"));
        assertThat(UnderscoreString.trim("_-foo-_", "_-"), is("foo"));
    }

    @Test
    public void ltrim() throws Exception {
        assertThat(UnderscoreString.ltrim(" foo"), is("foo"));
        assertThat(UnderscoreString.ltrim(" foo "), is("foo "));
        assertThat(UnderscoreString.ltrim("foof", "f"), is("oof"));
        assertThat(UnderscoreString.ltrim("_-foo-_", "_-"), is("foo-_"));
    }

    @Test
    public void rtrim() throws Exception {
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
        assertThat(UnderscoreString.reverse("foo"), is("oof"));
        assertThat(UnderscoreString.reverse("saippuakauppias"), is("saippuakauppias"));
    }

    @Test
    public void clean() throws Exception {
        assertThat(UnderscoreString.clean(" foo    bar   "), is("foo bar"));
    }

    @Test
    public void chop() throws Exception {
        assertThat(UnderscoreString.chop("whitespace", 2), is(_a("wh", "it", "es", "pa", "ce")));
        assertThat(UnderscoreString.chop("whitespace", 3), is(_a("whi", "tes", "pac", "e")));
        assertThat(UnderscoreString.chop("whitespace", 0), is(_a("whitespace")));
    }

    @Test
    public void splice() throws Exception {
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
        assertThat(UnderscoreString.titleize("the titleize string method"), is("The Titleize String Method"));
        assertThat(UnderscoreString.titleize("a-dash-separated-string"), is("A-Dash-Separated-String"));
        assertThat(UnderscoreString.titleize("a_dash_separated_string"), is("A_Dash_Separated_String"));
        assertThat(UnderscoreString.titleize("A-DASH-SEPARATED-STRING"), is("A-Dash-Separated-String"));
    }

    @Test
    public void camelize() throws Exception {
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
        assertThat(UnderscoreString.dasherize("the_dasherize_string_method"), is("the-dasherize-string-method"));
        assertThat(UnderscoreString.dasherize("TheDasherizeStringMethod"), is("the-dasherize-string-method"));
        assertThat(UnderscoreString.dasherize("The_Dasherize-String_-Method"), is("the-dasherize-string-method"));
        assertThat(UnderscoreString.dasherize("the dasherize string method"), is("the-dasherize-string-method"));
    }

    @Test
    public void underscored() throws Exception {
        assertThat(UnderscoreString.underscored("the-underscored-string-method"), is("the_underscored_string_method"));
        assertThat(UnderscoreString.underscored("theUnderscoredStringMethod"), is("the_underscored_string_method"));
        assertThat(UnderscoreString.underscored("TheUnderscoredStringMethod"), is("the_underscored_string_method"));
        assertThat(UnderscoreString.underscored("The-Underscored_String_-Method"), is("the_underscored_string_method"));
    }

    @Test
    public void classify() throws Exception {
        assertThat(UnderscoreString.classify("some_class_name"), is("SomeClassName"));
        assertThat(UnderscoreString.classify("my wonderfull class_name"), is("MyWonderfullClassName"));
    }

    @Test
    public void humanize() throws Exception {
        assertThat(UnderscoreString.humanize("the humanize string method"), is("The humanize string method"));
        assertThat(UnderscoreString.humanize("the_humanize_string_method"), is("The humanize string method"));
        assertThat(UnderscoreString.humanize("TheHumanizeStringMethod"), is("The humanize string method"));
        assertThat(UnderscoreString.humanize("the humanize_id string method"), is("The humanize id string method"));
    }

    @Test
    public void surround() throws Exception {
        assertThat(UnderscoreString.surround("'", "\""), is("\"'\""));
        assertThat(UnderscoreString.surround("foo", "|"), is("|foo|"));
        assertThat(UnderscoreString.surround("foo", ""), is("foo"));
    }

    @Test
    public void quote() throws Exception {
        assertThat(UnderscoreString.quote("foo"), is("\"foo\""));
        assertThat(UnderscoreString.quote("\"foo\""), is("\"\"foo\"\""));
    }

    @Test
    public void unquote() throws Exception {
        assertThat(UnderscoreString.unquote("\"foo\""), is("foo"));
        assertThat(UnderscoreString.unquote("\"\"foo\"\""), is("\"foo\""));
        assertThat(UnderscoreString.unquote("'foo'", '\''), is("foo"));
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
        assertThat(UnderscoreString.strRight("This_is_a_test_string", "_"), is("is_a_test_string"));
        assertThat(UnderscoreString.strRight("This_is_a_test_string", "string"), is(""));
        assertThat(UnderscoreString.strRight("This_is_a_test_string", ""), is("This_is_a_test_string"));
        assertThat(UnderscoreString.strRight("This_is_a_test_string", "-"), is("This_is_a_test_string"));
    }

    @Test
    public void strRightBack() throws Exception {
        assertThat(UnderscoreString.strRightBack("This_is_a_test_string", "_"), is("string"));
        assertThat(UnderscoreString.strRightBack("This_is_a_test_string", "string"), is(""));
        assertThat(UnderscoreString.strRightBack("This_is_a_test_string", ""), is("This_is_a_test_string"));
        assertThat(UnderscoreString.strRightBack("This_is_a_test_string", "-"), is("This_is_a_test_string"));
    }

    @Test
    public void strLeft() throws Exception {
        assertThat(UnderscoreString.strLeft("This_is_a_test_string", "_"), is("This"));
        assertThat(UnderscoreString.strLeft("This_is_a_test_string", "This"), is(""));
        assertThat(UnderscoreString.strLeft("This_is_a_test_string", ""), is("This_is_a_test_string"));
        assertThat(UnderscoreString.strLeft("This_is_a_test_string", "-"), is("This_is_a_test_string"));
    }

    @Test
    public void strLeftBack() throws Exception {
        assertThat(UnderscoreString.strLeftBack("This_is_a_test_string", "_"), is("This_is_a_test"));
        assertThat(UnderscoreString.strLeftBack("This_is_a_test_string", "This"), is(""));
        assertThat(UnderscoreString.strLeftBack("This_is_a_test_string", ""), is("This_is_a_test_string"));
        assertThat(UnderscoreString.strLeftBack("This_is_a_test_string", "-"), is("This_is_a_test_string"));
    }

    @SafeVarargs
    private static <T> T[] _a(T... args) {
        return args;
    }
}
