underscore.string.java
[![Build Status](https://travis-ci.org/qianyan/underscore.string.java.svg?branch=master)](https://travis-ci.org/qianyan/underscore.string.java)
[![CircleCI](https://circleci.com/gh/qianyan/underscore.string.java.svg?style=shield)](https://circleci.com/gh/qianyan/underscore.string.java)
===

[Here an introduction blog](http://lambeta.com/2017/09/06/Underscore-string-java/)

A library for Java String manipulation
## Prerequisites
* java >= 1.6
* guava 18.0

## Installation
### gradle
```groovy
repositories {
    mavenCentral()
}

dependencies {
    compile 'com.lambeta:underscore.string.java:0.0.1'
}
```
### maven
```xml
<dependency>
    <groupId>com.lambeta</groupId>
    <artifactId>underscore.string.java</artifactId>
    <version>0.0.1</version>
</dependency>
```

## Supported features
- [x] capitalize
- [x] slugify
- [x] count
- [x] trim
- [x] ltrim
- [x] rtrim
- [x] repeat
- [x] decapitalize
- [x] join
- [x] reverse
- [x] clean
- [x] chop
- [x] splice
- [x] pred
- [x] succ
- [x] titleize
- [x] camelize
- [x] dasherize
- [x] underscored
- [x] classify
- [x] humanize
- [x] quote
- [x] unquote
- [x] surround
- [x] numberFormat
- [x] strRight
- [x] strRightBack
- [x] strLeft
- [x] strLeftBack
- [x] toSentence
- [x] truncate
- [x] lpad
- [x] rpad
- [x] lrpad
- [x] words
- [x] prune
- [x] isBlank
- [x] replaceAll
- [x] swapCase
- [x] naturalSort
- [x] naturalCmp
- [x] dedent

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
```java
import static com.lambeta.UnderscoreString.slugify;

slugify(" hello World!");
// -> "hello-world"
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
import static com.lambeta.underscorestring.lrpad;

lrpad("1", 8);
// -> "    1   "
```
### words
Split string by delimiter.
```java
import static com.lambeta.underscorestring.words;

words("I_love_you!");
// -> [ "I", "love", "you!" ]
```
### prune
Elegant version of truncate. Makes sure the pruned string does not exceed the original length. Avoid half-chopped words when truncating.
```java
import static com.lambeta.underscorestring.prune;

prune("Hello, cruel world", 15);
// -> "Hello, cruel..."
```
...
