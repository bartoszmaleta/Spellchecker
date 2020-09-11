Have you ever thought how spellchecker works?

Here's a complete project description: http://www.ics.uci.edu/~goodrich/teach/ics23/LabManual/LostForWords/

1. Run "SpellCheck" to see options and build project.
2. Go to "../spellchekcer/target/classes"

3. Enter in terminal the appropriate command.

Usage: java SpellCheck [options] inputFilename

    options
    -------
    -degenerate
        runs the spell checker with the DegenerateStringHasher algorithm

    -lousy
        runs the spell checker with a LousyStringHasher

    -better
        runs the spell checker with a better word hashing algorithm

    -quiet
        runs the spell checker without any output, reporting the total time
        taken to load the dictionary and perform the spell check

    -wordlist wordlistFilename
        runs the spell checker using the wordlist specified, rather than
        the default (wordlist.txt)

    example
    -------
    java SpellCheck -wordlist wordlist.txt -better -quiet big-test.txt