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
...

