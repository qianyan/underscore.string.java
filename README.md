underscore.string.java
[![Build Status](https://travis-ci.org/qianyan/underscore.string.java.svg?branch=jigsaw)](https://travis-ci.org/qianyan/underscore.string.java)
===

A library for Java String manipulation

## Latest Release
* __0.2.0__

More details in [Release Notes](doc/ReleaseNotes.md)

[An introduction blog here](https://lambeta.com/2017/09/06/Underscore-string-java/)

[sync gitee repo](https://gitee.com/lambeta/underscore.string.java)
## Prerequisites
* java = 1.9
* guava 18.0

## Installation
### gradle
```groovy
repositories {
    mavenCentral()
}

dependencies {
    compile 'com.lambeta:underscore.string.java:0.2.0'
}
```
### maven
```xml
<dependency>
    <groupId>com.lambeta</groupId>
    <artifactId>underscore.string.java</artifactId>
    <version>0.2.0</version>
</dependency>
```
## API
### capitalize
Converts first letter of the string to uppercase.
```java
import static com.lambeta.UnderscoreString.capitalize;

capitalize(" hello ");
// -> "Hello"
```
### decapitalize
Converts first letter of the string to lowercase.
```java
import static com.lambeta.UnderscoreString.decapitalize;

decapitalize(" Hello ");
// -> "hello"
decapitalize("HELLO");
// -> "hELLO"
```
### slugify
Transform text into an ascii slug which can be used in safely in URLs.
Replaces whitespaces, accentuated, and special characters with a dash. Limited set of non-ascii characters are transformed to similar versions in the ascii character set such as ä to a.
```java
import static com.lambeta.UnderscoreString.slugify;

slugify(" hello World!");
// -> "hello-world"
slugify("Un éléphant àß l\'orée du bois");
// -> "un-elephant-ass-l-oree-du-bois"
```
### count
Returns number of occurrences of substring in string.
```java
import static com.lambeta.UnderscoreString.count;

count("Hello world", "l");
// -> 3
```
### trim
Trims defined characters from begining and ending of the string. Defaults to whitespace characters.
```java
import static com.lambeta.UnderscoreString.trim;

trim(" foo ");
// -> "foo"
trim("foo", "f");
// -> "oo"
```
### ltrim
Left trim. Similar to trim, but only for left side.
```java
import static com.lambeta.UnderscoreString.ltrim;

ltrim(" foo");
// -> "foo"
ltrim("foof", "f");
// -> "oof"
```
### rtrim
Right trim. Similar to trim, but only for right side.
```java
import static com.lambeta.UnderscoreString.rtrim;

rtrim("foo ");
// -> "foo"
rtrim("foof", "f");
// -> "foo"
```
### repeat
Repeats a string *count* times.
```java
import static com.lambeta.UnderscoreString.repeat;

repeat("foo");
// -> ""
repeat("foo", 3);
// -> "foofoofoo"
repeat("foo", 3, 3);
// -> "foo3foo3foo"
repeat("foo", 3, new Person("ryan", "male"));
// -> "fooPerson{name=ryan, gender=male}fooPerson{name=ryan, gender=male}foo"
```
### join
Joins strings together with given separator.
```java
import static com.lambeta.UnderscoreString.join;

join("", "foo", "bar");
// -> "foobar"
join("", null, "bar")
// -> "bar"
```
### reverse
Return reversed string.
```java
import static com.lambeta.UnderscoreString.reverse;

reverse("foo");
// -> "oof"
```
### clean
Trim and replace multiple spaces with a single space.
```java
import static com.lambeta.UnderscoreString.clean;

clean(" foo    bar   ");
// -> "foo bar"
```
### chop
Chop given string to pieces.
```java
import static com.lambeta.UnderscoreString.chop;

chop("whitespace", 2);
// -> ["wh", "it", "es", "pa", "ce"]
```
### splice
Like an array splice. `splice(start, deleteCount, item)`.
```java
import static com.lambeta.UnderscoreString.splice;

splice("whitespace", 5, 5, "shift");
// -> "whiteshift"
```
### pred
Returns the predecessor to string.

```java
import static com.lambeta.UnderscoreString.pred;

pred('2');
// -> '1'
```
### succ
Returns the successor to string.
```java
import static com.lambeta.UnderscoreString.succ;

succ('a');
// -> 'b'
```
### titleize
Capitalize each word of the given string like a title.
```java
import static com.lambeta.UnderscoreString.titleize;

titleize("the titleize string method");
// -> "The Titleize String Method"
```
### camelize
Converts underscored or dasherized string to a camelized one. Begins with a lower case letter unless it starts with an underscore, dash or an upper case letter.
```java
import static com.lambeta.UnderscoreString.camelize;

camelize("the_camelize_string_method");
// -> "theCamelizeStringMethod"
camelize("_webkit   _  transform ");
// -> "WebkitTransform"
```
### dasherize
Converts a underscored or camelized string into an dasherized one.
```java
import static com.lambeta.UnderscoreString.dasherize;

dasherize("the_dasherize_string_method");
// -> "the-dasherize-string-method"
```
### underscored
Converts a camelized or dasherized string into an underscored one.
```java
import static com.lambeta.UnderscoreString.underscored;

underscored("the-underscored-string-method");
// -> "the_underscored_string_method"
```
### classify
Converts string to camelized class name. First letter is always upper case.
```java
import static com.lambeta.UnderscoreString.classify;

classify("some_class_name");
// -> "SomeClassName"
```
### humanize
Converts an underscored, camelized, or dasherized string into a humanized one. 
```java
import static com.lambeta.UnderscoreString.humanize;

humanize("the humanize string method");
// -> "The humanize string method"
humanize("the humanize_id string method");
// -> "The humanize id string method"
```
### surround
Surround a string with another string.
```java
import static com.lambeta.UnderscoreString.surround;

surround("foo", "|");
// -> "|foo|"
```
### quote
Quotes a string. quoteChar defaults to ".
```java
import static com.lambeta.UnderscoreString.quote;

quote("foo");
// -> "\"foo\""
```
### unquote
nquotes a string. quoteChar defaults to ".
```java
import static com.lambeta.UnderscoreString.unquote;

unquote("\"foo\"");
// -> "foo"
unquote("'foo'", '\'');
// -> "foo"
```
### numberFormat
Formats the numbers.
```java
import static com.lambeta.UnderscoreString.numberFormat;

numberFormat(9000);
// -> "9,000"
```
### strRight
Searches a string from left to right for a pattern and returns a substring consisting of the characters in the string that are to the right of the pattern or all string if no match found.
```java
import static com.lambeta.UnderscoreString.strRight;

strRight("This_is_a_test_string", "_");
// -> "is_a_test_string"
```
### strRightBack
Searches a string from right to left for a pattern and returns a substring consisting of the characters in the string that are to the right of the pattern or all string if no match found.
```java
import static com.lambeta.UnderscoreString.strRightBack;

strRightBack("This_is_a_test_string", "_");
// -> "string"
```
### strLeft
Searches a string from left to right for a pattern and returns a substring consisting of the characters in the string that are to the left of the pattern or all string if no match found.
```java
import static com.lambeta.UnderscoreString.strLeft;

strLeft("This_is_a_test_string", "_");
// -> "This"
```
### strLeftBack
Searches a string from right to left for a pattern and returns a substring consisting of the characters in the string that are to the left of the pattern or all string if no match found.
```java
import static com.lambeta.UnderscoreString.strLeftBack;

strLeftBack("This_is_a_test_string", "_");
// -> "This_is_a_test"
```
### toSentence
Join an array into a human readable sentence.
```java
import static com.lambeta.UnderscoreString.toSentence;

String[] words = new String[] {"Hello", "Welcome"};
toSentence(words);
// -> "Hello and Welcome"
```
### truncate
truncate given string with length.
```java
import static com.lambeta.UnderscoreString.truncate;

truncate("Hello World", 5, "...");
// -> "Hello..."
```
### lpad
left-pad a string.
```java
import static com.lambeta.UnderscoreString.lpad;

lpad("Hello", 8);
// -> "   Hello"
```
### rpad
right-pad a string.
```java
import static com.lambeta.UnderscoreString.rpad;

rpad("Hello", 8);
// -> ("Hello   ")
```
### lrpad
left/right-pad a string.
```java
import static com.lambeta.Underscorestring.lrpad;

lrpad("1", 8);
// -> "    1   "
```
### words
Split string by delimiter.
```java
import static com.lambeta.Underscorestring.words;

words("I_love_you!");
// -> [ "I", "love", "you!" ]
```
### prune
Elegant version of truncate. Makes sure the pruned string does not exceed the original length. Avoid half-chopped words when truncating.
```java
import static com.lambeta.Underscorestring.prune;

prune("Hello, cruel world", 15);
// -> "Hello, cruel..."
```
### isBlank
Determine whether given string is blank or not.
```java
import static com.lambeta.Underscorestring.isBlank;

isBlank("")
// -> true
isBlank(null);
// -> true
isBlank("\n");
// -> true
```
### replaceAll
Replace all *find str* in given string with *replacement*, if given string is null or empty, then returns empty string. The last argument *true* means ignore cases.
```java
import static com.lambeta.Underscorestring.replaceAll;

replaceAll("aca", "a", "b");
// -> "bcb"
replaceAll("Aa", "a", "b", true);
// -> "bb"
replaceAll("", "a", "b");
// -> ""
```
### swapCase
Returns a copy of the string in which all the case-based characters have had their case swapped.
```java
import static com.lambeta.Underscorestring.swapCase;

swapCase("Hello World");
// -> "hELLO wORLD"
swapCase("ß");
// -> "SS"
```
### naturalCmp
Naturally sort strings like humans would do. None numbers are compared by their ASCII values. Note: this means "a" > "A". Use .toLowerCase if this isn't to be desired.
```java
import static com.lambeta.Underscorestring.naturalCmp;

naturalCmp("abc", "123");
// -> 1
naturalCmp("15a123", "15a122");
// -> 1
naturalCmp("r9", "r69");
// -> -1
```
### dedent
Dedent unnecessary indentation.
```java
import static com.lambeta.Underscorestring.dedent;

dedent("    Hello\n  World");
// -> "  Hello\nWorld"
dedent("\t\tHello\tWorld");
// -> "Hello\tWorld"
dedent("\t\tHello\n\t\tWorld");
// -> "Hello\nWorld"
```
### commonPrefix
Returns the longest common prefix of s and s1. given *ignoreCase* as true will return common suffix of s1. 
```java
import static com.lambeta.Underscorestring.commonPrefix;

commonPrefix("123456", "123o8yuidfg");
// -> "123"
commonPrefix("Hello", "helloo", true);
// -> "hello"
```

### commonSuffix
Returns the longest common suffix of s and s1. given *ignoreCase* as true will return common suffix of s1. 
```java
import static com.lambeta.Underscorestring.commonSuffix;

commonSuffix("456123", "1414123");
// -> "123"
commonSuffix("hello", "hellO", true);
// -> "hellO"
```
### chopPrefix
Remove prefix from the start of s. Otherwise return s.
```java
import static com.lambeta.Underscorestring.chopPrefix;

chopPrefix("foo", "FOO")
// -> "foo"
chopPrefix("foo", "FOO", true);
// -> ""
```
### chopSuffix
Remove suffix from the end of s. Otherwise return s.
```java
import static com.lambeta.Underscorestring.chopSuffix;

chopSuffix("foo", "FOO");
// -> "foo"
chopSuffix("foo", "FOO", true);
// -> ""
```
### screamingUnderscored
Upper case s and use underscores to separate words.
```java
import static com.lambeta.Underscorestring.screamingUnderscored;

screamingUnderscored("The-Underscored_String_-Method");
// -> "THE_UNDERSCORED_STRING_METHOD"
screamingUnderscored("HTTPRequest");
// -> "HTTP_REQUEST"
screamingUnderscored("setID");
// -> "SET_ID"
```
### stripAccents
Strip all accents (diacritical marks) from s.
```java
import static com.lambeta.Underscorestring.stripAccents;

stripAccents("Et ça sera sa moitié");
// -> "Et ca sera sa moitie"
```
### pascalize
Upper the case first char in s and use capitalization to separate words.
```java
import static com.lambeta.Underscorestring.pascalize;

pascalize("PascalCase");
// -> "PascalCase"
```
### translate
Translate all characters in s according to the mappings found in *tmap (second arg)*.

Any characters found in the set *delete-chars (third arg)* will be pruned prior to
consulting *tmap*.

Any characters mapping to `null` in *tmap* will also be deleted.

```java
import static com.lambeta.Underscorestring.translate;

translate("ababa", Map.of('a', 'b'));
// -> "bbbbb"
translate("ababa", Map.of('a', 'b'), Set.of('b'));
// -> "bbb"
```
### mixedCase
Return `Optional<String> s` if s contains both upper and lower case letters.
```java
import static com.lambeta.Underscorestring.mixedCase;

mixedCase("1AB");
// -> Optional.<String>absent();
mixedCase("FooBar");
// -> Optional.<String>of("FooBar")
```
### collapseWhitespaces
Convert all adjacent whitespace in s to a single space.
```java
import static com.lambeta.Underscorestring.collapseWhitespaces;

collapseWhitespaces("foo    bar    baz");
// -> "foo bar baz"
```
### ascii
Return `Optional<String> s` if s only contains ASCII characters.
```java
import static com.lambeta.Underscorestring.ascii;

ascii("ascii");
// -> Optional<String>.of("ascii")
ascii("Et ça sera sa moitié");
// -> Optional<String>.absent()
```
### chomp
Return a new string with `\r\n`, `\n` or `\r` removed from the end.
```java
import static com.lambeta.Underscorestring.chomp;

chomp("foo\n");
// -> "foo"
chomp("foo\n\r");
// -> "foo\n"
```
### startsWith
Return true if s starts with prefix. If the third argument is provided as `true`, the string comparison is insensitive to case.
```java
import static com.lambeta.Underscorestring.startsWith;

startsWith("foo", "foo");
// -> true
startsWith("foo", "foobar");
// -> false
startsWith("Foo", "foo", true);
// -> true
```
### endsWith
Return true if s ends with suffix. If the third argument is provided as `true`, the string comparison is insensitive to case.
```java
import static com.lambeta.Underscorestring.endsWith;

endsWith("foobar", "bar");
// -> true
endsWith("fooBar", "bar");
// -> false
endsWith("fooBar", "bar", true);
// -> true
```
### levenshtein
Get the edit distance between s1 and s2.
```java
import static com.lambeta.Underscorestring.levenshtein;

levenshtein("Godfather", "Godfather");
// -> 0
levenshtein("Godfather", "Gdfthr");
// -> 3
levenshtein("因為我是中國人所以我會說中文", "因為我是英國人所以我會說英文");
// -> 2
levenshtein("lol", null);
// -> 3
levenshtein(null, "lol");
// -> 3
```
### hamming
Get the hamming distance between s1 and s2.
refer to [hamming distance.](https://en.wikipedia.org/wiki/Hamming_distance)
```java
import static com.lambeta.Underscorestring.hamming;

hamming("karolin", "kerstin");
// -> 3
```
### longestCommonSubstring
Returns the set of the longest common substrings in s1 and s2.

This implementation uses dynamic programming, and not a generalized
suffix tree, so the runtime is O(nm).
```java
import static com.lambeta.Underscorestring.longestCommonSubstring;

longestCommonSubstring("fooquxbar", "foobar");
// -> {"foo", "bar"} 
```
## New Features in 0.2.1-SNAPSHOT
### gradle
```gradle
repositories {
    maven {
        url 'https://oss.sonatype.org/content/groups/public'
    }
}

dependencies {
    compile ("com.lambeta:underscore.string.java:0.2.1-SNAPSHOT")
}

```
### maven
```xml
<repositories>
    <repository>
      <id>my-repo</id>
      <name>sonatype</name>
      <url>https://oss.sonatype.org/content/groups/public</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.lambeta</groupId>
    <artifactId>underscore.string.java</artifactId>
    <version>0.2.1-SNAPSHOT</version>
</dependency>
```
### replaceZeroWidthDelimiterWith
Replaces the zero width delimiter between two Uppercase characters or character and number and so on.
```java
import static com.lambeta.Underscorestring.replaceZeroWidthDelimiterWith;

replaceZeroWidthDelimiterWith("GL11Version", " ");
// -> "GL 11 Version"
replaceZeroWidthDelimiterWith("SimpleXMLParser", " ");
// -> "Simple XML Parser"
```
