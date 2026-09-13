# Shunting Yard

> a small Java calculator because uhm parsing math expressions manually sounded like a good idea which is not because i already have a calculator app in my phone

## About

`ShuntingYard` is a small calculator written in `Java` that uses the **Shunting Yard algorithm** to parse mathematical expressions and evaluate them...

i made this mainly to practice working with:

- lexical analysis
- tokenization
- infix -> postfix (RPN) conversion
- operator precedence
- stack-based evaluation
- structuring a Java project without a build tool (prefer this over Makefile, bye...)

## Features

- basic arithmetic operations
- operator precedence
- parentheses
- decimal numbers
- infix -> postfix (RPN) conversion
- division and modulo
- interactive terminal interface
- error handling for invalid characters and division by zero
- unary operators
- exponentiation

## Supported Operators

| Operator | Operation      |
| -------- | -------------- |
| `+`      | addition       |
| `-`      | subtraction    |
| `*`      | multiplication |
| `/`      | division       |
| `%`      | modulo         |
| `^`      | exponentiation |

parentheses can be used to control the order of operations:

```text
(10 + 5) / 3
```

## Requirements

- `Java`
- `JDK 26`

## Running

clone the repository:

```bash
git clone https://github.com/jayywashere/shunting-yard
cd ShuntingYard
```

compile the project with:

```powershell
javac -d out (Get-ChildItem -Recurse -Filter *.java src).FullName
```

or:

```powershell
.\build.bat
# or \dev.bat for both build + run
```

then run it with:

```powershell
java -cp out app.Main
```

or:

```powershell
.\run.bat
# or \dev.bat for both build + run
```

## Usage

once the program starts, enter an expression:

```text
Shunting Yard Calculator
Type an expression to calculate, or "exit" to quit.

> 1
1.00

> 5 + 5
10.00

> -5 + 5
0.00

> 780 / 2
390.00

> 5 % 6
5.00

> 123 - 1234
-1,111.00

> 2 ^ 3
8.00

> exit
Goodbye!
```

## How It Works

the calculator processes an expression in a few stages:

```text
expression
    ↓ --> got this uhmm down-arrow symbol from google because my keyboard does not have it and my keyboard does not have a numpad.
Lexer
    ↓
tokens
    ↓
Shunting Yard / RPN
    ↓
postfix tokens
    ↓
Evaluator
    ↓
result
```

### Lexer

the `Lexer` reads the expression and turns it into tokens such as numbers, operators, and parentheses.

### RPN

the `Rpn` class converts the infix expression into **Reverse Polish Notation** using the Shunting Yard algorithm.

for example:

```text
2 + 3 * 4
```

becomes:

```text
2 3 4 * +
```

this makes operator precedence straightforward to evaluate.

### Evaluator

the `Evaluator` processes the RPN tokens using a stack and performs the actual arithmetic.

## Why I Made This

i wanted an actual Java project that wasn't just another tiny program printing something to the terminal.

i also wanted to understand how expression parsing actually works instead of relying on some existing calculator/parser library.

so naturally, i made a calculator and voluntarily signed myself up for tokenization and stacks.

bonus: this is actually my first Java project, so.... i had to wrestle with both the language and the project.........

## Current Scope

- basic arithmetic
- decimal numbers
- parentheses
- operator precedence
- RPN conversion
- terminal interface

more features may be added later. (such as unary minus / negative numbers, exponentiation, variables, and functions (not the graphing functions ones, though.))

## License

See [LICENSE](LICENSE).
