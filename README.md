# And Sand Language

The And Sand Language (And Sand derived from &), is a small programming language I made in order to learn how making a programming language works. It is tiny, very strange, and quite bad. I may make another language in the future, as this was more of a test.

# Variables

All variables are strings, because who needs anything else. Also there is no math in this programming language. Variables can be declared with the `global` and `local` functions depending on the scope of availability. If you want to declare a `global` variable that can be accessed anywhere in the program, you use `global`. `local` is only accessible in the current function. Variables can share the same name as functions, as well as between local and global.

# Functions

There are a small number of builtin functions present, but they can be used to make limited programs.

## Builtin Functions

* print and println are both present and do exactly what you would think.<br>
`print Hello World!&`<br>
`println Hello World!&`
* The function keyio allows you to get user input from the console and assign it to a local variable `var`.<br>
`keyio var`
* The function global lets you assign a global variable.<br>
`global var value&`
* The function local lets you assign a local variable.<br>
`local var value&`
* The function equals lets you check if two values are equal.<br>
`equal value1& value2&`

## User Functions

User defined functions do not have parameters or return types. They are defined as such:

`fun name`<br>
`content`<br>
`end`

They can be called in two ways. The first is the 'normal' call, with syntax such as:

`%name`

And second is the conditional call. You see, this language has no if statement, so instead, you have the conditional function call.

`@name value`

Where the function will be called if the string `value` is `true`.

# The Ampersand

You may be wondering two things about the character. Why is the language named after it? Why is it after almost every line? That is because the ampersand is the end of string character. There is no character to start a string, that is based on context. The ampersand is the end of string. But wait, how do you check if a variable equals a value or another variable? Simple. You can insert a variable's value into a string by putting `^` or `*` in front of it for `global` and `local` variables respectively.

For example, to check if a local variable `a` is equal to a global variable `b`, you can do the following.

`equals truth *a& ^b&`

The string `true` or `false` will be placed into local variable `truth`. Then, you can do a conditional function call with `truth`.

`@funcname *truth&`

# Mathematical Operators

Don't exist.

# Examples

The examples are in the repo as `greetings.ands` and `test.ands`.